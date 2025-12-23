package com.dlg.fpc.service.util;

import com.dlg.fpc.service.config.StaticInit;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;

/**
 * sm2非对称加密算法 工具类
 */
public class SM2Util {

    /**
     * 加密
     * @param data      数据
     * @param publicKey 公钥
     * @return 加密之后的数据
     */
    public static byte[] encrypt(byte[] data, byte[] publicKey) throws Exception {
        CipherParameters pubKeyParameters = new ParametersWithRandom(publicKeyToParams("SM2", publicKey));
        SM2Engine engine = new SM2Engine();
        engine.init(true, pubKeyParameters);
        return engine.processBlock(data, 0, data.length);
    }

    /**
     * 解密
     * @param data       数据
     * @param privateKey 私钥
     * @return 解密之后的数据
     */
    public static byte[] decrypt(byte[] data, byte[] privateKey) throws Exception {
        CipherParameters privateKeyParameters = privateKeyToParams("SM2", privateKey);
        SM2Engine engine = new SM2Engine();
        engine.init(false, privateKeyParameters);
        return engine.processBlock(data, 0, data.length);
    }

    /**
     * 签名
     * @param data 数据
     * @return 签名
     */
    public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
        SM2Signer signer = new SM2Signer();
        CipherParameters param = new ParametersWithRandom(privateKeyToParams("SM2", privateKey));
        signer.init(true, param);
        signer.update(data, 0, data.length);
        return signer.generateSignature();
    }

    /**
     * 用公钥检验数字签名的合法性
     * @param data      数据
     * @param sign      签名
     * @param publicKey 公钥
     * @return 是否验证通过
     */
    public static boolean verify(byte[] data, byte[] sign, byte[] publicKey) throws Exception {
        SM2Signer signer = new SM2Signer();
        CipherParameters param = publicKeyToParams("SM2", publicKey);
        signer.init(false, param);
        signer.update(data, 0, data.length);
        return signer.verifySignature(sign);
    }

    /**
     * 私钥转换为 {@link ECPrivateKeyParameters}
     * @param key key
     * @return ec
     * @throws InvalidKeyException ex
     */
    public static ECPrivateKeyParameters privateKeyToParams(String algorithm,
                                                            byte[] key) throws InvalidKeyException,
            InvalidKeySpecException, NoSuchAlgorithmException {
        PrivateKey privateKey = generatePrivateKey(algorithm, key);
        return (ECPrivateKeyParameters) ECUtil.generatePrivateKeyParameter(privateKey);
    }

    /**
     * 生成私钥
     * @param algorithm 算法
     * @param key       key
     * @return private key
     */
    public static PrivateKey generatePrivateKey(String algorithm, byte[] key)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new PKCS8EncodedKeySpec(key);
        algorithm = getAlgorithmAfterWith(algorithm);
        return getKeyFactory(algorithm).generatePrivate(keySpec);
    }

    /**
     * 公钥转换为 {@link ECPublicKeyParameters}
     * @param key key
     * @return ec
     * @throws InvalidKeyException ex
     */
    public static ECPublicKeyParameters publicKeyToParams(String algorithm, byte[] key)
            throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException {
        PublicKey publicKey = generatePublicKey(algorithm, key);
        return (ECPublicKeyParameters) ECUtil.generatePublicKeyParameter(publicKey);
    }

    /**
     * 生成公钥
     * @param algorithm 算法
     * @param key       key
     * @return pub
     */
    public static PublicKey generatePublicKey(String algorithm, byte[] key)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new X509EncodedKeySpec(key);
        algorithm = getAlgorithmAfterWith(algorithm);
        return getKeyFactory(algorithm).generatePublic(keySpec);
    }

    /**
     * 获取用于密钥生成的算法<br>
     * 获取XXXwithXXX算法的后半部分算法，如果为ECDSA或SM2，返回算法为EC
     * @param algorithm XXXwithXXX算法
     * @return 算法
     */
    private static String getAlgorithmAfterWith(String algorithm) {
        int indexOfWith = StringUtils.lastOrdinalIndexOf(algorithm, "with",1);
        if (indexOfWith > 0) {
            algorithm = StringUtils.substring(algorithm, indexOfWith + "with".length());
        }
        if ("ECDSA".equalsIgnoreCase(algorithm) || "SM2".equalsIgnoreCase(algorithm)) {
            algorithm = "EC";
        }
        return algorithm;
    }

    /**
     * 获取{@link KeyFactory}
     * @param algorithm 非对称加密算法
     * @return {@link KeyFactory}
     */
    private static KeyFactory getKeyFactory(String algorithm)
            throws NoSuchAlgorithmException {
        final Provider provider = new BouncyCastleProvider();
        return KeyFactory.getInstance(algorithm, provider);
    }

    public static void main(String[] args) throws Exception {
        StaticInit.BCInit();
        // 使用sm2p256v1曲线生成密钥对
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGen.initialize(new ECGenParameterSpec("sm2p256v1"));
        KeyPair keyPair = keyPairGen.generateKeyPair();

        System.out.println("prKey : " + ECodeUtil.en64ToStr(keyPair.getPrivate().getEncoded()));
        System.out.println();
        System.out.println("puKey : " + ECodeUtil.en64ToStr(keyPair.getPublic().getEncoded()));
        System.out.println();

        //明文
        String plaintext = "test abc 123";
        //加密
        String ciphertext = ECodeUtil.en64ToStr(encrypt(plaintext.getBytes(StandardCharsets.UTF_8),
                keyPair.getPublic().getEncoded()));
        //生成签名
        String signature = ECodeUtil.en64ToStr(sign(plaintext.getBytes(StandardCharsets.UTF_8),
                keyPair.getPrivate().getEncoded()));
        System.out.println("ciphertext: " + ciphertext);
        System.out.println("signature: " + signature);
        //解密
        plaintext = new String(decrypt(ECodeUtil.de64ToByte(ciphertext),
                keyPair.getPrivate().getEncoded()),StandardCharsets.UTF_8);
        //验签
        boolean result = verify(plaintext.getBytes(StandardCharsets.UTF_8),
                ECodeUtil.de64ToByte(signature),keyPair.getPublic().getEncoded());
        System.out.println("plaintext: " + plaintext);
        System.out.println("verify result: " + result);
    }

}

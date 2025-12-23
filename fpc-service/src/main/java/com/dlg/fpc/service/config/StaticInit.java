package com.dlg.fpc.service.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * 静态初始化
 */
public class StaticInit {

    static {
        BCInit();
    }

    /**
     * BC加密套件
     */
    public static void BCInit() {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            synchronized (StaticInit.class) {
                if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                    Security.addProvider(new BouncyCastleProvider());
                }
            }
        }
    }

}

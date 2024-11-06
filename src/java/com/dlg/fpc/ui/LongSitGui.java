package com.dlg.fpc.ui;


import com.dlg.fpc.Application;
import com.dlg.fpc.comp.LsLockListener;
import com.dlg.fpc.util.DateUtils;
import com.dlg.fpc.wincp.Win32WindowState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;

/**
 * @author lingui
 */
@Slf4j
public class LongSitGui extends JFrame {

    /**
     * 初始图片目录
     */
    private static String IMG_PATH;

    /**
     * 锁定监听器
     */
    public final static LsLockListener lockListener = new LsLockListener();

    /**
     * windows状态对象
     */
    public final static Win32WindowState windowState = new Win32WindowState();

    public static void main(String[] args) {
        new LongSitGui();
    }

    public LongSitGui() {
        // 设置主题
        super("久坐提醒");
        // 设置默认关闭模式
        this.setVisible(false);
        // 不允许缩放
        this.setResizable(false);
        //设置窗口的默认关闭方式
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // 初始化
        guiInit();
        // 设置系统托盘
        trayInit();
        // compInit
        compInit();
    }

    private void compInit() {
        // 添加监听器
        windowState.setListener(lockListener);
    }

    /**
     * 初始化
     */
    private void guiInit() {
        JOptionPane.setRootFrame(this);
        Locale.setDefault(Locale.CHINESE);
        Font font = new Font("Arial", Font.PLAIN, 200);
        UIManager.put("OptionPane.font", font);
        IMG_PATH = "D:\\document\\gitResp\\f2pc\\f2pc\\src\\resources\\img\\";
        System.out.println("图像资源路径： " + IMG_PATH);
    }

    /**
     * 初始化托盘
     */
    private void trayInit() {
        try {
            PopupMenu popupMenu = new PopupMenu();
            addMenuItem(popupMenu);
            //创建托盘图标
            // 创建图片对象
            ImageIcon icon = new ImageIcon(IMG_PATH + "lsit_128.png");
            TrayIcon trayIcon = new TrayIcon(icon.getImage(), new String("久坐"), popupMenu);
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                }

            });
            // 添加托盘
            SystemTray tray = SystemTray.getSystemTray();
            tray.add(trayIcon);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
    }

    private void addMenuItem(PopupMenu popupMenu) {
        MenuItem itemExit = new MenuItem("exit system");
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        MenuItem dilog = new MenuItem("cost time");
        dilog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDialog(new String[]{"OK"}, getHitMessage(), "久坐提醒", 0);
            }
        });
        MenuItem nextHit = new MenuItem("nexit hit");
        nextHit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDialog(new String[]{"OK"}, getNexHitMessage(), "下次提醒", 0);
            }
        });
        popupMenu.add(nextHit);
        popupMenu.add(dilog);
        popupMenu.add(itemExit);
    }

    /**
     * 显示提示框
     *
     * @param button        按钮
     * @param message       消息
     * @param title         主题
     * @param defaultButton 默认按钮
     * @return 返回下标
     */
    public static int showDialog(Component component, String[] button, String message, String title, int defaultButton) {
        if (StringUtils.isBlank(title)) {
            title = "久坐提醒";
        }
        ImageIcon icon = new ImageIcon(IMG_PATH + "lsit_128.png");
        // 缩小图标
        Image newImage = icon.getImage().getScaledInstance(46, 46, Image.SCALE_DEFAULT);
        ImageIcon imageIconNew = new ImageIcon(newImage);
        int clickCode = JOptionPane.showOptionDialog(component, message,
                title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIconNew, button, defaultButton);
        System.out.println("响应码： " + clickCode);
        return clickCode;
    }

    /**
     * 显示通知
     *
     * @param button        按钮
     * @param message       消息
     * @param title         主题
     * @param defaultButton 默认按钮
     * @return 按钮下标
     */
    public static int showDialog(String[] button, String message, String title, int defaultButton) {
        return showDialog(Application.getLongSitGui(), button, message, title, defaultButton);
    }

    /**
     * 提醒久坐
     *
     * @return 按钮下标
     */
    public static int noticeLongSit() {
        String[] buttonKey = new String[]{"好的", "稍等五分钟", "稍等十分钟"};
        String message = getHitMessage();
        String title = "久坐提醒";
        int defaultIndex = 1;
        return showDialog(Application.getLongSitGui(), buttonKey, message, title, defaultIndex);
    }

    /**
     * 获取久坐提醒信息
     *
     * @return 提醒消息
     */
    private static String getHitMessage() {
        long cost = System.currentTimeMillis() - lockListener.getUnLockTime();
        log.info("cost : {}", cost);
        long min = cost / 60_000;
        long sec = cost / 1000;
        return "已使用PC" + min + "分钟 \r\n" + sec + "秒 \r\n" +
                "开始使用： " + date2str(lockListener.getUnLockTime()) + "\r";
    }

    /**
     * 获取下次提醒消息
     *
     * @return 提醒消息
     */
    private String getNexHitMessage() {
        long nextHit = lockListener.getNextHit();
        return "下次提醒时间： " + date2str(nextHit) + "\r";
    }

    private static String date2str(long date) {
        return DateUtils.parseDateToStr(DateUtils.HH_mm_ss, new Date(date));
    }

}
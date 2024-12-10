package com.dlg.fpc.comp;

import com.dlg.fpc.util.DateUtils;
import com.dlg.fpc.wincp.Win32WindowState;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * 锁定监听器
 */
@Slf4j
@Getter
public class LsLockListener implements Win32WindowState.OnWindowsLockListener {

    /**
     * 锁定时间
     */
    private long lockTime;
    /**
     * 解锁时间
     */
    private long unLockTime;
    /**
     * 是否锁定
     */
    private boolean lock = false;

    /**
     * 久坐定义时间
     */
    private final long longSit = 90 * 60_000;

    /**
     * 提醒最小间隔
     */
    private final long hitTime = 5 * 60_000;
    private long nextHit = System.currentTimeMillis() + longSit;

    public LsLockListener() {
        // 默认提前
        lockTime = System.currentTimeMillis() - 10;
        unLockTime = System.currentTimeMillis();
    }

    @Override
    public void onLockState(boolean state) {
        lock = state;
        if (lock) {
            lockTime = System.currentTimeMillis();
            log.info("锁定时间： {}", DateUtils.parseTimeToStr(lockTime));
        } else {
            unLockTime = System.currentTimeMillis();
            log.info("解锁时间： {}", DateUtils.parseTimeToStr(unLockTime));
            nextHit = System.currentTimeMillis() + longSit;
            log.info("下次提醒： {}", DateUtils.parseTimeToStr(nextHit));
        }
    }

    @Override
    public boolean isLock() {
        return lock;
    }

    /**
     * 添加提醒时间
     *
     * @param time 毫秒
     */
    public void addHit(long time) {
        nextHit += time;
    }

    /**
     * 是否需要提醒
     *
     * @return true 需要
     */
    public boolean isHit() {
        return System.currentTimeMillis() > nextHit;
    }

    /**
     * 提醒延迟
     * @param sit
     */
    public void hitDelay(int sit) {
        // 如果没有立即休息，则默认  5min
        if (0 >= sit) {
            sit = 1;
        }
        nextHit = (long) sit * hitTime;
    }
}
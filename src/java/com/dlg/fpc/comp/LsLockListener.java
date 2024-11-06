package com.dlg.fpc.comp;

import com.dlg.fpc.util.DateUtils;
import com.dlg.fpc.wincp.Win32WindowState;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

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

    @Getter
    private long nextHit = setUnLockHit();

    public LsLockListener() {
        lockTime = System.currentTimeMillis();
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
            nextHit = setUnLockHit();
            log.info("下次提醒： {}", DateUtils.parseTimeToStr(nextHit));
        }
    }

    @Override
    public boolean isLock() {
        return lock;
    }

    /**
     * 下次解锁时间
     *
     * @return 毫秒
     */
    private long setUnLockHit() {
        return lockTime + 45_60_000;
    }

    /**
     * 添加提醒时间
     *
     * @param time 毫秒
     */
    public void addHit(long time) {
        nextHit += time;
    }

}
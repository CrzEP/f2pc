package com.dlg.fpc.comp;

import com.dlg.fpc.Application;
import com.dlg.fpc.service.TagLogService;
import com.dlg.fpc.ui.LongSitGui;
import com.dlg.fpc.wincp.Win32WindowState;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.dlg.fpc.ui.LongSitGui.noticeLongSit;

/**
 * 久坐组件
 *
 * @author lingui
 * @Date 2023/12/29 14:09
 */
@Component
@Slf4j
public class LsComp {

    @Resource
    TagLogService tagLogService;
    LsLockListener listener = LongSitGui.lockListener;

    /**
     * 检查触发
     */
    @Scheduled(fixedRate = 30_000)
    private void timeClock() {
        if (listener.isLock()) {
            log.info("unlock");
            return;
        }
        // 是否满足提示条件
        boolean hitFlag = listener.isHit();
        // 提示
        if (!hitFlag) {
            return;
        }
        tagLogService.logSitlog();
        int sit = noticeLongSit();
        log.info("sit notice return: {}", sit);

    }

}

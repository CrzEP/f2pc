package com.dlg.fpc.entity;

import com.dlg.fpc.util.DateUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;

/**
 * 标签日志相关实体类
 *
 * @author lingui
 * @Date 2022-09-16 10:58:14
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_tag_log")
public class TagLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logTime;

    private long time;

    private String level;

    private String tag;

    private String msg;

    public static TagLogEntity getBaseInfoLog(String tag,String msg){
        TagLogEntity entity = new TagLogEntity();
        entity.setLogTime(DateUtils.getTime());
        entity.setTime(System.currentTimeMillis());
        entity.setLevel(LogLevel.INFO.name());
        entity.setTag(tag);
        entity.setMsg(msg);
        return entity;
    }

}

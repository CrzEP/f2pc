package com.dlg.fpc.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * api操作相关实体类
 *
 * @author lingui
 * @Date 2022-09-26 11:10:58
 */
@Data
@TableName("t_api_log")
public class ApiLogEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String api;

    private String module = "service";

    private Long cost;

    private Boolean status;

    private String ipAddr;

    private String header;

    private String url;

    private String response;

    private String method;

    private String requestParam;

    private Date requestTime;

    private Date createTime;

}

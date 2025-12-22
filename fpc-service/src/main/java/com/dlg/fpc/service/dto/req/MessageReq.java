package com.dlg.fpc.service.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@Data
public class MessageReq implements Serializable {

    @NotBlank
    private String message;

}

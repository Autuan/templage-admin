package com.autuan.project.mumu.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author Autuan.Yu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendReq {
//    @NotEmpty(message = "发送者不能为空")
    private String fromUserToken;
    @NotEmpty(message = "接收者不能为空")
    private String toUserToken;
    @NotEmpty(message = "发送内容不能为空")
    private String msg;
}

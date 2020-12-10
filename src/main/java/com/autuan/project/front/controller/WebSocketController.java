package com.autuan.project.front.controller;

import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.mumu.websocket.entity.SendReq;
import com.autuan.project.mumu.websocket.serveice.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/front")
public class WebSocketController {
    @PostMapping("/socket/send")
    ReturnResult send(@Valid @RequestBody SendReq sendReq) {
        try {
            WebSocketServer.send(sendReq.getMsg(),sendReq.getToUserToken());
        } catch (IOException e) {
            return ReturnResult.error("发生异常");
        }
        return ReturnResult.ok();
    }
}

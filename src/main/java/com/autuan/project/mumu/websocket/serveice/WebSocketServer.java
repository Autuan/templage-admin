package com.autuan.project.mumu.websocket.serveice;

import cn.hutool.core.util.StrUtil;
import com.autuan.common.exception.BusinessException;
import com.autuan.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Autuan.Yu
 */
@ServerEndpoint("/front/websocket/{userId}")
@Component
@Slf4j
public class WebSocketServer {
    /**
     * 所有链接对象
     */
    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        // 保存userId 到类变量
        this.userId = userId;
        // 连接建立：存入map
        webSocketMap.put(userId, session);
        log.info("WebSocketServer -> onOpen -> new connect -> userId -> {}", userId);
    }

    /**
     * 收到客户端消息的处理
     *
     * @param message 消息内容
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("WebSocketServer -> onMessage -> received ->  userId -> {} message -> {}",userId,message);
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // 链接关闭，移除map中的保存信息
        webSocketMap.remove(userId);
        log.info("WebSocketServer -> onClose -> userId -> {}", userId);
    }

    /**
     * 错误处理
     */
    @OnError
    public void onError(Session session, Throwable e) {
        log.error("WebSocketServer -> onError", e);
    }

    /**
     * 发送自定义消息
     */
    public static void send(String message,String userId) throws IOException {
        log.info("WebSocketServer -> send -> userId -> {} msg -> {}",userId,message);
        if (StrUtil.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).getBasicRemote().sendText(message);
        } else {
            log.warn("WebSocketServer -> send -> offline -> userId -> {}",userId);
            throw new BusinessException("该用户已下线");
        }
    }
}

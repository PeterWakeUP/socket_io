package com.example.demo.socket_handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.example.demo.dto.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by 苏文辉 on 2019/3/18.
 */
@Component
public class MsgHandler {

    private final SocketIOServer socketIOServer;

    @Autowired
    public MsgHandler(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

    @OnConnect
    public void onConnect(SocketIOClient client){
        String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
        String roomName = client.getHandshakeData().getSingleUrlParam("room");
        client.leaveRoom("");
        client.joinRoom(roomName);
        System.out.println("Connect clientId:" + clientId);
    }


    @OnDisconnect
    public void disConnect(SocketIOClient client){
        String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
        System.out.println("disConnect clientId:" + clientId);
    }

    @OnEvent("chat")
    public void onEvent(SocketIOClient client, AckRequest request, Msg data){
        String roomName = client.getHandshakeData().getSingleUrlParam("room");
        socketIOServer.getNamespace("/chat").getRoomOperations(roomName).sendEvent("chat", data);
    }

    public static void main(String[] args){
        System.out.println("Hello World"); //放大发顺丰
    }
}

package com.example.demo.socket_handler;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by 苏文辉 on 2019/3/18.
 */
@Component
public class SocketServerRunner implements CommandLineRunner {

    private final SocketIOServer socketIOServer;
    private final SocketIONamespace chatSocketIONamespace;

    @Autowired
    public SocketServerRunner(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        this.chatSocketIONamespace = socketIOServer.addNamespace("/chat");
    }


    @Bean("chatNameSpace")
    public SocketIONamespace getChatSocketIONamespace(SocketIOServer server){
        chatSocketIONamespace.addListeners(new MsgHandler(server));
        return chatSocketIONamespace;
    }


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("----------------------------------server start");
        socketIOServer.start();
    }
}

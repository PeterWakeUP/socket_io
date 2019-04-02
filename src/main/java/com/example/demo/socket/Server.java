package com.example.demo.socket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.demo.dto.Msg;

/**
 * Created by 苏文辉 on 2019/3/18.
 */
public class Server {

    public static void main(String[] args) throws Exception{
        Configuration config = new Configuration();
        config.setHostname("192.168.42.84");
        config.setPort(10001);
        SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                server.getBroadcastOperations().sendEvent("online", "some one online");
            }
        });

        server.addEventListener("chat", Msg.class, new DataListener<Msg>() {
            @Override
            public void onData(SocketIOClient client, Msg data, AckRequest ackSender) throws Exception {
                server.getBroadcastOperations().sendEvent("chat", data);
            }
        });


        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                server.getBroadcastOperations().sendEvent("online", "some one offline");
            }
        });

        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }

}

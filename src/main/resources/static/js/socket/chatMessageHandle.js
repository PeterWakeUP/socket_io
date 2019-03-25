/**
 * Created by 苏文辉 on 2019/3/18.
 */

var socket;
var clientid = parseInt(Math.random()*100+1, 10);
var name = "player" + clientid;
var room = "room" + clientid;

function online() {
    socket = io.connect("ws://10.100.99.144:10001/chat?clientid="+clientid + "&room=" + room);

    socket.on('connect',function (data) {
        console.log(data);
        socket.emit('chat',{
            userName:name,
            content:name+"_online"
        })
    })

    socket.on('chat', function (data) {
        console.log(data);
    })
}

function chat() {
    socket.emit('chat',{
        userName:name,
        content:name+"_content",
        room:room
    })
}

function offline() {
    socket.disconnect();
}



/**
 * Created by 苏文辉 on 2019/3/18.
 */

var socket;
var name = "player" + parseInt(Math.random()*100+1, 10);

function online() {
    socket = io.connect("ws://10.100.99.144:10000")

    socket.on('connect',function (data) {
        console.log(data);
        socket.emit('chat',{
            userName:name,
            content:name+"_online"
        })
    })

    socket.on('chat', function (data) {
        console.log(data.content);
    })
}

function chat() {
    socket.emit('chat',{
        userName:name,
        content:name+"_content"
    })
}

function offline() {
    socket.disconnect();
}



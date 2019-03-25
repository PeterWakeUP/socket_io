/**
 * Created by 苏文辉 on 2019/3/18.
 */

var socket;
var room;
var name;

function online() {
    room = $("#room").val();
    name = $("#name").val();

    socket = io.connect("ws://10.100.99.144:10001/chat?clientid="+ name + "&room=" + room);

    socket.on('connect',function (data) {
        socket.emit('chat',{
            userName:name,
            content:name+"_online",
            room:room
        })
    })

    socket.on('chat', function (data) {
        console.log(data);
    })
}

function chat() {
    socket.emit('chat',{
        userName:name,
        content:$("#content").val(),
        room:room
    })
}

function offline() {
    socket.disconnect();
}



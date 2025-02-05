let socket = null;
let stompClient = null;
let num = 1;
// stompClient.connect({}, (frame) => {
//     setConnected(true);
//     console.log('Connected: ' + frame);
//     stompClient.subscribe('/topic/greetings', (greeting) => {
//         showGreeting(JSON.parse(greeting.body).content);
//     });
// }, (error) => {
//     console.error('Error with websocket', error);
// });

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', (greeting) => {
            showGreeting(JSON.parse(greeting.body).content);
            num = num * 2;
            console.log(num);
        });
    }, (error) => {
        console.error('Error with websocket', error);
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect(() => {
            setConnected(false);
            console.log("Disconnected");
        });
        stompClient = null; // 清除 stompClient，避免使用已關閉的物件
    }
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#send").click(() => sendName());
});

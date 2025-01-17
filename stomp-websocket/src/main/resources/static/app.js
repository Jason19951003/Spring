let socket = new SockJS('/gs-guide-websocket');
let stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
}, (error) => {
    console.error('Error with websocket', error);
});

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
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', (greeting) => {
            showGreeting(JSON.parse(greeting.body).content);
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

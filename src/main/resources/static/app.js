var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (message) {
        	showMessage(JSON.parse(message.body).content);
        });
        stompClient.subscribe('/topic/game', function (message) {
        	drawGameBoard(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function start() {
    stompClient.send("/app/start", {}, {});
}

function sendMessage() {
    stompClient.send("/app/chat", {}, JSON.stringify({'content': $("#content").val()}));
}

function move(pileId) {
    stompClient.send("/app/move", {}, pileId);
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function drawGameBoard(message) {
    $("#gameStatus").text(message.gameStatus);
    $("#winner").text(message.winner);
    piles = message.piles;
    for (i=0; i<14; i++) {
    	$("#"+i).text(piles[i]);
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
    $( "#start" ).click(function() { start(); });
});
//Este codigo establece la conexion, si nos fijamos en websockethander incluimos /notifications


let socket = new WebSocket("ws://"+window.location.host+"/notifications");
//cuando hay conexion esta funcion se ejecuta indicando que la conexion se ha establecido correctamente
//simplemente se muestra el mensaje en la consola del navegador
socket.onopen = function (e) {
    console.log("WebSocket conexión establecida");
};

socket.onmessage = function (event) {
    console.log(`[message] Datos recibidos por el servidor: ${event.data}`);
};
//se cierra la conexion websocket, se indica porque se ha cerrado, pero en el caso de que se cierrre innesperadamente te indica que la conexion murio
socket.onclose = function (event) {
    if (event.wasClean) {
        console.log(`[close] La conexión se ha cerrado limpiamente, code=${event.code} reason=${event.reason}`);
    } else {
        console.log('[close] La conexión ha muerto');
    }
};
//error en la conexion
socket.onerror = function (error) {
    console.log(`[error] ${error.message}`);
};

//Esto es un ejemplo de como mandar un mensaje a través de websocket
function sendMessage() {
    socket.send("Hola necesito información");
    console.log("WebSocket mensaje enviado con éxito");
}
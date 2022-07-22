import http from 'http'
import express from 'express'
import { WebSocketServer } from 'ws';
import cons from 'consolidate'

const app = express();

app.engine('html', cons.swig);
app.set('views', __dirname + "/views");
app.set('view engine', 'html');
app.use('/public', express.static(__dirname + "/public"));
app.get('/', (req, res) => res.render("index.html"));
app.get('/customer', (req, res) => res.render("template/customer.html"));
app.get('/showcard', (req, res) => res.render("template/showcard.html"));
app.get('/*', (req, res) => res.redirect('/'));

function handleListen() {
    console.log('server on')
}

const server = http.createServer(app);
const wss = new WebSocketServer({ server });

function onSocketClose() {
    console.log('Disconnect from the socket');
}

const sockets = [];

wss.on("connection", (socket) => {
    sockets.push(socket);
    console.log("Connected to Browser ✅");
    socket.on("close", onSocketClose);
    socket.on("message", (msg) => {
        const message = JSON.parse(msg);
        sockets.forEach((aSocket) => aSocket.send(message));
    });
});

server.listen(3000, handleListen);
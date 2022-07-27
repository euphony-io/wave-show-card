'use strict';

const express = require('express');
const socketIO = require('socket.io');

const PORT = process.env.PORT || 3000;
const INDEX = '/index.html';

const server = express()
  .use((req, res) => res.sendFile(INDEX, { root: __dirname }))
  .listen(PORT, () => console.log(`Listening on ${PORT}`));

const io = socketIO(server);

function onSocketClose() {
  console.log('Disconnect from the socket');
}

io.on('connection', (socket) => {
  console.log('Client connected');
  socket.on('disconnect', () => console.log('Client disconnected'));
  socket.on("close", onSocketClose);

  socket.on('setData', (setData)=>{
    socket.broadcast.emit('setShowCardData', setData);
  })
});

// 소켓이 잘 작동하는지 확인하기 위한 시간
// 최종 완성시 삭제할것
setInterval(() => io.emit('condition', 1), 2000);

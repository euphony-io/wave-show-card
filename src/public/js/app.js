const customer = document.querySelector("costomerTransmitter");

const socket = new WebSocket(`ws://${window.location.host}`);

socket.addEventListener("open", ()=>console.log("open"));

socket.addEventListener("message", (message) => {
  const li = document.createElement("li");
  li.innerText = message.data;
  messageList.append(li);
});

socket.addEventListener("close", () => {
  console.log("Disconnected from Server ❌");
});

function makeMessage(payload) {
    const msg = { payload };
    return JSON.stringify(msg);
  }

function handleSubmit(event) {
    event.preventDefault();
    const name = customer.querySelector(".costomerTransmitter__name");
    const price = customer.querySelector(".costomerTransmitter__price");
    console.log(name)
    socket.send(makeMessage("new_message", {name:name.nodeValue, price:price.nodeValue}));
  }


  customer.addEventListener('submit', handleSubmit)
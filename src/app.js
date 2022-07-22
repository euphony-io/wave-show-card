import express from 'express'

const app = express();

app.set('views', __dirname + "/views");
app.use('/public', express.static(__dirname + "/public"));
app.get('/', (req, res) => res.render("index"));
app.get('/*', (req, res) => res.redirect('/'));

const handleListen = () => console.log('server on')

app.listen(3000, handleListen);
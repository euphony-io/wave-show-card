# wave-show-card

Wave-Show-Card helps visually impaired people listen to the show card. 🛒

## Team Link 🎶

[Euterpe](https://github.com/orgs/euphony-io/teams/euterpe)

## Content 📡

### Web

Deploy : [https://wafevafssef.herokuapp.com/](https://wafevafssef.herokuapp.com/)

Directory : showcard-side

Explanation(KOR) : [README.md](https://github.com/euphony-io/wave-show-card/blob/main/showcard-side/README.md)

```shell
cd showcard-side
npm install
npm run start
```

### Android

Directory : cunsumer-side

Explanation(KOR) : [README.md](https://github.com/euphony-io/wave-show-card/blob/main/cunsumer-side/README.md)

```shell
cd cunsumer-side/-java/release
# Download and Install debug-release.apk
```

## Intention 💡

When visually impaired people shop at a mart, they have an inconvenience of not knowing what is in front of them.

Even if a visually impaired person picks up an item and checks the Braille notation, he or she cannot choose a product according to his or her preference because Braille notation does not even know the brand or product name or type. 
([Related Article](https://biz.chosun.com/topics/topics_social/2022/06/15/FX3JYYBMP5AMRGYRG35GR4YXYQ/))

Wave-Show-Card is designed to help visually impaired people shop comfortably.

## Features 🚀

Wave-Show-Card is a service that helps visually impaired people listen to the show card. It uses the sound-wave communication library `Euphony` . It provides the following features:

(Visually Impaired)

- When a visually impaired person clicks the web show card, the show card transmits sound waves.
- When a visually impaired person brings the app near the show card, the app receives the sound waves of the show card, interprets the content, and plays it as a sound.

(Clerk)

- The clerk can save and modify the contents of the show card using a web socket.

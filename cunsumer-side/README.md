### 담당
[@sunyeongan](https://github.com/sunyeongan)
--
### 역할
- 음파신호 수신 앱 구현
- Todo
  - [x] java
  - [x] kotlin compose UI (아주 간단하게.. 리팩토링 필요 )
  - [x] kotlin 마이크 권한 요청 
  - [ ] kotiln 음파신호 수신
  - [ ] 수신 속도, 정확도 개선 
---
### 작동방식 
1. 시각장애인이 앱을 실행시키고 화면을 한 번 터치한다. (음파신호 수신 상태)
2. 웹 쇼카드에서 상품명, 가격 정보를 음파신호로 송신하고 앱에서 수신한다.
3. 앱에서 수신 후, 음파신호 수신 상태를 중지한다.
4. 수신한 상품명, 가격정보를 음성으로 들려준다. (Text to Speech) 
--- 
### 시연영상
![ezgif com-gif-maker](https://user-images.githubusercontent.com/44018024/181766356-6db799c5-ef46-42b6-9d17-608b2a45a38f.gif)
1. 점원이 웹 쇼카드에 상품명(cap), 가격정보(1)를 입력한다.
2. 웹 쇼카드에서 음파신호를 송신한다.
3. 앱에서 음파신호를 수신 후 음성으로 출력한다. 

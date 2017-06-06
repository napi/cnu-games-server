### Homework project 1주차 변경사항 공지
* 1주차 숙제의 마감은 6/10(토) 24:00 까지 입니다.
	* 2주차에 프로젝트를 완성시키는것을 목표로 합니다. 1주차에 다 끝낼 필요는 없습니다
	* 각 조별로 사정을 고려해서 1주차에는 각 조별 딱 1개의 commit 만 있으면 숙제 pass 로 인정합니다.
* 직접 merge 할 수 있게 권한을 주려 했는데, 프로젝트 참여 그룹이 적어서 직접 컨트롤 할 수 있겠네요.
	* 정석대로, PR 보내주시면 제가 merge 하겠습니다.
	* merge 가 늦어지면 메일 보내주세요.

### Homework 
* Extractor
    ```
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(6, Suit.HEARTS));
        cardList.add(new Card(7, Suit.HEARTS));
        cardList.add(new Card(8, Suit.HEARTS));
        cardList.add(new Card(9, Suit.HEARTS));
        cardList.add(new Card(10, Suit.DIAMONDS));
        cardList.add(new Card(11, Suit.DIAMONDS));
    ```
  * extractHighHands (5조)
    * 주어진 N개의 카드에 대해 가장 높은 조합을 가지는 5개의 카드를 추출해 내야 한다.
    * 위의 경우 FLUSH의 1,6,7,8,9 카드가 추출되어야 한다.
  * extractLowHands (6조)
    * 주어진 N개의 카드에 대해 가장 낮은 조합을 가지는 5개의 카드를 추출해 내야 한다.
    * 위의 경우 NOTHING 의 1,6,7,8,10 이 되어야 한다.
* Evaluator
  * evalauteHighHands (15조)
    * HandsType 에 맞춰 Ordering 이 잘 되는지 확인
    * HandsType 이 같을 경우 숫자 + 모양으로 판단해야 한다.
  * evalauteLowHands (13조)
    * HandsType 에 맞춰 Ordering 이 잘 되는지 확인
    * HandsType 이 같을 경우 숫자 + 모양으로 판단해야 한다. 
* 고려해야할 사항
	* Ace 는 1과 14로 모두 사용될 수 있다.
	* 1,2,3,4,5 는, high에서는 mountin 바로 다음 족보로, low에서는 perpect로 사용될 수 있다.
	* Suit 의 순서는 스페이드, 다이아, 하트, 클로버 순서다.
	* 조커는 고려하지 않는다.
* 완료조건
	* 각 method 를 구현하고, Test code 를 만들어 각종 조건을 테스트 한다.
	* 마감은 6/11 18:00 까지로 정한다.
	* 일부 버그가 있거나 구현이 힘들 부분이 있을 경우, 주석으로 TODO를 마킹해 놓으면, 감점없이 다음 주차 숙제에 포함시킨다.
	* 작업이 완료되면 직접 merge 한다. Test 가 깨지지 않는 상황이라면 merge 의 횟수에 대한 제약은 없다.
	* 잘 모르는 내용은 google 및 강사에게 문의해 주세요. 최대한 친절히 답해드립니다.
* Git 사용방법
	* /napi/cnu-games-server 를 fork 따서 자신의 repository 로 가져온다.
	* 자신의 repository 에 있는 프로젝트를 clone 하여 local PC에 저장한다.
	* 자신의 PC에서 git remote add 명령으로 원본 프로젝트를 추가한다.
		```
		# upstream 이라는 이름으로 새로운 remote 저장소를 연결한다.
		git remote add upstream https://github.com/napi/cnu-games-server.git
		# remote 저장소를 조회한다.
		git remote -v
		```
	* 주기적으로 git pull upstream master 명령을 사용해서, upstream 과 싱크를 맞춰준다.
	* conflict 가 발생하는 경우, 직접 해결하도록 한다.
	* upstream 으로 PR은 누구나 날릴 수 있다. 각 조 조장에게 merge 권한을 부여하였으니, 조장에게 직접 merge 를 부탁한다.
	* <중요> PR을 날릴땐 항상 prefix 로 [X조] 를 붙여준다.



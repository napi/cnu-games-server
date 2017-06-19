package kr.ac.cnu.games;

import kr.ac.cnu.games.poker.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void 로얄_스트레이트_플러쉬일_경우(){
		//TODO 가지고 있는 카드 중 족보 로얄 스트레이트 플러쉬가 있을 경우를 테스트한다
	}

	@Test
	public void 가장높은조합추출_플러쉬테스트(){
		Extractor extractor = new Extractor();
		List<Card> cardList = new ArrayList<>();


		cardList.add(new Card(1, Suit.HEARTS));
		cardList.add(new Card(6, Suit.HEARTS));
		cardList.add(new Card(7, Suit.HEARTS));
		cardList.add(new Card(8, Suit.HEARTS));
		cardList.add(new Card(9, Suit.HEARTS));
		cardList.add(new Card(10, Suit.DIAMONDS));
		cardList.add(new Card(11, Suit.DIAMONDS));


		Hands result = extractor.extractHighHands(cardList);

		assertThat(result.getHandsType(), is(HandsType.FLUSH));
	}

	@Test
	public void 가장높은조합추출_스트레이트_테스트(){
		Extractor extractor = new Extractor();
		List<Card> cardList = new ArrayList<>();


		cardList.add(new Card(2, Suit.DIAMONDS));
		cardList.add(new Card(6, Suit.HEARTS));
		cardList.add(new Card(7, Suit.HEARTS));
		cardList.add(new Card(8, Suit.SPADES));
		cardList.add(new Card(9, Suit.HEARTS));
		cardList.add(new Card(10, Suit.DIAMONDS));
		cardList.add(new Card(5, Suit.DIAMONDS));


		Hands result = extractor.extractHighHands(cardList);

		assertThat(result.getHandsType(), is(HandsType.STRIGHT));
	}

}

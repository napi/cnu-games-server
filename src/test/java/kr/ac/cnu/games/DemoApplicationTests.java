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
	@Test
	public void 가장높은조합추출_스트레이트_플러쉬_테스트(){
		Extractor extractor = new Extractor();
		List<Card> cardList = new ArrayList<>();


		cardList.add(new Card(5, Suit.HEARTS));
		cardList.add(new Card(6, Suit.HEARTS));
		cardList.add(new Card(7, Suit.HEARTS));
		cardList.add(new Card(8, Suit.HEARTS));
		cardList.add(new Card(9, Suit.HEARTS));
		cardList.add(new Card(3, Suit.DIAMONDS));
		cardList.add(new Card(5, Suit.DIAMONDS));


		Hands result = extractor.extractHighHands(cardList);

		assertThat(result.getHandsType(), is(HandsType.STRIGHT_FLUSH));
	}
	@Test
	public void 가장높은조합추출_투페어_테스트(){
		Extractor extractor = new Extractor();
		List<Card> cardList = new ArrayList<>();


		cardList.add(new Card(5, Suit.HEARTS));
		cardList.add(new Card(6, Suit.CLUBS));
		cardList.add(new Card(7, Suit.HEARTS));
		cardList.add(new Card(9, Suit.SPADES));
		cardList.add(new Card(9, Suit.HEARTS));
		cardList.add(new Card(3, Suit.DIAMONDS));
		cardList.add(new Card(5, Suit.DIAMONDS));


		Hands result = extractor.extractHighHands(cardList);

		assertThat(result.getHandsType(), is(HandsType.TWO_PAIR));
	}

	@Test
	public void 가장높은조합추출_원페어_테스트(){
		Extractor extractor = new Extractor();
		List<Card> cardList = new ArrayList<>();


		cardList.add(new Card(5, Suit.SPADES));
		cardList.add(new Card(6, Suit.HEARTS));
		cardList.add(new Card(1, Suit.DIAMONDS));
		cardList.add(new Card(8, Suit.HEARTS));
		cardList.add(new Card(9, Suit.HEARTS));
		cardList.add(new Card(3, Suit.DIAMONDS));
		cardList.add(new Card(5, Suit.DIAMONDS));


		Hands result = extractor.extractHighHands(cardList);

		assertThat(result.getHandsType(), is(HandsType.ONE_PAIR));
	}

	@Test
	public void 가장높은조합추출_트리플_테스트(){
		Extractor extractor = new Extractor();
		List<Card> cardList = new ArrayList<>();


		cardList.add(new Card(5, Suit.HEARTS));
		cardList.add(new Card(6, Suit.SPADES));
		cardList.add(new Card(7, Suit.HEARTS));
		cardList.add(new Card(5, Suit.SPADES));
		cardList.add(new Card(9, Suit.HEARTS));
		cardList.add(new Card(3, Suit.DIAMONDS));
		cardList.add(new Card(5, Suit.DIAMONDS));


		Hands result = extractor.extractHighHands(cardList);

		assertThat(result.getHandsType(), is(HandsType.THREE_CARD));
	}

	@Test
	public void 가장높은조합추출_풀하우스_테스트(){
		Extractor extractor = new Extractor();
		List<Card> cardList = new ArrayList<>();


		cardList.add(new Card(7, Suit.HEARTS));
		cardList.add(new Card(7, Suit.SPADES));
		cardList.add(new Card(7, Suit.CLUBS));
		cardList.add(new Card(2, Suit.SPADES));
		cardList.add(new Card(2, Suit.HEARTS));
		cardList.add(new Card(3, Suit.DIAMONDS));
		cardList.add(new Card(5, Suit.DIAMONDS));


		Hands result = extractor.extractHighHands(cardList);

		assertThat(result.getHandsType(), is(HandsType.FULL_HOUSE));
	}

	@Test
	public void 가장높은조합추출_포카드_테스트(){
		Extractor extractor = new Extractor();
		List<Card> cardList = new ArrayList<>();


		cardList.add(new Card(5, Suit.HEARTS));
		cardList.add(new Card(5, Suit.SPADES));
		cardList.add(new Card(5, Suit.CLUBS));
		cardList.add(new Card(5, Suit.DIAMONDS));
		cardList.add(new Card(9, Suit.HEARTS));
		cardList.add(new Card(3, Suit.DIAMONDS));
		cardList.add(new Card(2, Suit.DIAMONDS));


		Hands result = extractor.extractHighHands(cardList);

		assertThat(result.getHandsType(), is(HandsType.FOUR_CARD));
	}
}

package kr.ac.cnu.games.poker;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by rokim on 2017. 6. 4..
 */
public class EvaluatorTest {
    private Evaluator evaluator;

    @Before
    public void setUp() {
        evaluator = new Evaluator();
    }

    // <!-- High CARD 테스트

    @Test
    //handstype이 다를 때 큰 것 부터 정렬
    public void evaluateHighCard1() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getStright5());
        handsList.add(getFlushclubs());
        handsList.add(getOnepair5());

        List<Hands> resultList = evaluator.evalauteHighHands(handsList);

        System.out.println(handsList);

        assertThat(resultList.get(0).getHandsType(), is(HandsType.FLUSH));
        assertThat(resultList.get(1).getHandsType(), is(HandsType.STRIGHT));
        assertThat(resultList.get(2).getHandsType(), is(HandsType.ONE_PAIR));
    }

    @Test
    //원페어 세개가 있을 때 그 수를 비교
    public void evaluateHighCardOnepair() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getOnepair7());
        handsList.add(getOnepair10());
        handsList.add(getOnepair5());


        List<Hands> resultList = evaluator.evalauteHighHands(handsList);

        System.out.println(handsList);

        assertThat(resultList.get(0).getCardList().get(0).getNumber(), is(10));
        assertThat(resultList.get(1).getCardList().get(0).getNumber(), is(7));
        assertThat(resultList.get(2).getCardList().get(0).getNumber(), is(5));
    }

    @Test
    //아무것도 없을 때 가장 큰 수 비교
    public void evaluateHighCardNothing() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getNothing9());
        handsList.add(getNothing10());


        List<Hands> resultList = evaluator.evalauteHighHands(handsList);

        System.out.println(handsList);

        assertThat(resultList.get(0).getCardList().get(0).getNumber(), is(10));
        assertThat(resultList.get(1).getCardList().get(0).getNumber(), is(9));
    }

    @Test
    //같을 때 무늬로 비교
    public void evaluateHighCardFlush() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getFlushclubs());
        handsList.add(getFlushspades());


        List<Hands> resultList = evaluator.evalauteHighHands(handsList);

        System.out.println(handsList);

        assertThat(resultList.get(0).getCardList().get(0).getSuit(), is(Suit.SPADES));
        assertThat(resultList.get(1).getCardList().get(0).getSuit(), is(Suit.CLUBS));

    }
    @Test
    //같을 때 5개의 수 정렬 중 큰 수를 비교
    public void evaluateHighCardStraight() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getStright5());
        handsList.add(getStright9());


        List<Hands> resultList = evaluator.evalauteHighHands(handsList);

        System.out.println(handsList);

        assertThat(resultList.get(0).getCardList().get(0).getNumber(), is(9));
        assertThat(resultList.get(1).getCardList().get(0).getNumber(), is(5));
    }

    @Test
    //같을 때 3개인 숫자를 비교 하여 정렬
    public void evaluateHighCardFullhouse() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getFullhouse3());
        handsList.add(getFullhouse10());


        List<Hands> resultList = evaluator.evalauteHighHands(handsList);

        System.out.println(handsList);

        assertThat(resultList.get(0).getCardList().get(0).getNumber(), is(10));
        assertThat(resultList.get(1).getCardList().get(3).getNumber(), is(3));
    }

    @Test
    public void evaluateHighCard2() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getStright());
        handsList.add(getOnepair());
        handsList.add(getNothing());
        handsList.add(getFlush());
        handsList.add(getFullhouse());
        handsList.add(getTwopair());

        List<Hands> resultList = evaluator.evalauteHighHands(handsList);

        System.out.println(handsList);

        assertThat(resultList.get(0).getHandsType(), is(HandsType.FULL_HOUSE));
        assertThat(resultList.get(1).getHandsType(), is(HandsType.FLUSH));
        assertThat(resultList.get(2).getHandsType(), is(HandsType.STRIGHT));
        assertThat(resultList.get(3).getHandsType(), is(HandsType.TWO_PAIR));
        assertThat(resultList.get(4).getHandsType(), is(HandsType.ONE_PAIR));
        assertThat(resultList.get(5).getHandsType(), is(HandsType.NOTHING));
    }

    // TODO 각 HandsType 에 맞춰 ordering 이 잘 되는지 확인
    // TODO HandsType 이 같을때 가장 높은 숫자로 odering 이 되는지 확인.


    // High CARD 테스트 -->


@Ignore
    // <!-- Low CARD 테스트
    @Test
    public void evaluateLowCard1() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getStright5());
        handsList.add(getFlushclubs());

        List<Hands> resultList = evaluator.evalauteLowHands(handsList);
        System.out.print(handsList);
        assertThat(resultList.get(0).getHandsType(), is(HandsType.STRIGHT));
        assertThat(resultList.get(1).getHandsType(), is(HandsType.FLUSH));

    }

    // TODO 각 HandsType 에 맞춰 ordering 이 잘 되는지 확인
    // TODO HandsType 이 같을때 가장 낮은 숫자로 odering 이 되는지 확인.

    // Low CARD 테스트 -->


    // Givens
    private Hands getNothing9() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(3, Suit.DIAMONDS));
        cardList.add(new Card(5, Suit.SPADES));
        cardList.add(new Card(9, Suit.CLUBS));
        cardList.add(new Card(7, Suit.HEARTS));

        return new Hands(HandsType.NOTHING, cardList);
    }

    private Hands getNothing10() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(3, Suit.DIAMONDS));
        cardList.add(new Card(5, Suit.SPADES));
        cardList.add(new Card(10, Suit.CLUBS));
        cardList.add(new Card(7, Suit.HEARTS));

        return new Hands(HandsType.NOTHING, cardList);
    }

    private Hands getFlushclubs() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(2, Suit.CLUBS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(6, Suit.CLUBS));
        cardList.add(new Card(7, Suit.CLUBS));

        return new Hands(HandsType.FLUSH, cardList);
    }

    private Hands getFlushspades() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.SPADES));
        cardList.add(new Card(2, Suit.SPADES));
        cardList.add(new Card(5, Suit.SPADES));
        cardList.add(new Card(6, Suit.SPADES));
        cardList.add(new Card(7, Suit.SPADES));

        return new Hands(HandsType.FLUSH, cardList);
    }

    private Hands getStright5() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(2, Suit.DIAMONDS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(4, Suit.CLUBS));

        return new Hands(HandsType.STRIGHT, cardList);
    }
    private Hands getOnepair() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(9, Suit.SPADES));


    private Hands getStright9() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(6, Suit.DIAMONDS));
        cardList.add(new Card(9, Suit.CLUBS));
        cardList.add(new Card(8, Suit.HEARTS));
        cardList.add(new Card(7, Suit.CLUBS));

        return new Hands(HandsType.STRIGHT, cardList);
    }

    private Hands getOnepair7() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(7, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(7, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(2, Suit.SPADES));

        return new Hands(HandsType.ONE_PAIR, cardList);
    }

    private Hands getOnepair10() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(10, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(10, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(9, Suit.SPADES));

        return new Hands(HandsType.ONE_PAIR, cardList);
    }

    private Hands getOnepair5() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(2, Suit.SPADES));

        return new Hands(HandsType.ONE_PAIR, cardList);
    }


    private Hands getFullhouse3() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(8, Suit.CLUBS));
        cardList.add(new Card(8, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(3, Suit.SPADES));
        cardList.add(new Card(3, Suit.DIAMONDS));

        return new Hands(HandsType.FULL_HOUSE, cardList);
    }

    private Hands getFullhouse10() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(10, Suit.HEARTS));
        cardList.add(new Card(10, Suit.CLUBS));
        cardList.add(new Card(10, Suit.SPADES));
        cardList.add(new Card(3, Suit.SPADES));
        cardList.add(new Card(3, Suit.HEARTS));
=======
        return new Hands(HandsType.ONE_PAIR, cardList);
    }

    private Hands getNothing() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(9, Suit.SPADES));

        return new Hands(HandsType.NOTHING, cardList);
    }
    private Hands getTwopair() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(9, Suit.SPADES));

        return new Hands(HandsType.TWO_PAIR, cardList);
    }
    private Hands getFullhouse() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(9, Suit.SPADES));


        return new Hands(HandsType.FULL_HOUSE, cardList);
    }
}

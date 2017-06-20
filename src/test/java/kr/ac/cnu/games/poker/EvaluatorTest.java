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
    public void evaluateHighCard1() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getStright());
        handsList.add(getFlush());

        List<Hands> resultList = evaluator.evalauteHighHands(handsList);

        assertThat(resultList.get(0).getHandsType(), is(HandsType.FLUSH));
        assertThat(resultList.get(1).getHandsType(), is(HandsType.STRIGHT));
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



    // <!-- Low CARD 테스트
    @Test
    public void evaluateLowCard1() {
        List<Hands> handsList = new ArrayList<>();
        handsList.add(getStright());
        handsList.add(getFlush());

        List<Hands> resultList = evaluator.evalauteLowHands(handsList);
        System.out.print(handsList);
        assertThat(resultList.get(0).getHandsType(), is(HandsType.STRIGHT));
        assertThat(resultList.get(1).getHandsType(), is(HandsType.FLUSH));
    }

    // TODO 각 HandsType 에 맞춰 ordering 이 잘 되는지 확인
    // TODO HandsType 이 같을때 가장 낮은 숫자로 odering 이 되는지 확인.
    @Test
    public void 핸드_타입이_다를때_족보가_낮으면_이긴다(){
        Hands myHands= getStright();
        Hands otherHands = getFlush();

        int result = evaluator.evalauteHandsType(myHands, otherHands);
        assertThat(result, is(1));
    }

    @Test
    public void 스트레이트_플러시로_같을_때_제일_큰_숫자_비교해서_낮은_쪽이_이긴다(){
        Hands myHands = getStrightFlash4();
        Hands otherHands = getStrightFlash3();

        int result = evaluator.evalauteHandsType(myHands, otherHands);
        assertThat(result, is(8));
    }

    @Test
    public void 스트레이트_플러시로_같을_때_제일_큰_숫자_비교했는데_같으면_클로버에_가까운_모양이_이긴다(){
        Hands myHands = getStrightFlash5();
        Hands otherHands = getStrightFlash3();

        int result = evaluator.evalauteHandsType(myHands, otherHands);
        assertThat(result, is(-1));
    }

    @Test
    public void Flush_비교_다른랭크_다른문양(){
        Hands myHands = getFlush1();
        Hands otherhands = getFlush4();
        int result = evaluator.evalauteHandsType(myHands, otherhands);
        assertThat(result, is(-2));
    }

    @Test
    public void Flush_비교_같은랭크_다른문양(){
        Hands myHands = getFlush1();
        Hands otherhands = getFlush3();
        int result = evaluator.evalauteHandsType(myHands, otherhands);
        assertThat(result, is(1));
    }

    @Test
    public void Flush_비교_다른랭크_같은문양(){
        Hands myHands = getFlush1();
        Hands otherhands = getFlush2();
        int result = evaluator.evalauteHandsType(myHands, otherhands);
        assertThat(result, is(-1));
    }


    @Test
    public void extractorLowHands_같은_원페어의_경우_후속_NUMBER_비교(){
        //given
       Hands myHands = getOnePair1();
       Hands otherHans = getOnePair2();

       int result = evaluator.evalauteHandsType(myHands, otherHans);
       assertThat(result, is (-2));
    }

    @Test
    public void extractorLowHands_NUMBER같은_원페어의_경우_후속_문양_비교(){
        //given
        Hands myHands = getOnePair2();
        Hands otherHans = getOnePair3();

        int result = evaluator.evalauteHandsType(myHands, otherHans);
        assertThat(result, is (-2));
    }

    @Test
    public void 투페어비교() {
        Hands myHands = getTwoPair1();
        Hands otherHans = getTwoPair2();
        int result = evaluator.evalauteHandsType(myHands, otherHans);
        assertThat(result, is (-2));
    }

    // Low CARD 테스트 -->

    // Givens
    private Hands getFlush() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(2, Suit.CLUBS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(6, Suit.CLUBS));
        cardList.add(new Card(7, Suit.CLUBS));

        return new Hands(HandsType.FLUSH, cardList);
    }

    private Hands getFlush1(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(2, Suit.CLUBS));
        cardList.add(new Card(3, Suit.CLUBS));
        cardList.add(new Card(7, Suit.CLUBS));
        cardList.add(new Card(13, Suit.CLUBS));

        return new Hands(HandsType.FLUSH, cardList);
    }

    private Hands getFlush2(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(4, Suit.CLUBS));
        cardList.add(new Card(9, Suit.CLUBS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(6, Suit.CLUBS));
        cardList.add(new Card(12, Suit.CLUBS));

        return new Hands(HandsType.FLUSH, cardList);
    }

    private Hands getFlush3(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(7, Suit.HEARTS));
        cardList.add(new Card(13, Suit.HEARTS));

        return new Hands(HandsType.FLUSH, cardList);
    }

    private Hands getFlush4(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(7, Suit.SPADES));
        cardList.add(new Card(1, Suit.SPADES));
        cardList.add(new Card(3, Suit.SPADES));
        cardList.add(new Card(8, Suit.SPADES));
        cardList.add(new Card(11, Suit.SPADES));

        return new Hands(HandsType.FLUSH, cardList);
    }

    private Hands getStright() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(2, Suit.DIAMONDS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(4, Suit.CLUBS));

        return new Hands(HandsType.STRIGHT, cardList);
    }

    //<-- 15조 get내용
    private Hands getOnepair() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(9, Suit.SPADES));
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
    //15조 get 내용-->

    // <--- 13조 get 내용
    private Hands getOnePair1() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(2, Suit.SPADES));
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(5, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(4, Suit.CLUBS));

        return new Hands(HandsType.ONE_PAIR, cardList);
    }

    private Hands getOnePair2() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(2, Suit.CLUBS));
        cardList.add(new Card(2, Suit.DIAMONDS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(7, Suit.DIAMONDS));
        cardList.add(new Card(4, Suit.CLUBS));

        return new Hands(HandsType.ONE_PAIR, cardList);
    }

    private Hands getOnePair3() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(2, Suit.SPADES));
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(5, Suit.SPADES));
        cardList.add(new Card(7, Suit.HEARTS));
        cardList.add(new Card(4, Suit.HEARTS));

        return new Hands(HandsType.ONE_PAIR, cardList);
    }

    private Hands getTwoPair1() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(2, Suit.CLUBS));
        cardList.add(new Card(2, Suit.DIAMONDS));
        cardList.add(new Card(5, Suit.SPADES));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(4, Suit.CLUBS));

        return new Hands(HandsType.TWO_PAIR, cardList);
    }

    private Hands getTwoPair2() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(2, Suit.SPADES));
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(5, Suit.HEARTS));
        cardList.add(new Card(4, Suit.DIAMONDS));

        return new Hands(HandsType.TWO_PAIR, cardList);
    }

    private Hands getTripple1() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(3, Suit.SPADES));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(3, Suit.CLUBS));
        cardList.add(new Card(5, Suit.SPADES));
        cardList.add(new Card(4, Suit.CLUBS));

        return new Hands(HandsType.THREE_CARD, cardList);
    }

    private Hands getTripple2() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(5, Suit.SPADES));
        cardList.add(new Card(6, Suit.HEARTS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(4, Suit.CLUBS));

        return new Hands(HandsType.THREE_CARD, cardList);
    }

    private Hands getFullHouse1() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(3, Suit.SPADES));
        cardList.add(new Card(4, Suit.HEARTS));
        cardList.add(new Card(3, Suit.CLUBS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(4, Suit.CLUBS));

        return new Hands(HandsType.FULL_HOUSE, cardList);
    }

    private Hands getFullHouse2() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.SPADES));
        cardList.add(new Card(5, Suit.HEARTS));
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(5, Suit.CLUBS));

        return new Hands(HandsType.FULL_HOUSE, cardList);
    }

    private Hands getFourCard1() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.SPADES));
        cardList.add(new Card(1, Suit.DIAMONDS));
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(5, Suit.CLUBS));

        return new Hands(HandsType.FOUR_CARD, cardList);
    }

    private Hands getFourCard2() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(13, Suit.SPADES));
        cardList.add(new Card(13, Suit.DIAMONDS));
        cardList.add(new Card(13, Suit.CLUBS));
        cardList.add(new Card(13, Suit.HEARTS));
        cardList.add(new Card(7, Suit.DIAMONDS));

        return new Hands(HandsType.FOUR_CARD, cardList);
    }

    private Hands getStrightFlash1() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(4, Suit.HEARTS));
        cardList.add(new Card(5, Suit.HEARTS));

        return new Hands(HandsType.STRIGHT_FLUSH, cardList);
    }

    private Hands getStrightFlash2() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(5, Suit.CLUBS));
        cardList.add(new Card(4, Suit.CLUBS));
        cardList.add(new Card(2, Suit.CLUBS));
        cardList.add(new Card(3, Suit.CLUBS));

        return new Hands(HandsType.STRIGHT_FLUSH, cardList);
    }

    private Hands getStrightFlash3() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(12, Suit.CLUBS));
        cardList.add(new Card(1, Suit.CLUBS));
        cardList.add(new Card(10, Suit.CLUBS));
        cardList.add(new Card(13, Suit.CLUBS));
        cardList.add(new Card(11, Suit.CLUBS));

        return new Hands(HandsType.STRIGHT_FLUSH, cardList);
    }

    private Hands getStrightFlash4() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(5, Suit.HEARTS));
        cardList.add(new Card(4, Suit.HEARTS));
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(3, Suit.HEARTS));

        return new Hands(HandsType.STRIGHT_FLUSH, cardList);
    }

    private Hands getStrightFlash5() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(12, Suit.HEARTS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(13, Suit.HEARTS));
        cardList.add(new Card(10, Suit.HEARTS));
        cardList.add(new Card(11, Suit.HEARTS));

        return new Hands(HandsType.STRIGHT_FLUSH, cardList);
    }

    private Hands getNoting1() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.SPADES));
        cardList.add(new Card(3, Suit.CLUBS));
        cardList.add(new Card(7, Suit.HEARTS));
        cardList.add(new Card(8, Suit.CLUBS));
        cardList.add(new Card(9, Suit.DIAMONDS));

        return new Hands(HandsType.NOTHING, cardList);
    }
    private Hands getNoting2() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.DIAMONDS));
        cardList.add(new Card(3, Suit.HEARTS));
        cardList.add(new Card(7, Suit.SPADES));
        cardList.add(new Card(8, Suit.SPADES));
        cardList.add(new Card(9, Suit.CLUBS));

        return new Hands(HandsType.NOTHING, cardList);
    }

    private Hands getNoting3() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.DIAMONDS));
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(7, Suit.SPADES));
        cardList.add(new Card(10, Suit.SPADES));
        cardList.add(new Card(9, Suit.SPADES));

        return new Hands(HandsType.NOTHING, cardList);
    }

    private Hands getNoting4() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.DIAMONDS));
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(7, Suit.DIAMONDS));
        cardList.add(new Card(9, Suit.DIAMONDS));
        cardList.add(new Card(10, Suit.HEARTS));

        return new Hands(HandsType.NOTHING, cardList);
    }

    // 13조 get 내용--->
}

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
public class ExtractorTest {
    private Extractor extractor;

    @Before
    public void setUp() {
        extractor = new Extractor();
    }

    @Ignore
    @Test
    public void extractHighHands1() {
        // given
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(6, Suit.HEARTS));
        cardList.add(new Card(7, Suit.HEARTS));
        cardList.add(new Card(8, Suit.HEARTS));
        cardList.add(new Card(9, Suit.HEARTS));
        cardList.add(new Card(10, Suit.DIAMONDS));
        cardList.add(new Card(11, Suit.DIAMONDS));


        Hands hands = extractor.extractHighHands(cardList);
        // High 싸움에서 핸드는 1,6,7,8,9 HEARTS FLUSH가 되어야 하고,
        assertThat(hands.getHandsType(), is(HandsType.FLUSH));
        // 가장 높은 카드는 Ace (1 또는 14)인 1 이 되어야 한다.
        assertThat(hands.getCardList().stream().min(new HighCardComparator()).get().getNumber(), is(1));
    }

    @Ignore
    @Test
    public void extractLowHands1() {
        // given
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(5, Suit.HEARTS));
        cardList.add(new Card(7, Suit.HEARTS));
        cardList.add(new Card(8, Suit.HEARTS));
        cardList.add(new Card(9, Suit.HEARTS));
        cardList.add(new Card(10, Suit.DIAMONDS));
        cardList.add(new Card(11, Suit.DIAMONDS));


        Hands hands = extractor.extractLowHands(cardList);
        // 로우싸움에서 핸드는 1,2,3,8 HEARTS, 10DIAMONDS 로 Type이 NOTHING이 되어야 하며,
        assertThat(hands.getHandsType(), is(HandsType.NOTHING));
        // Ace는 1 또는 14으로 쓰일 수 있기 때문에, 가장 높은 카드는 10이 되어야 한다. (낮을 수록 좋다.)
        assertThat(hands.getCardList().stream().min(new HighCardComparator()).get().getNumber(), is(10));

    }
    @Test
    public void extractLowHands2() {
        // given
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(2, Suit.HEARTS));
        cardList.add(new Card(4, Suit.DIAMONDS));
        cardList.add(new Card(8, Suit.SPADES));
        cardList.add(new Card(8, Suit.HEARTS));
        cardList.add(new Card(10, Suit.HEARTS));
        cardList.add(new Card(10, Suit.DIAMONDS));
        cardList.add(new Card(11, Suit.DIAMONDS));


        Hands hands = extractor.extractLowHands(cardList);
        // 2페어 중 한 카드씩만 고를 경우 최소는 NOTHING이 될 수 있다.
        assertThat(hands.getHandsType(), is(HandsType.NOTHING));
        // 1페어도 안 나오게 해야 하므로 8과 10은 하나만 들어갈 수 있다. 그러므로 11이 나오게 된다.
        assertThat(hands.getCardList().stream().min(new HighCardComparator()).get().getNumber(), is(11));

    }

    @Test
    public void extractLowHands3() {
        // given
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(2, Suit.DIAMONDS));
        cardList.add(new Card(3, Suit.DIAMONDS));
        cardList.add(new Card(4, Suit.DIAMONDS));
        cardList.add(new Card(5, Suit.DIAMONDS));
        cardList.add(new Card(6, Suit.DIAMONDS));
        cardList.add(new Card(7, Suit.DIAMONDS));
        cardList.add(new Card(8, Suit.DIAMONDS));


        Hands hands = extractor.extractLowHands(cardList);
        // 모든 카드가 같으므로 최소가 FLUSH이어야 한다.
        assertThat(hands.getHandsType(), is(HandsType.FLUSH));
        // Stragiht 플러시가 아니어야 하므로 한칸 띄워진 7이 가장 큰 숫자가 될 것이다.
        assertThat(hands.getCardList().stream().min(new HighCardComparator()).get().getNumber(), is(7));

    }

}

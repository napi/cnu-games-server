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
        cardList.add(new Card(6, Suit.HEARTS));
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
}

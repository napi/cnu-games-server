package kr.ac.cnu.games.poker;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by rokim on 2017. 6. 4..
 */
public class HandsTest {
    @Ignore
    @Test
    public void test_숫자() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(8, Suit.HEARTS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(10, Suit.DIAMONDS));
        cardList.add(new Card(6, Suit.HEARTS));
        cardList.add(new Card(11, Suit.DIAMONDS));
        cardList.add(new Card(7, Suit.HEARTS));

        Hands hands = new Hands(HandsType.NOTHING, cardList);
        System.out.println(hands);

        assertThat(hands.getCardList().get(0).getNumber(), is(11));
    }

    @Ignore
    @Test
    public void test_모양() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(8, Suit.HEARTS));
        cardList.add(new Card(1, Suit.HEARTS));
        cardList.add(new Card(11, Suit.SPADES));
        cardList.add(new Card(6, Suit.HEARTS));
        cardList.add(new Card(11, Suit.DIAMONDS));
        cardList.add(new Card(6, Suit.CLUBS));

        Hands hands = new Hands(HandsType.NOTHING, cardList);

        System.out.println(hands);
        assertThat(hands.getCardList().get(0).getNumber(), is(11));
        assertThat(hands.getCardList().get(0).getSuit(), is(Suit.SPADES));
    }

}

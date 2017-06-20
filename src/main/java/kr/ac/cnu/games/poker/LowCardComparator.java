package kr.ac.cnu.games.poker;

import java.util.Comparator;

/**
 * Created by rokim on 2017. 6. 4..
 */
public class LowCardComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        if (o1.getNumber() == o2.getNumber()) {
            return o1.getSuit().compareTo(o2.getSuit());
        }

        if (o1.getNumber() == 1) {
            return 1;
        } else if (o2.getNumber() == 1) {
            return -1;
        } else {
            return o2.getNumber() - o1.getNumber();
        }

    }
    public int compare(Card o1, Card o2, int i) {
        if (o1.getNumber() == o2.getNumber()) {
            return 0;
        }

        if (o1.getNumber() == 1) {
            return 1;
        } else if (o2.getNumber() == 1) {
            return -1;
        } else {
            return o2.getNumber() - o1.getNumber();
        }
    }

}

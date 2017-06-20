package kr.ac.cnu.games.poker;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rokim on 2017. 6. 4..
 */
@Data
public class Hands {

    private final HandsType handsType;
    private final List<Card> cardList;

    public Hands(HandsType handsType, List<Card> cardList) {
        this.handsType = handsType;

        this.cardList = cardList;


//        this.cardList = cardList
//                .stream()
//                .sorted((o1, o2) -> {
//                    if (o1.getNumber() != o2.getNumber()) {
//                        return o2.getNumber() - o1.getNumber();
//                    }
//
//                    return o1.getSuit().compareTo(o2.getSuit());
//                })
//                .collect(Collectors.toList());
    }

    public HandsType getHandsType() {
        return handsType;
    }
}

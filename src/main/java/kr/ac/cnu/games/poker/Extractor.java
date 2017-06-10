package kr.ac.cnu.games.poker;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rokim on 2017. 6. 4..
 */
@Component
public class Extractor {
    public Hands extractHighHands(List<Card> cardList) {
        //TODO 주어진 N개의 카드에 대해 가장 높은 조합을 가지는 5개의 카드를 추출해 내야 한다.
        //TODO 한마디로 말해서 카드 중에 제일 높은 족보를 추출
        return null;
    }

    public Hands extractLowHands(List<Card> cardList) {
        // do Something
        return null;
    }
}

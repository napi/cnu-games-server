package kr.ac.cnu.games.poker;

import org.springframework.stereotype.Component;

import javax.swing.text.html.HTMLDocument;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rokim on 2017. 6. 4..
 */
@Component
public class Evaluator {
    public List<Hands> evalauteHighHands(List<Hands> handsList) {

        Comparator<Hands> sort = new Comparator<Hands>() {
            public int compare(Hands o1, Hands o2) {

                //handtype 이 같을 때
                if(o1.getHandsType().compareTo(o2.getHandsType()) == 0){
                    //원페어 투페어 쓰리카드 포카드 일 경우
                    if(o1.getHandsType() == HandsType.ONE_PAIR || o1.getHandsType() == HandsType.TWO_PAIR || o1.getHandsType() == HandsType.THREE_CARD || o1.getHandsType() == HandsType.FOUR_CARD){
                        return checkPairs(o1, o2);
                    }

                    //아무것도 없을 경우 큰 수자로 비교
                    else if(o1.getHandsType() == HandsType.NOTHING){
                        if(o1.getCardList().get(0).getNumber() > o2.getCardList().get(0).getNumber())
                            return -1;
                        else
                            return 1;
                    }

                    //풀하우스일 경우 세개 숫자 크기 비교
                    else if(o1.getHandsType() == HandsType.FULL_HOUSE) {
                        return checkFullhouse(o1, o2);
                    }

                    //플러쉬일 경우 문자 비교
                    else if(o1.getHandsType() == HandsType.FLUSH) {
                        if (o1.getCardList().get(0).getSuit().compareTo(o2.getCardList().get(0).getSuit()) > 1)
                            return 1;
                        else
                            return -1;
                    }

                    //스트레이트일경우 큰 숫자 비교
                    else if(o1.getHandsType() == HandsType.STRIGHT) {
                        if(o1.getCardList().get(0).getNumber() > o2.getCardList().get(0).getNumber())
                            return -1;
                        else
                            return 1;
                    }
                }

                return (o1.getHandsType().compareTo(o2.getHandsType()));
            }
        };

        Collections.sort(handsList, sort);



        return handsList;
    }

    private int checkFullhouse(Hands o1, Hands o2) {
        int i = 0;
        int j = 0;

        while(o1.getCardList().get(i).getNumber() != o1.getCardList().get(i+1).getNumber()
                || o1.getCardList().get(i+1).getNumber()!=o1.getCardList().get(i+2).getNumber()) { i++;}
        while(o2.getCardList().get(j).getNumber() != o2.getCardList().get(j+1).getNumber()
                || o2.getCardList().get(j+1).getNumber()!=o2.getCardList().get(j+2).getNumber()) { j++;}

        if(o1.getCardList().get(i).getNumber() > o2.getCardList().get(j).getNumber())
            return -1;
        else
            return 1;
    }

    private int checkPairs(Hands o1, Hands o2) {
        int i = 0;
        int j = 0;

        while(o1.getCardList().get(i).getNumber()!=o1.getCardList().get(i+1).getNumber()) { i++;}
        while(o2.getCardList().get(j).getNumber()!=o2.getCardList().get(j+1).getNumber()) { j++;}
        if(o1.getCardList().get(i).getNumber() > o2.getCardList().get(j).getNumber())
            return -1;
        else
            return 1;
=======
                return (o1.getHandsType().compareTo(o2.getHandsType()));
            }
        };
        Collections.sort(handsList, sort);

        return handsList;

    }

    public List<Hands> evalauteLowHands(List<Hands> handsList) {
        // do Something
        Comparator<Hands> sort = new Comparator<Hands>() {
            public int compare(Hands o1, Hands o2) {
                return (o2.getHandsType().compareTo(o1.getHandsType()));
            }
        };
        Collections.sort(handsList, sort);

        return handsList;
    }

//    TODO


}
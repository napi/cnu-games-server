package kr.ac.cnu.games.poker;

import org.springframework.stereotype.Component;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

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
//                        return checkPairs(o1, o2);
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

//    private int checkPairs(Hands o1, Hands o2) {
//        int i = 0;
//        int j = 0;
//
//        while(o1.getCardList().get(i).getNumber()!=o1.getCardList().get(i+1).getNumber()) { i++;}
//        while(o2.getCardList().get(j).getNumber()!=o2.getCardList().get(j+1).getNumber()) { j++;}
//        if(o1.getCardList().get(i).getNumber() > o2.getCardList().get(j).getNumber())
//            return -1;
//        else
//            return 1;
//=======
//                return (o1.getHandsType().compareTo(o2.getHandsType()));
//            }
//        };
//        Collections.sort(handsList, sort);
//
//        return handsList;
//
//    }
//

    // 족보를 내림차순 정렬
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


    public int evaluateLowHandsRanks(Hands myHands, Hands otherHands){

        return 0;
    }

    public int evalauteHandsType(Hands myHands,Hands otherHands) {

        if (!myHands.getHandsType().equals(otherHands.getHandsType())) {
            return myHands.getHandsType().ordinal() - otherHands.getHandsType().ordinal(); // 양수면 내가이긴다
        } else {
            LowCardComparator lowComp = new LowCardComparator();
            Collections.sort(myHands.getCardList(), lowComp);
            Collections.sort(otherHands.getCardList(), lowComp);

            Iterator<Card> myCard = myHands.getCardList().iterator();
            Iterator<Card> otherCard = otherHands.getCardList().iterator();
            int comp;
            List<Card> myTempList = new ArrayList<>();
            List<Card> otherTempList = new ArrayList<>();

            switch (myHands.getHandsType()) {
                case STRIGHT_FLUSH:
                    myCard = myHands.getCardList().iterator();
                    otherCard = otherHands.getCardList().iterator();

                    while (myCard.hasNext() && otherCard.hasNext()) {
                        return lowComp.compare(myCard.next(), otherCard.next());
                    }
                case FOUR_CARD:
                    return evaluateLowNum(myHands, otherHands, 4);
                case FULL_HOUSE:
                    return evaluateLowNum(myHands, otherHands, 3);
                case FLUSH:
                    myCard = myHands.getCardList().iterator();
                    otherCard = otherHands.getCardList().iterator();

                    while (myCard.hasNext() && otherCard.hasNext()) {
                        return lowComp.compare(myCard.next(), otherCard.next());
                    }
                case STRIGHT:
                    myCard = myHands.getCardList().iterator();
                    otherCard = otherHands.getCardList().iterator();

                    while (myCard.hasNext() && otherCard.hasNext()) {
                        return lowComp.compare(myCard.next(), otherCard.next());
                    }
                case THREE_CARD:
                    return evaluateLowNum(myHands, otherHands, 3);
                case TWO_PAIR:
                    int myNPair;
                    int otherNPair;
                    if(myHands.getCardList().get(0).getNumber() - myHands.getCardList().get(1).getNumber() == 0 ) {
                        if(myHands.getCardList().get(2).getNumber() - myHands.getCardList().get(3).getNumber() == 0 ) {
                            myNPair = 4;
                        }else {
                            myNPair = 2;
                        }
                    }else {
                        myNPair = 0;
                    }
                    if(otherHands.getCardList().get(0).getNumber() - otherHands.getCardList().get(1).getNumber() == 0 ) {
                        if(otherHands.getCardList().get(2).getNumber() - otherHands.getCardList().get(3).getNumber() == 0 ) {
                            otherNPair = 4;
                        }else {
                            otherNPair = 2;
                        }
                    }else {
                        otherNPair = 0;
                    }
                    for (int i =0; i<5; i++) {
                        if( i == myNPair) {
                            continue;
                        }
                        myTempList.add(myHands.getCardList().get(i));
                    }
                    for (int i =0; i<5; i++) {
                        if( i == otherNPair) {
                            continue;
                        }
                        otherTempList.add(otherHands.getCardList().get(i));
                    }
                    for (int i=0; i<4; i++) {
                        if (lowComp.compare(myTempList.get(i), otherTempList.get(i), 1) == 0) {
                            i++;
                        } else {
                            return lowComp.compare(myTempList.get(i), otherTempList.get(i), 1);
                        }
                    }
                    if (lowComp.compare(myHands.getCardList().get(myNPair),otherHands.getCardList().get(otherNPair),1) == 0) {
                        return lowComp.compare(myTempList.get(0), otherTempList.get(0));
                    } else {
                        return lowComp.compare(myHands.getCardList().get(myNPair),otherHands.getCardList().get(otherNPair),1);
                    }
                case ONE_PAIR:
                    Card card1 = myCard.next();
                    Card card2;
                    Card card3 = otherCard.next();
                    while (myCard.hasNext()) {
                        card2 = myCard.next();
                        if (card1.getNumber() - card2.getNumber() != 0) {
                            myTempList.add(card1);
                            card1 = card2;
                            continue;
                        } else {        // card1 과 card2가 같을 때
                            while(myCard.hasNext()) {
                                myTempList.add(myCard.next());
                            }
                            break;
                        }
                    }
                    while (otherCard.hasNext()) {
                        card2 = otherCard.next();
                        if (card3.getNumber() - card2.getNumber() != 0) {
                            otherTempList.add(card3);
                            card3 = card2;
                            continue;
                        } else {
                            while(otherCard.hasNext()) {
                                otherTempList.add(otherCard.next());
                            }
                            break;
                        }
                    }
                    if (lowComp.compare(card1, card3, 1) == 0 ) {
                        int result;
                        int i= 0;
                        while(i < 3) {
                            if((result = lowComp.compare(myTempList.get(i),otherTempList.get(i), 1)) != 0) {
                                return result;
                            }
                            i++;
                        }
                        return lowComp.compare(card1,card3);
                    }
                case NOTHING:
                    while (myCard.hasNext()) {
                        if ((comp = lowComp.compare(myCard.next(), otherCard.next(), 1)) == 0) {
                            continue;
                        } else {
                            return comp;
                        }
                    }
                    return myHands.getCardList().get(0).getSuit().compareTo(otherHands.getCardList().get(0).getSuit());
                default:
            }
        }
        return 0;
    }

    private int evaluateLowNum(Hands myHands, Hands otherHands, int duplicated) {
        Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> otherMap = new HashMap<Integer, Integer>();

        int myNum = 0;
        int otherNum = 0;
        for (Card card : myHands.getCardList()) {
            if (myMap.containsKey(card.getNumber())) {
                Integer count = myMap.get(card.getNumber());
                count = new Integer(count.intValue() + 1);
                myMap.put(card.getNumber(), count);
            } else {
                myMap.put(card.getNumber(), new Integer(1));
            }
        }
        for (Card card : otherHands.getCardList()) {
            if (otherMap.containsKey(card.getNumber())) {
                Integer count = otherMap.get(card.getNumber());
                count = new Integer(count.intValue() + 1);
                otherMap.put(card.getNumber(), count);
            } else {
                otherMap.put(card.getNumber(), new Integer(1));
            }
        }

        for (int key : myMap.keySet()) {
            if (myMap.get(key) == duplicated) {
                myNum = key;
            }
        }

        for (int key : otherMap.keySet()) {
            if (otherMap.get(key) == duplicated) {
                otherNum = key;
            }
        }

        return otherNum - myNum;
    }
}
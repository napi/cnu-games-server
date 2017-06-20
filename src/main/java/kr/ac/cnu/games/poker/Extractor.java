package kr.ac.cnu.games.poker;

import org.springframework.stereotype.Component;

import java.util.*;

import static org.apache.coyote.http11.Constants.a;

/**
 * Created by rokim on 2017. 6. 4..
 */
@Component
public class Extractor {
    public Hands extractHighHands(List<Card> cardList) {
        // do Something
        return null;
    }

    public Hands extractLowHands(List<Card> cardList) {
        // do Something
        List<Card> compareCardList = new ArrayList<>();
        List<Card> minCardList = new ArrayList<>();
        List<Integer> maxCardNumber1 = new ArrayList<>();
        List<Integer> maxCardNumber2 = new ArrayList<>();
        int min = 14, compareInt;

        for (int i = 0; i < 6; i++) {
            for (int j = i+1; j < 7; j++) {

                compareCardList.clear();
                compareCardList.add(cardList.get(0));
                compareCardList.add(cardList.get(1));
                compareCardList.add(cardList.get(2));
                compareCardList.add(cardList.get(3));
                compareCardList.add(cardList.get(4));
                compareCardList.add(cardList.get(5));
                compareCardList.add(cardList.get(6));

                compareCardList.remove(cardList.get(i));
                compareCardList.remove(cardList.get(j));

                compareInt = evaluate(compareCardList);
                if (compareInt < min) {
                    min = compareInt;

                    minCardList.clear();
                    minCardList.add(compareCardList.get(0));
                    minCardList.add(compareCardList.get(1));
                    minCardList.add(compareCardList.get(2));
                    minCardList.add(compareCardList.get(3));
                    minCardList.add(compareCardList.get(4));
                }
                else if (compareInt == min) { // 숫자 하는 중.

                    maxCardNumber1.clear();
                    maxCardNumber1.add(minCardList.get(0).getNumber());
                    maxCardNumber1.add(minCardList.get(1).getNumber());
                    maxCardNumber1.add(minCardList.get(2).getNumber());
                    maxCardNumber1.add(minCardList.get(3).getNumber());
                    maxCardNumber1.add(minCardList.get(4).getNumber());
                    Collections.sort(maxCardNumber1);

                    maxCardNumber2.clear();
                    maxCardNumber2.add(compareCardList.get(0).getNumber());
                    maxCardNumber2.add(compareCardList.get(1).getNumber());
                    maxCardNumber2.add(compareCardList.get(2).getNumber());
                    maxCardNumber2.add(compareCardList.get(3).getNumber());
                    maxCardNumber2.add(compareCardList.get(4).getNumber());
                    Collections.sort(maxCardNumber2);

                    if (maxCardNumber1.get(0) == 1) {
                        min = compareInt;

                        minCardList.clear();
                        minCardList.add(compareCardList.get(0));
                        minCardList.add(compareCardList.get(1));
                        minCardList.add(compareCardList.get(2));
                        minCardList.add(compareCardList.get(3));
                        minCardList.add(compareCardList.get(4));
                    }
                    else if (maxCardNumber2.get(0) == 1) {

                    }
                    else if (maxCardNumber1.get(4) > maxCardNumber2.get(4)) {
                        min = compareInt;

                        minCardList.clear();
                        minCardList.add(compareCardList.get(0));
                        minCardList.add(compareCardList.get(1));
                        minCardList.add(compareCardList.get(2));
                        minCardList.add(compareCardList.get(3));
                        minCardList.add(compareCardList.get(4));
                    }

                }


            }
        }
        Hands result;
        switch (min){
            case 0:
                result = new Hands(HandsType.NOTHING,minCardList);
                break;
            case 1:
                result = new Hands(HandsType.ONE_PAIR,minCardList);
                break;
            case 2:
                result = new Hands(HandsType.TWO_PAIR,minCardList);
                break;
            case 3:
                result = new Hands(HandsType.THREE_CARD,minCardList);
                break;
            case 4:
                result = new Hands(HandsType.STRIGHT,minCardList);
                break;
            case 5:
                result = new Hands(HandsType.STRIGHT,minCardList);
                break;
            case 6:
                result = new Hands(HandsType.STRIGHT,minCardList);
                break;
            case 7:
                result = new Hands(HandsType.FLUSH,minCardList);
                break;
            case 8:
                result = new Hands(HandsType.FULL_HOUSE,minCardList);
                break;
            case 9:
                result = new Hands(HandsType.FOUR_CARD,minCardList);
                break;
            default:
                result = new Hands(HandsType.STRIGHT_FLUSH,minCardList);

        }
        return result;
    }

    public int evaluate(List<Card> cardList) {
        boolean isFlush = flush(cardList);


        Map<Integer, Integer> rankMap = new HashMap<Integer, Integer>();
        for (Card card : cardList) {
            if (rankMap.containsKey(card.getNumber())) {
                Integer count = rankMap.get(card.getNumber());
                count = new Integer(count.intValue() + 1);
                rankMap.put(card.getNumber(), count);
            } else {
                rankMap.put(card.getNumber(), new Integer(1));
            }
        }

        boolean isMountain = mountain(rankMap);
        boolean isStraight = straight(rankMap);
        boolean isBackstraight = backstraight(rankMap);

        if (isFlush) {
            //로티플
            if ( isMountain ) return 12;
            //백스트레이트 플러쉬
            if ( isBackstraight ) return 11;
            //스트레이트 플러쉬
            if ( isStraight ) return 10;
        }

        //포카드
        if ( fourcard(rankMap) ) return 9;

        //풀하우스
        if ( fullhouse(rankMap) ) return 8;

        //플러시
        if ( isFlush ) return 7;

        //마운틴
        if ( isMountain ) return 6;

        //백스트레이트
        if ( isBackstraight ) return 5;

        //스트레이스
        if ( isStraight ) return 4;

        //트리플
        if ( triple(rankMap) ) return 3;

        //투 페어

        if ( twopair(rankMap) ) return 2;

        //원 페어
        if ( onepair(rankMap) ) return 1;

        //탑
        return 0;

    }


    public boolean straight(Map<Integer,Integer> rankMap) {
        int count=0;

        for (int i=1;i<=13;i++) {
            if (rankMap.containsKey(i))
                count++;
            else
                count=0;
            if(count==5)
                return true;
        }

        return false;
    }


    public boolean backstraight(Map<Integer,Integer> rankMap){

        if (rankMap.containsKey(1))
            for(int i=2;i<=5;i++) {
                if (!rankMap.containsKey(i))
                    return false;
            }
        else
            return false;

        return true;


    }

    private boolean flush(List<Card> cardList) {
        boolean isFlush = false;

        Map<Suit, Integer> suitMap = new HashMap<Suit, Integer>();

        for (Card card : cardList) {
            if (suitMap.containsKey(card.getSuit())) {
                Integer count = suitMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                suitMap.put(card.getSuit(), count);
            } else {
                suitMap.put(card.getSuit(), new Integer(1));
            }
        }
        for (Suit key : suitMap.keySet()) {
            if (suitMap.get(key) == 5) {
                isFlush = true;
            }
        }

        return isFlush;
    }

    private boolean fourcard(Map<Integer, Integer> rankMap) {
        for (Integer key: rankMap.keySet()) {
            if (rankMap.get(key) == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean triple(Map<Integer, Integer> rankMap) {
        for (Integer key: rankMap.keySet()) {
            if (rankMap.get(key) == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean onepair(Map<Integer, Integer> rankMap) {
        for (Integer key: rankMap.keySet()) {
            if (rankMap.get(key) == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean twopair(Map<Integer, Integer> rankMap) {
        int count = 0;
        for (Integer key: rankMap.keySet()) {
            if (rankMap.get(key) == 2) {
                count++;
            }
        }
        if (count >= 2) {
            return true;
        }
        return false;
    }

    private boolean mountain(Map<Integer, Integer> rankMap) {
        if (rankMap.containsKey(1))
            for(int i=10;i<=13;i++) {
                if (!rankMap.containsKey(i))
                    return false;
            }
        else
            return false;

        return true;
    }

    private boolean fullhouse(Map<Integer, Integer> rankMap) {
        boolean flag1=false,flag2 = false;
        for (Integer key : rankMap.keySet()) {
            if(rankMap.get(key) == 3){
                flag1 = true;
            }
            if(rankMap.get(key) == 2){
                flag2 = true;
            }
        }
        if(flag1 && flag2){
            return true;
        }
        return false;
    }


}

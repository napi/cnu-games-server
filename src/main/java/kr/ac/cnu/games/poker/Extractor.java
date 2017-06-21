package kr.ac.cnu.games.poker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

import static org.apache.coyote.http11.Constants.a;

/**
 * Created by rokim on 2017. 6. 4..
 */
@Component
public class Extractor {
    public Hands extractHighHands(List<Card> cardList) {
        //TODO 주어진 N개의 카드에 대해 가장 높은 조합을 가지는 5개의 카드를 추출해 내야 한다.
        //TODO 한마디로 말해서 카드 중에 제일 높은 족보를 추출
        Evaluator5zo e5 = new Evaluator5zo();   // inner class를 사용하기 위한 객체 생성
        Hands hand = new Hands(e5.evaluate(cardList), cardList);

        return hand;
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

    // 가지고 있는 카드의 족보를 확인하기 위한 inner Class
    private class Evaluator5zo {
        public HandsType evaluate(List<Card> cardList) {
            // suit가 몇개 나오는지 같은 숫자가 몇개 있는지 확인하기 위해서 Map에다가 저장
            Map<Suit, Integer> suitMap = new HashMap<Suit, Integer>();
            Map<Integer, Integer> integerMap = new HashMap<Integer, Integer>();

            // Map에 suit와 같은 숫자를 비교해서 추가
            for (Card card : cardList) {
                // Suit가 몇개를 가지고 있는지 확인
                if (suitMap.containsKey(card.getSuit())) {
                    Integer count = suitMap.get(card.getSuit());
                    count = count + 1;
                    suitMap.put(card.getSuit(), count);
                } else {
                    suitMap.put(card.getSuit(), 1);
                }

                // 같은 숫자가 몇개나 있는지 확인
                if (integerMap.containsKey(card.getNumber())) {
                    Integer count = integerMap.get(card.getNumber());
                    count = count + 1;
                    integerMap.put(card.getNumber(), count);
                } else {
                    integerMap.put(card.getNumber(), 1);
                }
            }

            // 족보 순서대로 비교확인하여 일치하면 반환시
            if(getStraightFlush(suitMap, cardList)) return HandsType.STRIGHT_FLUSH;
            if(getFourCard(integerMap)) return HandsType.FOUR_CARD;
            if(getFullHouse(integerMap)) return HandsType.FULL_HOUSE;
            if(getFlush(suitMap)) return HandsType.FLUSH;
            if(getStraight(integerMap)) return HandsType.STRIGHT;
            if(getTriple(integerMap)) return HandsType.THREE_CARD;
            if(getTwoPair(integerMap)) return HandsType.TWO_PAIR;
            if(getOnePair(integerMap)) return HandsType.ONE_PAIR;

            // 아무것도 일치하지 않을 경우 NOTHING으로 반환
            return HandsType.NOTHING;
        }

        //본래 Suit와 관계없이 STRIGHT여부를 계산하여 수정
        private boolean getStraightFlush(Map<Suit, Integer> suitMap, List<Card> cardList){
            Suit canFlush = null;//FLUSH가 될 수 있는 Suit를 임시저장할 변수
            for(Suit key : suitMap.keySet()){
                if(suitMap.get(key) == 5)
                    canFlush = key;//FLUSH가 될 수 있는 Suit저장
            }
            if(canFlush == null)
                return false;//FLUSH가 될 수 없다면 STRIGHT_FLUSH도 될 수 없음
            List<Card> tempList = new ArrayList<>();//STRIGHT여부를 확인할 Card들을 임시저장할 변수
            for(Card card : cardList){
                if(card.getSuit() == canFlush)
                    tempList.add(card);//canFlush에 저장된 Suit와 같은 Suit를 갖는 Card들 저장
            }
            Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();//tempList를 number, 개수로 맵핑할 변수
            for(Card card : tempList){//임시 맵에 맵핑하는 반복문
                if (tempMap.containsKey(card.getNumber())) {
                    Integer count = tempMap.get(card.getNumber());
                    count = count + 1;
                    tempMap.put(card.getNumber(), count);
                } else {
                    tempMap.put(card.getNumber(), 1);
                }
            }
            return getStraight(tempMap);//임시 맵으로 STRIGHT여부 판단 참일 경우 FLUSH이며 STRIGHT이므로 STRIGHT_FLUSH만족
        }

        // Four Card인지 확인하기 위한 함수
        private boolean getFourCard(Map<Integer, Integer> integerMap) {
            for(Integer key : integerMap.keySet()){
                if(integerMap.get(key) == 4)
                    return true;
            }
            return false;
        }

        // Full House인지 확인하기 위한 함수
        private boolean getFullHouse(Map<Integer, Integer> integerMap) {
            int triple = 0;
            int pair = 0;
            for(int key : integerMap.keySet()){
                if(integerMap.get(key) == 3){
                    triple = key;
                }
                if(integerMap.get(key) == 2){
                    pair = key;
                }
            }
            return triple != 0 && pair != 0;
        }

        // Flush인지 확인하기 위한 함수
        private boolean getFlush(Map<Suit, Integer> suitMap) {
            for(Suit key : suitMap.keySet()){
                if(suitMap.get(key) == 5){ //기존 코드에 조건문 추가 적용 by dhn
                    return true;
                }
            }
            return false;
        }

        //Straight인지 확인하기 위한 함수 새로운 arrayList를 사용함
        private boolean getStraight(Map<Integer, Integer> integerMap){
            int count = 0;
            int max = 0;
            int gap;

            List<Integer> tempList = new ArrayList<>();

            for(int key = 1; key < 14; key++){
                if(integerMap.get(key) != null) {
                    for (int i = 0; i < integerMap.get(key); i++) {
                        tempList.add(new Integer(key));
                    }
                }
            }

            for(int i = 0; i < tempList.size(); i++){
                if(i == 0)
                    max = tempList.get(i);
                else{
                    gap = tempList.get(i) - tempList.get(i-1);
                    if(gap == 1){
                        count++;
                        max = tempList.get(i);
                    }
                    else if(gap == 0){

                    }
                    else{
                        count = 0;
                    }
                }
            }
            if(count == 3 && max == 13){
                if(tempList.get(0) == 1)
                    return true;
            }
            return count >= 4;
        }


        // Triple인지 확인하기 위한 함수
        private boolean getTriple(Map<Integer, Integer> integerMap) {
            for(Integer key : integerMap.keySet()){
                if(integerMap.get(key) == 3){ //기존 코드에 조건문 추가 적용 by dhn
                    return true;
                }
            }
            return false;
        }

        // Two Pair인지 확인하기 위한 함수
        private boolean getTwoPair(Map<Integer, Integer> integerMap) {
            int count = 0;
            for(Integer key : integerMap.keySet()){
                if(integerMap.get(key) == 2){
                    count++;
                }
            }
            return count == 2;
        }

        // One Pair인지 확인하기 위한 함수
        private boolean getOnePair(Map<Integer, Integer> integerMap) {
            for(Integer key : integerMap.keySet()){
                if(integerMap.get(key) == 2){ //기존 코드에 조건문 추가 적용 by dhn
                    return true;
                }
            }
            return false;
        }
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

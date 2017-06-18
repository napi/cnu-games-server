package kr.ac.cnu.games.poker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return null;
    }

    // 가지고 있는 카드의 족보를 확인하기 위한 inner Class
    public class Evaluator5zo {
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
            if(getStraightFlush(suitMap, integerMap)) return HandsType.STRIGHT_FLUSH;
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

        // Straight Flush인지 확인하기 위한 함수, 조건에 맞기 위해서 Straight함수와 Flush함수 이용
        private boolean getStraightFlush(Map<Suit, Integer> suitMap, Map<Integer, Integer> integerMap){
            return getStraight(integerMap) && getFlush(suitMap);
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

        //Straight인지 확인하기 위한 함수
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
}

package kr.ac.cnu.games.poker;

import org.springframework.stereotype.Component;

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

        private boolean getRoyalStraightFlush(Map<Suit, Integer> suitMap, Map<Integer, Integer> integerMap){
            return getFlush(suitMap) && getMountain(integerMap);
        }

        private boolean getBackStraightFlush(Map<Suit, Integer> suitMap, Map<Integer, Integer> integerMap){
            return getFlush(suitMap) && getBackStraight(integerMap);
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

        private boolean getBackStraight(Map<Integer, Integer> integerMap){
            return integerMap.containsKey(1) && integerMap.containsKey(2) && integerMap.containsKey(3) && integerMap.containsKey(4) && integerMap.containsKey(5);
        }

        private boolean getMountain(Map<Integer, Integer> integerMap){
            return integerMap.containsKey(10) && integerMap.containsKey(11) &&integerMap.containsKey(12) && integerMap.containsKey(13) && integerMap.containsKey(1);
        }

        // Flush인지 확인하기 위한 함수
        private boolean getFlush(Map<Suit, Integer> suitMap) {
            for(Suit key : suitMap.keySet()){
                return suitMap.get(key) == 5;
            }
            return false;
        }

        //Straight인지 확인하기 위한 함수
        private boolean getStraight(Map<Integer, Integer> integerMap){
            int min = 14;
            int max = 0;

            for(int key : integerMap.keySet()){
                if(integerMap.get(key) >= 2){
                    return false;
                }
                if(integerMap.get(key) == 1){
                    min = min < key? min : key;
                    max = max > key? max : key;
                }
            }
            return max - min == 4;
        }

        // Triple인지 확인하기 위한 함수
        private boolean getTriple(Map<Integer, Integer> integerMap) {
            for(Integer key : integerMap.keySet()){
                return integerMap.get(key) == 3;
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
                return integerMap.get(key) == 2;
            }
            return false;
        }
    }
}

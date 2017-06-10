package kr.ac.cnu.games.poker;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * Created by rokim on 2017. 6. 4..
 */
@Component
public class Evaluator {
    public List<Hands> evalauteHighHands(List<Hands> handsList) {
        // hansList 의 HandType 을 확인
        // get~~~ 하여 확인한 뒤 ordering
        // 이 때 같은 HandsType 의 경우 모양 숫자도 확인

        boolean isSameHandsType;

        return null;
    }

    public List<Hands> evalauteLowHands(List<Hands> handsList) {
        // do Something
        return null;
    }

}

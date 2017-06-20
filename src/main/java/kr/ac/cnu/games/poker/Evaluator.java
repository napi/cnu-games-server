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
        // hansList 의 HandType 을 확인
        // get~~~ 하여 확인한 뒤 ordering
        // 이 때 같은 HandsType 의 경우 모양 숫자도 확인

//        boolean isSameHandsType;
//
//        return null;

        Comparator<Hands> sort = new Comparator<Hands>() {
            public int compare(Hands o1, Hands o2) {
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

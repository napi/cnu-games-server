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
        // do Something
        return null;
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

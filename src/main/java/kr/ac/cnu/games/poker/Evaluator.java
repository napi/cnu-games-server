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

                if(o1.getHandsType().compareTo(o2.getHandsType()) == 0){
                    if(o1.getHandsType() == HandsType.ONE_PAIR || o1.getHandsType() == HandsType.TWO_PAIR || o1.getHandsType() == HandsType.THREE_CARD || o1.getHandsType() == HandsType.FOUR_CARD){
                        int i = 0;
                        int j = 0;

                        while(o1.getCardList().get(i).getNumber()!=o1.getCardList().get(i+1).getNumber()) {
                            i++;

                        }
                        while(o2.getCardList().get(j).getNumber()!=o2.getCardList().get(j+1).getNumber()){
                            j++;
                        }

                        if(o1.getCardList().get(i).getNumber() > o2.getCardList().get(j).getNumber()) {
                            return -1;
                        }
                        else
                            return 1;
                    }

                    else if(o1.getHandsType() == HandsType.NOTHING){
                        if(o1.getCardList().get(0).getNumber() > o2.getCardList().get(0).getNumber()) {
                            return -1;
                        }
                        else
                            return 1;
                    }

                    else if(o1.getHandsType() == HandsType.FLUSH) {

                    }

                    else if(o1.getHandsType() == HandsType.FULL_HOUSE) {

                    }

                    else if(o1.getHandsType() == HandsType.STRIGHT_FLUSH) {

                    }
                    else if(o1.getHandsType() == HandsType.STRIGHT) {

                    }
                }

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

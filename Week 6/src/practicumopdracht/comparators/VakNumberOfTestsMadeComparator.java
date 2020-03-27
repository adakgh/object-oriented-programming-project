package practicumopdracht.comparators;

import practicumopdracht.models.Vak;

import java.util.Comparator;

/**
 * Master aantal gemaakte toetsen comparator voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class VakNumberOfTestsMadeComparator {

    public static class vakAantalOplopend implements Comparator<Vak> {
        //oplopende sortering
        @Override
        public int compare(Vak o1, Vak o2) {
            return o1.getAantalGemaakteToetsen() - o2.getAantalGemaakteToetsen();
        }
    }

    public static class vakAantalAflopend implements Comparator<Vak> {
        //aflopende sortering
        @Override
        public int compare(Vak o1, Vak o2) {
            return o2.getAantalGemaakteToetsen() - o1.getAantalGemaakteToetsen();
        }
    }
}

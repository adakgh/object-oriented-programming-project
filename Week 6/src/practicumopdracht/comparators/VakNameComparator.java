package practicumopdracht.comparators;

import practicumopdracht.models.Vak;

import java.util.Comparator;

public class VakNameComparator {

    public static class vakNaamOplopend implements Comparator<Vak> {
        //oplopende sortering
        @Override
        public int compare(Vak o1, Vak o2) {
            return o1.getToetsNaam().compareTo(o2.getToetsNaam());
        }
    }

    public static class vakNaamAflopend implements Comparator<Vak> {
        //aflopende sortering
        @Override
        public int compare(Vak o1, Vak o2) {
            return o2.getToetsNaam().compareTo(o1.getToetsNaam());
        }
    }
}

package practicumopdracht.comparators;

import practicumopdracht.models.Resultaat;

import java.util.Comparator;

public class ResultatenDateComparator {

    public static class resultaatDatumOplopend implements Comparator<Resultaat> {
        //oplopende sortering
        @Override
        public int compare(Resultaat o1, Resultaat o2) {
            return o1.getDatum().compareTo(o2.getDatum());
        }
    }

    public static class resultaatDatumAflopend implements Comparator<Resultaat> {
        //aflopende sortering
        @Override
        public int compare(Resultaat o1, Resultaat o2) {
            return o1.getDatum().compareTo(o2.getDatum());
        }
    }
}

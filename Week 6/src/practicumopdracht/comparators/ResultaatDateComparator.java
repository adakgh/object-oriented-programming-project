package practicumopdracht.comparators;

import practicumopdracht.models.Resultaat;

import java.util.Comparator;

/**
 * Detail datum comparator voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class ResultaatDateComparator {

    public static class resultaatDatumOplopend implements Comparator<Resultaat> {
        //oplopende sortering
        @Override
        public int compare(Resultaat o1, Resultaat o2) {
            int compare = 0;
            compare = o1.getDatum().compareTo(o2.getDatum());
            //als data gelijk zijn sorteer op oplopend op studentennummer
            if (compare == 0) {
                return Integer.compare(o1.getStudentennummer(), o2.getStudentennummer());
            } else {
                return compare;
            }
        }

    }

    public static class resultaatDatumAflopend implements Comparator<Resultaat> {
        //aflopende sortering
        @Override
        public int compare(Resultaat o1, Resultaat o2) {
            int compare = 0;
            compare = o2.getDatum().compareTo(o1.getDatum());
            //als data gelijk zijn sorteer op oplopend op studentennummer
            if (compare == 0) {
                return Integer.compare(o1.getStudentennummer(), o2.getStudentennummer());
            } else {
                return compare;
            }
        }
    }
}

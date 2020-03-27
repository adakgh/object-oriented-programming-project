package practicumopdracht.comparators;

import practicumopdracht.models.Resultaat;

import java.util.Comparator;

/**
 * Detail studentennummer comparator voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class ResultaatStudentIDComparator {

    public static class resultaatStudentennummerOplopend implements Comparator<Resultaat> {
        //oplopende sortering
        @Override
        public int compare(Resultaat o1, Resultaat o2) {
            return Integer.compare(o1.getStudentennummer(), o2.getStudentennummer());
        }
    }

    public static class resultaatStudentennummerAflopend implements Comparator<Resultaat> {
        //aflopende sortering
        @Override
        public int compare(Resultaat o1, Resultaat o2) {
            return Integer.compare(o2.getStudentennummer(), o1.getStudentennummer());
        }
    }
}

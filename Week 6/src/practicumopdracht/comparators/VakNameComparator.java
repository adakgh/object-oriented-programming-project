package practicumopdracht.comparators;

import practicumopdracht.models.Vak;

import java.util.Comparator;

/**
 * Master naam comparator voor OOP2 practicumopdracht.
 *
 */
public class VakNameComparator {

    public static class vakNaamOplopend implements Comparator<Vak> {
        //oplopende sortering
        @Override
        public int compare(Vak o1, Vak o2) {
            return o1.getVakNaam().compareTo(o2.getVakNaam());
        }
    }

    public static class vakNaamAflopend implements Comparator<Vak> {
        //aflopende sortering
        @Override
        public int compare(Vak o1, Vak o2) {
            return o2.getVakNaam().compareTo(o1.getVakNaam());
        }
    }
}

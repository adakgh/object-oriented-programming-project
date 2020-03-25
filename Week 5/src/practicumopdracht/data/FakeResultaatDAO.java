package practicumopdracht.data;

import practicumopdracht.models.Resultaat;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Detail Fake DAO voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class FakeResultaatDAO extends ResultaatDAO {
    @Override
    public boolean load() {
        objects = new ArrayList<>();
        objects.add(new Resultaat(1, 102345789, "Jantje Beton", LocalDate.of(2020, 3, 25), 10, true));
        objects.add(new Resultaat(2, 113456789, "Pieter Zoon", LocalDate.of(2020, 3, 24), 9.5, true));
        objects.add(new Resultaat(3, 123456789, "Jan Klaas", LocalDate.of(2020, 3, 27), 4, false));
        return true;
    }

    @Override
    public boolean save() {
        return false;
    }
}

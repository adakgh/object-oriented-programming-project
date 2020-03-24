package practicumopdracht.data;

import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;

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
        objects.add(new Resultaat(1, 102345789, "Jantje Beton", LocalDate.of(2020, 3, 25), 10, true, (new Vak("Programming", "Tentamen", 30))));
        objects.add(new Resultaat(2, 113456789, "Pieter Zoon", LocalDate.of(2020, 3, 24), 9.5, true, (new Vak("Database", "Herkansing tentamen", 10))));
        objects.add(new Resultaat(3, 123456789, "Jan Klaas", LocalDate.of(2020, 3, 27), 4, false, (new Vak("OOP1", "Tentamen", 20))));
        return true;
    }

    @Override
    public boolean save() {
        return false;
    }
}

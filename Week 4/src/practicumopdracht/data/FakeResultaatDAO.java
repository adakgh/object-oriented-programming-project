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
        addOrUpdate(new Resultaat(1, 123456789, "Testpersoon test", LocalDate.now(), 10, true, (new Vak("Programming", "Tentamen", 30))));
        return true;
    }

    @Override
    public boolean save() {
        return false;
    }
}

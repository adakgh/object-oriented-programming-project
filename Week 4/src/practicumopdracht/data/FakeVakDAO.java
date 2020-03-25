package practicumopdracht.data;

import practicumopdracht.models.Vak;

import java.util.ArrayList;

/**
 * Master Fake DAO voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class FakeVakDAO extends VakDAO {
    @Override
    public boolean load() {
        objects = new ArrayList<>();

        addOrUpdate(new Vak("Programming", "Tentamen", 30));
        addOrUpdate(new Vak("Database", "Herkansing tentamen", 10));
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }
}

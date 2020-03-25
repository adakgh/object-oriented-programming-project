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
        objects.add(new Vak("Programming", "Tentamen", 30));
        objects.add(new Vak("Database", "Herkansing tentamen", 10));
        objects.add(new Vak("OOP1", "Tentamen", 20));
        objects.add(new Vak("Nederlands", "Schrijfopdracht", 15));
        return true;
    }

    @Override
    public boolean save() {
        return false;
    }
}

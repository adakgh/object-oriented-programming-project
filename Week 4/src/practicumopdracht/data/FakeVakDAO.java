package practicumopdracht.data;

import practicumopdracht.models.Vak;

import java.util.ArrayList;

public class FakeVakDAO extends VakDAO {

    public FakeVakDAO(){
        load();
    }

    @Override
    public boolean load() {
        objects = new ArrayList<>();
        objects.add(new Vak("Programming", "Tentamen", 30));
        objects.add(new Vak("Database", "Herkansing tentamen", 10));

        return false;
    }

    @Override
    public boolean save() {
        return false;
    }
}

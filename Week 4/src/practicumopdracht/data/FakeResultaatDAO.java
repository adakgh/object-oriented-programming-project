package practicumopdracht.data;

import practicumopdracht.models.Resultaat;

import java.util.ArrayList;
import java.util.List;

public class FakeResultaatDAO extends ResultaatDAO {

    public FakeResultaatDAO(){
        load();
    }

    @Override
    public ArrayList<Resultaat> getAll() {
        return objects;
    }

    @Override
    public boolean load() {
        objects = new ArrayList<>();
        objects.add(new Resultaat(-1, 12345, "Test test", null, 10, true, null));

        return true;
    }


    @Override
    public boolean save() {
        return false;
    }
}

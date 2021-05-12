package practicumopdracht.data;

import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;

import java.util.ArrayList;
import java.util.List;

/**
 * Detail DAO voor OOP2 practicumopdracht.
 *
 */
public abstract class ResultaatDAO implements DAO<Resultaat> {
    protected List<Resultaat> objects;
    private int id = 0;

    public ResultaatDAO() {
        load();
    }

    public List<Resultaat> getAllFor(Vak object) {
        ArrayList<Resultaat> resultaatList = new ArrayList<>();
        for (Resultaat resultaat : objects) {
            if (object.getId() == resultaat.getMasterId()) {
                resultaatList.add(resultaat);
            }
        }
        return resultaatList;
    }

    @Override
    public List<Resultaat> getAll() {
        return objects;
    }

    @Override
    public Resultaat get(int id) {
        for (Resultaat resultaat : objects) {
            if (resultaat.getId() == id) {
                return resultaat;
            }
        }
        return null;
    }

    @Override
    public void addOrUpdate(Resultaat object) {
        if (object.getId() > id) {
            int index = objects.indexOf(get(object.getId()));
            objects.remove(index);
            objects.add(index, object);
        } else {
            object.setId(getUniqueId());
            objects.add(object);
        }
    }

    @Override
    public void remove(Resultaat object) {
        objects.remove(objects.indexOf(object));
    }

    @Override
    public abstract boolean load();

    @Override
    public abstract boolean save();

    private int getUniqueId() {
        int maxId = 0;
        for (Resultaat resultaat : objects) {
            if (resultaat.getId() > maxId) {
                maxId = resultaat.getId();
            }
        }
        maxId++;
        return maxId;
    }
}

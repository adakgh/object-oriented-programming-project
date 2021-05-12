package practicumopdracht.data;

import practicumopdracht.models.Vak;

import java.util.ArrayList;
import java.util.List;

/**
 * Master DAO voor OOP2 practicumopdracht.
 *
 */
public abstract class VakDAO implements DAO<Vak> {
    protected List<Vak> objects;
    private int id = 0;

    public VakDAO() {
        load();
    }

    @Override
    public List<Vak> getAll() {
        return objects;
    }

    @Override
    public Vak get(int id) {
        for (Vak vak : objects) {
            if (vak.getId() == id) {
                return vak;
            }
        }
        return null;
    }

    @Override
    public void addOrUpdate(Vak object) {
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
    public void remove(Vak object) {
        objects.remove(objects.indexOf(object));
    }

    @Override
    public abstract boolean load();

    @Override
    public abstract boolean save();

    private int getUniqueId() {
        int maxId = 0;

        for (Vak vak : objects) {
            if (vak.getId() > maxId) {
                maxId = vak.getId();
            }
        }
        maxId++;
        return maxId;
    }
}

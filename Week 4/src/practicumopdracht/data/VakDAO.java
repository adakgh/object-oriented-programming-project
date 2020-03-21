package practicumopdracht.data;

import practicumopdracht.models.Vak;

import java.util.ArrayList;
/**
 * Master DAO voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public abstract class VakDAO implements DAO<Vak> {
    protected ArrayList<Vak> objects;
    private int id = 1;

    public VakDAO(){
        objects = new ArrayList<>();
        load();
    }

    @Override
    public ArrayList<Vak> getAll() {
        return objects;
    }

    @Override
    public Vak get(int id) {
        for(Vak vak : objects)
        {
            if (vak.getId() == id)
            {
                return vak;
            }
        }
        return null;
    }

    @Override
    public void addOrUpdate(Vak object) {
        if (object.getId() < 1)
        {
            object.setId(getUniqueId());
            objects.add(object);
            return;
        }
        int indexOfObject = objects.indexOf(get(object.getId()));
        objects.remove(indexOfObject);
        objects.add(indexOfObject, object);
    }

    @Override
    public void remove(Vak object) {
        objects.remove(objects.indexOf(object));
    }

    @Override
    public abstract boolean load();

    @Override
    public abstract boolean save();

    private int getUniqueId(){
        int highestId = -1;
        for (Vak vak : objects)
        {
            if (vak.getId() > highestId)
            {
                highestId = vak.getId();
            }
        }
        if (highestId == -1)
        {
            return id;
        }
        return highestId + 1;
    }
}

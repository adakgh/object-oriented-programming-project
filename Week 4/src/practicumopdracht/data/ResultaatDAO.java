package practicumopdracht.data;

import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;

import java.util.ArrayList;
import java.util.List;

public abstract class ResultaatDAO implements DAO<Resultaat> {

    protected ArrayList<Resultaat> objects;

    public ResultaatDAO(){
        objects  = new ArrayList<>();
        load();
    }

    public ArrayList<Resultaat> getAllFor(Vak object){
        ArrayList<Resultaat> resultaatList = new ArrayList<>();
        for (Resultaat resultaat : objects)
        {
            if (resultaat.getHoortBijVak() == object)
            {
                resultaatList.add(resultaat);
            }
        }
        return resultaatList;
    }



    @Override
    public Resultaat get(int id) {
        for(Resultaat resultaat : objects){
            if (resultaat.getId() == id){
                return resultaat;
            }
        }
        return null;
    }

    @Override
    public void addOrUpdate(Resultaat object) {
        if (objects.indexOf(object) > -1){
            objects.set(objects.indexOf(object), object);
        } else {
            objects.add(getUniqueId(), object);
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

    private int getUniqueId(){
        return 0;
    }
}

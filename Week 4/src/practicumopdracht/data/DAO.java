package practicumopdracht.data;

import java.util.ArrayList;
import java.util.List;

public interface DAO<T> {

    ArrayList<T> getAll();

    T get(int id);

    void addOrUpdate(T object);
    void remove(T object);

    boolean save();
    boolean load();
}


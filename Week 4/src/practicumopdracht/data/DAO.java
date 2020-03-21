package practicumopdracht.data;

import java.util.ArrayList;
/**
 * DAO interface voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public interface DAO<T> {

    ArrayList<T> getAll();

    T get(int id);

    void addOrUpdate(T object);
    void remove(T object);

    boolean save();
    boolean load();
}


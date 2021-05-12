package practicumopdracht.data;

import java.util.List;

/**
 * DAO interface voor OOP2 practicumopdracht.
 *
 */
public interface DAO<T> {

    List<T> getAll();

    T get(int id);

    void addOrUpdate(T object);
    void remove(T object);

    boolean save();
    boolean load();
}


package database;

import java.util.List;
import java.util.Optional;

/**
 * DAO API.
 *
 * Generic Interface which is used in every Dao Class
 * */
public interface DBInterface<T> {

    Optional<T> getById(int id);
    List<T> getAll();
    void add(T t);
    void edit(T t, String[] params);
    void delete(T t);
}

package mo.ed.aad.pagingroomimpl.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM Persons")
    LiveData<List<Person>> getAll();

    @Query("SELECT * FROM persons")
    DataSource.Factory<Integer, Person> getAllPaged();

    @Insert
    long insertAll(Person personList);

    @Delete
    abstract int deleteAllMembers(Person person);

}

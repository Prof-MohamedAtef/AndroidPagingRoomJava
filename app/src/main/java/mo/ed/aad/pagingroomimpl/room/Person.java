package mo.ed.aad.pagingroomimpl.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Persons")
public class Person implements Serializable {

    public Person() {
    }


    public Person(@NonNull String personName) {
        PersonName = personName;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "PersonID")
    public int PersonID;

    @NonNull
    @ColumnInfo(name = "PersonName")
    public String PersonName;

    @NonNull
    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(@NonNull int personID) {
        PersonID = personID;
    }

    @NonNull
    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(@NonNull String personName) {
        PersonName = personName;
    }
}

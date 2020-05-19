package mo.ed.aad.pagingroomimpl.room;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class PersonsRepository {
    private final AppDatabase appDatabase;
    PersonDao personDao;

    public PersonsRepository(Context context) {
        appDatabase=AppDatabaseInitializer.getAppDatabase(context);
        this.personDao = appDatabase.personDao();
    }

    public LiveData<PagedList<Person>> getAllPagedPersons(PagedList.Config config){
        DataSource.Factory<Integer, Person> factory=this.personDao.getAllPaged();
        return new LivePagedListBuilder<>(factory,config)
                .build();
    }

}

package mo.ed.aad.pagingroomimpl.room;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class PersonsViewModel  extends ViewModel {
    private final PersonsRepository personsRepository;
    private LiveData<PagedList<Person>> mPersonsList;

    private final static PagedList.Config  config=(new PagedList.Config.Builder())
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build();

    public PersonsViewModel(PersonsRepository personsRepository) {
        this.personsRepository=personsRepository;
    }

    public LiveData<PagedList<Person>> getPersonsList(){
        if (mPersonsList==null){
            mPersonsList=personsRepository.getAllPagedPersons(config);
        }
        return mPersonsList;
    }
}
package mo.ed.aad.pagingroomimpl.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Map;

import javax.inject.Provider;

public class PersonsViewModelFactory implements ViewModelProvider.Factory {
    private final Context mContext;


//    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels;

//    @Inject
//    public PersonsViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators, Context context){
//        this.viewModels=creators;
//    }


    public PersonsViewModelFactory(Context context) {
        this.mContext=context;
    }

    PersonsRepository personsRepository;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(PersonsViewModel.class)) {
            personsRepository=new PersonsRepository(this.mContext);
            return (T) new PersonsViewModel(personsRepository);
        }else {
             new IllegalArgumentException("unknown model class " + modelClass);
            return null;
        }

        //        Provider<? extends ViewModel> viewModel = viewModels.get(modelClass);
//        if (viewModel==null){
//            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : viewModels.entrySet()) {
//                if (modelClass.isAssignableFrom(entry.getKey())) {
//                    viewModel = entry.getValue();
//                    break;
//                }
//            }
//        }
//        if (viewModel==null){
//            throw new IllegalArgumentException("unknown model class " + modelClass);
//        }
//        try {
//            return (T) viewModel.get();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}

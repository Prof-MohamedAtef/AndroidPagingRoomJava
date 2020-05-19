package mo.ed.aad.pagingroomimpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;

import javax.inject.Inject;

import mo.ed.aad.pagingroomimpl.room.AppDatabase;
import mo.ed.aad.pagingroomimpl.room.AppDatabaseInitializer;
import mo.ed.aad.pagingroomimpl.room.Person;
import mo.ed.aad.pagingroomimpl.room.PersonsViewModel;
import mo.ed.aad.pagingroomimpl.room.PersonsViewModelFactory;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Inject
    PersonsViewModelFactory personsViewModelFactory;
    private PersonsViewModel personsViewModel;


    private PersonsPagingListAdapter adapter;
    private ArrayList<Person> personsList;
    private AppDatabase appDatabase;
    private Person person;
    private long x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabaseInitializer.getAppDatabase(getApplication());

        personsList=new ArrayList<>();

        person=new Person("Mohamed");
        personsList.add(person);
        person=new Person("Ahamed");
        personsList.add(person);
        person=new Person("Esmail");
        personsList.add(person);
        person=new Person("Kamel");
        personsList.add(person);
        person=new Person("Noha");
        personsList.add(person);
        person=new Person("Nahed");
        personsList.add(person);
        person=new Person("Gamal");
        personsList.add(person);


        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                for(Person Person : personsList){
                    x=appDatabase.personDao().insertAll(Person);
                    if (x>0){
                        return true;
                    }else {
                        return false;
                    }
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean==true){
                    launchSelect();
                }else {

                }
            }
        }.execute();
    }

    private void launchSelect() {
        personsViewModelFactory=new PersonsViewModelFactory(getApplicationContext());
        personsViewModel = new ViewModelProvider(this, personsViewModelFactory).get(PersonsViewModel.class);
        recyclerView=(RecyclerView)findViewById(R.id.persons_list);

        personsViewModel.getPersonsList().observe(this, new Observer<PagedList<Person>>() {
            @Override
            public void onChanged(PagedList<Person> people) {
                adapter=new PersonsPagingListAdapter(getApplicationContext());
                adapter.submitList(people);
                adapter.notifyDataSetChanged();
//                RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//                recyclerView.setLayoutManager(mLayoutManager);
                if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                } else {


                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));


                }
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
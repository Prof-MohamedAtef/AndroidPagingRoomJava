package mo.ed.aad.pagingroomimpl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import mo.ed.aad.pagingroomimpl.room.Person;

public class PersonsPagingListAdapter extends PagedListAdapter<Person, PersonsPagingListAdapter.PersonViewHolder> {

    private static final String TAG = PersonsPagingListAdapter.class.getSimpleName();

    private final Context mContext;



    public PersonsPagingListAdapter(Context context){
        super(DIFF_CALLBACK);
        this.mContext=context;
    }


    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,
                parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person=getItem(position);

        if (person!=null){
            holder.tvName.setText(person.getPersonName());
        }else {

        }

    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName=(TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    private static DiffUtil.ItemCallback<Person> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Person>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Person oldConcert, Person newConcert) {
                    return oldConcert.getPersonID() == newConcert.getPersonID();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Person oldConcert,
                                                  Person newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
}
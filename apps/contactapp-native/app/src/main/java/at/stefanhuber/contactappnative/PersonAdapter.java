package at.stefanhuber.contactappnative;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView firstLine;
        TextView secondLine;

        public PersonViewHolder(LinearLayout item) {
            super(item);
            firstLine = item.findViewById(R.id.name);
            secondLine = item.findViewById(R.id.data);
        }
    }

    protected PersonRepository personRepository;
    protected Cursor cursor;

    public PersonAdapter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void loadData() {
        cursor = personRepository.queryAll();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LinearLayout item = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new PersonViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = PersonRepository.getPersonAtPosition(cursor, position);
        holder.firstLine.setText(person.getName());
        holder.secondLine.setText(person.getPhone());
    }

    @Override
    public int getItemCount() {
        return this.cursor != null ? this.cursor.getCount() : 0;
    }

    public void removeItem(int position) {
        Person person = PersonRepository.getPersonAtPosition(cursor, position);
        if (personRepository.removePerson(person.getId())) {
            loadData();
        }
    }

    public void removeAllItems() {
        if (personRepository.removeAllPersons()) {
            loadData();
        }
    }

    public void addDemoData() {
        personRepository.addDemoData(100);
        loadData();
    }

}

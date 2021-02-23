package at.stefanhuber.contactappnative;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.Random;

public class PersonRepository {

    protected static PersonRepository instance;
    protected DatabaseHelper databaseHelper;

    public static PersonRepository getInstance(Context context) {
        if (instance == null) {
            instance = new PersonRepository(new DatabaseHelper(context));
        }

        return instance;
    }

    protected PersonRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static Person getPersonAtPosition(Cursor cursor, int position) {
        Person person = new Person();

        if (cursor != null && cursor.moveToPosition(position)) {
            person.setId(cursor.getInt(cursor.getColumnIndex("id")));
            person.setFirstname(cursor.getString(cursor.getColumnIndex("firstname")));
            person.setLastname(cursor.getString(cursor.getColumnIndex("lastname")));
            person.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
        }

        return person;
    }


    public static ContentValues getContentValues(Person person) {
        ContentValues values = new ContentValues();
        values.put("firstname", person.getFirstname());
        values.put("lastname", person.getLastname()        );
        values.put("phone", person.getPhone());
        return values;
    }

    public int count() {
        return this.databaseHelper
                .getReadableDatabase()
                .query("person", null, null, null, null, null, null)
                .getCount();

    }

    public Cursor queryAll() {
        return this.databaseHelper
            .getReadableDatabase()
            .query("person", null, null, null, null, null, "id ASC");
    }

    public Person addPerson(Person person) {
        ContentValues values = getContentValues(person);

        int id = (int) this.databaseHelper
            .getWritableDatabase()
            .insert("person", null, values);

        person.setId(id);
        return person;
    }

    public boolean removePerson(int id) {
        return this.databaseHelper
            .getWritableDatabase()
            .delete("person", "id = ?", new String[] {"" + id}) > 0;
    }

    public boolean removeAllPersons() {
        return this.databaseHelper
            .getWritableDatabase()
            .delete("person", null, null) > 0;
    }

    public void addDemoData(int count) {
        Random rand = new Random();

        for (int i = 1; i <= count; i++) {
            int r = Math.round(rand.nextFloat() * 100000);
            Person person = new Person();
            person.setFirstname("Firstname");
            person.setLastname("Lastname " + r);
            person.setPhone("0043 1234567 " + r);
            addPerson(person);
        }
    }

}

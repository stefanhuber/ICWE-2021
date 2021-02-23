package at.stefanhuber.contactappnative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    protected Person person;
    protected EditText firstname;
    protected EditText lastname;
    protected EditText phone;
    protected PersonRepository personRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        personRepository = PersonRepository.getInstance(getApplicationContext());
        person = new Person();

        firstname = findViewById(R.id.editText);
        lastname = findViewById(R.id.editText2);
        phone = findViewById(R.id.editText3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                person.setFirstname(firstname.getText().toString());
                person.setLastname(lastname.getText().toString());
                person.setPhone(phone.getText().toString());
                personRepository.addPerson(person);

                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

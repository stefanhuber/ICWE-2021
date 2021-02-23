package at.stefanhuber.contactappnative;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected ActionBarDrawerToggle toggle;
    protected PersonRepository personRepository;
    protected PersonAdapter personAdapter;
    protected DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.app_name,R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        personRepository = PersonRepository.getInstance(getApplicationContext());
        personAdapter = new PersonAdapter(personRepository);

        prepareRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        personAdapter.loadData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_form:
                startActivity(new Intent(this, FormActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void prepareRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.virtual_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(personAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        ItemTouchCallback callback = new ItemTouchCallback();
        callback.setItemRemovedListener(new ItemTouchCallback.ItemRemovedListener() {
            @Override
            public void onItemRemoved(int position) {
                personAdapter.removeItem(position);
                Snackbar snack = Snackbar.make(drawer, "Contact item was removed.", Snackbar.LENGTH_SHORT);
                snack.setDuration(1800);
                snack.show();
            }
        });
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_form_2:
                startActivity(new Intent(this, FormActivity.class));
                return true;
            case R.id.nav_form_3:
                personAdapter.addDemoData();
                drawer.closeDrawers();
                return true;
            case R.id.nav_form_4:
                personAdapter.removeAllItems();
                drawer.closeDrawers();
                return true;
        }
        return false;
    }
}

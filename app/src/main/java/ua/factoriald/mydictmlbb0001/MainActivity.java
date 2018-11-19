package ua.factoriald.mydictmlbb0001;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //тулбар
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //кнопка Герои в главном меню
        Button heroesButton = (Button) findViewById(R.id.all_hero_button);
        heroesButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, AllHeroesActivity.class);
            startActivity(intent);
        });

        //кнопка wiki в главном меню
        Button wikiButton = (Button)findViewById(R.id.wiki_button);
        wikiButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, BrowserActivity.class);
            startActivity(intent);
        });

        //кнопка about в главном меню
        Button aboutButton = (Button)findViewById(R.id.about_button);
        aboutButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { //походу тут происходит обработка нажатия итемов в боковой панели
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.slidepanel_list_item_heroes) {

            Intent intent = new Intent(this, AllHeroesActivity.class);
            startActivity(intent);

        } else if (id == R.id.slidepanel_list_item_gameitems) {

        } else if (id == R.id.slidepanel_list_item_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.slidepanel_list_item_about) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

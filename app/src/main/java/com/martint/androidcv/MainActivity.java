package com.martint.androidcv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up navigation drawer
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Sets up view pager to allow the swiping between the fragments
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageAdapterFragment(getSupportFragmentManager()));
    }

    /**
     * Handles navigation drawer menu item selection
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.nav_item_home:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.nav_item_about_me:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.nav_item_education:
                viewPager.setCurrentItem(2, true);
                break;
            case R.id.nav_item_qualification:
                viewPager.setCurrentItem(3, true);
                break;
            case R.id.nav_item_computing_project:
                viewPager.setCurrentItem(4, true);
                break;
            case R.id.nav_item_employment:
                viewPager.setCurrentItem(5, true);
                break;
            case R.id.nav_item_interests:
                viewPager.setCurrentItem(6, true);
                break;
        }

        // Closes navigation drawer after selection
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}

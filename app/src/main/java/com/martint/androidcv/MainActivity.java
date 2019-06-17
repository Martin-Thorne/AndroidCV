package com.martint.androidcv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, PopupMenu.OnMenuItemClickListener {

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
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Sets up listener for navigation drawer user selection
        navigationView.setNavigationItemSelectedListener(this);

        // Sets up view pager to allow the swiping between the fragments
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageAdapterFragment(getSupportFragmentManager()));

        // Sets up listener for changes in fragment selection
        viewPager.addOnPageChangeListener(this);
    }

    /**
     * Creates actionbar button for settings menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    /**
     * Handles navigation drawer menu item selection
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
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

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    /**
     * Changes toolBar title to reflect content of page
     *
     * @param i
     */
    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                getSupportActionBar().setTitle(R.string.app_name);
                break;
            case 1:
                getSupportActionBar().setTitle(R.string.about_me_title);
                break;
            case 2:
                getSupportActionBar().setTitle(R.string.education_title);
                break;
            case 3:
                getSupportActionBar().setTitle(R.string.qualification_title);
                break;
            case 4:
                getSupportActionBar().setTitle(R.string.computing_project_title);
                break;
            case 5:
                getSupportActionBar().setTitle(R.string.employment_title);
                break;
            case 6:
                getSupportActionBar().setTitle(R.string.interests_title);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * Handles settings icon selected in toolbar. Opens setting popup menu
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu = item.getItemId();
        if (menu == R.id.action_settings) {
            View view = this.findViewById(R.id.action_settings);
            // Opens popup menu and sets up listener for item selection
            PopupMenu popup = new PopupMenu(this, view);
            popup.setOnMenuItemClickListener(this);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.popup_menu, popup.getMenu());
            popup.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Handles setting popup menu selection
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        // Collection of all active fragments
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        // Holds the choice to increase/decrease text size
        int itemId = menuItem.getItemId();
        // Takes all active fragments and updates the text size
        for (int i = 0; i < allFragments.size(); i++) {
            Fragment fragment = allFragments.get(i);
            String name = fragment.getClass().getName();
            // i is used to ensure that only the first looped fragment sets the text size in Settings
            if (name.contains("HomeFragment")) {
                ((HomeFragment) fragment).setTextSize(itemId, i);
            } else if (name.contains("AboutMeFragment")) {
                ((AboutMeFragment) fragment).setTextSize(itemId, i);
            } else if (name.contains("EducationFragment")) {
                ((EducationFragment) fragment).setTextSize(itemId, i);
            } else if (name.contains("QualificationsFragment")) {
                ((QualificationsFragment) fragment).setTextSize(itemId, i);
            } else if (name.contains("ComputingProjectFragment")) {
                ((ComputingProjectFragment) fragment).setTextSize(itemId, i);
            } else if (name.contains("EmploymentFragment")) {
                ((EmploymentFragment) fragment).setTextSize(itemId, i);
            } else if (name.contains("InterestsFragment")) {
                ((InterestsFragment) fragment).setTextSize(itemId, i);
            }
        }
        return true;
    }

}

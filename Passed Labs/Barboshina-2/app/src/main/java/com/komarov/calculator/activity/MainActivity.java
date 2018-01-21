package com.komarov.calculator.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.komarov.calculator.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private DrawerLayout drawer;
    private SectionPagerAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPageAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPageAdapter.addFragment(new MainCalcFragment(), getResources().getString(R.string.title_activity_main_calc));
        mSectionsPageAdapter.addFragment(new EngineerCalcFragment(), getResources().getString(R.string.title_activity_engineer_calc));
        mViewPager.setAdapter(mSectionsPageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean res = false;
        int selectedId = item.getItemId();
        switch (selectedId) {
            case R.id.about: {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.menu_about_title)
                        .setIcon(R.mipmap.ic_action_about)
                        .setMessage(R.string.menu_about_message)
                        .setCancelable(false)
                        .setNegativeButton(R.string.button_ok,
                                (dialog, id) -> dialog.cancel());
                AlertDialog alert = builder.create();
                alert.show();
                break;
            }
            case R.id.exit: {
                res = true;
                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setTitle(R.string.menu_exit_title);
                ad.setMessage(R.string.menu_exit_message);
                ad.setPositiveButton(R.string.button_ok, (dialog, arg1) -> {
                    finish();
                    System.exit(0);
                });
                ad.setNegativeButton(R.string.button_cancel, (dialog, arg1) -> {
                    Toast.makeText(context, R.string.menu_exit_cancel_text, Toast.LENGTH_LONG).show();
                });
                ad.show();
                break;
            }
            default: {
                break;
            }
        }
        return res;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_calc:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.nav_history:
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                break;
            case R.id.nav_graphs:
                break;
            case R.id.nav_settings:
                startActivity(new Intent(getApplicationContext(), MyPreferencesActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}

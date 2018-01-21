package com.komarov.calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.komarov.calculator.R;

import java.util.Calendar;

public class HistoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatePicker datePicker;
    private boolean isDateDialogOpened;
    private int year, month, day;
    private String storedDate = "";
    private TextView dateTextView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dateTextView = (TextView) findViewById(R.id.dateView);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        storedDate = Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year);
        dateTextView.setText(storedDate);
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
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean res = false;
        int selectedId = item.getItemId();
        switch (selectedId) {
            case R.id.about: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                AlertDialog.Builder ad = new AlertDialog.Builder(this);
                ad.setTitle(R.string.menu_exit_title);
                ad.setMessage(R.string.menu_exit_message);
                ad.setPositiveButton(R.string.button_ok, (dialog, arg1) -> {
                    finish();
                    System.exit(0);
                });
                ad.setNegativeButton(R.string.button_cancel, (dialog, arg1) -> {
                    Toast.makeText(this, R.string.menu_exit_cancel_text, Toast.LENGTH_LONG).show();
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

    public void onChooseDate(View view) {
        isDateDialogOpened = true;
        openDateDialog(day, month, year);
    }

    public void openDateDialog(final int d, final int m, final int y) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.date_dialog, null);
        datePicker = promptView.findViewById(R.id.datePicker);
        datePicker.init(y, m, d, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton(R.string.button_ok, (dialog, id) -> {
                    int day1 = datePicker.getDayOfMonth();
                    int month1 = datePicker.getMonth() + 1;
                    int year1 = datePicker.getYear();
                    storedDate = day1 + "." + month1 + "." + year1;
                    dateTextView.setText(storedDate);
                    isDateDialogOpened = false;
                    dialog.cancel();
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (isDateDialogOpened && datePicker != null) {
            outState.putInt("day", datePicker.getDayOfMonth());
            outState.putInt("month", datePicker.getMonth());
            outState.putInt("year", datePicker.getYear());
        }

        outState.putBoolean("isDateDialogOpened", isDateDialogOpened);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            isDateDialogOpened = savedInstanceState.getBoolean("isDateDialogOpened", false);

            if (isDateDialogOpened) {
                int savedDay = savedInstanceState.getInt("day");
                int savedMonth = savedInstanceState.getInt("month");
                int savedYear = savedInstanceState.getInt("year");
                openDateDialog(savedDay, savedMonth, savedYear);
            }
        }
    }
}

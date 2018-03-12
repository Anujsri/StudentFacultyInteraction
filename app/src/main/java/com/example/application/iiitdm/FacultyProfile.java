package com.example.application.iiitdm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FacultyProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
TextView fname,fdepartment,fcabin_no,fmobile,femail;
    Button addProject,viewProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);
        if(!SharedPrefManager.getInstance(getApplicationContext()).facultyisLoggedIn())
        {
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));

        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fname=(TextView) findViewById(R.id.faculty_name);
        fdepartment=(TextView) findViewById(R.id.faculty_depart);
        fcabin_no=(TextView) findViewById(R.id.faculty_cabin);

        femail=(TextView) findViewById(R.id.faculty_email);
        addProject=(Button) findViewById(R.id.addProject);
        viewProject=(Button) findViewById(R.id.viewProject);

        fname.setText(SharedPrefManager.getInstance(this).getFacultyName());
        fcabin_no.setText(SharedPrefManager.getInstance(this).getFacultyCabin());
        femail.setText(SharedPrefManager.getInstance(this).getFacultyEmail());

        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProjectAdd.class));
            }
        });

        viewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RecyclerProjectList.class));
            }
        });
    }

    boolean twice;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (twice == true) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
            }
            Toast.makeText(this, "Press Back Again To Exit", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    twice = false;
                }
            }, 3000);
            twice = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.faculty_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.messages_icon) {
            // Handle the camera action
        } else if (id == R.id.edit_profile_icon) {
            startActivity(new Intent(this,EditFacultyProfile.class));
        } else if (id == R.id.website_icon) {
            startActivity(new Intent(this,CollegeWebsite.class));

        } else if (id == R.id.about_us_icon) {


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.faculty_logout) {
            SharedPrefManager.getInstance(getApplicationContext()).facultyLogout();
            finish();
            Toast.makeText(getApplicationContext(),"Log Out Successfull", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,Home.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.example.application.iiitdm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class StudPro extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private PopupWindow branchpopup;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    public TextView poptext;
    SQLiteHelper an;
    private SQLiteDatabase newDB;
    public static String EmailHolder;
   public Button projbtn,meetbtn,csebtn,ecebtn,mebtn,desbtn,nsbtn;
    public static int btnflag,branchflag; // if btnflag=1 than projbtn is pressed and if it is 2 than meetbtn is pressed
    public static String branchString;
    TextView anemail,anname,andepartment,anpassword,anroll;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_pro);
        if (!SharedPrefManager.getInstance(this).studisLoggedIn())
        {
            finish();
            startActivity(new Intent(this,Home.class));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Home.stringFlagIdentifier ="student";

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView img=(ImageView) findViewById(R.id.imageView);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.student);
        RoundedBitmapDrawable roundedBitmapdrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapdrawable.setCircular(true);
        img.setImageDrawable(roundedBitmapdrawable);
        projbtn=(Button) findViewById(R.id.projectbtn);
        meetbtn=(Button) findViewById(R.id.meetinbtn);

        anemail=(TextView) findViewById(R.id.txtemailid);
        anname=(TextView)findViewById(R.id.txtstudname);
        andepartment=(TextView)findViewById(R.id.txtbranch);
        anroll=(TextView)findViewById(R.id.txtrollno);
        //anpassword=(TextView)findViewById(R.id.txtpassword);

        anemail.setText(SharedPrefManager.getInstance(this).getStudEmail());
        anname.setText(SharedPrefManager.getInstance(this).getStudName());
        andepartment.setText(SharedPrefManager.getInstance(this).getStudDept());
        anroll.setText(SharedPrefManager.getInstance(this).getStudRollNo());
        relativeLayout=(RelativeLayout) findViewById(R.id.stud_pro);
        projbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnflag=1;
                layoutInflater=(LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container=(ViewGroup) layoutInflater.inflate(R.layout.branch_popup,null);
                    poptext=(TextView) container.findViewById(R.id.pop_text);
                    poptext.setText("Choose Field For The Project");
                branchpopup=new PopupWindow(container,700,550,true);
                branchpopup.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,10,200);
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        branchpopup.dismiss();
                        return true;
                    }
                });
                csebtn=(Button) container.findViewById(R.id.btn_cse);
                csebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=1;
                        branchString="cse";
                    }
                });
                mebtn=(Button) container.findViewById(R.id.btn_me);
                mebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=2;
                        branchString="me";
                    }
                });
                ecebtn=(Button) container.findViewById(R.id.btn_ece);
                ecebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=3;
                        branchString="ece";
                    }
                });
                desbtn=(Button) container.findViewById(R.id.btn_design);
                desbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=4;
                        branchString="design";
                    }
                });
                nsbtn=(Button) container.findViewById(R.id.btn_ns);
                nsbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=5;
                        branchString="ns";
                    }
                });

            }
        });
        meetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnflag=2;
                layoutInflater=(LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container=(ViewGroup) layoutInflater.inflate(R.layout.branch_popup,null);
                poptext=(TextView) container.findViewById(R.id.pop_text);
                poptext.setText("Choose Department Of Faculty To Meet");
                branchpopup=new PopupWindow(container,700,600,true);
                branchpopup.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,10,200);
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        branchpopup.dismiss();
                        return true;
                    }

                });
                csebtn=(Button) container.findViewById(R.id.btn_cse);
                csebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=1;
                        branchString="cse";
                    }
                });
                mebtn=(Button) container.findViewById(R.id.btn_me);
                mebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=2;
                        branchString="me";
                    }
                });
                ecebtn=(Button) container.findViewById(R.id.btn_ece);
                ecebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=3;
                        branchString="ece";
                    }
                });
                desbtn=(Button) container.findViewById(R.id.btn_design);
                desbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=4;
                        branchString="design";
                    }
                });
                nsbtn=(Button) container.findViewById(R.id.btn_ns);
                nsbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        branchbtnClk();
                        branchflag=5;
                        branchString="ns";
                    }
                });

            }
        });

       /* anemail=(TextView) findViewById(R.id.txtemailid);
        anname=(TextView)findViewById(R.id.txtstudname);
         andepartment=(TextView)findViewById(R.id.txtbranch);
             anroll=(TextView)findViewById(R.id.txtrollno);
                anpassword=(TextView)findViewById(R.id.txtpassword);*/

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(Home.UserEmail);
       // openAndQueryDatabase( );


    }



    public void openAndQueryDatabase( ) {

        try {
            String[] columns = {
                    an.Table_Column_1_Name,
                    an.Table_Column_2_Email,
                    an.Table_Column_3_Password,
                    an. Table_Column_4_Department,

                    an.Table_Column_6_Roll,

            };
            SQLiteHelper dbHelper = new SQLiteHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor cursor = newDB.query(SQLiteHelper.TABLE_NAME, //Table to query
                    columns,    //columns to return
                    an.Table_Column_2_Email+"=?",        //columns for the WHERE clause
                    new String[]{ EmailHolder} ,        //The values for the WHERE clause
                    null,       //group the rows
                    null,       //filter by row groups
                    null); //The sort order

            if (cursor != null ) {
                if  (cursor.moveToFirst()) {
                    do {
                        String Name = cursor.getString(cursor.getColumnIndex("name"));
                        String Email = cursor.getString(cursor.getColumnIndex("email"));
                        String Department = cursor.getString(cursor.getColumnIndex("department"));
                        String Password = cursor.getString(cursor.getColumnIndex("password"));
                        String Roll = cursor.getString(cursor.getColumnIndex("roll"));

                          anname.setText(Name);
                        anemail.setText(Email);
                        andepartment.setText(Department);
                        anroll.setText(Roll);
                        anpassword.setText(Password);
                       // results.add("Name: " + Name + ",Email: " + Email + ",Department: " + Department + ",Mobile: " + Mobile + ",Roll No.: " + Roll  );
                    }while (cursor.moveToNext());

                }

            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
           /* if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);*/
           // newDB.close();
        }

    }







   /* public void csebtnClk(View x) {
        startActivity(new Intent(this,FacultyList.class));
    }*/


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
   /* @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stud, menu);
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
            startActivity(new Intent(this,EditStudentProfile.class));
        } else if (id == R.id.website_icon) {
            startActivity(new Intent(this,CollegeWebsite.class));
        } else if (id == R.id.about_us_icon) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.logout_btn) {

           // Home.Email.setText("");
           // Home.Password.setText("");
            //Finishing current DashBoard activity on button click.
            //finish();
            SharedPrefManager.getInstance(this).logout();
            finish();
            Toast.makeText(StudPro.this,"Log Out Successfull", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,Home.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
public void branchbtnClk()
{
    startActivity(new Intent(getApplicationContext(),RecFacultyList.class));
}



}

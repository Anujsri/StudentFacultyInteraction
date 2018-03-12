package com.example.application.iiitdm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpChooser extends Activity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private int[] layouts;
    private Button studsign, facsign,studreg,facreg;



    EditText Email, Password, Name,Department,Roll,Conpassword ;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder,DepartmentHolder ,RollHolder,ConpasswordHolder;
    Boolean EditTextEmptyHolder,EditTextEmptyHolderf;
    SQLiteDatabase sqLiteDatabaseObj,sqLiteDatabaseObjf;
    String SQLiteDataBaseQueryHolder,SQLiteDataBaseQueryHolderf ;
    SQLiteHelper sqLiteHelper,sqLiteHelperf;
    Cursor cursor,cursorf;
    String F_Result = "Not_Found";
    String F_Resultf = "Not_Found";
    private Context context;
    EditText femail,fpassword,fconpassword;
    String FpassHolder,FemailHolder,Fconpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_chooser);
        /*if (SharedPrefManager.getInstance(this).studisLoggedIn())
        {
            finish();
            startActivity(new Intent(this,StudPro.class));
            return;
        }*/
        viewPager = (ViewPager) findViewById(R.id.pager);
        studsign = (Button) findViewById(R.id.ssignbtn);
       facsign = (Button) findViewById(R.id.fsignbtn);
        studreg = (Button) findViewById(R.id.studreg);
        facreg = (Button) findViewById(R.id.facreg);
        layouts = new int[]{
                R.layout.student_signup,
                R.layout.choose_sign_up,
                R.layout.faculty_signup};
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);




    }

    public  void onstudClk(View v)
    {
        viewPager.setCurrentItem(0);
        Name = (EditText)findViewById(R.id.editnamean);
        Email = (EditText)findViewById(R.id.textemail);
        Password = (EditText)findViewById(R.id.textpassword);
        Conpassword=(EditText)findViewById(R.id.textpasswordretype);
        Department = (EditText)findViewById(R.id.textdepartment);

        Roll = (EditText)findViewById(R.id.textroll);


      //  sqLiteHelper = new SQLiteHelper(this);


    }
    public  void onfacClk(View v)
    {
        viewPager.setCurrentItem(2);
        femail = (EditText)findViewById(R.id.factextemail);
                fpassword = (EditText)findViewById(R.id.factextpassword);
        fconpassword = (EditText)findViewById(R.id.factextpasswordretype);
        sqLiteHelperf = new SQLiteHelper(this);

    }
    public  void studregClk(View v)
    {
        /*SQLiteDataBaseBuild();

        // Creating SQLite table if dose n't exists.
        SQLiteTableBuild();

        // Checking EditText is empty or Not.
        CheckEditTextStatus();

        // Method to check Email is already exists or not.
        CheckingEmailAlreadyExistsOrNot();

        // Empty EditText After done inserting process.
        EmptyEditTextAfterDataInsert();*/
        final   String name=Name.getText().toString().trim();
        final  String email= Email.getText().toString().trim();
        final String roll_no=Roll.getText().toString().trim();
        final String password=Password.getText().toString().trim();
        final String department=Department.getText().toString().trim();
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {

                           // response = response.replace("<br","");


                            JSONObject jsonObject = new JSONObject(response);
                           boolean b=jsonObject.getString("message").toString().equals("success");
                            if(b)
                            {
                                startActivity(new Intent(SignUpChooser.this,RegisterSplash.class));
                            }
                            else
                            {
                               Toast.makeText(getApplicationContext(),jsonObject.getString("message").toString(),Toast.LENGTH_LONG).show();
                            }
                           // Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                        }catch (JSONException e)
                        {
                            e.printStackTrace();

                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }}
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =new HashMap<>();
                params.put("name",name);
                params.put("roll_no",roll_no);
                params.put("email",email);
                params.put("password",password);

                params.put("department",department);
                return  params;
            }
        };
       // RequestQueue requestQueue= Volley.newRequestQueue(this);
       // requestQueue.add(stringRequest);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
    public  void facregClk(View v)

    {

       // SQLiteDataBaseBuildf();

        // Creating SQLite table if dose n't exists.
       // SQLiteTableBuildf();

        // Checking EditText is empty or Not.
       // CheckEditTextStatusf();

        // Method to check Email is already exists or not.
       // CheckingEmailAlreadyExistsOrNotf();

        // Empty EditText After done inserting process.
        //EmptyEditTextAfterDataInsertf();

        //startActivity(new Intent(this, RegisterSplash.class));
        //final   String name=Name.getText().toString().trim();
        final  String email= femail.getText().toString().trim();
       // final String roll_no=Roll.getText().toString().trim();
        final String password=fpassword.getText().toString().trim();
       // final String department=Department.getText().toString().trim();
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Registering Faculty");
        progressDialog.show();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Constants.URL_REGISTER_FACULTY,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {

                            // response = response.replace("<br","");


                            JSONObject jsonObject = new JSONObject(response);
                            boolean b=jsonObject.getString("message").toString().equals("success");
                            if(b)
                            {
                                startActivity(new Intent(SignUpChooser.this,RegisterSplash.class));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message").toString(),Toast.LENGTH_LONG).show();
                            }
                            // Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                        }catch (JSONException e)
                        {
                            e.printStackTrace();

                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }}
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =new HashMap<>();

                params.put("email",email);
                params.put("password",password);


                return  params;
            }
        };
        // RequestQueue requestQueue= Volley.newRequestQueue(this);
        // requestQueue.add(stringRequest);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    private int getItem(int i) {

        return viewPager.getCurrentItem() + i;
    }



    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;


        public ViewPagerAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }





    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }



    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "
                + SQLiteHelper.TABLE_NAME + "(" +SQLiteHelper.Table_Column_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " +SQLiteHelper.Table_Column_2_Email + " VARCHAR, "
                + SQLiteHelper.Table_Column_3_Password + " VARCHAR, "+ SQLiteHelper.Table_Column_4_Department +" VARCHAR,"
                + SQLiteHelper.Table_Column_6_Roll +" TEXT);");

    }




    public void InsertDataIntoSQLiteDatabase(){

        // If editText is not empty then this block will executed.
        if(EditTextEmptyHolder == true)
        {
            if(isInputEditTextMatches(Password,Conpassword)==true){
                // SQLite query to insert data into table.
                SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+
                        " (name,email,password,department,roll) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PasswordHolder+"','"+DepartmentHolder+"','"+RollHolder+"');";

                // Executing query.
                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                // Closing SQLite database object.
                sqLiteDatabaseObj.close();
                startActivity(new Intent(this, RegisterSplash.class));
                // Printing toast message after done inserting.
                //Toast.makeText(SignUpChooser.this,"User Registered Successfully", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(SignUpChooser.this, "Password does not match", Toast.LENGTH_SHORT).show();
            }

        }
        // This block will execute if any of the registration EditText is empty.
        else {

            // Printing toast message if any of EditText is empty.
            Toast.makeText(SignUpChooser.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }





    public void EmptyEditTextAfterDataInsert(){

        Name.getText().clear();

        Email.getText().clear();

        Password.getText().clear();
        Conpassword.getText().clear();
        Department.getText().clear();

        Roll.getText().clear();

    }





    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = Name.getText().toString() ;
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        ConpasswordHolder=Conpassword.getText().toString();
        DepartmentHolder = Department.getText().toString() ;

        RollHolder = Roll.getText().toString() ;


        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder) ||  TextUtils.isEmpty(DepartmentHolder)  ||  TextUtils.isEmpty(RollHolder) || TextUtils.isEmpty(ConpasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }






    public void CheckingEmailAlreadyExistsOrNot(){

        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null,
                " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";

                // Closing cursor.
                cursor.close();
            }
        }

        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();

    }





    public void CheckFinalResult(){

        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase("Email Found"))
        {

            // If email is exists then toast msg will display.
            Toast.makeText(SignUpChooser.this,"Email Already Exists",Toast.LENGTH_LONG).show();

        }
        else {

            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found" ;

    }




    public boolean isInputEditTextMatches(EditText textInputEditText1, EditText textInputEditText2 ) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            return false;
        }
        else{
            return true;
        }
    }




    public void SQLiteDataBaseBuildf(){

        sqLiteDatabaseObjf = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }



    public void SQLiteTableBuildf() {

        sqLiteDatabaseObjf.execSQL("CREATE TABLE IF NOT EXISTS "
                + SQLiteHelper.FACULTY_TABLE + "(" +SQLiteHelper.Table_Column_FID + " INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + SQLiteHelper.Table_Column_2_FEmail + " VARCHAR, " +SQLiteHelper.Table_Column_3_FPassword + " VARCHAR);");

    }




    public void InsertDataIntoSQLiteDatabasef(){

        // If editText is not empty then this block will executed.
        if(EditTextEmptyHolderf == true)
        {
            if(isInputEditTextMatchesf(fpassword,fconpassword)==true){
                // SQLite query to insert data into table.
                SQLiteDataBaseQueryHolderf = "INSERT INTO "+SQLiteHelper.FACULTY_TABLE+
                        " (femail,fpassword) VALUES('"+FemailHolder+"', '"+FpassHolder+"');";

                // Executing query.
                sqLiteDatabaseObjf.execSQL(SQLiteDataBaseQueryHolderf);

                // Closing SQLite database object.
                sqLiteDatabaseObjf.close();
                startActivity(new Intent(this, RegisterSplash.class));
                // Printing toast message after done inserting.
                //Toast.makeText(SignUpChooser.this,"User Registered Successfully", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(SignUpChooser.this, "Password does not match", Toast.LENGTH_SHORT).show();
            }

        }
        // This block will execute if any of the registration EditText is empty.
        else {

            // Printing toast message if any of EditText is empty.
            Toast.makeText(SignUpChooser.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }





    public void EmptyEditTextAfterDataInsertf(){

        femail.getText().clear();

        fpassword.getText().clear();

        fconpassword.getText().clear();


    }





    public void CheckEditTextStatusf(){

        // Getting value from All EditText and storing into String Variables.
        FemailHolder = femail.getText().toString() ;
        FpassHolder = fpassword.getText().toString();
        Fconpassword = fconpassword.getText().toString();
        if(TextUtils.isEmpty(FemailHolder) || TextUtils.isEmpty(FpassHolder) || TextUtils.isEmpty(Fconpassword)){

            EditTextEmptyHolderf = false ;

        }
        else {

            EditTextEmptyHolderf = true ;
        }
    }






    public void CheckingEmailAlreadyExistsOrNotf(){

        // Opening SQLite database write permission.
        sqLiteDatabaseObjf = sqLiteHelperf.getWritableDatabase();

        // Adding search email query to cursor.
        cursorf = sqLiteDatabaseObjf.query(SQLiteHelper.FACULTY_TABLE, null,
                " " + SQLiteHelper.Table_Column_2_FEmail + "=?", new String[]{FemailHolder}, null, null, null);

        while (cursorf.moveToNext()) {

            if (cursorf.isFirst()) {

                cursorf.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Resultf = "Email Found";

                // Closing cursor.
                cursorf.close();
            }
        }

        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResultf();

    }





    public void CheckFinalResultf(){

        // Checking whether email is already exists or not.
        if(F_Resultf.equalsIgnoreCase("Email Found"))
        {

            // If email is exists then toast msg will display.
            Toast.makeText(SignUpChooser.this,"Email Already Exists",Toast.LENGTH_LONG).show();

        }
        else {

            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabasef();

        }

        F_Resultf = "Not_Found" ;

    }




    public boolean isInputEditTextMatchesf(EditText textInputEditText1, EditText textInputEditText2 ) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            return false;
        }
        else{
            return true;
        }
    }





}
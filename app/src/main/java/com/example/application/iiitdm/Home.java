package com.example.application.iiitdm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
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

public class Home extends AppCompatActivity {


    Button LogInButton, RegisterButton ;
    public static EditText Email, Password ;
    public  static String EmailHolder;
    String   PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    public static final String UserEmail = "";
    public static String stringFlagIdentifier;
    //String email;

    Button loginbtn;
    Button signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        if (SharedPrefManager.getInstance(this).studisLoggedIn())
        {
            finish();
            startActivity(new Intent(this,StudPro.class));
            return;
        }

        if(SharedPrefManager.getInstance(this).facultyisLoggedIn())
        {
            finish();
            startActivity(new Intent(this,FacultyProfile.class));
            return;
        }
        loginbtn=(Button) findViewById(R.id.loginbtn);
         signupbtn=(Button) findViewById(R.id.signupbtn);

        Email = (EditText) findViewById(R.id.editEmaila);
        Password = (EditText) findViewById(R.id.editPassworda);
      //  sqLiteHelper = new SQLiteHelper(this);
        loginbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
               // CheckEditTextStatus();

                // Calling login method.
                //LoginFunction();
                final String email=Email.getText().toString().trim();
                final String pass=Password.getText().toString().trim();
                final ProgressDialog progressDialog=new ProgressDialog(Home.this);
                progressDialog.setTitle("Please Wait....");
                progressDialog.setMessage("Loging in");
                progressDialog.show();
                StringRequest stringRequest= new StringRequest(
                        Request.Method.POST,
                        Constants.URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    JSONObject object = new JSONObject(response);
                                    if(!object.getBoolean("error")){
                                        if(object.getString("type").equals("student")) {
                                            SharedPrefManager.getInstance(getApplicationContext()).studLogin(
                                                    object.getString("name"),
                                                    object.getString("email"),
                                                    object.getString("roll_no"),
                                                    object.getString("department"),
                                                    object.getString("skills")
                                            );
                                            Toast.makeText(getApplicationContext(), "User Login Successful", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(Home.this, StudPro.class));
                                            finish();
                                        }else if (object.getString("type").equals("faculty")){
                                            SharedPrefManager.getInstance(getApplicationContext()).facultyLogin(
                                                    object.getString("name"),
                                                    object.getString("email"),
                                                    object.getString("cabin_addr"),
                                                    object.getString("department"),
                                                    object.getString("designation")
                                            );
                                            Toast.makeText(getApplicationContext(),"Faculty Login Successful",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(),FacultyProfile.class));
                                        }
                                    }
                                    else{
                                       Toast.makeText(getApplicationContext(),object.getString("message"),Toast.LENGTH_LONG).show();
                                    }
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"No Internet Or Some Other Problem",Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params=new HashMap<>();
                        params.put("email",email);
                        params.put("pass",pass);
                        return params;
                    }
                };
                RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,SignUpChooser.class));
            }
        });
    }

    public void LoginFunction(){

        if(EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " "
                    + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));

                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult();

        }
        else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(Home.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }

    }

    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(Home.this,"Login Successfully",Toast.LENGTH_SHORT).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(Home.this, StudPro.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, EmailHolder);

            startActivity(intent);


        }
        else {

            Toast.makeText(Home.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }
boolean twice;
    @Override
    public void onBackPressed() {
        if (twice==true){
            Intent intent= new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        Toast.makeText(this,"Press Back Again To Exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                twice=false;
            }
        },3000);
        twice=true;
    }
}

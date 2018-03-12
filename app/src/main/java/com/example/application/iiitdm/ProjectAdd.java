package com.example.application.iiitdm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProjectAdd extends AppCompatActivity {
 private Button submitProject;
    private EditText submitProjectName,submitProjectDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);

        submitProject=(Button) findViewById(R.id.submitProject);
        submitProjectName=(EditText) findViewById(R.id.submitProjectName);
        submitProjectDescription=(EditText) findViewById(R.id.submitProjectDescription);



        submitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog= new ProgressDialog(v.getContext());
                final String project_name=submitProjectName.getText().toString().trim();
                final String fac_email=SharedPrefManager.getInstance(getApplicationContext()).getFacultyEmail().toString().trim();
                final String department = SharedPrefManager.getInstance(getApplicationContext()).getFacultyDepartment().toString().trim();
               final String project_description=submitProjectDescription.getText().toString().trim();
                progressDialog.setMessage("Adding Project");
                progressDialog.show();
                // holder.progressDialog.setMessage("Requesting Please Wait.....");
                //holder.progressDialog.show();

              StringRequest stringRequest=new StringRequest(
                        Request.Method.POST,
                        Constants.URL_PROJECT_ADD,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                 progressDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message").toString(),Toast.LENGTH_LONG).show();


                                } catch (Exception e){
                                    // holder.progressDialog.dismiss();
                                    //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                                    Toast.makeText(getApplicationContext(),"Response error",Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                //holder.progressDialog.dismiss();
                                //Toast.makeText(ProjectAdd.this,error.getMessage(),Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),"Volley Error", Toast.LENGTH_LONG).show();

                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params=new HashMap<>();
                        params.put("project_name", project_name);
                        params.put("fac_email", fac_email);
                        params.put("department", department);
                        params.put("project_description",project_description);
                        return params;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }
}

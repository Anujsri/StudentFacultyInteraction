package com.example.application.iiitdm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRequestList extends AppCompatActivity {
    private RecyclerView recyclerViewRequest;
    private  RecyclerView.Adapter adapter;
    private List<StudentListItem> listItems;
    private TextView project_title;
    public static int apprflag;

public static Button unconfirmed_request_btn,confirmed_request_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request_list);

        project_title=(TextView) findViewById(R.id.project_title);
        project_title.setText(ProjectAdapter.projectname);

        recyclerViewRequest= (RecyclerView) findViewById(R.id.recyclerViewRequest);
        recyclerViewRequest.setHasFixedSize(true);
        recyclerViewRequest.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));




        listItems= new ArrayList<>();

        unconfirmed_request_btn=(Button) findViewById(R.id.unconfirmed_request);
        confirmed_request_btn=(Button) findViewById(R.id.confirmed_request);
        confirmed_request_btn.setClickable(false);
        unconfirmed_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apprflag=0;
                confirmed_request_btn.setClickable(true);
                unconfirmed_request_btn.setClickable(false);
                listItems.clear();
                getStudentList();
            }
        });
        confirmed_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apprflag=1;
                unconfirmed_request_btn.setClickable(true);
                confirmed_request_btn.setClickable(false);
                listItems.clear();
                getStudentListCon();
            }
        });
    }
     public void getStudentList() {

         final  String fac_email=SharedPrefManager.getInstance(getApplicationContext()).getFacultyEmail().toString().trim();
         final String project_name=ProjectAdapter.projectname.toString().trim();

         final ProgressDialog progressDialog=new ProgressDialog(this);

         progressDialog.setMessage("Please Wait....");
         progressDialog.show();

         StringRequest stringRequest=new StringRequest(
                 Request.Method.POST,
                 Constants.URL_STUDENT_LIST,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         progressDialog.dismiss();
                         try {
                             JSONObject jsonObject = new JSONObject(response);
                             if(!jsonObject.getBoolean("error")){
                             JSONArray jsonArray= jsonObject.getJSONArray("student");
                             for(int i=0; i<jsonArray.length(); i++)
                             {

                                 JSONObject o=jsonArray.getJSONObject(i);

                          StudentListItem item=new StudentListItem(
                                         o.getString("name"),
                                         o.getString("email"),
                                         o.getString("roll_no"),
                                         o.getString("department"),
                                         o.getString("skills"),
                                         o.getString("project_name"));
                                     listItems.add(item);

                             }

                             }else{
                                 Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                             }

                             adapter=new StudentAdapter(listItems, getApplicationContext());

                             recyclerViewRequest.setAdapter(adapter);
                         } catch (Exception e){
                             Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                         }
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {


                     }
                 }){
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params=new HashMap<>();
                 params.put("fac_email", fac_email);
                 params.put("project_name",project_name);
                 return params;
             }
         };
         RequestQueue requestQueue= Volley.newRequestQueue(this);
         requestQueue.add(stringRequest);
     }


    public void getStudentListCon() {

        final  String fac_email=SharedPrefManager.getInstance(getApplicationContext()).getFacultyEmail().toString().trim();
        final String project_name=ProjectAdapter.projectname.toString().trim();

        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                Constants.URL_STUDENT_LIST_CON,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error")){
                                JSONArray jsonArray= jsonObject.getJSONArray("student");
                                for(int i=0; i<jsonArray.length(); i++)
                                {

                                    JSONObject o=jsonArray.getJSONObject(i);

                                    StudentListItem item=new StudentListItem(
                                            o.getString("name"),
                                            o.getString("email"),
                                            o.getString("roll_no"),
                                            o.getString("department"),
                                            o.getString("skills"),
                                            o.getString("project_name"));
                                    listItems.add(item);

                                }

                            }else{
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }

                            adapter=new StudentAdapter(listItems, getApplicationContext());

                            recyclerViewRequest.setAdapter(adapter);
                        } catch (Exception e){
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("fac_email", fac_email);
                params.put("project_name",project_name);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

     }

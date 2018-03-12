package com.example.application.iiitdm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import static com.example.application.iiitdm.StudPro.branchString;

public class RecFacultyList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private List<FacultyListItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rec_faculty_list);
       recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listItems= new ArrayList<>();
        loadFacultyData();



    }

    private void loadFacultyData() {
        final String department=branchString;

       /* switch (btnflag){
            case 1:{
                String department ="CSE";

            }
            case 2:{

                 String department ="CSE";
            }
            case 3:{
                String department ="CSE";
            }
            case 4:{
                String department ="CSE";
            }
            case 5:{
                temp ="CSE";
            }
        }*/
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Faculty Data Loading....");
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                Constants.URL_FACULTY_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("faculty");
                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                JSONObject o=jsonArray.getJSONObject(i);
                                FacultyListItem item=new FacultyListItem(
                                     o.getString("name"),
                                        o.getString("email"),
                                        o.getString("designation"),
                                        o.getString("department")
                                );
                                listItems.add(item);
                            }

                            adapter=new FacultyAdapter(listItems, getApplicationContext());

                            recyclerView.setAdapter(adapter);
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
                params.put("department", department);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}

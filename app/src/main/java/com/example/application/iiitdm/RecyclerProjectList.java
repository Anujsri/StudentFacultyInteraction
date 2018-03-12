package com.example.application.iiitdm;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RecyclerProjectList extends AppCompatActivity {
    private RecyclerView recyclerViewProject;
    private  RecyclerView.Adapter adapter;
    private List<ProjectListItem> listItems;
    private String depStr,femailStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_project_list);
        recyclerViewProject= (RecyclerView) findViewById(R.id.recyclerViewProject);
        recyclerViewProject.setHasFixedSize(true);
        recyclerViewProject.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listItems= new ArrayList<>();
        if(SharedPrefManager.getInstance(this).facultyisLoggedIn()){
            depStr=SharedPrefManager.getInstance(this).getFacultyDepartment().toString().trim();
            femailStr=SharedPrefManager.getInstance(this).getFacultyEmail().toString().trim();
        }else if(SharedPrefManager.getInstance(this).studisLoggedIn()){
            depStr=branchString;
            femailStr=FacultyAdapter.facemail;
        }
        loadProjectData();

    }

    private void loadProjectData() {
        final String department=depStr;
        final String fac_email=femailStr;
        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                Constants.URL_PROJECT_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("project");
                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                JSONObject o=jsonArray.getJSONObject(i);
                                ProjectListItem item=new ProjectListItem(
                                        o.getString("project_name"),
                                        o.getString("project_description")

                                );
                                listItems.add(item);
                            }

                            adapter=new ProjectAdapter(listItems, getApplicationContext());

                            recyclerViewProject.setAdapter(adapter);
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
                params.put("email", fac_email);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

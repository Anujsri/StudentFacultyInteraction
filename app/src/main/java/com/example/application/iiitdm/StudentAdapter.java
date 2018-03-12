package com.example.application.iiitdm;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import java.util.List;
import java.util.Map;

/**
 * Created by S-Tech Computer on 20/07/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<StudentListItem> listItems;
    private Context context;
public static String faculname,faculdesignation,faculdept,facemail;
    public StudentAdapter(List<StudentListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final StudentListItem listItem=listItems.get(position);
        holder.studListname.setText(listItem.getStudentListName());
        holder.studListRoll.setText(listItem.getStudentListRoll());
        holder.studListSkills.setText(listItem.getStudenttListSkills());
        holder.studListDepartment.setText(listItem.getStudentListDepartment());
        holder.studListEmail.setText(listItem.getStudentListEmail());
        if (StudentRequestList.apprflag==1){
            holder.confirmityButton.setText("unconfirm");
            holder.deleteRequestButton.setVisibility(View.INVISIBLE);
            holder.confirmityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final  String fac_email=SharedPrefManager.getInstance(context).getFacultyEmail().toString().trim();
                    final String project_name=ProjectAdapter.projectname.toString().trim();
                    final String roll_no=listItem.getStudentListRoll();
                    final ProgressDialog progressDialog=new ProgressDialog(v.getContext());

                    progressDialog.setMessage("Please Wait....");
                    progressDialog.show();

                    StringRequest stringRequest=new StringRequest(
                            Request.Method.POST,
                            Constants.URL_PROJECT_UNCONFIRM,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        Toast.makeText(context,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                        if (!jsonObject.getBoolean("error")){
                                            StudentRequestList.confirmed_request_btn.performClick();

                                        }

                                    } catch (Exception e){
                                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG);
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
                            params.put("project_name",project_name);
                            params.put("fac_email", fac_email);
                            params.put("roll_no",roll_no);
                            return params;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);  }
            });
        }else if (StudentRequestList.apprflag==0){
            holder.confirmityButton.setText("confirm");
            holder.deleteRequestButton.setVisibility(View.VISIBLE);
            holder.deleteRequestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final  String fac_email=SharedPrefManager.getInstance(context).getFacultyEmail().toString().trim();
                    final String project_name=ProjectAdapter.projectname.toString().trim();
                    final String roll_no=listItem.getStudentListRoll();
                    final ProgressDialog progressDialog=new ProgressDialog(v.getContext());

                    progressDialog.setMessage("Please Wait....");
                    progressDialog.show();

                    StringRequest stringRequest=new StringRequest(
                            Request.Method.POST,
                            Constants.URL_PROJECT_REQUEST_DELETE,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        Toast.makeText(context,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                        if (!jsonObject.getBoolean("error")){
                                            StudentRequestList.unconfirmed_request_btn.performClick();
                                        }

                                    } catch (Exception e){
                                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG);
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
                            params.put("project_name",project_name);
                            params.put("fac_email", fac_email);
                            params.put("roll_no",roll_no);
                            return params;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);

                }
            });
            holder.confirmityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final  String fac_email=SharedPrefManager.getInstance(context).getFacultyEmail().toString().trim();
                    final String project_name=ProjectAdapter.projectname.toString().trim();
                    final String roll_no=listItem.getStudentListRoll();
                    final ProgressDialog progressDialog=new ProgressDialog(v.getContext());

                    progressDialog.setMessage("Please Wait....");
                    progressDialog.show();

                    StringRequest stringRequest=new StringRequest(
                            Request.Method.POST,
                            Constants.URL_PROJECT_CONFIRM,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        Toast.makeText(context,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                        if (!jsonObject.getBoolean("error")){
                                            StudentRequestList.unconfirmed_request_btn.performClick();
                                        }

                                    } catch (Exception e){
                                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG);
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
                            params.put("project_name",project_name);
                            params.put("fac_email", fac_email);
                            params.put("roll_no",roll_no);
                            return params;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);   }
            });
        }
       holder.itemLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               /*faculname=listItem.getFacListname();
               facemail=listItem.getFacemail();
               faculdesignation=listItem.getFacListdesignation();
                faculdept=listItem.getFacdepartment();*/
              // Intent i = new Intent(context, FaculPro.class);
              // ActivityOptionsCompat optioncompat= ActivityOptionsCompat.makeSceneTransitionAnimation(R)
               //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               //context.startActivity(new Intent(context,FaculPro.class));
               // context.startActivity(i);
           }
       });
    }

    @Override
    public int getItemCount() {
      return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView studListname;
       public TextView studListRoll;
        public TextView studListSkills;
        public LinearLayout itemLayout;
        public TextView studListEmail;
        public TextView studListDepartment;
        public Button confirmityButton;
        public Button deleteRequestButton;

        public ViewHolder(View itemView) {
            super(itemView);
            studListname=(TextView) itemView.findViewById(R.id.txt_stud_name);
            studListRoll=(TextView) itemView.findViewById(R.id.txt_stud_roll_no);
            studListEmail=(TextView) itemView.findViewById(R.id.txt_stud_email);
            studListDepartment=(TextView) itemView.findViewById(R.id.txt_stud_department);
            studListSkills=(TextView) itemView.findViewById(R.id.txt_stud_skills);
            itemLayout=(LinearLayout) itemView.findViewById(R.id.studItemLayout);
            confirmityButton=(Button) itemView.findViewById(R.id.confirmity_btn);
            deleteRequestButton=(Button) itemView.findViewById(R.id.delete_request_btn);
        }

    }





}

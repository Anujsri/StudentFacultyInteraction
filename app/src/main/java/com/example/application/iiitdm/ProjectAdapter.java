package com.example.application.iiitdm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<ProjectListItem> listItems;
    private Context context;
public static String projectname,projectdescription;
    public ProjectAdapter(List<ProjectListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ProjectListItem listItem = listItems.get(position);

        holder.projectListName.setText(listItem.getProjectListName());
        holder.projectListDescription.setText(listItem.getProjectListDescription());
        if(SharedPrefManager.getInstance(context).studisLoggedIn())
        {
        holder.projectItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Long Press To Add Request", Toast.LENGTH_SHORT).show();
            }
        });
        holder.projectItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final String project_name = listItem.getProjectListName();
                final String fac_email = FacultyAdapter.facemail;
                final String roll_no = SharedPrefManager.getInstance(context).getStudRollNo();


                // holder.progressDialog.setMessage("Requesting Please Wait.....");
                //holder.progressDialog.show();

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        Constants.URL_PROJECT_REQUEST,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    Toast.makeText(context, jsonObject.getString("message").toString(), Toast.LENGTH_LONG).show();


                                } catch (Exception e) {
                                    // holder.progressDialog.dismiss();
                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //holder.progressDialog.dismiss();
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("project_name", project_name);
                        params.put("fac_email", fac_email);
                        params.put("roll_no", roll_no);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);

                //Toast.makeText(context,"Long Press",Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }else if(SharedPrefManager.getInstance(context).facultyisLoggedIn()){

            holder.projectItemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Toast.makeText(context,"Long Press to See the Requests",Toast.LENGTH_SHORT).show();
                }

            });
            holder.projectItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    projectname=listItem.getProjectListName();
                    Intent i = new Intent(context, StudentRequestList.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
      return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView projectListName;
       public TextView projectListDescription;
        public RelativeLayout projectItemLayout;
       // final ProgressDialog progressDialog;
        public ViewHolder(View itemView) {
            super(itemView);
            projectListName=(TextView) itemView.findViewById(R.id.textprojectname);
            projectListDescription=(TextView) itemView.findViewById(R.id.textprojectDescription);
            projectItemLayout=(RelativeLayout) itemView.findViewById(R.id.projectItemLayout);
           // progressDialog=new ProgressDialog(context);
        }

    }

}

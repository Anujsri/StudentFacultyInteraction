package com.example.application.iiitdm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by S-Tech Computer on 20/07/2017.
 */

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.ViewHolder> {
    private List<FacultyListItem> listItems;
    private Context context;
public static String faculname,faculdesignation,faculdept,facemail;
    public FacultyAdapter(List<FacultyListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FacultyListItem listItem=listItems.get(position);
        holder.facListname.setText(listItem.getFacListname());
        holder.facListDesignation.setText(listItem.getFacListdesignation());
       holder.itemLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               faculname=listItem.getFacListname();
               facemail=listItem.getFacemail();
               faculdesignation=listItem.getFacListdesignation();
                faculdept=listItem.getFacdepartment();
               Intent i = new Intent(context, FaculPro.class);
              // ActivityOptionsCompat optioncompat= ActivityOptionsCompat.makeSceneTransitionAnimation(R)
               i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               //context.startActivity(new Intent(context,FaculPro.class));
                context.startActivity(i);
           }
       });
    }

    @Override
    public int getItemCount() {
      return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView facListname;
       public TextView facListDesignation;
        public RelativeLayout itemLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            facListname=(TextView) itemView.findViewById(R.id.fac_list_name);
             facListDesignation=(TextView) itemView.findViewById(R.id.fac_list_designation);
            itemLayout=(RelativeLayout) itemView.findViewById(R.id.itemLayout);

        }

    }

}

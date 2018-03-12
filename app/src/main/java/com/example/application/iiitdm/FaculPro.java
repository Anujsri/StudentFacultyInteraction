package com.example.application.iiitdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FaculPro extends AppCompatActivity {
    private PopupWindow mssgpopup,projectpopup;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    String[] projectName={"project1","project2","project3","project4","project5","project6","project7","project8","project9","project10"};
   public Button studaction;
    TextView facnam,dept,fac_desg;
    ImageView facimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facul_pro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        relativeLayout=(RelativeLayout) findViewById(R.id.stud_to_fac_pro);
        facnam=(TextView) findViewById(R.id.facul_nam);
        dept=(TextView) findViewById(R.id.fac_depart);
        fac_desg=(TextView) findViewById(R.id.fac_designation);
        facimage=(ImageView) findViewById(R.id.facul_image);


        facnam.setText(FacultyAdapter.faculname);
        dept.setText(FacultyAdapter.faculdept);
        facimage.setImageResource(FacultyList.img);
        fac_desg.setText(FacultyAdapter.faculdesignation);
        studaction=(Button) findViewById(R.id.stud_action);


        if(StudPro.btnflag==1){
            studaction.setText("Project list");
            studaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* layoutInflater=(LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container=(ViewGroup) layoutInflater.inflate(R.layout.project_pop_up,null);
                    projectpopup=new PopupWindow(container,700,400,true);
                    projectpopup.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,10,200);
                    ListView facultyList=(ListView)container.findViewById(R.id.project_list);
                    FaculPro.CustomAdapter customAdapter=new FaculPro.CustomAdapter();
                    facultyList.setAdapter(customAdapter);
                    facultyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Toast.makeText(FaculPro.this,"You Selected "+projectName[position],Toast.LENGTH_LONG).show();
                            projectpopup.dismiss();
                        }
                    });
                    container.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            projectpopup.dismiss();
                            return true;
                        }
                    });*/
                   startActivity(new Intent(FaculPro.this,RecyclerProjectList.class));

                }
            });
        }
        if(StudPro.btnflag==2){
            studaction.setText("Message");
            studaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layoutInflater=(LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container=(ViewGroup) layoutInflater.inflate(R.layout.stud_mssg_popup,null);
                    mssgpopup=new PopupWindow(container,700,400,true);
                    mssgpopup.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,10,200);
                    Button mssgsent=(Button) container.findViewById(R.id.sent_mssg);
                    mssgsent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(FaculPro.this,"Message Sent",Toast.LENGTH_LONG).show();
                            mssgpopup.dismiss();
                        }
                    });
                    container.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            mssgpopup.dismiss();
                            return true;
                        }
                    });

                }
            });
        }

    }
  /*  class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return projectName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.project_list_layout,null);
           TextView txtproject=(TextView) convertView.findViewById(R.id.textproject);
            txtproject.setText(projectName[position]);
            return convertView;
        }
    }*/

}

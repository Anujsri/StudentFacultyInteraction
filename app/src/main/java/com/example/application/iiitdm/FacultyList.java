package com.example.application.iiitdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FacultyList extends AppCompatActivity {
    public static int img;
    public static String facnam,department;
  int[] ImagesCSE={R.drawable.aprajitaojha,R.drawable.atulgupta,R.drawable.ayanseal,R.drawable.kusumkumari,R.drawable.mkbajpai,
                     R.drawable.priteek,R.drawable.srabanmohanty,R.drawable.vkjain},
            ImagesME={R.drawable.amarnath,R.drawable.goutamdutta,R.drawable.chelladurai,R.drawable.harpreetsingh,R.drawable.zahidansari,
                      R.drawable.pavankankar,R.drawable.pramodkumarjain,R.drawable.prashantjain,R.drawable.puneettandon,R.drawable.samratrao,
                      R.drawable.saurabhpratap,R.drawable.shivdayal,R.drawable.sujoymukher,R.drawable.sunilagra,R.drawable.tanuja,R.drawable.vkgupta},
            ImagesECE={R.drawable.anilkumar,R.drawable.atulkumar,R.drawable.biswajeetmukh,R.drawable.dheerajsharma,R.drawable.dineshkumarv,
                       R.drawable.dipprakash,R.drawable.jawar,R.drawable.manojpari,R.drawable.matadeenbansal,R.drawable.pnkondekar,R.drawable.prabinpathy,
                       R.drawable.skjain,R.drawable.varunb},
            ImagesNS={R.drawable.amreshmishra,R.drawable.kundu,R.drawable.bhupendra,R.drawable.deepmala,R.drawable.balyan,R.drawable.manand,
                      R.drawable.panda,R.drawable.mkroy,R.drawable.neerajjais,R.drawable.mahato,R.drawable.jena,R.drawable.lamba,R.drawable.yashkath},
            ImagesDesign={R.drawable.prabirmuko,R.drawable.puneettandon,R.drawable.sangeetapandit,R.drawable.shekharchat};

     String[] FacultyNamesCSE={"Aparajita Ojha","Atul Gupta","Ayan Seal","Kusum Kumari Bharti","Manish Kumar Bajpai","Pritee Khanna",
                              "Sraban Kumar Mohanty","Vinod Kumar Jain"},
            FacultyNamesME={"Amarnath M.","Goutam Datta","H. Chelladurai","Harpreet Singh","Mohd. Zahid Ansari","Pavan Kumar Kankar",
                            "Pramod Kumar Jain","Prashant Kumar Jain","Puneet Tandon","Samrat Rao","Saurabh Pratap","Shivdayal Patel",
                              "Sujoy Mukherjee","Sunil Agrawal","Tanuja Sheorey","Vijay Kumar Gupta"},
            FacultyNamesECE={"Anil Kumar","Atul Kumar","Biswajeet Mukherjee","Dheeraj Sharma","Dinesh Kumar V","Dip Prakash Samajdar",
                             "Jawar Singh","Manoj Singh Parihar","Matadeen Bansal","P.N. Kondekar","Prabin Kumar Padhy","Sachin Kumar Jain","Varun Bajaj"},
            FacultyNamesNS={"Amresh Mishra","Ashish K. Kundu","Bhupendra Gupta","Deepmala","Lokendra Balyan","Mamta Anand","Manoj Kumar Panda",
                            "Mukesh Kumar Roy","Neeraj Kumar Jaiswal","Nihar Kumar Mahato","Nihar Ranjan Jena","Subir Lamba","Yashpal Singh Katharia"},
            FacultyNamesDesign={"Prabir Mukhopadhyay","Puneet Tandon","Sangeeta Pandit","Shekhar Chatterjee"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView facultyList=(ListView)findViewById(R.id.faculty_list);
        CustomAdapter customAdapter=new CustomAdapter();
        facultyList.setAdapter(customAdapter);
        facultyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(StudPro.branchflag==1)
                {
                    img=ImagesCSE[position];
                    facnam=FacultyNamesCSE[position];
                    department="CSE";
                }
                if(StudPro.branchflag==2)
                {
                    img=ImagesME[position];
                    facnam=FacultyNamesME[position];
                    if(facnam=="Puneet Tandon")
                        department="ME/Design and Descipline";
                    else
                    department="ME";
                }
                if(StudPro.branchflag==3)
                {
                    img=ImagesECE[position];
                    facnam=FacultyNamesECE[position];
                    department="ECE";
                }
                if(StudPro.branchflag==4)
                {
                    img=ImagesDesign[position];
                    facnam=FacultyNamesDesign[position];
                    if(facnam=="Puneet Tandon")
                    department="Design and Descipline/ME";
                    else
                        department="Design and Descipline";
                }
                if(StudPro.branchflag==5)
                {
                    img=ImagesNS[position];
                    facnam=FacultyNamesNS[position];
                    if(facnam=="Mamta Anand")
                    department="English Language and Literature";
                    else
                        if(facnam=="Lokendra Balyan" || facnam=="Nihar Kumar Mahato" || facnam=="Manoj Kumar Panda" || facnam=="Subir Lamba" || facnam=="Bhupendra Gupta" || facnam=="Deepmala")
                            department="Mathematics";
                        else
                            department="Physics";
                }
             proClk();
            }
        });
    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            int i=0;
            if(StudPro.branchflag==1)
            i=ImagesCSE.length;
            if(StudPro.branchflag==2)
                i=ImagesME.length;
            if(StudPro.branchflag==3)
                i=ImagesECE.length;
            if(StudPro.branchflag==4)
                i=ImagesDesign.length;
            if(StudPro.branchflag==5)
                i= ImagesNS.length;
            return i;
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
            convertView=getLayoutInflater().inflate(R.layout.list_layout,null);
            ImageView fac_image=(ImageView) convertView.findViewById(R.id.fac_image);
            TextView text_fac_name=(TextView) convertView.findViewById(R.id.fac_list_name);
            if(StudPro.branchflag==1) {
                fac_image.setImageResource(ImagesCSE[position]);
                text_fac_name.setText(FacultyNamesCSE[position]);
            }
            if(StudPro.branchflag==2) {
                fac_image.setImageResource(ImagesME[position]);
                text_fac_name.setText(FacultyNamesME[position]);
            }
            if(StudPro.branchflag==3) {
                fac_image.setImageResource(ImagesECE[position]);
                text_fac_name.setText(FacultyNamesECE[position]);
            }
            if(StudPro.branchflag==4) {
                fac_image.setImageResource(ImagesDesign[position]);
                text_fac_name.setText(FacultyNamesDesign[position]);
            }
            if(StudPro.branchflag==5) {
                fac_image.setImageResource(ImagesNS[position]);
                text_fac_name.setText(FacultyNamesNS[position]);
            }
            return convertView;
        }
    }
    public void proClk()
    {
        startActivity(new Intent(this,FaculPro.class));
    }
}

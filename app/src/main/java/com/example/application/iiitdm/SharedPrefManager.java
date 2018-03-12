package com.example.application.iiitdm;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by S-Tech Computer on 16/07/2017.
 */
public class SharedPrefManager {
    private static SharedPrefManager mInstance;

    private ImageLoader mImageLoader;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_NAME="name";
    private static final String KEY_EMAIL="email";
    private static final String KEY_ROLL_NO="roll_no";
    private static final String KEY_DEPARTMENT="department";
    private static  final String KEY_SKILLS="skills";

    private static final String FACULTY_SHARED_PREF_NAME="fmysharedpref12";
    private static final String FACULTY_KEY_NAME="name";
    private static final String FACULTY_KEY_EMAIL="email";
    private static final String FACULTY_KEY_CABIN="cabin_addr";
    private static final String FACULTY_KEY_DEPARTMENT="department";
    private static final String FACULTY_KEY_DESIGNATION="designation";

    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean studLogin(String name,String email,String roll_no,String department, String skills){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_ROLL_NO,roll_no);
        editor.putString(KEY_DEPARTMENT,department);
        editor.putString(KEY_SKILLS,skills);
        editor.apply();
        return true;

    }
    public boolean facultyLogin(String name,String email,String cabin_addr,String department,String designation){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(FACULTY_SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(FACULTY_KEY_NAME,name);
        editor.putString(FACULTY_KEY_EMAIL,email);
        editor.putString(FACULTY_KEY_CABIN,cabin_addr);
        editor.putString(FACULTY_KEY_DEPARTMENT,department);
        editor.putString(FACULTY_KEY_DESIGNATION,designation);
        editor.apply();
        return true;

    }

    public boolean studisLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_NAME,null)!=null){
        return true;
        }else
        return false;
    }
    public boolean facultyisLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(FACULTY_SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(FACULTY_KEY_NAME,null)!=null){
            return true;
        }else
            return false;
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public boolean facultyLogout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(FACULTY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getFacultyName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(FACULTY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACULTY_KEY_NAME,null);
    }

    public String getFacultyEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(FACULTY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACULTY_KEY_EMAIL,null);
    }
    public String getFacultyCabin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(FACULTY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACULTY_KEY_CABIN,null);
    }
    public String getFacultyDepartment(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(FACULTY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACULTY_KEY_DEPARTMENT,null);
    }
    public String getFacultyDesignation(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(FACULTY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACULTY_KEY_DESIGNATION,null);
    }
    public String getStudDept(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DEPARTMENT,null);
    }

    public String getStudName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME,null);
    }

    public String getStudEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null);
    }
    public String getStudRollNo(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLL_NO,null);
    }
}
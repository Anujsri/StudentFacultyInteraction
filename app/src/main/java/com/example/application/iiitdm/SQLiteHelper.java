package com.example.application.iiitdm;

/**
 * Created by anujmam on 7/11/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="UserDataBase";

    public static final String TABLE_NAME="UserTable";

    public static final String FACULTY_TABLE="FacultyUser";
    public static final String Table_Column_FID="fid";



    public static final String Table_Column_2_FEmail="femail";

    public static final String Table_Column_3_FPassword="fpassword";




    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Email="email";

    public static final String Table_Column_3_Password="password";
    public static final String Table_Column_4_Department="department";

    public static final String Table_Column_6_Roll="roll";



    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+
                " VARCHAR,"+Table_Column_4_Department+
                " VARCHAR,"+Table_Column_6_Roll+" TEXT)";
        database.execSQL(CREATE_TABLE);

        String CREATE_FTABLE="CREATE TABLE IF NOT EXISTS "+ FACULTY_TABLE+"("+Table_Column_FID+
                "INTEGER PRIMARY KEY AUTOINCREMENT, "+Table_Column_2_FEmail+"VARCHAR, "+Table_Column_3_FPassword+"VARCHAR)";
        database.execSQL(CREATE_FTABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS");
        onCreate(db);

    }

    public Cursor rawQuery(String string, String[] strings) {
        // TODO Auto-generated method stub
        return null;
    }


}

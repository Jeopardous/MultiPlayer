package com.cheguza.facilitycenter.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDataBaseHelper extends SQLiteOpenHelper {

        public String TAG="MY Database helper";
        public static final String DATABASE_NAME="mulplayer.db";
        public static final String TABLE_NAME="mullplayer";
        public static final String COL_1="ID";
        public static final String COL_2="FIRST_NAME";
        public static final String COL_3="LAST_NAME";
        public static final String COL_4="USER_NAME";
        public static final String COL_5="PHONE_NO";

        public MyDataBaseHelper( Context context)
        {

            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(" create table "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRST_NAME TEXT,LAST_NAME TEXT,USER_NAME TEXT,PHONE_NO TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            // onCreate(db);
        }

        public boolean insertData(String fname,String lname, String username,String phoneno)
        {
            Log.d("TAG","Inserting Data");

            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(COL_2,fname);
            contentValues.put(COL_3,lname);
            contentValues.put(COL_4,username);
            contentValues.put(COL_5,phoneno);

            long result=db.insert(TABLE_NAME,null,contentValues);

            if(result==-1)
            {
                return false;
            }
            else {
                Log.d("TAG","inserted");
                return true;

            }
        }

        public int deleteData(String PhnNo)
        {
            SQLiteDatabase db=this.getWritableDatabase();
            return db.delete(TABLE_NAME,"PHONE_NO = ?",new String[] {PhnNo});
        }

}

package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.util.ArrayList;
import java.util.List;

public class sqlitehelper3 extends SQLiteOpenHelper {
    private String tname = "budget";
    private String cname = "cat";
    private String ename = "exp";
    private String dname = "date";
    private String id="id";

    public sqlitehelper3(Context context) {
        super(context, "db2", null, 1);
    }

    public sqlitehelper3(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table " + tname + " (" +id+" integer primary key autoincrement ,"+ cname + " text," + ename + " number,"+dname+" text)";
        sqLiteDatabase.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public long add_user(String cat, int exp,String date) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(cname, cat);
        values.put(ename, exp);
        values.put(dname,date);
        long l = sqLiteDatabase.insert(tname, "", values);
        return l;
    }
    public ArrayList<extra> show_cal(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cr=sqLiteDatabase.rawQuery(" Select *from "+tname,null);
        ArrayList<extra> earray=new ArrayList<>();
        extra e;
        while (cr.moveToNext()){
            e=new extra(cr.getString(1),cr.getInt(2),cr.getString(3));
            earray.add(e);
        }
        return  earray;
    }
    public void delete_cal(){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        int Cr=sqLiteDatabase.delete(tname,null,null);
    }

}

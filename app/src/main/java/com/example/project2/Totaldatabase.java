package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

public class Totaldatabase extends SQLiteOpenHelper {
    private String tname="Totaltable";
    private String total_val="Total";
    private String remain="Remian";
    public Totaldatabase(Context context) {
        super(context, "total", null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase total) {
        total.execSQL(" Create table "+tname+"( "+this.total_val+" number, "+remain+" number)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public long Set_total(int value){
        SQLiteDatabase total=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(this.total_val,value);
        values.put(this.remain,value);
        return total.insert(tname,"",values);
    }
    public String disp_total(){
        SQLiteDatabase total=getReadableDatabase();
        StringBuilder stringBuilder=new StringBuilder();
        Cursor cr=total.rawQuery("select *from "+tname,null);
        while(cr.moveToNext()){
            stringBuilder.append(cr.getString(0));
        }
        return  stringBuilder.toString();
    }
    public String disp_remain(){
        SQLiteDatabase total=getReadableDatabase();
        StringBuilder stringBuilder=new StringBuilder();
        Cursor cr=total.rawQuery("select *from "+tname,null);
        while(cr.moveToNext()){
            stringBuilder.append(cr.getString(1));
        }
        return  stringBuilder.toString();
    }
    public  long up_rem(int value){
        SQLiteDatabase total=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(remain,value);
        return total.update(tname,values,null,null);

    }
    public long up_new(int value){
        SQLiteDatabase total=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(total_val,value);
        values.put(remain,value);
        return  total.update(tname,values,null,null);


    }
    public void delete_ca(){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        int Cr=sqLiteDatabase.delete(tname,null,null);
    }
}

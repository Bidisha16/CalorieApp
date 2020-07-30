package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class sqlitehelper2 extends SQLiteOpenHelper {
    private String tname="user1";
    private String uname="name";
    private String uage="age";
    private String uheight="height";
    private String uweight="weight";

    public sqlitehelper2(Context context){
        super(context,"db3",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sql="Create table "+tname+" ("+uname+" text,"+uage+" text,"+uheight+" text,"+uweight+" text)";
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion,int newVersion){

    }
    public long add_user(String name,String age,String height,String weight) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(uname, name);
        values.put(uage, age);
        values.put(uheight, height);
        values.put(uweight, weight);

        long l=sqLiteDatabase.insert(tname,"",values);
        return l;
    }
    public long updateUser(String name,String age,String height,String weight) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(uage, age);
        values.put(uheight, height);
        values.put(uweight, weight);

        return sqLiteDatabase.update(tname, values, "name=?", new String[]{name});
    }
    public String get_user(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        StringBuilder stringBuilder=new StringBuilder();
        Cursor cursor=sqLiteDatabase.rawQuery("select *from "+tname,null);
        while (cursor.moveToNext()){
            stringBuilder.append(cursor.getString(0)+" "+cursor.getString(1)+"\n");
        }
        return sqLiteDatabase.toString();
    }

}

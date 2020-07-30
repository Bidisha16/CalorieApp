package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlitehelper extends SQLiteOpenHelper {
      private String tname="user";
      private String uname="user_name";
      private String upass="password";
      private String mail="mail";

      public sqlitehelper(Context context){
            super(context,"db",null,1);
      }

      @Override
      public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql="Create table "+tname+" ("+uname+" text,"+upass+" text,"+mail+" text)";
            sqLiteDatabase.execSQL(sql);
      }
      public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion,int newVersion){
      }
      public long add_user(String name,String pass,String mail){
            SQLiteDatabase sqLiteDatabase=getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(uname,name);
            values.put(upass,pass);
            values.put(this.mail,mail);
            long l=sqLiteDatabase.insert(tname,"",values);
            return l;
      }
      public boolean login(String name,String pass){
            SQLiteDatabase db=getReadableDatabase();
            Cursor cr=db.rawQuery("select *from "+tname+ " where "+uname+ " "+"=? and "+upass+"=?",new String[]{name,pass});
            return cr.moveToNext();
    }
    public  String show_username(){
            SQLiteDatabase sqLiteDatabase=getReadableDatabase();
            StringBuilder stringBuilder=new StringBuilder();
            Cursor cr=sqLiteDatabase.rawQuery("select *from "+tname,null);
            while (cr.moveToNext()){
                  stringBuilder.append(cr.getString(0));
            }
            return stringBuilder.toString();

    }
      public  String show_mail(){
            SQLiteDatabase sqLiteDatabase=getReadableDatabase();
            StringBuilder stringBuilder=new StringBuilder();
            Cursor cr=sqLiteDatabase.rawQuery("select *from "+tname,null);
            while (cr.moveToNext()){
                  stringBuilder.append(cr.getString(2));
            }
            return stringBuilder.toString();

      }

}

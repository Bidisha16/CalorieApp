package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_adapter extends ArrayAdapter {
    Context context;
    public ArrayList<extra> arrayList;
    public  String s[];

    public Custom_adapter(Context context, ArrayList<extra> e,String s[]) {
        super(context, R.layout.custom_list,s);
        this.context=context;
        this.arrayList=e;
        this.s=s;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.custom_list,null);
        TextView tv1=v.findViewById(R.id.cal);
        TextView tv2=v.findViewById(R.id.value);
        TextView tv3=v.findViewById(R.id.show_date);
        extra e=arrayList.get(position);
        tv1.setText(e.getCat());
        tv2.setText(Integer.toString(e.getExp()));
        tv3.setText(e.getDate());
        return v;
    }
}

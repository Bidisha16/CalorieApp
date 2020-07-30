package com.example.project2;

public class extra {
    private String cat;
    private int exp;
    private String date;

    public extra(String cat, int exp,String date) {
        this.cat = cat;
        this.exp = exp;
        this.date=date;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
package com.example.testzap;

import java.util.Date;
import java.text.SimpleDateFormat;

public class time_msg{

    static String user="Shivam";

    public static void main(String []args){

        String s= "";
        Date today = new Date();

        SimpleDateFormat format = new SimpleDateFormat("HH");

        String time = format.format(today);

        int date= Integer.parseInt(time);
        if(date>=4&&date<12)
            s="Good Morning, "+user;
        else if(date>=12&&date<17)
            s="Good Afternoon, "+user;
        else if(date>=17&&date<23)
            s="Good Evening, "+user;
        else
            s="Good Morning, "+user;
    }
}

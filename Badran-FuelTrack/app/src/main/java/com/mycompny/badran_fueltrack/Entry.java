package com.mycompny.badran_fueltrack;

import java.util.Date;

/**
 * Created by hasan on 2016-01-28.
 */

public class Entry {
    protected Date date;
    protected String message;

    //public Boolean isImportant();

    public Entry(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public Entry(String message) {
        this.message = message;
        this.date = new Date();
    }

    public Entry() {
    }

    @Override
    public String toString() {
        return "YES";
    }


    //public void setMessage(String message) throws TweetTooLongException {
        //if (message.length() > 140) {
            //throw new TweetTooLongException();
        //}
        //this.message = message;
    //}

    public void setDate(Date date) {
        this.date = date;
    }

    //@Override
//    public String toString(){
//        return date.toString() + " | " + message;
//    }

    public String getMessage() {
        return null;
    }

    public Date getDate() {
        return this.date = date;
    }
}

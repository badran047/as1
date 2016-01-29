package com.mycompny.badran_fueltrack;

import java.util.Date;

/**
 * Created by hasan on 2016-01-28.
 */
public class NormalEntry extends Entry implements Entryable {
//    @Override
//    public Boolean isImportant() {
//        return false;
//    }

    public NormalEntry(Date date, String message) {
        super(date, message);
    }

    public NormalEntry(String message) {
        super(message);
    }

    public String getMessage() {
        return null;
    }

    public Date getDate() {
        return this.date = date;
    }
}

package com.mycompny.badran_fueltrack;

import java.util.Date;

/**
 * Created by hasan on 2016-01-28.
 */
public class ImportantEntry extends Entry implements Entryable {
//    @Override
//    public Boolean isImportant() {
//        return Boolean.TRUE;
//    }

    public ImportantEntry(Date date, String message) {
        super(date, message);
    }

    public ImportantEntry(String message) {
        super(message);
    }

    public Date getDate() {
        return this.date;
    }

    public String getMessage() {
        return "!IMPORTANT! " + this.message;
    }
}

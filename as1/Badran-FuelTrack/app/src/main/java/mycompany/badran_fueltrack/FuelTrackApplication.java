package mycompany.badran_fueltrack;

import android.app.Application;

/**
 * Created by hasan on 2016-01-29.
 */
public class FuelTrackApplication extends Application {

    transient private static EntryList fueltrack = null;

    public static EntryList getFuelTrack() {
        if (fueltrack == null) {
            fueltrack = new EntryList();
        }
        return fueltrack;
    }

    // Singleton
    transient private static Controller controller = null;

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
}

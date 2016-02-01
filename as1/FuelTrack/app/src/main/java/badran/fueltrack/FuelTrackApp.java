package badran.fueltrack;

import android.app.Application;
import android.content.Context;

/**
 * Created by hasan on 2016-01-30.
 */
public class FuelTrackApp extends Application {
    /* create EntryList */
    transient public static EntryList app = null;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            appContext = getApplicationContext();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /* taken from:
     * http://stackoverflow.com/questions/21994612/get-application-context-returns-null
     */
    public static Context getContext() {
        return appContext;
    }

    public static EntryList getApp() {
        if (app == null) {
            app = new EntryList();
        }
        return app;
    }

    /* create AppController */
    transient static public AppController controller = null;
    public static AppController getController() {
        if (controller == null) {
            controller = new AppController();
        }
        return controller;
    }

}

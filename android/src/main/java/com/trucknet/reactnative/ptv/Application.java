package com.trucknet.reactnative.ptv;

import com.ptvag.navigation.sdk.NavigationException;
import com.ptvag.navigation.sdk.NavigationSDK;

import java.io.File;

public class Application extends android.app.Application {

    private static Throwable startupException;

    public static boolean hadStartupException() {
        return startupException != null;
    }

    public static Throwable getStartupException() {
        return startupException;
    }

    private static File basePath;
    private static File mapsPath;
    private static File addrPath;
    private static File dataPath;

    @Override
    public void onCreate() {
        super.onCreate();

        basePath = getExternalFilesDir(null);
        mapsPath = new File(basePath, "maps");
        addrPath = new File(basePath, "addr");
        dataPath = new File(basePath, "data");

        try {
            NavigationSDK.initialize(
                    mapsPath,
                    "deu",
                    addrPath,
                    dataPath
            );
        } catch (NavigationException e) {
            startupException = e;
            return;
        }
    }

    public static File getBasePath() {
        return basePath;
    }

    public static File getMapsPath() {
        return mapsPath;
    }

    public static File getAddrPath() {
        return addrPath;
    }

    public static File getDataPath() {
        return dataPath;
    }
}
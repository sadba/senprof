package sadba.lab.com.senprof;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(this);


        RealmConfiguration config =
                new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build();

        Realm.setDefaultConfiguration(config);
    }


    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }
}

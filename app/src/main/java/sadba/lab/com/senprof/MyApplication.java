package sadba.lab.com.senprof;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import sadba.lab.com.senprof.Api.JsonFetcher;
import sadba.lab.com.senprof.model.Lecon;
import sadba.lab.com.senprof.model.Video;

public class MyApplication extends Application {

    Realm realm;
    List<Video> videos = new ArrayList<>();
    List<Lecon> lecons = new ArrayList<>();
    Boolean donneeIntialiser;
    private static final String TAG = MyApplication.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("RNPT.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        donneeIntialiser = sharedPreferences.getBoolean("donneesIntialisees", false);

        if (!donneeIntialiser) {
            try {
                lecons = setUpLecon();
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(lecons);
            realm.commitTransaction();

            editor.putBoolean("niveauAjouter", true);
            editor.apply();
        }
    }

    private List<Lecon> setUpLecon() throws IOException, JSONException {
        JsonFetcher fetcher = new JsonFetcher(this, R.raw.leconsvideos);
        String fileContent = fetcher.toString();
        JSONObject tokner = new JSONObject(fileContent);
        RealmList<Video> videos = new RealmList<>();
        try {
            JSONArray jArray = tokner.getJSONArray("LeconsVideos");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject l = jArray.getJSONObject(i);
                Lecon lecon = new Lecon();
                lecon.setId(l.getInt("id"));
                lecon.setNom(l.getString("nom"));
                //lecon.setChapitre_id(l.getInt("chapitre_id"));
                //lecon.setMatiere_id(l.getInt("matiere_id"));

                JSONArray videoArray = l.getJSONArray("videos");

                for (int j = 0; j < videoArray.length(); j++) {
                    JSONObject vi = videoArray.getJSONObject(j);
                    Video video = new Video();
                    video.setYoutube_id(vi.getString("youtube_id"));
                    video.setNom(vi.getString("nom"));
                    videos.add(video);

                }
                lecon.setVideos(videos);
                lecons.add(lecon);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "setUpLecon: " + e.getLocalizedMessage());
        }
        return lecons;
    }
}

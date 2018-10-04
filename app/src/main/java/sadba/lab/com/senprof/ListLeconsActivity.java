package sadba.lab.com.senprof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.List;

import io.realm.Realm;
import sadba.lab.com.senprof.adapter.ExpandAdapter;
import sadba.lab.com.senprof.model.Lecon;

public class ListLeconsActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lecons);
        Realm.init(this);

        ExpandableListView elv = findViewById(R.id.LeconExpandId);
        realm = Realm.getDefaultInstance();
        List<Lecon> lecons = realm.where(Lecon.class).findAll();

        elv.setAdapter(new ExpandAdapter(this, lecons));
    }
}

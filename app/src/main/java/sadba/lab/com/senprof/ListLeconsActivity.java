package sadba.lab.com.senprof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.List;

import sadba.lab.com.senprof.adapter.ExpandAdapter;
import sadba.lab.com.senprof.model.Lecon;

public class ListLeconsActivity extends AppCompatActivity {

    private ExpandableListView elv;
    private List<Lecon> lecons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lecons);

        elv = findViewById(R.id.LeconExpandId);

        elv.setAdapter(new ExpandAdapter(this, lecons));
    }
}

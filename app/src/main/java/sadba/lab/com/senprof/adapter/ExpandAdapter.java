package sadba.lab.com.senprof.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import sadba.lab.com.senprof.R;
import sadba.lab.com.senprof.YoutubeDialogActivity;
import sadba.lab.com.senprof.model.Lecon;
import sadba.lab.com.senprof.model.Video;

public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Lecon> lecons;

    public ExpandAdapter(Context context, List<Lecon> lecons) {
        this.context = context;
        this.lecons = lecons;
    }

    @Override
    public int getGroupCount() {
        return lecons.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return lecons.get(groupPosition).getVideos().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lecons.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return lecons.get(groupPosition).getVideos().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Lecon  lecon = lecons.get(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_row, null);
        }
        TextView txtTitle = convertView.findViewById(R.id.title);

        txtTitle.setText(lecon.getNom());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Video video = (Video) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.raw_child, null);
        }
        TextView txtchild = convertView.findViewById(R.id.VideoTitle);
        if (!TextUtils.isEmpty(video.getNom())) {
            txtchild.setText(video.getNom());
            txtchild.setTextColor(Color.BLACK);
        } else {
            txtchild.setText(video.getYoutube_id());
        }
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), YoutubeDialogActivity.class);
            v.getContext().startActivity(intent);
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

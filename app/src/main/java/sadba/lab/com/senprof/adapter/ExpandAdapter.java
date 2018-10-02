package sadba.lab.com.senprof.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import sadba.lab.com.senprof.model.Lecon;

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
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

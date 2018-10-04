package sadba.lab.com.senprof.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sadba.lab.com.senprof.R;
import sadba.lab.com.senprof.model.Video;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private List<Video> videos;

    public VideoAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.raw_child, viewGroup, false);
        return new VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        Video video = videos.get(i);
        VideoViewHolder.bindVideo(video);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}

package sadba.lab.com.senprof.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sadba.lab.com.senprof.R;
import sadba.lab.com.senprof.model.Video;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    private static TextView mVideoTitle;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        mVideoTitle = itemView.findViewById(R.id.VideoTitle);

    }

    static void bindVideo(Video video) {
        mVideoTitle.setText(video.getNom());
    }
}

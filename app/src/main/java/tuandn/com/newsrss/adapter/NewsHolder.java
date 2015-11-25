package tuandn.com.newsrss.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tuandn.com.newsrss.R;

/**
 * Created by Anh Trung on 11/24/2015.
 */
public class NewsHolder extends RecyclerView.ViewHolder {
    ImageView ivImage;
    TextView tvNewsTitle;
    TextView tvNewsContent;
    TextView tvNewsTime;
    RelativeLayout lyNewsItem;
    FrameLayout container;

    public NewsHolder(View itemView) {
        super(itemView);
//        lyNewsItem = (RelativeLayout) itemView.findViewById(R.id.layout_news_item);
        ivImage = (ImageView) itemView.findViewById(R.id.iv_news_image);
        tvNewsTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
        tvNewsContent = (TextView) itemView.findViewById(R.id.tv_news_content);
        tvNewsTime = (TextView) itemView.findViewById(R.id.tv_news_time);
        container = (FrameLayout) itemView.findViewById(R.id.item_layout_container);
    }
}

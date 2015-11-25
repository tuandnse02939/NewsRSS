package tuandn.com.newsrss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.greenrobot.event.EventBus;
import tuandn.com.newsrss.R;
import tuandn.com.newsrss.vnexpress.Item;
import tuandn.com.newsrss.vnexpress.Rss;

/**
 * Created by Anh Trung on 11/24/2015.
 */
public class ListAdapter extends RecyclerView.Adapter<NewsHolder> {
    private Rss data;
    private Context mContext;
    private int lastPosition = -1;

    public ListAdapter(Rss rss) {
        this.data = rss;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate((R.layout.item_task), parent,false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        final Item news;
        news = data.getChannel().getItem().get(position);
        holder.tvNewsTitle.setText(Html.fromHtml(news.getTitle()));
        holder.tvNewsContent.setText(Html.fromHtml(news.getDescription()));
        holder.tvNewsTime.setText(news.getPubDate());
        if(news.getSummaryImg() != null && !news.getSummaryImg().equals("")){
            Picasso.with(mContext).load(news.getSummaryImg())
                    .resize(100, 100)
                    .centerCrop()
                    .into(holder.ivImage);
        } else if(news.getImage() != null && !news.getImage().equals("")){
            Picasso.with(mContext).load(news.getImage())
                    .resize(100, 100)
                    .centerCrop()
                    .into(holder.ivImage);
        } else if(!news.getGuid().equals("")) {
            Picasso.with(mContext).load(news.getGuid())
                    .resize(100, 100)
                    .centerCrop()
                    .into(holder.ivImage);
        }

        holder.lyNewsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(news.getLink());
            }
        });
        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return data.getChannel().getItem().size();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_out);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}

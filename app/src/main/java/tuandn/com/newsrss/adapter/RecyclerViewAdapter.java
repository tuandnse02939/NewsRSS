/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tuandn.com.newsrss.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.greenrobot.event.EventBus;
import tuandn.com.newsrss.R;
import tuandn.com.newsrss.vnexpress.Item;
import tuandn.com.newsrss.vnexpress.Rss;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NewsHolder> implements View.OnClickListener {

    private Rss data;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context,Rss rss) {
        this.data = rss;
        mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        v.setOnClickListener(this);
        return new NewsHolder(v);
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
        holder.itemView.setTag(news);
    }

    @Override
    public int getItemCount() {
        return data.getChannel().getItem().size();
    }

    @Override public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    onItemClickListener.onItemClick(v, (Item) v.getTag());
                }
            }, 200);
        }
    }

    protected class NewsHolder extends RecyclerView.ViewHolder {
        ImageView       ivImage;
        TextView        tvNewsTitle;
        TextView        tvNewsContent;
        TextView        tvNewsTime;
        RelativeLayout  lyNewsItem;
        FrameLayout     container;

        public NewsHolder(View itemView) {
            super(itemView);
            lyNewsItem      = (RelativeLayout)  itemView.findViewById(R.id.layout_news_item);
            ivImage         = (ImageView)       itemView.findViewById(R.id.iv_news_image);
            tvNewsTitle     = (TextView)        itemView.findViewById(R.id.tv_news_title);
            tvNewsContent   = (TextView)        itemView.findViewById(R.id.tv_news_content);
            tvNewsTime      = (TextView)        itemView.findViewById(R.id.tv_news_time);
            container       = (FrameLayout)     itemView.findViewById(R.id.item_layout_container);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Item news);

    }
}

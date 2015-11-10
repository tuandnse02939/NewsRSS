package tuandn.com.newsrss.fragment;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import tuandn.com.newsrss.R;
import tuandn.com.newsrss.vnexpress.Item;
import tuandn.com.newsrss.vnexpress.Rss;

/**
 * Created by Anh Trung on 11/9/2015.
 */
public class ListNewsAdapter implements ListAdapter{

    private Context context;
    private Rss data;

    public ListNewsAdapter(Context context, Rss data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return data.getChannel() != null ? data.getChannel().getItem().size() : 0;
    }

    @Override
    public Item getItem(int position) {
        if (data != null && data.getChannel().getItem().size() - 1 >= position && position >= 0) {
            return data.getChannel().getItem().get(position);
        }
        else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_task, null);
            holder = new ViewHolder();
            holder.lyNewsItem = (RelativeLayout) convertView.findViewById(R.id.layout_news_item);
            holder.ivImage = (ImageView) convertView.findViewById(R.id.iv_news_image);
            holder.tvNewsTitle = (TextView) convertView.findViewById(R.id.tv_news_title);
            holder.tvNewsContent = (TextView) convertView.findViewById(R.id.tv_news_content);
            holder.tvNewsTime = (TextView) convertView.findViewById(R.id.tv_news_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Item news = data.getChannel().getItem().get(position);
        holder.tvNewsTitle.setText(news.getTitle());
        int i = news.getDescription().indexOf("</br>");
        String description = news.getDescription().substring(i+5, news.getDescription().length());
        holder.tvNewsContent.setText(description);
        holder.tvNewsTime.setText(news.getPubDate());

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private static class ViewHolder {
        ImageView ivImage;
        TextView tvNewsTitle;
        TextView tvNewsContent;
        TextView tvNewsTime;
        RelativeLayout lyNewsItem;
    }
}

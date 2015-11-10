package tuandn.com.newsrss.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tuandn.com.newsrss.DataForNewsPaper;

/**
 * Created by Anh Trung on 11/9/2015.
 */
public class NewsFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[];
    private Context context;

    public NewsFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        DataForNewsPaper dataForNewsPaper = DataForNewsPaper.getInstance();
        tabTitles = dataForNewsPaper.getListCategory();
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}

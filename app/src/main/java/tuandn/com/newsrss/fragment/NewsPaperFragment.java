package tuandn.com.newsrss.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tuandn.com.newsrss.DataForNewsPaper;
import tuandn.com.newsrss.R;


/**
 * Created by Anh Trung on 11/3/2015.
 */
public class NewsPaperFragment extends Fragment {

    private int banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_fr_news_paper,container,false);
        banner = DataForNewsPaper.getInstance().getBanner();

        ImageView ivBanner = (ImageView) rootView.findViewById(R.id.banner);
        ivBanner.setBackground(getResources().getDrawable(banner));

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new NewsFragmentAdapter(getActivity().getSupportFragmentManager(), getActivity()));
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

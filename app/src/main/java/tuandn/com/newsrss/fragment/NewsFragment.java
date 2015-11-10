package tuandn.com.newsrss.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;
import tuandn.com.newsrss.DataForNewsPaper;
import tuandn.com.newsrss.MainActivity;
import tuandn.com.newsrss.R;
import tuandn.com.newsrss.vnexpress.ApiInterface;
import tuandn.com.newsrss.vnexpress.Rss;

/**
 * Created by Anh Trung on 11/9/2015.
 */
public class NewsFragment extends Fragment {

    public static final int NEWEST = 0;
    public static final int HEADLINE = 1;
    public static final int WORLD = 2;
    public static final int BUSINESS = 3;

    private static NewsFragment instance;
    private String[] news;
    private int position;
    private Call<Rss> call;

    private ListNewsAdapter mAdapter;
    private ListView mLvNew;
    private Rss rss;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_news, container, false);
        mLvNew = (ListView) rootView.findViewById(R.id.lvListNew);

        rss = new Rss();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://vnexpress.net")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        DataForNewsPaper dataForNewsPaper = DataForNewsPaper.getInstance();
        String newspaperName = dataForNewsPaper.getNewspaperName();

        position = getArguments().getInt("position");

        ApiInterface api = retrofit.create(ApiInterface.class);
        if(newspaperName.equals(MainActivity.VNEXPRESS)) {
            switch (position) {
                case NEWEST:
                    call = api.getNewestRss();
                    break;
                case HEADLINE:
                    call = api.getHeadlineRss();
                    break;
                case WORLD:
                    call = api.getWorldRss();
                    break;
                case BUSINESS:
                    call = api.getBussinessRss();
                    break;
                default:
                    call = api.getNewestRss();
                    break;
            }

            // Asynchronous
            call.enqueue(new Callback<Rss>() {

                @Override
                public void onResponse(Response<Rss> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
//                    EventBus.getDefault().post(response.body());
                        mAdapter = new ListNewsAdapter(getContext(),response.body());
                        mLvNew.setAdapter(mAdapter);
                    } else {
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println("Loi: " + t.getMessage());
                }
            });

            rss = new Rss();
            mAdapter = new ListNewsAdapter(getContext(),rss);
            mLvNew.setAdapter(mAdapter);
        } else if(newspaperName.equals(MainActivity.DANTRI)){
            switch (position) {
                case NEWEST:
                    call = api.getNewestRss();
                    break;
                case HEADLINE:
                    call = api.getHeadlineRss();
                    break;
                case WORLD:
                    call = api.getWorldRss();
                    break;
                case BUSINESS:
                    call = api.getBussinessRss();
                    break;
                default:
                    call = api.getNewestRss();
                    break;
            }
        } else if(newspaperName.equals(MainActivity.ONLINE24H)){
            switch (position) {
                case NEWEST:
                    call = api.getNewestRss();
                    break;
                case HEADLINE:
                    call = api.getHeadlineRss();
                    break;
                case WORLD:
                    call = api.getWorldRss();
                    break;
                case BUSINESS:
                    call = api.getBussinessRss();
                    break;
                default:
                    call = api.getNewestRss();
                    break;
            }
        } else if(newspaperName.equals(MainActivity.BONGDAPLUS)){
            switch (position) {
                case NEWEST:
                    call = api.getNewestRss();
                    break;
                case HEADLINE:
                    call = api.getHeadlineRss();
                    break;
                case WORLD:
                    call = api.getWorldRss();
                    break;
                case BUSINESS:
                    call = api.getBussinessRss();
                    break;
                default:
                    call = api.getNewestRss();
                    break;
            }
        }

        return rootView;
    }

    public static NewsFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public void onEvent(Rss response) {
        mAdapter = new ListNewsAdapter(getContext(),response);
        rss = new Rss();
        rss = response;
        mLvNew.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}

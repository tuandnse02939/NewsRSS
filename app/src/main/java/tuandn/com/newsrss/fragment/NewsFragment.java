package tuandn.com.newsrss.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;
import tuandn.com.newsrss.DataForNewsPaper;
import tuandn.com.newsrss.MainActivity;
import tuandn.com.newsrss.R;
import tuandn.com.newsrss.ultilities.GlobalParams;
import tuandn.com.newsrss.ultilities.SharedPreferenceManager;
import tuandn.com.newsrss.vnexpress.ApiInterface;
import tuandn.com.newsrss.vnexpress.Channel;
import tuandn.com.newsrss.vnexpress.Item;
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
    private ProgressBar mProgress;
    private FrameLayout frameLayout;
    private WebView webView;
    private SharedPreferenceManager mPreferencee;
    private int k = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_news, container, false);
        mLvNew = (ListView) rootView.findViewById(android.R.id.list);

        mPreferencee = new SharedPreferenceManager(getContext());

        rss = new Rss();
        mProgress = (ProgressBar) rootView.findViewById(R.id.pbWaiting);
        mProgress.setVisibility(View.VISIBLE);
        mProgress.setProgress(100);

        frameLayout = (FrameLayout) rootView.findViewById(R.id.layout_list_news);
        webView = (WebView) rootView.findViewById(R.id.wv_news_detail);

        DataForNewsPaper dataForNewsPaper = DataForNewsPaper.getInstance();
        String newspaperName = dataForNewsPaper.getNewspaperName();

        position = getArguments().getInt("position");


        if (newspaperName.equals(MainActivity.VNEXPRESS)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://vnexpress.net")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface api = retrofit.create(ApiInterface.class);
            switch (position) {
                case NEWEST:
                    call = api.getNewestRssVnexpress();
                    break;
                case HEADLINE:
                    call = api.getHeadlineRssVnexpress();
                    break;
                case WORLD:
                    call = api.getWorldRssVnexpress();
                    break;
                case BUSINESS:
                    call = api.getBussinessRssVnexpress();
                    break;
                default:
                    call = api.getNewestRssVnexpress();
                    break;
            }

        } else if (newspaperName.equals(MainActivity.DANTRI)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://dantri.com.vn")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface api = retrofit.create(ApiInterface.class);
            switch (position) {
                case NEWEST:
                    call = api.getTrangchuDantri();
                    break;
                case HEADLINE:
                    call = api.getSuckhoeDantri();
                    break;
                case WORLD:
                    call = api.getXahoiDantri();
                    break;
                case BUSINESS:
                    call = api.getGiaitriDantri();
                    break;
                case 4:
                    call = api.getGiaoducDantri();
                    break;
                case 5:
                    call = api.getTheothaoDantri();
                    break;
                case 6:
                    call = api.getThegioiDantri();
                    break;
                case 7:
                    call = api.getKinhdoanhDantri();
                    break;
                default:
                    call = api.getTrangchuDantri();
                    break;
            }
        } else if (newspaperName.equals(MainActivity.ONLINE24H)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://24h.com.vn")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface api = retrofit.create(ApiInterface.class);
            switch (position) {
                case NEWEST:
                    call = api.getTintuc24h();
                    break;
                case HEADLINE:
                    call = api.getBongda24h();
                    break;
                case WORLD:
                    call = api.getAnninh24h();
                    break;
                case BUSINESS:
                    call = api.getThoitrang24h();
                    break;
                case 4:
                    call = api.getTaichinh24h();
                    break;
                case 5:
                    call = api.getThethao24h();
                    break;
                case 6:
                    call = api.getPhim24h();
                    break;
                default:
                    call = api.getTintuc24h();
                    break;
            }
        } else if (newspaperName.equals(MainActivity.VIETNAMNET)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://vietnamnet.vn/")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            ApiInterface api = retrofit.create(ApiInterface.class);
            switch (position) {
                case NEWEST:
                    call = api.getTrangchuVNN();
                    break;
                case HEADLINE:
                    call = api.getTinmoinongVNN();
                    break;
                case WORLD:
                    call = api.getTinnoibatVNN();
                    break;
                case BUSINESS:
                    call = api.getXahoiVNN();
                    break;
                case 4:
                    call = api.getGiaoducVNN();
                    break;
                case 5:
                    call = api.getChinhtriVNN();
                    break;
                default:
                    call = api.getTrangchuVNN();
                    break;
            }
        }


        // Asynchronous
        call.enqueue(new Callback<Rss>() {

            @Override
            public void onResponse(Response<Rss> response, Retrofit retrofit) {
                mProgress.setProgress(0);
                mProgress.setVisibility(View.INVISIBLE);
                if (response.isSuccess()) {
                    rss = response.body();
                    for (Item item : rss.getChannel().getItem()) {
                        int i = item.getDescription().indexOf("src") + 5;
                        int j = item.getDescription().indexOf(".jpg") + 4;
                        if (j == 3) {
                            j = item.getDescription().indexOf(".jpeg") + 5;
                            if (j == 4) {
                                j = item.getDescription().indexOf(".png") + 4;
                            }
                        }
                        String imageUrl = "";
                        if (i != 4 && j != 3) {
                            imageUrl = item.getDescription().substring(i, j);
                        }
                        item.setGuid(imageUrl);

                        i = item.getDescription().indexOf("</br>");
                        j = item.getDescription().indexOf("<br /");
                        String description = "";
                        if (i > 0) {
                            //News Description for VnExpress
                            description = item.getDescription().substring(i + 5, item.getDescription().length());
                        } else if (j > 0) {
                            //News Description for Vietnamnet
                            description = item.getDescription().substring(0, j);
                            description = description.replace("&amp;nbsp;", " ");
                        } else if (item.getDescription().length() > 0 && item.getDescription().indexOf("<a") != 0) {
                            //News Description for 24h
                            description = item.getDescription();
                        }
                        item.setDescription(description);
                    }
                    mAdapter = new ListNewsAdapter(getContext(), rss);
                    mLvNew.setAdapter(mAdapter);
                    mLvNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_LONG).show();
//                            frameLayout.setVisibility(View.INVISIBLE);
//                            webView.setVisibility(View.VISIBLE);
//                            webView.getSettings().setJavaScriptEnabled(true);
//                            webView.loadUrl(mAdapter.getItem(position).getLink());
                        }
                    });
                } else {
                    System.out.println("Loi: " + response.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgress.setProgress(0);
                mProgress.setVisibility(View.INVISIBLE);
                System.out.println("Loi: " + t.getMessage());
            }
        });

        if(!mPreferencee.getBoolean(GlobalParams.READING_NEWS,false)) {
            frameLayout.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }
        k=0;
        return rootView;
    }

    public static NewsFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public void onEvent(Rss response) {
        mAdapter = new ListNewsAdapter(getContext(), response);
        rss = new Rss();
        rss = response;
        mLvNew.setAdapter(mAdapter);
    }

    public void onEvent(String response) {
        if(k == 0) {
            frameLayout.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        } else {
            frameLayout.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }
        webView.clearView();
//        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.loadUrl(response);
        k = 1;
        mPreferencee.saveBoolean(GlobalParams.READING_NEWS, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}

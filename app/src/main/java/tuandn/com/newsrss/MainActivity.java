package tuandn.com.newsrss;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import de.greenrobot.event.EventBus;
import tuandn.com.newsrss.fragment.NewsFragmentAdapter;
import tuandn.com.newsrss.ultilities.GlobalParams;
import tuandn.com.newsrss.ultilities.SharedPreferenceManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String CATEGORY_VnExpress[] = {"Tin mới nhất","Thời sự", "Thế giới" ,"Kinh doanh"};
    public static String CATEGORY_Dantri[] = {"Trang chủ","Sức khỏe","Xã hội", "Giải trí", "Giáo dục", "Thể thao", "Thế giới", "Kinh doanh" };
    public static String CATEGORY_24h[] = {"Tin tức","Bóng đá","An ninh", "Thời trang", "Tài chính", "Thể thao", "Phim"};
    public static String CATEGORY_Vietnamnet[] = {"Trang chủ","Tin mới nóng", "Tin nổi bật" ,"Xã hội", "Giáo dục", "Chính trị"};

    public static String VNEXPRESS = "VNEXPRESS";
    public static String DANTRI = "DANTRI";
    public static String ONLINE24H = "ONLINE24H";
    public static String VIETNAMNET = "VIETNAMNET";

    private FragmentManager fragmentManager;
    private Fragment targetFragment;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView ivBanner;

    private WebView webView;

    private SharedPreferenceManager mPreferencee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ivBanner = (ImageView)  findViewById(R.id.banner);
        webView = (WebView)     findViewById(R.id.wv_news_detail);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        //Load VnExpress on Start
        DataForNewsPaper dataForNewsPaper = DataForNewsPaper.getInstance();
        dataForNewsPaper.setBanner(R.drawable.banner_vnexpress);
        dataForNewsPaper.setListCategory(CATEGORY_VnExpress);
        dataForNewsPaper.setNewspaperName(VNEXPRESS);
        ivBanner.setBackground(getResources().getDrawable(R.drawable.banner_vnexpress));
        viewPager.setAdapter(new NewsFragmentAdapter(getSupportFragmentManager(), this));
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        mPreferencee = new SharedPreferenceManager(MainActivity.this);
        mPreferencee.saveBoolean(GlobalParams.READING_NEWS, false);
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(mPreferencee.getBoolean(GlobalParams.READING_NEWS,false)) {
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
            mPreferencee.saveBoolean(GlobalParams.READING_NEWS, false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DataForNewsPaper dataForNewsPaper = DataForNewsPaper.getInstance();

        if (id == R.id.nav_vnexpress) {
            dataForNewsPaper.setBanner(R.drawable.banner_vnexpress);
            dataForNewsPaper.setListCategory(CATEGORY_VnExpress);
            dataForNewsPaper.setNewspaperName(VNEXPRESS);
            ivBanner.setBackground(getResources().getDrawable(R.drawable.banner_vnexpress));
        } else if (id == R.id.nav_dantri) {
            dataForNewsPaper.setBanner(R.drawable.banner_dantri);
            dataForNewsPaper.setListCategory(CATEGORY_Dantri);
            dataForNewsPaper.setNewspaperName(DANTRI);
            ivBanner.setBackground(getResources().getDrawable(R.drawable.banner_dantri));
        } else if (id == R.id.nav_24h) {
            dataForNewsPaper.setBanner(R.drawable.banner_24h);
            dataForNewsPaper.setListCategory(CATEGORY_24h);
            dataForNewsPaper.setNewspaperName(ONLINE24H);
            ivBanner.setBackground(getResources().getDrawable(R.drawable.banner_24h));
        } else if (id == R.id.nav_vietnamnet) {
            dataForNewsPaper.setBanner(R.drawable.banner_vietnamnet);
            dataForNewsPaper.setListCategory(CATEGORY_Vietnamnet);
            dataForNewsPaper.setNewspaperName(VIETNAMNET);
            ivBanner.setBackground(getResources().getDrawable(R.drawable.banner_vietnamnet));
        }

        viewPager.setAdapter(new NewsFragmentAdapter(getSupportFragmentManager(), this));
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPreferencee.saveBoolean(GlobalParams.READING_NEWS, false);
            }

            @Override
            public void onPageSelected(int position) {
                mPreferencee.saveBoolean(GlobalParams.READING_NEWS, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mPreferencee.saveBoolean(GlobalParams.READING_NEWS, false);
            }
        });
        mPreferencee.saveBoolean(GlobalParams.CHECK_NEWS, false);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onEvent(String response) {
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        mPreferencee.saveBoolean(GlobalParams.READING_NEWS, true);
        webView.clearView();
//        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.loadUrl(response);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}

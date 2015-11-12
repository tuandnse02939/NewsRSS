package tuandn.com.newsrss;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import tuandn.com.newsrss.fragment.NewsPaperFragment;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        targetFragment = new NewsPaperFragment();
        DataForNewsPaper dataForNewsPaper = DataForNewsPaper.getInstance();
        dataForNewsPaper.setBanner(R.drawable.banner_vnexpress);
        dataForNewsPaper.setListCategory(CATEGORY_VnExpress);
        dataForNewsPaper.setNewspaperName(VNEXPRESS);
        fragmentManager.beginTransaction().replace(R.id.layout_content, targetFragment).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            targetFragment = new NewsPaperFragment();
            dataForNewsPaper.setBanner(R.drawable.banner_vnexpress);
            dataForNewsPaper.setListCategory(CATEGORY_VnExpress);
            dataForNewsPaper.setNewspaperName(VNEXPRESS);
            fragmentManager.beginTransaction().replace(R.id.layout_content, targetFragment).commitAllowingStateLoss();
        } else if (id == R.id.nav_dantri) {
            targetFragment = new NewsPaperFragment();
            dataForNewsPaper.setBanner(R.drawable.banner_dantri);
            dataForNewsPaper.setListCategory(CATEGORY_Dantri);
            dataForNewsPaper.setNewspaperName(DANTRI);
            fragmentManager.beginTransaction().replace(R.id.layout_content, targetFragment).commitAllowingStateLoss();
        } else if (id == R.id.nav_24h) {
            targetFragment = new NewsPaperFragment();
            dataForNewsPaper.setBanner(R.drawable.banner_24h);
            dataForNewsPaper.setListCategory(CATEGORY_24h);
            dataForNewsPaper.setNewspaperName(ONLINE24H);
            fragmentManager.beginTransaction().replace(R.id.layout_content, targetFragment).commitAllowingStateLoss();
        } else if (id == R.id.nav_vietnamnet) {
            targetFragment = new NewsPaperFragment();
            dataForNewsPaper.setBanner(R.drawable.banner_vietnamnet);
            dataForNewsPaper.setListCategory(CATEGORY_Vietnamnet);
            dataForNewsPaper.setNewspaperName(VIETNAMNET);
            fragmentManager.beginTransaction().replace(R.id.layout_content, targetFragment).commitAllowingStateLoss();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

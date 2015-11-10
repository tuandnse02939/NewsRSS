package tuandn.com.newsrss.vnexpress;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Anh Trung on 10/29/2015.
 */
public interface ApiInterface{
    @GET("/rss/tin-moi-nhat.rss")
    Call<Rss> getNewestRss();

    @GET("/rss/thoi-su.rss")
    Call<Rss> getHeadlineRss();

    @GET("/rss/the-gioi.rss")
    Call<Rss> getWorldRss();

    @GET("/rss/kinh-doanh.rss")
    Call<Rss> getBussinessRss();
}
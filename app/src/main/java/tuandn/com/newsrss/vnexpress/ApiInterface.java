package tuandn.com.newsrss.vnexpress;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Anh Trung on 10/29/2015.
 */
public interface ApiInterface{
    @GET("/rss/tin-moi-nhat.rss")
    Call<Rss> getNewestRssVnexpress();

    @GET("/rss/thoi-su.rss")
    Call<Rss> getHeadlineRssVnexpress();

    @GET("/rss/the-gioi.rss")
    Call<Rss> getWorldRssVnexpress();

    @GET("/rss/kinh-doanh.rss")
    Call<Rss> getBussinessRssVnexpress();

    @GET("/trangchu.rss")
    Call<Rss> getTrangchuDantri();

    @GET("/suc-khoe.rss")
    Call<Rss> getSuckhoeDantri();

    @GET("/xa-hoi.rss")
    Call<Rss> getXahoiDantri();

    @GET("/giai-tri.rss")
    Call<Rss> getGiaitriDantri();

    @GET("/giao-duc-khuyen-hoc.rss")
    Call<Rss> getGiaoducDantri();

    @GET("/the-thao.rss")
    Call<Rss> getTheothaoDantri();

    @GET("/the-gioi.rss")
    Call<Rss> getThegioiDantri();

    @GET("/kinh-doanh.rss")
    Call<Rss> getKinhdoanhDantri();

}
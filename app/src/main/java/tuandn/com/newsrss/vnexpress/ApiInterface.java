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

    @GET("/upload/rss/tintuctrongngay.rss")
    Call<Rss> getTintuc24h();

    @GET("/upload/rss/bongda.rss")
    Call<Rss> getBongda24h();

    @GET("/upload/rss/anninhhinhsu.rss")
    Call<Rss> getAnninh24h();

    @GET("/upload/rss/thoitrang.rss")
    Call<Rss> getThoitrang24h();

    @GET("/upload/rss/taichinhbatdongsan.rss")
    Call<Rss> getTaichinh24h();

    @GET("/upload/rss/thethao.rss")
    Call<Rss> getThethao24h();

    @GET("/upload/rss/phim.rss")
    Call<Rss> getPhim24h();

    @GET("/rss/home.rss")
    Call<Rss> getTrangchuVNN();

    @GET("/rss/moi-nong.rss")
    Call<Rss> getTinmoinongVNN();

    @GET("/rss/tin-noi-bat.rss")
    Call<Rss> getTinnoibatVNN();

    @GET("/rss/xa-hoi.rss")
    Call<Rss> getXahoiVNN();

    @GET("/rss/giao-duc.rss")
    Call<Rss> getGiaoducVNN();

    @GET("/rss/chinh-tri.rss")
    Call<Rss> getChinhtriVNN();

}
package tuandn.com.newsrss;

/**
 * Created by Anh Trung on 11/3/2015.
 */
public class DataForNewsPaper {
    private int banner;
    private String[] listCategory;

    private static DataForNewsPaper instance;

    public DataForNewsPaper(int banner, String[] listCategory) {
        this.banner = banner;
        this.listCategory = listCategory;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public DataForNewsPaper(String[] listCategory) {
        this.listCategory = listCategory;
    }

    public String[] getListCategory() {
        return listCategory;
    }

    public void setListCategory(String[] listCategory) {
        this.listCategory = listCategory;
    }

    public static DataForNewsPaper getInstance() {
        if (null == instance) {
            instance = new DataForNewsPaper();
        }

        return instance;
    }


    public DataForNewsPaper() {
    }
}

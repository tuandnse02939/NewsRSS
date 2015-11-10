package tuandn.com.newsrss;

/**
 * Created by Anh Trung on 11/3/2015.
 */
public class DataForNewsPaper {
    private int banner;
    private String[] listCategory;
    private String newspaperName;

    private static DataForNewsPaper instance;

    public DataForNewsPaper(int banner, String[] listCategory, String name) {
        this.banner = banner;
        this.listCategory = listCategory;
        this.newspaperName = name;
    }

    public static DataForNewsPaper getInstance() {
        if (null == instance) {
            instance = new DataForNewsPaper();
        }

        return instance;
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

    public String getNewspaperName() {
        return newspaperName;
    }

    public void setNewspaperName(String newspaperName) {
        this.newspaperName = newspaperName;
    }

    public DataForNewsPaper() {
    }
}

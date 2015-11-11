package tuandn.com.newsrss.vnexpress;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Anh Trung on 11/2/2015.
 */
@Root(name = "item")
public class Item {
    @Element(name = "title", required=false)
    private String title;

    @Element(name = "description", required=false)
    private String description;

    @Element(name = "pubDate", required=false)
    private String pubDate;

    @Element(name = "link", required=false)
    private String link;

    @Element(name = "guid", required=false)
    private String guid;

    @Element(name = "slash", required=false)
    private String slash;

    @Element(name = "comments", required=false)
    private String comments;

    public Item() {}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }

    public String getGuid() {
        return guid;
    }

    public String getSlash() {
        return slash;
    }

    public String getComments() {
        return comments;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}

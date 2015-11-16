package tuandn.com.newsrss.vnexpress;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel")
public class Channel {

    @Attribute(name = "rel", required = false)
    private String rel;

    @Element(name = "title", required = false)
    private String title;

    @Element(name = "description", required = false)
    private String description;

    @Element(name = "ttl", required = false)
    private String ttl;

    @Element(name = "copyright", required = false)
    private String copyright;

    @Element(name = "docs", required = false)
    private String docs;

    @Element(required = false)
    private Image image;

    @Element(name = "pubDate", required = false)
    private String pubDate;

    @Element(name = "generator", required = false)
    private String generator;

    @Element(name = "language", required = false)
    private String language;

    @Element(name = "link", required = false)
    private String link;

    @ElementList(inline = true)
    private List<Item> item;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}

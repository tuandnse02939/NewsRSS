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

    @Element(name = "title")
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

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "generator")
    private String generator;

    @Element(name = "link")
    private String link;

    @ElementList(inline = true)
    private List<Item> item;

    public List<Item> getItem() {
        return item;
    }
}

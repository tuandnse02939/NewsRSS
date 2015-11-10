package tuandn.com.newsrss.vnexpress;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel")
public class Channel {
    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    @Element
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

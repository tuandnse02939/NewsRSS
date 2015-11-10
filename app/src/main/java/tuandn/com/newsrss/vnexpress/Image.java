package tuandn.com.newsrss.vnexpress;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Anh Trung on 11/2/2015.
 */
@Root(name = "image")
public class Image {

    @Element(name = "url")
    private String url;

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String link;

}

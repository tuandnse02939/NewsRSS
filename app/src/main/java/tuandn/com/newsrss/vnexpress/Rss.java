package tuandn.com.newsrss.vnexpress;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Anh Trung on 11/2/2015.
 */
@Root(name = "rss", strict=false)
public class Rss {

    @Element
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

}

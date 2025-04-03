import java.util.HashMap;
import java.util.Map;

import static com.sun.org.apache.xml.internal.serializer.Method.TEXT;

public abstract class SlideItemFactory
{
    public static final Map<String, SlideItemFactory> creators = new HashMap<>();

    static {
        creators.put(TEXT, new TextItemFactory());
        creators.put(XMLAccessor.IMAGE, new BitmapItemFactory());
    }
    public abstract SlideItem createSlideItem(int level, String content);
}

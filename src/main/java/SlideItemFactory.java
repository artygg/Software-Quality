import java.util.HashMap;
import java.util.Map;

public abstract class SlideItemFactory
{
    public static final String TEXT = "text";
    public static final Map<String, SlideItemFactory> creators = new HashMap<>();

    static {
        creators.put(TEXT, new TextItemFactory());
        creators.put(XMLAccessor.IMAGE, new BitmapItemFactory());
    }
    public abstract SlideItem createSlideItem(int level, String content);
}

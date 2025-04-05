import java.util.HashMap;
import java.util.Map;

public abstract class SlideItemFactory
{
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    
    private static final Map<String, SlideItemFactory> creators = new HashMap<>();

    static {
        registerCreator(TEXT, new TextItemFactory());
        registerCreator(IMAGE, new BitmapItemFactory());
    }
    public abstract SlideItem createSlideItem(int level, String content);

    public static void registerCreator(String type, SlideItemFactory factory) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        creators.put(type, factory);
    }


    public static SlideItem create(String type, int level, String content) {
        SlideItemFactory factory = creators.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown slide item type: " + type);
        }
        if (level < 0) {
            throw new IllegalArgumentException("Level cannot be negative");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        return factory.createSlideItem(level, content);
    }

    public static boolean isSupported(String type) {
        return creators.containsKey(type);
    }
}

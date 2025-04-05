
public class BitmapItemFactory extends SlideItemFactory
{
    public BitmapItemFactory()
    {
        super();
    }

    @Override
    public SlideItem createSlideItem(int level, String content){
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if (level < 0) {
            throw new IllegalArgumentException("Level cannot be negative");
        }
        return new BitmapItem(level, content);
    }
}

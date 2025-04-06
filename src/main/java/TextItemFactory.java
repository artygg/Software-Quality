
public class TextItemFactory extends SlideItemFactory
{
    @Override
    public SlideItem createSlideItem(int level, String content)
    {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if (level < 0) {
            throw new IllegalArgumentException("Level cannot be negative");
        }
        return new TextItem(level, content);
    }
}

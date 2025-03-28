public class BitmapItemFactory extends SlideItemFactory
{
    public BitmapItemFactory()
    {
        super();
    }

    @Override
    public SlideItem createSlideItem(int level, String content){
        return new BitmapItem(level, content);
    }
}

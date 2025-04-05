import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

/**
 * Tests for the Slide class.
 */
public class SlideTest {
    
    private Slide slide;
    
    @BeforeEach
    public void setUp() {
        slide = new Slide();
    }
    
    @Test
    public void testDefaultConstructor() {
        assertNotNull(slide, "Slide should be created");
        assertEquals("", slide.getTitle(), "Default title should be empty");
    }
    
    @Test
    public void testSetTitle() {
        slide.setTitle("Test Title");
        assertEquals("Test Title", slide.getTitle(), "Title should be set correctly");
    }
    
    @Test
    public void testAppendText() {
        slide.appendText(1, "Test Text");
        
        Vector<SlideItem> items = slide.getSlideItems();
        assertEquals(1, items.size(), "Slide should have 1 item");
        
        SlideItem item = items.elementAt(0);
        assertTrue(item instanceof TextItem, "Item should be a TextItem");
        assertEquals(1, item.getLevel(), "Item level should be 1");
        assertEquals("Test Text", ((TextItem) item).getText(), "Item text should be 'Test Text'");
    }
    
    @Test
    public void testAppendItem() {
        slide.appendItem("text", 1, "Test Text");
        
        Vector<SlideItem> items = slide.getSlideItems();
        assertEquals(1, items.size(), "Slide should have 1 item");
        
        SlideItem item = items.elementAt(0);
        assertTrue(item instanceof TextItem, "Item should be a TextItem");
        assertEquals(1, item.getLevel(), "Item level should be 1");
        assertEquals("Test Text", ((TextItem) item).getText(), "Item text should be 'Test Text'");
    }
    
    @Test
    public void testGetSlideItems() {
        slide.appendText(1, "Text 1");
        slide.appendText(2, "Text 2");
        
        Vector<SlideItem> items = slide.getSlideItems();
        assertEquals(2, items.size(), "Slide should have 2 items");
        
        SlideItem item1 = items.elementAt(0);
        SlideItem item2 = items.elementAt(1);
        
        assertTrue(item1 instanceof TextItem, "First item should be a TextItem");
        assertTrue(item2 instanceof TextItem, "Second item should be a TextItem");
        
        assertEquals(1, item1.getLevel(), "First item level should be 1");
        assertEquals(2, item2.getLevel(), "Second item level should be 2");
        
        assertEquals("Text 1", ((TextItem) item1).getText(), "First item text should be 'Text 1'");
        assertEquals("Text 2", ((TextItem) item2).getText(), "Second item text should be 'Text 2'");
    }
} 
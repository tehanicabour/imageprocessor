import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests for brightness and darkness.
 */
public class BrightnessTest {
  private ImageModel test;

  @Before
  public void setUp() throws IOException {

    test = new ImageModelImpl();
    test.loadPPM("res/Snail.ppm", "snail");
    test.loadPPM("res/SnailBrighter-By-50.ppm", "brighter-50");
    test.loadPPM("res/SnailDarker-By-50.ppm", "darker-50");

  }

  @Test
  public void testBrighterBy50() {
    test.brighten("snail", "brighter-50-transformed", 50);
    assertEquals(test.getImageRep("brighter-50"),
        test.getImageRep("brighter-50-transformed"));
  }

  @Test
  public void testDarkerBy50() {
    test.brighten("snail", "darker-50-transformed", -50);
    assertEquals(test.getImageRep("darker-50"),
        test.getImageRep("darker-50-transformed"));
  }

  @Test
  public void testClamping() {
    test.brighten("snail", "darker-50-transformed", 50);
    test.brighten("darker-50", "plus-minus-50", 50);
    assertNotEquals(test.getImageRep("plus-minus-50"), test.getImageRep("snail"));
  }
}

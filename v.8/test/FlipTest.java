import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for flips.
 */
public class FlipTest {
  private ImageModel test;

  @Before
  public void setUp() throws IOException {

    test = new ImageModelImpl();
    test.loadPPM("res/Snail.ppm", "snail");

  }

  @Test
  public void testHorizontalFlip() {
    test.loadPPM("res/SnailHorizontal.ppm", "snail-horizontal");
    test.horizontallyFlip("snail", "snail-horizontal-transformed");
    // test.savePPM("res/SnailHorizontalTransformed", "snail-horizontal-transformed");
    assertEquals(test.getImageRep("snail-horizontal"),
        test.getImageRep("snail-horizontal-transformed"));
  }

  @Test
  public void testVerticalFlip() {
    test.loadPPM("res/SnailVertical.ppm", "snail-vertical");
    test.verticallyFlip("snail", "snail-vertical-transformed");

    assertEquals(test.getImageRep("snail-vertical"),
        test.getImageRep("snail-vertical-transformed"));
  }

  @Test
  public void testVerticalHorizontalFlip() {
    test.loadPPM("res/SnailVerticalHorizontal.ppm", "snail-vertical-horizontal");
    test.horizontallyFlip("snail-vertical", "snail-vertical-horizontal-transformed");
    assertEquals(test.getImageRep("snail-vertical-horizontal"),
        test.getImageRep("snail-vertical-horizontal-transformed"));
  }

}

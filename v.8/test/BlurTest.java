import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the blur method on images.
 */
public class BlurTest {
  private ImageModel test;

  @Before
  public void setUp() throws IOException {
    test = new ImageModelImpl();
    test.loadPPM("res/Snail.ppm", "snail");
  }

  @Test
  public void testBlurOnce() {
    test.loadPPM("res/SnailBlur1.ppm", "SnailBlur");
    test.blur("snail", "SnailBlurTransformed");
    assertEquals(test.getImageRep("SnailBlur"),
            test.getImageRep("SnailBlurTransformed"));
  }

  @Test
  public void testBlurTwice() {
    test.loadPPM("res/SnailBlur2.ppm", "SnailBlurTwice");
    test.loadPPM("res/SnailBlur1.ppm", "SnailBlur");
    test.blur("SnailBlur", "SnailBlurTwiceTransformed");
    assertEquals(test.getImageRep("SnailBlurTwice"),
            test.getImageRep("SnailBlurTwiceTransformed"));
  }

  //blur non-ppm image
  @Test
  public void testBlurPNG() {
    test.loadPPM("res/SnailPNG.png", "SnailPNG");
    test.loadPPM("res/SnailBlur1.ppm", "SnailBlur");
    test.blur("SnailPNG", "SnailPNGBlur");
    assertEquals(test.getImageRep("SnailBlur"),
            test.getImageRep("SnailPNGBlur"));
  }

  //blur sharpened image and compare it to normal image
  @Test
  public void testBlurSharpened() {
    test.loadPPM("res/SnailBlur1.ppm", "SnailBlur");
    test.loadPPM("res/SnailSharpen1.ppm", "SnailSharpen");
    test.blur("SnailSharpen", "SnailBlurSharpened");
    assertEquals(test.getImageRep("snail"),
            test.getImageRep("SnailBlurSharpened"));
  }
}

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Sepia function on images.
 */
public class SepiaTest {

  private ImageModel test;

  @Before
  public void setUp() throws IOException {

    test = new ImageModelImpl();
    test.loadPPM("res/Snail.ppm", "snail");
    //test.loadPPM("res/SnailPNG.png", "SnailPNG");

  }

  //test sepia on ppm image
  @Test
  public void testSepiaPPM() {
    test.loadPPM("res/SnailSepia1.ppm", "SnailSepia");
    test.sepia("snail", "SnailSepiaTransformed");
    assertEquals(test.getImageRep("SnailSepia"),
            test.getImageRep("SnailSepiaTransformed"));
  }

  //test sepia on greyscale image (returns same image)
  @Test
  public void testSepiaGreyscale() {
    test.loadPPM("res/SnailLuma1.ppm", "SnailGreyScale");
    test.luma("snail", "snail-luma");
    test.sepia("snail-luma", "snail-luma-sepia");
    assertEquals(test.getImageRep("snail-luma-sepia"),
            test.getImageRep("SnailGreyScale"));
  }

  //test sepia on other format image
  @Test
  public void testSepiaPNG() {
    test.sepia("snail", "SnailSepiaPPMtoPNG");
    test.savePPM("res/SnailSepiaPPMtoPNG.png", "SnailSepiaPPMtoPNG");
    test.loadPPM("res/SnailSepiaPPMtoPNG.png", "SnailSepiaPNG");
    assertEquals(test.getImageRep("SnailSepiaPPMtoPNG"),
            test.getImageRep("SnailSepiaPNG"));
  }
}

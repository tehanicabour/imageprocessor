import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test for image model implementation.
 */
public class ImageModelImplTest {
  private ImageModel test;

  @Before
  public void setUp() throws IOException {

    test = new ImageModelImpl();

  }

  @Test
  public void testLoadingPPM() {
    test.loadPPM("res/TwoByTwo", "two-by-two");
    assertEquals(test.getImageRep("two-by-two"), "P3\n" +
                                                 "100 100\n"
                                                 + "255\n"
                                                 + "100 120 255\n"
                                                 + "200 100 255\n"
                                                 + "26 10 100");

  }

  @Test
  public void testSaveTransformedImage() {
    test.loadPPM("res/TwoByTwoBright", "two-by-two-bright");
    test.brighten("two-by-two", "two-by-two-brighter10", 10);
    test.savePPM("res/TwoByTwoPixels", "two-by-two-brighter10");
    assertEquals(test.getImageRep("two-by-two-bright"),
        test.getImageRep("two-by-two-brighter10"));
    assertEquals(test.getImageRep("two-by-two-brighter10"),
        "P3\n" +
        "110 110\n" +
        "255\n" +
        "110 130 255\n" +
        "210 110 255\n" +
        "36 20 110");
  }

}

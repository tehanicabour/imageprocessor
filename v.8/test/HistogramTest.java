import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;

/**
 * Tests the that histograms accurately visualize distributions: red, green, blue, and intensity.
 */
public class HistogramTest {
  private ImageModel test;

  @Before
  public void setUp() throws IOException {

    test = new ImageModelImpl();
    test.loadPPM("res/Snail.ppm", "snail");

  }

  //test for visualizing distribution of red on histogram
  @Test
  public void testRedDistribution() {
    int[] distributionReds = new int[256];
    test.loadPPM("res/green.png", "green");
    assertEquals(test.getImage("green").getHistogram("red"),
            distributionReds);
    test.loadPPM("res/red.png", "red");
    int[] histogramRedPNG = test.getImage("red").getHistogram("red");
    int sizeOfRedImage = test.getImage("red").getWidth() * test.getImage("red").getHeight();
    assertEquals(histogramRedPNG[255], sizeOfRedImage);
  }

  //test for visualizing distribution of blue on histogram
  @Test
  public void testBlueDistribution() {
    int[] distributionBlues = new int[256];
    test.loadPPM("res/red.png", "red");
    assertEquals(test.getImage("red").getHistogram("blue"),
            distributionBlues);
    test.loadPPM("res/blue.png", "blue");
    int[] histogramBluePNG = test.getImage("blue").getHistogram("blue");
    int sizeOfBlueImage = test.getImage("blue").getWidth()
            * test.getImage("blue").getHeight();
    assertEquals(histogramBluePNG[255], sizeOfBlueImage);
  }

  //test for visualizing distribution of green on histogram
  @Test
  public void testGreenDistribution() {
    int[] distributionGreens = new int[256];
    test.loadPPM("res/red.jpg", "red");
    assertEquals(test.getImage("red").getHistogram("green"),
            distributionGreens);
    test.loadPPM("res/green.png", "green");
    int[] histogramGreenPNG = test.getImage("green").getHistogram("green");
    int sizeOfGreenImage = test.getImage("green").getWidth()
            * test.getImage("green").getHeight();
    assertEquals(histogramGreenPNG[203], sizeOfGreenImage);
  }

  //test for visualizing distribution of intensity on histogram
  @Test
  public void testIntensityDistribution() {
    int[] distributionIntensity = new int[256];
    test.loadPPM("res/red.jpg", "red");
    assertEquals(test.getImage("red").getHistogram("intensity"),
            distributionIntensity);
    test.loadPPM("res/grey.png", "grey");
    int[] histogramGreenPNG = test.getImage("grey").getHistogram("intensity");
    int sizeOfGreyImage = test.getImage("grey").getWidth()
            * test.getImage("grey").getHeight();
    assertEquals(histogramGreenPNG[100], sizeOfGreyImage);
  }

}



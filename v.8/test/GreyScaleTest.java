import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;

/**
 * Tests for greyscale.
 */
public class GreyScaleTest {

  private ImageModel test;

  @Before
  public void setUp() throws IOException {

    test = new ImageModelImpl();
    test.loadPPM("res/Snail.ppm", "snail");

  }

  //test for visualizing red component on color image
  @Test
  public void testRedComponent() {
    test.loadPPM("res/SnailGreyScaleRed1.ppm", "red-grey-scale");
    test.loadPPM("res/SnailPNG.png", "SnailPNG");
    test.loadPPM("res/SnailGreyScaleRedPNG.png", "red-grey-scale-png");
    test.redComponent("snail", "red-grey-snail-transformed");
    test.redComponent("SnailPNG", "red-grey-snail-transformed-png");
    assertEquals(test.getImageRep("red-grey-scale"),
        test.getImageRep("red-grey-snail-transformed"));
    assertEquals(test.getImageRep("red-grey-scale-png"),
            test.getImageRep("red-grey-snail-transformed-png"));
  }

  //test for visualizing green component on color image
  @Test
  public void testGreenComponent() {
    test.loadPPM("res/SnailGreenGreyScale.ppm", "green-grey-scale");
    test.greenComponent("snail", "green-grey-snail-transformed");
    assertEquals(test.getImageRep("green-grey-scale"),
        test.getImageRep("green-grey-snail-transformed"));
  }

  //test for visualizing blue component on color image
  @Test
  public void testBlueComponent() {
    test.loadPPM("res/SnailBlueGreyScale.ppm", "blue-grey-scale");
    test.blueComponent("snail", "blue-grey-snail-transformed");
    assertEquals(test.getImageRep("blue-grey-scale"),
        test.getImageRep("blue-grey-snail-transformed"));
  }


  //test where red is max value component
  @Test
  public void testMaxValueComponent() {
    test.loadPPM("res/SnailMaxValueComponent.ppm", "max-value-component");
    test.maxValue("snail", "max-value-grey-snail-transformed");
    test.savePPM("res/SnailMaxValueComponentTransformed",
        "max-value-grey-snail-transformed");
    assertEquals(test.getImageRep("max-value-component"),
        test.getImageRep("max-value-grey-snail-transformed"));
    assertEquals(test.getImageRep("blue-grey-scale"),
        test.getImageRep("max-value-grey-snail-transformed"));
  }

  //TESTS FOR INTENSITY:
  @Test
  public void testIntensity() {
    test.loadPPM("res/SnailIntensity.ppm", "snail-intensity");
    test.intensity("snail", "snail-intensity-transformed");
    assertEquals(test.getImageRep("snail-intensity"),
        test.getImageRep("snail-intensity-transformed"));
  }


  //TESTS FOR LUMA:
  @Test
  public void testLuma() {
    test.loadPPM("res/SnailLuma.ppm", "snail-luma");
    test.luma("snail", "snail-luma-transformed");

    assertEquals(test.getImageRep("snail-luma"),
        test.getImageRep("snail-luma-transformed"));


    assertEquals(test.getImageRep("snail-luma"),
        test.getImageRep("snail-luma-transformed"));
  }

}

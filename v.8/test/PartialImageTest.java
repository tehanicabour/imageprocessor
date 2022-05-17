import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

/**
 * Tests for PartialImageManipulation functionality.
 */
public class PartialImageTest {

  private ImageModel test;

  @Before
  public void setUp() throws IOException {

    test = new ImageModelImpl();
    test.loadPPM("res/mushroom.jpg", "mushroom");
    test.loadPPM("res/mask-half.png", "mask-half");

  }

  @Test
  public void testPartial() {
    test.redComponent("mushroom", "mushroom-red-component");
    test.partialImageManipulation("mushroom", "mushroom-half-bw",
            "mask-half", "red-component");
    ArrayList<ArrayList<Pixel>> original = test.getImage("mushroom").getPixels();
    ArrayList<ArrayList<Pixel>> halfBW = test.getImage("mushroom-half-bw").getPixels();
    ArrayList<ArrayList<Pixel>> mask = test.getImage("mask-half").getPixels();
    ArrayList<ArrayList<Pixel>> transformedFull
            = test.getImage("mushroom-red-component").getPixels();

    int black = 0;
    int transformedPixels = 0;

    for (int i = 0; i < halfBW.size(); i++) {
      for (int j = 0; j < halfBW.get(0).size(); j++) {
        if (mask.get(i).get(j).getRed() + mask.get(i).get(j).getGreen()
                + mask.get(i).get(j).getBlue() == 0) {
          black++;
        }
        if (halfBW.get(i).get(j).getRed() == transformedFull.get(i).get(j).getRed()
                && halfBW.get(i).get(j).getGreen() == transformedFull.get(i).get(j).getGreen()
                && halfBW.get(i).get(j).getBlue() == transformedFull.get(i).get(j).getBlue()) {
          transformedPixels++;
        }
        if (original.get(i).get(j).getRed() == transformedFull.get(i).get(j).getRed()
                && original.get(i).get(j).getGreen() == transformedFull.get(i).get(j).getGreen()
                && original.get(i).get(j).getBlue() == transformedFull.get(i).get(j).getBlue()) {
          transformedPixels--;
        }
      }
    }
    assertEquals(black, transformedPixels);
  }
}

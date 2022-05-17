import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for downscaling.
 */
public class DownScaleTest {
  private ImageModel test;

  @Before
  public void setUp() throws IOException {

    test = new ImageModelImpl();
    test.loadPPM("res/catan.jpg", "catan");

  }

  @Test
  public void testDownscaling() {
    test.downscale("catan", "catan-downscale-50-60", 50, 60);
    assertEquals(50, test.getImage("catan-downscale-50-60").getWidth());
    assertEquals(60, test.getImage("catan-downscale-50-60").getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownscalingError() {
    test.downscale("catan", "catan-downscale-300-60", 300, 60);
  }
}

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import model.ImageModel;
import model.ImageModelImpl;

/**
 * Tests for saving images of multiple formats.
 */
public class SavePPMTest {
  private ImageModel test;

  @Before
  public void setUp() throws IOException {
    test = new ImageModelImpl();
  }

  //test for saving ppm as each image format
  @Test
  public void savePPMAsAllFileFormats() {
    test.loadPPM("res/Snail.ppm", "Snail-PNG");
    test.savePPM("res/Snail-PNG.png", "Snail-PNG");

    test.loadPPM("res/Snail.ppm", "Snail-JPEG");
    test.savePPM("res/Snail-JPEG.jpg", "Snail-JPEG");

    test.loadPPM("res/Snail.ppm", "Snail-BMP");
    test.savePPM("res/Snail-BMP.bmp", "Snail-BMP");
    test.loadPPM("res/Snail-BMP.bmp", "Snail-BMP-Loaded");

    assertEquals(test.getImageRep("Snail-BMP-Loaded"), test.getImageRep("Snail"));
  }

  //test to save any image format as a ppm
  @Test
  public void saveAllFileFormatsAsPPM() {
    test.loadPPM("res/SnailPNG.png", "SnailPNG-PPM");
    test.savePPM("res/SnailPNG-PPM.ppm", "SnailPNG-PPM");

    test.loadPPM("res/SnailJPEG.ppm", "SnailJPEG-PPM");
    test.savePPM("res/SnailJPEG-PPM.ppm", "SnailJPEG-PPM");

    test.loadPPM("res/SnailBMP.bmp", "SnailBMP-PPM");
    test.savePPM("res/SnailBMP-PPM.ppm", "SnailBMP-PPM");
    test.loadPPM("res/SnailBMP-PPM.ppm", "SnailBMP-PPM-Loaded");

    assertEquals(test.getImageRep("SnailBMP-PPM-Loaded"), test.getImageRep("Snail"));
  }

  //test that throws exception when file already exists
  @Test(expected = IllegalArgumentException.class)
  public void saveExistentFile() {
    test.loadPPM("res/Snail.ppm", "Snail");
    test.savePPM("res/Snail.ppm", "Snail");
  }

  //test that throws exception when filePath doesn't contain imageName
  @Test(expected = IllegalArgumentException.class)
  public void mismatchNames() {
    test.loadPPM("res/Snail.ppm", "Snail");
    test.luma("Snail", "SnailLumaTransform");
    test.savePPM("res/randomName.ppm", "SnailLumaTransform");
  }
}

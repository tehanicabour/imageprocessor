import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import model.ImageModel;
import model.ImageModelImpl;


/**
 * Tests to load images via loadPPM.
 */
public class LoadPPMTest {

  private ImageModel test;

  @Before
  public void setUp() throws IOException {
    test = new ImageModelImpl();
  }

  //test that loadPPM throws exception when file does not exist
  @Test(expected = IllegalArgumentException.class)
  public void nonExistentFileTest() {
    test.loadPPM("res/giraffe.ppm","giraffe");
    test.loadPPM("res/giraffe.png","giraffe");
    test.loadPPM("res/giraffe.jpg","giraffe");
    test.loadPPM("res/giraffe.bmp","giraffe");
  }

  //test that loadPPM throws exception when image not in hashmap
  @Test(expected = NullPointerException.class)
  public void nonExistentImage() {
    test.loadPPM("res/Snail.ppm","Snail");
    test.brighten("NonExistent", "NonExistentBright", 50);
  }

  //load all file formats
  @Test
  public void loadAllFileFormats() {
    test.loadPPM("res/Snail.ppm","SnailPPM");
    test.loadPPM("res/SnailPNG.png","SnailPNG");
    test.loadPPM("res/SnailJPEG.jpg","SnailJPEG");
    test.loadPPM("res/SnailBMP.bmp","SnailBMP");

    assertEquals(test.getImageRep("SnailPPM"), test.getImageRep("SnailPNG"));
    assertEquals(test.getImageRep("SnailPPM"), test.getImageRep("SnailJPEG"));
    assertEquals(test.getImageRep("SnailPPM"), test.getImageRep("SnailBMP"));
  }



}

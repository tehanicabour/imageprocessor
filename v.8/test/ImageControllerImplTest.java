import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for image controller implementation.
 */
public class ImageControllerImplTest {
  private ImageModel testModel;
  private ImageView testView;

  @Before
  public void setUp() throws IOException {
    testModel = new ImageModelImpl();
    testView = new ImageViewImpl(testModel);
    testModel.loadPPM("res/Snail.ppm", "snail");
  }

  //test for null model throw exception
  @Test(expected = IllegalArgumentException.class)
  public void nullModel() {
    Reader in = new StringReader("vertical-flip snail snail-vertical");
    ImageController testController = new ImageControllerImpl(null, testView,
            in);
  }

  //test for invalid command
  @Test(expected = IllegalArgumentException.class)
  public void invalidEnteredCommand() {
    Reader in = new StringReader("vertical-flippp snail snail-vertical");
    ImageController testController = new ImageControllerImpl(testModel, testView,
            in);
  }

  //test for non-existent file
  @Test(expected = IllegalArgumentException.class)
  public void nonExistentFile() {
    Reader in = new StringReader("-flip nonExist.txt");
    ImageController testController = new ImageControllerImpl(testModel, testView,
            in);
  }

  //test for a valid file input
  @Test
  public void readValidFile() {
    try {
      testModel.luma("snail", "snail-luma");
      ImageModel testModelScript = new ImageModelImpl();
      ImageView testViewScript = new ImageViewImpl(testModelScript);
      Reader testInput = new FileReader("res/testScriptCommand.txt");
      ImageController testControllerScript = new ImageControllerImpl(testModelScript,
              testViewScript, testInput);

      assertEquals(testModelScript.getImageRep("snail-luma-script"),
              testModel.getImageRep("snail-luma"));
    } catch (IOException io) {
      throw new IllegalArgumentException();
    }
  }

  @Test
  public void readValidFile2() {
    try {
      testModel.luma("snail", "snail-luma");

      ImageModel testModelScript = new ImageModelImpl();
      ImageView testViewScript = new ImageViewImpl(testModelScript);
      Reader testInput = new FileReader("res/testScriptCommand.txt");
      ImageController testControllerScript = new ImageControllerImpl(testModelScript,
              testViewScript, testInput);
      testControllerScript.beginRead();
      testModel.loadPPM("res/snail-luma-script.ppm", "snail-luma-script");

      assertEquals(testModelScript.getImageRep("snail-luma-script"),
              testModel.getImageRep("snail-luma"));
    } catch (IOException io) {
      throw new IllegalArgumentException("nope");
    }
  }


  @Test
  public void validFile() {
    Reader in = new StringReader("luma snail snail-luma");
    ImageController testController = new ImageControllerImpl(testModel, testView,
            in);
    testModel.luma("snail", "snail-luma");

    ImageModel testModelScript = new ImageModelImpl();
    ImageView testViewScript = new ImageViewImpl(testModelScript);
    Reader inFile = new StringReader("-file testScriptCommand.txt");
    ImageController testControllerScript = new ImageControllerImpl(testModelScript, testViewScript,
            inFile);

    assertEquals(testModel.getImageRep("snail-luma"),
            testModelScript.getImageRep("snail-luma-script"));

  }

  @Test
  public void validFileAfterCommand() {
    Reader in = new StringReader("luma snail snail-luma");
    ImageController testController = new ImageControllerImpl(testModel, testView,
            in);
    testModel.luma("snail", "snail-luma");
    in = new StringReader("-file testScriptCommand.txt");
    assertEquals(testModel.getImageRep("snail-luma"),
            testModel.getImageRep("snail-luma-script"));

  }


}

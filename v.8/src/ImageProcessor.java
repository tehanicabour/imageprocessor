import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.GUIControllerImpl;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.GUIViewImpl;
import view.ImageView;
import view.ImageViewImpl;


/**
 * Represents the runner class for an Image Processor where a user
 * can input commands to modify and process images.
 */

public class ImageProcessor {

  /**
   * Runs the Image Processor and allows the user to interact with the Image Processor.
   *
   * @param args the arguments a user can input to interact with
   *             the Image Processor
   */
  public static void main(String[] args) {
    ImageModel imageModel = new ImageModelImpl();

    if (args.length == 0) {
      GUIControllerImpl guiController = new GUIControllerImpl(imageModel,
              new GUIViewImpl(imageModel));
      guiController.beginRead();
      return;
    }
    if (args[0].equals("-file")) {
      try {
        System.out.println("Searching file...");
        ImageView view = new ImageViewImpl(imageModel);
        new ImageControllerImpl(imageModel, view,
                new FileReader(args[1])).beginRead();
      } catch (IOException io) {
        System.out.println("File not found.");
        return;
      }
    }
    if (args[0].equals("-text")) {
      ImageView view = new ImageViewImpl(imageModel);
      new ImageControllerImpl(imageModel,
              view, new InputStreamReader(System.in)).beginRead();
    }
  }
}

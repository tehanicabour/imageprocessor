import java.io.IOException;
import java.util.Objects;

import controller.ImageController;
import view.Listener;

/**
 * Creates a controller of the mock for a GUI. Mock is created in order to test whether
 * emitting methods and listeners successfully communicate between the view and controller.
 */
public class MockGUIController implements ImageController, Listener {

  private final Appendable out;

  /**
   * Constructs and instance of the MockGUIController. It takes in a model that it controls and a
   * view (GUI) that it listens to. It sets itself as a listener for the inputted GUI.
   *
   * @param out visualizes message
   **/
  public MockGUIController(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  /**
   * Begins reading commands to the controller. Images from the model are modified as dictated
   * by the given commands.
   */
  @Override
  public String beginRead() {
    return "MockGUI initiated";
  }

  @Override
  public void applyTransformationListen(String transformation, String id,
                                        String newID, int field1, int field2) {
    try {
      out.append(transformation);
    } catch (IOException e) {
      throw new IllegalArgumentException("apply transformation unsuccessful");
    }
  }


  @Override
  public void loadImageGUI(String filename, String id) {
    try {
      out.append(filename + " saved as " + id);
    } catch (IOException e) {
      throw new IllegalArgumentException("loading image unsuccessful");
    }
  }


  @Override
  public void saveImageGUI(String filename, String fileType) {
    try {
      out.append(filename + " saved as a " + fileType);
    } catch (IOException e) {
      throw new IllegalArgumentException("saving image unsuccessful");
    }
  }
}

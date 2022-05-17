import java.io.IOException;
import java.util.ArrayList;

import view.GUIView;
import view.Listener;

/**
 * Creates a view of the mock for a GUI. Mock is created in order to test whether emitting methods
 * and listeners successfully communicate between the view and controller.
 */
public class MockGUIView implements GUIView {
  private final ArrayList<Listener> listener = new ArrayList<>();

  @Override
  public void makeVisible(boolean b) {
    return;
  }

  @Override
  public void addListener(Listener actionListener) {
    this.listener.add(actionListener);
  }

  @Override
  public void renderMessage(String message) {
    return;
  }

  @Override
  public void renderImageRep(String id) throws IOException {
    return;
  }

  /**
   * Copy of emitTransformationEvent method in the GUIView class.
   *
   * @param transformation on image
   * @param id             of current/given image
   * @param newID          of destination image key
   * @param field1         of transformation
   * @param field2         of transformation
   */
  public void emitTransformationEvent(String transformation, String id,
                                      String newID, int field1, int field2) {
    for (Listener l : this.listener) {
      l.applyTransformationListen(transformation, id, newID, field1, field2);
    }
  }

  /**
   * Copy of emitLoadImageEvent method in the GUIView class.
   *
   * @param path path of the image to be imported
   * @param id   id the image will be saved as
   */
  public void emitLoadImageEvent(String path, String id) {
    for (Listener l : this.listener) {
      l.loadImageGUI(path, id);
    }
  }

  /**
   * Copy of emitSaveImageEvent method in the GUIView class.
   *
   * @param path filepath of where to save
   * @param id   image id
   */
  public void emitSaveImageEvent(String path, String id) {
    for (Listener l : this.listener) {
      l.saveImageGUI(path, id);
    }
  }
}

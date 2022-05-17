package view;

/**
 * Interface for GUIView. Representation of a user interactive view of the model,
 * which controller listens to when acting on model.
 */
public interface GUIView extends ImageView {

  /**
   * Sets visibility of the model's GUI view.
   * @param b true if visible
   */
  void makeVisible(boolean b);

  /**
   * Adds a view listener to this GUIView.
   * @param actionListener the action listener to be added to the GUI
   */
  void addListener(Listener actionListener);

}

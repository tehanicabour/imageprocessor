package view;

/**
 * This interface represents the listener of the view and communicates between the view and the
 * controller.
 */
public interface Listener {

  /**
   * Applies the specified transformation.
   *
   * @param transformation this string indicates what transformation the user wants
   */
  void applyTransformationListen(String transformation, String id, String newID,
                                 int field1, int field2);

  /**
   * Imports a folder with the given name and stores it with the given id.
   *
   * @param filename name of the folder
   * @param id       id to be stored as
   */
  void loadImageGUI(String filename, String id);

  /**
   * Saves the entire image in a folder.
   *
   * @param filename the name of the saved folder
   * @param fileType the type of the saved folder
   */
  void saveImageGUI(String filename, String fileType);

}

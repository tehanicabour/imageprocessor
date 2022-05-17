package controller;

/**
 * Interface for controller classes that takes in user inputs.
 */
public interface ImageController {

  /**
   * Begins reading commands to the controller. Images from the model are modified as dictated
   * by the given commands.
   */
  String beginRead();
}

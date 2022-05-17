package view;

import java.io.IOException;

/**
 * The interface for the rendering of a view for the model. Visualizes messages and images.
 */
public interface ImageView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message);


  /**
   * Render the desired image given a key to the provided data destination.
   *
   * @param id the key of the desired image to be represented
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderImageRep(String id) throws IOException;


}
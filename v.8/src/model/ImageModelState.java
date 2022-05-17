package model;

/**
 * This interface represents operations that can be used to monitor the state of an image model,
 * without changing it.
 */
public interface ImageModelState {

  /**
   * Returns the text representation of the given image in the modelstate.
   *
   * @param id the key of the desired image to be represented
   * @return a string that represents an image with the give name
   */
  String getImageRep(String id);
}

package view;

import model.ImageModelState;

import java.io.IOException;


/**
 * Represents a simple implenting class of the view of a Image Processor.
 */
public class ImageViewImpl implements ImageView {

  private ImageModelState imageModelState;
  private Appendable appendable;

  /**
   * Constructs a view for an Image Processor given the state of the
   * Image Processor's model.
   *
   * @param imageModelState the immutable state of the Image Processor's model
   */
  public ImageViewImpl(ImageModelState imageModelState) throws IllegalArgumentException {

    if (imageModelState == null) {
      throw new IllegalArgumentException("The provided model state cannot be null.");
    }

    this.imageModelState = imageModelState;
    this.appendable = System.out;
  }


  /**
   * Constructs a view for an Image Processor given the state of the
   * Image Processor's model.
   *
   * @param imageModelState imageModelState the immutable state of the Image Processor's model
   * @param appendable      display added onto the control panel
   * @throws IllegalArgumentException if any parameter is null
   */
  public ImageViewImpl(ImageModelState imageModelState, Appendable appendable)
      throws IllegalArgumentException {
    if (imageModelState == null || appendable == null) {
      throw new IllegalArgumentException("The provided model state or appendable cannot be null.");
    }
    this.imageModelState = imageModelState;
    this.appendable = appendable;
  }


  @Override
  public void renderMessage(String message) {
    try {
      this.appendable.append(message).append("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void renderImageRep(String id) throws IOException {
    this.appendable.append(this.imageModelState.getImageRep(id));
  }
}

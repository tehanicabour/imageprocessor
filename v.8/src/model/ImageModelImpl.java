package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Represent the model for an Image Processing and Enhancement application. Here the user can
 * transform, load, and save images.
 */
public class ImageModelImpl implements ImageModel {

  private final Map<String, Image> imageMap;

  /**
   * Constructs an instance of the model for an Image Processor.
   */
  public ImageModelImpl() {
    this.imageMap = new HashMap<String, Image>();
  }

  @Override
  public Image getImage(String id) {

    return this.imageMap.get(id);
  }

  @Override
  public void maxValue(String id, String dest) {
    actionHelper(getImage(id), dest, Image::getValue);
  }

  @Override
  public void intensity(String id, String dest) {
    actionHelper(getImage(id), dest, Image::getIntensity);
  }

  @Override
  public void luma(String id, String dest) {
    actionHelper(getImage(id), dest, Image::getLuma);
  }

  @Override
  public void redComponent(String id, String dest) {
    actionHelper(getImage(id), dest, Image::getRedComponent);
  }

  @Override
  public void greenComponent(String id, String dest) {
    actionHelper(getImage(id), dest, Image::getGreenComponent);
  }

  @Override
  public void blueComponent(String id, String dest) {
    actionHelper(getImage(id), dest, Image::getBlueComponent);
  }

  @Override
  public void brighten(String id, String dest, int change) {
    actionHelper(getImage(id), dest, image -> image.brighten(change));
  }

  protected void actionHelper(Image image, String dest, Function<Image, Image> action) {
    this.imageMap.put(dest, action.apply(image));
  }

  @Override
  public void verticallyFlip(String id, String dest) {
    this.actionHelper(getImage(id), dest, Image::flipVertical);
  }

  @Override
  public void horizontallyFlip(String id, String dest) {
    this.actionHelper(getImage(id), dest, Image::flipHorizontal);
  }

  @Override
  public void loadPPM(String filePath, String imageName) {
    imageMap.put(imageName, ImageUtil.loadPPM(filePath));

  }

  @Override
  public void savePPM(String filePath, String imageName) {
    if (!(filePath.contains(imageName))) {
      throw new IllegalArgumentException();
    } else {
      System.out.println(imageMap.containsKey(imageName));
      imageMap.get(imageName).savePPM(filePath);
    }
  }

  @Override
  public void sepia(String id, String dest) {
    actionHelper(getImage(id), dest, Image::sepia);
  }


  @Override
  public void sharpen(String id, String dest) {
    actionHelper(getImage(id), dest, Image::sharpen);
  }


  @Override
  public void blur(String id, String dest) {
    actionHelper(getImage(id), dest, Image::blur);
  }

  @Override
  public void downscale(String id, String dest, int width, int height) {
    actionHelper(getImage(id), dest, image -> image.downscale(width, height));
  }

  @Override
  public void partialImageManipulation(String id, String dest, String mask, String func) {
    actionHelper(getImage(id), dest, image -> image.partialImageManipulation(getImage(mask),
            getFunction(func, getImage(id))));
  }

  /**
   * Returns image with performed function given user's string input.
   * @param func String of desired operation.
   * @param img id of image to transform.
   * @return Image with given transformtion.
   */
  protected Image getFunction(String func, Image img) {
    switch (func) {
      case "value": img.getValue();
        break;
      case "intensity":
        return img.getIntensity();
      case "luma":
        return img.getLuma();
      case "red-component":
        return img.getRedComponent();
      case "green-component":
        return img.getGreenComponent();
      case "blue-component":
        return img.getBlueComponent();
      case "sepia":
        return img.sepia();
      case "blur":
        return img.blur();
      case "sharpen":
        return img.sharpen();
      default: return img;
    }
    return img;
  }

  @Override
  public String getImageRep(String id) {
    return getImage(id).toString();
  }
}


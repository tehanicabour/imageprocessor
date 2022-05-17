package model;

import model.filter.Filter;
import model.filter.FilterImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Represents and abstracts all variant implementations of an Image.
 */
public abstract class AbstractImage implements Image {

  protected final ArrayList<ArrayList<Pixel>> pixels;

  /**
   * Constructs and abstracts all variant class for an Image given a 2D array-list of pixels.
   *
   * @param pixels represents a matrix of pixels that make up an Image
   */
  public AbstractImage(ArrayList<ArrayList<Pixel>> pixels)
          throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("Image cannot have null pixels");
    }
    this.pixels = pixels;
  }

  @Override
  public Image getValue() {
    return imageHelper(Pixel::getValue);
  }

  @Override
  public Image getIntensity() {
    return imageHelper(Pixel::getIntensity);
  }

  @Override
  public Image getLuma() {
    return imageHelper(Pixel::getLuma);
  }

  @Override
  public Image getRedComponent() {
    return imageHelper(Pixel::getRedComponent);
  }

  @Override
  public Image getGreenComponent() {
    return imageHelper(Pixel::getGreenComponent);
  }

  @Override
  public Image getBlueComponent() {
    return imageHelper(Pixel::getBlueComponent);
  }

  @Override
  public Image brighten(int change) {
    return imageHelper(pixel -> pixel.brighten(change));
  }


  @Override
  public Image sepia() {
    return imageHelper(Pixel::sepia);
  }

  @Override
  public Image savePPM(String filePath) throws IllegalArgumentException {
    File file;
    FileOutputStream stream;

    try {
      file = new File(filePath);
      if (!file.createNewFile()) {
        throw new IllegalArgumentException("The file already exists.");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot create file.");
    }

    try {
      stream = new FileOutputStream(file);
      stream.write(this.getPPMString().getBytes(StandardCharsets.UTF_8));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to write to file.");
    }
    return createImage(pixels);
  }

  protected String getPPMString() {
    //any risk of aliasing here?
    StringBuilder result = new StringBuilder();
    result.append("P3\n").append(pixels.get(0).size())
            .append(" ").append(pixels.size())
            .append("\n")
            .append("255\n");

    for (ArrayList<Pixel> pixel : pixels) {
      for (Pixel p : pixel) {
        result.append(p.getRed()).append(" ")
                .append(p.getGreen()).append(" ")
                .append(p.getBlue()).append("\n");
      }
    }
    return result.toString();
  }

  @Override
  public Image flipVertical() {
    ArrayList<ArrayList<Pixel>> newPixels = new ArrayList<ArrayList<Pixel>>();

    for (int row = 0; row < pixels.size(); row++) {
      ArrayList<Pixel> pRow = new ArrayList<Pixel>();
      for (int col = 0; col < pixels.get(0).size(); col++) {
        pRow.add(pixels.get(pixels.size() - 1 - row).get(col));
      }
      newPixels.add(pRow);
    }

    return createImage(newPixels);

  }

  @Override
  public Image flipHorizontal() {
    ArrayList<ArrayList<Pixel>> newPixels = new ArrayList<ArrayList<Pixel>>();

    for (ArrayList<Pixel> pixel : pixels) {
      ArrayList<Pixel> pRow = new ArrayList<Pixel>();
      for (int col = 0; col < pixels.get(0).size(); col++) {
        pRow.add(pixel.get(pixels.get(0).size() - 1 - col));
      }
      newPixels.add(pRow);
    }

    return createImage(newPixels);
  }


  protected Image imageHelper(Function<Pixel, Pixel> action) {
    ArrayList<ArrayList<Pixel>> newPixels = new ArrayList<ArrayList<Pixel>>();
    for (ArrayList<Pixel> pixelRow : pixels) {
      ArrayList<Pixel> pRow = new ArrayList<Pixel>();
      for (Pixel p : pixelRow) {
        pRow.add(action.apply(p));
      }
      newPixels.add(pRow);
    }
    return createImage(newPixels);
  }

  protected abstract Image createImage(ArrayList<ArrayList<Pixel>> pixels);

  public Image duplicateImage() {
    return imageHelper(pixel -> pixel);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (ArrayList<Pixel> pixelRow : pixels) {
      for (Pixel pixel : pixelRow) {
        builder.append("(")
                .append(pixel.getRed())
                .append(", ")
                .append(pixel.getGreen())
                .append(", ")
                .append(pixel.getBlue())
                .append(")");
      }
      builder.append("\n");
    }

    return builder.toString();
  }


  @Override
  public ArrayList<ArrayList<Pixel>> getPixels() {
    ArrayList<ArrayList<Pixel>> newPixels = new ArrayList<ArrayList<Pixel>>();
    for (ArrayList<Pixel> pixelRow : pixels) {
      ArrayList<Pixel> pRow = new ArrayList<Pixel>(pixelRow);
      newPixels.add(pRow);
    }

    return newPixels;

  }


  @Override
  public Image sharpen() {

    Filter filter = new FilterImpl();
    double[][] sharpen = new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
      {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, 0.25, 1, 0.25, -0.125},
      {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, -0.125, -0.125, -0.125, -0.125}};

    return createImage(filter.applyFilter(this, sharpen));
  }

  @Override
  public Image blur() {
    Filter filter = new FilterImpl();

    double[][] blur =
            new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
    return createImage(filter.applyFilter(this, blur));
  }

  @Override
  public Image partialImageManipulation(Image mask, Image transformedImage) {
    ArrayList<ArrayList<Pixel>> originalImg = this.getPixels();
    ArrayList<ArrayList<Pixel>> maskImg = mask.getPixels();
    ArrayList<ArrayList<Pixel>> transformedImg = new ArrayList<>();
    transformedImg = transformedImage.getPixels();
    ArrayList<ArrayList<Pixel>> newImg = new ArrayList<>();

    int black = 0;
    for (int i = 0; i < this.getHeight(); i++) {
      ArrayList<Pixel> row = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) {
        if (maskImg.get(i).get(j).getRed() == 0
                && maskImg.get(i).get(j).getGreen() == 0
                && maskImg.get(i).get(j).getBlue() == 0) {
          row.add(transformedImg.get(i).get(j));

          black++;
        } else {
          row.add(originalImg.get(i).get(j));
        }
      }
      newImg.add(row);
    }
    System.out.println("Total black pixels: " + black);
    return createImage(newImg);
  }

  @Override
  public Image downscale(int newWidth, int newHeight) {
    int originalWidth = this.getPixels().get(0).size();
    int originalHeight = this.getPixels().size();

    if (newWidth * newHeight > originalWidth * originalHeight) {
      throw new IllegalArgumentException("dimensions cannot be larger than inputted image!");
    }
    ArrayList<ArrayList<Pixel>> downscaled = this
            .downscaleArrayList(this.getPixels(), originalWidth,
                    originalHeight, newWidth, newHeight);
    return createImage(downscaled);
  }

  //Returns the arraylist of the arraylist of pixels for the (transformed) downscaled image.
  private ArrayList<ArrayList<Pixel>> downscaleArrayList(ArrayList<ArrayList<Pixel>> originalImg,
                                                         int originalWidth, int originalHeight,
                                                         int newWidth, int newHeight) {
    ArrayList<ArrayList<Pixel>> downscaledImage = new ArrayList<>();
    double xPrime;
    double yPrime;
    Pixel originalPix;
    Pixel newPix;

    for (int i = 0; i < newHeight; i++) {
      ArrayList<Pixel> newImageRow = new ArrayList<>();
      for (int j = 0; j < newWidth; j++) {
        xPrime = (j * (double) originalWidth) / newWidth;
        yPrime = (i * (double) originalHeight) / newHeight;

        if ((int) xPrime == xPrime || (int) yPrime == yPrime) {
          originalPix = originalImg.get((int) yPrime).get((int) xPrime);
          newPix = new PixelImpl(originalPix.getRed(), originalPix.getGreen(),
                  originalPix.getBlue(), new Position(j, i));
        } else {
          int[] colors = this.getColorComponents(originalImg, xPrime, yPrime);
          newPix = new PixelImpl(colors[0], colors[1], colors[2], new Position(j, i));
        }
        newImageRow.add(newPix);
      }
      downscaledImage.add(newImageRow);
    }
    return downscaledImage;
  }

  //Returns color components for the pixels in the downscaled image.
  private int[] getColorComponents(ArrayList<ArrayList<Pixel>> original, double xPrime,
                                   double yPrime) {
    int[] newRGB = new int[3];
    Pixel aPix;
    Pixel bPix;
    Pixel cPix;
    Pixel dPix;

    Position aPos = new Position((int) Math.floor(xPrime), (int) Math.floor(yPrime));
    Position bPos = new Position((int) Math.ceil(xPrime), (int) Math.floor(yPrime));
    Position cPos = new Position((int) Math.floor(xPrime), (int) Math.ceil(yPrime));
    Position dPos = new Position((int) Math.ceil(xPrime), (int) Math.ceil(yPrime));

    try {
      aPix = original.get(aPos.getY()).get(aPos.getX());
      bPix = original.get(bPos.getY()).get(bPos.getX());
      cPix = original.get(cPos.getY()).get(cPos.getX());
      dPix = original.get(dPos.getY()).get(dPos.getX());
    } catch (IndexOutOfBoundsException e) {
      aPix = original.get((int) yPrime).get((int) xPrime);
      bPix = original.get((int) yPrime).get((int) xPrime);
      cPix = original.get((int) yPrime).get((int) xPrime);
      dPix = original.get((int) yPrime).get((int) xPrime);
    }


    double mRedComponent = (bPix.getRed() * (xPrime - Math.floor(xPrime)))
            + (aPix.getRed() * (Math.ceil(xPrime) - xPrime));
    double mGreenComponent = (bPix.getGreen() * (xPrime - Math.floor(xPrime)))
            + (aPix.getGreen() * (Math.ceil(xPrime) - xPrime));
    double mBlueComponent = (bPix.getBlue() * (xPrime - Math.floor(xPrime)))
            + (aPix.getBlue() * (Math.ceil(xPrime) - xPrime));

    double nRedComponent = (dPix.getRed() * (xPrime - Math.floor(xPrime)))
            + (cPix.getRed() * (Math.ceil(xPrime) - xPrime));
    double nGreenComponent = (dPix.getGreen() * (xPrime - Math.floor(xPrime)))
            + (cPix.getGreen() * (Math.ceil(xPrime) - xPrime));
    double nBlueComponent = (dPix.getBlue() * (xPrime - Math.floor(xPrime)))
            + (cPix.getBlue() * (Math.ceil(xPrime) - xPrime));

    double pRedComponent = (nRedComponent * (yPrime - Math.floor(yPrime)))
            + (mRedComponent * (Math.ceil(yPrime) - yPrime));
    double pGreenComponent = (nGreenComponent * (yPrime - Math.floor(yPrime)))
            + (mGreenComponent * (Math.ceil(yPrime) - yPrime));
    double pBlueComponent = (nBlueComponent * (yPrime - Math.floor(yPrime)))
            + (mBlueComponent * (Math.ceil(yPrime) - yPrime));

    newRGB[0] = (int) pRedComponent;
    newRGB[1] = (int) pGreenComponent;
    newRGB[2] = (int) pBlueComponent;

    return newRGB;
  }
}
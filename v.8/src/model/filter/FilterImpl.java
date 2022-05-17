package model.filter;

import model.Image;
import model.Pixel;
import model.PixelImpl;
import model.Position;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the concrete implementing class of an Image filter in
 * the Image Processor.
 */

public class FilterImpl implements Filter {


  @Override
  public ArrayList<ArrayList<Pixel>> applyFilter(Image image, double[][] kernel)
      throws IllegalArgumentException {


    if (kernel.length % 2 == 0 || kernel[0].length % 2 == 0) {
      throw new IllegalArgumentException("Kernel dimensions cannot be even!");
    }


    ArrayList<ArrayList<Pixel>> newPixels = image.getPixels();
    for (int i = 0; i < newPixels.size(); i++) {
      for (int j = 0; j < newPixels.get(i).size(); j++) {
        int[][][] imageKernel = getKernel(newPixels, i, j, kernel.length, kernel[0].length);
        int[] newPixel = filteredPixel(imageKernel, kernel);
        Pixel pixel = new PixelImpl(newPixel[0], newPixel[1], newPixel[2], new Position(i, j));
        newPixels.get(i).set(j, pixel);
      }
    }

    return newPixels;
  }


  protected int[] filteredPixel(int[][][] kernel, double[][] filter) {

    int[] tempPixel = new int[3];
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[i].length; j++) {
        tempPixel[0] += kernel[i][j][0] * filter[i][j];
        tempPixel[1] += kernel[i][j][1] * filter[i][j];
        tempPixel[2] += kernel[i][j][2] * filter[i][j];
      }
    }

    return Arrays.stream(tempPixel).map(this::clamp).toArray();

  }


  protected int[][][] getKernel(ArrayList<ArrayList<Pixel>> pixels,
                                int row, int col, int height, int width) {

    int[][][] imageKernel = new int[height][width][3];

    int xBounds = findBounds(height);
    int yBounds = findBounds(width);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        try {
          imageKernel[i][j][0] = pixels.get(i + row - xBounds).get(j + col - yBounds).getRed();
          imageKernel[i][j][1] = pixels.get(i + row - xBounds).get(j + col - yBounds).getGreen();
          imageKernel[i][j][2] = pixels.get(i + row - xBounds).get(j + col - yBounds).getBlue();
        } catch (IndexOutOfBoundsException ignored) {
        }
      }
    }
    return imageKernel;
  }

  private int findBounds(int dimension) {
    return roundDoubleToInt(Math.floor(dimension / 2.0));
  }

  private int roundDoubleToInt(double val) {
    return Math.round(Math.round(val));
  }

  protected int clamp(int component) {
    if (component < 0) {
      component = 0;
    } else if (component > 255) {
      component = 255;
    }
    return component;
  }
}

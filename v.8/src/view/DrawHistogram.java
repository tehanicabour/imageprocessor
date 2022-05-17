package view;

import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Represents a Histogram object which visualizes data.
 */
public class DrawHistogram extends JPanel {
  private static final int BORDER_GAP = 30;
  private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
  private static final int GRAPH_POINT_WIDTH = 12;
  private static final int Y_HATCH_CNT = 10;
  private static int[] scores;
  private Color color;
  private int maxScore = 100;

  /**
   * Makes a histogram object which stores an int array of the scores.
   */
  public DrawHistogram(int[] scores, Color color) {
    this.scores = scores;
    this.color = color;
  }

  private static int getMax(int[] scores) {
    int max = 0;
    for (int i : scores) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // create x-axis and y-axis
    g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
    g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP,
            getHeight() - BORDER_GAP);

    // create hatch marks
    for (int i = 0; i < Y_HATCH_CNT; i++) {
      int x0 = BORDER_GAP;
      int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
      int y0 = getHeight() -
              (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
      int y1 = y0;
      g2.drawLine(x0, y0, x1, y1);
    }
    double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.length - 1);
    double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (maxScore - 1);

    if (scores.length != 0 && scores != null) {
      maxScore = getMax(scores);
    }

    for (int i = 0; i < scores.length - 1; i++) {
      int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.length - 1) + BORDER_GAP;
      int x1 = x0;
      int y0 = getHeight() - BORDER_GAP;
      int y1 = y0 - GRAPH_POINT_WIDTH;
      g2.drawLine(x0, y0, x1, y1);
    }

    //points
    ArrayList<Point> graphPoints = new ArrayList<Point>();
    for (int i = 0; i < scores.length; i++) {
      int x1 = (int) (i * xScale + BORDER_GAP);
      int y1 = (int) ((maxScore - scores[i]) * yScale + BORDER_GAP);
      graphPoints.add(new Point(x1, adjustSize(y1)));
    }

    g2.setColor(color);
    g2.setStroke(GRAPH_STROKE);
    for (int i = 0; i < graphPoints.size() - 1; i++) {
      int x1 = graphPoints.get(i).x;
      int y1 = graphPoints.get(i).y;
      int x2 = graphPoints.get(i + 1).x;
      int y2 = graphPoints.get(i + 1).y;
      g2.drawLine(x1, y1, x2, y2);
    }

  }

  private int adjustSize(int i) {
    if (i > (getHeight() - BORDER_GAP)) {
      return getHeight() - BORDER_GAP;
    }
    return i;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(400, 250);
  }

}

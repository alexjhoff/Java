package snack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawBar extends JPanel {
   private int MAX_SCORE = 20;
   private static final int PREF_W = 350;
   private static final int PREF_H = 350;
   private static final int BORDER_GAP = 30;
   private static final Color GRAPH_COLOR = Color.green;
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 12;
   private static final int Y_HATCH_CNT = 10;
   private List<Double> calVal;

   public DrawBar(List<Double> calVal, int maxScore) {
	      this.calVal = calVal;
	      MAX_SCORE = maxScore;
	 }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (calVal.size() - 1);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < calVal.size(); i++) {
         int x1 = (int) (i * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - calVal.get(i)) * yScale + BORDER_GAP);
         graphPoints.add(new Point(x1, y1));
      }

      // create x and y axes 
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

      // create hatch marks for y axis. 
      for (int i = 0; i < Y_HATCH_CNT; i++) {
         int x0 = BORDER_GAP;
         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
      }

      // and for x axis
      for (int i = 0; i < calVal.size(); i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (calVal.size()) + BORDER_GAP;
         int x1 = x0;
         int y0 = getHeight() - BORDER_GAP;
         int y1 = y0 - GRAPH_POINT_WIDTH;
         g2.drawLine(x0, y0, x1, y1);
      }
      
      // determine longest bar
   		int max = Integer.MIN_VALUE;
   		for (int i=0; i<calVal.size(); i++)
   		{
   			max = Math.max(max,calVal.get(i).intValue());
   		}
   		// paint bars

   		int width = (getWidth() / calVal.size()) - 15;
   		int x = 33;
   		for (int i=0; i<calVal.size(); i++)
   		{
   			int value = calVal.get(i).intValue();
   			int height = (int)((getHeight()-40) * ((double)value / max));
   			g.setColor(Color.blue);
   			g.fillRect(x, getHeight() - height - 13, width, height-20);
   			g.setColor(Color.blue);
   			g.drawRect(x, getHeight() - height - 13, width, height-20);
   			x += (width + 5.5);
   		}
      
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   
}
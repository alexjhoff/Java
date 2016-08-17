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
public class DrawGraph extends JPanel {
   private  int MAX_SCORE = 1300;
   private static final int PREF_W = 550;
   private static final int PREF_H = 350;
   private static final int BORDER_GAP = 30;
   private static final Color GRAPH_COLOR = Color.green;
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 12;
   private static final int Y_HATCH_CNT = 10;
   private List<Double> calVal;
private List<String> dateList;

   public DrawGraph(List<Double> calVal,List<String> dateList, int maxScore) {
      this.calVal = calVal;
      this.dateList = dateList;
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
         //System.out.println("x1: " + x1 + "y1" + y1);
         graphPoints.add(new Point(x1, y1));
      }

      // create x and y axes 
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
      g2.drawString(dateList.get(0), BORDER_GAP - 25, (getHeight() - BORDER_GAP) + 10);

      // create hatch marks for y axis. 
      for (int i = 0; i < Y_HATCH_CNT; i++) {
         int x0 = BORDER_GAP;
         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
         //System.out.println("==========hatch marks for y axis ===============");
         //System.out.println("x0:" + x0 +"\n" + "x1:" + x1 + "\n" + "y0:" + y0 + "\n"+ "y1:" + y1);
         
      }

      // and for x axis
      for (int i = 0; i < calVal.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (calVal.size() - 1) + BORDER_GAP;
         int x1 = x0;
         int y0 = getHeight() - BORDER_GAP;
         int y1 = y0 - GRAPH_POINT_WIDTH;
         g2.drawLine(x0, y0, x1, y1);
         //System.out.println("==========hatch marks for x axis ===============");
         //System.out.println("x0:" + x0 +"\n" + "x1:" + x1 + "\n" + "y0:" + y0 + "\n"+ "y1:" + y1);
        	 g.drawString(dateList.get(i + 1), x0-32, y0+15);
      }

      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);    
         //System.out.println("=============oldStroke===========");
         //System.out.println( "x1:" + x1 + "\n" + "y1:" + y1 + "\n"+ "x2:" + x2 + "y2:" + y2) ;
      }

      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
         g2.drawString(calVal.get(i).toString(), x, y - 20);
         //System.out.println("===================fillOval================");
         //System.out.println("x:" + x + "\n" + "y:" + y + "\n" + "ovalW" + ovalW + "ovalH" + ovalH );
      }
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   
}
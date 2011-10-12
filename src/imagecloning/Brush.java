package imagecloning;


import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.geom.*; 

public class Brush implements Serializable
{
	//private Color color = new Color((float)198/255, (float)226/255, (float)1.0, (float)0.02);
	private Color color = new Color((float)100/255, (float)149/255, (float)237/255, (float)0.05);
	private int diameter = 20;
	private  Area brushingArea;
	private  ArrayList <Point> points;
	private  ArrayList <Point> brushingPoints;

	public Brush() {
		points = new ArrayList <Point> ();
		brushingPoints = new ArrayList<Point>();
		brushingArea = new Area();
	}

	public void addPoint(Point p) {
		points.add(p);
		//brushingPoints.add(p);
		for (int i = 0; i <= diameter; i++) {
			for (int j = 0; j <= diameter; j++) {
				Point new_p = new Point(p.x + i, p.y +j);
				if (!brushingPoints.contains(new_p)) 
						brushingPoints.add(new_p);
			}
		}
	}
	

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(color);
	
		if(!points.isEmpty()){
			int lastIndex = points.size() - 1;
			Rectangle2D.Double square = new Rectangle2D.Double(points.get(lastIndex).x, points.get(lastIndex).y, diameter, diameter);
			brushingArea.add(new Area(square));
			g2d.fill(square);
		}
		
		
	}
	
	public void paintBrushingArea(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if (!brushingPoints.isEmpty()){
			g2d.setColor(Color.yellow);
		
			for (int i=0; i<brushingPoints.size();i++) {
				Rectangle2D.Double square = new Rectangle2D.Double(brushingPoints.get(i).x, brushingPoints.get(i).y, 1, 1);
				g2d.fill(square);
			}
		}
	}
	
	public Area getArea() {
		return brushingArea;
	}
	
	public void clear() {
		points.clear();
		brushingArea.reset();
	}
	
	public ArrayList getPoints() {
		return brushingPoints;
	}
			
}

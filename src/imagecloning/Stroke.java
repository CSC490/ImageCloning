package imagecloning;


import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.geom.*;


public class Stroke implements Serializable
{
	private ArrayList <Point> points;
	private  boolean isNewStroke;
	public  GeneralPath shape = null;
	
	/*
	 * Stroke class constructor
	 */
	public Stroke(){
		isNewStroke = true; // No position defined yet.
		points = new ArrayList<Point>(); // Colection of points.
		shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD); // Our stroke.
	}

	
	public Stroke(ArrayList points) {
		isNewStroke = true; // No position defined yet.
		
		shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD); // Our stroke.
		
		this.points = new ArrayList<Point>(points);
	}
	
	/*
	 * Adding a new point to the shape
	 */
	public void addPoint(Point p){
		points.add(p);
	}

	
	public void paintAll(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.red);
		
		if(!points.isEmpty()){ // Dont draw if there are no points.
			
			shape.moveTo(points.get(0).x, points.get(0).y);
			for (int i = 1; i < points.size();i++){
				shape.lineTo(points.get(i).x, points.get(i).y);
			}
			g2d.draw(shape);
		}
	}
	
	
	/*
	 * Paint the shape on the graphics provided.
	 */
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.black);
		
		if(!points.isEmpty()){ // Dont draw if there are no points.
			int lastIndex = points.size() - 1;
			if (isNewStroke) {
				System.out.println("Started New stroke");
				// Move to the start of the the new stroke. It should be the last point added.
				shape.moveTo(points.get(lastIndex).x, points.get(lastIndex).y);
				isNewStroke = false;
			} else {
				//Draw line to the last point added.
				shape.lineTo(points.get(lastIndex).x, points.get(lastIndex).y);
			}

			g2d.draw(shape);
		}
	}
	
	/*
	 * Indicate that a new stroke is going to be drawn 
	 * starting from the next point.
	 */
	public void startNewStroke() {
		isNewStroke = true;
	}
	
	public Area getArea(){
		return new Area(shape);
	}

	public void clear() {
		points.clear();
		shape.reset();
	}

	public ArrayList getPoints(){
		return points;
	}

	
}

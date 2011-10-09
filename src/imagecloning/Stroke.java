package imagecloning;


import java.awt.*;
import java.util.*;
import java.io.*;

public class Stroke implements Serializable
{
	Color color = Color.black;
	public ArrayList points;

	
	public Stroke(){
		points = new ArrayList();
	}

	public void addPoint(Point p){
		points.add(p);
	}

	public void paint(Graphics g){

		if (color == null) return;
		g.setColor(color);
		for(int i=0; i< points.size()-1; i++){
			Point p0 = (Point)points.get(i);
			Point p1 = (Point)points.get(i+1);
			if(p0!=null && p1!=null){
				g.drawLine(p0.x, p0.y, p1.x, p1.y);
			}
		}
		if (points.size() == 1){
			Point p0 = (Point)points.get(0);
			g.drawLine(p0.x, p0.y, p0.x, p0.y);
		}
	}
	

}

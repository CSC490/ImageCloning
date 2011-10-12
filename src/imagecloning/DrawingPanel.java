package imagecloning;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JPanel;
import Constant.Constants;
import java.awt.geom.*;
import java.util.ArrayList;

class DrawingPanel extends JPanel {

	
	public int operation_status = Constants.DRAWING;
	public boolean clear_drawings = true; // Should clear the drawing panel
	//private ArrayList strokesArray = new ArrayList();
	private Stroke stroke = null;
	private Brush brush = null;
	private Area substract = null;
	private ArrayList <Stroke> cloneArray = null;
	private ArrayList <Stroke> strokesArray = null;
	
	public DrawingPanel(){
		// Mouse Controls
		
		stroke = new Stroke();
		brush = new Brush();
		cloneArray = new ArrayList<Stroke>();
		strokesArray = new ArrayList<Stroke>();
		//substract = new A
		MouseDispatcher mouseDispatcher = new MouseDispatcher();
		addMouseListener(mouseDispatcher);
		addMouseMotionListener(mouseDispatcher);
	}
	
	
	/*
	 * Delete all the strokesArray on the drawing panel.
	 */
	public void clearDrawingPanel(){
		//strokesArray.clear();
		clear_drawings = true;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		if (clear_drawings){
			g.clearRect(0, 0, getSize().width, getSize().height);	
			clear_drawings = false;
			stroke.clear();
			brush.clear();
		} else{
			// paint code!
			if (operation_status == Constants.DRAWING) {
				stroke.paint(g);
			} else if (operation_status ==  Constants.BRUSHING) {
				brush.paint(g);
			} else if (operation_status == Constants.SUBSTRACTING) {
				//g.clearRect(0, 0, getSize().width, getSize().height);	
				//clear_drawings = false;
				brush.paintBrushingArea(g);
				if (!cloneArray.isEmpty()){ 
					for (int i=0; i < cloneArray.size();i++){
						cloneArray.get(i).paintAll(g);
					}
				}
				
				
//				Graphics2D g2d = (Graphics2D) g;
//				g2d.setPaint(Color.black);
//				g2d.draw(substract);
//				}
			
			}
	
		}
	}
	

	
	/*
	 * Event handling for drawing
	 */
	public void start_stroke(Point p){
		stroke = new Stroke();
		extend_stroke(p);
	}
	public void extend_stroke(Point p){
		stroke.addPoint(p);
		repaint();
	}
	public void finish_stroke(Point p){
		strokesArray.add(stroke);
	}
	
	
	/*
	 * Event handling for brushing
	 */
	public void start_brushing(Point p){
		extend_brushing(p);
	}
	public void extend_brushing(Point p){
		brush.addPoint(p);
		repaint();
	}
//	public void finish_brushing(Point p){
//		strokesArray.add(stroke);
//	}
		
	
	
	

	void switchToMode(int mode) {
		operation_status = mode;
	}

	void substract() {
			
		operation_status = Constants.SUBSTRACTING;
		ArrayList brushPoints = (ArrayList) brush.getPoints().clone();
		for (int i = 0; i < strokesArray.size(); i++ ){
			//Get the stroke
			Stroke localstroke = strokesArray.get(i);
			//Get the points of the stroke
			ArrayList strokePoints = (ArrayList) localstroke.getPoints().clone();
			
			boolean isNewStroke = true;
			Stroke s = null;
			for (int j = 0;j < strokePoints.size(); j++){
				Point p = (Point) strokePoints.get(j);
				if (brushPoints.contains(p)) {
					System.out.println("AAAAAAAA");
					if (isNewStroke) {
						s = new Stroke(); 
						isNewStroke = false;
					}
					
					s.addPoint(p);
				} else {
					if (isNewStroke == false) {
						cloneArray.add(s);
					}
					isNewStroke = true;
					
				}
			}
			cloneArray.add(s);
			
			
			
		}
		
		repaint();
	}
   
	//   
	// central event dispatcher
	//
	public class MouseDispatcher extends MouseAdapter implements MouseMotionListener{

		@Override
  		public void mouseMoved(MouseEvent e) {
  		}
		
		@Override
  		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			
			boolean right_button = (e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0;
			
			
			if (operation_status == Constants.DRAWING) {
				start_stroke(p);
			} else if (operation_status ==  Constants.BRUSHING) {
				start_brushing(p);
			}
			
		}
		
		@Override
  		public void mouseDragged(MouseEvent e) {
			Point p = e.getPoint();
			
			if (operation_status == Constants.DRAWING) {
				extend_stroke(p);
			} else if (operation_status ==  Constants.BRUSHING) {
				extend_brushing(p);
			}
			
  		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			Point p = e.getPoint();
			
			switch(operation_status){
			case Constants.DRAWING:
				finish_stroke(p);
				break;
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e){
		}
    }
}








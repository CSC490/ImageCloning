package imagecloning;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.JPanel;
import Constant.Constants;

class DrawingPanel extends JPanel {

	
	public int operation_status = Constants.DRAWING;
	public boolean clear_drawings = true; // Should clear the drawing panel
	private ArrayList strokesArray = new ArrayList();
	Stroke stroke = null;
	Brush brush = null;
	
	
	public DrawingPanel(){
		// Mouse Controls
		MouseDispatcher mouseDispatcher = new MouseDispatcher();
		addMouseListener(mouseDispatcher);
		addMouseMotionListener(mouseDispatcher);
	}
	
	
	/*
	 * Delete all the strokesArray on the drawing panel.
	 */
	public void clearDrawingPanel(){
		strokesArray.clear();
		clear_drawings = true;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		if (clear_drawings){
			g.clearRect(0, 0, getSize().width, getSize().height);	
			clear_drawings = false;
		} else{
			// paint code!
			if (operation_status == Constants.DRAWING) {
				stroke.paint(g);
			} else if (operation_status ==  Constants.BRUSHING) {
				brush.paint(g);
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
		brush = new Brush();
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
   
	//   
	// central event dispatcher
	//
	public class MouseDispatcher extends MouseAdapter implements MouseMotionListener{

  		public void mouseMoved(MouseEvent e) {
  		}
  		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			boolean right_button = (e.getModifiers() & e.BUTTON3_MASK) != 0;
			
			
			if (operation_status == Constants.DRAWING) {
				start_stroke(p);
			} else if (operation_status ==  Constants.BRUSHING) {
				start_brushing(p);
			}
			
		}
		
  		public void mouseDragged(MouseEvent e) {
			Point p = e.getPoint();
			
			if (operation_status == Constants.DRAWING) {
				extend_stroke(p);
			} else if (operation_status ==  Constants.BRUSHING) {
				extend_brushing(p);
			}
			
  		}
		
		
		public void mouseReleased(MouseEvent e) {
			Point p = e.getPoint();
			
			switch(operation_status){
			case Constants.DRAWING:
				finish_stroke(p);
				break;
			}
		}
		
		public void mouseClicked(MouseEvent e){
		}
    }
}








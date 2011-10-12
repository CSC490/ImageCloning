/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagecloning;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author rinatabdrashitov
 */
public class ClonePanel extends JPanel{
	ArrayList <Stroke> cloneArray;
	
	public ClonePanel(){
		
	}

	public void clone(ArrayList cloneArray){
		this.cloneArray = new ArrayList <Stroke>(cloneArray);
	}
	
	
	@Override
	public void paint(Graphics g) {
//		if (clear_drawings){
//			g.clearRect(0, 0, getSize().width, getSize().height);	
//			clear_drawings = false;
//			stroke.clear();
//			brush.clear();
//		} else{
			// paint code!
//			if (operation_status == Constants.DRAWING) {
//				stroke.paint(g);
//			} else if (operation_status ==  Constants.BRUSHING) {
//				brush.paint(g);
			//} else if (operation_status == Constants.SUBSTRACTING) {
				//g.clearRect(0, 0, getSize().width, getSize().height);	
				//clear_drawings = false;
				//brush.paintBrushingArea(g);
				if (!cloneArray.isEmpty()){ 
					for (int i=0; i < cloneArray.size();i++){
						cloneArray.get(i).paintAll(g);
					}
				}
			}
	
		//}
	//}

}

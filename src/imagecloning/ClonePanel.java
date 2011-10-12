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
	ArrayList <Stroke> cloneArray = null;
	
	public ClonePanel(){
		super();
	}

	public void clone(ArrayList cloneArray){
		this.cloneArray = new ArrayList <Stroke>(cloneArray);
	}
		
	@Override
	public void paint(Graphics g) {
			if (cloneArray!=null && !cloneArray.isEmpty()){ 
				for (int i=0; i < cloneArray.size();i++){
					cloneArray.get(i).paintAll(g);
				}
			}
		}
}

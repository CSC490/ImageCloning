package imagecloning;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import Constant.Constants;


/**
 *
 * @author rinatabdrashitov
 */
public class MainFrame extends JFrame{
	
	private DrawingPanel drawingPanel;
	private ClonePanel clonePanel;
	
	/*
	 * MainFrame Constructor
	 */
	public MainFrame() {
		
		this.addWindowListener(
		  new WindowAdapter(){
			public void windowClosing(WindowEvent e) {System.exit(0);}}
			);
		
		
		initMainFrame(); // Set all the default parameters of the Frame
		createPanels(); // Create Drawing and Cloning Panels
	}
	

	/*
	 * Main Frame initializer
	 */
	private void initMainFrame(){
		setTitle("ImageCloning");
		setSize(800, 400);
		
		setResizable(false); // TO DO: Figured out resizing
		// Creating a Menu
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		JMenuItem clearMenuItem = new JMenuItem("Clear");
		JMenuItem drawingMenuItem = new JMenuItem("Drawing Mode");
		JMenuItem brushingMenuItem = new JMenuItem("Brushing Mode");
		JMenuItem substractMenuItem = new JMenuItem("Substract");
		JMenuItem cloneMenuItem = new JMenuItem("Clone");
		
		
		exitMenuItem.addActionListener(
				new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent ae) {
						System.exit(0);
					}
				});
		
		clearMenuItem.addActionListener(
				new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent ae) {
						//TO DO: Rethink the way we gonna
						drawingPanel.clearDrawingPanel();
					}
				});
		
		drawingMenuItem.addActionListener(
				new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent ae) {
						//TO DO: Rethink the way we gonna
						drawingPanel.switchToMode(Constants.DRAWING);
					}
				});
		
		brushingMenuItem.addActionListener(
				new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent ae) {
						//TO DO: Rethink the way we gonna
						drawingPanel.switchToMode(Constants.BRUSHING);
					}
				});
		
		substractMenuItem.addActionListener(
				new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent ae) {
						drawingPanel.substract();
					}
				});
		
		
		
		cloneMenuItem.addActionListener(
				new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent ae) {
						clonePanel.clone(drawingPanel.getCloneArray());
						clonePanel.repaint();
					}
				});
		
		
		fileMenu.add(drawingMenuItem);
		fileMenu.add(brushingMenuItem);
		fileMenu.add(substractMenuItem);
		fileMenu.add(cloneMenuItem);
		fileMenu.add(clearMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		
		
		setJMenuBar(menuBar);
		
		setLayout(new GridLayout(1,2));
		
	}
	
	
	/*
	 * Create and add Panels to the Frame
	 */
	private void createPanels(){
		drawingPanel = new DrawingPanel();
		//drawingPanel.setSize(399, 400);
		add(drawingPanel);
			
//		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
//		separator.setBackground(Color.BLACK);
//		separator.setSize(1, 400);
//		add(separator);
		
		clonePanel = new ClonePanel();
		clonePanel.setBackground(Color.green);
		//clonePanel.setSize(400, 400);
		add(clonePanel);
	};

}

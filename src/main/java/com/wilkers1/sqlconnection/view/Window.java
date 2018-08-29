package com.wilkers1.sqlconnection.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Window extends JFrame{
	private static final long serialVersionUID = -6247088568170886703L;
	
	protected Container pane = this.getContentPane();
	
	/** identifier for setting up {@link #buildLayout(GridBagLayout, int)} */
	public static final int PESSOA = 0x20;
	/** identifier for setting up {@link #buildLayout(GridBagLayout, int)} */
	public static final int TAREFA = 0x70;
	/** identifier for setting up {@link #buildLayout(GridBagLayout, int)} */
	public static final int REF_PARTICIPANTE = Window.PESSOA; 
	
	/** variable for usage at {@link #buildLayout(GridBagLayout, int)} and other possible building methods */
	private static int xgap = 4;
	/** variable for usage at {@link #buildLayout(GridBagLayout, int)} and other possible building methods */
	private static int ygap = 8;
	/** variable for usage at {@link #buildLayout(GridBagLayout, int)} and other possible building methods */
	private static int borderBoxSize = 40;
	/** variable for usage at {@link #buildLayout(GridBagLayout, int)} and other possible building methods */
	private static int lblx = 48;
	/** variable for usage at {@link #buildLayout(GridBagLayout, int)} and other possible building methods */
	private static int barx = 128;
	/** variable for usage at {@link #buildLayout(GridBagLayout, int)} and other possible building methods */
	private static int bary = 24;
	
	protected GridBagLayout bag = new GridBagLayout();
	protected GridBagConstraints[][] field = null;
	
	/**
	 * Basic setup constructor for the window.
	 * @param windowName is the window title.
	 */
	public Window(String windowName) {
		super(windowName);
		this.setSize(480, 360);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pane.setLayout(bag);
		buildLayout(this.field, this.bag, PESSOA);
	}
	
	/**
	 * TODO complete window building
	 * Prepares a layout according to the field layout specified.
	 * 
	 * <p>The first row holds a constraint for the title, while the last holds
	 * another constraint for a new panel for the action buttons.</p>
	 * 
	 * <code>
	 * |||||||||||||||||<br/>
	 * |[----TITLE----]|<br/>
	 * |[LABEL]-[FIELD]|<br/>
	 * |[LABEL]-[FIELD]|<br/>
	 * |[LABEL]-[FIELD]|<br/>
	 * |[----PANEL----]|<br/>
	 * |||||||||||||||||<br/>
	 * .<br/>
	 * .<br/>
	 * [BUTTON__PANEL]</br>
	 * </code></br>
	 * 
	 * @param layout is the GridBagLayout used to prepare the spaces.
	 * @param fieldSetup is the id of the field setup to insert.
	 * @return	an array of arrays of <code>GridBagConstraints</code>,
	 * 			just in case the instance passed as the parameter is not a
	 * 			pre-existing variable.
	 * @author WilkerS1
	 */
	@SuppressWarnings("serial")
	public static GridBagConstraints[][] buildLayout(GridBagConstraints[][] pos, GridBagLayout layout, int fieldSetup) {
		pos = new GridBagConstraints[ //sets up the size of the layout
									 fieldSetup==PESSOA?5: //if PESSOA is selected
									 fieldSetup==TAREFA?9:0 //otherwise if TAREFA is selected, otherwise 2.
									 ][2];
		if(pos.length==0) { throw new IllegalArgumentException("Invalid setup."); }
		
		for(int row=0; row<pos.length; row++) { //repeats for every array entry in the array...
			if(row==0 || row==pos.length-1) { //if it's the first or last row...
				pos[row] =	new GridBagConstraints[] { new GridBagConstraints() }; //this array will have only one entry.
			}
			
			for(GridBagConstraints posx : pos[row]) {
				posx = new GridBagConstraints();
				posx.gridy = row+2;
			}
			
			pos[row][0].gridx = 1;
			if(pos[row].length==1) { continue; }
			pos[row][1].gridx = 3;
			pos[row][1].gridwidth = 2;
		}
		
		//space,	field,	gap,	field,field,	space
		layout.columnWeights = new double[] {0,0,0,0,1,0};
		//||label|field-field||
		layout.columnWidths  = new int[] {borderBoxSize, lblx,  xgap, barx, barx,  borderBoxSize};
		
		//ROW WEIGHTS
		double[] rowWeights = new double[pos.length*2 +1]; //creates a new set of row weights
		for(double row : rowWeights) { row = 0; } //sets every row to 0
		rowWeights[0] = rowWeights[rowWeights.length-1] = 1; //first and last rows are 1
		layout.rowWeights = rowWeights; //sets up row weights
		//ROW HEIGHTS
		int[] rowHeights = new int[pos.length*2 +1]; //creates a set of row weights
		boolean isField=false;
		for(int row : rowHeights) { //repeats for every row...
			row = isField?bary:ygap; //if it's a field row, bar size. otherwise y space gap.
			isField = !isField; //toggles 'isField'
		}
		layout.rowHeights = rowHeights; //sets up the row heights
		
		return pos;
	}
	
	public static void main(String...args) {
		new Window("").setLocationRelativeTo(null);
	}
}

package com.wilkers1.sqlconnection.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Window extends JFrame{
	private static final long serialVersionUID = -6247088568170886703L;
	
	protected Container pane = this.getContentPane();
	
	/** identifier for setting up {@link #buildLayout(GridBagLayout, int)} */
	public static final int PESSOA = 2;
	/** identifier for setting up {@link #buildLayout(GridBagLayout, int)} */
	public static final int TAREFA = 7;
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
	 * @param fieldCount is the ammount of fields to create
	 * @return	an array of arrays of <code>GridBagConstraints</code>,
	 * 			just in case the instance passed as the parameter is not a
	 * 			pre-existing variable.
	 * @author WilkerS1
	 */
	public static GridBagConstraints[][] buildLayout(GridBagConstraints[][] pos, GridBagLayout layout, int fieldCount) {
		if(fieldCount<1) { throw new IllegalArgumentException("Invalid setup."); }
		pos = new GridBagConstraints[fieldCount][2];
		
		//
		//GRIDBAG LAYOUT SETUP
		//
		//space,	field,	gap,	field,field,	space
		layout.columnWeights = new double[] {0,0,0,0,1,0};
		//||label|field-field||
		layout.columnWidths  = new int[] {borderBoxSize, lblx,  xgap, barx, barx,  borderBoxSize};
		
		//ROW HEIGHTS
		layout.rowHeights = fieldCount==1?
				new int[] {borderBoxSize, bary, borderBoxSize}:
				new int[fieldCount+(fieldCount-1)+2];
		if(layout.rowHeights.length >3)
			layout.rowHeights[0] = layout.rowHeights[layout.rowHeights.length-1] = borderBoxSize;
		//ROW WEIGHTS
		layout.rowWeights = new double[layout.rowHeights.length]; //same array size as rowHeights
		for(@SuppressWarnings("unused") double row : layout.rowWeights) { row = 0; } //sets every row to 0
		layout.rowWeights[0] = layout.rowWeights[layout.rowWeights.length-1] = 1; //first and last rows are 1
		
		//if there is more than 1 field
		if(fieldCount != 3) {
			for(int line=0; line<layout.rowHeights.length; line++) { //repeats for every entry of rowHeights...
				layout.rowHeights[line] = line%2==0?ygap:barx; //if even number, set gap. if not, set bar.
			}
			layout.rowHeights[0] = layout.rowHeights[layout.rowHeights.length-1] = borderBoxSize;
		}
		
		//
		//CONSTRAINTS SETUP
		//TODO: fix broken code
		int row=0;
		for(GridBagConstraints[] posy : pos) { //repeats for every row...
			System.out.println(row);
			if(row==0 || row==fieldCount) //if it's the first or last row
				posy = new GridBagConstraints[] {
					new GridBagConstraints() {
						{
							this.fill = GridBagConstraints.BOTH; //the constraint will fill the element at both directions.
							this.gridwidth = 4; //sets the width of the constraint to fill the row.
							this.gridx = 1; //sets up the column space
						}
					}
				};
			else
				posy = new GridBagConstraints[] {
					new GridBagConstraints() {
						{
							this.fill = GridBagConstraints.BOTH; //the constraint will fill the element at both directions.
							this.gridwidth = 1; //sets the width of the constraint to fill the row.
							this.gridx = 1; //sets up the column space
						}
					},
					new GridBagConstraints() {
						{
							this.fill = GridBagConstraints.BOTH; //the constraint will fill the element at both directions.
							this.gridwidth = 1; //sets the width of the constraint to fill the row.
							this.gridx = 3; //sets up the column space
						}
					}
				};
			for(GridBagConstraints posx : posy) { //repeats for every column...
				posx.gridy = row==0?1:row*2; //starting at 1, the Y position of every column will be equals to the current Y position of the previous row plus 2.
			}
			
			posy[0].gridx = 1; //sets up the position of the first constraint of this row.
			posy[0].gridwidth = posy.length==2?1:4;
			if(posy.length!=1) { //if the size is greater than 1
				posy[1].gridx = 3; //sets up the position
				posy[1].gridwidth = 2; //and size of this constraint.
			}
			row++;
		}
		pos[pos.length-1] =new GridBagConstraints[] {new GridBagConstraints()};
		
		
		/*
		double[] rowWeights = new double[pos.length*2 +1]; //creates a new set of row weights
		layout.rowWeights = rowWeights; //sets up row weights
		//ROW HEIGHTS
		int[] rowHeights = new int[pos.length*2 +1]; //creates a set of row weights
		boolean isField=false;
		for(int row : rowHeights) { //repeats for every row...
			row = isField?bary:ygap; //if it's a field row, bar size. otherwise y space gap.
			isField = !isField; //toggles 'isField'
		}
		layout.rowHeights = rowHeights; //sets up the row heights
		*/
		return pos;
	}
	
	public static void main(String...args) {
		new Window("").setLocationRelativeTo(null);
	}
}

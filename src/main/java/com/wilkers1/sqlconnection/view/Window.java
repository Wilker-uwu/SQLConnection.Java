package com.wilkers1.sqlconnection.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
	 * [----TITLE----]</br>
	 * [LABEL]-[FIELD]</br>
	 * [LABEL]-[FIELD]</br>
	 * [LABEL]-[FIELD]</br>
	 * .</br>
	 * .</br>
	 * [BUTTON__PANEL]</br>
	 * </code></br>
	 * 
	 * @param layout is the GridBagLayout used to prepare the spaces.
	 * @param fieldSetup is the id of the field setup to insert.
	 * @return
	 */
	public static GridBagConstraints[][] buildLayout(GridBagConstraints[][] pos, GridBagLayout layout, int fieldSetup) {
		pos = new GridBagConstraints[ //sets up the size of the layout
									 fieldSetup==PESSOA?5: //if PESSOA is selected
									 fieldSetup==TAREFA?8:0 //otherwise if TAREFA is selected, otherwise 2.
									 ][1];
		if(pos.length==0) { throw new IllegalArgumentException("Invalid setup."); }
		
		for(int row=1; row<pos.length; row++) {
			pos[row] = new GridBagConstraints[] { //set this position to be of two constraints
					new GridBagConstraints() {
						{
							this.fill = GridBagConstraints.BOTH;
							this.gridx = 1;
							this.gridy = row+2;
						}
					},
					new GridBagConstraints() {
						{
							this.fill = GridBagConstraints.BOTH;
							this.gridx = 3;
							this.gridy = row+2;
							this.gridwidth = 2;
						}
					},
			};
		}
		
		//First and last rows...
		pos[0] = new GridBagConstraints[] {new GridBagConstraints()}; //the first row will have one field.
		pos[1][1].gridwidth = 1; //the second field of the second row will will one space, instead of 2 like the others.
		pos[pos.length-1] = new GridBagConstraints[] {new GridBagConstraints()}; //the last row will have one field.
		pos[pos.length-1][0].gridwidth = 4; //the field of the last row will fill most of the horizontal space.
		
		//space,	field,	gap,	field,field,	space
		layout.columnWeights = new double[] {0,0,0,0,1,0};
		//||label|field||
		layout.columnWidths  = new int[] {borderBoxSize, lblx,  xgap, barx,  borderBoxSize};
		
		switch(fieldSetup) {
			case PESSOA | REF_PARTICIPANTE: {
				//---title field||field||field||panel field---
				layout.rowWeights	= new double[] {1,0,0,0,0,0,1};
				layout.rowHeights	= new int[] {borderBoxSize,
						bary, ygap, //title
						bary, ygap, //selection
						bary, ygap, //field 0
						bary, ygap, //field 1
						borderBoxSize};
			};break;
			
			case TAREFA: {
				double[] rowWeights = new double[19]; //creates a new set of row weights
				for(@SuppressWarnings("unused") double row : rowWeights) { row = 0; } //sets every row to 0
				rowWeights[0]= rowWeights[18] = 1; //first and last rows are 1
				layout.rowWeights = rowWeights; //sets up row weights
				
				int[] rowHeights = new int[20]; //creates a set of row weights
				for(int i=0; i<20; i++) { //repeats for every row...
					//if it's the first or last row, borderBoxSize. otherwise: if odd number, bar. if not, gap.
					rowHeights[i] = (i==0||i==19)?borderBoxSize:(i%2==0)?bary:ygap;
				}
				layout.rowHeights = rowHeights; //sets up the row heights
			};break;
			
		}
		return pos;
	}
	
	public static void main(String...args) {
		new Window("").setLocationRelativeTo(null);
	}
}

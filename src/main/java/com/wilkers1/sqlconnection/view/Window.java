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
	
	public static final int PESSOA = 0x20;
	public static final int TAREFA = 0x70;
	public static final int REF_PARTICIPANTE = Window.PESSOA; 
	
	private static int xgap = 4;
	private static int ygap = 8;
	private static int borderBoxSize = 40;
	private static int lblx = 48;
	private static int barx = 128;
	private static int bary = 24;
	
	protected GridBagLayout bag = new GridBagLayout();
	protected GridBagConstraints[][] field = null;
	
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
	 * @param layout is the GridBagLayout used to prepare the spaces.
	 * @param fieldSetup is the id of the field setup to insert.
	 * @return
	 */
	public static GridBagConstraints[][] buildLayout(GridBagLayout layout, int fieldSetup) {
		GridBagConstraints[][] pos = new GridBagConstraints[ //sets up the size of the layout
															fieldSetup==PESSOA?4: //if PESSOA is selected
															fieldSetup==TAREFA?8:2 //otherwise if TAREFA is selected, otherwise 2.
															][0];
		if(pos.length==2) { throw new IllegalArgumentException("Invalid setup."); }
		
		for(GridBagConstraints[] posy : pos) { //repeats for every row of constraints...
			posy = new GridBagConstraints[] { //set this position to be of two constraints
					new GridBagConstraints(),
					new GridBagConstraints(),
			};
			for(GridBagConstraints posx : posy) { //repeats for every field...
				posx.fill = GridBagConstraints.BOTH; //every field will will its entire constraint space.
			}
			posy[1].gridwidth = 2;
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

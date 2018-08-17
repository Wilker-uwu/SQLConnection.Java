package com.wilkers1.sqlconnection.view;

import java.awt.Container;
import java.awt.Dimension;
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
	
	protected static int xgap = 4;
	protected static int ygap = 8;
	protected static int borderBoxSize = 40;
	protected static int lblx = 48;
	protected static int barx = 128;
	protected static int bary = 24;
	
	protected GridBagLayout bag = new GridBagLayout();
	protected GridBagConstraints[][] pos = null;
	
	public Window(String windowName, int buildType) {
		super(windowName);
		this.setSize(480, 360);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pane.setLayout(bag);
		buildLayout(this.bag, buildType);
	}
	
	/**
	 * TODO complete window building
	 * Prepares a layout according to the field layout specified.
	 * @param layout
	 * @param fields
	 * @return
	 */
	protected GridBagConstraints[][] buildLayout(GridBagLayout layout, int fields) {
		switch(fields) {
			case PESSOA | REF_PARTICIPANTE: {
				pos = new GridBagConstraints[][] {
						{
							new GridBagConstraints(),
						},
						{
							new GridBagConstraints(),
							new GridBagConstraints()
						},
						{
							new GridBagConstraints(),
							new GridBagConstraints()
						},
						{
							new GridBagConstraints(),
						},
				};
				
				//space,	field,	gap,	field,field,	space
				layout.columnWeights = new double[] {0,0,0,0,1,0};
				//space,	field, gap, field, gap, field, gap, field,	space
				layout.rowWeights    = new double[] {1,0,0,0,0,0,1};
				layout.columnWidths  = new int[] {borderBoxSize, lblx,  xgap, barx,  borderBoxSize};
				layout.rowHeights    = new int[] {borderBoxSize, bary, ygap, bary, borderBoxSize};
				for(int posx=1; posx<pos.length; posx++) {
					
				}
				
				for(GridBagConstraints[] posy : pos) {
					for(GridBagConstraints posx : posy) {
						posx.fill = GridBagConstraints.BOTH;
					}
				}
			};break;
			
			case TAREFA: {
				//TODO complete
			};break;
			
		}
		return pos;
	}
	
	public static void main(String...args) {
		new Window("", 0);
	}
}

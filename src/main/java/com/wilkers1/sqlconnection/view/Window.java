package com.wilkers1.sqlconnection.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
	
	protected static GridBagLayout bag = new GridBagLayout();
	
	public Window(String windowName, int buildType) {
		super(windowName);
		this.setSize(480, 360);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pane.setLayout(bag);
		buildLayout(buildType);
	}
	
	/**
	 * TODO complete window building
	 * 
	 * @param layout
	 * @param fields
	 * @return
	 */
	private static GridBagConstraints[][] buildLayout(GridBagLayout layout, int fields) {
		GridBagConstraints[][] pos = null;
		switch(fields) {
			case PESSOA | REF_PARTICIPANTE: {
				pos = new GridBagConstraints[][] {
						{
							new GridBagConstraints(),
							new GridBagConstraints()
						},
						{
							new GridBagConstraints(),
							new GridBagConstraints()
						}
				};
				
				layout.columnWeights = new double[] {0,0,0,1,0};
				layout.rowWeights    = new double[] {1,0,0,0,1};
				layout.columnWidths  = new int[] {borderBoxSize, lblx,  xgap, barx,  borderBoxSize};
				layout.rowHeights    = new int[] {borderBoxSize, bary, ygap, bary, borderBoxSize};
				pos[0][0].gridx = 1;	pos[0][0].gridy = 1;
				pos[0][1].gridx = 1;	pos[0][1].gridy = 3;
				pos[1][0].gridx = 3;	pos[1][0].gridy = 1;
				pos[1][1].gridx = 3;	pos[1][1].gridy = 3;
				
				for(GridBagConstraints[] posy : pos) {
					for(GridBagConstraints posx : posy) {
						posx.fill = GridBagConstraints.BOTH;
					}
				}
			}
		}
		return pos;
	}
	
	public static void main(String...args) {
		Window w = new Window("");
		w.pane.add(new JLabel("aa"), lbl0);
		w.pane.add(new JLabel("bb"), lbl1);

		w.pane.add(new JTextField("aa"), txt0);
		w.pane.add(new JTextField("aa"), txt1);
	}
}

package com.wilkers1.sqlconnection.view;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Insert extends Window{
	
	private class Tar extends JPanel {
		protected Tar() {
			this.setLayout(bag);
		}
	}
	
	protected JLabel lblNome = new JLabel("Nome:");
	protected JLabel lblEmail = new JLabel("Email:");
	protected JTextField txtNome = new JTextField();
	protected JTextField txtEmail = new JTextField();
	
	private JPanel paneBtn = new JPanel();
	private JButton btnInsert = new JButton();
	
	public Insert(String windowName, int fields) {
		super(windowName, fields);
		this.pane.add(lblNome,  this.field[0][0]);
		this.pane.add(lblEmail, this.field[0][1]);
		this.pane.add(txtNome,  this.field[1][0]);
		this.pane.add(txtEmail, this.field[1][1]);
	}
	
	public static void main(String...args) {
		//new Insert("Inserir", Window.TAREFA);
		new Insert("Inserir", Window.PESSOA);
	}
	
}

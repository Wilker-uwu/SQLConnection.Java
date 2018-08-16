package com.wilkers1.sqlconnection.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Insert extends Window{
	
	protected JLabel lblNome = new JLabel("Nome:");
	protected JLabel lblEmail = new JLabel("Email:");
	protected JTextField txtNome = new JTextField();
	protected JTextField txtEmail = new JTextField();
	
	private JPanel paneBtn = new JPanel();
	private JButton btnInsert = new JButton();
	
	public Insert(String windowName, int fields) {
		super(windowName, fields);
		pane.add(lblNome, lbl0);
		pane.add(lblEmail, lbl1);
		pane.add(txtNome, txt0);
		pane.add(txtEmail, txt1);
	}
	
	public static void main(String...args) {
		new Insert("Inserir", Window.TAREFA);
		new Insert("Inserir", Window.PESSOA);
	}
	
}

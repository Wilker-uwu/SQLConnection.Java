package com.wilkers1.sqlconnection.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class Insert extends Window{
	
	private class Tar extends JPanel {
		protected GridBagConstraints[][] field = null;
		
		protected JLabel lblTitulo = new JLabel("Título:");
		protected JLabel lblDesc = new JLabel("Descrição:");
		protected JLabel lblPrazo = new JLabel("Prazo:");
		protected JLabel lblPrazoInicio = new JLabel("Início:");
		protected JLabel lblPrazoTermino = new JLabel("Término:");
		protected JLabel lblMetodo = new JLabel("Metodologia:");
		
		protected JFormattedTextField txtTitulo = null;
		protected JTextArea txtDesc = new JTextArea();
		protected   JScrollPane scrDesc = new JScrollPane(txtDesc);
		
		protected MaskFormatter mskData = new MaskFormatter("####-##-##");
		protected JFormattedTextField txtPrazo = new JFormattedTextField(mskData);
		protected JFormattedTextField txtPrazoInicio = new JFormattedTextField(mskData);
		protected JFormattedTextField txtPrazoTermino = new JFormattedTextField(mskData);
		
		protected Tar() throws ParseException {
			super();
			this.setLayout(bag);
			this.field = Window.buildLayout(bag, Insert.TAREFA);
			
			txtTitulo = new JFormattedTextField(new MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
		}
	}
	
	protected JLabel lblNome = new JLabel("Nome:");
	protected JLabel lblEmail = new JLabel("Email:");
	protected JTextField txtNome = new JTextField();
	protected JTextField txtEmail = new JTextField();
	
	private JPanel paneBtn = new JPanel();
	private JButton btnInsert = new JButton();
	
	public Insert(String windowName) {
		super(windowName);
		this.setContentPane(new Tar());
	}
	
	public static void main(String...args) {
		//new Insert("Inserir", Window.TAREFA);
		new Insert("Inserir");
	}
	
}

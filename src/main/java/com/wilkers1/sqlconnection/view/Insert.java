package com.wilkers1.sqlconnection.view;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class Insert extends Window {
	
	/**
	 * {@link JPanel} instance used for having fields for creating instances of {@link Pessoa}
	 */
	private class Per extends JPanel {
		/** Constraint arrays for setting up the fields. */
		protected GridBagConstraints[][] field = null;
		
		protected JLabel lblNome = new JLabel("Nome:");
		protected JLabel lblEmail = new JLabel("Email:");
		
		protected JFormattedTextField txtNome = null;
		protected JFormattedTextField txtEmail = null;
		
		protected Per() throws ParseException {
			super();
			//layout setup
			this.setLayout(bag);
			this.field = Window.buildLayout(this.field, bag, Insert.PESSOA);
			//formatted text field instances setup
			txtNome = new JFormattedTextField(new MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
			txtEmail = new JFormattedTextField(new MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
			//title setup
			this.add(new JLabel("Pessoa..."), this.field[0][0]);
			//selector setup
			this.add(lblEntity, this.field[1][0]);
			this.add(cmbEntity, this.field[1][1]);
			
			//field setup
			this.add(this.lblNome,  this.field[2][0]); //labels
			this.add(this.lblEmail, this.field[2][0]);
			this.add(this.txtNome,  this.field[3][1]); //fields
			this.add(this.txtEmail, this.field[3][1]);
		}
	}
	
	/**
	 * {@link JPanel} instance used for having fields for creating instances of {@link RefParticipante}
	 */
	private class Tar extends JPanel {
		/** Constraint arrays for setting up the fields. */
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
		
		protected JComboBox<String> cmbMetodo = new JComboBox<String>(new String[] {"0. A", "1. B", "2. C", "?. ..."});
		
		protected Tar() throws ParseException {
			super();
			//layout setup
			this.setLayout(bag);
			this.field = Window.buildLayout(this.field, bag, Insert.TAREFA);
			//formatted text field instances setup
			txtTitulo = new JFormattedTextField(new MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
			//title setup
			this.add(new JLabel("Tarefas..."),this.field[0][0]);
			//selector setup
			this.add(lblEntity, this.field[1][0]);
			this.add(cmbEntity, this.field[1][1]);
			
			//fields setup
			this.add(lblTitulo,       this.field[2][0]); //labels
			this.add(lblDesc,         this.field[3][0]);
			this.add(lblPrazo,        this.field[4][0]);
			this.add(lblPrazoInicio,  this.field[5][0]);
			this.add(lblPrazoTermino, this.field[6][0]);
			this.add(lblMetodo,       this.field[7][0]);
			this.add(txtTitulo,       this.field[2][1]); //fields
			this.add(scrDesc,         this.field[3][1]);
			this.add(txtPrazo,        this.field[4][1]);
			this.add(txtPrazoInicio,  this.field[5][1]);
			this.add(txtPrazoTermino, this.field[6][1]);
			this.add(cmbMetodo,       this.field[7][1]);
			
		}
	}
	
	/** Label for entity tyoe selector */
	private JLabel lblEntity = new JLabel("Tipo de entidade:");
	/** Entity selector combo box, for switching between panels */
	private JComboBox<String> cmbEntity = new JComboBox<String>(new String[] {"Pessoa", "Tarefa", "Participantes"});
	
	/** JPanel instance for holding the action buttons */
	private JPanel paneBtn = new JPanel(false);
	/** Registration button for the database */
	private JButton btnRegister = new JButton("Register");
	/**  Clear button for clearing the text fields. */
	private JButton btnClear = new JButton("Clear");
	
	/**
	 * Constructor for the registration window.
	 * @param windowName is the name of the window.
	 * @throws ParseException 
	 */
	public Insert(String windowName) throws ParseException {
		super(windowName);
		
		Per perPanel = new Per();
		Tar tarPanel = new Tar();
		
		this.paneBtn.setLayout(new GridLayout(3,1));
		this.paneBtn.add(this.btnRegister);
		this.paneBtn.add(this.btnClear);
		
		this.setContentPane(perPanel);
	}
	
	public static void main(String...args) {
		//new Insert("Inserir", Window.TAREFA);
		try {
			new Insert("Inserir");
		} catch (ParseException exp) {
			exp.printStackTrace();
		}
	}
	
}

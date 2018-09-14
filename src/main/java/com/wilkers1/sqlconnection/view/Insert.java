package com.wilkers1.sqlconnection.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import com.wilkers1.sqlconnection.controller.Model;
import com.wilkers1.sqlconnection.model.Pessoa;
import com.wilkers1.sqlconnection.model.Tarefa;

@SuppressWarnings("serial")
public class Insert extends Window {
	
	Dimension grid = new Dimension(6, 8);
	Dimension label = new Dimension(120, 22);
	Dimension textField = new Dimension(300, 22);
	
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
			this.setLayout(null);
			//formatted text field instances setup
			txtNome = new JFormattedTextField(new MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
			txtEmail = new JFormattedTextField(new MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
			
			lblNome .setSize(label);
			lblEmail.setSize(label);
			txtNome .setSize(textField);
			txtEmail.setSize(textField);
			
			
			lblNome  .setLocation(grid.width*2, grid.height);
			lblEmail .setLocation(grid.width*2, lblNome.getY() +grid.height +label.height);
			
			txtNome  .setLocation(lblNome .getX() +label.width +grid.width,   grid.height);
			txtEmail .setLocation(lblEmail.getX() +label.width +grid.width,   txtNome.getY() +grid.height +label.height);			
			
			//field setup
			this.add(this.lblNome); //labels
			this.add(this.lblEmail);
			this.add(this.txtNome); //fields
			this.add(this.txtEmail);
		}
	}
	
	/**
	 * {@link JPanel} instance used for having fields for creating instances of {@link RefParticipante}
	 */
	private class Tar extends JPanel {
		
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
			this.setLayout(null);
			txtTitulo = new JFormattedTextField(new MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
			
			lblTitulo      .setSize(label);                               
			lblDesc        .setSize(label);                               
			lblPrazo       .setSize(label);                               
			lblPrazoInicio .setSize(label);                               
			lblPrazoTermino.setSize(label);                               
			lblMetodo      .setSize(label);                               
			                                                              
			txtTitulo      .setSize(textField);                           
			scrDesc        .setSize(textField.width, textField.height*5); 
			txtPrazo       .setSize(textField);                           
			txtPrazoInicio .setSize(textField);                           
			txtPrazoTermino.setSize(textField);                           
			cmbMetodo      .setSize(textField);                           
			
			lblTitulo      .setLocation(grid.width*2, grid.height);
			lblDesc        .setLocation(grid.width*2, lblTitulo      .getY() +txtTitulo      .getY() +grid.height +txtTitulo      .getHeight());
			lblPrazo       .setLocation(grid.width*2, lblDesc        .getY() +txtDesc        .getY() +grid.height +scrDesc        .getHeight());
			lblPrazoInicio .setLocation(grid.width*2, lblPrazo       .getY() +txtPrazo       .getY() +grid.height +txtPrazo       .getHeight());
			lblPrazoTermino.setLocation(grid.width*2, lblPrazoInicio .getY() +txtPrazoInicio .getY() +grid.height +txtPrazoInicio .getHeight());
			lblMetodo      .setLocation(grid.width*2, lblPrazoTermino.getY() +txtPrazoTermino.getY() +grid.height +txtPrazoTermino.getHeight());

			//formatted text field instances setup
			txtTitulo      .setLocation(lblTitulo      .getX() +label.width +grid.width,   grid.height);
			scrDesc        .setLocation(lblDesc        .getX() +label.width +grid.width,   txtTitulo      .getY() +grid.height +txtTitulo      .getHeight());
			txtPrazo       .setLocation(lblPrazo       .getX() +label.width +grid.width,   scrDesc        .getY() +grid.height +scrDesc        .getHeight());
			txtPrazoInicio .setLocation(lblPrazoInicio .getX() +label.width +grid.width,   txtPrazo       .getY() +grid.height +txtPrazo       .getHeight());
			txtPrazoTermino.setLocation(lblPrazoTermino.getX() +label.width +grid.width,   txtPrazoInicio .getY() +grid.height +txtPrazoInicio .getHeight());
			cmbMetodo      .setLocation(lblMetodo      .getX() +label.width +grid.width,   txtPrazoTermino.getY() +grid.height +txtPrazoTermino.getHeight());
			System.out.println(scrDesc.getX());
			System.out.println(scrDesc.getY());
			
			//fields setup
			this.add(this.lblTitulo); //labels
			this.add(this.lblDesc);
			this.add(this.lblPrazo);
			this.add(this.lblPrazoInicio);
			this.add(this.lblPrazoTermino);
			this.add(this.lblMetodo);
			this.add(this.txtTitulo); //fields
			this.add(this.scrDesc);
			this.add(this.txtPrazo);
			this.add(this.txtPrazoInicio);
			this.add(this.txtPrazoTermino);
			this.add(this.cmbMetodo);
		}
	}
	
	private class Btn extends JPanel {
		
		protected Btn() {
			this.setLayout(new GridLayout(1,0));
			this.add(btnRegister);
			this.add(btnDelete);
			this.add(btnClear);
		}
		
	}
	
	/** Label for entity type selector */
	private JLabel lblEntity = new JLabel("Tipo de entidade:");
	/** Entity selector combo box, for switching between panels */
	private JComboBox<String> cmbEntity = new JComboBox<String>(new String[] {"Pessoa", "Tarefa", "Participantes"});
	
	/** Registration button for the database */
	private JButton btnRegister = new JButton("Register");
	/** Deletion button for the database */
	private JButton btnDelete = new JButton("Delete");
	/**  Clear button for clearing the text fields. */
	private JButton btnClear = new JButton("Clear");
	
	private Per perPanel = new Per();
	private Tar tarPanel = new Tar();
	private Btn btnPanel = new Btn();
	
	/** Container for holding the panels */
	Container pane = this.getContentPane();
	
	/**
	 * Constructor for the registration window.
	 * @param windowName is the name of the window.
	 * @throws ParseException 
	 */
	public Insert(String windowName) throws ParseException {
		super(windowName);
		
		this.setLayout(new GridLayout(3,1));
		this.add(this.perPanel);
		this.add(this.btnPanel);
		
		
		cmbEntity.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				switch((String)cmbEntity.getSelectedItem()) {
					case "Pessoa":
						pane.remove(tarPanel);
						pane.add(perPanel);
						break;
					case "Tarefa":
						pane.remove(perPanel);
						pane.add(tarPanel);
						break;
					case "Participantes":
						break;
				}
			}
			
		});
		
		this.btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					insert((JPanel)getContentPane());
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
			
		});
	}
	
	/**
	 * Gathers the data inserted into the fields of the panel to call <code>Model.insert(Model)</code>.
	 * @param panel is the panel to gather the data from.
	 * @throws SQLException if sql happens lol
	 * @throws IllegalArgumentException if the panel is invalid.
	 */
	private static void insert(JPanel panel) throws IllegalArgumentException, SQLException {
		if(panel instanceof Per) {
			Per pessoa = (Per)panel;
			Model.insert(new Pessoa(pessoa.txtNome.getText(), pessoa.txtEmail.getText()));
		} else if(panel instanceof Tar) {
			Tar tarefa = (Tar)panel;
			Model.insert(new Tarefa(tarefa.txtTitulo.getText(), tarefa.txtDesc.getText(), tarefa.txtPrazo.getText(), tarefa.txtPrazoInicio.getText(), tarefa.txtPrazoTermino.getText(), tarefa.cmbMetodo.getSelectedIndex()));
		}
	}
	
	public static void main(String...args) {
		//new Insert("Inserir", Window.TAREFA);
		try {
			new Insert("Inserir").setLocationRelativeTo(null);
		} catch (ParseException exp) {
			exp.printStackTrace();
		}
	}
	
}

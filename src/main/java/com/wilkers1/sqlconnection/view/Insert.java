package com.wilkers1.sqlconnection.view;

import java.awt.Container;
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
			this.add(this.lblTitulo,       this.field[2][0]); //labels
			this.add(this.lblDesc,         this.field[3][0]);
			this.add(this.lblPrazo,        this.field[4][0]);
			this.add(this.lblPrazoInicio,  this.field[5][0]);
			this.add(this.lblPrazoTermino, this.field[6][0]);
			this.add(this.lblMetodo,       this.field[7][0]);
			this.add(this.txtTitulo,       this.field[2][1]); //fields
			this.add(this.scrDesc,         this.field[3][1]);
			this.add(this.txtPrazo,        this.field[4][1]);
			this.add(this.txtPrazoInicio,  this.field[5][1]);
			this.add(this.txtPrazoTermino, this.field[6][1]);
			this.add(this.cmbMetodo,       this.field[7][1]);
			
		}
	}
	
	/** Label for entity tyoe selector */
	private JLabel lblEntity = new JLabel("Tipo de entidade:");
	/** Entity selector combo box, for switching between panels */
	private JComboBox<String> cmbEntity = new JComboBox<String>(new String[] {"Pessoa", "Tarefa", "Participantes"});
	
	private Per perPanel = new Per();
	private Tar tarPanel = new Tar();
	
	/** JPanel instance for holding the action buttons */
	private JPanel paneBtn = new JPanel(false);
	/** Registration button for the database */
	private JButton btnRegister = new JButton("Register");
	/** Deletion button for the database */
	private JButton btnDelete = new JButton("Delete");
	/**  Clear button for clearing the text fields. */
	private JButton btnClear = new JButton("Clear");
	
	/**
	 * Constructor for the registration window.
	 * @param windowName is the name of the window.
	 * @throws ParseException 
	 */
	public Insert(String windowName) throws ParseException {
		super(windowName);
		
		this.paneBtn.setLayout(new GridLayout(3,1));
		this.paneBtn.add(this.btnRegister);
		this.paneBtn.add(this.btnClear);
		
		this.setContentPane(this.perPanel);
		
		cmbEntity.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				switch((String)cmbEntity.getSelectedItem()) {
					case "Pessoa":
						pane = perPanel;
						break;
					case "Tarefa":
						pane = tarPanel;
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
			new Insert("Inserir");
		} catch (ParseException exp) {
			exp.printStackTrace();
		}
	}
	
}

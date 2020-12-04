package Exercicio_BD_IG;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class IG {

	private JFrame frame;
	private JTextField idTxt;
	private JTextField nomeTxt;
	private JTextField emailTxt;
	private JTextField rendaTxt;
	private JTextField qtdePessoasTxt;
	
	BancoDados bd = new BancoDados();	
	DefaultListModel model = new DefaultListModel();

	public void setIdTxt(JTextField idTxt) {
		this.idTxt = idTxt;
	}	

	public void setNomeTxt(JTextField nomeTxt) {
		this.nomeTxt = nomeTxt;
	}


	public void setEmailTxt(JTextField emailTxt) {
		this.emailTxt = emailTxt;
	}


	public void setRendaTxt(JTextField rendaTxt) {
		this.rendaTxt = rendaTxt;
	}


	public void setQtdePessoasTxt(JTextField qtdePessoasTxt) {
		this.qtdePessoasTxt = qtdePessoasTxt;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IG window = new IG();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IG() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 999, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cadastro de Pessoas");
		frame.getContentPane().setLayout(null);
		
		JLabel idLabel = new JLabel("Identificador");
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setBounds(54, 35, 109, 14);
		frame.getContentPane().add(idLabel);
		
		idTxt = new JTextField();
		idTxt.setBounds(173, 32, 52, 20);
		frame.getContentPane().add(idTxt);
		idTxt.setColumns(10);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeLabel.setBounds(104, 84, 59, 14);
		frame.getContentPane().add(nomeLabel);
		
		nomeTxt = new JTextField();
		nomeTxt.setBounds(173, 81, 140, 20);
		frame.getContentPane().add(nomeTxt);
		nomeTxt.setColumns(10);
		
		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setBounds(95, 123, 68, 14);
		frame.getContentPane().add(emailLabel);
		
		emailTxt = new JTextField();
		emailTxt.setBounds(173, 120, 140, 20);
		frame.getContentPane().add(emailTxt);
		emailTxt.setColumns(10);
		
		JLabel rendaLabel = new JLabel("Renda familiar");
		rendaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		rendaLabel.setBounds(54, 161, 109, 14);
		frame.getContentPane().add(rendaLabel);
		
		rendaTxt = new JTextField();
		rendaTxt.setBounds(173, 158, 98, 20);
		frame.getContentPane().add(rendaTxt);
		rendaTxt.setColumns(10);
		
		JLabel qtdePessoasLabel = new JLabel("Qtde Pessoas na fam\u00EDlia");
		qtdePessoasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		qtdePessoasLabel.setBounds(10, 200, 153, 14);
		frame.getContentPane().add(qtdePessoasLabel);
		
		qtdePessoasTxt = new JTextField();
		qtdePessoasTxt.setBounds(173, 197, 58, 20);
		frame.getContentPane().add(qtdePessoasTxt);
		qtdePessoasTxt.setColumns(10);
		
		JList lista = new JList();
		lista.setFont(new Font("Courier New", Font.PLAIN, 10));
		lista.setBorder(new LineBorder(new Color(0, 0, 0)));
		lista.setBounds(410, 34, 518, 198);
		frame.getContentPane().add(lista);
		
		JLabel listaLabel = new JLabel("Identificador / Nome / E-mail / Renda Familiar / Qtde Pessoas");
		listaLabel.setBounds(411, 11, 354, 14);
		frame.getContentPane().add(listaLabel);
		
		JButton listaBotao = new JButton("Listar");
		listaBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				lista.setModel(model);
				List<Pessoa> pessoas = new ArrayList<>();
				try {
					pessoas = bd.listarPessoas();
				
				}
				catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Erro: "+erro.getMessage());				
				}
				
				String espacos25 = String.format("%" + 25 + "s", "").replaceAll(" ", " ");
				for (Pessoa p : pessoas) {
					model.addElement(   (Integer.toString(p.getId()) + espacos25).substring(0, 4)+ "  " +
										(p.getNome() + espacos25).substring(0, 25) + "  " + 
										(p.getEmail() + espacos25).substring(0, 25) + "  " + 
										(p.getRendaFamiliar() + espacos25).substring(0, 10) + "  " + 
										(Integer.toString(p.getQtdePessoas()) + espacos25).substring(0, 4));
					lista.setModel(model);
				}

			}
		});
		listaBotao.setBounds(626, 241, 89, 23);
		frame.getContentPane().add(listaBotao);
		
		JButton incluirBotao = new JButton("Incluir");
		incluirBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa p = new Pessoa(nomeTxt.getText(), emailTxt.getText(), Float.parseFloat(rendaTxt.getText()), Integer.parseInt(qtdePessoasTxt.getText()));
				bd.inserirUsuario(p);
				bd.consultarId(p);
			}
		});
		incluirBotao.setBounds(14, 274, 87, 23);
		frame.getContentPane().add(incluirBotao);
		
		JButton alterarBotao = new JButton("Alterar");
		alterarBotao.setEnabled(false);
		alterarBotao.setBounds(219, 274, 87, 23);
		frame.getContentPane().add(alterarBotao);
		
		JButton consultarbotao = new JButton("Consultar");
		consultarbotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa p = new Pessoa(Integer.parseInt(idTxt.getText()));
				Pessoa pes = bd.consultarUsuario(p);
				nomeTxt.setText(pes.getNome());
				emailTxt.setText(pes.getEmail());
				rendaTxt.setText(String.valueOf(pes.getRendaFamiliar()));
				qtdePessoasTxt.setText(String.valueOf(pes.getQtdePessoas()));
				alterarBotao.setEnabled(true);
			}
		});
		consultarbotao.setBounds(111, 274, 100, 23);
		frame.getContentPane().add(consultarbotao);
		
		//Ação botão alterar
		alterarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pessoa pes = new Pessoa();
				pes.setId(Integer.parseInt(idTxt.getText()));
				pes.setNome(nomeTxt.getText());
				pes.setEmail( emailTxt.getText());
				pes.setRendaFamiliar( Float.parseFloat(rendaTxt.getText()));
				pes.setQtdePessoas(Integer.parseInt(qtdePessoasTxt.getText()));
				bd.alterarUsuario(pes);
			}
		});
		
		
		JButton excluirBotao = new JButton("Excluir");
		excluirBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa p = new Pessoa(Integer.parseInt(idTxt.getText()));
				bd.consultarUsuario(p);
				bd.excluirUsuario(p);
			}
		});
		excluirBotao.setBounds(316, 274, 87, 23);
		frame.getContentPane().add(excluirBotao);
		
	}
}

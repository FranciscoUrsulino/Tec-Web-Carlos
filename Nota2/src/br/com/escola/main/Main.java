package br.com.escola.main;

import java.util.Date;

import javax.swing.JOptionPane;

import br.com.escola.dao.EscolaDAO;
import br.com.escola.model.Aluno;

public class Main {

	public static void main(String[] args) {
		
		EscolaDAO escolaDao = new EscolaDAO ();
		String opcao;
		while (true) {
			opcao = JOptionPane.showInputDialog("Digite a opção:\n" + 
		"1 - Cadastrar Aluno:\n"+
		"2 - Editar dados:\n"+
		"3 - Deletar:\n"+
		"4 - Mostrar Turma:\n"+
		"5 - Sair");
			switch (opcao) {
				case "1":
					
					Aluno aluno = new Aluno();
					aluno.setNome(JOptionPane.showInputDialog("Informe o nome:\n"));
					aluno.setEmail(JOptionPane.showInputDialog("Informe o email:\n"));
					aluno.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Informe a idade:\n")));
					aluno.setDataCadastro(new Date());
					
					escolaDao.save(aluno);
					
					break;
				case "2":
					
					Aluno c1 = new Aluno();
					
					c1.setId(Integer.parseInt(JOptionPane.showInputDialog("Informe o id do aluno que deseja editar:\n")));
					c1.setNome(JOptionPane.showInputDialog("Informe o novo nome:\n"));
					c1.setEmail(JOptionPane.showInputDialog("Informe o novo email:\n"));
					c1.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Informe a nova idade:\n")));
					c1.setDataCadastro(new Date());
					
					escolaDao.update(c1);
					
					break;
				
				case "3":
					escolaDao.deleteByID(Integer.parseInt(JOptionPane.showInputDialog("Informe a ID que deseja deletar:\n")));
					
					break;
					
				case "4":
					for (Aluno c : escolaDao.getAlunos()) {
					System.out.println("Aluno: "+c.getNome()+" - ID: "+c.getId()+" - Data de cadastro: "+c.getDataCadastro());
					}
					break;
					
				case "5":
					System.exit(0);
					break;
					
				default:
					System.out.println("Selecione outra opção");
					
			}
		}
	
		}
	}



package br.com.escola.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.escola.factory.ConnectionFactory;
import br.com.escola.model.Aluno;

public class EscolaDAO {
	
	public void save(Aluno aluno) {
		
		String sql = "INSERT INTO dados(nome, email, idade, Datacadastro) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm  = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, aluno.getNome());
			pstm.setString(2, aluno.getEmail());
			pstm.setInt(3, aluno.getIdade());
			pstm.setDate(4, new Date(aluno.getDataCadastro().getTime()));
			
			pstm.execute();
			
			System.out.println("Aluno Salvo no BD!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void update (Aluno aluno) {
		
		String sql = "UPDATE dados SET nome = ?, email = ?, idade = ?, datacadastro = ? "+
		"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, aluno.getNome());
			pstm.setString(2, aluno.getEmail());
			pstm.setInt(3, aluno.getIdade());
			pstm.setDate(4, new Date(aluno.getDataCadastro().getTime()));
			
			pstm.setInt(5, aluno.getId());
			
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void deleteByID (int id) {
		
		String sql = "DELETE FROM dados WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm .setInt(1, id);
			
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public List<Aluno> getAlunos(){
		
		String sql = "SELECT * FROM dados";
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			
			while (rset.next()) {
				
				Aluno aluno = new Aluno();
				
				aluno.setId(rset.getInt("id"));
				aluno.setNome(rset.getString("nome"));
				aluno.setEmail(rset.getString("email"));
				aluno.setIdade(rset.getInt("idade"));
				aluno.setDataCadastro(rset.getDate("datacadastro"));
				
				
				alunos.add(aluno);
			}	
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset!=null) {
						rset.close();
					}
					
					if(pstm!=null) {
						pstm.close();
					}
					if(conn!=null) {
						conn.close();
					}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	return alunos;
	}
}

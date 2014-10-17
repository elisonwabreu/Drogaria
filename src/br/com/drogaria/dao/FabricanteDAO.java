package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	public void inserir(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	public void excluir(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setInt(1, f.getCodigo());
		comando.executeUpdate();
	}

	public void editar(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ?");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.setInt(2, f.getCodigo());

		comando.executeUpdate();
	}

	public Fabricante bucarPorCodigo(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM fabricante ");
		sql.append("SET descricao = ?");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, f.getCodigo());

		ResultSet rs = comando.executeQuery();

		Fabricante retorno = null;

		if (rs.next()) {

			retorno = new Fabricante();
			retorno.setCodigo(rs.getInt("codigo"));
			retorno.setDescricao(rs.getString("descricao"));

		}

		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {  

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM fabricante ");
		sql.append("ORDER BY codigo ASC");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet rs = comando.executeQuery();

		ArrayList<Fabricante> retorno = new ArrayList<>();

		while (rs.next()) {

			Fabricante f = new Fabricante();
			f.setCodigo(rs.getInt("codigo"));
			f.setDescricao(rs.getString("descricao"));
			
			retorno.add(f);

		}

		return retorno;
	}
	
	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM fabricante ");
		sql.append("WHERE descricao LIKE ?");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());

		ResultSet rs = comando.executeQuery();

		ArrayList<Fabricante> retorno = new ArrayList<>();

		while (rs.next()) {

			Fabricante fun = new Fabricante();
			fun.setCodigo(rs.getInt("codigo"));
			fun.setDescricao(rs.getString("descricao"));
			
			retorno.add(fun);

		}

		return retorno;
	}

}

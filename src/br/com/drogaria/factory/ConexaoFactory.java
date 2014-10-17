package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
	private static final String USUARIO = "postgres";
	private static final String SENHA = "jhetro";
	private static final String URL = "jdbc:postgresql://localhost:5432/drogaria";

	public static Connection conectar() throws SQLException{
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		Connection conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
		return conexao;
	}

}

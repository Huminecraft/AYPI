package com.aypi.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class AypiSQL{

	private Connection connection;
	private Statement state;
	
	private String url;
	private int port;
	private String user;
	private String dataBase;
	
	/**
	 * AypiSQL est une extension permettant de se connecter a une
	 * base de donnee <b>POSTGRES</b>
	 * @param server le nom de domaine (DNS) ou l'ip du server auquel se connecter
	 * @param port le port de connexion
	 * @param user le nom d'utilisateur
	 * @param passwd le mot de passe associe avec le nom d'utilisateur
	 * @param dataBase la Base de donnee auquel se connecter
	 * @param result le type de lecture de la base de donnee
	 * @throws SQLException les exceptions les plus souvents levees sont des
	 * exception <b>adresse introuvable</b> ou <b>mot de passe invalide</b>
	 */
	public AypiSQL(String server, int port, String user, String passwd, String dataBase, AypiSQLResult result) throws SQLException {
		this.port = port;
		this.user = user;
		this.dataBase = dataBase;
		
		this.url = "jdbc:postgresql://" + server + ":" + port + "/" + dataBase;
		
		this.connection = DriverManager.getConnection(this.url, this.user, passwd);
		
		if(result == AypiSQLResult.READ_ONLY)
			this.state = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		else
			this.state = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}
	
	/**
	 * AypiSQL est une extension permettant de se connecter a une
	 * base de donnee <b>POSTGRES</b> (LE PORT N'EST PAS SPECIFIE DANS CE CONSTRUCTEUR)
	 * PORT PAR DEFAUT : <b>5432</b>
	 * @param server le nom de domaine (DNS) ou l'ip du server auquel se connecter
	 * @param user le nom d'utilisateur
	 * @param passwd le mot de passe associe avec le nom d'utilisateur
	 * @param dataBase la Base de donnee auquel se connecter
	 * @param result le type de lecture de la base de donnee
	 * @throws SQLException les exceptions les plus souvents levees sont des
	 * exception <b>adresse introuvable</b> ou <b>mot de passe invalide</b>
	 */
	public AypiSQL(String server, String user, String passwd, String dataBase, AypiSQLResult result) throws SQLException {
		this(server, 5432, user, passwd, dataBase, result);
	}
	
	/**
	 * Permet d'executer une commande SQL <b>(ex: insert into table values(...))</b>
	 * @param cmd la commande SQL a executer
	 * @return true si la commande c'est execute, sinon false
	 * @throws SQLException
	 */
	public boolean execute(String cmd) throws SQLException {
		return this.state.execute(cmd);
	}
	
	/**
	 * Permet d'executer une commande SQL <b>(ex: select * from table)</b>
	 * et de recuperer la table de reponse de votre commande
	 * @param cmd la commande SQL a executer
	 * @return une Objet Result d'avoir la table de reponse a la commande
	 */
	public Result executeQuery(String cmd) {
		try {
			Result result = new Result();
			result.setResultSet(this.state.executeQuery(cmd));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public Statement getState() {
		return state;
	}

	public String getUrl() {
		return url;
	}

	public int getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getDataBase() {
		return dataBase;
	}
}

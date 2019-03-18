package com.aypi.sql;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.bukkit.command.ConsoleCommandSender;

public class Result {

	private ResultSet resultSet;
	private ResultSetMetaData meta;

	/**
	 * Permet de stocker une table resultante
	 */
	public Result() {
		this.resultSet = null;
		this.meta = null;
	}

	/**
	 * @return le ResulSet
	 */
	public ResultSet getResultSet() {
		return resultSet;
	}

	/**
	 * Initialise le ResultSet
	 * @param resultSet
	 * @throws SQLException
	 */
	public void setResultSet(ResultSet resultSet) throws SQLException {
		this.resultSet = resultSet;
		this.meta = this.resultSet.getMetaData();
	}

	/**
	 * @return le meta de ResultSet
	 */
	public ResultSetMetaData getMeta() {
		return meta;
	}

	/**
	 * Afficher la table complete
	 * @param stream la ou il faut envoyer la table
	 * @throws SQLException
	 * @throws IOException
	 */
	public void printTable(Writer stream) throws SQLException, IOException {
		for (int i = 1; i <= this.meta.getColumnCount(); i++) {
			stream.write("|\t" + this.meta.getColumnName(i).toUpperCase() + "\t|");
			stream.flush();
		}
		
		stream.write("============================\n");
		stream.flush();
		
		while (this.resultSet.next()) {
			for (int i = 1; i <= this.meta.getColumnCount(); i++) {
				stream.write("|\t" + this.resultSet.getString(i) + "\t|");
				stream.flush();
				stream.write("-------------------------");
				stream.flush();
			}
		}
		
	}
	
	/**
	 * Afficher la table complete
	 * @param console dans la console dans laquelle afficher
	 * @throws SQLException
	 */
	public void printTable(ConsoleCommandSender console) throws SQLException {
		for (int i = 1; i <= this.meta.getColumnCount(); i++) {
			console.sendMessage("|\t" + this.meta.getColumnName(i).toUpperCase() + "\t|");
		}
		
		console.sendMessage("============================\n");
		
		while (this.resultSet.next()) {
			for (int i = 1; i <= this.meta.getColumnCount(); i++) {
				console.sendMessage("|\t" + this.resultSet.getString(i) + "\t|");
				console.sendMessage("-------------------------");
			}
		}
		
	}
	
	/**
	 * @return les noms des colonnes de la table
	 * @throws SQLException
	 */
	public String getColumnsName() throws SQLException {
		String str = "";
		for (int i = 1; i <= this.meta.getColumnCount(); i++) {
			str += "|" + this.meta.getColumnName(i).toUpperCase() + "|";
		}
		
		return str;
	}
	
	/**
	 * @param column la colonne choisi
	 * @return le nom de la colonne
	 * @throws SQLException
	 */
	public String getColumnName(int column) throws SQLException {
		return this.meta.getColumnName(column).toUpperCase();
	}
	
	/**
	 * Permet de recuperer dans une ligne et une colonne precise
	 * @param row la ligne a choisir
	 * @param column la colonne a choisir
	 * @return l'element selectionner
	 * @throws SQLException
	 */
	public String getCell(int row, int column) throws SQLException {
		String cell = getRow(row);
		cell = cell.split("|")[column-1];
		return cell;
	}
	
	/**
	 * recupere tout les elements de la ligne choisi
	 * @param row la ligne en question
	 * @return tout les elements de la ligne
	 * @throws SQLException
	 */
	public String getRow(int row) throws SQLException {
		String str = "";
		
		while(this.resultSet.next() && this.resultSet.getRow() != row) {};
		
		for (int i = 1; i <= this.meta.getColumnCount(); i++) {
			str += "|" + this.resultSet.getString(i) + "|";
		}
		
		return str;
	}
	
	/**
	 * @return le nombre de ligne dans la table
	 * @throws SQLException
	 */
	public int getRowCount() throws SQLException {
		if(this.resultSet.next())
			return 1 + getRowCount();
		else
			return 0;
	}
	
	/**
	 * retourne au debut de la table
	 * @throws SQLException
	 */
	public void returnToStart() throws SQLException {
		this.resultSet.beforeFirst();
	}

	/**
	 * Permet d'aller a la ligne suivante de la table
	 * @return vrai si il reste des lignes a parcourir, sinon false
	 * @throws SQLException 
	 */
	public boolean next() throws SQLException {
		return this.resultSet.next();
	}
	
	/**
	 * @return le nombre de colonne dans la table
	 * @throws SQLException
	 */
	public int getColumnCount() throws SQLException {
		return this.meta.getColumnCount();
	}
	
	/**
	 * Renvoie le contenu par rapport a la ligne actuelle
	 * @param n
	 * @return le contenu
	 * @throws SQLException
	 */
	public String getString(int n) throws SQLException {
		return this.resultSet.getString(n);
	}
	
	/**
	 * Renvoie le contenu par rapport a la ligne actuelle
	 * @param n
	 * @return le contenu
	 * @throws SQLException
	 */
	public int getInt(int n) throws SQLException {
		return this.resultSet.getInt(n);
	}
	
	/**
	 * permet de fermer la connexion avec la base de donnee
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		this.resultSet.close();
	}
}

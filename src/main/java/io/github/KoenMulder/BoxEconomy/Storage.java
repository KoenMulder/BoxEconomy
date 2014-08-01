package io.github.KoenMulder.BoxEconomy;

import io.github.KoenMulder.BoxEconomy.BankAccount;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import java.util.logging.Logger;

import com.google.gson.Gson;

/*
 * Ugh, had to use SQLite because LevelDB-JNI kept throwing 'java.lang.UnsatisfiedLinkError: org.fusesource.leveldbjni.internal.NativeOptions.init()V'
 * It's overly complicated... but some day the plugin might switch to leveldb again, if it works...
 */

public class Storage {
	private static Connection db;
	private static String dbFile;
	private static Gson gson;
	
	static {
		dbFile = Bukkit.getPluginManager().getPlugin("BoxEconomy").getDataFolder().getPath() + "/data.db";
		try {
			Class.forName("org.sqlite.JDBC");
			db = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
		
		gson = new Gson();
	}
	
	public static void init() throws SQLException {
		Statement s = db.createStatement();
		String sql =
				"CREATE TABLE IF NOT EXISTS accounts (" +
				"name TEXT PRIMARY KEY, " +
				"data TEXT" +
				");";
		s.executeUpdate(sql);
	    s.close();
	}
	
	public static void setAccountValue(String key, BankAccount val) {
		String json = gson.toJson(val);
		Statement s = null;
		
		try {
			s = db.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			s.executeUpdate(
					"INSERT OR REPLACE INTO accounts (name, data)" +
					"VALUES ( '" + key + "', '" + json + "');"
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// db.put(bytes(key), bytes(gson.toJson(val)));
	}
	
	public static BankAccount getAccountValue(String name) {
		Statement s = null;
		String json = null;
		
		try {
			s = db.createStatement();
			ResultSet rs = s.executeQuery(
					"SELECT * FROM accounts WHERE name = '" + name + "';"
			);
		
			while (rs.next()) {
				json = rs.getString("data");
			}
			s.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Logger.getLogger("Minecraft").info(json);
		
		return gson.fromJson(json, BankAccount.class);
		 
		//return gson.fromJson(db.get(bytes(key)).toString(), null);
	}
}

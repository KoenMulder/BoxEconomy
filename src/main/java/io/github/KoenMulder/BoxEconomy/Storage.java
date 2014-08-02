package io.github.KoenMulder.BoxEconomy;

import io.github.KoenMulder.BoxEconomy.BankAccount;
import io.github.KoenMulder.BoxEconomy.TradeRequest;

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
		String accounts =
				"CREATE TABLE IF NOT EXISTS accounts (" +
				"name TEXT PRIMARY KEY, " +
				"data TEXT" +
				");";
		String tradeRequests =
				"CREATE TABLE IF NOT EXISTS trade_requests (" +
				"player TEXT PRIMARY KEY, " +
				"target TEXT, " +
				"data TEXT" +
				");";
		String auctions =
				"CREATE TABLE IF NOT EXISTS auctions (" +
				"name TEXT PRIMARY KEY, " +
				"data TEXT" +
				");";
		s.executeUpdate(accounts);
		s.executeUpdate(tradeRequests);
		s.executeUpdate(auctions);
	    s.close();
	}
	
	public static void setAccount(String key, BankAccount val) {
		String json = gson.toJson(val);
		Statement s = null;
		
		try {
			s = db.createStatement();
		
			s.executeUpdate(
					"INSERT OR REPLACE INTO accounts (name, data)" +
					"VALUES ( '" + key + "', '" + json + "');"
			);
		
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static BankAccount getAccount(String name) {
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
	}
	
	public static void addTradeRequest(TradeRequest data) {
		String json = gson.toJson(data);
		Statement s = null;
		
		try {
			s = db.createStatement();
		
			s.executeUpdate(
					"INSERT OR REPLACE INTO trade_requests (player, target, data)" +
					"VALUES ("
						+ "'" + data.playerId + "', "
						+ "'" + data.targetId + "', "
						+ "'" + json + "'"
					+ ");"
			);
		
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeTradeRequest(String field, String value) {
		Statement s = null;
		
		try {
			s = db.createStatement();
			s.executeUpdate(
					"DELETE FROM trade_requests WHERE " + field + " = '" + value + "';"
			);
		
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static TradeRequest getTradeRequest(String field, String value) {
		Statement s = null;
		String json = null;
		
		try {
			s = db.createStatement();
			ResultSet rs = s.executeQuery(
					"SELECT * FROM trade_requests WHERE " + field + " = '" + value + "';"
			);
		
			while (rs.next()) {
				json = rs.getString("data");
			}
			s.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return gson.fromJson(json, TradeRequest.class);
	}
}

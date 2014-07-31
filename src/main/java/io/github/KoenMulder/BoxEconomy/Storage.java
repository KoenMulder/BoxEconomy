package io.github.KoenMulder.BoxEconomy;

import io.github.KoenMulder.BoxEconomy.BankAccount;

import org.iq80.leveldb.*;
import static org.fusesource.leveldbjni.JniDBFactory.*;
import java.io.*;

import com.google.gson.Gson;

public class Storage {
	private static DB db;
	private static Gson gson;
	
	static {
		gson = new Gson();
		try {
			db = factory.open(new File("db"), new Options());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setValue(String key, BankAccount val) {
		db.put(bytes(key), bytes(gson.toJson(val)));
	}
	
	public static BankAccount getValue(String key) {
		return gson.fromJson(db.get(bytes(key)).toString(), null);
	}
}

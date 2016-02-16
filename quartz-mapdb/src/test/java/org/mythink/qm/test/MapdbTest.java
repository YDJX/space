package org.mythink.qm.test;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class MapdbTest {

	
	public static void main(String[] args){
		DB db = DBMaker.fileDB(new File("test.db")).make();
		ConcurrentNavigableMap<String, String> treeMap =  db.treeMap("test");
		treeMap.put("name", "job1");
		db.commit();
		db.close();
		
		DB db1 = DBMaker.fileDB(new File("test.db")).make();
		ConcurrentNavigableMap<String, String> treeMap1 =  db1.treeMap("test");
		System.out.println(treeMap1.get("name"));
		db1.close();
		
	}
}

package com.lanou.exam;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class jdbcUtils {
	 private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
	  private static final String URL = "jdbc:mysql://localhost:3306/homework?useSSL=false";
	  private static final String USENAME = "root";
	  private static final String PASSWORD = "zjtzjy137381";
	  
	  //通用的增删改
	   public static int UpDate(String sql,Object...parameters) throws Exception {
		     Class.forName(DRIVERNAME);
		     try(Connection con = DriverManager.getConnection(URL, USENAME, PASSWORD);
		    		 PreparedStatement ps = con.prepareStatement(sql);){
		    	       for(int i=0;i<parameters.length;i++) {
		    	    	   ps.setObject(i+1, parameters[i]);
		    	       }
		    	 return ps.executeUpdate();
		    	 
		     }
		        
	}
	  
		
	  //通用的查询 
	 public static<T> List<T> query(String sql,Class<T>class1,Object...parameters) throws Exception {
		Class.forName(DRIVERNAME);
		List<T> list = new ArrayList<T>();
		try(Connection con = DriverManager.getConnection(URL, USENAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(sql);){
			
			for(int i = 0;i<parameters.length;i++) {
				 ps.setObject(i+1, parameters[i]);
			}
			    ResultSet  rs = ps.executeQuery();
			  //获取结果集元数据
			    ResultSetMetaData rsmd = rs.getMetaData();
			    int columnCount = rsmd.getColumnCount();
			    while(rs.next()) {
			    	T t= class1.newInstance();
			    	for(int i = 1;i<=columnCount;i++) {
			    		//读取列名
			    		String columnName = rsmd.getColumnLabel(i);
			    		//通过反射获取与列名相同的java类的属性。
			    		Field field = class1.getDeclaredField(columnName);
			    		field.setAccessible(true);
			    		field.set(t, rs.getObject(i));
			    	}
			    	list.add(t);
			    }
			
			
		}
		
		 return list;
		 
	}  
	   
	
	
}

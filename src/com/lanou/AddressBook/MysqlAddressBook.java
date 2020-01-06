package com.lanou.AddressBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;



public class MysqlAddressBook {

	public static void main(String[] args) throws Exception {
		showAllCotacts();
        
        
	}

	
	public static void addContact() throws Exception {
		String sql = "insert into addressBook ( aid,agroup,aname,anumber)values(?,?,?,?)";
        jdbcUtils.UpDate(sql, 1,"Z","张三","12345678912");
        jdbcUtils.UpDate(sql, 2,"Z","张四","18845678912");
        jdbcUtils.UpDate(sql, 3,"W","王五","16645678912");
	}
	
	public static void deleteContact() throws Exception {
		String sql = "delete from addressBook where aname = ? and anumber = ? ";
		jdbcUtils.UpDate(sql);
		
	}
	
	public static void showAllCotacts() throws Exception {
		String sql= "select aid id, agroup group1,aname name,anumber number  from addressbook";
		List<Show> list =jdbcUtils.query(sql, Show.class);
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i).getGroup() +"  "+list.get(i).getName()+"  "+list.get(i).getNumber());
    	}
	}
	
	public static void update() throws Exception {
		String sql = "update addressbook set aid=? ,agroup=?,aname=?,anumber=? ";
		jdbcUtils.UpDate(sql);
	}
	
	
	public static void findCotactsByName() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/homework?useSSL=false";
		try(Connection con = DriverManager.getConnection(url, "root", "zjtzjy137381");){
			Statement st = con.createStatement();
			String sql = "select*from addressbook where aname like '张%' ";
            ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String group = rs.getString("agroup");
				String aname = rs.getString("aname");
				String number = rs.getString("anumber");
				System.out.println(  group+" "+aname + " " + number);
			}
           	 
		}
		
		
            }
         
	
	public static void findCotactsBynumber() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/homework?useSSL=false";
		try(Connection con = DriverManager.getConnection(url, "root", "zjtzjy137381");){
			Statement st = con.createStatement();
			String sql = "select*from addressbook where anumber like '1%'";
            ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String group = rs.getString("agroup");
				String aname = rs.getString("aname");
				String number = rs.getString("anumber");
				System.out.println(  group+" "+aname + " " + number);
			}
           	 
		}
		
		
            }
         
	
	public static void findCotactsBygroup() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/homework?useSSL=false";
		try(Connection con = DriverManager.getConnection(url, "root", "zjtzjy137381");){
			Statement st = con.createStatement();
			String sql = "select *from addressbook  where agroup = 'Z'";
            ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String group = rs.getString("agroup");
				String aname = rs.getString("aname");
			String number = rs.getString("anumber");
				System.out.println(  group+" "+aname + " " + number);
			}
           	 
		}
		 
		
            }
	
	
  }
        
        
        
	
	
	
	
	
	
	
	
	
	


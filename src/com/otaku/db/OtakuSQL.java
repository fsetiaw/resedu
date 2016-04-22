package com.otaku.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Collections;

import org.apache.tomcat.jdbc.pool.*;

import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.StringTokenizer;

public class OtakuSQL {

	private static DataSource ds = null;
	private static Context context = null;
	private static final String alamatIp = "http://localhost:8080/com.otaku.rest/api/";
	
    private static DataSource connectToUsgDb() {
    	ResultSet rs = null;
    	PreparedStatement stmt = null;
    	Connection con = null;
    	//Context initContext  = new InitialContext();
		//Context envContext  = (Context)initContext.lookup("java:/comp/env");
		//ds = (DataSource)envContext.lookup("jdbc"+beans.setting.Constants.getDbschema());
		//con = ds.getConnection();
    	try {
    		if(context == null) {
    			context = new InitialContext();
    			context = (Context)context.lookup("java:/comp/env");
    		}
    		ds = (DataSource)context.lookup("jdbc/USG");
    		//con = ds.getConnection();
    		
    	}
		catch (NamingException e) {
			e.printStackTrace();
		}
		//catch (SQLException ex) {
		//	ex.printStackTrace();
		//} 
		finally {
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (con!=null) try { con.close();} catch (Exception ignore){}
		}	
    	return ds;
    }
    
	protected static Connection connectToUsg() {
		Connection con = null;
		try {
			ds = connectToUsgDb();
			con = ds.getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private static DataSource connectToBackupUsgDb() {
    	ResultSet rs = null;
    	PreparedStatement stmt = null;
    	Connection con = null;
    	//Context initContext  = new InitialContext();
		//Context envContext  = (Context)initContext.lookup("java:/comp/env");
		//ds = (DataSource)envContext.lookup("jdbc"+beans.setting.Constants.getDbschema());
		//con = ds.getConnection();
    	try {
    		if(context == null) {
    			context = new InitialContext();
    			context = (Context)context.lookup("java:/comp/env");
    		}
    		ds = (DataSource)context.lookup("jdbc/backupUSG");
    		//con = ds.getConnection();
    		
    	}
		catch (NamingException e) {
			e.printStackTrace();
		}
		//catch (SQLException ex) {
		//	ex.printStackTrace();
		//} 
		finally {
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (con!=null) try { con.close();} catch (Exception ignore){}
		}	
    	return ds;
    }
    
	protected static Connection connectToBackupUsg() {
		Connection con = null;
		try {
			ds = connectToBackupUsgDb();
			con = ds.getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	protected boolean isStringNullOrEmpty(String word) {
    	boolean empty = true;
    	if(word!=null) {
    		StringTokenizer st = new StringTokenizer(word);
    		if(st.countTokens()>0) {
    			String tkn = st.nextToken();
    			if(!tkn.equalsIgnoreCase("null") && !tkn.equalsIgnoreCase("NULL")) {
    				empty = false;
    			}
    		}
    	}
    	//System.out.println("checker word="+word+" "+empty);
    	return empty;
    }
	
	/*
	 * sama dengan yg ada pada DATE FORMATED di USG
	 */
	protected String prepStringDateToDbInputFormat(String ddmmyyyy) {
    	StringTokenizer st = null;
    	if(ddmmyyyy.contains("/")) {
    		st = new StringTokenizer(ddmmyyyy,"/");
    	}
    	else {
    		if(ddmmyyyy.contains("-")) {
        		st = new StringTokenizer(ddmmyyyy,"-");
        	}
        	else {
        		if(ddmmyyyy.contains("_")) {
            		st = new StringTokenizer(ddmmyyyy,"_");
            	}
            	else {
            		
            	}
        	}
    	}
    	String dd = st.nextToken();
    	String mm = st.nextToken();
    	String yy = st.nextToken();
    	if(dd.length()==1) {
    		dd = "0"+dd;
    	}
    	else if(dd.length()==4 && yy.length()<=2){
    		String tmp_thn = ""+dd;
    		dd = ""+yy;
    		yy = ""+tmp_thn;
    		if(dd.length()==1) {
        		dd = "0"+dd;
        	}
    	}
    	if(mm.length()==1) {
    		mm = "0"+mm;
    	}
    	return yy+"-"+mm+"-"+dd;
    }
    
    /*
     * from db format ke tampian di form
     * 
     * 
	 * sama dengan yg ada pada DATE FORMATED di USG
	 */
     
    protected String prepStringFromDbDateToInputFormFormat(String ddmmyyyy) {
    	StringTokenizer st = null;
    	if(ddmmyyyy.contains("/")) {
    		st = new StringTokenizer(ddmmyyyy,"/");
    	}
    	else {
    		if(ddmmyyyy.contains("-")) {
        		st = new StringTokenizer(ddmmyyyy,"-");
        	}
        	else {
        		if(ddmmyyyy.contains("_")) {
            		st = new StringTokenizer(ddmmyyyy,"_");
            	}
            	else {
            		
            	}
        	}
    	}
    	String dd = st.nextToken();
    	String mm = st.nextToken();
    	String yy = st.nextToken();
    	if(dd.length()==1) {
    		dd = "0"+dd;
    	}
    	else if(dd.length()==4 && yy.length()<=2){
    		String tmp_thn = ""+dd;
    		dd = ""+yy;
    		yy = ""+tmp_thn;
    		if(dd.length()==1) {
        		dd = "0"+dd;
        	}
    	}
    	if(mm.length()==1) {
    		mm = "0"+mm;
    	}
    	return dd+"/"+mm+"/"+yy;
    }
	
    protected boolean validateTokenTransaction(String token) {
    	double value = 0;
    	boolean boolean_value = false;
    	StringTokenizer st = new StringTokenizer(token,"`");
    	String time_stamp = st.nextToken();
    	String npm = st.nextToken();
    	String value_to_test = st.nextToken();
    	
    	st = new StringTokenizer(time_stamp);
    	String date_part = st.nextToken();
    	date_part = date_part.replace("-", "");
    	Double top = Double.parseDouble(date_part);
    	
    	String time_part = st.nextToken();
    	time_part = time_part.replace(":", "");
    	Double bottom = Double.parseDouble(time_part);
    	
    	value = top.doubleValue()/bottom.doubleValue();
    	int npm_db = Integer.valueOf(npm);
    	npm = ""+npm_db;
    	for(int i=0; i<npm.length();i++) {
    		if(i%2==0) {
    			value = value + Double.valueOf(npm.charAt(i));
    		}
    		else {
    			value = value - Double.valueOf(npm.charAt(i));
    		}	
    	}
    	//System.out.println("value to test otaku = "+value_to_test);
    	if(value_to_test.equalsIgnoreCase(value+"")) {
    		boolean_value = true;
    	
    	}
    	return boolean_value;
    }
    
    protected Timestamp getCurrentTimestamp() 
    {
    	java.util.Date date= new java.util.Date();
    	return (new Timestamp(date.getTime()));
    }
    
    public String convertBackVariableFromUrlRest(String var) {
    	/*
    	 * harus sync dengan ISU.convertBackVariableFromUrlRest();
    	 */
    	var = var.replace("-tanda0-", " ");
    	var = var.replace("-tanda1-", "/");
    	var = var.replace("-tanda2-", "&");
    	var = var.replace("-tanda3-", "#");
    	var = var.replace("-tanda4-", "`");
    	var = var.replace("-tanda5-", "%");
    	var = var.replace("-tanda6-", "|");
    	var = var.replace("-tanda7-", "<");
    	var = var.replace("-tanda8-", ">");
    	var = var.replace("-tanda9-", "\"");
	  	return var;
    }
}

package com.otaku.db;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.owasp.esapi.ESAPI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.NamingException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.otaku.util.VectorTools;
import com.otaku.util.converter.ToJson;
import java.util.ListIterator;


public class SearchQueryUsrDatTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	String returnString = null;

	public Response validateUsrPwd(String usr, String pwd) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select * from USR_DAT where USR_NAME=? and USR_PWD=?");
			stmt.setString(1,usr);
			stmt.setString(2, pwd);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()) {
				returnString = "[{\"VALID\":\"false\"}]";
			}
			else {
				rs.beforeFirst();
				ToJson converter = new ToJson();
	    		JSONArray joba = new JSONArray();
	    		
	    		joba = converter.toJSONArray(rs);
	    		returnString = joba.toString();
	    		//res = Response.ok(returnString).build();
			}
			
			res = Response.ok(returnString).build();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (conn!=null) try { conn.close();} catch (Exception ignore){}
		}	
		return res;
	}

	/*
	public Response returnTargetAkunKdjenBased(String target_mama_master_pos_rev, String target_keter_shift, String target_kdjen) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select NAMA_AKUN_LOOKUP,SHIFT_LOOKUP,HARI_LOOKUP from AKUN_SHIFT_POS inner join POS_REVENUE on ID_POS=ID where NAMA_MASTER_POS=? and KETERANGAN_SHIFT=? and AKUN_SHIFT_POS.TKN_KDJEN_AVAILIBILITY LIKE ?");
			stmt.setString(1,target_mama_master_pos_rev);
			stmt.setString(2, target_keter_shift);		
			stmt.setString(3,"%"+target_kdjen+"%");
	
			ResultSet rs = stmt.executeQuery();
			
			ToJson converter = new ToJson();
    		JSONArray joba = new JSONArray();
    		
    		joba = converter.toJSONArray(rs);
    		String returnString = joba.toString();
    		res = Response.ok(returnString).build();
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (conn!=null) try { conn.close();} catch (Exception ignore){}
		}	
		return res;
	}
	
	public Response returnAkunTunaiKdjenBased(String target_kdjen) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select NAMA_AKUN from AKUN where NAMA_BANK='TUNAI' and TKN_KDJEN_AVAILIBILITY LIKE ?");
			stmt.setString(1,"%"+target_kdjen+"%");
			ResultSet rs = stmt.executeQuery();
			
			ToJson converter = new ToJson();
    		JSONArray joba = new JSONArray();
    		
    		joba = converter.toJSONArray(rs);
    		String returnString = joba.toString();
    		res = Response.ok(returnString).build();
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (conn!=null) try { conn.close();} catch (Exception ignore){}
		}	
		return res;
	}
	*/
}

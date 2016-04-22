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


public class SearchQueryPosRevenueTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	

	public Response returnPosYgDapatDibayarkanTunaiDanBank() throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			//stmt = conn.prepareStatement("select * from AKUN_SHIFT_POS inner join POS_REVENUE on ID_POS=ID where TUNAI_DAN_BANK=true and POS_REVENUE.HAPUS=false order by POS_REVENUE.NICKNAME_POS");
			stmt = conn.prepareStatement("select * from AKUN_SHIFT_POS inner join POS_REVENUE on ID_POS=ID where TUNAI_DAN_BANK=true and POS_REVENUE.HAPUS=false order by POS_REVENUE.NICKNAME_POS");
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

	public Response returnPosYgDapatDibayarkanTunaiDanBank(String kdjen) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			//stmt = conn.prepareStatement("select * from AKUN_SHIFT_POS inner join POS_REVENUE on ID_POS=ID where TUNAI_DAN_BANK=true and AKUN_SHIFT_POS.TKN_KDJEN_AVAILIBILITY LIKE ? and POS_REVENUE.HAPUS=false order by POS_REVENUE.NICKNAME_POS");
			stmt = conn.prepareStatement("select * from AKUN_SHIFT_POS inner join POS_REVENUE on ID_POS=ID where TUNAI_DAN_BANK=true and AKUN_SHIFT_POS.TKN_KDJEN_AVAILIBILITY LIKE ? and POS_REVENUE.HAPUS=false order by POS_REVENUE.NICKNAME_POS");
			stmt.setString(1, "%"+kdjen+"%");
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
	
	public Response returnPosYgDapatDibayarkanTunaiDanBank(String kdjen, String keter_shift) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			//stmt = conn.prepareStatement("select * from AKUN_SHIFT_POS inner join POS_REVENUE on ID_POS=ID where TUNAI_DAN_BANK=true and AKUN_SHIFT_POS.TKN_KDJEN_AVAILIBILITY LIKE ? and KETERANGAN_SHIFT=? and POS_REVENUE=false order by POS_REVENUE.NICKNAME_POS");
			stmt = conn.prepareStatement("select * from AKUN_SHIFT_POS inner join POS_REVENUE on ID_POS=ID where TUNAI_DAN_BANK=true and AKUN_SHIFT_POS.TKN_KDJEN_AVAILIBILITY LIKE ? and KETERANGAN_SHIFT=? and POS_REVENUE.HAPUS=false order by POS_REVENUE.NICKNAME_POS");
			stmt.setString(1, "%"+kdjen+"%");
			stmt.setString(2, keter_shift);
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
	
	/*
	 * =======================================================================================
	 * bakup usg
	 * ==========================================================================================
	 */
	
	public Response returnAllFromBackupPosRevenueTable() throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToBackupUsg();
			//stmt = conn.prepareStatement("select * from AKUN_SHIFT_POS inner join POS_REVENUE on ID_POS=ID where TUNAI_DAN_BANK=true and POS_REVENUE.HAPUS=false order by POS_REVENUE.NICKNAME_POS");
			stmt = conn.prepareStatement("select * from POS_REVENUE");
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
}

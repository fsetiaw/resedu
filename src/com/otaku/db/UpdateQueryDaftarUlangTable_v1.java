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


public class UpdateQueryDaftarUlangTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	/*
	 * return UPDATE_STATUS : int
	 */
	public Response copyDataDaftarUlangRulesFromBaseThsms(String token_trans, String tkn_input_value) throws Exception {
		Response res = null;
		boolean valid = false;
		int upd = 0;
		/*
		 * ALWAYS REMEBER convertBackVariableFromUrlRest input variable
		 */
		//if(token_trans!=null) {
		//	token_trans = convertBackVariableFromUrlRest(token_trans);
		//}
		//if(tkn_input_value!=null) {
		//	tkn_input_value = convertBackVariableFromUrlRest(tkn_input_value);
		//}
		try {
						
			 //validate token
			//System.out.println("start validating"); 
			valid = validateTokenTransaction(token_trans);
			//System.out.println("done validating");
			//System.out.println("valid 00 ="+valid);
			if(valid && tkn_input_value!=null) {
				
				StringTokenizer st = new StringTokenizer(tkn_input_value,"`");
				
				String base_thsms=st.nextToken();
				String target_thsms = st.nextToken();//thsms reg
				
				Vector v = new Vector();
				ListIterator li = v.listIterator();
				conn = OtakuSQL.connectToUsg();
				String cmd = "select * from DAFTAR_ULANG_RULES where THSMS=? order by KDPST";
				stmt = conn.prepareStatement(cmd);
				stmt.setString(1, base_thsms);
				rs = stmt.executeQuery();
				
				
				while(rs.next()) {
					String kdpst = rs.getString("KDPST");
					String verificator = rs.getString("TKN_VERIFICATOR");
					String urut = ""+rs.getBoolean("URUTAN");
					String kode_kmp = rs.getString("KODE_KAMPUS");
					li.add(kdpst+"`"+verificator+"`"+urut+"`"+kode_kmp);
				}
				if(v!=null && v.size()>0) {
					//hapus prev record
					stmt = conn.prepareStatement("delete from DAFTAR_ULANG_RULES where THSMS=?");
					stmt.setString(1, target_thsms);
					stmt.executeUpdate();
					
					//insert new data
					stmt = conn.prepareStatement("insert into DAFTAR_ULANG_RULES (THSMS,KDPST,TKN_VERIFICATOR,URUTAN,KODE_KAMPUS) values (?,?,?,?,?)");
					li = v.listIterator();
					while(li.hasNext()) {
						String brs = (String)li.next();
						st = new StringTokenizer(brs,"`");
						String kdpst = st.nextToken();
						String verificator = st.nextToken();
						String urut = st.nextToken();
						String kode_kmp = st.nextToken();
						int i = 1;
						if(isStringNullOrEmpty(target_thsms) ) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, target_thsms);	
						}
						if(isStringNullOrEmpty(kdpst) ) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, kdpst);	
						}
						if(isStringNullOrEmpty(verificator) ) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, verificator);	
						}
						
						if(isStringNullOrEmpty(urut) ) {
							stmt.setNull(i++, java.sql.Types.BOOLEAN);
						}
						else {
							stmt.setBoolean(i++, Boolean.parseBoolean(urut));	
						}
						
						if(isStringNullOrEmpty(kode_kmp) ) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, kode_kmp);	
						}
						
						upd = stmt.executeUpdate();
					}
				}
				else {
					//empty vector = no update
				}
			}
			
			
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		//String returnString = joba.toString();
    		res = Response.ok("[{\"UPDATE_STATUS\":\""+upd+"\"}]").build();
			
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
	

	public Response updateDataDaftarUlangRules(String token_trans, String tkn_input_value) throws Exception {
		Response res = null;
		boolean valid = false;
		int upd = 0;


		try {
						

			valid = validateTokenTransaction(token_trans);
			//System.out.println("done validating");
			//System.out.println("valid 00 ="+valid);
			if(valid && tkn_input_value!=null) {
				
				StringTokenizer st = new StringTokenizer(tkn_input_value,"`");
				conn = OtakuSQL.connectToUsg();
				String cmd = "update DAFTAR_ULANG_RULES set TKN_VERIFICATOR=?,URUTAN=? where THSMS=? and KDPST=? and KODE_KAMPUS=?";
				stmt = conn.prepareStatement(cmd);
				String target_thsms = st.nextToken();//thsms buka kelas
				
				while(st.hasMoreTokens()) {
					int i = 1;
					String kdpst = st.nextToken();
					String verificator = st.nextToken();
					String urutan = st.nextToken();
					String kode_kmp = st.nextToken();	
					if(isStringNullOrEmpty(verificator) ) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, verificator);	
					}
					if(isStringNullOrEmpty(urutan) ) {
						stmt.setNull(i++, java.sql.Types.BOOLEAN);
					}
					else {
						stmt.setBoolean(i++, Boolean.parseBoolean(urutan));	
					}
					
					if(isStringNullOrEmpty(target_thsms) ) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, target_thsms);	
					}
					if(isStringNullOrEmpty(kdpst) ) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, kdpst);	
					}
					if(isStringNullOrEmpty(kode_kmp) ) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, kode_kmp);	
					}
					upd = stmt.executeUpdate();
				}
			}
			
			
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		//String returnString = joba.toString();
    		res = Response.ok("[{\"UPDATE_STATUS\":\""+upd+"\"}]").build();
			
		}
		catch (Exception ex) {
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

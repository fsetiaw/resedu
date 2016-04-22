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


public class UpdateQueryCommandTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	/*
	 * return UPDATE_STATUS : int
	 */
	public Response updateDataFromFormUpdate(String token_trans, String tkn_input_value) throws Exception {
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
				String cmd_code=st.nextToken();
				String cmd_keter=st.nextToken();
				String use_by=st.nextToken();
				String cmd_dependecy=st.nextToken();
				String hak_akses_scope_kmp=st.nextToken();
				String obj_lvl_allow_to_set=st.nextToken();
				
				
				
				conn = OtakuSQL.connectToUsg();
				String cmd = "UPDATE TABEL_COMMAND SET "
						+"CMD_KETER=?,"//1
						+"USE_BY=?,"//2
						+"CMD_DEPENDENCY=?,"//3
						+"PILIHAN_VALUE_SCOPE_KAMPUS=?,"//4
						+"OBJECT_LEVEL_ALLOWED_TO_SET=? "//5
						+"WHERE CMD_CODE=?";//6
				//System.out.println(cmd);
				stmt = conn.prepareStatement(cmd);
				int i=1;
				//1
				//stmt.setString(i++, thsms);
				if(isStringNullOrEmpty(cmd_keter) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, cmd_keter);	
				}
				//System.out.println(i-1+".thsms="+thsms);
				//2.stmt.setString(i++, thsms_pmb);
				if(isStringNullOrEmpty(use_by) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, use_by);	
				}
				//System.out.println(i-1+".thsms_pmb="+thsms_pmb);
				
				//3.stmt.setString(i++, thsms_reg);
				if(isStringNullOrEmpty(cmd_dependecy) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, cmd_dependecy);	
				}
				//System.out.println(i-1+".thsms_reg="+thsms_reg);
				
				//4.stmt.setString(i++, thsms_krs);
				if(isStringNullOrEmpty(hak_akses_scope_kmp) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, hak_akses_scope_kmp);	
				}
				//System.out.println(i-1+".thsms_krs="+thsms_krs);
				
				//5.list_thsms_for_edit - tidak / belumdigunakan
				//intuk sekarang value disamakan dgn thsms sms
				if(isStringNullOrEmpty(obj_lvl_allow_to_set) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, obj_lvl_allow_to_set);	
				}
				//System.out.println(i-1+".thsms="+thsms);
				
				//6. tgl orientasi
				if(isStringNullOrEmpty(cmd_code) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, cmd_code);	
				}
				//System.out.println(i-1+".tgl_orientasi="+tgl_orientasi);
								
				upd = stmt.executeUpdate();
				
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
	
	public Response insertNewCommand(String token_trans, String tkn_input_value) throws Exception {
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
				String inp_cmd_code=st.nextToken();
				String inp_cmd_keter=st.nextToken();
				String inp_cmd_dependency=st.nextToken();
				String inp_cmd_pilihan_value=st.nextToken();
				String inp_cmd_scope_kmp=st.nextToken();
				String inp_cmd_obj_lvl_pengampu=st.nextToken();
				String inp_cmd_use_by=st.nextToken();
				if(inp_cmd_code!=null && !isStringNullOrEmpty(inp_cmd_code) ) {
					conn = OtakuSQL.connectToUsg();
					String sql = "insert into TABEL_COMMAND ("
								+"CMD_CODE,"//1
								+"CMD_KETER,"//2
								+"USE_BY,"//3
								+"CMD_DEPENDENCY,"//4
								+"PILIHAN_VALUE,"//5
								+"PILIHAN_VALUE_SCOPE_KAMPUS,"//6
								+"OBJECT_LEVEL_ALLOWED_TO_SET)"//7
								+"values(?,?,?,?,?,?,?)";
					stmt = conn.prepareStatement(sql);
					int i=1;
					//1
					stmt.setString(i++,inp_cmd_code);
					//2
					if(isStringNullOrEmpty(inp_cmd_keter)) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, inp_cmd_keter);
					}
					//3
					if(isStringNullOrEmpty(inp_cmd_use_by)) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, inp_cmd_use_by);
					}
					//4
					if(isStringNullOrEmpty(inp_cmd_dependency)) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, inp_cmd_dependency);
					}
					//5
					if(isStringNullOrEmpty(inp_cmd_pilihan_value)) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, inp_cmd_pilihan_value);
					}
					//6
					if(isStringNullOrEmpty(inp_cmd_scope_kmp)) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, inp_cmd_scope_kmp);
					}
					//7
					if(isStringNullOrEmpty(inp_cmd_obj_lvl_pengampu)) {
						stmt.setNull(i++, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(i++, inp_cmd_obj_lvl_pengampu);
					}
					upd = stmt.executeUpdate();
					
				}

	    		res = Response.ok("[{\"UPDATE_STATUS\":\""+upd+"\"}]").build();
			}	
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

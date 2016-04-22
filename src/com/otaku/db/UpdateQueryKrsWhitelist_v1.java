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


public class UpdateQueryKrsWhitelist_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	/*
	 * return UPDATE_STATUS : int
	 */
	public Response updateDataFromFormKrsWhitelist(String token_trans, String tkn_input_value) throws Exception {
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
				
				String kdpst=st.nextToken();
				String npmhs=st.nextToken();
				String tkn_thsms=st.nextToken();
				if(!isStringNullOrEmpty(kdpst) && !isStringNullOrEmpty(npmhs)) {
					conn = OtakuSQL.connectToUsg();
					String cmd = "UPDATE KRS_WHITE_LIST SET "
							+"TOKEN_THSMS=? where "//1
							+"KDPST=? and "//2
							+"NPMHS=?";//3
					stmt = conn.prepareStatement(cmd);
					if(isStringNullOrEmpty(tkn_thsms) ) {
						stmt.setNull(1, java.sql.Types.VARCHAR);
					}
					else {
						stmt.setString(1, tkn_thsms);	
					}
					
					stmt.setString(2, kdpst);
					stmt.setString(3, npmhs);
					upd = stmt.executeUpdate();
					if(upd<1) {
						//belum ada prev record jadi harsu di iins
						cmd = "insert into KRS_WHITE_LIST (KDPST,NPMHS,TOKEN_THSMS) values (?,?,?)";
						stmt = conn.prepareStatement(cmd);
						stmt.setString(1, kdpst);
						stmt.setString(2, npmhs);
						//stmt.setString(3, tkn_thsms);
						if(isStringNullOrEmpty(tkn_thsms) ) {
							stmt.setNull(3, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(3, tkn_thsms);	
						}
						
						upd=stmt.executeUpdate();
					}
				}	
				//System.out.println("upd vale - "+upd);
				res = Response.ok("[{\"UPDATE_STATUS\":\""+upd+"\"}]").build();				
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
	

	
}

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


public class SearchQueryObjectTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	

	public Response returnListObjectType() throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select * from OBJECT");
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
	
	public Response returnListObjectGivenKdpst(String kdpst) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select * from OBJECT where KDPST=?");
			stmt.setString(1, kdpst);
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
	
	public Response returnListObjectType(int id_obj) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select * from OBJECT where ID_OBJ=?");
			stmt.setInt(1, id_obj);
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
	
	public Response returnHakAksesObjectType(int id_obj) throws Exception {
		Response res = null;
		try {
			JSONArray jsoa = new JSONArray();
			
			

			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select ACCESS_LEVEL_CONDITIONAL,ACCESS_LEVEL from OBJECT where ID_OBJ=?");
			stmt.setInt(1, id_obj);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				JSONObject job = new JSONObject();
				String scope = rs.getString("ACCESS_LEVEL_CONDITIONAL");
				String scopeName = rs.getString("ACCESS_LEVEL");
				job.put("ACCESS_LEVEL", scopeName);
				job.put("ACCESS_LEVEL_CONDITIONAL", scope);
				jsoa.put(job);
			}
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		String returnString = jsoa.toString();
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
	
	public Response returnHakAksesObjectTypeForScope(int id_obj, String targetScope) throws Exception {
		Response res = null;
		int max_index = 1000;
		Vector v = new Vector();
		ListIterator li = v.listIterator();
		try {
			
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select distinct OBJ_LEVEL from OBJECT");
			rs = stmt.executeQuery();
			while(rs.next()) {
				String tmp = ""+rs.getInt("OBJ_LEVEL");
				li.add(tmp);
			}
			//stmt = conn.prepareStatement("select ACCESS_LEVEL_CONDITIONAL,ACCESS_LEVEL from OBJECT where ID_OBJ=?");
			stmt = conn.prepareStatement("select ACCESS_LEVEL_CONDITIONAL,ACCESS_LEVEL,HAK_AKSES,SCOPE_KAMPUS from OBJECT where ID_OBJ=?");
			stmt.setInt(1, id_obj);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//JSONObject job = new JSONObject();
				String scope = rs.getString("ACCESS_LEVEL_CONDITIONAL");
				String scopeName = rs.getString("ACCESS_LEVEL");
				String scopeEdit = rs.getString("HAK_AKSES");
				String scopeKampus = rs.getString("SCOPE_KAMPUS");
				if(scopeName.toUpperCase().contains(targetScope.toUpperCase())) {
					
					StringTokenizer st = new StringTokenizer(scopeName,"#");
					boolean match = false;
					int i=0;
					while(st.hasMoreTokens() && !match) {
						i++;
						String scp = st.nextToken();
						if(scp.toUpperCase().equalsIgnoreCase(targetScope.toUpperCase())) {
							match = true;
							scopeName = ""+targetScope;
						}
					}
					if(match) {
						st = new StringTokenizer(scope,"#");
						
						for(int j=0;j<i;j++) {
							scope = st.nextToken();
						//	System.out.println(i+" vs "+j+" scope="+scope);
						}
						boolean samaDenganOnly = true;
						if(scope.contains(">")||scope.contains("<")) {
							samaDenganOnly=false;
						}
						if(samaDenganOnly) {
							//reset list obj_level vector
							v = new Vector();
						}
						st = new StringTokenizer(scope,",");
						
						int iter=0;
						while(st.hasMoreTokens()) {
							String tkn = st.nextToken();
							if(tkn.startsWith("=")) {
								String tmp = ""+tkn.substring(1, tkn.length());
								li.add(tmp);
							}
							else if(tkn.startsWith(">=")) {
								int startIndex = Integer.valueOf(tkn.substring(2, tkn.length())).intValue();
								int maxIndex = startIndex;
								for(int l=0;l<maxIndex;l++) {
									li = v.listIterator();
									while(li.hasNext()) {
										String tmpVal = (String)li.next();
										if(tmpVal.equalsIgnoreCase(""+l)) {
											li.remove();
										}
									}
								}
							}
							else if(tkn.startsWith(">")) {
								int startIndex = Integer.valueOf(tkn.substring(1, tkn.length())).intValue()+1;
								int maxIndex = startIndex;
								for(int l=0;l<maxIndex;l++) {
									li = v.listIterator();
									while(li.hasNext()) {
										String tmpVal = (String)li.next();
										if(tmpVal.equalsIgnoreCase(""+l)) {
											li.remove();
										}
									}
								}	
							}
							else if(tkn.startsWith("<=")) {
								int startIndex = Integer.valueOf(tkn.substring(2, tkn.length())).intValue();
								int maxIndex = startIndex;
								li = v.listIterator();
								while(li.hasNext()) {
									String tmpVal = (String)li.next();
									if(Integer.valueOf(tmpVal).intValue()>startIndex) {
										li.remove();
									}
								}	
							}
							else if(tkn.startsWith("<")) {
								int startIndex = Integer.valueOf(tkn.substring(1, tkn.length())).intValue();
								int maxIndex = startIndex;
								li = v.listIterator();
								while(li.hasNext()) {
									String tmpVal = (String)li.next();
									if(Integer.valueOf(tmpVal).intValue()>=startIndex) {
										li.remove();
									}
								}	
							}
						}
						v = VectorTools.removeDuplicateFromVector(v);
						v = VectorTools.sortVectorStringOfInteger(v);
						li = v.listIterator();
						//String sql = "select * from OBJECT";
						String sql = "select ID_OBJ,KDPST,OBJ_NAME,OBJ_DESC,OBJ_LEVEL,ACCESS_LEVEL_CONDITIONAL,ACCESS_LEVEL,OBJ_NICKNAME,HAK_AKSES,SCOPE_KAMPUS,KODE_KAMPUS_DOMISILI from OBJECT";
						boolean first = true;
						while(li.hasNext()) {
							//JSONObject job = new JSONObject();
							String tmp = ""+(Integer)li.next();
							if(first) {
								first = false;
								sql = sql + " where OBJ_LEVEL="+tmp;
							}
							else {
								sql = sql + "OBJ_LEVEL="+tmp;
							}
							if(li.hasNext()) {
								sql = sql + " OR ";
							}
						}
						stmt = conn.prepareStatement(sql);
						rs = stmt.executeQuery();
						
						ToJson converter = new ToJson();
						JSONArray jsoa = new JSONArray();
			    		//JSONArray joba = new JSONArray();
			    		
			    		jsoa = converter.toJSONArray(rs);
			    		String returnString = jsoa.toString();
			    		res = Response.ok(returnString).build();
						/*
						while(li.hasNext()) {
							JSONObject job = new JSONObject();
							String tmp = ""+(Integer)li.next();
							job.put("OBJ_LEVEL", tmp);
							jsoa.put(job);
						}
						*/
						
					}	
				}
			}

    		//String returnString = jsoa.toString();
    		//res = Response.ok(returnString).build();
			
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

	public Response returnObjectTknNickname(int id_obj) throws Exception {
		Response res = null;
		try {
			JSONArray jsoa = new JSONArray();
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select OBJ_NICKNAME from OBJECT where ID_OBJ=?");
			stmt.setInt(1, id_obj);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				JSONObject job = new JSONObject();
				String nick = rs.getString("OBJ_NICKNAME");
				job.put("OBJ_NICKNAME", nick);
				jsoa.put(job);
			}
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		String returnString = jsoa.toString();
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
	
	public Response returnObjectTknNicknameBasedOnNpm(String npm) throws Exception {
		Response res = null;
		try {
			JSONArray jsoa = new JSONArray();
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select OBJ_NICKNAME from OBJECT inner JOIN CIVITAS on OBJECT.ID_OBJ=CIVITAS.ID_OBJ where NPMHSMSMHS=?");
			stmt.setString(1, npm);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				JSONObject job = new JSONObject();
				String nick = rs.getString("OBJ_NICKNAME");
				job.put("OBJ_NICKNAME", nick);
				jsoa.put(job);
			}
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		String returnString = jsoa.toString();
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
	 * NOTE : proses ini tabel objek harus terisi melalui form EDIT PARAM
	 */
	public Response getUsrInfoDetailHakAksesGivemParam_v1(int id_obj, String param_target) throws Exception {
		
		Response res = null;
		String returnString = null;
		try {
			JSONArray jsoa = new JSONArray();
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select * from OBJECT where ID_OBJ=?");
			stmt.setInt(1, id_obj);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				String access_level_conditional = rs.getString("ACCESS_LEVEL_CONDITIONAL");
				String list_param = rs.getString("ACCESS_LEVEL");
				String hak_akses =  rs.getString("HAK_AKSES");
				String scope_kmp =  rs.getString("SCOPE_KAMPUS");
				
				//step 1 = apakah usr memiliki targtet param
				if(list_param.contains(param_target)) {
					//step 2 = cek urutan ke berapa target param tersebut
					StringTokenizer st = new StringTokenizer(list_param,"#");
					int norut = 0;
					boolean match = false;
					while(st.hasMoreTokens() && !match) {
						norut++;
						String param = st.nextToken();
						if(param.equalsIgnoreCase(param_target)) {
							match = true;
						}
					}
					
					//step 3 = cek hak akses untuk norut tsb
					st = new StringTokenizer(hak_akses,"#");
					String hak_akses_value = "";
					for(int i=0;i<norut;i++) {
						hak_akses_value = st.nextToken();
					}
					
					//step 4 = cek scope_kmp untuk norut tsb
					st = new StringTokenizer(scope_kmp,"#");
					String scope_kmp_value = "";
					for(int i=0;i<norut;i++) {
						scope_kmp_value = st.nextToken();
					}
					
					//step 5 = cek scope_prodi  untuk norut tsb
					st = new StringTokenizer(access_level_conditional,"#");
					String access_level_conditional_value = "";
					for(int i=0;i<norut;i++) {
						access_level_conditional_value = st.nextToken();
					}
					returnString = new String();
					returnString = "[{\"ACCESS_LEVEL\":\""+param_target+"\",\"ACCESS_LEVEL_CONDITIONAL\":\""+access_level_conditional_value+"\",\"HAK_AKSES\":\""+hak_akses_value+"\",\"SCOPE_KAMPUS\":\""+scope_kmp_value+"\"}]";
				}
				else {
					returnString = new String();
					returnString = "[{\"ACCESS_LEVEL\":\"null\",\"ACCESS_LEVEL_CONDITIONAL\":\"null\",\"HAK_AKSES\":\"null\",\"SCOPE_KAMPUS\":\"null\"}]";
				}
			}
			else {
				returnString = new String();
				returnString = "[{\"ACCESS_LEVEL\":\"null\",\"ACCESS_LEVEL_CONDITIONAL\":\"null\",\"HAK_AKSES\":\"null\",\"SCOPE_KAMPUS\":\"null\"}]";
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
	
}

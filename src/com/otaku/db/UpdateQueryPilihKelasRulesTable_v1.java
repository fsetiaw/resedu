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


public class UpdateQueryPilihKelasRulesTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	/*
	 * return UPDATE_STATUS : int
	 */
	public Response copyRuleFromPrevThsms(String token_trans, String tkn_input_value) throws Exception {
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
				//System.out.println("valied");
				StringTokenizer st = new StringTokenizer(tkn_input_value,"`");
				String base_thsms=st.nextToken();
				String target_thsms=st.nextToken();
				
				conn = OtakuSQL.connectToUsg();
				String cmd = "select * from PILIH_KELAS_RULES where THSMS=?";//6
				
				//copy from base thsms
				stmt = conn.prepareStatement(cmd);
				stmt.setString(1, base_thsms);
				rs = stmt.executeQuery();
				Vector v = null;
				if(rs.next()) {
					v = new Vector();
					ListIterator li = v.listIterator();
					do {
						String kdpst =""+rs.getString("KDPST");
						String id_obj_mhs =""+rs.getInt("ID_OBJ_MHS");
						String shift_only =""+rs.getBoolean("SHIFT_ONLY");
						String all_shift =""+rs.getBoolean("ALL_SHIFT");
						String all_prodi =""+rs.getBoolean("ALL_PRODI");
						String tkn_prodi =""+rs.getString("TKN_PRODI");
						String all_fak =""+rs.getBoolean("ALL_FAKULTAS");
						String tkn_fak =""+rs.getString("TKN_FAKULTAS");
						String all_kmp =""+rs.getBoolean("ALL_KAMPUS");
						String tkn_kmp =""+rs.getString("TKN_KAMPUS");
						String tkn_npm_whitelist_shift =""+rs.getString("NPMHS_WHITELIST_SHIFT");
						String tkn_npm_whitelist_prodi =""+rs.getString("NPMHS_WHITELIST_PRODI");
						String tkn_npm_whitelist_fak =""+rs.getString("NPMHS_WHITELIST_FAK");
						String tkn_npm_whitelist_kmp =""+rs.getString("NPMHS_WHITELIST_KMP");
						String kode_kmp =""+rs.getString("KODE_KAMPUS");
						String tkn_shift =""+rs.getString("TKN_SHIFT");
						
						li.add(kdpst+"`"+id_obj_mhs+"`"+shift_only+"`"+all_shift+"`"+all_prodi+"`"+tkn_prodi+"`"+all_fak+"`"+tkn_fak+"`"+all_kmp+"`"+tkn_kmp+"`"+tkn_npm_whitelist_shift+"`"+tkn_npm_whitelist_prodi+"`"+tkn_npm_whitelist_fak+"`"+tkn_npm_whitelist_kmp+"`"+kode_kmp+"`"+tkn_shift);
						
					}
					while(rs.next());
					if(v.size()>0) {
						//System.out.println("vsixe = "+v.size());
						//System.out.println("target_thsms="+target_thsms);
						//delete prev record
						stmt = conn.prepareStatement("delete from PILIH_KELAS_RULES where THSMS=?");
						stmt.setString(1, target_thsms);
						stmt.executeUpdate();
						
						//insert new data
						cmd = "INSERT INTO PILIH_KELAS_RULES ("
								+"THSMS,"
								+"KDPST,"
								+"ID_OBJ_MHS,"
								+"SHIFT_ONLY,"
								+"ALL_SHIFT,"
								+"ALL_PRODI,"
								+"TKN_PRODI,"
								+"ALL_FAKULTAS,"
								+"TKN_FAKULTAS,"
								+"ALL_KAMPUS,"
								+"TKN_KAMPUS,"
								+"NPMHS_WHITELIST_SHIFT,"
								+"NPMHS_WHITELIST_PRODI,"
								+"NPMHS_WHITELIST_FAK,"
								+"NPMHS_WHITELIST_KMP,"
								+"KODE_KAMPUS,"
								+"TKN_SHIFT"
								+")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						stmt = conn.prepareStatement(cmd);
						li = v.listIterator();
						while (li.hasNext()) {
							String brs = (String)li.next();
							
							st = new StringTokenizer(brs,"`");
							String kdpst=st.nextToken();
							String id_obj_mhs=st.nextToken();
							String shift_only=st.nextToken();
							String all_shift=st.nextToken();
							String all_prodi=st.nextToken();
							String tkn_prodi=st.nextToken();
							String all_fak=st.nextToken();
							String tkn_fak=st.nextToken();
							String all_kmp=st.nextToken();
							String tkn_kmp=st.nextToken();
							String tkn_npm_whitelist_shift=st.nextToken();
							String tkn_npm_whitelist_prodi=st.nextToken();
							String tkn_npm_whitelist_fak=st.nextToken();
							String tkn_npm_whitelist_kmp=st.nextToken();
							String kode_kmp=st.nextToken();
							String tkn_shift=st.nextToken();
							int i = 1;
							//System.out.println(i+"."+brs);
							if(isStringNullOrEmpty(target_thsms)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, target_thsms);
							}
							//2
							if(isStringNullOrEmpty(kdpst)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, kdpst);
							}
							//3
							if(isStringNullOrEmpty(id_obj_mhs) || (Integer.parseInt(id_obj_mhs)<1)) {
								stmt.setNull(i++, java.sql.Types.INTEGER);
							}
							else {
								stmt.setInt(i++, Integer.parseInt(id_obj_mhs));
							}
							//4
							if(shift_only!=null && (shift_only.equalsIgnoreCase("1")||shift_only.equalsIgnoreCase("true"))) {
								stmt.setBoolean(i++, true);
							}
							else {
								stmt.setBoolean(i++, false);
							}
							//5
							if(all_shift!=null && (all_shift.equalsIgnoreCase("1")||all_shift.equalsIgnoreCase("true"))) {
								stmt.setBoolean(i++, true);
							}
							else {
								stmt.setBoolean(i++, false);
							}
							//6
							if(all_prodi!=null && (all_prodi.equalsIgnoreCase("1")||all_prodi.equalsIgnoreCase("true"))) {
								stmt.setBoolean(i++, true);
							}
							else {
								stmt.setBoolean(i++, false);
							}
							//7
							if(isStringNullOrEmpty(tkn_prodi)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, tkn_prodi);
							}
							//8
							if(all_fak!=null && (all_fak.equalsIgnoreCase("1")||all_fak.equalsIgnoreCase("true"))) {
								stmt.setBoolean(i++, true);
							}
							else {
								stmt.setBoolean(i++, false);
							}
							//9
							if(isStringNullOrEmpty(tkn_fak)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, tkn_fak);
							}
							//10
							if(all_kmp!=null && (all_kmp.equalsIgnoreCase("1")||all_kmp.equalsIgnoreCase("true"))) {
								stmt.setBoolean(i++, true);
							}
							else {
								stmt.setBoolean(i++, false);
							}
							//11
							if(isStringNullOrEmpty(tkn_kmp)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, tkn_kmp);
							}
							//12
							if(isStringNullOrEmpty(tkn_npm_whitelist_shift)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, tkn_npm_whitelist_shift);
							}
							//13
							if(isStringNullOrEmpty(tkn_npm_whitelist_prodi)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, tkn_npm_whitelist_prodi);
							}
							//14
							if(isStringNullOrEmpty(tkn_npm_whitelist_fak)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, tkn_npm_whitelist_fak);
							}
							//15
							if(isStringNullOrEmpty(tkn_npm_whitelist_kmp)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, tkn_npm_whitelist_kmp);
							}
							//16
							if(isStringNullOrEmpty(kode_kmp)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, kode_kmp);
							}
							//17
							if(isStringNullOrEmpty(tkn_shift)) {
								stmt.setNull(i++, java.sql.Types.VARCHAR);
							}
							else {
								stmt.setString(i++, tkn_shift);
							}
							upd = stmt.executeUpdate();
							//System.out.println(upd);
						}
					}
					
				}
				else {
					//empty base_thsms - tidak ada yg bisa di copyu
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
	
	
	
	public Response updRuleFromPrevThsms(String token_trans, String tkn_input_value) throws Exception {
		Response res = null;
		boolean valid = false;
		int upd = 0;
		
		/*
		 * tkn_inp_val = tkn_inp_val+"`"+thsms_target+"`"+target_kdpst[i]+"`"+all_shift+"`"+tkn_shift+"`"+list_npm_shift
								+"`"+all_prodi+"`"+tkn_prodi+"`"+list_npm_shift
								+"`"+all_fak+"`"+tkn_fak+"`"+list_npm_fak
								+"`"+all_kmp+"`"+tkn_kmp+"`"+list_npm_kmp;
		 */
		try {
			
						
			 //validate token
			valid = validateTokenTransaction(token_trans);
			//System.out.println("done validating");
			//System.out.println("valid 00 ="+valid);
			if(valid && tkn_input_value!=null) {
				//System.out.println("valied");
				StringTokenizer st = new StringTokenizer(tkn_input_value,"`");
				//System.out.println("valied tkn_input_value="+tkn_input_value);
				//`20151`57302`yes`null`null`no`null`null`no`null`null`no`null`null`118-PST`null
				//`20151`57302`true`REGULER PAGI`31111`true`all`all`true`null`all`true`all`all`118-PST`null
				String shift_only = "true";
				String target_thsms=st.nextToken();
				String target_kdpst=st.nextToken();
				String all_shift=st.nextToken();
				if(Boolean.parseBoolean(all_shift)==true) {
					shift_only = "false";
				}
				//System.out.println("shift_onlu="+shift_only);
				String tkn_shift=st.nextToken();
				String list_npm_shift=st.nextToken();
				String all_prodi=st.nextToken();
				String tkn_prodi=st.nextToken();
				String list_npm_prodi=st.nextToken();
				String all_fak=st.nextToken();
				String tkn_fak=st.nextToken();
				String list_npm_fak=st.nextToken();
				String all_kmp=st.nextToken();
				String tkn_kmp=st.nextToken();
				String list_npm_kmp=st.nextToken();
				String list_objid_kdkmp=st.nextToken();
				while(st.hasMoreTokens()) {
					list_objid_kdkmp=list_objid_kdkmp+"`"+st.nextToken();
				}
				//System.out.println("list_objid_kdpsrt="+list_objid_kdpst);
				conn = OtakuSQL.connectToUsg();
				/*
				//delete prev record
				
				String cmd = "delete from PILIH_KELAS_RULES where THSMS=?";//6
				stmt = conn.prepareStatement(cmd);
				stmt.setString(1, target_thsms);
				stmt.executeUpdate();
				*/
				//insert nu value
				String cmd = "INSERT INTO PILIH_KELAS_RULES ("
						+"THSMS,"
						+"KDPST,"
						+"ID_OBJ_MHS,"
						+"SHIFT_ONLY,"
						+"ALL_SHIFT,"
						+"ALL_PRODI,"
						+"TKN_PRODI,"
						+"ALL_FAKULTAS,"
						+"TKN_FAKULTAS,"
						+"ALL_KAMPUS,"
						+"TKN_KAMPUS,"
						+"NPMHS_WHITELIST_SHIFT,"
						+"NPMHS_WHITELIST_PRODI,"
						+"NPMHS_WHITELIST_FAK,"
						+"NPMHS_WHITELIST_KMP,"
						+"KODE_KAMPUS,"
						+"TKN_SHIFT"
						+")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				stmt = conn.prepareStatement(cmd);
				
				if(list_objid_kdkmp!=null  && new StringTokenizer(list_objid_kdkmp).countTokens()>0) {
					st = new StringTokenizer(list_objid_kdkmp,"`");
					while(st.hasMoreTokens()) {
						//System.out.println("looping "+st.countTokens());
						//System.out.println("st tokn="+st.countTokens());
						String target_obj_id_and_kode_kmp = st.nextToken();
						StringTokenizer st1 = new StringTokenizer(target_obj_id_and_kode_kmp,"-");
						String target_obj_id = st1.nextToken();
						String kd_kmp = st1.nextToken();
						//System.out.println("1.target_obj_id="+target_obj_id);
						int i = 1;
						if(isStringNullOrEmpty(target_thsms)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, target_thsms);
						}
						//2
						if(isStringNullOrEmpty(target_kdpst)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, target_kdpst);
						}
						//3
						if(isStringNullOrEmpty(target_obj_id) || (Integer.parseInt(target_obj_id)<1)) {
							stmt.setNull(i++, java.sql.Types.INTEGER);
						}
						else {
							stmt.setInt(i++, Integer.parseInt(target_obj_id));
						}
						//4
						if(shift_only!=null && (shift_only.equalsIgnoreCase("1")||shift_only.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//5
						if(all_shift!=null && (all_shift.equalsIgnoreCase("1")||all_shift.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//6
						//System.out.println("all_pro="+all_prodi);
						if(all_prodi!=null && (all_prodi.equalsIgnoreCase("1")||all_prodi.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//7
						if(isStringNullOrEmpty(tkn_prodi)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, tkn_prodi);
						}
						//8
						if(all_fak!=null && (all_fak.equalsIgnoreCase("1")||all_fak.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//9
						if(isStringNullOrEmpty(tkn_fak)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, tkn_fak);
						}
						//10
						if(all_kmp!=null && (all_kmp.equalsIgnoreCase("1")||all_kmp.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//11
						if(isStringNullOrEmpty(tkn_kmp)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, tkn_kmp);
						}
						//12
						if(isStringNullOrEmpty(list_npm_shift)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, list_npm_shift);
						}
						//13
						if(isStringNullOrEmpty(list_npm_prodi)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, list_npm_prodi);
						}
						//14
						if(isStringNullOrEmpty(list_npm_fak)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, list_npm_fak);
						}
						//15
						if(isStringNullOrEmpty(list_npm_kmp)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, list_npm_kmp);
						}
						//16
						if(isStringNullOrEmpty(kd_kmp)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, kd_kmp);
						}
						//17
						if(isStringNullOrEmpty(tkn_shift)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, tkn_shift);
						}
						//System.out.println("target_obj_id="+target_obj_id);
						try {
							upd = stmt.executeUpdate();
						}
						catch(Exception e) {
							//ignore
							//System.out.println("ignore error "+target_obj_id);
						}
					}	
				}	
			
			
			
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		//String returnString = joba.toString();
    		res = Response.ok("[{\"UPDATE_STATUS\":\""+upd+"\"}]").build();
		
			}
			
		
		//catch (SQLException ex) {
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally {
			//System.out.println("masu final");
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (conn!=null) try { conn.close();} catch (Exception ignore){}
		}	
		return res;
	}
	
	
	public Response updRuleFromPrevThsms_v1(String token_trans, String tkn_input_value) throws Exception {
		Response res = null;
		boolean valid = false;
		int upd = 0;
		
		/*
		 * tkn_inp_val = tkn_inp_val+"`"+thsms_target+"`"+target_kdpst[i]+"`"+all_shift+"`"+tkn_shift+"`"+list_npm_shift
								+"`"+all_prodi+"`"+tkn_prodi+"`"+list_npm_shift
								+"`"+all_fak+"`"+tkn_fak+"`"+list_npm_fak
								+"`"+all_kmp+"`"+tkn_kmp+"`"+list_npm_kmp;
		 */
		try {
			
						
			 //validate token
			valid = validateTokenTransaction(token_trans);
			//System.out.println("done validating");
			//System.out.println("valid 00 ="+valid);
			if(valid && tkn_input_value!=null) {
				//System.out.println("valied");
				StringTokenizer st = new StringTokenizer(tkn_input_value,"`");
				//System.out.println("valied tkn_input_value="+tkn_input_value);
				//`20151`57302`yes`null`null`no`null`null`no`null`null`no`null`null`118-PST`null
				//`20151`57302`true`REGULER PAGI`31111`true`all`all`true`null`all`true`all`all`118-PST`null
				String shift_only = "true";
				String target_thsms=st.nextToken();
				String target_kdpst=st.nextToken();
				String all_shift=st.nextToken();
				if(Boolean.parseBoolean(all_shift)==true) {
					shift_only = "false";
				}
				//System.out.println("shift_onlu="+shift_only);
				String tkn_shift=st.nextToken();
				String list_npm_shift=st.nextToken();
				String all_prodi=st.nextToken();
				String tkn_prodi=st.nextToken();
				String list_npm_prodi=st.nextToken();
				String all_fak=st.nextToken();
				String tkn_fak=st.nextToken();
				String list_npm_fak=st.nextToken();
				String all_kmp=st.nextToken();
				String tkn_kmp=st.nextToken();
				String list_npm_kmp=st.nextToken();
				String target_obj_id=st.nextToken();
				/*
				 * list_objid_kdkmp = target_obj id
				 * sudah tidak dipergunakan tapi variablenya tetep ada krn IT WORKS jgn dihilangkan
				 */
				String list_objid_kdkmp=st.nextToken(); 
				/*
				 * 
				 */
				while(st.hasMoreTokens()) {
					list_objid_kdkmp=list_objid_kdkmp+"`"+st.nextToken();
				}
				//System.out.println("list_objid_kdpsrt="+list_objid_kdpst);
				conn = OtakuSQL.connectToUsg();
				/*
				//delete prev record
				
				String cmd = "delete from PILIH_KELAS_RULES where THSMS=?";//6
				stmt = conn.prepareStatement(cmd);
				stmt.setString(1, target_thsms);
				stmt.executeUpdate();
				*/
				//insert nu value
				String cmd = "INSERT INTO PILIH_KELAS_RULES ("
						+"THSMS,"
						+"KDPST,"
						+"ID_OBJ_MHS,"
						+"SHIFT_ONLY,"
						+"ALL_SHIFT,"
						+"ALL_PRODI,"
						+"TKN_PRODI,"
						+"ALL_FAKULTAS,"
						+"TKN_FAKULTAS,"
						+"ALL_KAMPUS,"
						+"TKN_KAMPUS,"
						+"NPMHS_WHITELIST_SHIFT,"
						+"NPMHS_WHITELIST_PRODI,"
						+"NPMHS_WHITELIST_FAK,"
						+"NPMHS_WHITELIST_KMP,"
						+"KODE_KAMPUS,"
						+"TKN_SHIFT"
						+")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				stmt = conn.prepareStatement(cmd);
				
				if(list_objid_kdkmp!=null  && new StringTokenizer(list_objid_kdkmp).countTokens()>0) {
					st = new StringTokenizer(list_objid_kdkmp,"`");
					while(st.hasMoreTokens()) {
						//System.out.println("looping "+st.countTokens());
						//System.out.println("st tokn="+st.countTokens());
						String target_obj_id_and_kode_kmp = st.nextToken();
						StringTokenizer st1 = new StringTokenizer(target_obj_id_and_kode_kmp,"-");
						String tkn_obj_id = st1.nextToken();
						String kd_kmp = st1.nextToken();
						//System.out.println("1.target_obj_id="+target_obj_id);
						int i = 1;
						if(isStringNullOrEmpty(target_thsms)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, target_thsms);
						}
						//2
						if(isStringNullOrEmpty(target_kdpst)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, target_kdpst);
						}
						
						if(isStringNullOrEmpty(target_obj_id) || (Integer.parseInt(target_obj_id)<1)) {
							stmt.setNull(i++, java.sql.Types.INTEGER);
						}
						else {
							stmt.setInt(i++, Integer.parseInt(target_obj_id));
						}
						//4
						if(shift_only!=null && (shift_only.equalsIgnoreCase("1")||shift_only.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//5
						if(all_shift!=null && (all_shift.equalsIgnoreCase("1")||all_shift.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//6
						//System.out.println("all_pro="+all_prodi);
						if(all_prodi!=null && (all_prodi.equalsIgnoreCase("1")||all_prodi.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//7
						if(isStringNullOrEmpty(tkn_prodi)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, tkn_prodi);
						}
						//8
						if(all_fak!=null && (all_fak.equalsIgnoreCase("1")||all_fak.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//9
						if(isStringNullOrEmpty(tkn_fak)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, tkn_fak);
						}
						//10
						if(all_kmp!=null && (all_kmp.equalsIgnoreCase("1")||all_kmp.equalsIgnoreCase("true"))) {
							stmt.setBoolean(i++, true);
						}
						else {
							stmt.setBoolean(i++, false);
						}
						//11
						if(isStringNullOrEmpty(tkn_kmp)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, tkn_kmp);
						}
						//12
						if(isStringNullOrEmpty(list_npm_shift)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, list_npm_shift);
						}
						//13
						if(isStringNullOrEmpty(list_npm_prodi)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, list_npm_prodi);
						}
						//14
						if(isStringNullOrEmpty(list_npm_fak)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, list_npm_fak);
						}
						//15
						if(isStringNullOrEmpty(list_npm_kmp)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, list_npm_kmp);
						}
						//16
						if(isStringNullOrEmpty(kd_kmp)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, kd_kmp);
						}
						//17
						if(isStringNullOrEmpty(tkn_shift)) {
							stmt.setNull(i++, java.sql.Types.VARCHAR);
						}
						else {
							stmt.setString(i++, tkn_shift);
						}
						//System.out.println("target_obj_id="+target_obj_id);
						try {
							upd = stmt.executeUpdate();
						}
						catch(Exception e) {
							//ignore
							//System.out.println("ignore error "+target_obj_id);
						}
					}	
				}	
			
			
			
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		//String returnString = joba.toString();
    		res = Response.ok("[{\"UPDATE_STATUS\":\""+upd+"\"}]").build();
		
			}
			
		
		//catch (SQLException ex) {
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally {
			//System.out.println("masu final");
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (conn!=null) try { conn.close();} catch (Exception ignore){}
		}	
		return res;
	}
	
	public Response deletePrevRec(String token_trans, String target_thsms) throws Exception {
		Response res = null;
		boolean valid = false;
		int upd = 0;
		try {
			 //validate token
			valid = validateTokenTransaction(token_trans);
			if(valid) {

				conn = OtakuSQL.connectToUsg();
				
				//delete prev record
				
				String cmd = "delete from PILIH_KELAS_RULES where THSMS=?";//6
				stmt = conn.prepareStatement(cmd);
				stmt.setString(1, target_thsms);
				upd = stmt.executeUpdate();
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		//String returnString = joba.toString();
				res = Response.ok("[{\"UPDATE_STATUS\":\""+upd+"\"}]").build();
		
			}
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
	/*
	public Response insertNewCommand(String token_trans, String tkn_input_value) throws Exception {
		Response res = null;
		boolean valid = false;
		int upd = 0;

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
	*/
	
}

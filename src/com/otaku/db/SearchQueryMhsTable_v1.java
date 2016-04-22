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


public class SearchQueryMhsTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	

	public Response returnInfoShiftMhs(String npmhs) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select KETERANGAN,SHIFT,HARI,TOKEN_KDJEN_AVAILABILITY,KODE_KONVERSI,AKTIF from CIVITAS inner join SHIFT on SHIFTMSMHS=KETERANGAN where NPMHSMSMHS=?");
			stmt.setString(1, npmhs);
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
	
	public Response returnPrimeVariable(String npmhs) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			//cmd=histKrs
			stmt = conn.prepareStatement("select CIVITAS.ID_OBJ,CIVITAS.NMMHSMSMHS,CIVITAS.NPMHSMSMHS,CIVITAS.KDPSTMSMHS,OBJECT.OBJ_LEVEL from CIVITAS inner join OBJECT on CIVITAS.ID_OBJ=OBJECT.ID_OBJ where NPMHSMSMHS=?");
			stmt.setString(1, npmhs);
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
	
	public Response returnMhsAktif(String thsms) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			//cmd=histKrs
			stmt = conn.prepareStatement("select distinct NPMHSMSMHS,NMMHSMSMHS,SMAWLMSMHS from CIVITAS inner join TRNLM on NPMHSMSMHS=NPMHSTRNLM where THSMSTRNLM=? order by KDPSTMSMHS,SMAWLMSMHS,NPMHSMSMHS");
			stmt.setString(1, thsms);
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
	public Response returnMhsAktif(String thsms, String kdpst) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			//cmd=histKrs
			stmt = conn.prepareStatement("select distinct NPMHSMSMHS,NMMHSMSMHS,SMAWLMSMHS from CIVITAS inner join TRNLM on NPMHSMSMHS=NPMHSTRNLM where THSMSTRNLM=? and KDPSTTRNLM=? order by KDPSTMSMHS,SMAWLMSMHS,NPMHSMSMHS");
			stmt.setString(1, thsms);
			stmt.setString(2, kdpst);
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
	

	
	public Response returnMhsAktif(String thsms, long idObj) throws Exception {
		Response res = null;
		System.out.println("mskin="+idObj);
		try {
			Vector v = null;
			ListIterator li = null;
			conn = OtakuSQL.connectToUsg();
			//cmd=histKrs
			//stmt = conn.prepareStatement("select distinct NPMHSMSMHS,NMMHSMSMHS,SMAWLMSMHS from CIVITAS inner join TRNLM on NPMHSMSMHS=NPMHSTRNLM where THSMSTRNLM=? and ID_OBJ=? order by KDPSTMSMHS,SMAWLMSMHS,NPMHSMSMHS");
			stmt = conn.prepareStatement("select distinct NPMHSMSMHS,NMMHSMSMHS,SMAWLMSMHS,KODE_KAMPUS_DOMISILI from CIVITAS inner join OBJECT on CIVITAS.ID_OBJ=OBJECT.ID_OBJ where CIVITAS.ID_OBJ=?");
			//stmt.setString(1, thsms);
			stmt.setLong(1, idObj);
			ResultSet rs = stmt.executeQuery();
			if(rs!=null) {
				v = new Vector();
				li = v.listIterator();
				while(rs.next()) {
					String npm = rs.getString("NPMHSMSMHS");
					String nmm = rs.getString("NMMHSMSMHS");
					String smawl = rs.getString("SMAWLMSMHS");
					String kmp = rs.getString("KODE_KAMPUS_DOMISILI");
					li.add(npm+"#"+nmm+"#"+smawl+"#"+kmp);
				}
				li = v.listIterator();
				System.out.println("beforee="+v.size());
				stmt = conn.prepareStatement("select * from TRNLM where THSMSTRNLM=? and NPMHSTRNLM=? limit 1");
				while(li.hasNext()) {
					String brs = (String)li.next();
					//System.out.println("brs="+brs);
					StringTokenizer st = new StringTokenizer(brs,"#");
					String npm = st.nextToken();
					String nmm = st.nextToken();
					String smawl = st.nextToken();
					String kmp = st.nextToken();

					stmt.setString(1, thsms);
					stmt.setString(2, npm);
					rs = stmt.executeQuery();
					if(!rs.next()) {
						li.remove();
					}
				}
				System.out.println("after="+v.size());
			}
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
			String returnString = "[]";
			if(v!=null && v.size()>0) {
				li = v.listIterator();
				returnString = "[";
				while(li.hasNext()) {
					String brs = (String)li.next();
					StringTokenizer st = new StringTokenizer(brs,"#");
					String npm = st.nextToken();
					String nmm = st.nextToken();
					String smawl = st.nextToken();
					String kmp = st.nextToken();
					returnString=returnString+"{\"NPMHSMSMHS\":\""+npm+"\",\"NMMHSMSMHS\":\""+nmm+"\",\"SMAWLMSMHS\":\""+smawl+"\",\"KODE_KAMPUS\":\""+kmp+"\"}";
					if(li.hasNext()) {
						returnString=returnString+",";
					}
					else {
						returnString=returnString+"]";
					}
				}
			}
			
    		//String returnString = joba.toString();
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
	public Response returnMhsAktif(String thsms, String kdpst) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			//cmd=histKrs
			//stmt = conn.prepareStatement("select distinct CIVITAS.ID_OBJ,NPMHSMSMHS,NMMHSMSMHS,SMAWLMSMHS,KODE_KAMPUS_DOMISILI from CIVITAS inner join TRNLM on NPMHSMSMHS=NPMHSTRNLM where THSMSTRNLM=? and KDPSTTRNLM=? order by KDPSTMSMHS,SMAWLMSMHS,NPMHSMSMHS");
			stmt = conn.prepareStatement("select distinct CIVITAS.ID_OBJ,NPMHSMSMHS,NMMHSMSMHS,SMAWLMSMHS,KODE_KAMPUS_DOMISILI from CIVITAS inner join TRNLM on NPMHSMSMHS=NPMHSTRNLM inner join OBJECT on CIVITAS.ID_OBJ=OBJECT.ID_OBJ where THSMSTRNLM=? and KDPSTTRNLM=? order by KDPSTMSMHS,KODE_KAMPUS_DOMISILI,SMAWLMSMHS,NPMHSMSMHS");
			stmt.setString(1, thsms);
			stmt.setString(2, kdpst);
			ResultSet rs = stmt.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
			}
			rs = stmt.executeQuery();
			//System.out.println("iii="+i);
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
	
	public Response returnMhsAktif(String thsms, String kdpst, long idObj) throws Exception {
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			//cmd=histKrs
			//stmt = conn.prepareStatement("select distinct CIVITAS.ID_OBJ,NPMHSMSMHS,NMMHSMSMHS,SMAWLMSMHS,KODE_KAMPUS_DOMISILI from CIVITAS inner join TRNLM on NPMHSMSMHS=NPMHSTRNLM where THSMSTRNLM=? and KDPSTTRNLM=? order by KDPSTMSMHS,SMAWLMSMHS,NPMHSMSMHS");
			stmt = conn.prepareStatement("select distinct CIVITAS.ID_OBJ,NPMHSMSMHS,NMMHSMSMHS,SMAWLMSMHS,KODE_KAMPUS_DOMISILI from CIVITAS inner join TRNLM on NPMHSMSMHS=NPMHSTRNLM inner join OBJECT on CIVITAS.ID_OBJ=OBJECT.ID_OBJ where THSMSTRNLM=? and KDPSTTRNLM=? and CIVITAS.ID_OBJ=? order by KDPSTMSMHS,KODE_KAMPUS_DOMISILI,SMAWLMSMHS,NPMHSMSMHS");
			stmt.setString(1, thsms);
			stmt.setString(2, kdpst);
			stmt.setLong(3, idObj);
			ResultSet rs = stmt.executeQuery();
			//int i=0;
			//while(rs.next()) {
			//	i++;
			//}
			//rs = stmt.executeQuery();
			//System.out.println("iii="+i);
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

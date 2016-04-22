package com.otaku.rest.status;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.codehaus.jettison.json.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.otaku.db.*;
import com.otaku.util.converter.ToJson;
import org.owasp.esapi.ESAPI;
@Path("/v1/status")

public class Status_v1 extends OtakuSQL{
	
	private static final String version = "ver.1";
	OtakuSQL otak = null; 
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>JAVA REST SERVICE</P>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version : "+version+"</p>";
	}
	
	
	@Path("/time")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getCurrentTimestampInText() {
		java.util.Date date= new java.util.Date();
    	return "<p>"+(new Timestamp(date.getTime()))+"</p>";
    }
	
	@Path("/usg_db")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDbStatus() throws Exception {
		ResultSet rs = null;
		String myString = null;
		String returnString = null;
    	PreparedStatement stmt = null;
    	Connection con = null;
    	DataSource ds = null;
    	try {
    		//otak = new OtakuSQL();
    		con = OtakuSQL.connectToUsg();
    		//con = ds.getConnection();
    		stmt = con.prepareStatement("select * from CIVITAS limit 2");
    		rs = stmt.executeQuery();
    		while(rs.next()) {
    			String nmmhs = rs.getString("NMMHSMSMHS");
    			returnString = returnString+", "+nmmhs;
    		}
    	}
		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (con!=null) try { con.close();} catch (Exception ignore){}
		}	
    	return "<p>"+returnString+"</p>";
	}
	
	@Path("/usg_db/listMhs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNmmhs() throws Exception {
		ResultSet rs = null;
		String myString = null;
		String returnString = null;
    	PreparedStatement stmt = null;
    	Connection con = null;
    	DataSource ds = null;
    	Response res = null;
    	try {
    		con = OtakuSQL.connectToUsg();
    		stmt = con.prepareStatement("select * from CIVITAS limit 3");
    		rs = stmt.executeQuery();
    		ToJson converter = new ToJson();
    		JSONArray joba = new JSONArray();
    		
    		joba = converter.toJSONArray(rs);
    		returnString = joba.toString();
    		res = Response.ok(returnString).build();
    	}
		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (con!=null) try { con.close();} catch (Exception ignore){}
		}	
    	return res;
	}
}

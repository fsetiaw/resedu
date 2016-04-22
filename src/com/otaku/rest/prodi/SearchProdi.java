package com.otaku.rest.prodi;
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

@Path("/v1/search_prodi")
public class SearchProdi {
	
	private static final String version = "ver.1";
	
	
	@Path("/visi/{kdpst}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisiProdi(@PathParam("kdpst") String kdpst) throws Exception {
		Response res = null;
		try {
			SearchQueryVisiMisiTable_v1 sob = new SearchQueryVisiMisiTable_v1();
			res = sob.returnVisiProdi(kdpst);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("/misi/{kdpst}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMisiProdi(@PathParam("kdpst") String kdpst) throws Exception {
		Response res = null;
		try {
			SearchQueryVisiMisiTable_v1 sob = new SearchQueryVisiMisiTable_v1();
			res = sob.returnMisiProdi(kdpst);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("/tujuan/{kdpst}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTujuanProdi(@PathParam("kdpst") String kdpst) throws Exception {
		Response res = null;
		try {
			SearchQueryVisiMisiTable_v1 sob = new SearchQueryVisiMisiTable_v1();
			res = sob.returnTujuanProdi(kdpst);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	/*
	 * return : seluruh info tiap objeck yang ada pada table OBEJEK dalam bentuk JSON 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllObjectType() throws Exception {
		Response res = null;
		try {
			SearchQueryMspstTable_v1 sob = new SearchQueryMspstTable_v1();
			res = sob.returnListProdi();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	

}

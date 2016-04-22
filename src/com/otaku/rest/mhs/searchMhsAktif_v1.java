package com.otaku.rest.mhs;
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


@Path("/v1/mhs/aktif")
public class searchMhsAktif_v1 {
private static final String version = "ver.1";
		
	@Path("/thsms/{thsms}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMhsAktif(@PathParam("thsms") String thsms) throws Exception {
		Response res = null;
		try {
			SearchQueryMhsTable_v1 sob = new SearchQueryMhsTable_v1();
			res = sob.returnMhsAktif(thsms);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	@Path("/thsms/{thsms}/kdpst/{kdpst}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMhsAktif(@PathParam("thsms") String thsms,@PathParam("kdpst") String kdpst) throws Exception {
		Response res = null;
		try {
			SearchQueryMhsTable_v1 sob = new SearchQueryMhsTable_v1();
			res = sob.returnMhsAktif(thsms,kdpst);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("/thsms/{thsms}/kdpst/{kdpst}/{idObj}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMhsAktif(@PathParam("thsms") String thsms,@PathParam("kdpst") String kdpst,@PathParam("idObj") long idObj) throws Exception {
		Response res = null;
		try {
			SearchQueryMhsTable_v1 sob = new SearchQueryMhsTable_v1();
			res = sob.returnMhsAktif(thsms,kdpst,idObj);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	



}

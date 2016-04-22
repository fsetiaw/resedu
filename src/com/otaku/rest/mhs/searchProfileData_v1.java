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


@Path("/v1/mhs")
public class searchProfileData_v1 {
private static final String version = "ver.1";
		
	@Path("/{npm}/shift")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInfoShiftMhs(@PathParam("npm") String npmhs) throws Exception {
		Response res = null;
		try {
			SearchQueryMhsTable_v1 sob = new SearchQueryMhsTable_v1();
			res = sob.returnInfoShiftMhs(npmhs);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	@Path("/{npm}/prime_var")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCivitasPrimeVar(@PathParam("npm") String npmhs) throws Exception {
		Response res = null;
		try {
			SearchQueryMhsTable_v1 sob = new SearchQueryMhsTable_v1();
			res = sob.returnPrimeVariable(npmhs);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	

}

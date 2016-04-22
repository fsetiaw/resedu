package com.otaku.rest.beasiswa;
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


@Path("/v1/bea")
public class SearchBeasiswa_v1 {
private static final String version = "ver.1";
	
	/*
	 * return : seluruh info beasiswa dalam bentuk JSON 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNamaAkun() throws Exception {
		Response res = null;
		try {
			SearchQueryBeasiswaTable_v1 sob = new SearchQueryBeasiswaTable_v1();
			res = sob.returnListInfoBeasiswa();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	

}

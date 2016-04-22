package com.otaku.rest.pymnt;
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


@Path("/v1/pymnt")
public class SearchPymnt_v1 {
private static final String version = "ver.1";
	

	/*
	 * return : seluruh pymnt dalam bentuk JSON not  void dgn norut yg sama 
	 */
	@Path("/norut/{norut}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPymntBasedOnKuiid(@PathParam("norut") long norut) throws Exception {
		Response res = null;
		try {
			SearchQueryPymntTable_v1 sob = new SearchQueryPymntTable_v1();
			res = sob.returnPymntBasedOnNorut(norut);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	

}

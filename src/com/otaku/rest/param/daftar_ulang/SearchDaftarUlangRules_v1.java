package com.otaku.rest.param.daftar_ulang;
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


@Path("/v1/daftar_ulang")
public class SearchDaftarUlangRules_v1 {
private static final String version = "ver.1";
	
	/*
	 * return : seluruh info beasiswa dalam bentuk JSON 
	 */
	@Path("/param/rules/{thsms_target}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getList(@PathParam("thsms_target") String thsms_target) throws Exception {
		Response res = null;
		try {
			SearchQueryDaftarUlangTable_v1 sob = new SearchQueryDaftarUlangTable_v1();
			res = sob.returnListRules(thsms_target);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	

}


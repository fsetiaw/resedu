package com.otaku.rest.class_pool;
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


@Path("/v1/class_pool")
public class SearchClassPool {
private static final String version = "ver.1";
	

	@Path("rules/{thsms_buka_kelas}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingleObjectType(@PathParam("thsms_buka_kelas") String thsms_target) throws Exception {
		Response res = null;
		try {
			SearchQueryClassPoolRulesTable_v1 sob = new SearchQueryClassPoolRulesTable_v1();
			res = sob.returnListClassPoolRules(thsms_target);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}

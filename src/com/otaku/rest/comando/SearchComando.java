package com.otaku.rest.comando;
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


@Path("/v1/search_cmd")
public class SearchComando {
private static final String version = "ver.1";
	
	/*
	 * DEPRICATED
	 * return : seluruh info hak akses dari tabel comando dalam bentuk JSON 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllObjectType() throws Exception {
		Response res = null;
		try {
			SearchQueryComandoTable_v1 sob = new SearchQueryComandoTable_v1();
			res = sob.returnListCmd();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	@Path("list_cmd")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListAllCommand() throws Exception {
		Response res = null;
		try {
			SearchQueryComandoTable_v1 sob = new SearchQueryComandoTable_v1();
			res = sob.returnListCmd_ver2();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("obj_lvl/{objLvl}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingleObjectType(@PathParam("objLvl") String objLvl) throws Exception {
		Response res = null;
		try {
			SearchQueryComandoTable_v1 sob = new SearchQueryComandoTable_v1();
			res = sob.returnListCmd(Long.valueOf(objLvl).longValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}

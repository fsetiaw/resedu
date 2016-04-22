package com.otaku.rest.dosen;
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


@Path("/v1/search_dsn")
public class SearchDosen {
private static final String version = "ver.1";
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDosen() throws Exception {
		Response res = null;
		try {
			SearchQueryCivitasDataDosenTable_v1 sob = new SearchQueryCivitasDataDosenTable_v1();
			res = sob.returnAllDataDosenPengajar();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Path("status/{stdos}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDosenAktif(@PathParam("stdos") String stdos) throws Exception {
		Response res = null;
		try {
			SearchQueryCivitasDataDosenTable_v1 sob = new SearchQueryCivitasDataDosenTable_v1();
			res = sob.returnAllDataDosenPengajar(stdos);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("kdpst/{kdpst}/tipe_ika/{tipe_ika}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingleObjectType(@PathParam("kdpst") String kdpst, @PathParam("tipe_ika") String tipe_ika) throws Exception {
		Response res = null;
		try {
			SearchQueryCivitasDataDosenTable_v1 sob = new SearchQueryCivitasDataDosenTable_v1();
			res = sob.returnListDosenGiven(kdpst, tipe_ika);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	@Path("npm/{npm}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingleObjectType(@PathParam("npm") String npm) throws Exception {
		Response res = null;
		try {
			SearchQueryCivitasDataDosenTable_v1 sob = new SearchQueryCivitasDataDosenTable_v1();
			res = sob.returnDataDosenPengajar(npm);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("data_incomplete/tkn_ika/{tkn_ika}/tkn_prodi/{tkn_prodi}/kdpti/{kdpti}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingleObjectType(@PathParam("tkn_ika") String tkn_ika, @PathParam("tkn_prodi") String tkn_prodi, @PathParam("kdpti") String kdpti) throws Exception {
		Response res = null;
		try {
			SearchQueryCivitasDataDosenTable_v1 sob = new SearchQueryCivitasDataDosenTable_v1();
			res = sob.returnAllDataDosenPTYgBlumAdaTipeIkaDanBaseProdi(tkn_ika, tkn_prodi, kdpti);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}

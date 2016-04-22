package com.otaku.rest.pos_biaya;
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


@Path("/v1/pos_biaya")
public class SearchPosBiaya_v1 {
private static final String version = "ver.1";

	@Path("/backup")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDataFromBackupPosRevenueTable() throws Exception {
		Response res = null;
		try {
			SearchQueryPosRevenueTable_v1 sob = new SearchQueryPosRevenueTable_v1();
			res = sob.returnAllFromBackupPosRevenueTable();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	
	@Path("/pymnt_type/tunai_dan_bank")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTargetAkunBasedOnKdjen() throws Exception {
		Response res = null;
		try {
			SearchQueryPosRevenueTable_v1 sob = new SearchQueryPosRevenueTable_v1();
			res = sob.returnPosYgDapatDibayarkanTunaiDanBank();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("/pymnt_type/tunai_dan_bank/{kdjen}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTargetAkunBasedOnKdjen(@PathParam("kdjen") String kdjen) throws Exception {
		Response res = null;
		try {
			SearchQueryPosRevenueTable_v1 sob = new SearchQueryPosRevenueTable_v1();
			res = sob.returnPosYgDapatDibayarkanTunaiDanBank(kdjen);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("/pymnt_type/tunai_dan_bank/{kdjen}/{keter_shift}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTargetAkunBasedOnKdjen(@PathParam("kdjen") String kdjen, @PathParam("keter_shift") String keter_shift) throws Exception {
		Response res = null;
		keter_shift = keter_shift.replace("N&#x2f", "/");
		try {
			SearchQueryPosRevenueTable_v1 sob = new SearchQueryPosRevenueTable_v1();
			res = sob.returnPosYgDapatDibayarkanTunaiDanBank(kdjen, keter_shift);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}

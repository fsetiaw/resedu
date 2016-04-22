package com.otaku.rest.kalender;
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


@Path("/v1/kalender")
public class SearchKalendarTabel_v1 {
private static final String version = "ver.1";
	
	/*
	 * return : seluruh info hak akses dari tabel kalender dalam bentuk JSON 
	 */
	@Path("/aktif")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInfoKalenderAktif() throws Exception {
		Response res = null;
		try {
			SearchQueryKalenderTable_v1 sob = new SearchQueryKalenderTable_v1();
			res = sob.returnInfo();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
/*	
	@Path("/getTargetAkun/{nama_master_pos_revenue}/{keter_shift}/{kdjen}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTargetAkunBasedOnKdjen(@PathParam("nama_master_pos_revenue") String nama_pos,@PathParam("keter_shift") String keter_shift,@PathParam("kdjen") String kdjen) throws Exception {
		Response res = null;
		keter_shift = keter_shift.replace("N&#x2f", "/");
		try {
			SearchQueryAkunTable_v1 sob = new SearchQueryAkunTable_v1();
			res = sob.returnTargetAkunKdjenBased(nama_pos, keter_shift, kdjen);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	@Path("/getAkunTunai/{kdjen}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAkunTunaiBasedOnKdjen(@PathParam("kdjen") String kdjen) throws Exception {
		Response res = null;
		try {
			SearchQueryAkunTable_v1 sob = new SearchQueryAkunTable_v1();
			res = sob.returnAkunTunaiKdjenBased(kdjen);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
*/
}

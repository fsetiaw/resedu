package com.otaku.rest.pilih_kelas_rules;


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


@Path("/v1/pilih_kelas_rules/update")
public class UpdatePilihKelasRulesTabel_v1 {
private static final String version = "ver.1";
	
	/*
	 * return : seluruh info hak akses dari tabel kalender dalam bentuk JSON 
	 */
	@Path("/copy/{token_trans}/{token_input_value}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRulesOnTargetThsms(@PathParam("token_trans") String token_trans, @PathParam("token_input_value") String token_input_value) throws Exception {
		Response res = null;

		try {
			UpdateQueryPilihKelasRulesTable_v1 uob = new UpdateQueryPilihKelasRulesTable_v1();
			if(token_trans!=null) {
				token_trans = uob.convertBackVariableFromUrlRest(token_trans);
			}
			if(token_input_value!=null) {
				token_input_value = uob.convertBackVariableFromUrlRest(token_input_value);
			}
			//System.out.println("token_trans1="+token_trans);
			res = uob.copyRuleFromPrevThsms(token_trans,token_input_value);
			//res = sob.returnInfo();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	
	@Path("/from/updateForm/{token_trans}/{token_input_value}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response updRulesOnTargetThsms(@PathParam("token_trans") String token_trans, @PathParam("token_input_value") String token_input_value) throws Exception {
		Response res = null;
		//System.out.println("masip");
		try {
			UpdateQueryPilihKelasRulesTable_v1 uob = new UpdateQueryPilihKelasRulesTable_v1();
			if(token_trans!=null) {
				token_trans = uob.convertBackVariableFromUrlRest(token_trans);
			}
			if(token_input_value!=null) {
				token_input_value = uob.convertBackVariableFromUrlRest(token_input_value);
			}
			//System.out.println("token_trans1="+token_trans);
			//res = uob.updRuleFromPrevThsms(token_trans,token_input_value);
			res = uob.updRuleFromPrevThsms_v1(token_trans,token_input_value);
			//res = sob.returnInfo();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	@Path("/delete_prev_records/{token_trans}/{target_thsms}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAllRec(@PathParam("token_trans") String token_trans,@PathParam("target_thsms") String target_thsms) throws Exception {
		Response res = null;
		//System.out.println("masip");
		try {
			UpdateQueryPilihKelasRulesTable_v1 uob = new UpdateQueryPilihKelasRulesTable_v1();
			if(token_trans!=null) {
				token_trans = uob.convertBackVariableFromUrlRest(token_trans);
			}
			
			//System.out.println("token_trans1="+token_trans);
			res = uob.deletePrevRec(token_trans,target_thsms);
			//res = sob.returnInfo();
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

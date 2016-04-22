package com.otaku.rest.object;
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

@Path("/v1/search_obj_type")
public class SearchObjType {
	

	private static final String version = "ver.1";
	
	/*
	 * return : seluruh info tiap objeck yang ada pada table OBEJEK dalam bentuk JSON 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllObjectType() throws Exception {
		Response res = null;
		try {
			SearchQueryObjectTable_v1 sob = new SearchQueryObjectTable_v1();
			res = sob.returnListObjectType();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
	
	/*
	 * return : info objeck dengan given objek_id pada table OBEJEK dalam bentuk JSON 
	 */
	@Path("/{objId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingleObjectType(@PathParam("objId") String objId) throws Exception {
		Response res = null;
		try {
			SearchQueryObjectTable_v1 sob = new SearchQueryObjectTable_v1();
			res = sob.returnListObjectType(Integer.valueOf(objId).intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * return : info objeck dengan given objek_id pada table OBEJEK dalam bentuk JSON 
	 */
	@Path("/{objId}/hak_akses")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllObjectType(@PathParam("objId") String objId) throws Exception {
		Response res = null;
		try {
			SearchQueryObjectTable_v1 sob = new SearchQueryObjectTable_v1();
			res = sob.returnHakAksesObjectType(Integer.valueOf(objId).intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	} 
	
	@Path("/{objId}/hak_akses/{targetScope}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllObjectType(@PathParam("objId") String objId,@PathParam("targetScope") String targetScope) throws Exception {
		Response res = null;
		try {
			SearchQueryObjectTable_v1 sob = new SearchQueryObjectTable_v1();
			res = sob.returnHakAksesObjectTypeForScope(Integer.valueOf(objId).intValue(),targetScope);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	} 
	
	
	/*
	 * return : objeck nickname 
	 */
	@Path("/{objId}/nickname")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObjNickname(@PathParam("objId") String objId) throws Exception {
		Response res = null;
		try {
			SearchQueryObjectTable_v1 sob = new SearchQueryObjectTable_v1();
			res = sob.returnObjectTknNickname(Integer.valueOf(objId).intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	} 
	
	/*
	 * return : objeck nickname based on npm
	 */
	@Path("/npm/{npm}/nickname")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObjNicknameBasedOnNpm(@PathParam("npm") String npm) throws Exception {
		Response res = null;
		try {
			SearchQueryObjectTable_v1 sob = new SearchQueryObjectTable_v1();
			res = sob.returnObjectTknNicknameBasedOnNpm(npm);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	} 
	
	@Path("/kdpst/{kdpst}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObjGivenKdpst(@PathParam("kdpst") String kdpst) throws Exception {
		Response res = null;
		try {
			SearchQueryObjectTable_v1 sob = new SearchQueryObjectTable_v1();
			res = sob.returnListObjectGivenKdpst(kdpst);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	} 
	
	
	/*
	 * return : objeck nickname based on npm
	 */
	@Path("/info_akses/{objId}/{target_param}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsrInfoAksesDetailgivenParam_v1(@PathParam("objId") int obj_id ,@PathParam("target_param") String target_param) throws Exception {
		Response res = null;
		try {
			SearchQueryObjectTable_v1 sob = new SearchQueryObjectTable_v1();
			res = sob.getUsrInfoDetailHakAksesGivemParam_v1(obj_id,target_param);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	} 
	
	
}

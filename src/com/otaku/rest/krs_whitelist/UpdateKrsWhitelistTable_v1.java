package com.otaku.rest.krs_whitelist;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.otaku.db.UpdateQueryKalenderTable_v1;
import com.otaku.db.UpdateQueryKrsWhitelist_v1;


@Path("/v1/krs_whitelist/update")
public class UpdateKrsWhitelistTable_v1 {
	private static final String version = "ver.1";
	

	@Path("/from/krs_whitelist_form/{token_trans}/{token_input_value}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateKalenderParam(@PathParam("token_trans") String token_trans, @PathParam("token_input_value") String token_input_value) throws Exception {
		Response res = null;
		
		//System.out.println("terima var = "+token_trans+","+token_input_value);
		try {
			UpdateQueryKrsWhitelist_v1 uob = new UpdateQueryKrsWhitelist_v1();
			if(token_trans!=null) {
				token_trans = uob.convertBackVariableFromUrlRest(token_trans);
			}
			if(token_input_value!=null) {
				token_input_value = uob.convertBackVariableFromUrlRest(token_input_value);
			}
			//System.out.println("token_trans1="+token_trans);
			//System.out.println("token_input1="+token_input_value);
			
			res = uob.updateDataFromFormKrsWhitelist(token_trans,token_input_value);
			//res = sob.returnInfo();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	


}

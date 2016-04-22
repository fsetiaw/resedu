package com.otaku.db;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.owasp.esapi.ESAPI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.NamingException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.otaku.util.VectorTools;
import com.otaku.util.converter.ToJson;
import java.util.ListIterator;


public class UpdateQueryKalenderTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	/*
	 * return UPDATE_STATUS : int
	 */
	public Response updateDataFromFormSettingParam(String token_trans, String tkn_input_value) throws Exception {
		Response res = null;
		boolean valid = false;
		int upd = 0;
		/*
		 * ALWAYS REMEBER convertBackVariableFromUrlRest input variable
		 */
		//if(token_trans!=null) {
		//	token_trans = convertBackVariableFromUrlRest(token_trans);
		//}
		//if(tkn_input_value!=null) {
		//	tkn_input_value = convertBackVariableFromUrlRest(tkn_input_value);
		//}
		try {
						
			 //validate token
			//System.out.println("start validating"); 
			valid = validateTokenTransaction(token_trans);
			//System.out.println("done validating");
			//System.out.println("valid 00 ="+valid);
			if(valid && tkn_input_value!=null) {
				
				StringTokenizer st = new StringTokenizer(tkn_input_value,"`");
				
				String thsms=st.nextToken();
				String thsms_pmb=st.nextToken();
				String thsms_reg=st.nextToken();
				String thsms_buka_kls=st.nextToken();
				String thsms_nilai=st.nextToken();
				String oper_allow_edit_nilai=st.nextToken();
				String allow_edit_nilai_all_thsm=st.nextToken();
				String thsms_krs=st.nextToken();
				String tgl_sta_krs=st.nextToken();
				String tgl_end_krs=st.nextToken();
				String mhs_allow_input_krs=st.nextToken();
				String under_maintenance=st.nextToken();
				String npm_allow_under_maintenance=st.nextToken();
				String kdpst=st.nextToken();
				String id=st.nextToken();
				String tgl_orientasi=st.nextToken();
				String tgl_kuliah_sta=st.nextToken();
				String tgl_uts_sta=st.nextToken();
				String tgl_uas_sta=st.nextToken();
				String tgl_kuliah_end=st.nextToken();
				String opr_allow_ins_krs=st.nextToken();
				String list_tgl_pmb_per_gel=st.nextToken();
				String list_tgl_saring_per_gel=st.nextToken();
				String list_tgl_hereg_per_gel=st.nextToken();
				String range_thsms_sta=st.nextToken();
				String range_thsms_fokus=st.nextToken();
				String range_thsms_end=st.nextToken();
				String reg_for_inp_krs=st.nextToken();
				
				conn = OtakuSQL.connectToUsg();
				String cmd = "UPDATE CALENDAR SET "
						+"THSMS=?,"//1
						+"THSMS_PMB=?,"//2
						+"THSMS_HEREGISTRASI=?,"//3
						+"THSMS_KRS=?,"//4
						+"LIST_THSMS_FOR_EDIT=?,"//5
						+"TGL_ORIENTASI=?,"//6
						+"TGL_AWAL_PERKULIAHAN=?,"//7
						+"TGL_UTS=?,"//8
						+"TGL_UAS=?,"//9
						+"TGL_AKHIR_PERKULIAHAN=?,"//10
						+"LIST_TGL_PMB=?,"//11
						+"LIST_TGL_UJIAN_PENYARINGAN=?,"//12
						+"LIST_TGL_PENDAFTARAN_ULANG=?,"//13
						+"RANGE_LIST_THSMS=?,"//14
						+"UPDATETIME=?,"//15
						+"THSMS_BUKA_KELAS=?,"//16
						+"MHS_ALLOW_PENGAJUAN_KRS=?,"//17
						+"TGL_MULAI_PENGAJUAN_KRS=?,"//18
						+"TGL_AKHIR_PENGAJUAN_KRS=?,"//19
						+"OPERATOR_ALLOW_INS_KRS=?,"//20
						+"OPERATOR_ALLOW_EDIT_NILAI=?,"//21
						+"ALLOW_EDIT_NILAI_ALL_THSMS=?,"//22
						+"UNDER_MAINTENANCE=?,"//23
						+"NPM_ALLOW_UNDER_MAINTENANCE=?,"//24
						+"THSMS_INP_NILAI_MK=?,"//25
						+"REGISTRASI_FOR_KRS=? "//26
						+"WHERE ID=?";//27
				//System.out.println(cmd);
				stmt = conn.prepareStatement(cmd);
				int i=1;
				//1
				//stmt.setString(i++, thsms);
				if(isStringNullOrEmpty(thsms) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, thsms);	
				}
				//System.out.println(i-1+".thsms="+thsms);
				//2.stmt.setString(i++, thsms_pmb);
				if(isStringNullOrEmpty(thsms_pmb) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, thsms_pmb);	
				}
				//System.out.println(i-1+".thsms_pmb="+thsms_pmb);
				
				//3.stmt.setString(i++, thsms_reg);
				if(isStringNullOrEmpty(thsms_reg) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, thsms_reg);	
				}
				//System.out.println(i-1+".thsms_reg="+thsms_reg);
				
				//4.stmt.setString(i++, thsms_krs);
				if(isStringNullOrEmpty(thsms_krs) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, thsms_krs);	
				}
				//System.out.println(i-1+".thsms_krs="+thsms_krs);
				
				//5.list_thsms_for_edit - tidak / belumdigunakan
				//intuk sekarang value disamakan dgn thsms sms
				if(isStringNullOrEmpty(thsms) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, thsms);	
				}
				//System.out.println(i-1+".thsms="+thsms);
				
				//6. tgl orientasi
				String tmp_value = "";
				if(isStringNullOrEmpty(tgl_orientasi) ) {
					
					stmt.setNull(i++, java.sql.Types.DATE);
				}
				else {
					tmp_value = prepStringDateToDbInputFormat(tgl_orientasi);
					stmt.setDate(i++, java.sql.Date.valueOf(tmp_value));	
				}
				//System.out.println(i-1+".tgl_orientasi="+tgl_orientasi);
				
				//7.TGL_AWAL_PERKULIAHAN
				tmp_value = "";
				if(isStringNullOrEmpty(tgl_kuliah_sta) ) {
					stmt.setNull(i++, java.sql.Types.DATE);
				}
				else {
					tmp_value = prepStringDateToDbInputFormat(tgl_kuliah_sta);
					stmt.setDate(i++, java.sql.Date.valueOf(tmp_value));	
				}
				//System.out.println(i-1+".tgl_kuliah_sta="+tgl_kuliah_sta);
				
				//8. tgl uts
				tmp_value = "";
				//System.out.println("tgl_uts_sta="+tgl_uts_sta);
				if(isStringNullOrEmpty(tgl_uts_sta) ) {
					stmt.setNull(i++, java.sql.Types.DATE);
				}
				else {
					tmp_value = prepStringDateToDbInputFormat(tgl_uts_sta);
					stmt.setDate(i++, java.sql.Date.valueOf(tmp_value));	
				}
				//System.out.println(i-1+".tgl_uts_sta="+tgl_uts_sta);
				
				//9. tgl uas
				tmp_value = "";
				if(isStringNullOrEmpty(tgl_uas_sta) ) {
					stmt.setNull(i++, java.sql.Types.DATE);
				}
				else {
					tmp_value = prepStringDateToDbInputFormat(tgl_uas_sta);
					stmt.setDate(i++, java.sql.Date.valueOf(tmp_value));	
				}
				//System.out.println(i-1+".tgl_uas_sta="+tgl_uas_sta);
				
				//10 tgl_kuliah_end
				tmp_value = "";
				if(isStringNullOrEmpty(tgl_kuliah_end) ) {
					stmt.setNull(i++, java.sql.Types.DATE);
				}
				else {
					tmp_value = prepStringDateToDbInputFormat(tgl_kuliah_end);
					stmt.setDate(i++, java.sql.Date.valueOf(tmp_value));	
				}
				//System.out.println(i-1+".tgl_kuliah_end="+tgl_kuliah_end);
				
				//11. list_tgl_pmb_per_gel
				if(isStringNullOrEmpty(list_tgl_pmb_per_gel) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, list_tgl_pmb_per_gel);	
				}
				//System.out.println(i-1+".list_tgl_pmb_per_gel="+list_tgl_pmb_per_gel);
				
				//12. list_tgl_saring_per_gel
				if(isStringNullOrEmpty(list_tgl_saring_per_gel) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, list_tgl_saring_per_gel);	
				}
				//System.out.println(i-1+".list_tgl_saring_per_gel="+list_tgl_saring_per_gel);
				
				//13. list_tgl_hereg_per_gel
				if(isStringNullOrEmpty(list_tgl_hereg_per_gel) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, list_tgl_hereg_per_gel);	
				}
				//System.out.println(i-1+".list_tgl_hereg_per_gel="+list_tgl_hereg_per_gel);
				
				//14. range list thsms
				if(isStringNullOrEmpty(range_thsms_sta) ) {
					range_thsms_sta = "20021";
				}
				if(isStringNullOrEmpty(range_thsms_fokus) ) {
					range_thsms_fokus = "20141";
				}
				if(isStringNullOrEmpty(range_thsms_end) ) {
					range_thsms_end = "20142";
				}
				stmt.setString(i++, range_thsms_sta+","+range_thsms_fokus+","+range_thsms_end);	
				//System.out.println(i-1+".range thsms="+range_thsms_sta+","+range_thsms_fokus+","+range_thsms_end);
				
				//15. UPDATETIME
				stmt.setTimestamp(i++, getCurrentTimestamp());
				//System.out.println(i-1+".getCurrentTimestamp="+getCurrentTimestamp());
				
				//16. thsms_buka_kls
				if(isStringNullOrEmpty(thsms_buka_kls) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, thsms_buka_kls);	
				}
				//System.out.println(i-1+".thsms_buka_kls="+thsms_buka_kls);
				
				//17. mhs_allow_input_krs
				if(mhs_allow_input_krs!=null && (mhs_allow_input_krs.equalsIgnoreCase("1")||mhs_allow_input_krs.equalsIgnoreCase("true"))) {
					stmt.setBoolean(i++, true);
				}
				else {
					stmt.setBoolean(i++, false);
				}
				//System.out.println(i-1+".mhs_allow_input_krs="+mhs_allow_input_krs);
				
				//18. tgl_sta_krs
				tmp_value = "";
				if(isStringNullOrEmpty(tgl_sta_krs) ) {
					stmt.setNull(i++, java.sql.Types.DATE);
				}
				else {
					tmp_value = prepStringDateToDbInputFormat(tgl_sta_krs);
					stmt.setDate(i++, java.sql.Date.valueOf(tmp_value));	
				}
				//System.out.println(i-1+".tgl_sta_krs="+tgl_sta_krs);
				
				//19. tgl_end_krs
				tmp_value = "";
				if(isStringNullOrEmpty(tgl_end_krs) ) {
					stmt.setNull(i++, java.sql.Types.DATE);
				}
				else {
					tmp_value = prepStringDateToDbInputFormat(tgl_end_krs);
					stmt.setDate(i++, java.sql.Date.valueOf(tmp_value));	
				}
				//System.out.println(i-1+".tgl_end_krs="+tgl_end_krs);
				
				//20. opr_allow_ins_krs
				if(opr_allow_ins_krs!=null && (opr_allow_ins_krs.equalsIgnoreCase("1")||opr_allow_ins_krs.equalsIgnoreCase("true"))) {
					stmt.setBoolean(i++, true);
				}
				else {
					stmt.setBoolean(i++, false);
				}
				//System.out.println(i-1+".opr_allow_ins_krs="+opr_allow_ins_krs);
				
				//21. oper_allow_edit_nilai
				if(oper_allow_edit_nilai!=null && (oper_allow_edit_nilai.equalsIgnoreCase("1")||oper_allow_edit_nilai.equalsIgnoreCase("true"))) {
					stmt.setBoolean(i++, true);
				}
				else {
					stmt.setBoolean(i++, false);
				}
				//System.out.println(i-1+".oper_allow_edit_nilai="+oper_allow_edit_nilai);
				
				//22. allow_edit_nilai_all_thsm
				if(allow_edit_nilai_all_thsm!=null && (allow_edit_nilai_all_thsm.equalsIgnoreCase("1")||allow_edit_nilai_all_thsm.equalsIgnoreCase("true"))) {
					stmt.setBoolean(i++, true);
				}
				else {
					stmt.setBoolean(i++, false);
				}
				//System.out.println(i-1+".allow_edit_nilai_all_thsm="+allow_edit_nilai_all_thsm);
				
				//23. under_maintenance
				if(under_maintenance!=null && (under_maintenance.equalsIgnoreCase("1")||under_maintenance.equalsIgnoreCase("true"))) {
					stmt.setBoolean(i++, true);
				}
				else {
					stmt.setBoolean(i++, false);
				}
				//System.out.println(i-1+".under_maintenance="+under_maintenance);
				
				//24. NPM_ALLOW_UNDER_MAINTENANCE
				if(isStringNullOrEmpty(npm_allow_under_maintenance) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, npm_allow_under_maintenance);	
				}
				//System.out.println(i-1+".npm_allow_under_maintenance="+npm_allow_under_maintenance);
				
				//25. THSMS_INP_NILAI_MK 
				if(isStringNullOrEmpty(thsms_nilai) ) {
					stmt.setNull(i++, java.sql.Types.VARCHAR);
				}
				else {
					stmt.setString(i++, thsms_nilai);	
				}
				//System.out.println(i-1+".thsms_nilai="+thsms_nilai);
				
				//26. under_maintenance
				if(reg_for_inp_krs!=null && (reg_for_inp_krs.equalsIgnoreCase("1")||reg_for_inp_krs.equalsIgnoreCase("true"))) {
					stmt.setBoolean(i++, true);
				}
				else {
					stmt.setBoolean(i++, false);
				}
				//27. ID
				stmt.setInt(i++,Integer.parseInt(id));
				//System.out.println(i-1+".id="+id);
				
				upd = stmt.executeUpdate();
				
			}
			
			
			//ToJson converter = new ToJson();
    		//JSONArray joba = new JSONArray();
    		
    		//joba = converter.toJSONArray(rs);
    		//String returnString = joba.toString();
    		res = Response.ok("[{\"UPDATE_STATUS\":\""+upd+"\"}]").build();
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (rs!=null) try  { rs.close(); } catch (Exception ignore){}
		    if (stmt!=null) try  { stmt.close(); } catch (Exception ignore){}
		    if (conn!=null) try { conn.close();} catch (Exception ignore){}
		}	
		return res;
	}
	

	
}

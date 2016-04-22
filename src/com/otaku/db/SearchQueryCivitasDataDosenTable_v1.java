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


public class SearchQueryCivitasDataDosenTable_v1 extends OtakuSQL {
	PreparedStatement stmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	public Response returnListDosenGiven(String kdpst, String tipe_ika) throws Exception {
		/*
		 * civitas dikatakan sbg dosen pengajar jika memiliki data di tabel EXT_CIVITAS_DATA_DOSEN
		 */
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select NPMHS,NMMHSMSMHS from EXT_CIVITAS_DATA_DOSEN inner join CIVITAS on NPMHS=NPMHSMSMHS where IKATAN_KERJA_DOSEN=? and KDPST_HOMEBASE=?");
			stmt.setString(1, tipe_ika);
			stmt.setString(2, kdpst);
			ResultSet rs = stmt.executeQuery();
			
			ToJson converter = new ToJson();
    		JSONArray joba = new JSONArray();
    		
    		joba = converter.toJSONArray(rs);
    		String returnString = joba.toString();
    		res = Response.ok(returnString).build();
			
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
	

	public Response returnDataDosenPengajar(String npmhs) throws Exception {
		/*
		 * civitas dikatakan sbg dosen pengajar jika memiliki data di tabel EXT_CIVITAS_DATA_DOSEN
		 */
		Response res = null;
		try {
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select * from EXT_CIVITAS_DATA_DOSEN inner join CIVITAS on NPMHS=NPMHSMSMHS where NPMHS=?");
			stmt.setString(1, npmhs);
			ResultSet rs = stmt.executeQuery();
			
			ToJson converter = new ToJson();
    		JSONArray joba = new JSONArray();
    		
    		joba = converter.toJSONArray(rs);
    		String returnString = joba.toString();
    		res = Response.ok(returnString).build();
			
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
	
	public Response returnAllDataDosenPengajar() throws Exception {
		/*
		 * civitas dikatakan sbg dosen pengajar jika memiliki data di tabel EXT_CIVITAS_DATA_DOSEN
		 */
		Response res = null;
		try {
			
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select EXT_CIVITAS_DATA_DOSEN.KDPST,EXT_CIVITAS_DATA_DOSEN.NPMHS,EXT_CIVITAS_DATA_DOSEN.NODOS_LOCAL,EXT_CIVITAS_DATA_DOSEN.GELAR_DEPAN,EXT_CIVITAS_DATA_DOSEN.GELAR_BELAKANG,EXT_CIVITAS_DATA_DOSEN.NIDNN,EXT_CIVITAS_DATA_DOSEN.TIPE_ID,EXT_CIVITAS_DATA_DOSEN.NOMOR_ID,EXT_CIVITAS_DATA_DOSEN.STATUS,EXT_CIVITAS_DATA_DOSEN.PT_S1,EXT_CIVITAS_DATA_DOSEN.JURUSAN_S1,EXT_CIVITAS_DATA_DOSEN.KDPST_S1,EXT_CIVITAS_DATA_DOSEN.GELAR_S1,EXT_CIVITAS_DATA_DOSEN.TKN_BIDANG_KEAHLIAN_S1,EXT_CIVITAS_DATA_DOSEN.NOIJA_S1,EXT_CIVITAS_DATA_DOSEN.TGLLS_S1,EXT_CIVITAS_DATA_DOSEN.FILE_IJA_S1,EXT_CIVITAS_DATA_DOSEN.JUDUL_TA_S1,EXT_CIVITAS_DATA_DOSEN.PT_S2,EXT_CIVITAS_DATA_DOSEN.JURUSAN_S2,EXT_CIVITAS_DATA_DOSEN.KDPST_S2,EXT_CIVITAS_DATA_DOSEN.GELAR_S2,EXT_CIVITAS_DATA_DOSEN.TKN_BIDANG_KEAHLIAN_S2,EXT_CIVITAS_DATA_DOSEN.NOIJA_S2,EXT_CIVITAS_DATA_DOSEN.TGLLS_S2,EXT_CIVITAS_DATA_DOSEN.FILE_IJA_S2,EXT_CIVITAS_DATA_DOSEN.JUDUL_TA_S2,EXT_CIVITAS_DATA_DOSEN.PT_S3,EXT_CIVITAS_DATA_DOSEN.JURUSAN_S3,EXT_CIVITAS_DATA_DOSEN.KDPST_S3,EXT_CIVITAS_DATA_DOSEN.GELAR_S3,EXT_CIVITAS_DATA_DOSEN.TKN_BIDANG_KEAHLIAN_S3,EXT_CIVITAS_DATA_DOSEN.NOIJA_S3,EXT_CIVITAS_DATA_DOSEN.TGLLS_S3,EXT_CIVITAS_DATA_DOSEN.FILE_IJA_S3,EXT_CIVITAS_DATA_DOSEN.JUDUL_TA_S3,EXT_CIVITAS_DATA_DOSEN.PT_PROF,EXT_CIVITAS_DATA_DOSEN.JURUSAN_PROF,EXT_CIVITAS_DATA_DOSEN.KDPST_PROF,EXT_CIVITAS_DATA_DOSEN.GELAR_PROF,EXT_CIVITAS_DATA_DOSEN.TKN_BIDANG_KEAHLIAN_PROF,EXT_CIVITAS_DATA_DOSEN.NOIJA_PROF,EXT_CIVITAS_DATA_DOSEN.TGLLS_PROF,EXT_CIVITAS_DATA_DOSEN.FILE_IJA_PROF,EXT_CIVITAS_DATA_DOSEN.JUDUL_TA_PROF,EXT_CIVITAS_DATA_DOSEN.TOTAL_KUM,EXT_CIVITAS_DATA_DOSEN.JABATAN_AKADEMIK_DIKTI,EXT_CIVITAS_DATA_DOSEN.JABATAN_AKADEMIK_LOCAL,EXT_CIVITAS_DATA_DOSEN.JABATAN_STRUKTURAL,EXT_CIVITAS_DATA_DOSEN.IKATAN_KERJA_DOSEN,EXT_CIVITAS_DATA_DOSEN.TANGGAL_MULAI_KERJA,EXT_CIVITAS_DATA_DOSEN.TANGGAL_KELUAR_KERJA,EXT_CIVITAS_DATA_DOSEN.SERDOS,EXT_CIVITAS_DATA_DOSEN.KDPTI_HOMEBASE,EXT_CIVITAS_DATA_DOSEN.KDPST_HOMEBASE,EXT_CIVITAS_DATA_DOSEN.EMAIL_INSTITUSI,EXT_CIVITAS_DATA_DOSEN.PANGKAT_GOLONGAN,EXT_CIVITAS_DATA_DOSEN.CATATAN_RIWAYAT,CIVITAS.NMMHSMSMHS from EXT_CIVITAS_DATA_DOSEN inner join CIVITAS on NPMHSMSMHS=NPMHS order by NMMHSMSMHS");
			ResultSet rs = stmt.executeQuery();
			
			ToJson converter = new ToJson();
    		JSONArray joba = new JSONArray();
    		
    		joba = converter.toJSONArray(rs);
    		String returnString = joba.toString();
    		res = Response.ok(returnString).build();
			
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

	public Response returnAllDataDosenPengajar(String stdos) throws Exception {
		/*
		 *  kode stdos A=aktif dll lat contants
		 */
		Response res = null;
		try {
			
			
			conn = OtakuSQL.connectToUsg();
			stmt = conn.prepareStatement("select EXT_CIVITAS_DATA_DOSEN.KDPST,EXT_CIVITAS_DATA_DOSEN.NPMHS,EXT_CIVITAS_DATA_DOSEN.NODOS_LOCAL,EXT_CIVITAS_DATA_DOSEN.GELAR_DEPAN,EXT_CIVITAS_DATA_DOSEN.GELAR_BELAKANG,EXT_CIVITAS_DATA_DOSEN.NIDNN,EXT_CIVITAS_DATA_DOSEN.TIPE_ID,EXT_CIVITAS_DATA_DOSEN.NOMOR_ID,EXT_CIVITAS_DATA_DOSEN.STATUS,EXT_CIVITAS_DATA_DOSEN.PT_S1,EXT_CIVITAS_DATA_DOSEN.JURUSAN_S1,EXT_CIVITAS_DATA_DOSEN.KDPST_S1,EXT_CIVITAS_DATA_DOSEN.GELAR_S1,EXT_CIVITAS_DATA_DOSEN.TKN_BIDANG_KEAHLIAN_S1,EXT_CIVITAS_DATA_DOSEN.NOIJA_S1,EXT_CIVITAS_DATA_DOSEN.TGLLS_S1,EXT_CIVITAS_DATA_DOSEN.FILE_IJA_S1,EXT_CIVITAS_DATA_DOSEN.JUDUL_TA_S1,EXT_CIVITAS_DATA_DOSEN.PT_S2,EXT_CIVITAS_DATA_DOSEN.JURUSAN_S2,EXT_CIVITAS_DATA_DOSEN.KDPST_S2,EXT_CIVITAS_DATA_DOSEN.GELAR_S2,EXT_CIVITAS_DATA_DOSEN.TKN_BIDANG_KEAHLIAN_S2,EXT_CIVITAS_DATA_DOSEN.NOIJA_S2,EXT_CIVITAS_DATA_DOSEN.TGLLS_S2,EXT_CIVITAS_DATA_DOSEN.FILE_IJA_S2,EXT_CIVITAS_DATA_DOSEN.JUDUL_TA_S2,EXT_CIVITAS_DATA_DOSEN.PT_S3,EXT_CIVITAS_DATA_DOSEN.JURUSAN_S3,EXT_CIVITAS_DATA_DOSEN.KDPST_S3,EXT_CIVITAS_DATA_DOSEN.GELAR_S3,EXT_CIVITAS_DATA_DOSEN.TKN_BIDANG_KEAHLIAN_S3,EXT_CIVITAS_DATA_DOSEN.NOIJA_S3,EXT_CIVITAS_DATA_DOSEN.TGLLS_S3,EXT_CIVITAS_DATA_DOSEN.FILE_IJA_S3,EXT_CIVITAS_DATA_DOSEN.JUDUL_TA_S3,EXT_CIVITAS_DATA_DOSEN.PT_PROF,EXT_CIVITAS_DATA_DOSEN.JURUSAN_PROF,EXT_CIVITAS_DATA_DOSEN.KDPST_PROF,EXT_CIVITAS_DATA_DOSEN.GELAR_PROF,EXT_CIVITAS_DATA_DOSEN.TKN_BIDANG_KEAHLIAN_PROF,EXT_CIVITAS_DATA_DOSEN.NOIJA_PROF,EXT_CIVITAS_DATA_DOSEN.TGLLS_PROF,EXT_CIVITAS_DATA_DOSEN.FILE_IJA_PROF,EXT_CIVITAS_DATA_DOSEN.JUDUL_TA_PROF,EXT_CIVITAS_DATA_DOSEN.TOTAL_KUM,EXT_CIVITAS_DATA_DOSEN.JABATAN_AKADEMIK_DIKTI,EXT_CIVITAS_DATA_DOSEN.JABATAN_AKADEMIK_LOCAL,EXT_CIVITAS_DATA_DOSEN.JABATAN_STRUKTURAL,EXT_CIVITAS_DATA_DOSEN.IKATAN_KERJA_DOSEN,EXT_CIVITAS_DATA_DOSEN.TANGGAL_MULAI_KERJA,EXT_CIVITAS_DATA_DOSEN.TANGGAL_KELUAR_KERJA,EXT_CIVITAS_DATA_DOSEN.SERDOS,EXT_CIVITAS_DATA_DOSEN.KDPTI_HOMEBASE,EXT_CIVITAS_DATA_DOSEN.KDPST_HOMEBASE,EXT_CIVITAS_DATA_DOSEN.EMAIL_INSTITUSI,EXT_CIVITAS_DATA_DOSEN.PANGKAT_GOLONGAN,EXT_CIVITAS_DATA_DOSEN.CATATAN_RIWAYAT,CIVITAS.NMMHSMSMHS from EXT_CIVITAS_DATA_DOSEN inner join CIVITAS on NPMHSMSMHS=NPMHS where STATUS=? order by NMMHSMSMHS");
			stmt.setString(1, stdos);
			ResultSet rs = stmt.executeQuery();
			
			ToJson converter = new ToJson();
    		JSONArray joba = new JSONArray();
    		
    		joba = converter.toJSONArray(rs);
    		String returnString = joba.toString();
    		res = Response.ok(returnString).build();
			
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
	
	
	public Response returnAllDataDosenPTYgBlumAdaTipeIkaDanBaseProdi(String tkn_ika, String tkn_prodi, String kdpti) throws Exception {
		/*
		 * data dosen yg belum punya homebase prodi dan ikatan kerja
		 */
		Response res = null;
		try {
			
			boolean ada_ika = false;
			conn = OtakuSQL.connectToUsg();
			String sql_cmd = "select NPMHS,NMMHSMSMHS from EXT_CIVITAS_DATA_DOSEN inner join CIVITAS on NPMHS=NPMHSMSMHS";
			if(tkn_ika!=null) {
				StringTokenizer st = new StringTokenizer(tkn_ika,"|");
				if(st.hasMoreTokens()) {
					ada_ika = true;
					sql_cmd = sql_cmd + " where ((";
					while(st.hasMoreTokens()) {
						String ika = st.nextToken();
						sql_cmd = sql_cmd + "IKATAN_KERJA_DOSEN<>'"+ika+"'";
						if(st.hasMoreTokens()) {
							sql_cmd = sql_cmd + " and ";
						}
					}
					sql_cmd = sql_cmd + ")";
				}
			}
			
			if(tkn_prodi!=null) {
				StringTokenizer st = new StringTokenizer(tkn_prodi,"|");
				if(st.hasMoreTokens()) {
					if(ada_ika) {
						sql_cmd = sql_cmd + " or (";
					}
					else {
						sql_cmd = sql_cmd + " where (";
					}
					while(st.hasMoreTokens()) {
						String prodi = st.nextToken();
						sql_cmd = sql_cmd + "KDPST_HOMEBASE<>'"+prodi+"'";
						if(st.hasMoreTokens()) {
							sql_cmd = sql_cmd + " and ";
						}
					}
					sql_cmd = sql_cmd + ")";
				}
			}
			if(ada_ika) {
				sql_cmd=sql_cmd+") and KDPTI_HOMEBASE='"+kdpti+"'";
			}
			System.out.println("sqlCommand="+sql_cmd);
			stmt = conn.prepareStatement(sql_cmd);
			ResultSet rs = stmt.executeQuery();
			
			ToJson converter = new ToJson();
    		JSONArray joba = new JSONArray();
    		
    		joba = converter.toJSONArray(rs);
    		String returnString = joba.toString();
    		res = Response.ok(returnString).build();
			
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

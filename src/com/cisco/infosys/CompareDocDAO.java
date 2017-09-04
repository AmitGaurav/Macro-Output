package com.cisco.infosys;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CompareDocDAO {
	public List<Blob> getDocById(String reportRequestId, StringBuffer fileNameSb) throws  Exception {
		int reportRequetId = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Blob binLgObj = null;
		String fileName = null;
		String SQL_GET_BY_PK=null;
		List<Blob> blobList = new ArrayList<Blob>();
		if (reportRequestId != null) {
			reportRequetId = Integer.parseInt(reportRequestId);
		}
		try {
			conn = CompareDocDAO.getConnection();
			SQL_GET_BY_PK= "select DOC_SEQUENCE , assembled_doc from SOW_DOC_DETAILS where doc_type='docx' and DOC_SEQUENCE=? "; 
			pstmt =  conn.prepareStatement(SQL_GET_BY_PK);				
				pstmt.setLong(1, reportRequetId);
				rSet =  pstmt.executeQuery();
				if (rSet.next()) {
					binLgObj = rSet.getBlob(2);
					fileName = reportRequestId+"_report.docx";//rSet.getString(2);
					fileNameSb.append(fileName);
					blobList.add(binLgObj);
				}
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		} finally {
			if(rSet!=null){
				rSet.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
		}
		return blobList;
	}
	
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		try{
			String dbURL = "jdbc:oracle:thin:@dbs-dev-vm-3011.cisco.com:1805:FNTR2DEV";
			String userName = "GAPADM";
			String password = "gapadm123";
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(dbURL,
					userName, password);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) throws Exception {
		getConnection();
		System.out.println("Got the connection");
	}
}

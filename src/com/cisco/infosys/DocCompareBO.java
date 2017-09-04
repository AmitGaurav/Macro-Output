package com.cisco.infosys;

import java.sql.Blob;
import java.util.List;


public class DocCompareBO {
	public Blob getDocById(String requestId, StringBuffer fileNameSb){
		CompareDocDAO dao = new CompareDocDAO();
		Blob binLgObj = null;
		try {
			List<Blob> blobList = dao.getDocById(requestId, fileNameSb);
			if (blobList!=null && blobList.size() > 0) {
				binLgObj = (Blob) blobList.get(0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return binLgObj;
	}
}

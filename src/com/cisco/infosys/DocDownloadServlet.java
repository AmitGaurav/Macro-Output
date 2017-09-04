package com.cisco.infosys;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DocDownloadServlet
 */
@WebServlet("/DocDownloadServlet")
public class DocDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getReportDocById(request, response);
	}
	
	private void getReportDocById(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String requestId = req.getParameter("requestId");
		StringBuffer fileNameSb = new StringBuffer();
		ServletOutputStream out = resp.getOutputStream();
		if(requestId!=null && !requestId.trim().equals("")){
			DocCompareBO bo = new DocCompareBO();
			Blob binLgObj = bo.getDocById(requestId,fileNameSb);
			resp.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			String fileName = fileNameSb.toString();
			resp.setHeader("Content-disposition","attachment; filename=" + fileName);
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			if (binLgObj != null) {
				InputStream in;
				try {
					in = binLgObj.getBinaryStream();
					int length = (int) binLgObj.length();
					buffer = new byte[length];
					while ((length = in.read(buffer)) != -1) {
						out.write(buffer, 0, length);
					}
					in.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.flush();
			}
		}
		else{
			throw new IllegalArgumentException("Wrong input paramenters : requestId "+requestId );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

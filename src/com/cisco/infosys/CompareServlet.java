package com.cisco.infosys;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class CompareServlet
 */
@WebServlet("/CompareServlet")
public class CompareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public CompareServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside Compare Servlet");
		ServletOutputStream sos;
		String root = getServletContext().getRealPath("/");
		String hardCodedFileName=request.getParameter("fname1")+"_"+request.getParameter("fname2");
		System.out.println("dynamic filename through request params:"+hardCodedFileName);
		String extension=".docm";
		File TEMP_DIR_LOC = null;
		File path = new File(root + "/MacroFiles");

		if (! path.exists()){
			path.mkdir();
		}

		String tempFolderName = String.valueOf(System.currentTimeMillis());
		final String SOURCE_DIR_LOC = path.getAbsolutePath(); 
		TEMP_DIR_LOC = new File(SOURCE_DIR_LOC+"\\"+tempFolderName);


		if (! TEMP_DIR_LOC.exists()){
			TEMP_DIR_LOC.mkdir();
		}
		File mainMacroFile = new File(SOURCE_DIR_LOC+"\\123.docm");
		File destFile = new File(TEMP_DIR_LOC+"/"+hardCodedFileName+"_"+extension);
		FileUtils.copyFile(mainMacroFile, destFile);

		System.out.println("macro file inside temp folder path:"+destFile.getAbsolutePath());


		//code to download output file once it has been generated 
		response.setContentType("application/msword");
		File fileToDownload=new File(destFile.getAbsolutePath());
		System.out.println("Download file path:"+fileToDownload.getAbsolutePath()); 
		FileInputStream istr = new FileInputStream(fileToDownload);
		BufferedInputStream bstr = new BufferedInputStream( istr );
		int size = (int) fileToDownload.length();
		byte[] data = new byte[size];
		bstr.read( data, 0, size ); // read into byte array
		bstr.close();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition","attachment; filename=\"" + hardCodedFileName+"_"+extension + "\"");
		sos = response.getOutputStream();
		sos.write(data);
		sos.flush();
		sos.close();
	}

}

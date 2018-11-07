package com.team24.stp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.team24.stp.framework.ServerSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class CommonMgr {
	
	private static FileInputStream fin;

	public static JasperPrint getJrPrint(HashMap<String, Object> params, String fileName, List<? extends Object> list) throws JRException{
		JRBeanCollectionDataSource l_dataSource = new JRBeanCollectionDataSource(list);
		JasperReport jasperRpt = JasperCompileManager.compileReport(fileName);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperRpt, params, l_dataSource);
		return jasperPrint;
	}
	
	public static void exportPdf(JasperPrint jPrint, HttpServletResponse response) throws JRException, IOException{
		JRPdfExporter pdfExporter = new JRPdfExporter();
		response.setContentType("application/pdf");
		pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT,jPrint);
		pdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM,response.getOutputStream());
		pdfExporter.exportReport();
	}
	
	public static void exportExcel(JasperPrint jPrint, HttpServletResponse response, String fileName) throws IOException, JRException{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		JRXlsExporter excelExporter = new JRXlsExporter();

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition","attachment;filename="+fileName);

		excelExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jPrint);
		excelExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,response.getOutputStream());
		excelExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,	Boolean.TRUE);
		excelExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,	Boolean.TRUE);
		excelExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
		excelExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.TRUE);
		excelExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
		excelExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
		excelExporter.exportReport();

		ServletOutputStream servletOutputStream = response.getOutputStream();
		servletOutputStream.write(byteArrayOutputStream.toByteArray());
		servletOutputStream.flush();
	}
	
	 public static void downloadFile(String folder, String fileName, HttpServletResponse response) {
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	        File file = new File(ServerSession.serverPath + folder + "/" + fileName);
	        int length = (int) file.length();
	        byte[] bytes = new byte[length];
	        try {
	            fin = new FileInputStream(file);
	            fin.read(bytes);
	            ServletOutputStream os = response.getOutputStream();
	            os.write(bytes);
	            os.flush();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

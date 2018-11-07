package com.team24.stpservice.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.team24.stp.Mgr.CompanyMgr;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.Result;
import com.team24.stp.framework.ServerSession;
import com.team24.stp.shared.CompanyData;
import com.team24.stp.shared.CompanyDataset;
import com.team24.stp.shared.ValueCaptionDataSet;

@Path("/serviceCompany")
public class ServiceCompany {
	
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	@javax.ws.rs.core.Context
	ServletContext context;

	public static String userid = "";
	public static String username = "";
	public static String userpsw = "";
	
	
	private MrBean getUser() {
		ServerSession.serverPath = request.getServletContext().getRealPath("/") + "/";
		MrBean user = new MrBean();
		user.getUser().setOrganizationID("001");
		user.getUser().setUserId(userid);
		user.getUser().setUserName(username);
		user.getUser().setPassword(userpsw);
		return user;
	}
	

	@POST
	@Path("saveCompany")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Result saveCompany(CompanyData data) {
		Result res = CompanyMgr.saveCompanyData(data, getUser());
		return res;
	}


	@GET
	@Path("getCompanylist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CompanyDataset getCompanylist() {
		String searchVal = request.getParameter("searchVal");
		String page = request.getParameter("page");
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		System.out.println("page  : " + page + "  order  : " + order + "  sort  :  " + sort);
		System.out.println("start  :" + start);
		System.out.println("end  :" + end);
		CompanyDataset res = new CompanyDataset();
		res = CompanyMgr.searchCompanybyValue(searchVal, start, end, sort, order, getUser());
		return res;
	}

	@GET
	@Path("readComapany")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CompanyData readCompanyDataBySyskey() {
		CompanyData res = new CompanyData();
		String key = request.getParameter("syskey");
		long syskey = 0;
		if(key!=null) {
			syskey = Long.parseLong(key);
		}
		res = CompanyMgr.readDataBySyskey(syskey, getUser());
		return res;
	}

	@GET
	@Path("deleteCompany")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result deleteCompanyData() {
		String key = request.getParameter("syskey");
		long syskey = 0;
		if(key!=null) {
			syskey = Long.parseLong(key);
		}
		Result res = CompanyMgr.deleteCompanyData(syskey, getUser());
		return res;
	}
	
	
	@GET
	@Path("getCompanyName")
	@Produces(MediaType.APPLICATION_JSON)
	public ValueCaptionDataSet getCompanyName() {
		ValueCaptionDataSet res = new ValueCaptionDataSet();
		res = CompanyMgr.getCompanyName(getUser());
		return res;
	}



}

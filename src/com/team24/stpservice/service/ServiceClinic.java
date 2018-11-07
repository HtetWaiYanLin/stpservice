package com.team24.stpservice.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.team24.stp.Mgr.ClinicMgr;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.ServerSession;
import com.team24.stp.shared.ClinicData;
import com.team24.stp.shared.ClinicDataset;

@Path("/serviceClinic")
public class ServiceClinic {

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

	@GET
	@Path("getCliniclist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ClinicDataset getCliniclist() {
		String searchVal = request.getParameter("searchval");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		ClinicDataset res = new ClinicDataset();
		res = ClinicMgr.searchClinicbyValue(searchVal, start, end, getUser());
		return res;
	}

	@GET
	@Path("readClinic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ClinicData readClinicDataBySyskey() {
		ClinicData res = new ClinicData();
		String key = request.getParameter("syskey");
		long syskey = 0;
		if (key != null) {
			syskey = Long.parseLong(key);
		}
		res = ClinicMgr.readDataBySyskey(syskey, getUser());
		return res;
	}

}

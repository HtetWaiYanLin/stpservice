package com.team24.stpservice.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.team24.stp.Mgr.AddressMgr;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.ServerSession;
import com.team24.stp.shared.AddressCombo;

@Path("/serviceaddress")
public class ServiceAddress {
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	@javax.ws.rs.core.Context
	ServletContext context;

	private MrBean getUser() {
		ServerSession.serverPath = request.getServletContext().getRealPath("/") + "/";
		MrBean user = new MrBean();
		user.getUser().setOrganizationID("001");
		user.getUser().setUserId(Service001.userid);
		user.getUser().setUserName(Service001.username);
		return user;
	}

	@GET
	@Path("getDivision")
	@Produces(MediaType.APPLICATION_JSON)
	public AddressCombo[] getDivision() {
		AddressCombo[] res = null;
		res = AddressMgr.getDivision(getUser());
		return res;
	}

	@GET
	@Path("getDistrictbyDiv")
	@Produces(MediaType.APPLICATION_JSON)
	public AddressCombo[] getDistrictbyDiv() {
		String division = request.getParameter("div");
		AddressCombo[] res = null;
		res = AddressMgr.getDistrictbyDiv(division, getUser());
		return res;
	}

	@GET
	@Path("getTownshipByDistrict")
	@Produces(MediaType.APPLICATION_JSON)
	public AddressCombo[] getTownshipByDistrict() {
		String district = request.getParameter("district");
		AddressCombo[] res = null;
		res = AddressMgr.getTownshipByDistrict(district, getUser());
		return res;
	}


}

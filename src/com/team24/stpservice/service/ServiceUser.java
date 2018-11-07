package com.team24.stpservice.service;

import java.sql.SQLException;

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

import com.team24.stp.Mgr.RoleMgr;
import com.team24.stp.Mgr.UserMgr;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.Result;
import com.team24.stp.framework.ServerSession;
import com.team24.stp.shared.RoleMenuData;
import com.team24.stp.shared.RoleParentMenuData;
import com.team24.stp.shared.UserData;
import com.team24.stp.shared.UserDataset;

@Path("/serviceUser")
public class ServiceUser {

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
	@Path("saveUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Result saveCompany(UserData data) {
		Result res = UserMgr.saveUserData(data, getUser());
		return res;
	}

	@GET
	@Path("getUserlist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserDataset getUserlist() {
		String searchVal = request.getParameter("searchVal");
		String page = request.getParameter("page");
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		System.out.println("page  : " + page + "  order  : " + order + "  sort  :  " + sort);
		System.out.println("start  :" + start);
		System.out.println("end  :" + end);
		UserDataset res = new UserDataset();
		res = UserMgr.searchUserbyValue(searchVal, start, end, sort, order, getUser());
		return res;
	}

	@GET
	@Path("readUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserData readUserDataBySyskey() {
		UserData res = new UserData();
		String key = request.getParameter("syskey");
		long syskey = 0;
		if (key != null) {
			syskey = Long.parseLong(key);
		}
		res = UserMgr.readDataBySyskey(syskey, getUser());
		return res;
	}

	@GET
	@Path("deleteUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result deleteUserData() {
		String key = request.getParameter("syskey");
		long syskey = 0;
		if (key != null) {
			syskey = Long.parseLong(key);
		}
		Result res = UserMgr.deleteUserData(syskey, getUser());
		return res;
	}
	
	@GET
	@Path("getRole")
	@Produces(MediaType.APPLICATION_JSON)
	public RoleParentMenuData getRole() throws SQLException {
		RoleMenuData[] dataarray;
		RoleParentMenuData res = new RoleParentMenuData();
		dataarray = RoleMgr.getRoleMenuList(getUser());
		res.setMenu(dataarray);
		return res;
	}

}

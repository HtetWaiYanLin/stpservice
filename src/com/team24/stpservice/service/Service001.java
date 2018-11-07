package com.team24.stpservice.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.team24.stp.Mgr.MenuDataMgr;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.Result;
import com.team24.stp.framework.ServerSession;
import com.team24.stp.shared.MenuData;
import com.team24.stp.shared.MenuViewDataset;
import com.team24.stp.shared.ValueCaptionDataSet;
import com.team24.stp.util.UtilFile;

@Path("/service001")
public class Service001 {
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	@javax.ws.rs.core.Context
	ServletContext context;

	public static String userid = "";
	public static String username = "";
	public static String userpsw = "";

	/*
	 * private MrBean getUser(Profile p) { ServerSession.serverPath =
	 * request.getServletContext().getRealPath("/") + "/"; MrBean user = new
	 * MrBean(); user.getUser().setOrganizationID("001");
	 * user.getUser().setUserId(p.getUserID());
	 * user.getUser().setPassword(p.getPassword());
	 * user.getUser().setUserName(p.getUserName()); return user; }
	 */

	private MrBean getUser() {
		ServerSession.serverPath = request.getServletContext().getRealPath("/") + "/";
		MrBean user = new MrBean();
		user.getUser().setOrganizationID("001");
		user.getUser().setUserId(userid);
		user.getUser().setUserName(username);
		user.getUser().setPassword(userpsw);
		return user;
	}

	/*
	 * @POST
	 * 
	 * @Path("signin")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Profile signin(Profile p) throws
	 * JsonGenerationException, JsonMappingException, IOException { RoleData res =
	 * new RoleData(); if (p != null && p.getUserID() != null && p.getPassword() !=
	 * null) { ServerSession.serverPath =
	 * request.getServletContext().getRealPath("/") + "/"; HttpSession session =
	 * this.request.getSession(true); MrBean user = new MrBean();
	 * user.getUser().setOrganizationID("001");
	 * user.getUser().setUserId(p.getUserID());
	 * user.getUser().setPassword(p.getPassword()); userpsw = p.getPassword();
	 * userid = p.getUserID(); ValueCaptionData data =
	 * Service001Mgr.login(getUser(p)); String u1 = data.getCaption(); if
	 * (!u1.equals("")) { res =
	 * Service001Mgr.getRole(Long.parseLong(data.getValue()), getUser(p)); if
	 * (res.getT1().equalsIgnoreCase("master") ||
	 * OrganizationMgr.checkOrgExpired(p.getUserID(), getUser())) {
	 * p.setPassword("*"); p.setUserName(u1); //
	 * p.setUserPhoto(UserDataMgr.getUserInfo(userid, user).getT8());
	 * p.setUsersyskey(UserDataMgr.getUserInfo(userid, user).getSyskey()); //
	 * String[] a2 = Service001Mgr.getMainMenu(getUser(p), p.getUserID()); if
	 * (a2.length > 0) { p.setMenus(getProfileMainMenu(a2, getUser(p),
	 * p.getUserID())); } p.setRightMenus(getProfileRightMenu(getUser(p),
	 * p.getUserID())); username = u1; ButtonCarryData[] arr =
	 * Service001Mgr.getBtns(getUser(p), data.getValue()); p.setBtndata(arr);
	 * p.setT1(data.getValue()); p.setRole(res.getSyskey()); if
	 * (res.getT1().equalsIgnoreCase("Content Writer")) { p.setLoginStatus(1); } if
	 * (res.getT1().equalsIgnoreCase("Editor")) { p.setLoginStatus(2); } if
	 * (res.getT1().equalsIgnoreCase("publisher")) { p.setLoginStatus(3); } if
	 * (res.getT1().equalsIgnoreCase("master")) { p.setLoginStatus(4); } if
	 * (res.getT1().equalsIgnoreCase("admin")) { p.setLoginStatus(5); } if
	 * (p.getLoginStatus() > 0) {
	 * p.setStatus(QnAMgr.getRoleStatusCount(p.getLoginStatus(), getUser())); } }
	 * else { p.setRole(-1); }
	 * 
	 * } else { p.setRole(0); } } else { p.setRole(0); } return p; }
	 */

	/*
	 * @POST
	 * 
	 * @Path("changePassword")
	 * 
	 * @Consumes({ MediaType.APPLICATION_JSON })
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Result
	 * changePassword(PasswordData data) { Result res = new Result(); res =
	 * Service001Mgr.changePassword(data, getUser()); return res; }
	 */

	/*
	 * @POST
	 * 
	 * @Path("saveUser")
	 * 
	 * @Consumes({ MediaType.APPLICATION_JSON })
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Result saveUser(UserData data) {
	 * Result res = new Result(); if (data.getRolesyskey() != null &&
	 * data.getRolesyskey().length != 0) {
	 * 
	 * if (!ServerUtil.isUniEncoded(data.getName())) { // userid
	 * data.setName(FontChangeUtil.zg2uni(data.getName())); }
	 * 
	 * if (!ServerUtil.isUniEncoded(data.getT1())) { // userid
	 * data.setT1(FontChangeUtil.zg2uni(data.getT1())); }
	 * 
	 * if (!ServerUtil.isUniEncoded(data.getT6())) { // name
	 * data.setT6(FontChangeUtil.zg2uni(data.getT6())); }
	 * 
	 * if (!ServerUtil.isUniEncoded(data.getT7())) { // userid
	 * data.setT7(FontChangeUtil.zg2uni(data.getT7())); }
	 * 
	 * if(OrganizationMgr.checkOrg(data, getUser())) { res =
	 * UserDataMgr.saveUserData(data, getUser()); } else {
	 * res.setMsgDesc("User Count is Limited in Organization!"); } } else {
	 * res.setMsgDesc("Please select Role!"); }
	 * 
	 * return res; }
	 */

	String request(String dir, String to, String msg) {
		String ret = "0";
		long ts = System.currentTimeMillis();
		long rdm = 100 + (int) (Math.random() * 900);
		String thepath = context.getRealPath("/");
		if (dir == null || dir.equals(""))
			thepath += "requests";
		else
			thepath += dir;
		if (to != null && msg != null) {
			UtilFile.makedir(thepath);
			UtilFile.writeLine(thepath + "/" + ts + "_" + rdm + ".req", to + "|" + msg, false);
			ret = "1";
		}
		return ret;
	}

	@GET
	@Path("del")
	@Produces("text/plain")
	public String del(@QueryParam("channel") String dir, @QueryParam("keys") String files) {
		String ret = "0";
		String thepath = context.getRealPath("/");
		if (dir == null || dir.equals(""))
			thepath += "requests";
		else
			thepath += dir;
		if (files != null) {
			String row[] = files.split("\\|\\|\\|");
			for (int i = 0; i < row.length; i++) {
				UtilFile.del(thepath + "/" + row[i].split("\\|\\|")[0]);
			}
			ret = "1";
		}
		return ret;
	}

	// confirm button for BlueNumber (mobile or web )
	/*
	 * @POST
	 * 
	 * @Path("checksRegOTP")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public RegisterData
	 * checksRegOTP(RegisterData p) { Result result = new Result(); boolean b =
	 * false; RegisterData ret = new RegisterData(); b =
	 * RegisterMgr.isExistMobile(p, getUser()); if (b == true) { result =
	 * OPTMgr.UpdateUserData(p, getUser()); if (result.isState()) { ret =
	 * RegisterMgr.readByID(p.getT1(), getUser()); } else { } } else { }
	 * 
	 * return ret;
	 * 
	 * }
	 */
	/*
	 * // TDA for resetoptcode
	 * 
	 * @POST
	 * 
	 * @Path("regResetOTPCodeforupdate")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public String
	 * regResetOTPCodeforupdate(RegisterData p) throws SQLException { String ret =
	 * ""; String otpcode = goresetotpcodeforiuser(p.getT1(), p.getT59()); if
	 * (otpcode != null) { ret = RegisterMgr.resetoptforregister(p.getT1(), otpcode,
	 * getUser()); goSMSGateWay(p.getT1(), ret, p.getT61()); } return ret; }
	 */

	/*
	 * @GET
	 * 
	 * @Path("goDeactivate")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Result goDeactivate() throws
	 * SQLException { Result res = new Result(); String mobile =
	 * request.getParameter("t1"); res = OPTMgr.deleteUser(mobile, getUser());
	 * return res; }
	 */

	/*
	 * @GET
	 * 
	 * @Path("goLogout")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Result goLogout() throws
	 * SQLException { Result res = new Result(); String mobile =
	 * request.getParameter("t1"); if (mobile.contains("+959")) { res =
	 * OPTMgr.deleteUser(mobile, getUser()); } else if (mobile.contains("@")) { res
	 * = OPTMgr.deleteUserByMail(mobile, getUser()); } return res; }
	 */

	/*
	 * @GET
	 * 
	 * @Path("checkUser")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Result checkUser() throws
	 * SQLException { Result res = new Result(); String t1 =
	 * request.getParameter("t1").replace(" ", "+"); res = OPTMgr.checkUser(t1,
	 * getUser());
	 * 
	 * return res; }
	 */

	/*
	 * // for OPTData (APX)
	 * 
	 * @GET
	 * 
	 * @Path("Deactivate")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Result Deactivate() throws
	 * SQLException { Result res = new Result(); String mobile =
	 * request.getParameter("t1"); res = OPTMgr.deleteOPT(mobile, getUser()); return
	 * res; }
	 */

	/*
	 * @GET
	 * 
	 * @Path("readBySyskey")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public RegisterData readBySyskey()
	 * throws NumberFormatException, SQLException { RegisterData ret; String key =
	 * request.getParameter("key"); ret =
	 * RegisterMgr.readDataBySyskey(Long.parseLong(key), getUser());
	 * 
	 * return ret; }
	 */

	/*
	 * @GET
	 * 
	 * @Path("readByprofileSyskey")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public RegisterData
	 * readByprofileSyskey() { RegisterData ret; String key =
	 * request.getParameter("key"); ret =
	 * RegisterMgr.readByprofileSyskey(Long.parseLong(key), getUser()); return ret;
	 * }
	 */

	/*
	 * @POST
	 * 
	 * @Path("updateProfile")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public RegisterData
	 * updateProfile(RegisterData data) { // Result res = new Result(); RegisterData
	 * res = new RegisterData(); res = RegisterMgr.saveProfile(data, getUser());
	 * res.setUsersyskey(data.getUsersyskey()); return res; }
	 */

	/*
	 * // TDA for update profile
	 * 
	 * @POST
	 * 
	 * @Path("updateProfileforupdate")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public RegisterData
	 * updateProfileforupdate(RegisterData data) { RegisterData res = new
	 * RegisterData(); res = RegisterMgr.saveProfileforupdate(data, getUser());
	 * res.setUsersyskey(data.getUsersyskey()); return res; }
	 */

	/*
	 * /// web
	 * 
	 * @POST
	 * 
	 * @Path("searchRegisterList")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public RegisterDataSet
	 * searchRegisterList(PagerData p) { String searchVal =
	 * request.getParameter("searchVal"); if (!ServerUtil.isUniEncoded(searchVal)) {
	 * // name searchVal = FontChangeUtil.zg2uni(searchVal); } RegisterDataSet res =
	 * new RegisterDataSet(); res = RegisterMgr.searchArticleLists(p, searchVal,
	 * getUser()); return res; }
	 */

	/*
	 * // search_Content_writer_list
	 * 
	 * @POST
	 * 
	 * @Path("searchContentWriterList")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public UserViewDataset
	 * searchContentWriterList(PagerData p) { String searchVal =
	 * request.getParameter("searchVal"); String status =
	 * request.getParameter("status"); if (!ServerUtil.isUniEncoded(searchVal)) { //
	 * name searchVal = FontChangeUtil.zg2uni(searchVal); } UserViewDataset res =
	 * new UserViewDataset(); res = UserDataMgr.searchContentWriterList(p,
	 * searchVal, status, getUser()); return res; }
	 */

	/*
	 * @POST
	 * 
	 * @Path("deleteRegister")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Result
	 * deleteMessage(RegisterData p) { Result res = new Result(); Long key =
	 * p.getSyskey(); String sessionKey = request.getParameter("sessionKey");
	 * RegisterMgr.setSessionExpire(p.getSyskey(), sessionKey, getUser()); res =
	 * RegisterMgr.deleteReg(key, p, getUser()); return res; }
	 */

	/*
	 * private String goSMSGateWay(String t1, String otp, String type) { String
	 * urlPar = ""; if (type.equalsIgnoreCase("phone")) { String ph =
	 * t1.replace("+", ""); String urlParameters =
	 * "http://52.74.183.252:8080/apx/module001/service001/mpt?"; urlPar =
	 * urlParameters + "to=" + ph + "&msg=" + otp;
	 * 
	 * } else if (type.equalsIgnoreCase("email")) { //
	 * "http://192.168.214.45:9090/apx/module001/service001/email?"; String
	 * urlParameters = "http://52.74.183.252:8080/apx/module001/service001/email?";
	 * urlPar = urlParameters + "to=" + t1 + "&msg=" + otp; } int responseCode; try
	 * { URL url = new URL(urlPar); HttpURLConnection connection =
	 * (HttpURLConnection) url.openConnection(); connection.setDoInput(true);
	 * connection.setDoOutput(true); connection.setRequestMethod("GET");
	 * responseCode = connection.getResponseCode();
	 * System.out.println("\nSending 'GET' request to URL : " + url);
	 * System.out.println("Response Code : " + responseCode); BufferedReader in =
	 * new BufferedReader(new InputStreamReader(connection.getInputStream()));
	 * String inputLine; StringBuffer response = new StringBuffer(); while
	 * ((inputLine = in.readLine()) != null) { response.append(inputLine); }
	 * in.close(); } catch (Exception e) { throw new
	 * RuntimeException(e.getMessage()); } return otp; }
	 */
	/*
	 * private String goDeactiveforuseriuser(String mobile, String appID) {
	 * 
	 * String urlPar = ""; String urlParameters =
	 * "http://cms.htwettoe.com/iUser/module001/service001/godeactivateuser?";
	 * urlPar = urlParameters + "to=" + mobile + "&appID=" + appID; String result =
	 * "0"; int responseCode; try { URL url = new URL(urlPar); HttpURLConnection
	 * connection = (HttpURLConnection) url.openConnection();
	 * connection.setDoInput(true); connection.setDoOutput(true);
	 * connection.setRequestMethod("GET"); responseCode =
	 * connection.getResponseCode(); BufferedReader in = new BufferedReader(new
	 * InputStreamReader(connection.getInputStream())); String inputLine;
	 * StringBuffer response = new StringBuffer(); while ((inputLine =
	 * in.readLine()) != null) { response.append(inputLine); result =
	 * response.toString(); } in.close(); } catch (Exception e) { throw new
	 * RuntimeException(e.getMessage()); } return result; }
	 * 
	 */ // reset otpcode
	/*
	 * private String goresetotpcodeforiuser(String mobile, String appID) { String
	 * urlPar = ""; String urlParameters =
	 * "http://hcm.nirvasoft.com:8085/iUser/module001/service001/goresetopcodeapx?";
	 * urlPar = urlParameters + "to=" + mobile + "&appID=" + appID; String result =
	 * "0"; int responseCode; try { URL url = new URL(urlPar); HttpURLConnection
	 * connection = (HttpURLConnection) url.openConnection();
	 * connection.setDoInput(true); connection.setDoOutput(true);
	 * connection.setRequestMethod("GET"); responseCode =
	 * connection.getResponseCode(); BufferedReader in = new BufferedReader(new
	 * InputStreamReader(connection.getInputStream())); String inputLine;
	 * StringBuffer response = new StringBuffer(); while ((inputLine =
	 * in.readLine()) != null) { response.append(inputLine); result =
	 * response.toString(); } in.close(); } catch (Exception e) { throw new
	 * RuntimeException(e.getMessage()); } return result; }
	 */
	// email
	public void goSMSLink(String mobile, String otp) {
		String ph = mobile.replace("+", "");
		String urlParameters = "http://apiv2.blueplanet.com.mm/mptsdp/bizsendsmsapi.php?u=farmerapp&p=42f2a2381158585652dec5586d06334d&k=VC&";
		String urlPar = urlParameters + "callerid=" + ph + "&m=" + otp;
		try {
			URL url = new URL(urlPar);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void sendMail(String to, String otpCode) {
		final String SENDERS_EMAIL = "emailotp.office@gmail.com"; // sender mail
		final String SENDERS_PWD = "emailotpmit";
		String subject = "Registration Confirmation";
		String content = "<i>Hello Customer,</i><br>";
		content += "<p> <b>If you did try to sign in: </b> <br>";
		content += "Please use this verification code to complete your sign in: ";
		content += "<b>" + otpCode + "</b></p>";
		content += "<p>Sincerely, </p>";
		content += "<p>Farmer Moblie Application Team.</p><br> ";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host/sender
		props.put("mail.smtp.port", "587"); // TLS Port //587 or 465
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDERS_EMAIL, SENDERS_PWD);
			}
		};
		Session session = Session.getDefaultInstance(props, authenticator);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SENDERS_EMAIL));// sender mail
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setContent(content, "text/html; charset=utf-8");// Send
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * // TDA goDeactivateforuser
	 * 
	 * @GET
	 * 
	 * @Path("goDeactivateforuser")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Result goDeactivateforuser()
	 * throws SQLException { Result res = new Result(); String ret = ""; String
	 * mobile = request.getParameter("t1").replace(" ", "+"); String appID =
	 * request.getParameter("t3"); ret = goDeactiveforuseriuser(mobile, appID); if
	 * (ret.equalsIgnoreCase("1")) { res = OPTMgr.deleteUserfordeactivate(mobile,
	 * getUser()); } return res; }
	 */
	/*
	 * // TDA confirm
	 * 
	 * @POST
	 * 
	 * @Path("confirmOTP")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public RegisterData
	 * confirmOTP(RegisterData p) { RegisterData ret = new RegisterData(); ret =
	 * RegisterMgr.saveRegisterDataforconfirm(p, getUser()); if (ret.getSyskey() >
	 * 0) { ret = RegisterMgr.readByID(p.getT1(), getUser()); } else {
	 * ret.setSyskey(0); } return ret; }
	 */

	/*
	 * // TDA back iuser exit phone no in register(08062017)
	 * 
	 * @POST
	 * 
	 * @Path("checkMobile")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public RegisterData
	 * checkMobile(RegisterData p) throws SQLException { RegisterData ret = new
	 * RegisterData(); if (RegisterMgr.IsExitmoible(p.getT1(), getUser())) { ret =
	 * RegisterMgr.updateDeviceID(p, getUser()); if (ret.getSyskey() > 0) { ret =
	 * RegisterMgr.readByID(p.getT1(), getUser()); } } else { ret.setSyskey(0); }
	 * return ret;
	 * 
	 * }
	 */

	/*
	 * // TDA sending new for update otpcode(08062017)
	 * 
	 * @POST
	 * 
	 * @Path("checkingRegOTP")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public RegisterData
	 * checkingRegOTP(RegisterData p) throws SQLException { RegisterData ret = new
	 * RegisterData(); if (RegisterMgr.IsExitmoible(p.getT1(), getUser())) { ret =
	 * RegisterMgr.updateRegisterDataforotpcode(p, getUser()); if (ret.getSyskey() >
	 * 0) { ret = RegisterMgr.readByID(p.getT1(), getUser()); } else {
	 * ret.setSyskey(0); } } else { ret = RegisterMgr.saveRegisterDataforotp(p,
	 * getUser()); if (ret.getSyskey() > 0) { ret = RegisterMgr.readByID(p.getT1(),
	 * getUser()); } else { ret.setSyskey(0); } } return ret; }
	 */

	/*
	 * // save Register detail
	 * 
	 * @POST
	 * 
	 * @Path("saveRegister")
	 * 
	 * @Consumes({ MediaType.APPLICATION_JSON })
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Result saveUser(RegisterData
	 * data) { Result res = new Result(); if (!data.getT1().equalsIgnoreCase(""))
	 * res = RegisterMgr.saveRegisterData(data, getUser()); return res; }
	 */

	/* Start Menu */
	@GET
	@Path("getMainMenuList")
	@Produces(MediaType.APPLICATION_JSON)
	public ValueCaptionDataSet getMainMenuList() {
		ValueCaptionDataSet res = new ValueCaptionDataSet();
		res = MenuDataMgr.getmainmenulist(getUser());
		return res;
	}

	@POST
	@Path("saveMenu")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Result saveMenu(MenuData data) {
		Result res = MenuDataMgr.saveMenuData(data, getUser());
		return res;
	}

	@GET
	@Path("getmenulist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MenuViewDataset getMenulist() {
		String searchVal = request.getParameter("searchVal");
		String page = request.getParameter("page");
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		System.out.println("page  : " + page + "  order  : " + order + "  sort  :  " + sort);
		System.out.println("start  :" + start);
		System.out.println("end  :" + end);

		MenuViewDataset res = new MenuViewDataset();
		res = MenuDataMgr.searchMenubyValue(searchVal, start, end, sort, order, getUser());
		return res;
	}

	@GET
	@Path("readMenu")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MenuData readMenuDataBySyskey() {
		MenuData res = new MenuData();
		String key = request.getParameter("syskey");
		long syskey = 0;
		if (key != null) {
			syskey = Long.parseLong(key);
		}
		res = MenuDataMgr.readDataBySyskey(syskey, getUser());
		return res;
	}

	@GET
	@Path("deleteMenu")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result deleteMenuData() {
		String key = request.getParameter("syskey");
		long syskey = 0;
		if (key != null) {
			syskey = Long.parseLong(key);
		}
		Result res = MenuDataMgr.deleteMenuData(syskey, getUser());
		return res;
	}

	/* End Menu */

	
}

package com.team24.stpservice.service;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.team24.stp.framework.Result;
import com.team24.stp.shared.FileUploadResponseData;
import com.team24.stp.shared.UploadData;
import com.team24.stp.shared.UserData;
import com.team24.stp.util.ServerUtil;

@Path("/file")
public class FileUpload {
	@Context
	HttpServletRequest request;

	@javax.ws.rs.core.Context
	ServletContext context;

	// upload image for mobile
	@POST
	@Path("/uploadImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public Result uploadImage() {
		Result res = new Result();
		String inputFileName = "";
		String filePath = "upload";
		String sfilePath = "upload";
		String outputFileName = "";
		String userid = "";
		String mobile = "";
		String username = "";
		String time = "";
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			int i = 0;
			for (FileItem item : items) {
				if (item.isFormField()) {
					if (i == 0) {
						inputFileName = item.getString();
					} else if (i == 1) {
						userid = item.getString();
					} else if (i == 2) {
						mobile = item.getString();
					} else if (i == 3) {
						username = item.getString();
					} else if (i == 5) {
						time = item.getString();
					}
					i++;
				} else {
					if (inputFileName.contains("/")) {
						inputFileName = inputFileName.substring(inputFileName.lastIndexOf("/") + 1);
					}
					sfilePath = request.getServletContext().getRealPath("/") + "/" + filePath;
					filePath = request.getServletContext().getRealPath("/") + "/" + filePath;
					String extension = FilenameUtils.getExtension(inputFileName);
					if (extension.equalsIgnoreCase("JPEG") || extension.equalsIgnoreCase("JPG")
							|| extension.equalsIgnoreCase("PNG")) {
						filePath += "/image";
						sfilePath += "/smallImage";
						outputFileName = "IMG";
					} else if (extension.equalsIgnoreCase("MP4") || extension.equalsIgnoreCase("FLV")
							|| extension.equalsIgnoreCase("WEBM") || extension.equalsIgnoreCase("3GP")
							|| extension.equalsIgnoreCase("3GP2") || extension.equalsIgnoreCase("MPEG4")
							|| extension.equalsIgnoreCase("MPEG") || extension.equalsIgnoreCase("WMV")
							|| extension.equalsIgnoreCase("AVI")) {
						filePath += "/video";
						outputFileName = "VDO";
					} else {
						filePath += "/other";
						outputFileName = "FMR";
					}
					File dirSm = new File(sfilePath);
					if (!dirSm.exists())
						dirSm.mkdirs();
					File dir = new File(filePath);
					if (!dir.exists())
						dir.mkdirs();

					String dtFormat = new SimpleDateFormat("yyyyMMdd").format(new Date());
					outputFileName += userid + dtFormat + inputFileName;
					filePath += "/" + outputFileName;
					sfilePath += "/" + outputFileName;
					File l_file = new File(filePath);
					File s_file = new File(sfilePath);
					if (l_file.exists()) {
						try {
							l_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (s_file.exists()) {
						try {
							s_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (l_file.createNewFile()) {
						item.write(l_file);
					}
					if (s_file.createNewFile()) {
						item.write(s_file);
						boolean ret = createThumbnail(sfilePath, sfilePath);
						if (ret == true) {
							String fileName = outputFileName;
							String sfileName = outputFileName;
							UploadData data = new UploadData();
							data.setUserId(mobile);
							data.setUserName(username);
							data.setT1(fileName);
							data.setT6(sfileName);
							data.setCreatedTime(time);
							data.setModifiedTime(time);
							// data = QnAMgr.setUploadDataMobile(data, Long.parseLong(postSys), getUser());
							// res = UploadDao.insertUpload(data, getUser());
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			res.setState(false);
		} catch (Exception e) {
			e.printStackTrace();
			res.setState(false);
		}
		return res;
	}

	@POST
	@Path("fileupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadFile(@QueryParam("f") String filePath, @QueryParam("fn") String inputFileName,
			@QueryParam("id") String userid, @QueryParam("type") String type) throws AWTException {

		String result = "{\"code\":\"ERROR\"}";
		String sfilePath = "";
		String outputFileName = "";
		String imgUrl = "";
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		if (inputFileName.contains("/")) {
			inputFileName = inputFileName.substring(inputFileName.lastIndexOf("/") + 1);
		}
		sfilePath = request.getServletContext().getRealPath("/") + "/" + filePath;
		filePath = request.getServletContext().getRealPath("/") + "/" + filePath;

		String extension = FilenameUtils.getExtension(inputFileName);
		if (extension.equalsIgnoreCase("JPEG") || extension.equalsIgnoreCase("JPG")
				|| extension.equalsIgnoreCase("PNG")) {
			filePath += "/image";
			sfilePath += "/smallImage";

			if (Integer.parseInt(type) == 9) {
				filePath += "/CompanyImage";
				sfilePath += "/CompanyImage";
			} else if (Integer.parseInt(type) == 10) {
				filePath += "/videoImage";
				sfilePath += "/videoImage";
			} else if (Integer.parseInt(type) == 11) {
				filePath += "/orgImage";
				sfilePath += "/orgImage";
			} else {
				filePath += "/contentImage";
				sfilePath += "/contentImage";
			}

			if (Integer.parseInt(type) == 0 || Integer.parseInt(type) == 1 || Integer.parseInt(type) == 2
					|| Integer.parseInt(type) == 3 || Integer.parseInt(type) == 4 || Integer.parseInt(type) == 5
					|| Integer.parseInt(type) == 6 || Integer.parseInt(type) == 7 || Integer.parseInt(type) == 10) {
				filePath += "/" + ServerUtil.getLocalDate();
				sfilePath += "/" + ServerUtil.getLocalDate();
				imgUrl += ServerUtil.getLocalDate();
			}
			outputFileName = "IMG";
		} else if (extension.equalsIgnoreCase("MP4") || extension.equalsIgnoreCase("FLV")
				|| extension.equalsIgnoreCase("WEBM") || extension.equalsIgnoreCase("3GP")
				|| extension.equalsIgnoreCase("3GP2") || extension.equalsIgnoreCase("MPEG4")
				|| extension.equalsIgnoreCase("MPEG") || extension.equalsIgnoreCase("WMV")
				|| extension.equalsIgnoreCase("AVI")) {
			filePath += "/video";
			outputFileName = "VDO";
		} else {
			filePath += "/other";
			outputFileName = "STP";
		}
		File dirSm = new File(sfilePath);
		if (!dirSm.exists())
			dirSm.mkdirs();
		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		String dtFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		outputFileName += userid + dtFormat + "." + extension;
		filePath += "/" + outputFileName;
		sfilePath += "/" + outputFileName;
		imgUrl += "/" + outputFileName;
		File l_file = new File(filePath);
		File s_file = new File(sfilePath);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
				} else {
					if (l_file.exists()) {
						try {
							l_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (s_file.exists()) {
						try {
							s_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (l_file.createNewFile()) {
						item.write(l_file);
					}
					if (!extension.equalsIgnoreCase("MP4")) {
						if (!extension.equalsIgnoreCase("xls")) {
							if (s_file.createNewFile()) {
								item.write(s_file);
								// createThumbnailMobileImage(sfilePath, sfilePath);
								transform(sfilePath, "", 600, 600, 100);
								result = "{\"code\":\"SUCCESS\",\"fileName\":\"" + outputFileName
										+ "\",\"sfileName\":\"" + outputFileName + "\",\"url\":\"" + imgUrl + "\"}";
							}
						} else {
							result = "{\"code\":\"SUCCESS\",\"fileName\":\"" + outputFileName + "\"}";
						}
					} else {
						result = "{\"code\":\"SUCCESS\",\"fileName\":\"" + outputFileName + "\"}";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@POST
	@Path("/mobileupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String mobileuploadFile(@FormDataParam("file") InputStream fis,
			@FormDataParam("file") FormDataContentDisposition fdcd) {

		String orgname = "upload/image/userProfile"; // request.getServletContext().getRealPath("/") + "/"
		File theDir = new File(request.getServletContext().getRealPath("/" + orgname));
		if (!theDir.exists()) {
			try {
				boolean success = theDir.mkdir();
				System.out.println("folder=" + success);
			} catch (SecurityException se) {
				// handle it
				se.printStackTrace();
			}
		} else {
			File childDir = new File(request.getServletContext().getRealPath("/" + orgname));
			if (!childDir.exists()) {
				try {
					childDir.mkdir();
				} catch (SecurityException se) {
					// handle it
					se.printStackTrace();
				}
			}
		}
		OutputStream outpuStream = null;
		String fileName = fdcd.getFileName();
		String basename = FilenameUtils.getBaseName(fileName);
		String extension = FilenameUtils.getExtension(fileName);
		String fullname = basename + "." + extension;
		String filePath = request.getServletContext().getRealPath("/" + orgname + "/" + fullname);
		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			outpuStream = new FileOutputStream(new File(filePath));
			while ((read = fis.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		} finally {
			if (outpuStream != null) {
				try {
					outpuStream.close();
				} catch (Exception ex) {
				}
			}
		}
		return fullname;
	}

	public static boolean createThumbnail(String imageUrl, String targetPath) {
		final int imageSize = 200;
		File thumbnail = new File(targetPath);
		try {
			thumbnail.getParentFile().mkdirs();
			thumbnail.createNewFile();
			BufferedImage sourceImage = ImageIO.read(new File(imageUrl));
			float width = 50;
			float height = 50;
			BufferedImage img2;
			float scaledWidth = (width / height) * (float) imageSize;
			float scaledHeight = imageSize;
			BufferedImage img = new BufferedImage((int) scaledWidth, (int) scaledHeight, sourceImage.getType());
			Image scaledImage = sourceImage.getScaledInstance((int) scaledWidth, (int) scaledHeight,
					Image.SCALE_SMOOTH);
			img.createGraphics().drawImage(scaledImage, 0, 0, null);
			int offset = (int) ((scaledWidth - scaledHeight) / 2f);
			img2 = img.getSubimage(offset, 0, imageSize, imageSize);
			ImageIO.write(img2, "png", thumbnail);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean createThumbnailMobileImage(String imageUrl, String targetPath) {

		File thumbnail = new File(targetPath);
		try {
			thumbnail.getParentFile().mkdirs();
			thumbnail.createNewFile();
			BufferedImage sourceImage = ImageIO.read(new File(imageUrl));
			float origwidth = 0;
			float origheight = 0;
			origwidth = sourceImage.getWidth();
			origheight = sourceImage.getHeight();

			double scaledWidth = 0;
			double scaledHeight = 0;
			double ratio = 0;

			if (origwidth > origheight) {
				ratio = 1.0 * (origwidth / origheight);
			} else {
				ratio = 1.0 * (origheight / origwidth);
			}
			scaledWidth = origwidth;
			scaledHeight = origheight;

			while (scaledWidth > scaledHeight && scaledWidth > 990) {
				scaledWidth = scaledWidth / ratio;
				scaledHeight = scaledHeight / ratio;
			}

			while (scaledHeight > scaledWidth && scaledHeight > 990) {
				scaledWidth = scaledWidth / ratio;
				scaledHeight = scaledHeight / ratio;
			}

			if (scaledWidth == scaledHeight) {
				scaledWidth = 400;
				scaledHeight = 400;
			}

			BufferedImage img = new BufferedImage((int) scaledWidth, (int) scaledHeight, sourceImage.getType());
			Image scaledImage = sourceImage.getScaledInstance((int) scaledWidth, (int) scaledHeight,
					Image.SCALE_SMOOTH);
			img.createGraphics().drawImage(scaledImage, 0, 0, null);
			ImageIO.write(img, "png", thumbnail);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/* ng2-material file upload */
	@POST
	@Path("/uploadProfile")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public UserData uploadProfile() {
		Result res = new Result();
		UserData data = new UserData();
		String inputFileName = "";
		String filePath = "upload";
		String outputFileName = "";
		String userid = "";
		String mobile = "";
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);

			int i = 0;
			for (FileItem item : items) {
				inputFileName = item.getName();
				if (item.isFormField()) {
					if (i == 0) {
						inputFileName = item.getString();
					} else if (i == 1) {
						userid = item.getString();
					} else if (i == 2) {
						mobile = item.getString();
					} else if (i == 3) {
						// username = item.getString();
					}
					i++;

				} else {
					if (inputFileName.contains("/")) {
						inputFileName = inputFileName.substring(inputFileName.lastIndexOf("/") + 1);
					}
					filePath = request.getServletContext().getRealPath("/") + "/" + filePath;
					String extension = FilenameUtils.getExtension(inputFileName);
					if (extension.equalsIgnoreCase("JPEG") || extension.equalsIgnoreCase("JPG")
							|| extension.equalsIgnoreCase("PNG")) {
						filePath += "/smallImage";
						outputFileName = "IMG";
					}
					File dir = new File(filePath);
					if (!dir.exists())
						dir.mkdirs();
					String dtFormat = new SimpleDateFormat("yyyyMMdd").format(new Date());
					outputFileName += userid + dtFormat + inputFileName;
					filePath += "/" + outputFileName;
					System.out.println(filePath);
					File l_file = new File(filePath);
					if (l_file.exists()) {
						try {
							l_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (l_file.createNewFile()) {
						item.write(l_file);
						String todayDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
						data.setT1(mobile);
						data.setT16(outputFileName);
						data.setModifieddate(todayDate);
						// res = RegisterDao.UpdateImageUpload(data, getUser());
						if (res.isState()) {
							// data = RegisterMgr.readByID(data.getT1(), getUser());
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			res.setState(false);
		} catch (Exception e) {
			e.printStackTrace();
			res.setState(false);
		}
		return data;
	}

	@GET
	@Path("fileRemove")
	@Produces(MediaType.APPLICATION_JSON)
	public FileUploadResponseData deleteFile(@QueryParam("fn") String fileName, @QueryParam("type") String type) {
		FileUploadResponseData result = new FileUploadResponseData();
		String filePath = request.getServletContext().getRealPath("/") + "\\upload\\image";
		String sfilePath = request.getServletContext().getRealPath("/") + "\\upload\\smallImage";
		if (type.equalsIgnoreCase("10")) {
			filePath += "\\WriterImage";
			sfilePath += "\\WriterImage";
		}
		try {
			File l_file = new File(filePath + "\\" + fileName);
			File s_file = new File(sfilePath + "\\" + fileName);
			if (l_file.exists()) {
				if (l_file.delete()) {
					result.setCode("SUCCESS");
				}
				if (s_file.delete()) {
					result.setCode("SUCCESS");
				}
			} /*
				 * else { if (QnAMgr.isUploadFileExit(fileName, getUser())) {
				 * result.setCode(QnAMgr.deleteCorrect(fileName, getUser())); } }
				 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@GET
	@Path("fileRemoveContent")
	@Produces(MediaType.APPLICATION_JSON)
	public FileUploadResponseData deleteFileContent(@QueryParam("fn") String fileName, @QueryParam("url") String url) {
		FileUploadResponseData result = new FileUploadResponseData();
		int tdyDate = url.lastIndexOf("/");
		String pathName = url.substring(tdyDate - 8, tdyDate);
		String filePath = request.getServletContext().getRealPath("/") + "\\upload\\image\\contentImage\\" + pathName
				+ "\\" + fileName;
		String sfilePath = request.getServletContext().getRealPath("/") + "\\upload\\smallImage\\contentImage\\"
				+ pathName + "\\" + fileName;

		try {
			File l_file = new File(filePath);
			File s_file = new File(sfilePath);
			if (l_file.exists()) {
				if (l_file.delete()) {
					result.setCode("SUCCESS");
				}
				if (s_file.delete()) {
					result.setCode("SUCCESS");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@POST
	@Path("mobileUploadImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String mobileUploadImage(@QueryParam("f") String filePath, @QueryParam("fn") String inputFileName,
			@QueryParam("type") String type) throws AWTException {

		String result = "{\"code\":\"ERROR\"}";
		String sfilePath = "";
		String outputFileName = "";
		String imgUrl = "";
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		if (inputFileName.contains("/")) {
			inputFileName = inputFileName.substring(inputFileName.lastIndexOf("/") + 1);
		}
		sfilePath = request.getServletContext().getRealPath("/") + "/" + filePath;
		filePath = request.getServletContext().getRealPath("/") + "/" + filePath;

		String extension = FilenameUtils.getExtension(inputFileName);
		if (extension.equalsIgnoreCase("JPEG") || extension.equalsIgnoreCase("JPG")
				|| extension.equalsIgnoreCase("PNG")) {
			filePath += "/image";
			sfilePath += "/smallImage";
			imgUrl += "upload/smallImage";

			if (type.equalsIgnoreCase("chat")) {
				filePath += "/ChatImage";
				sfilePath += "/ChatImage";
				imgUrl += "/ChatImage";
			} else if (type.equalsIgnoreCase("comment")) {
				filePath += "/CommentImage";
				sfilePath += "/CommentImage";
				imgUrl += "/CommentImage";
			}
			outputFileName = "IMG";
		} else {
			return result;
		}
		File dirSm = new File(sfilePath);
		if (!dirSm.exists())
			dirSm.mkdirs();
		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		String dtFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		outputFileName += dtFormat + "." + extension;
		filePath += "/" + outputFileName;
		sfilePath += "/" + outputFileName;
		imgUrl += "/" + outputFileName;
		File l_file = new File(filePath);
		File s_file = new File(sfilePath);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
				} else {
					if (l_file.exists()) {
						try {
							l_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (s_file.exists()) {
						try {
							s_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (l_file.createNewFile()) {
						item.write(l_file);
					}
					if (!extension.equalsIgnoreCase("MP4")) {
						if (!extension.equalsIgnoreCase("xls")) {
							if (s_file.createNewFile()) {
								item.write(s_file);
								// createThumbnailMobileImage(sfilePath, sfilePath);
								transform(sfilePath, "", 600, 600, 100);
								result = "{\"code\":\"SUCCESS\",\"fileName\":\"" + outputFileName
										+ "\",\"sfileName\":\"" + outputFileName + "\",\"url\":\"" + imgUrl + "\"}";
							}
						} else {
							result = "{\"code\":\"SUCCESS\",\"fileName\":\"" + outputFileName + "\"}";
						}
					} else {
						result = "{\"code\":\"SUCCESS\",\"fileName\":\"" + outputFileName + "\"}";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@POST
	@Path("cmsUploadImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String cmsUploadImage(@QueryParam("f") String filePath, @QueryParam("fn") String inputFileName,
			@QueryParam("id") String userid) throws AWTException {

		String result = "{\"code\":\"ERROR\"}";
		String outputFileName = "";
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		if (inputFileName.contains("/")) {
			inputFileName = inputFileName.substring(inputFileName.lastIndexOf("/") + 1);
		}
		filePath = request.getServletContext().getRealPath("/") + "/" + filePath;
		String extension = FilenameUtils.getExtension(inputFileName);
		if (extension.equalsIgnoreCase("JPEG") || extension.equalsIgnoreCase("JPG")
				|| extension.equalsIgnoreCase("PNG")) {
			filePath += "/image/chatImage";
			outputFileName = "IMG";
		} else if (extension.equalsIgnoreCase("MP4") || extension.equalsIgnoreCase("FLV")
				|| extension.equalsIgnoreCase("WEBM") || extension.equalsIgnoreCase("3GP")
				|| extension.equalsIgnoreCase("3GP2") || extension.equalsIgnoreCase("MPEG4")
				|| extension.equalsIgnoreCase("MPEG") || extension.equalsIgnoreCase("WMV")
				|| extension.equalsIgnoreCase("AVI")) {
			filePath += "/video";
			outputFileName = "VDO";
		} else {
			filePath += "/other";
			outputFileName = "FMR";
		}
		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		String dtFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		outputFileName += userid + dtFormat + "." + extension;
		System.out.println(
				"output file name : " + outputFileName + "  / userid  " + userid + "  /  dtFormat  " + dtFormat);
		filePath += "/" + outputFileName;
		File l_file = new File(filePath);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
				} else {
					if (l_file.exists()) {
						try {
							l_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (l_file.createNewFile()) {
						item.write(l_file);
					}
					result = "{\"code\":\"SUCCESS\",\"fileName\":\"" + outputFileName + "\"}";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/// Audio_upload
	@POST
	@Path("audioupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadAudio(@QueryParam("f") String filePath, @QueryParam("fn") String inputFileName,
			@QueryParam("id") String userid) throws AWTException {

		String result = "{\"code\":\"ERROR\"}";
		String sfilePath = "";
		String outputFileName = "";
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		if (inputFileName.contains("/")) {
			inputFileName = inputFileName.substring(inputFileName.lastIndexOf("/") + 1);
		}
		sfilePath = request.getServletContext().getRealPath("/") + "/" + filePath;
		filePath = request.getServletContext().getRealPath("/") + "/" + filePath;
		String extension = FilenameUtils.getExtension(inputFileName);

		if (extension.equalsIgnoreCase("3GP")) {
			filePath += "/audio";
			outputFileName = "AUD";
		} else {
			filePath += "/other";
			outputFileName = "FMR";
		}
		File dirSm = new File(sfilePath);
		if (!dirSm.exists())
			dirSm.mkdirs();
		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		String dtFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		outputFileName += userid + dtFormat + "." + extension;
		filePath += "/" + outputFileName;
		sfilePath += "/" + outputFileName;
		File l_file = new File(filePath);
		File s_file = new File(sfilePath);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
				} else {
					if (l_file.exists()) {
						try {
							l_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (s_file.exists()) {
						try {
							s_file.delete();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (l_file.createNewFile()) {
						item.write(l_file);
					}
					result = "{\"code\":\"SUCCESS\",\"fileName\":\"" + outputFileName + "\"}";
					// }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void transform(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight, int quality)
			throws Exception {
		Image image = javax.imageio.ImageIO.read(new File(originalFile));
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}
		if (imageWidth < thumbWidth && imageHeight < thumbHeight) {
			thumbWidth = imageWidth;
			thumbHeight = imageHeight;
		} else if (imageWidth < thumbWidth)
			thumbWidth = imageWidth;
		else if (imageHeight < thumbHeight)
			thumbHeight = imageHeight;

		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		File newFile = new File(originalFile);
		try {
			javax.imageio.ImageIO.write(thumbImage, "JPG", newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(newFile.getPath() + " created successfully!");
	}
}

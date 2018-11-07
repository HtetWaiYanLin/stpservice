package com.team24.stp.util;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import password.DESedeEncryption;

public class ServerUtil {
	private static Pattern dateFrmtPtrn = Pattern
			.compile("([0][1-9]|[12][0-9]|3[01])/([0][1-9]|1[012])/((19|20)\\d\\d)");
	private static Pattern zawgyi = Pattern.compile("[\u105a\u1060-\u1097]|" + "[\u1033\u1034]|" + "\u1031\u108f|"
			+ "\u1031[\u103b-\u103e]|" + "[\u102b-\u1030\u1032]\u1031|" + " \u1031| \u103b|"
			+ "^\u1031|^\u103b|\u1038\u103b|\u1038\u1031|" + "[\u102d\u102e\u1032]\u103b|\u1039[^\u1000-\u1021]|\u1039$"
			+ "|\u1004\u1039[\u1001-\u102a\u103f\u104e]" + "|\u1039[^u1000-\u102a\u103f\u104e]"
			+ "|\u103c\u103b|\u103d\u103b" + "|\u103e\u103b|\u103d\u103c" + "|\u103e\u103c|\u103e\u103d"
			+ "|\u103b\u103c" + "|[\u102f\u1030\u102b\u102c][\u102d\u102e\u1032]" + "|[\u102b\u102c][\u102f\u102c]"
			+ "|[\u1040-\u1049][\u102b-\u103e\u102b-\u1030\u1032\u1036\u1037\u1038\u103a]"
			+ "|^[\u1040\u1047][^\u1040-\u1049]"
			+ "|[\u1000-\u102a\u103f\u104e]\u1039[\u101a\u101b\u101d\u101f\u1022-\u103f]" + "|\u103a\u103e"
			+ "|\u1036\u102b]" + "|\u102d[\u102e\u1032]|\u102e[\u102d\u1032]|\u1032[\u102d\u102e]"

			+ "|\u102f\u1030|\u1030\u102f" + "|\u102b\u102c|\u102c\u102b"
			+ "|[\u1090-\u1099][\u102b-\u1030\u1032\u1037\u103a-\u103e]"

			+ "|[\u1000-\u10f4][\u1090-\u1099][\u1000-\u104f]"
			+ "|^[\u1090-\u1099][\u1000-\u102a\u103f\u104e\u104a\u104b]" + "|[\u1000-\u104f][\u1090-\u1099]$"
			+ "|[\u105e-\u1060\u1062-\u1064\u1067-\u106d\u1071-\u1074\u1082-\u108d" + "\u108f\u109a-\u109d]"
			+ "[\u102b-\u103e]" + "|[\u1000-\u102a]\u103a[\u102d\u102e\u1032]"// 1031 after other vowel signs
			+ "|[\u102b-\u1030\u1032\u1036-\u1038\u103a]\u1031" + "|[\u1087-\u108d][\u106e-\u1070\u1072-\u1074]"

			+ "|^[\u105e-\u1060\u1062-\u1064\u1067-\u106d\u1071-\u1074" + "\u1082-\u108d\u108f\u109a-\u109d]"
			+ "|[\u0020\u104a\u104b][\u105e-\u1060\u1062-\u1064\u1067-\u106d"
			+ "\u1071-\u1074\u1082-\u108d\u108f\u109a-\u109d]" + "|[\u1036\u103a][\u102d-\u1030\u1032]"
			+ "|[\u1025\u100a]\u1039"

			+ "|[\u108e-\u108f][\u1050-\u108d]" + "|\u102d-\u1030\u1032\u1036-\u1037]\u1039]"
			+ "|[\u1000-\u102a\u103f\u104e]\u1037\u1039"

			+ "|[\u1000-\u102a\u103f\u104e]\u102c\u1039[\u1000-\u102a\u103f\u104e]"
			+ "|[\u102b-\u1030\u1032][\u103b-\u103e]" + "|\u1032[\u103b-\u103e]" + "|\u101b\u103c"
			+ "|[\u1000-\u102a\u103f\u104e]\u1039[\u1000-\u102a\u103f\u104e]\u1039" + "[\u1000-\u102a\u103f\u104e]"
			+ "|[\u1000-\u102a\u103f\u104e]\u1039[\u1000-\u102a\u103f\u104e]" + "[\u102b\u1032\u103d]"

			+ "|[\u1000\u1005\u100f\u1010\u1012\u1014\u1015\u1019\u101a]\u1039\u1021" + "|[\u1000\u1010]\u1039\u1019"
			+ "|\u1004\u1039\u1000" + "|\u1015\u1039[\u101a\u101e]" + "|\u1000\u1039\u1001\u1036"
			+ "|\u1039\u1011\u1032"

			+ "|\u1037\u1032" + "|\u1036\u103b"

			+ "|\u102f\u102f");
	private static Pattern myanmar3Ptrn = Pattern.compile(
			"[ဃငဆဇဈဉညတဋဌဍဎဏဒဓနဘရဝဟဠအ]်|ျ[က-အ]ါ|ျ[ါ-း]|[^\1031]စ် |\u103e|\u103f|\u1031[^\u1000-\u1021\u103b\u1040\u106a\u106b\u107e-\u1084\u108f\u1090]|\u1031$|\u100b\u1039|\u1031[က-အ]\u1032|\u1025\u102f|\u103c\u103d[\u1000-\u1001]/");

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null)
				if (!conn.isClosed())
					conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String encryptPIN(String p) {
		String ret = "";
		try {
			DESedeEncryption myEncryptor = new DESedeEncryption();
			ret = myEncryptor.encrypt(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String decryptPIN(String p) {
		String ret = "";
		try {
			DESedeEncryption myEncryptor = new DESedeEncryption();
			ret = myEncryptor.decrypt(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 20160623.....
	public static String datetoString(String aDate) {
		String l_date = "";
		String[] l_arr = aDate.split("/");
		l_date = l_arr[2] + l_arr[0] + l_arr[1];

		return l_date;
	}

	public static String datetoString1(String aDate) {
		String l_date = "";
		String[] l_arr = aDate.split("/");
		l_date = l_arr[2] + l_arr[1] + l_arr[0];

		return l_date;
	}

	public static String datetoStringYMD(String aDate) {
		String l_date = "";
		String[] l_arr = aDate.split("-");
		l_date = l_arr[0] + l_arr[1] + l_arr[2];

		return l_date;
	}

	public static String ddMMyyyFormat(String aDate) {
		String l_Date = "";
		if (!aDate.equals("") && aDate != null)
			l_Date = aDate.substring(6) + "/" + aDate.substring(0, 4) + "/" + aDate.substring(4, 6);

		return l_Date;
	}

	public static String ddMMyyyFormat1(String aDate) throws NullPointerException {
		String l_Date = "";

		if (!aDate.equals("") && aDate != null) {
			l_Date = aDate.substring(6) + "/" + aDate.substring(4, 6) + "/" + aDate.substring(0, 4);
		}

		return l_Date;
	}

	public static String ddMMyyyFormatPAC(String date) {
		String l_Date = "";
		if (date != null && !date.equals("")) {

			String aDate = date.substring(0, 10);
			l_Date = aDate.substring(8) + "/" + aDate.substring(5, 7) + "/" + aDate.substring(0, 4);
		}

		return l_Date;
	}

	public static String stringDateTimeToStringDateFormat(String aDate) {
		String l_Date = "";
		if (aDate.equals("") || aDate == null) {
			l_Date = "";
		} else {
			aDate = aDate.substring(0, 10);
			String[] l_arr = aDate.split("-");
			l_Date = l_arr[2] + "/" + l_arr[1] + "/" + l_arr[0];
		}

		return l_Date;

	}

	public static String roundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("#,###.##");
		return df2.format(val);
	}

	// TDA
	public static String changeDateFormat(String date, String time) {
		SimpleDateFormat fm = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date_string = "";
		String dateString = "";
		if (!(date.equalsIgnoreCase(""))) {
			if (date.equalsIgnoreCase("Just now")) {
				date_string = date;
			} else {
				dateString = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
				try {
					if (time.equalsIgnoreCase("")) {
						date_string = fm.format(formatter.parse(dateString));
					} else {
						date_string = fm.format(formatter.parse(dateString)) + " at " + time;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return date_string;
	}

	public static int chkDateDiff(String date1, String date2, Connection conn) throws SQLException {
		int num = 0;
		String sql = "";

		if (date2.equals("")) {
			sql = "Select DATEDIFF(day,'" + date1 + "','" + new java.sql.Date(new java.util.Date().getTime())
					+ "') as daydiff";
		} else if (date2 != null && !date2.equals("")) {
			sql = "Select DATEDIFF(day,'" + date1 + "','" + date2 + "') as daydiff";
		}
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		res.next();
		num = res.getInt("daydiff");
		return num;
	}

	public static boolean isDate(String p) {
		boolean ret = false;
		try {
			if (!p.equals("")) {
				if (p.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
					ret = true;
			}
		} catch (Exception ex) {
		}
		return ret;

	}// tza

	// sql check
	public static String checkSQL(String str) {
		String s = "";
		s = str.replaceAll("'", "''");
		return s;
	}

	// Taking Month & Year... ymk 20160922...
	public static int getMonth(int m, int num) {
		int month = m + num;
		if (month > 12) {
			month = month % 12;
			if (month == 0) {
				month = 12;
			}
		}
		return month;
	}

	public static int getYear(int m, int y, int num) {
		int month = m + num;
		int year = y + (month / 12);
		if (month % 12 == 0) {
			year--;
		}
		return year;
	}

	public static String getMonthString(int m) {
		String month = "";

		if (m == 1) {
			month = "January";
		} else if (m == 2) {
			month = "February";
		} else if (m == 3) {
			month = "March";
		} else if (m == 4) {
			month = "April";
		} else if (m == 5) {
			month = "May";
		} else if (m == 6) {
			month = "June";
		} else if (m == 7) {
			month = "July";
		} else if (m == 8) {
			month = "August";
		} else if (m == 9) {
			month = "September";
		} else if (m == 10) {
			month = "October";
		} else if (m == 11) {
			month = "November";
		} else if (m == 12) {
			month = "December";
		}

		return month;
	}

	public static String getStartZero(int aZeroCount, String aValue) {
		while (aValue.length() < aZeroCount) {
			aValue = "0" + aValue;
		}
		return aValue;
	}

	@SuppressWarnings("deprecation")
	public static String datetoString(java.util.Date aDate) {
		String l_date = getStartZero(4, String.valueOf(aDate.getYear() + 1900))
				+ getStartZero(2, String.valueOf(aDate.getMonth() + 1))
				+ getStartZero(2, String.valueOf(aDate.getDate()));

		return l_date;
	}

	public static boolean isDateFormart(String aDate) {
		boolean l_ret = false;
		if (((Matcher) dateFrmtPtrn.matcher(aDate)).matches()) {
			l_ret = true;
		}
		return l_ret;
	}

	public static boolean isZawgyi(String font) {
		boolean l_ret = false;
		if (((Matcher) zawgyi.matcher(font)).matches()) {
			l_ret = true;
		}
		return l_ret;
	}

	public static Boolean isZawgyiEncoded(String value) {
		Matcher matcher = zawgyi.matcher(value);
		return matcher.find();
	}

	public static boolean isMyanmar3(String font) {
		boolean l_ret = false;
		if (((Matcher) myanmar3Ptrn.matcher(font)).matches()) {
			l_ret = true;
		}
		return l_ret;
	}

	public static Boolean isUniEncoded(String value) {
		Matcher matcher = myanmar3Ptrn.matcher(value);
		return matcher.find();
	}

	@SuppressWarnings("deprecation")
	public static String datetoString() {
		String l_date = "";
		java.util.Date l_Date = new java.util.Date();
		l_date = getStartZero(4, String.valueOf(l_Date.getYear() + 1900))
				+ getStartZero(2, String.valueOf(l_Date.getMonth() + 1))
				+ getStartZero(2, String.valueOf(l_Date.getDate()));

		return l_date;
	}

	public static String getMonthName(String date) {
		int effectNo = Integer.parseInt(date.substring(4, 6));
		System.out.println(effectNo);
		String monthName = "";
		switch (effectNo) {
		case 1:
			monthName = "Jan";
			break;
		case 2:
			monthName = "Feb";
			break;
		case 3:
			monthName = "Mar";
			break;
		case 4:
			monthName = "Apr";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "Jun";
			break;
		case 7:
			monthName = "Jul";
			break;
		case 8:
			monthName = "Aug";
			break;
		case 9:
			monthName = "Sep";
			break;
		case 10:
			monthName = "Oct";
			break;
		case 11:
			monthName = "Nov";
			break;
		case 12:
			monthName = "Dec";
			break;
		}
		return monthName;
	}

	// TDA
	public static String myan_number[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	public static String changeMyanNumber(String number) {
		if (number.contains(".")) {
			number = number.replace(".", "/");
			String[] splitcomma = number.split("/");
			number = "";
			for (int i = (splitcomma.length - 1); i >= 0; i--) {
				if (splitcomma[i].length() < 2) {
					splitcomma[i] = "Ã¡ï¿½â‚¬" + splitcomma[i];
				}
				number += splitcomma[i];
			}
		}
		String result = "";
		String num[] = number.split("");
		for (int i = 0; i < num.length; i++) {
			switch (num[i]) {
			case "Ã¡ï¿½â‚¬":
				result += myan_number[0];
				break;
			case "Ã¡ï¿½ï¿½":
				result += myan_number[1];
				break;
			case "Ã¡ï¿½â€š":
				result += myan_number[2];
				break;
			case "Ã¡ï¿½Æ’":
				result += myan_number[3];
				break;
			case "Ã¡ï¿½â€ž":
				result += myan_number[4];
				break;
			case "Ã¡ï¿½â€¦":
				result += myan_number[5];
				break;
			case "Ã¡ï¿½â€ ":
				result += myan_number[6];
				break;
			case "Ã¡ï¿½â€¡":
				result += myan_number[7];
				break;
			case "Ã¡ï¿½Ë†":
				result += myan_number[8];
				break;
			case "Ã¡ï¿½â€°":
				result += myan_number[9];
				break;
			default:
				result += "";
				break;
			}

		}
		return result;
	}

	public static String eng_number[] = { "Ã¡ï¿½â‚¬", "Ã¡ï¿½ï¿½", "Ã¡ï¿½â€š", "Ã¡ï¿½Æ’", "Ã¡ï¿½â€ž", "Ã¡ï¿½â€¦", "Ã¡ï¿½â€ ", "Ã¡ï¿½â€¡", "Ã¡ï¿½Ë†", "Ã¡ï¿½â€°" };

	public static String changeEngNumber(String number) {
		if (number.contains(",")) {
			String[] splitcomma = number.split(",");
			number = "";
			for (int i = 0; i < splitcomma.length; i++) {
				number += splitcomma[i];
			}
		}
		String result = "";
		String num[] = number.split("");
		for (int i = 0; i < num.length; i++) {
			switch (num[i]) {
			case "0":
				result += eng_number[0];
				break;
			case "1":
				result += eng_number[1];
				break;
			case "2":
				result += eng_number[2];
				break;
			case "3":
				result += eng_number[3];
				break;
			case "4":
				result += eng_number[4];
				break;
			case "5":
				result += eng_number[5];
				break;
			case "6":
				result += eng_number[6];
				break;
			case "7":
				result += eng_number[7];
				break;
			case "8":
				result += eng_number[8];
				break;
			case "9":
				result += eng_number[9];
				break;
			}
		}
		if (!result.equals("")) {
			String year = result.substring(0, 4);
			String month = result.substring(4, 6);
			String day = result.substring(6);
			result = day + "." + month + "." + year;
		}
		return result;
	}

	public static String getLocalTime() {
		LocalDateTime localDateAndTime = LocalDateTime.now(ZoneId.of("Asia/Rangoon"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		String formatDateTime = localDateAndTime.format(formatter);
		return formatDateTime;

	}

	public static String getLocalDate() {
		LocalDateTime localDateAndTime = LocalDateTime.now(ZoneId.of("Asia/Rangoon"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formatDateTime = localDateAndTime.format(formatter);
		return formatDateTime;
	}
	
	public static String getLocalTimeFormat() {
		
		LocalDateTime localDateAndTime = LocalDateTime.now(ZoneId.of("Asia/Rangoon"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kmm");
		String formatDateTime = localDateAndTime.format(formatter);
		return formatDateTime;

	}
}

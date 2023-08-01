package com.tes.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

public class Utilities
{

	private static final Logger LOGGER = LogManager.getLogger(Utilities.class);

	// public static String today;
	public static String getTodaysDate()
	{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return now.format(formatter);
	}

	public static int getMaxDaysInMonth(int month, int year)
	{
		Calendar cal = Calendar.getInstance();
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // how can I supply the month here?
		return days;
	}

	public static int daysBetween(Date one, Date two)
	{
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return (int) Math.abs(difference);
	}

	public static Float round(float val, int decimalPlace)
	{
		float finalFloat;
		BigDecimal bigDecimalRound = new BigDecimal(Float.toString(val));
		bigDecimalRound = bigDecimalRound.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

		finalFloat = (Float) bigDecimalRound.floatValue();
		return finalFloat;
	}

	public static float getFloatpoint(float val, int fractionPoint)
	{

		Float finalFloat;
		BigDecimal bigDecimalRound = new BigDecimal(val).setScale(fractionPoint, BigDecimal.ROUND_HALF_UP);

		finalFloat = (Float) bigDecimalRound.floatValue();
		return finalFloat;
	}

	public static String encodeString(String encodedString)
	{
		byte[] encodedByte = Base64.decodeBase64(encodedString);
		String tempEncoded = new String(encodedByte);
		return tempEncoded;
	}

	public static String decodeString(String decodedString)
	{
		byte[] decodedByte = Base64.decodeBase64(decodedString);
		String tempDecoded = new String(decodedByte);
		return tempDecoded;
	}

	public static String DateFormat(String pdate)
	{

		String today_date[] = pdate.split("-");
		int s_year = Integer.parseInt(today_date[0]);
		int s_month = Integer.parseInt(today_date[1]);
		int s_day = Integer.parseInt(today_date[2]);

		String monthStr = s_month < 10 ? "0" + s_month : String.valueOf(s_month);
		String dayStr = s_day < 10 ? "0" + s_day : String.valueOf(s_day);

		return pdate = s_year + "-" + monthStr + "-" + dayStr;
	}

	public static String previousDate(String date) throws ParseException
	{

		String cDate[] = date.split("-");
		int s_year = Integer.parseInt(cDate[0]);
		int s_month = Integer.parseInt(cDate[1]);
		int s_day = Integer.parseInt(cDate[2]);

		Calendar cal = new GregorianCalendar(s_year, s_month - 1, s_day);

		int dow = cal.get(Calendar.DAY_OF_WEEK);

		System.out.println("day of week >> " + dow);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DAY_OF_WEEK, -30);

		return sdf.format(cal.getTime());
	}

	public static String uploadFile(String location, long fileSize) throws IOException
	{
		String url, fileFlag = "NA";
		int fileExist = 0, uploadOk = 1;
		File file2 = new File(location);

		// Check if file already exists
		if (file2.exists())
		{
			fileExist = 1;
			file2.delete();
			// $url = 'analysing.php?msg=File already exists.';
			fileFlag = "File already exists and replaced with new.";
		}
		// Check file size
		if (fileSize > 500000000)
		{
			uploadOk = 0;
			fileFlag = "File is too large.";
		}
		// Check if $uploadOk is set to 0 by an error
		if (uploadOk == 0)
		{
			url = "analysing.php?msg=File was not uploaded.";
			// if everything is ok, try to upload file
		}
		else
		{
			if (file2.exists())
			{
				if (fileFlag != "NA")
					fileFlag = fileFlag + " File uploaded successfully";
				else
					fileFlag = "File uploaded successfully";
			}
			else
			{
				url = "analysing.php?msg=There was an error uploading your file.";
				fileFlag = "Something went wrong";
			}
		}
		return fileFlag;
	}

	public static String getTitle(String title)
	{
		String type = "";
		if (title.equalsIgnoreCase("products") || title.equalsIgnoreCase("product"))
		{
			type = "All Products";
		}
		else if (title.equalsIgnoreCase("byproducts") || title.equalsIgnoreCase("byproduct"))
		{
			type = "All Byproducts";
		}
		else if (title.equalsIgnoreCase("raw"))
		{
			type = "All Raw Materials";
		}
		else if (title.equalsIgnoreCase("fuel"))
		{
			type = "All Fuels";
		}
		else if (title.equalsIgnoreCase("hwp"))
		{
			type = "All Hazardous Wastes from Process";
		}
		else if (title.equalsIgnoreCase("hwpcf"))
		{
			type = "All Hazardous Wastes from PCF";
		}
		else if (title.equalsIgnoreCase("nhwp"))
		{
			type = "All Non-Hazardous Waste from Process";
		}
		else if (title.equalsIgnoreCase("nhwpcf"))
		{
			type = "All Non-Hazardous Waste from PCF";
		}
		else if (title.equalsIgnoreCase("bio"))
		{
			type = "All Bio-Medical Wastes";
		}

		return type;
	}

	public static double getDoubleRoundPoint(double val, int fractionPoint)
	{
		Double finalDouble;
		BigDecimal bigDecimalRound = new BigDecimal(val).setScale(fractionPoint, BigDecimal.ROUND_HALF_UP);
		return finalDouble = (Double) bigDecimalRound.doubleValue();
	}

	public static String removeSpace(String str)
	{
		return str.replaceAll("\\s", "");
	}

	public static LocalDate getDate(String stringDate)
	{
		return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static int getYearFromStringDate(String stringDate)
	{
		return getDate(stringDate).getYear();
	}

	public static int getMonthFromStringDate(String stringDate)
	{
		return getDate(stringDate).getMonthValue();
	}

	public static int getDayFromStringDate(String stringDate)
	{
		return getDate(stringDate).getDayOfMonth();
	}

	public static String getMonthNameFromStringDate(String stringDate)
	{
		return getDate(stringDate).getMonth().name();
	}

	public static float getYearlyConsentQuantity(String units, String unitSplit, Float consent_quantity,
			int workingDays)
	{
		Float actual_quantity = null;
		if (units.equalsIgnoreCase(unitSplit + "/Year"))
		{
			actual_quantity = consent_quantity;
		}
		if (units.equalsIgnoreCase(unitSplit + "/Month"))
		{
			actual_quantity = consent_quantity * 12;
		}
		if (units.equalsIgnoreCase(unitSplit + "/Day"))
		{
			actual_quantity = consent_quantity * 12 * workingDays;
		}
		if (units.equalsIgnoreCase(unitSplit + "/Hr"))
		{
			actual_quantity = consent_quantity * 12 * workingDays * 24;
		}
		return actual_quantity;
	}

	public static float getYearlyConsentQuantityWithFloat(String units, String unitSplit, Float consent_quantity,
			Float finalValue)
	{
		Float actual_quantity = null;
		if (units.equalsIgnoreCase(unitSplit + "/Year"))
		{
			actual_quantity = consent_quantity;
		}
		if (units.equalsIgnoreCase(unitSplit + "/Month"))
		{
			actual_quantity = consent_quantity * 12;
		}
		if (units.equalsIgnoreCase(unitSplit + "/Day"))
		{
			actual_quantity = consent_quantity * 12 * finalValue;
		}
		if (units.equalsIgnoreCase(unitSplit + "/Hr"))
		{
			actual_quantity = consent_quantity * 12 * finalValue * 24;
		}
		return actual_quantity;
	}

	/**
	 * @author vishal
	 * @param stringDate
	 * @param substractDays
	 * @return previous date according to substractDays
	 */
	public static String getDateFromSubstractDays(String stringDate, int substractDays)
	{
		Calendar cal = new GregorianCalendar(getYearFromStringDate(stringDate), getMonthFromStringDate(stringDate) - 1,
				getDayFromStringDate(stringDate));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -substractDays);
		return sdf.format(cal.getTime());
	}

	public static String getMonthForInt(int num)
	{
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 1 && num <= 12)
		{
			month = months[num - 1];
		}
		return month;
	}

	public static String getLastDate(String date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate convertedDate = LocalDate.parse(date, formatter);
		convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
		return (convertedDate).format(formatter);
	}

	public static String renameFile(String compName)
	{

		String CompNam = compName.length() < 3 ? compName : compName.substring(0, 3);
		String maincomp = CompNam.toUpperCase();
		String time = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss").format(new Date());
		String newStr1 = time.replaceAll("[`~!@#$%^&*()_+[\\\\]\\\\\\\\;\\',./{}|:\\\"<>-?]", "");
		maincomp = maincomp.replaceAll("[`~!@#$%^&*()_+[\\\\]\\\\\\\\;\\',./{}|:\\\"<>-?]", "");
		String name = maincomp + "_" + newStr1;
		return name;
	}

	// Common method for getting full product name
	public static String getFullNameOfProduct(String productType)
	{
		if (productType.equalsIgnoreCase("product"))
			productType = "Product";
		if (productType.equalsIgnoreCase("byproduct"))
			productType = "byproduct";
		if (productType.equalsIgnoreCase("raw"))
			productType = "Raw Material";
		if (productType.equalsIgnoreCase("fuel"))
			productType = "Fuel";
		if (productType.equalsIgnoreCase("hwp"))
			productType = "hwp";
		if (productType.equalsIgnoreCase("hwpcf"))
			productType = "hwpcf";
		if (productType.equalsIgnoreCase("nhwp"))
			productType = "nhwp";
		if (productType.equalsIgnoreCase("nhwpcf"))
			productType = "nhwpcf";
		if (productType.equalsIgnoreCase("eWaste"))
			productType = "eWaste";
		if (productType.equalsIgnoreCase("pWaste"))
			productType = "pWaste";
		if (productType.equalsIgnoreCase("bWaste"))
			productType = "bWaste";
		if (productType.equalsIgnoreCase("cdWaste"))
			productType = "cdWaste";
		if (productType.equalsIgnoreCase("cutfill"))
			productType = "cutfill";
		if (productType.equalsIgnoreCase("bio"))
			productType = "Bio-Medical";
		return productType;
	}

	// created by Jemish
	// used for statistics , graphs
	// pass starting date (yy-mm-dd) and number(30 or 7 or 1 or as your wishs) of previous date
	// return list of days >> format >> 17 FEB, 16 FEB , 15 FEB .......
	public static ArrayList<String> getDaysList(String date, int Days) throws ParseException
	{
		ArrayList<String> series = new ArrayList<>();
		String today_date[] = date.split("-");
		int s_year = Integer.parseInt(today_date[0]);
		int s_month = Integer.parseInt(today_date[1]);
		int s_day = Integer.parseInt(today_date[2]);

		for (int days = 0; days <= Days; days++)
		{
			Calendar cal = new GregorianCalendar(s_year, s_month - 1, s_day);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			cal.add(Calendar.DAY_OF_WEEK, -days);
			String newDate = sdf.format(cal.getTime());
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
			String dateToSend[] = newDate.split("-");
			int day = Integer.parseInt(dateToSend[2]);
			String month = new SimpleDateFormat("MMM").format(date1);
			String ddate = month + " " + day;
			series.add(ddate);
		}
		return series;
	}

	// created by Jemish
	// used for convert consented value to per day by unit
	// pass unit name and consented value
	// return consented value / day
	public static Float convertDataToPerDayByUnit(String Unit, Float value)
	{
		String splitedUnit[] = Unit.split("/");
		if (splitedUnit[1].equalsIgnoreCase("Hr"))
			value = value * 24.0f;
		if (splitedUnit[1].equalsIgnoreCase("Week"))
			value = value / 7.0f;
		if (splitedUnit[1].equalsIgnoreCase("Month"))
			value = value / 30.0f;
		if (splitedUnit[1].equalsIgnoreCase("Year"))
			value = value / 365.0f;

		return value;
	}
	// consent as per ,day,,month,year

	public static Float convertDataToByUnitAsPerYrMnthDay(String Unit, Float value, int convertTo)
	{
		String splitedUnit[] = Unit.split("/");
		if (splitedUnit[1].equalsIgnoreCase("Hr"))
			value = value * 24.0f;
		if (splitedUnit[1].equalsIgnoreCase("Week"))
			value = value / 7.0f;
		if (splitedUnit[1].equalsIgnoreCase("Month"))
			value = value / 30.0f;
		if (splitedUnit[1].equalsIgnoreCase("Year"))
			value = value / 365.0f;

		if (convertTo == 30)
			value = value * 7.0f;
		if (convertTo == 365 || convertTo == 366)
			value = value * 30.0f;
		if (convertTo == 0)
			value = value * 365.0f;

		return value;
	}

	public static String getMonthInShort(String dates) throws ParseException
	{
		String[] sDate = dates.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar(Integer.parseInt(sDate[0]), (Integer.parseInt(sDate[1]) - 1), Integer.parseInt(sDate[2]));
		String date = sdf.format(cal.getTime());
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		String dateToSend[] = date.split("-");
		int day = Integer.parseInt(dateToSend[2]);
		String month = new SimpleDateFormat("MMM").format(date1);
		String ddate = month + " " + day;
		return ddate;
	}

	public static ArrayList<String> getWeeklyArrayByDateOfMonth(String lastdate) throws ParseException
	{

		String aa[] = lastdate.split("-");
		int noOfDay = Integer.parseInt(aa[2]);
		String firstDate = aa[0] + "-" + aa[1] + "-00";
		int weeks = noOfDay / 7;
		ArrayList<String> weekArray = new ArrayList<>();
		for (int i = 0; i <= weeks; i++)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(firstDate));
			String nextDate = "";
			if (i == 4)
			{
				nextDate = lastdate;
				if (firstDate.equalsIgnoreCase(nextDate))
				{
					weekArray.add(firstDate);
				}
				else
				{
					weekArray.add(firstDate);
					weekArray.add(nextDate);
				}
			}
			else
			{
				c.add(Calendar.DAY_OF_MONTH, 7);
				nextDate = sdf.format(c.getTime());
				weekArray.add(firstDate);
			}
			firstDate = nextDate;
		}
		return weekArray;
	}

	public static int getCurrentStartYearFromCurrentMonth(int currentMonth)
	{
		int yearFrom = 0;
		switch (currentMonth)
		{
			case 01:
				yearFrom = Calendar.getInstance().get(Calendar.YEAR) - 1;
				break;
			case 02:
				yearFrom = Calendar.getInstance().get(Calendar.YEAR) - 1;
				break;
			case 03:
				yearFrom = Calendar.getInstance().get(Calendar.YEAR) - 1;
				break;
			default:
				// if month is beetween april to December pick 1st year from esr year
				yearFrom = Calendar.getInstance().get(Calendar.YEAR);
		}
		return yearFrom;
	}

	public static Date convertStringToDate(String dateString)
	{
		Date formatdate = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			formatdate = df.parse(dateString);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		return formatdate;
	}

	public static long daysBetweenWithString(String firstDate, String secondDate) throws ParseException
	{

		Date fDate = convertStringToDate(firstDate);
		Date sDate = convertStringToDate(secondDate);
		long difference = (sDate.getTime() - fDate.getTime()) / 86400000;
		return difference;
	}

	public static String saveUserProfilePic(String[] imageArray, String companyName, String userType)
	{
		String imageName = Utilities.renameFile(companyName);

		String extension;

		switch (imageArray[0])
		{// check image's extension
			case "data:image/jpeg;base64":
				extension = "jpeg";
				break;
			case "data:image/png;base64":
				extension = "png";
				break;
			default:// should write cases for more images types
				extension = "jpg";
				break;
		}

		if (userType.equalsIgnoreCase("admin"))
		{
			userType = "admn";
		}
		else if (userType.equalsIgnoreCase("Environmental Officer"))
		{
			userType = "env";
		}
		else if (userType.equalsIgnoreCase("Management"))
		{
			userType = "mn";
		}
		else if (userType.equalsIgnoreCase("Third Party"))
		{
			userType = "tp";
		}
		String newFileName = userType + "_" + imageName + "." + extension;
		byte[] data = imageArray[1].getBytes();
		// byte[] data = DatatypeConverter.parseBase64Binary(imageArray[1]);
		String path = Constant.UserProfiles_pic_path + newFileName;
		File file = new File(path);
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file)))
		{
			outputStream.write(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return newFileName;
	}

	public static String getPreviousDate(String date, int noPrevDate) throws ParseException
	{
		String today_date[] = date.split("-");
		int s_year = Integer.parseInt(today_date[0]);
		int s_month = Integer.parseInt(today_date[1]);
		int s_day = Integer.parseInt(today_date[2]);

		Calendar cal = new GregorianCalendar(s_year, s_month - 1, s_day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DAY_OF_WEEK, -noPrevDate);
		String newDate = sdf.format(cal.getTime());

		return newDate;
	}

	public static ArrayList<String> getWeekly7DaysDifferenceData(String lastdate) throws ParseException
	{
		ArrayList<String> weekArray = new ArrayList<>();

		try
		{
			String firstDate = lastdate.split("-")[0] + "-" + lastdate.split("-")[1] + "-00";
			int weeks = getDayFromStringDate(lastdate) / 7;

			for (int i = 0; i <= weeks; i++)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(firstDate));
				String nextDate = "";
				if (i == 4)
				{
					nextDate = lastdate;
					weekArray.add(firstDate);
					weekArray.add(nextDate);
				}
				else
				{
					c.add(Calendar.DAY_OF_MONTH, 7);
					nextDate = sdf.format(c.getTime());
					weekArray.add(firstDate);
				}
				firstDate = nextDate;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return weekArray;
	}

	public static String getDateinStringFromTimestamp(Timestamp timestampDate)
	{
		String date = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.format(timestampDate);

		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}
		return date;
	}

	public static String toAlphabetic(int i)
	{
		if (i < 0)
		{
			return "-" + toAlphabetic(-i - 1);
		}
		int quot = i / 26;
		int rem = i % 26;
		char letter = (char) ((int) 'A' + rem);
		if (quot == 0)
		{
			return "" + letter;
		}
		else
		{
			return toAlphabetic(quot - 1) + letter;
		}
	}

	public static Float calculatePercentage(float val, int days)
	{
		return getFloatpoint((Float) (((float) val / (float) days) * 100), 2);
	}

	public static String addedDateByDays(String date, int dayy)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localStartDate = LocalDate.parse(date, formatter);
		return localStartDate.plusDays(dayy).toString();
	}

	public static List<String> getDatesRangeArray(String startDate, String endDate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// convert String to LocalDate
		LocalDate localStartDate = LocalDate.parse(startDate, formatter);
		LocalDate localEndDate = LocalDate.parse(endDate, formatter);

		int numOfDays = Math.abs((int) ChronoUnit.DAYS.between(localStartDate, localEndDate));
		if (localStartDate.isBefore(localEndDate))
		{
			return IntStream.iterate(0, i -> i + 1)
					.limit(numOfDays)
					.mapToObj(i -> localStartDate.plusDays(i).toString())
					.collect(Collectors.toList());
		}
		else
		{
			return IntStream.iterate(0, i -> i - 1)
					.limit(numOfDays)
					.mapToObj(i -> localStartDate.plusDays(i).toString())
					.collect(Collectors.toList());
		}

	}
}

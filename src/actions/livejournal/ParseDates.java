package actions.livejournal;

import objects.EntryDate;

public class ParseDates {
	public static EntryDate parseDateFromCode(String date, String pattern) {
		if(pattern == "(?:[0-3][0-9]) (?:January|February|March|April|May|June|July|August|September|October|November|December) (?:20[0-5][0-9])") {
			return parseDDMonthYYYY(date, " ");
		} else if (pattern == "(?:[0-3][0-9])&nbsp;(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Sept|Oct|Nov|Dec)&nbsp;(?:20[0-5][0-9])") {
			return parseddMonYYYY(date, "&nbsp;");
		} else if (pattern == "(?:Jan.|Feb.|Mar.|Apr.|May.|Jun.|Jul.|Aug.|Sep.|Sept.|Oct.|Nov.|Dec.) (?:[0-3][0-9]|[0-9])(?:st|nd|rd|th), (?:20[0-5][0-9])") {
			return parseMonddSufYYYY(date, " ");
		} else if(pattern == "(?:January|February|March|April|May|June|July|August|September|October|November|December) (?:[0-3][0-9]|[0-9])(?:st|nd|rd|th), (?:20[0-5][0-9])") {
			return parseMonthDDYYYY(date, " ");
		} else if(pattern == "(?:[0-3][0-9]|[0-9])(?:st|nd|rd|th)-(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sept|Oct|Nov|Dec)-(?:20[0-5][0-9])") {
			return parseDDMonYYYY(date, "-");
		} else if(pattern == "(?:[0-3][0-9]|[0-9])/(?:[0-1][0-9]|[0-9])/(?:20[0-5][0-9])") {
			return parseddmmYYYY(date, "/");
		} else if(pattern == "(?:[0-3][0-9]|[0-9])/(?:[0-1][0-9]|[0-9])/(?:[0-5][0-9])") {
			return parseddmmYY(date, "/");
		} else if(pattern == "[^0-9](?:[0-1][0-9]|[0-9])/(?:[0-3][0-9]|[0-9])/(?:20[0-5][0-9])") {
			return parsemmddYYYY(date.substring(1), "/");
		} else if(pattern == "[^0-9](?:[0-1][0-9]|[0-9])/(?:[0-3][0-9]|[0-9])/(?:[0-5][0-9])") {
			return parsemmddYY(date.substring(1), "/");
		} else {
			System.out.println("ERROR: Invalid Parsing Date Format    "+pattern);
		}
		return new EntryDate();
	}
	
	public static EntryDate parseDDMonthYYYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		String[] dateArray = parsedDate.split(delimiter);
		String day = dateArray[0];
		String month = dateArray[1];
		String year = dateArray[2];
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}
	
	public static EntryDate parseddMonYYYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		parsedDate = parsedDate.replace("[", "").replace("]", "");
		String[] dateArray = parsedDate.split(delimiter);
		String day = dateArray[0];
		String month = dateArray[1];
		String year = dateArray[2];
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}
	
	public static EntryDate parseMonddSufYYYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		parsedDate = parsedDate.replace("[", "").replace("]", "").replace(",", "");
		String[] dateArray = parsedDate.split(delimiter);
		String month = dateArray[0];
		String day = dateArray[1];
		String year = dateArray[2];
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}
	
	public static EntryDate parseMonthDDYYYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		parsedDate = parsedDate.replace("[", "").replace("]", "").replace(",", "");
		String[] dateArray = parsedDate.split(delimiter);
		String month = dateArray[0];
		String day = dateArray[1];
		String year = dateArray[2];
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}
	
	public static EntryDate parseDDMonYYYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		parsedDate = parsedDate.replace("[", "").replace("]", "").replace(",", "");
		String[] dateArray = parsedDate.split(delimiter);
		String day = dateArray[0];
		String month = dateArray[1];
		String year = dateArray[2];
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}
	
	public static EntryDate parseddmmYYYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		parsedDate = parsedDate.replace("[", "").replace("]", "").replace(",", "");
		String[] dateArray = parsedDate.split(delimiter);
		String day = dateArray[0];
		String month = dateArray[1];
		String year = dateArray[2];
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}
	
	public static EntryDate parseddmmYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		parsedDate = parsedDate.replace("[", "").replace("]", "").replace(",", "");
		String[] dateArray = parsedDate.split(delimiter);
		String day = dateArray[0];
		String month = dateArray[1];
		String year = "20"+dateArray[2];
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}
	
	public static EntryDate parsemmddYYYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		parsedDate = parsedDate.replace("[", "").replace("]", "").replace(",", "");
		String[] dateArray = parsedDate.split(delimiter);
		String month = dateArray[0];
		String day = dateArray[1];
		String year = dateArray[2];
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}
	
	public static EntryDate parsemmddYY(String parsedDate, String delimiter) {
		EntryDate date = new EntryDate();
		parsedDate = parsedDate.replace("[", "").replace("]", "").replace(",", "");
		String[] dateArray = parsedDate.split(delimiter);
		String month = dateArray[0];
		String day = dateArray[1];
		String year = "20"+dateArray[2];
		
		int dayNum = Integer.parseInt(day);
		int monthNum = Integer.parseInt(month);
		int yearNum = Integer.parseInt(year);
		
		date.setDay(dayNum);
		date.setMonth(monthNum);
		date.setYear(yearNum);
		
		return date;
	}
}

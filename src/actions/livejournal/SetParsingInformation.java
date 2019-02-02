package actions.livejournal;

import java.util.ArrayList;
import java.util.List;

import variables.ParsingInformation;

public class SetParsingInformation {
	/*
	public ParsingInformation(String layoutName, boolean linkIsFirst, DatePatternCode datePattern, String subjectCode, boolean shouldClearAll) {
		this.layoutName = layoutName;
		this.linkIsFirst = linkIsFirst;
		this.datePattern = datePattern;
		this.subjectCode = subjectCode;
		this.shouldClearAll = shouldClearAll;
	}
	 */
	
	public static List<String> getDatePatterns(){
		List<String> datePatternList = new ArrayList<String>();
		datePatternList.add("(?:[0-3][0-9]) (?:January|February|March|April|May|June|July|August|September|October|November|December) (?:20[0-5][0-9])");
		datePatternList.add("(?:[0-3][0-9])&nbsp;(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Sept|Oct|Nov|Dec)&nbsp;(?:20[0-5][0-9])");
		datePatternList.add("(?:Jan.|Feb.|Mar.|Apr.|May.|Jun.|Jul.|Aug.|Sep.|Sept.|Oct.|Nov.|Dec.) (?:[0-3][0-9]|[0-9])(?:st|nd|rd|th), (?:20[0-5][0-9])");
		datePatternList.add("(?:January|February|March|April|May|June|July|August|September|October|November|December) (?:[0-3][0-9]|[0-9])(?:st|nd|rd|th), (?:20[0-5][0-9])");
		datePatternList.add("(?:[0-3][0-9]|[0-9])(?:st|nd|rd|th)-(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sept|Oct|Nov|Dec)-(?:20[0-5][0-9])");
		//datePatternList.add("(?:[0-3][0-9]|[0-9])/([0-1][0-9]|[0-9])/(?:20[0-5][0-9])");
		//datePatternList.add("(?:[0-3][0-9]|[0-9])/([0-1][0-9]|[0-9])/(?:[0-5][0-9])");
		datePatternList.add("[^0-9](?:[0-1][0-9]|[0-9])/(?:[0-3][0-9]|[0-9])/(?:20[0-5][0-9])");
		datePatternList.add("[^0-9](?:[0-1][0-9]|[0-9])/(?:[0-3][0-9]|[0-9])/(?:[0-5][0-9])");
		return datePatternList;
	}
	
	public static boolean isStyleInArray(String[] list, String style) {
		for(int i=0; i<list.length; i++) {
			if(list[i].equals(style)) {
				return true;
			}
		}
		return false;
	}
	
	public static ParsingInformation getParsingInformationFromLayoutName(String layoutName) {
		
		String[] dateFirstStyle = {"Flexible Squares", "Smooth Sailing", "Haven", "Magazine", "Variable Flow", "Component", "Style Contest"};
		String[] linkFirstStyle = {"Generator", "Mixit", "Expressive", "Expressive Winter"};
		
		if(isStyleInArray(dateFirstStyle, layoutName)) {
			return new ParsingInformation(layoutName, false);	
		} else if(isStyleInArray(linkFirstStyle, layoutName)) {
			return new ParsingInformation(layoutName, true);
		} 
		return new ParsingInformation();
	}

}

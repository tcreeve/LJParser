package actions.livejournal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import actions.SortEntryList;
import variables.ParsingInformation;
import variables.Properties;
import objects.Entry;
import objects.EntryDate;

public class FindEntries {
	public static String getSubstringUsingIndexOfString(String checkFor, String line) {
		int startIndex = line.indexOf(checkFor)+checkFor.length();
		line = line.substring(startIndex);
		return line;
	}
	
	public static BufferedReader createBufferedReaderFromURL(String currentURLString) throws IOException {
		URL currentURL = new URL(currentURLString);
		InputStream inputStream = currentURL.openStream();
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	
	public static ParsingInformation lookForLayoutInCode(String layoutCode, ParsingInformation parsingInformation, String url) throws IOException {
		BufferedReader bufferedReader = createBufferedReaderFromURL(url);
		
		String line = "";
		while ((line = bufferedReader.readLine()) != null ) {
			if(line.contains(layoutCode) && parsingInformation == null) {
				line = getSubstringUsingIndexOfString(layoutCode+"\":\"", line);
				line = line.substring(0, line.indexOf("\""));
				parsingInformation = SetParsingInformation.getParsingInformationFromLayoutName(line);
			}
        } 
		bufferedReader.close();
		return parsingInformation;
	}
	
	public static String getLinkFromLine(String line) {
		Pattern p = Pattern.compile(Properties.getReformatedLiveJournalURL()+"(.\\d+).html");
		Matcher m = p.matcher(line);
		if(m.find()) {
			if(Properties.isInDebuggingMode()) {
				System.out.println("--------------------"+m.group());
			}
			return m.group(0).substring(0, m.group(0).length());
		}
		return null;
	}
	
	public static String[] getEntryDateFromLine(String line) {
		for(String pattern : SetParsingInformation.getDatePatterns()) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(line);
			if(m.find()) {
				if(Properties.isInDebuggingMode()) {
					System.out.println("--------------------"+pattern);
					System.out.println("--------------------"+m.group());
					System.out.println("--------------------"+ParseDates.parseDateFromCode(m.group(0), pattern).toString());
				}
				String[] returnString = {m.group(0), pattern};
				return returnString;
			}
		}
		return null;
	}
	
	public static List<Entry> getEntryFromPage(ParsingInformation parsingInformation, String url) throws IOException{
		BufferedReader bufferedReader = createBufferedReaderFromURL(url);
		String line = "";
		List<Entry> entryList = new ArrayList<Entry>();
		Entry tempEntry = new Entry();
		EntryDate currentDate = new EntryDate();
		
		int pageTotalEntries = 0;
		
		boolean foundBody = false;
		while((line = bufferedReader.readLine()) != null){
			if(line.contains("<body")) {
				foundBody = true;
			}
			if(foundBody) {
				boolean doContinue = false;
				while(!doContinue) {
					String link = getLinkFromLine(line);
					String[] dateArray = getEntryDateFromLine(line);
					
					if(link != null) {
						line = line.replace(link, "");
					}
	
					if(link != null && (parsingInformation.isLinkFirst() || tempEntry.getDate().isComplete())) {
						tempEntry.setLink(link, Properties.getCharacterName());
						
						if(Properties.isInDebuggingMode()) {
							System.out.println("---Found Link: "+link);
						}
					}
					if(dateArray != null) {
						line = line.replace(dateArray[0], "");
						tempEntry.setDate(ParseDates.parseDateFromCode(dateArray[0], dateArray[1]));
						
						if(Properties.isInDebuggingMode()) {
							System.out.println("---Found Date: "+tempEntry.getDate().toString());
						}
					}
					if(tempEntry.isComplete()) {
						if(!SortEntryList.entryAlreadyExistsInList(entryList, tempEntry)) {
							entryList.add(new Entry(tempEntry.getDate(), tempEntry.getLink()));
							if(Properties.isInDebuggingMode()) {
								System.out.print("Created Entry: ");
							}
							System.out.println(tempEntry.toString());
							pageTotalEntries++;
						}
						if(parsingInformation.isLinkFirst())
							tempEntry.clear();
						else
							tempEntry.setLink("");
					}
					if(link == null && dateArray == null) {
						doContinue = true;
					}
				}
			}
		}
		System.out.println("Total page entries: "+pageTotalEntries);
		return entryList;
	}
	
	public static List<Entry> getEntriesFromURL(){
		List<Entry> currentEntries = new ArrayList<Entry>();
		int subtractFromSkip = 0;
	    ParsingInformation parsingInformation = null;
	    
	    String getCurrentURLString = Properties.getLiveJournalURL(subtractFromSkip);
	    //first time get parsing information
	    try {
	    	if(!getCurrentURLString.equals("")) {
	    		parsingInformation = lookForLayoutInCode("pd_style_layout", parsingInformation, getCurrentURLString);
	    		if(Properties.isInDebuggingMode()) {
	    			System.out.println(parsingInformation.toString());
	    		}
		    }
		    //loop through all 
		    while(!getCurrentURLString.equals("")){
		    	System.out.println(" * "+getCurrentURLString+" * ");
		    	currentEntries.addAll(getEntryFromPage(parsingInformation, getCurrentURLString));
		    	subtractFromSkip += Properties.getSkipBy();
		    	getCurrentURLString = Properties.getLiveJournalURL(subtractFromSkip);
		    }
	    } catch(Exception e) {
	    	System.out.println(e);
	    }
	    return currentEntries;
	    
	}

}

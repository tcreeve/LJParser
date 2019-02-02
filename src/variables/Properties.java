package variables;

import objects.EntryDate;

public class Properties {
	//livejournal values
	private static String livejournalURL = "https://pactbeast.livejournal.com/";
			//"http://twilightdawg.livejournal.com/";
	private static int firstSkip = 0;
	private static String name = "Angelus";
	private static EntryDate startDate = new EntryDate(1, 1, 2008);
	private static EntryDate endDate = new EntryDate(3, 1, 2011);
	private static int skipBy = 10;
	private static String currentlyOnString = "<a href=\"https://entranceooc.livejournal.com/2007/01/03/\">Currently on</a><br />";
	
	//CodeStuff values
	private static final boolean DEBUGGING_MODE = false;
	private static int defaultYear = startDate.getYear().getValue();
	
	//Archive Values
	private static String absolutepath = "<put path here>";
	private static String readfile = "Entranceway Timeline auto.html";//kind of a misnomer as it does write to this to
	//readfile is the end file
	private static String writefile = "timelines\\Entranceway Timeline "+System.nanoTime()+".html";
	//write file is the backups in case something goes wrong
	
	public static String getReformatedLiveJournalURL(){
		if(livejournalURL.contains("https"))
			return livejournalURL;
		return livejournalURL.replace("http", "https");
	}
	
	public static String getLiveJournalURL(int subtractFromSkip){
		String refinedURL = getReformatedLiveJournalURL();
		if(firstSkip > subtractFromSkip){
			return refinedURL + "?skip="+(getFirstSkip()-subtractFromSkip);
		} else if(firstSkip == subtractFromSkip){
			return refinedURL;
		} else {
			return "";
		}
	}
	
	public static String getCurrentlyOnLine() {
		return currentlyOnString;
	}
	
	public static int getFirstSkip(){
		return firstSkip;
	}
	
	public static int getSkipBy(){
		return skipBy;
	}
	
	public static String getCharacterName(){
		return name;
	}
	
	public static EntryDate getStartDate(){
		return startDate;
	}
	
	public static EntryDate getEndDate(){
		return endDate;
	}
	
	public static boolean isInDebuggingMode(){
		return DEBUGGING_MODE;
	}
	
	public static int getDefaultYearValue(){
		return defaultYear;
	}
	
	public static String getAbsolutePath(){
		return absolutepath;
	}
	
	public static String getFinalArchiveFile(){
		return readfile;
	}
	
	public static String getTemporaryArchiveFile(){
		return writefile;
	}
	
	public static String getFinalArchiveFileAddress(){
		return getAbsolutePath()+getFinalArchiveFile();
	}
	
	public static String getTemporaryArchiveFileAddress(){
		return getAbsolutePath()+getTemporaryArchiveFile();
	}

}

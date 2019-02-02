package actions;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import actions.archive.ReadCurrentArchive;
import actions.livejournal.FindEntries;
import objects.Entry;
import objects.EntryDate;
import variables.Properties;

public class UpdateArchive {
	
	public static void writeToBoth(PrintWriter firstWriter, PrintWriter secondWriter, String line) {
		firstWriter.println(line);
		secondWriter.println(line);
	}
	
	public static void reprintFile(List<Entry> archiveList) {
		EntryDate currentDate = new EntryDate();
		
		try {
			PrintWriter tempWriter = new PrintWriter(Properties.getTemporaryArchiveFileAddress(), "UTF-8");
			PrintWriter finalWriter = new PrintWriter(Properties.getFinalArchiveFileAddress(), "UTF-8");
			
			writeToBoth(tempWriter, finalWriter, "<a href=\"http://entranceooc.livejournal.com/2006/08/31/\">Earliest accessible OOC entries</a><br />");
			writeToBoth(tempWriter, finalWriter, Properties.getCurrentlyOnLine());
			writeToBoth(tempWriter, finalWriter, "Currently on: "+Properties.getReformatedLiveJournalURL());
			
			for(Entry entry : archiveList) {
				EntryDate dateForEntry = entry.getDate();
				if(dateForEntry.getYear().getValue() == 0) {
					if(currentDate.getYear().getValue() != dateForEntry.getYear().getValue()) {
						writeToBoth(tempWriter, finalWriter, "\n\n<h1>No Eway Posts</h1>");
					} 
				} else {
					if(currentDate.getYear().getValue() != dateForEntry.getYear().getValue()) {
						writeToBoth(tempWriter, finalWriter, "\n");
						writeToBoth(tempWriter, finalWriter, dateForEntry.getYear().returnYearWithTag());
						writeToBoth(tempWriter, finalWriter, "");
						writeToBoth(tempWriter, finalWriter, dateForEntry.getMonth().returnMonthWithTag());
						writeToBoth(tempWriter, finalWriter, dateForEntry.getDay().returnDayWithTag());
					} else if(currentDate.getMonth().getValue() != dateForEntry.getMonth().getValue()) {
						writeToBoth(tempWriter, finalWriter, "");
						writeToBoth(tempWriter, finalWriter, dateForEntry.getMonth().returnMonthWithTag());
						writeToBoth(tempWriter, finalWriter, dateForEntry.getDay().returnDayWithTag());
					} else if(currentDate.getDay().getValue() != dateForEntry.getDay().getValue()) {
						writeToBoth(tempWriter, finalWriter, dateForEntry.getDay().returnDayWithTag());
					}
				}
				currentDate.setYear(dateForEntry.getYear().getValue());
				currentDate.setMonth(dateForEntry.getMonth().getValue());
				currentDate.setDay(dateForEntry.getDay().getValue());
				
				writeToBoth(tempWriter, finalWriter, entry.getLink());
			}
			tempWriter.close();
			finalWriter.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
	    List<Entry> archiveEntries = ReadCurrentArchive.getCurrentEntries(Properties.getFinalArchiveFileAddress());
	    System.out.println("-------"+Properties.getReformatedLiveJournalURL()+"-------");
	    List<Entry> newEntries = FindEntries.getEntriesFromURL();
	    
	    
	    System.out.println("REMOVING DUPLICATE AND OUT OF BOUND ENTRIES:");
	    int addedEntries = 0;
	    for(Entry entry : newEntries) {
	    	if(!SortEntryList.entryAlreadyExistsInList(archiveEntries, entry)) {
	    		if(entry.dateIsBetween(Properties.getStartDate(), Properties.getEndDate())) {
	    			archiveEntries.add(entry);
		    		addedEntries++;
		    		
		    	    System.out.println("Added "+entry.toString());
	    		}
	    	}
	    }
	    
	    List<Entry> sortedList = SortEntryList.sortEntryListByDateAndLink(archiveEntries);
	    
	    if(!Properties.isInDebuggingMode()) {
	    	reprintFile(sortedList);
	    }
	    
	    System.out.println("------------------END-----------------");
	    System.out.println("Added "+addedEntries+" new entries");
	    if(Properties.isInDebuggingMode()) {
	    	System.out.println("\n\nRun with DEBUGGING_MODE = false to see changes in file");
	    }
	}

}

package actions;
import java.util.Collections;
import java.util.List;

import objects.Entry;
import objects.CustomComparator;

public class SortEntryList {
	
	public static List<Entry> sortEntryListByDateAndLink(List<Entry> entryList){
		List<Entry> sortList = entryList;
		Collections.sort(sortList, new CustomComparator());
		return sortList;
	}
	
	public static boolean entryAlreadyExistsInList(List<Entry> entryList, Entry newEntry) {
		for(Entry e : entryList) {
			if(e.getLink().equals(newEntry.getLink())) {
				return true;
			}
		}
		return false;
	}
}

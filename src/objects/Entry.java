package objects;

public class Entry {
	private EntryDate date = new EntryDate();
	private String link = "";
	
	/**
	 *
	 */
	public Entry() {
		this.date = new EntryDate();
		this.link = "";
	}
	
	/**
	 * @param date
	 * @param link
	 */
	public Entry(EntryDate date, String link) {
		this.date = new EntryDate(date);
		this.link = link;
	}

	/**
	 * @return the date
	 */
	public EntryDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(EntryDate date) {
		this.date = date;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setLink(String link, String name) {
		this.link = "<a href=\""+link+"\">"+name+"</a>";
	}
	
	public void clear() {
		setLink("");
		setDate(new EntryDate());
	}
	
	public boolean dateIsBetween(EntryDate start, EntryDate end) {
		int startCompare = getDate().compareTo(start);
		int endCompare = getDate().compareTo(end);
		if(startCompare == 1) {
			return false;
		}
		if(endCompare == -1) {
			return false;
		}
		return true;
	}
	
	public boolean isComplete() {
		if(!getLink().equals("") && getDate().isComplete()) {
			return true;
		}
		return false;
	}
	
	
	public String toString(){
		return getDate().toString()+" "+getLink();
	}
	
	public int compareTo(Entry entry){
		int dateCompare = getDate().compareTo(entry.getDate());
		if(dateCompare != 0){
			return dateCompare;
		}
		return entry.getLink().compareTo(getLink());
	}
	
	
}

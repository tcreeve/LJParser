package objects;

public class EntryDate {
	private Year year;
	private Month month;
	private Day day;
	
	/**
	 * 
	 */
	public EntryDate() {
		this.year = new Year();
		this.day = new Day();
		this.month = new Month();
	}

	/**
	 * @param year
	 * @param day
	 * @param month
	 */
	public EntryDate(int month, int day, int year) {
		this.year = new Year(year);
		this.day = new Day(day);
		this.month = new Month(month);
	}
	
	public EntryDate(EntryDate date){
		this.year = new Year(date.getYear().getValue());
		this.month = new Month(date.getMonth().getValue());
		this.day = new Day(date.getDay().getValue());
	}

	/**
	 * @return the year
	 */
	public Year getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year.setValue(year); 
	}
	
	/**
	 * @param year the year to set
	 */
	public void setYear(String yearString) {
		this.year.setValue(yearString);
	}

	/**
	 * @return the day
	 */
	public Day getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day.setValue(day);
	}
	
	/**
	 * @param day the day to set
	 */
	public void setDay(String dayString) {
		this.day.setValue(dayString);
	}

	/**
	 * @return the month
	 */
	public Month getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month.setValue(month);
	}
	
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month){
		this.month.setValue(month);
	}
	
	public boolean isComplete(){
		if(getYear().isValid() && getMonth().isValid() && getDay().isValid()){
			return true;
		}
		return false;
	}
	
	public boolean isDateSame(EntryDate entryDate){
		return getYear().isValueSame(entryDate.getYear().getValue())
				&& getMonth().isValueSame(entryDate.getMonth().getValue())
				&& getDay().isValueSame(entryDate.getDay().getValue());
	}
	
	public String toString(){
		return getMonth().toString()+"/"+getDay().toString()+"/"+getYear().toString();
	}
	
	public int compareTo(EntryDate entryDate){
		if(!(entryDate.getYear().getValue() == getYear().getValue())){
			if(entryDate.getYear().getValue() == 0) {
				return 1;
			} else if(getYear().getValue() == 0) {
				return -1;
			}
		}
		
		int yearCompare = getYear().compareTo(entryDate.getYear());
		if(yearCompare != 0){
			return yearCompare;
		}
		
		int monthCompare = getMonth().compareTo(entryDate.getMonth());
		if(monthCompare != 0){
			return monthCompare;
		}
		
		return getDay().compareTo(entryDate.getDay());
	}
}

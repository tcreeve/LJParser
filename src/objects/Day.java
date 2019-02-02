package objects;

public class Day extends DateType{

	public Day() {
		setValue(0);
		setHeaderTag("h3");
	}
	
	public Day(int day){
		setValue(day);
		setHeaderTag("h3");
	}
	
	public Day(String day){
		setValue(day);
		setHeaderTag("h3");
	}
	
	public void setValue(String day){
		day = stripArchiveHTMLTag(day);
		day = eraseDayEndings(day);
		setValue(Integer.parseInt(day));
	}
	
	public String stripArchiveHTMLTag(String day){
		return stripOpeningAndClosingTags(day, getHeaderTag());
	}
	
	public String returnDayWithTag(){
		return writeWithSurroundingTags(addEndingToDay(getValue()), getHeaderTag());
	}
	
	public String eraseDayEndings(String day){
		return day.replace("st", "").replace("nd", "").replace("rd", "").replace("th", "");
	}
	
	public String addEndingToDay(int day){
		String dayString = Integer.toString(day);
		char lastCharacter = dayString.charAt(dayString.length() - 1);
		if(lastCharacter == '1' && !dayString.equals("11")){
			dayString += "st";
		} else if(lastCharacter == '2' && !dayString.equals("12")){
			dayString += "nd";
		} else if(lastCharacter == '3' && !dayString.equals("13")){
			dayString += "rd";
		} else {
			dayString += "th";
		}
		return dayString;
	}

}

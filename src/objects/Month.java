package objects;

import java.text.DateFormatSymbols;

public class Month extends DateType{
	DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();

	public Month() {
		setValue(0);
		setHeaderTag("h2");
	}
	
	public Month(int value){
		setValue(value);
		setHeaderTag("h2");
	}
	
	public Month(String month){
		setValue(month);
		setHeaderTag("h2");
	}
	
	public void setValue(String month){
		month = stripArchiveHTMLTag(month);
		month = returnMonthWithoutDot(month);
		setValue(returnNumberFromMonthString(month));
	}
	
	public String stripArchiveHTMLTag(String month){
		return stripOpeningAndClosingTags(month, "h2");
	}
	
	public String returnMonthWithoutDot(String month){
		return month.replace(".", "");
	}
	
	public String returnMonthWithTag(){
		String month = returnMonthStringFromNumber(getValue());
		month = formatMonthForArchive(month);
		return writeWithSurroundingTags(month, getHeaderTag());
	}
	
	public int returnNumberFromMonthString(String month){
		try{
			String[] months = dateFormatSymbols.getMonths();
			//get month number from where it is in array
			for(int i=0; i<months.length; i++){
				if(months[i].contains(month)){
					return i+1;
				}
			}
			return -1;
		} catch(Exception e){
			System.out.println(e.toString());
			return -1;
		}
	}
	
	public String returnMonthStringFromNumber(int number){
		try {
	    	number--;
	    	DateFormatSymbols dfs = new DateFormatSymbols();
	        String[] months = dfs.getMonths();
	        if (number >= 0 && number <= 11 ) {
	            return months[number];
	        }
	        return "";
    	} catch(Exception e){
    		//checkStatus.printDebuggingComments("Exception: isInteger - "+e.toString(), isDebug, entry);
    		return "";
    	}
	}
	
	public String formatMonthForArchive(String month){
		if(month.length() > 2) {
			if(!month.equals("May")) {
				month = month.substring(0, 3)+".";
			}
		}
		return month;
	}

}

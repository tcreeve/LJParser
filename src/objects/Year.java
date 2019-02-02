package objects;

public class Year extends DateType{

	public Year() {
		setValue(0);
		setHeaderTag("h1");
	}
	
	public Year(int value){
		setValue(value);
		setHeaderTag("h1");
	}
	
	public Year(String year){
		setValue(year);
		setHeaderTag("h1");
	}
	
	public void setValue(String year){
		year = stripArchiveHTMLTag(year);
		int value = Integer.parseInt(year);
		setValue(value);
	}
	
	public String stripArchiveHTMLTag(String year){
		return stripOpeningAndClosingTags(year, getHeaderTag());
	}
	
	public String returnYearWithTag(){
		return writeWithSurroundingTags(getValue(), getHeaderTag());
	}

}

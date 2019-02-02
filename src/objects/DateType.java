package objects;

public abstract class DateType {
	private int value;
	private String headerTag;
	
	public int getValue(){
		return value;
	}
	public void setValue(int value){
		this.value = value;
	}
	
	public String getHeaderTag(){
		return headerTag;
	}
	
	public void setHeaderTag(String headerTag){
		this.headerTag = headerTag;
	}
	
	public String stripOpeningAndClosingTags(String line, String symbol){
		return line.replace("<"+symbol+">", "").replace("</"+symbol+">", "");
	}
	
	public String writeWithSurroundingTags(String date, String symbol){
		return "<"+symbol+">"+date+"</"+symbol+">";
	}
	
	public String writeWithSurroundingTags(int date, String symbol){
		return "<"+symbol+">"+date+"</"+symbol+">";
	}
	
	public boolean isValueSame(int value){
		return (getValue() == value);
	}
	
	public boolean hasError(){
		if(value < 0){
			return true;
		}
		return false;
	}
	
	public boolean isEmptyValue(){
		if(value == 0){
			return true;
		}
		return false;
	}
	
	public boolean isValid(){
		if(!hasError() && !isEmptyValue()){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return Integer.toString(getValue());
	}
	
	public int compareTo(DateType dateType){
		if(dateType.getValue() > getValue()){
			return 1;
		} else if(dateType.getValue() < getValue()){
			return -1;
		}
		return 0;
	}
}


package variables;

public class ParsingInformation {
	private String layoutName = "";
	private boolean linkIsFirst = true;
	
	public ParsingInformation() {
		layoutName = "";
		linkIsFirst = true;
	}
	
	public ParsingInformation(String layoutName, boolean linkIsFirst) {
		this.layoutName = layoutName;
		this.linkIsFirst = linkIsFirst;
	}
	
	public String getLayoutName() {
		return layoutName;
	}
	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
	public boolean isLinkFirst() {
		return linkIsFirst;
	}
	public void setIsLinkFirst(boolean linkIsFirst) {
		this.linkIsFirst = linkIsFirst;
	}

	public String toString() {
		return getLayoutName();
	}
}

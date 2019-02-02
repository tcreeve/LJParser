package actions.archive;
import java.util.*;

import objects.Entry;
import objects.EntryDate;

import java.io.*;

public class ReadCurrentArchive {
	
	public static boolean isInteger(String check){
		try{
			Integer.parseInt(check);
			return true;
		} catch(Exception e){
			return false;
		}
	}
	
	public static List<Entry> getCurrentEntries(String file){
		EntryDate currentDate = new EntryDate();
		int lineNumber = 0;
		try{
			//set up for looping
			List<Entry> LJEntries = new ArrayList<Entry>();
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			
			//while line exists
			while((line = bufferedReader.readLine()) != null){
				lineNumber++;
				if(line.contains("<h1>")){
					//exception for no eway posts
					if(line.contains(("No Eway Posts"))){
						currentDate.setYear(0);
					} else {
						currentDate.setYear(line);
					}
					currentDate.setMonth(0);
					currentDate.setDay(0);
				} else if(line.contains("<h2>")){
					currentDate.setMonth(line);
					currentDate.setDay(0);
				} else if(line.contains("<h3>")){
					currentDate.setDay(line);
				} else if(line.contains("<a href") && lineNumber > 3){
					//set new entry with link
					LJEntries.add(new Entry(currentDate, line));
				}
			}
			bufferedReader.close();
			return LJEntries;
		} catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

}

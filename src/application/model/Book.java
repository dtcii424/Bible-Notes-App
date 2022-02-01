package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Book {
	private String name;
	
	public static ArrayList<String> ot;
	public static ArrayList<String> nt;
	public static ArrayList<String> alph;
	public static ArrayList<String> notesInBook;
	

	public Book(String name) {
		this.setName(name);
	}
	
	public static void loadOldTestament(String fileName) {
		ot = new ArrayList<String>();
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
		} catch(FileNotFoundException exception) {
			System.err.println("can't find " + fileName);
			System.exit(1);
		}
		while(in.hasNext()) {
			String line = in.nextLine();
			String [] params = line.split(" ", 2);
			
			ot.add(params[1]);
		}
		in.close();
	}
	
	public static void loadNewTestament(String fileName) {
		nt = new ArrayList<String>();
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
		} catch(FileNotFoundException exception) {
			System.err.println("can't find " + fileName);
			System.exit(1);
		}
		while(in.hasNext()) {
			String line = in.nextLine();
			String [] params = line.split(" ", 2);
		
			nt.add(params[1]);
		}
		in.close();
		loadAlpha();
	}
	
	public static void loadAlpha() {
		alph = new ArrayList<String>();
		alph.addAll(ot);
		alph.addAll(nt);
		Collections.sort(alph);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void loadNote(String fileName, String nameOfBook) {
		notesInBook = new ArrayList<String>();
		String book = "";
		Scanner in = null;
		String pattern = "\\d+:\\d+-\\d+$";		
		try {
			in = new Scanner(new File(fileName));
		} catch(FileNotFoundException exception) {
			System.err.println("can't find " + fileName);
			System.exit(1);
		}
		if(nameOfBook.equals("all")){
			while(in.hasNext()) {
				String currLine = in.nextLine();
				String[] oneWord = currLine.split(" ", 2);
				
				if(oneWord[0].equals("1") || oneWord[0].equals("2") || oneWord[0].equals("3")) {//if(currLine.startsWith("1") || currLine.startsWith("2") || currLine.startsWith("3")) {
					String[] twoWord = currLine.split(" ", 3);
					book = twoWord[0] + " " + twoWord[1];
					if(alph.contains(book) && twoWord[2].matches(pattern)) {
						notesInBook.add(currLine);
					}
				}
				else {
					book = oneWord[0];
					if(alph.contains(book) && oneWord[1].matches(pattern)) {
						notesInBook.add(currLine);
					}
				}
			}
		}
		else {
			while(in.hasNext()) {
				String currLine = in.nextLine();
				if(currLine.matches(nameOfBook + " " + pattern)) {
					notesInBook.add(currLine);
				}
			}
		}
		in.close();
		if(notesInBook.isEmpty()) {
			notesInBook.add("No Notes Found");
		}
	}
	
	public static int addNote(String fileName, String book, String chapter, String beginVerse, String endingVerse, String note) throws IOException {
		String toAdd = book + " " + chapter + ":" + beginVerse + "-" + endingVerse;
		if(findScripture("data/Notes.txt", toAdd) == -1) {
			return -1;
		}
		
		note = note.replace("\n", "|");
		FileWriter fstream = new FileWriter(fileName, true);
		BufferedWriter writer = new BufferedWriter(fstream);
		writer.write(book + " " + chapter + ":" + beginVerse + "-" + endingVerse + "\n" + note + "\n");
		writer.close();
		
		return 0;
	}
	
	public static int findScripture(String fileName, String check) {		
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
		} catch(FileNotFoundException exception) {
			System.err.println("can't find " + fileName);
			System.exit(1);
		}
		while(in.hasNext()) {
			if(in.nextLine().equals(check)) {
				return -1;
			}
		}
		in.close();
		
		return 0;
	}
	
	public static String getNote(String fileName, String scripture) {
		String retString = "";
		
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
		} catch(FileNotFoundException exception) {
			System.err.println("can't find " + fileName);
			System.exit(1);
		}
		while(in.hasNext()) {
			if(in.nextLine().equals(scripture)) {
				retString = in.nextLine();
			}
		}
		in.close();
		retString = retString.replace("|", "\n");
		
		return retString;
	}
	
	public static int saveNote(String fileName, String noteToUpdate, String newNote) throws IOException {
		newNote = newNote.replace("\n", "|");
		String scripture = ""; String note = "";
		String tempFile = "temp.txt";
		File oldFile = new File(fileName);
		File newFile = new File(tempFile);
		FileWriter fw = new FileWriter(tempFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		Scanner s = new Scanner(new File(fileName));
		int i = 0;
		while(s.hasNext()) {
			scripture = s.nextLine();
			note = s.nextLine();
			
			if (noteToUpdate.equals(scripture)){
				i = 1;
				pw.print(scripture + "\n" + newNote + "\n");
    		}
    		else {
    			pw.print(scripture + "\n" + note + "\n");
    		}
		}
		s.close();
		pw.flush();
		pw.close();
		oldFile.delete();
		File dump = new File(fileName);
		newFile.renameTo(dump);	
		return i;
	}
	
	public static int deleteNote(String fileName, String noteToDelete) throws IOException {
		String scripture = ""; String note = "";
		String tempFile = "temp.txt";
		File oldFile = new File(fileName);
		File newFile = new File(tempFile);
		FileWriter fw = new FileWriter(tempFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		Scanner s = new Scanner(new File(fileName));
		int i = 0;
		while(s.hasNext()) {
			scripture = s.nextLine();
			note = s.nextLine();
			
			if (noteToDelete.equals(scripture)){
				i = 1;
				continue;
    		}
    		else {
    			pw.print(scripture + "\n" + note);
    			if(s.hasNext() == true) { pw.print("\n"); }
    		}
			
		}
		s.close();
		pw.flush();
		pw.close();
		oldFile.delete();
		File dump = new File(fileName);
		newFile.renameTo(dump);	
		return i;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null || strNum.length() >= 3 || strNum.startsWith("0")) {
	        return false;
	    }
	    try {
	        @SuppressWarnings("unused")
			int i = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}

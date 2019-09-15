import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Main {
	
	String chosenColor;
	Frame chosenFrame;
	Date lieferdatum;
	ArrayList<Package> chosenPackage;
	static ArrayList<Frame> framesList;
	static ArrayList<Package> packagesList;
	ArrayList<Order> orders;
	Order order;
	static int lastOrderNumber = 0;
	
	static Localization german = new Localization();
	static FileReader readerFrames,readerPackages;
	static BufferedReader brFrames,brPackages;
	public static void main(String[] args) throws IOException {
		
		/* FileReader erstellen */		
		try {
			readerFrames = new FileReader("src/frames.csv");
		} catch (FileNotFoundException e) {
			System.out.print("No Configfile found. Please contact Administrator");
		}
		try {
			readerPackages = new FileReader("src/packages.csv");
		} catch (FileNotFoundException e) {
			System.out.print("No Configfile found. Please contact Administrator");
		}		
		
		BufferedReader brFrames = new BufferedReader(readerFrames);
		BufferedReader brPackages = new BufferedReader(readerPackages);
		/* Arraylists erstellen */
		ArrayList<Frame> framesList = new ArrayList<>(1);
		ArrayList<Package> packagesList = new ArrayList<>(1);
		
		/* Frames einlesen*/
		  String line  = brFrames.readLine();		
		  while (line != null)
		   {
			 String[] lineValues = line.split(";");
			 Frame someFrame = new Frame(lineValues[2], lineValues[1], lineValues[0]);
			 framesList.add(someFrame);
			 line  = brFrames.readLine();
		   } 
		/* Packages einlesen*/
		  line  = brPackages.readLine();		
		  while (line != null)
		   {
			 String[] lineValues = line.split(";");
			 Package somePackage = new Package(lineValues[2], lineValues[1], lineValues[0], lineValues[3]);
			 packagesList.add(somePackage);
			 line  = brPackages.readLine();
		   } 
		
		/* Start*/
		System.out.print(german.hello);
		System.out.print(german.space);
		System.out.print(german.choseFrame);
		System.out.print(german.space);
		/* Rahmen auflisten */
		for(Frame frame : framesList) {
			System.out.print(frame.getFrameName());
			System.out.print("        Lieferzeit: " + frame.getFramePreis() + " Tage");
			System.out.print("        Preis: " + frame.getFrameLieferZeit());
			System.out.print(german.space);
		}
		/* PAkete auflisten */
		for(Package packages : packagesList) {
			System.out.print(packages.getPackageName());
			System.out.print("        Lieferzeit: " + packages.getPackagePreis());
			System.out.print("        Preis: " + packages.getPackageLieferZeit());
			System.out.print(german.space);
		}

	}

}

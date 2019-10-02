import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
	
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	String chosenColor;
	static Frame chosenFrame;
	Date lieferdatum;
	static Package chosenPackage;
	static int frameNr,packageNr;
	static ArrayList<Frame> framesList;
	static ArrayList<Package> packagesList, chosenPackageList = new ArrayList<Package>(1);
	ArrayList<Order> orders;
	Order order;
	static boolean frameSure,packageSure,packageFull = false;
	static int lastOrderNumber = 0;
	static Scanner scan = new Scanner(System.in);
	
	static Localization german = new Localization();
	static FileReader readerFrames,readerPackages;
	static BufferedReader brFrames,brPackages;
	
	
	/* Main Methode */
	public static void main(String[] args) throws IOException {
		 String answer;
		
		/* FileReader erstellen */		
		try {
			readerFrames = new FileReader("frames.csv");
		} catch (FileNotFoundException e) {
			System.out.print("No Configfile found. Please contact Administrator");
		}
		try {
			readerPackages = new FileReader("packages.csv");
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
			 Frame someFrame = new Frame(lineValues[0], lineValues[1], lineValues[2]);
			 framesList.add(someFrame);
			 line  = brFrames.readLine();
		   } 
		/* Packages einlesen*/
		  line  = brPackages.readLine();		
		  while (line != null)
		   {
			 String[] lineValues = line.split(";");
			 Package somePackage = new Package(lineValues[0], lineValues[1], lineValues[2], lineValues[3]);
			 packagesList.add(somePackage);
			 line  = brPackages.readLine();
		   } 
		
		/* Start*/
		System.out.print(german.hello);
		System.out.print(german.breaks);

		/* Rahmen aussuchen */
		while(!frameSure) {
			System.out.print(german.choseFrame);
			System.out.print(german.breaks);
			
			/* Rahmen auflisten */
			giveFramesList(framesList);
			
			/* Rahmen waehlen */
			System.out.print(german.frameNr);
			frameNr = scan.nextInt();
			System.out.print(german.areYouSure);
			answer = scan.next();
			if (answer.equalsIgnoreCase("Ja") || answer.equalsIgnoreCase("J") || answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes") ){
				/* Gewaehlten Frame eintragen */
				chosenFrame = framesList.get(frameNr -1);
				frameSure = true;
				for(int i = 0; i<8; i++) {
					System.out.print(german.breaks);
				}
			}
			else {
				for(int i = 0; i<8; i++) {
					System.out.print(german.breaks);
				}
			}
			
		}
		/* Pakete waehlen */
		while(!packageSure) {
			/* zuvor gewaehlten Rahmen anzeigen*/
			System.out.print(german.pickedFrame + german.space + chosenFrame.getFrameName() + german.space + german.delTime + chosenFrame.getFrameLieferZeit() + german.days + german.space + german.price + chosenFrame.getFramePreis() + german.breaks);
			for(int i = 0; i<2; i++) {
				System.out.print(german.breaks);
			}			
			
			System.out.print(german.chosePackage);
			System.out.print(german.breaks);

			while(!packageFull) {
				/* PAkete auflisten */
				givePackagesList(packagesList);
				
				/* Paket waehlen */
				System.out.print(german.packageNr);
				packageNr = scan.nextInt();
				System.out.print(german.areYouSure);
				answer = scan.next();
				if (answer.equalsIgnoreCase("Ja") || answer.equalsIgnoreCase("J") || answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes") ){
					/* Gewaehltes Paket eintragen */
					chosenPackageList.add(packagesList.get(packageNr -1));
					/* Gewaehltes Paket aus temporaerer Liste nehmen */
					packagesList.remove(packageNr - 1);
					packageSure = true;
					for(int i = 0; i<8; i++) {System.out.print(german.breaks);}
				}
				else {
					for(int i = 0; i<8; i++) {System.out.print(german.breaks);}
				}
				/* Weiteres Paket Waehlen */
				System.out.print(german.anotherPackage);
				answer = scan.next();
				System.out.print(german.breaks);
				if (answer.equalsIgnoreCase("Ja") || answer.equalsIgnoreCase("J") || answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes") ){
					/* repeat loop */					
				}
				else {
					packageFull = true;
				}				
			}
			for(int i = 0; i<8; i++) {System.out.print(german.breaks);}
			System.out.print(german.pickedFrame + german.breaks + german.space + chosenFrame.getFrameName() + german.space + german.delTime + chosenFrame.getFrameLieferZeit() + german.days + german.space + german.price + chosenFrame.getFramePreis() + german.breaks);

			System.out.print(german.breaks);
			System.out.print(german.pickedPackage + "\n \n");
			for(Package package2 : chosenPackageList) {				
				System.out.print(german.space + package2.getPackageName() + german.space + german.price + package2.getPackagePreis() + german.space + german.delTime + package2.getPackageLieferZeit() + german.breaks);

			}			
			statusZeile(chosenFrame,chosenPackageList);
		}
	}
	
	/* Methoden */
	public static void giveFramesList(ArrayList<Frame> framesList) {
		int counter = 1;
		for(Frame frame : framesList) {
			System.out.print("(" + counter + ")" + german.space + frame.getFrameName() + german.space + german.delTime + frame.getFrameLieferZeit() + german.days + german.space + german.price + frame.getFramePreis() + german.breaks);
			counter ++;
		}
	}
	public static void givePackagesList(ArrayList<Package> packagesList) {
		int counter = 1;
		for(Package packages : packagesList) {
			System.out.print("(" + counter + ")"+ german.space + packages.getPackageName() + german.space + packages.getPackageItems() + german.space + german.delTime + packages.getPackageLieferZeit() + german.space + german.price + packages.getPackagePreis() + german.breaks );
			counter ++;
		}		
	}
	/* Preis & Lieferzeit berechnen */
	public static void statusZeile(Frame chosenFrame2,ArrayList<Package> packagesList2) {
		int gesamtPreis = Integer.parseInt(chosenFrame2.getFramePreis());
		int lieferTage = Integer.parseInt(chosenFrame2.getFrameLieferZeit());
		Date lieferDatum = new Date();
		
		/* Kalender mit heutigem Datum */
		Calendar c = Calendar.getInstance();
	    c.setTime(lieferDatum);		
		
	    /* Paket-Preis und Zeit Addieren*/
		for(Package packages : packagesList2) {
			gesamtPreis = gesamtPreis + Integer.parseInt(packages.getPackagePreis());
			lieferTage = lieferTage +  Integer.parseInt(packages.getPackageLieferZeit());
		}
		 c.add(Calendar.DATE, lieferTage);
		lieferDatum = c.getTime();
		System.out.println("Gesamtpreis: " + gesamtPreis +  "€."  + german.space + " Vorraussichtliche Lieferung: " + dateFormat.format(lieferDatum));
	}

}
package driveg;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class DriveQuickStart
{
			
	private static GoogleDriveService driveService = new GoogleDriveServiceImpl();
	private static Drive service = null;
	
	private static void printMenu() 
	{
		System.out.println("1. Elenco files/cartelle");
		System.out.println("2. Sincronizza cartella");
		System.out.println("9. Esci");
	}
	

	private static void stampaElenco(File file) throws IOException
	{	
		GoogleDriveSubfolders googleDriveSubfolders = new GoogleDriveSubfoldersImpl(service);	
		
		List<File> files = googleDriveSubfolders.getGoogleDriveSubFolders(file);
		
		if (files != null && !(files.isEmpty()))
		{
			files.stream().forEach(f -> {
				
				System.out.printf("Id: %s\n", f.getId());
				System.out.printf("Nome: %s\n", f.getName());
				Stream.generate(() -> '-' )
					.limit(20)
					.forEach( c -> System.out.print(c));
				System.out.print('\n');
				
				try 
				{
					stampaElenco(f); 
				} 
				catch (IOException ioe) 
				{
					ioe.printStackTrace();
				}
					
			});
		}		
	}
	
	private static void sincronizza(String cartella) 
	{
		
	}
		
	public static void main(String[] args) throws IOException, GeneralSecurityException
	{	
		service = driveService.getDrive();

		int scelta;
		
		Scanner scan = new Scanner(System.in);
	
		while (true)
		{
			printMenu();
			scelta = scan.nextInt();			
			
			
			switch (scelta)
			{
				case 1:
					stampaElenco(null);
					break;
				case 2:
					String cartella;
					System.out.println("Percorso assoluto cartella da sincronizzare:");
					cartella = scan.nextLine();
					sincronizza(cartella);
					break;
				case 9:
					return;
				default:
					System.out.println("Scelta errata");
			
			}	
		
		}	
	}
	
}

package driveg;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Scanner;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class DriveQuickStart
{
		
	private static GoogleDriveService driveService = new GoogleDriveServiceImpl();
	
	private static void printMenu() 
	{
		System.out.println("1. Elenco files/cartelle");
		System.out.println("9. Esci");
	}
	
	private static void stampaElenco(Drive service) throws IOException
	{
		FileList result = service
				.files()
				.list()
				.setPageSize(10)
				.setFields("nextPageToken, files(id, name)")
				.execute();
		
		List<File> files = result.getFiles();
		if (files == null || files.isEmpty())
			System.out.println("Non ci sono files");
		else
		{
			System.out.println("Files:");
			files.stream().forEach(file -> {
				System.out.printf("Id: %s\t Nome: %s\n", file.getId(), file.getName());				
			});
		}		
	}
		
	public static void main(String[] args) throws IOException, GeneralSecurityException
	{	
		Drive service = driveService.getDrive();

		int scelta;
		
		Scanner scan = new Scanner(System.in);
	
		while (true)
		{
			printMenu();
			scelta = scan.nextInt();			
			
			switch (scelta)
			{
				case 1:
					stampaElenco(service);
					break;
				case 9:
					return;
				default:
					System.out.println("Scelta errata");
			
			}	
		
		}	
	}
	
}

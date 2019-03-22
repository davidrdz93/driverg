package driveg;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class DriveQuickStart
{
		
	private static GoogleDriveService driveService = new GoogleDriveServiceImpl();
		
	public static void main(String[] args) throws IOException, GeneralSecurityException
	{	
		Drive service = driveService.getDrive();
		
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
	
}

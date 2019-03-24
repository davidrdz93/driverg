package driveg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class GoogleDriveSubfoldersImpl implements GoogleDriveSubfolders
{
	private static final String FOLDER_MIME_TYPE = "application/vnd.google-apps.folder";
	private Drive drive = null;
	
	public GoogleDriveSubfoldersImpl(Drive drive) 
	{
		this.drive = drive;
	}
	
	@Override
	public List<File> getGoogleDriveSubFolders(File file) throws IOException
	{		
		List<File> list = new ArrayList<>();
		
		String query = null;
		
		if (file != null)
		{
			if (!file.getMimeType().equals(FOLDER_MIME_TYPE)) // file
				return list;
			System.out.printf("Controllo la cartella %s\n", file.getName());
			query = String.format("'%s' in parents", file.getId());
		}
		else
			// in assenza di file sono nella root directory
			query = "'root' in parents";
		
		String pageToken = null;
		
		do 
		{
			FileList fileList = this.drive.files()
					.list()
					.setQ(query)
					.setSpaces("drive")
					//.setFields("nextPageToken, files(id, name, mimeType)")
					.setPageToken(pageToken)
					.execute();
			
			fileList.getFiles().stream().forEach( f -> list.add(f) );
		}
		while (pageToken != null);
		
		return list;
	}
}

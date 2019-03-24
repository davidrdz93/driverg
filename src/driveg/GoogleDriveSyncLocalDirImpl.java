package driveg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class GoogleDriveSyncLocalDirImpl implements GoogleDriveSyncLocalDir
{
	Drive drive = null;
	
	public GoogleDriveSyncLocalDirImpl(Drive drive)
	{
		this.drive = drive;
	}
	
	private List<java.io.File> filesOnDir(String dir) throws FileNotFoundException
	{
		List<java.io.File> files = new ArrayList<>();
		java.io.File folder = new java.io.File(dir);
		
		if(!folder.exists())
			throw new FileNotFoundException("La cartella " + dir + " non esiste!");
		
		if (folder.isDirectory())
			folder.listFiles( file -> files.add(file));
		
		
		return files;
	}
	
	private List<File> filesOnDrive(File file) throws IOException
	{
		GoogleDriveSubfolders gd = new GoogleDriveSubfoldersImpl(drive);
		return gd.getGoogleDriveSubFolders(file);
	}
	
	private boolean isSameFile(java.io.File sysFile, File driveFile)
	{
		if (sysFile.getName().equals(driveFile.getName()))
			return true;
		return false;
	}
	
	@Override
	public void doSync(String folderName) throws IOException
	{
		try 
		{
			GoogleDriveFileManager fileManager = new GoogleDriveFileManagerImpl(drive);
			List<java.io.File> localFiles = filesOnDir(folderName);
			
			if (localFiles.size() == 0) 
			{
				System.out.println("Nessun file trovato nella cartella");
				return;
			}
			
			List<File> driveFiles = filesOnDrive(null);
			
			localFiles.forEach( localFile -> {
				
				boolean upload = driveFiles.stream().anyMatch( driveFile -> 
					isSameFile(localFile, driveFile));
				
				if (upload)
					try {
						fileManager.uploadFile(localFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			});
						
		}
		catch (FileNotFoundException f)
		{
			f.printStackTrace();
		}
		

	}
}

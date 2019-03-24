package driveg;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class GoogleDriveFileManagerImpl implements GoogleDriveFileManager
{
	Drive drive = null;
	
	public GoogleDriveFileManagerImpl(Drive drive)
	{
		this.drive = drive;
	}

	@Override
	public void downloadFile(File file, String path) throws IOException
	{
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		String fileName = String.format("%s/%s", path, file.getName());
		java.io.File newFile = new java.io.File(fileName);
		FileOutputStream fileOut = new FileOutputStream(newFile);
		
		if (!newFile.exists())
			newFile.createNewFile();
				
		drive
		.files()
		.get(file.getId())
		.executeMediaAndDownloadTo(byteArrayOut);
		
		if (byteArrayOut.size() > 0)
			fileOut.write(byteArrayOut.toByteArray());
		
		fileOut.flush();
		fileOut.close();	
	}

	@Override
	public void uploadFile(java.io.File file) throws IOException
	{
		File newFile = new File();
		newFile.setName(file.getName());
		String mimeType = Files.probeContentType(file.toPath());
		FileContent content = new FileContent(mimeType, file);
		
		File createdFile = drive
				.files()
				.create(newFile, content)
				.setFields("id")
				.execute();
		System.out.println("Upload con id " + createdFile.getId());
	}
	
}

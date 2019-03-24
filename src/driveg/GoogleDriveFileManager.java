package driveg;

import java.io.IOException;

import com.google.api.services.drive.model.File;

public interface GoogleDriveFileManager 
{
	/**
	 * Scarica il file nella cartella path
	 * il nome dato al file e' lo stesso di drive
	 * 
	 * @author David Rodriguez
	 * @param file
	 */
	void downloadFile(File file, String path) throws IOException;
	
	/**
	 * Carica il file su drive
	 * 
	 * @author David Rodriguez
	 */
	void uploadFile(java.io.File file) throws IOException;
	
}

package driveg;

import java.io.IOException;
import java.util.List;

import com.google.api.services.drive.model.File;

public interface GoogleDriveSubfolders 
{
	/**
	 * @author David Rodriguez
	 * @param file Se null si cerca nella directory root
	 * @return elenco delle istanze di File trovate dentro file
	 * @throws IOException
	 */
	public List<File> getGoogleDriveSubFolders(File file) throws IOException;
}

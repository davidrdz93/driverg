package driveg;

import java.io.IOException;

public interface GoogleDriveSyncLocalDir 
{
	/**
	 * sincronizza la directory folderName con una directory dello stesso nome su drive
	 * se la cartella non esiste su drive non succede nulla
	 * se un file è presente su filesystem e non su drive questo viene caricato
	 * se un file non è presente su filesystem e si su drive questo viene eliminato dal drive 
	 * 
	 * @author David Rodriguez
	 * @param folderName
	 */
	void doSync(String folderName) throws IOException;
}

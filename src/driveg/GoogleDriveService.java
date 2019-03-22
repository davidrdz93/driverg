package driveg;

import java.io.IOException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.Drive;

public interface GoogleDriveService 
{
	/**
	 * @author David Rodriguez
	 * @return istanza di Credential
	 * @throws IOException
	 */
	Credential getCredentials() throws IOException;
	
	/**
	 * @author David Rodriguez
	 * @return istanza di Drive
	 */
	Drive getDrive() throws IOException;
}

package aalto.ELECC8204.koodigenerointi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.opcfoundation.ua.common.ServiceResultException;

import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.AddressSpaceException;

import aalto.ELECC8204.opcua.OPCUAClient;


public class KoodigenerointiClient extends OPCUAClient {
	public static String APP_NAME = "KoodigenerointiClient";
	public static String SERVER_ENDPOINT = "opc.tcp://localhost:52521/OPCUA/KoodigenerointiServer";
	private static final Logger logger = LogManager.getLogger(KoodigenerointiClient.class);

	public KoodigenerointiClient(String appName, String serverEndpoint) throws UnknownHostException, StatusException, ServiceException, URISyntaxException, SecureIdentityException, IOException, ServiceResultException, AddressSpaceException {
		super(appName, serverEndpoint);
		/**
		 * TODO Register generated model here
		 */
	}	
	
	public void print() throws ServiceException, AddressSpaceException {
		/**
		 * TODO Print the value of the hello object variable
		 */
	}
	
	public static void main(String[] args) throws UnknownHostException, StatusException, ServiceException, URISyntaxException, SecureIdentityException, IOException, ServiceResultException, AddressSpaceException {
		logger.info("Starting KoodigenerointiClient");
		KoodigenerointiClient koodigenerointiClient = new KoodigenerointiClient(APP_NAME, SERVER_ENDPOINT);
		koodigenerointiClient.start();
		koodigenerointiClient.print();
		koodigenerointiClient.stop();
	}
}
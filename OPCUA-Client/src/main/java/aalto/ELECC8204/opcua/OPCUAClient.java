package aalto.ELECC8204.opcua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.transport.security.SecurityMode;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.AddressSpaceException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.nodes.UaInstance;
import com.prosysopc.ua.nodes.UaNode;
import com.prosysopc.ua.nodes.UaReference;

/**
 * A very minimal client application. Connects to the server and reads/writes one
 * variable. Works with a non-secure connection.
 */
public class OPCUAClient {
	protected UaClient client;
	public static String APP_NAME = "OPCUAClient";
	public static String SERVER_ENDPOINT = "opc.tcp://localhost:52520/OPCUA/OPCUAServer";
	
	private static final Logger logger = LogManager.getLogger(OPCUAClient.class);

	public OPCUAClient(String appName, String serverEndpoint) throws URISyntaxException, UnknownHostException, StatusException, ServiceException, SecureIdentityException, IOException, ServiceResultException, AddressSpaceException {
		client = new UaClient(serverEndpoint);
		initialize(appName);
		start();
	}
	
	public static void main(String[] args) throws UnknownHostException, StatusException, ServiceException, AddressSpaceException, SecureIdentityException, IOException, URISyntaxException, ServiceResultException {
		OPCUAClient opcuaClient = new OPCUAClient(APP_NAME, SERVER_ENDPOINT);
		opcuaClient.start();
		do {
			System.out.println("Commands: read, write, quit");
			String action;
			try {
				action = OPCUAClient.readInput();
				if (action.equals("quit")) {
					opcuaClient.stop();
					break;
				} else {
					NodeId nodeId = OPCUAClient.readNodeId();
					if (nodeId == null) {
						try {
							nodeId = opcuaClient.findNodeId();
						} catch (Exception e) {
							logger.error("Failed to findNodeId");
							logger.error(e.getMessage());
						}
					}
					if (action.equals("read")) {
						try {
							opcuaClient.read(nodeId);
						} catch (Exception e) {
							logger.error("Failed to read node");
							logger.error(e.getMessage());
						}
					} else if (action.equals("write")) {
						System.out.println("Enter new value: ");
						String value = OPCUAClient.readInput();
						try {
							opcuaClient.write(nodeId, value);
						} catch (Exception e) {
							logger.error("Failed to write node");
							logger.error(e.getMessage());
						}
					}
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		} while (true);
	}

	protected static String readInput() throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		do {
			s = stdin.readLine();
		} while ((s == null) || (s.length() == 0));
		return s;
	}

	protected static NodeId readNodeId() throws NumberFormatException, IOException {
		System.out.println("Do you know the nodeId? (yes/no)");
		String answer = OPCUAClient.readInput();
		if (answer.equals("yes")) {
			System.out.println("Enter NameSpaceIndex:");
			int index = Integer.parseInt(OPCUAClient.readInput());
			System.out.println("Enter NodeId value:");
			int value = Integer.parseInt(OPCUAClient.readInput());
			return new NodeId(index, value);
		}
		else if (answer.equals("no")) {
			return null;
		}
		else return OPCUAClient.readNodeId();
	}
	
	/**
	 * Define a minimal ApplicationIdentity. If you use secure connections, you will
	 * also need to define the application instance certificate and manage server
	 * certificates. See the SampleConsoleClient.initialize() for a full example of
	 * that.
	 * 
	 * @throws ServiceException
	 * @throws StatusException
	 * @throws ServiceResultException
	 */
	protected void initialize(String appName) throws SecureIdentityException, IOException, UnknownHostException, URISyntaxException,
	StatusException, ServiceException, ServiceResultException {
		client.setSecurityMode(SecurityMode.NONE);
		// *** Application Description is sent to the server
		ApplicationDescription appDescription = new ApplicationDescription();
		appDescription.setApplicationName(new LocalizedText(appName, Locale.ENGLISH));
		// 'localhost' (all lower case) in the URI is converted to the actual
		// host name of the computer in which the application is run
		appDescription.setApplicationUri("urn:localhost:UA:Client");
		appDescription.setProductUri("urn:prosysopc.com:UA:Client");
		appDescription.setApplicationType(ApplicationType.Client);

		final ApplicationIdentity identity = new ApplicationIdentity();
		identity.setApplicationDescription(appDescription);
		client.setApplicationIdentity(identity);
	}

	/**
	 * Initializes and connects the client to the server.
	 * opc.tcp://localhost:52520/OPCUA/Server
	 * 
	 * @throws UnknownHostException
	 * @throws StatusException
	 * @throws ServiceException
	 * @throws SecureIdentityException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ServiceResultException
	 * @throws AddressSpaceException
	 */
	public void start() throws UnknownHostException, StatusException, ServiceException, SecureIdentityException,
	IOException, URISyntaxException, ServiceResultException, AddressSpaceException {
		this.client.connect();
	}

	/**
	 * Disconnects the client from the server.
	 */
	public void stop() {
		this.client.disconnect();
	}

	protected void read(NodeId nodeId) {
		/**
		 * TODO Write your code here
		 */
	}

	protected void write(NodeId nodeId, String value) {
		/**
		 * TODO Write your code here
		 */
	}

	protected NodeId findNodeId() {
		/**
		 * TODO Write your code here
		 */
		return null;
	}
}
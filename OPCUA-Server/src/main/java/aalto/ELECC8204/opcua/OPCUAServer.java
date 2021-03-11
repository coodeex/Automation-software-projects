package aalto.ELECC8204.opcua;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.cert.DefaultCertificateValidator;
import org.opcfoundation.ua.cert.PkiDirectoryCertificateStore;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.UserTokenPolicy;
import org.opcfoundation.ua.transport.security.HttpsSecurityPolicy;
import org.opcfoundation.ua.transport.security.KeyPair;
import org.opcfoundation.ua.transport.security.SecurityMode;
import org.opcfoundation.ua.utils.EndpointUtil;
import org.xml.sax.SAXException;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.ModelException;
import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.UaApplication.Protocol;
import com.prosysopc.ua.nodes.UaObject;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.UaServer;
import com.prosysopc.ua.server.UaServerException;
import com.prosysopc.ua.types.opcua.BaseDataVariableType;
import com.prosysopc.ua.types.opcua.BaseObjectType;

public class OPCUAServer {
	public static int TCP_PORT = 52520;
	public static int HTTPS_PORT = 52443;
	public static String APP_NAME = "OPCUAServer";
	public static String discoveryServerUrl = "opc.tcp://localhost:4840";
	protected UaServer server;
	private static final Logger logger = LogManager.getLogger(OPCUAServer.class);


	public OPCUAServer(int tcpPort, int httpsPort, String appName) throws SecureIdentityException, IOException, UaServerException, StatusException, ServiceException, SAXException, ModelException, URISyntaxException {
		initialize(tcpPort, httpsPort, appName);
	}

	protected void createAddressSpace() throws SAXException, IOException, ModelException, URISyntaxException, StatusException, ServiceException {
		/** 
		 * TODO TIEDON LISÄÄMINEN OSOITEAVARUUTEEN
		 * 
		 * Kaikki OPC UA Server tehtävässä kirjoitettava koodi
		 * tulee tähän metodiin.
		 */
		NodeManagerUaNode nodeManager = new NodeManagerUaNode(server,"http://www.aalto.com/OPCUA/HelloAddressSpace");
	
		int index = nodeManager.getNamespaceIndex();
		NodeId objectId = new NodeId(index, 1);
		BaseObjectType object = nodeManager.createInstance(BaseObjectType.class, "Object", objectId);
		UaObject objectsFolder = server.getNodeManagerRoot().getObjectsFolder();
		nodeManager.addNodeAndReference(objectsFolder, object, Identifiers.Organizes);
		
	
		NodeId MuuttujaId = new NodeId(index, 2);
		
		
		BaseDataVariableType variable = nodeManager.createInstance(BaseDataVariableType.class, "Variable", MuuttujaId);
		variable.setDataTypeId(Identifiers.String);
		variable.setValue("Hello OPC UA");
		nodeManager.addNodeAndReference(object, variable, Identifiers.HasComponent);
	}
	
	private void initialize(int port, int httpsPort, String applicationName)
			throws SecureIdentityException, IOException, UaServerException {

		// *** Create the server
		server = new UaServer();

		// Use PKI files to keep track of the trusted and rejected client
		// certificates...

		final PkiDirectoryCertificateStore certificateStore = new PkiDirectoryCertificateStore();
		final DefaultCertificateValidator validator = new DefaultCertificateValidator(certificateStore);
		server.setCertificateValidator(validator);

		// *** Application Description is sent to the clients
		ApplicationDescription appDescription = new ApplicationDescription();
		// 'localhost' (all lower case) in the ApplicationName and
		// ApplicationURI is converted to the actual host name of the computer
		// (including the possible domain part) in which the application is run.
		// (as available from ApplicationIdentity.getActualHostName())
		// 'hostname' is converted to the host name without the domain part.
		// (as available from
		// ApplicationIdentity.getActualHostNameWithoutDomain())
		appDescription.setApplicationName(new LocalizedText(applicationName + "@hostname"));
		appDescription.setApplicationUri("urn:hostname:OPCUA:" + applicationName);
		appDescription.setProductUri("urn:prosysopc.com:OPCUA:" + applicationName);
		appDescription.setApplicationType(ApplicationType.Server);

		// *** Server Endpoints
		// TCP Port number for the UA Binary protocol
		server.setPort(Protocol.OpcTcp, port);
		// TCP Port for the HTTPS protocol
		server.setPort(Protocol.Https, httpsPort);

		// optional server name part of the URI (default for all protocols)
		server.setServerName("OPCUA/" + applicationName);

		// Optionally restrict the InetAddresses to which the server is bound.
		// You may also specify the addresses for each Protocol.
		// This is the default (isEnableIPv6 defines whether IPv6 address should
		// be included in the bound addresses. Note that it requires Java 7 or
		// later to work in practice in Windows):

		// Uncomment to enable IPv6 (NOTE! does not work on Java 6 on Windows)
		// server.setEnableIPv6(true);
		server.setBindAddresses(EndpointUtil.getInetAddresses(server.isEnableIPv6()));

		// *** Certificates

		File privatePath = new File(certificateStore.getBaseDir(), "private");

		// Define a certificate for a Certificate Authority (CA) which is used
		// to issue the keys. Especially
		// the HTTPS certificate should be signed by a CA certificate, in order
		// to make the .NET applications trust it.
		//
		// If you have a real CA, you should use that instead of this sample CA
		// and create the keys with it.
		// Here we use the IssuerCertificate only to sign the HTTPS certificate
		// (below) and not the Application Instance Certificate.
		KeyPair issuerCertificate = ApplicationIdentity.loadOrCreateIssuerCertificate("ProsysSampleCA", privatePath,
				"opcua", 3650, false);

		// If you wish to use big certificates (4096 bits), you will need to
		// define two certificates for your application, since to interoperate
		// with old applications, you will also need to use a small certificate
		// (up to 2048 bits).

		// Also, 4096 bits can only be used with Basic256Sha256 security
		// profile, which is currently not enabled by default, so we will also
		// leave the the keySizes array as null. In that case, the default key
		// size defined by CertificateUtils.getKeySize() is used.
		int[] keySizes = null;

		// Use 0 to use the default keySize and default file names as before
		// (for other values the file names will include the key size).
		// keySizes = new int[] { 0, 4096 };

		// *** Application Identity

		// Define the Server application identity, including the Application
		// Instance Certificate (but don't sign it with the issuerCertificate as
		// explained above).
		final ApplicationIdentity identity = ApplicationIdentity.loadOrCreateCertificate(appDescription,
				"Sample Organisation", /* Private Key Password */"opcua", /* Key File Path */privatePath,
				/* Issuer Certificate & Private Key */null, /* Key Sizes for instance certificates to create */keySizes,
				/* Enable renewing the certificate */true);

		// Create the HTTPS certificate bound to the hostname.
		// The HTTPS certificate must be created, if you enable HTTPS.
		String hostName = ApplicationIdentity.getActualHostName();
		identity.setHttpsCertificate(ApplicationIdentity.loadOrCreateHttpsCertificate(appDescription, hostName, "opcua",
				issuerCertificate, privatePath, true));

		server.setApplicationIdentity(identity);

		// *** Security settings
		// Define the security modes to support for the Binary protocol -
		// ALL is the default
		server.setSecurityModes(SecurityMode.ALL);
		// The TLS security policies to use for HTTPS
		server.getHttpsSettings().setHttpsSecurityPolicies(HttpsSecurityPolicy.ALL);

		// Number of threads to reserve for the HTTPS server, default is 10
		// server.setHttpsWorkerThreadCount(10);

		// Define the certificate validator for the HTTPS certificates;
		// we use the same validator that we use for Application Instance Certificates
		server.getHttpsSettings().setCertificateValidator(validator);

		// Define the supported user Token policies
		server.addUserTokenPolicy(UserTokenPolicy.ANONYMOUS);
		server.addUserTokenPolicy(UserTokenPolicy.SECURE_USERNAME_PASSWORD);
		server.addUserTokenPolicy(UserTokenPolicy.SECURE_CERTIFICATE);

		// Register to the local discovery server (if present)
		try {
			server.setDiscoveryServerUrl(discoveryServerUrl);
		} catch (URISyntaxException e) {
			System.out.println("DiscoveryURL is not valid");
		}

		// *** 'init' creates the service handlers and the default endpoints
		// *** according to the settings defined above
		server.init();

		// "Safety limits" for ill-behaving clients
		server.getSessionManager().setMaxSessionCount(500);
		server.getSessionManager().setMaxSessionTimeout(3600000); // one hour
		server.getSubscriptionManager().setMaxSubscriptionCount(50);

		// You can do your own additions to server initializations here

	}

	/**
	 * Run the server.
	 */
	public void run() throws UaServerException, StatusException {
		server.start();
		String[] endpointUris = server.getServerUris();
		logger.info("Server is running and listening following endpoints:");
		logger.info(Arrays.toString(endpointUris));
	}
	
	public static void main(String[] args) throws Exception {
		OPCUAServer opcuaServer = new OPCUAServer(TCP_PORT, HTTPS_PORT, APP_NAME);
		opcuaServer.createAddressSpace();
		opcuaServer.run();
	}
}
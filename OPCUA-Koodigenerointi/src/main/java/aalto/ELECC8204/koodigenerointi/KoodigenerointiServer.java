package aalto.ELECC8204.koodigenerointi;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.prosysopc.ua.ModelException;
import com.prosysopc.ua.SecureIdentityException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.server.UaServerException;

import aalto.ELECC8204.opcua.OPCUAServer;

public class KoodigenerointiServer extends OPCUAServer {
	public static int TCP_PORT = 52521;
	public static int HTTPS_PORT = 52444;
	public static String APP_NAME = "KoodigenerointiServer";
	private static final Logger logger = LogManager.getLogger(KoodigenerointiServer.class);
	
	public KoodigenerointiServer(int tcpPort, int httpsPort, String appName) throws SecureIdentityException, IOException, UaServerException, StatusException,
			ServiceException, SAXException, ModelException, URISyntaxException {
		super(tcpPort, httpsPort, appName);
		/**
		 * TODO Register the generated model and load it to the address space
		 */
	}
	
	@Override
	protected void createAddressSpace() {
		/**
		 * TODO
		 */
	}
	
	public static void main(String[] args) throws Exception {
		logger.info("Starting KoodigenerointiServer");
		KoodigenerointiServer server = new KoodigenerointiServer(TCP_PORT, HTTPS_PORT, APP_NAME);
		server.run();
	}

}

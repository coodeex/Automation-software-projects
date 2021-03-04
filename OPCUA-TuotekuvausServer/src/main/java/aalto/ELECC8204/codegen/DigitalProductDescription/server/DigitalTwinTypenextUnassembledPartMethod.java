package aalto.ELECC8204.codegen.DigitalProductDescription.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.server.ServiceContext;
import org.opcfoundation.ua.builtintypes.NodeId;

public abstract interface DigitalTwinTypenextUnassembledPartMethod {
  NodeId nextUnassembledPart(ServiceContext serviceContext, DigitalTwinTypeNode node) throws
      StatusException;
}

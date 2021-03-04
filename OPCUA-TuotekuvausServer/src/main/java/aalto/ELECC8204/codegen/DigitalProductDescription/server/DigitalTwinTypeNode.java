package aalto.ELECC8204.codegen.DigitalProductDescription.server;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import java.lang.Override;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.core.StatusCodes;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=101")
public class DigitalTwinTypeNode extends DigitalTwinTypeNodeBase {
  protected DigitalTwinTypeNode(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    // Use this method to initialize the nodes, when they are all created.
    // Note that 'super.afterCreate()' performs default initializations, so consider
    // whether your own initializations should be done before or after it.
    super.afterCreate();
  }

  @Override
  protected NodeId onnextUnassembledPart(ServiceContext serviceContext) throws StatusException {
    //Implement the generated method here (and remove the code below) OR set implementation via static method setNextUnassembledPartMethodImplementation 
    throw new StatusException(StatusCodes.Bad_NotImplemented);
  }
}

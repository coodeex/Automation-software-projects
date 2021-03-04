package aalto.ELECC8204.codegen.DigitalProductDescription.server;

import aalto.ELECC8204.codegen.DigitalProductDescription.DigitalTwinType;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaMethod;
import com.prosysopc.ua.server.GeneratedNodeInitializer;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.types.opcua.server.BaseObjectTypeNode;
import com.prosysopc.ua.types.opcua.server.FolderTypeNode;
import java.lang.Override;
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.Variant;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=101")
public abstract class DigitalTwinTypeNodeBase extends BaseObjectTypeNode implements DigitalTwinType {
  private static GeneratedNodeInitializer<DigitalTwinTypeNode> digitalTwinTypeNodeInitializer;

  private static DigitalTwinTypenextUnassembledPartMethod nextUnassembledPartMethodImplementation;

  protected DigitalTwinTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getDigitalPartsNode());
    GeneratedNodeInitializer<DigitalTwinTypeNode> impl = getDigitalTwinTypeNodeInitializer();
    if(impl != null) {
      impl.init((DigitalTwinTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<DigitalTwinTypeNode> getDigitalTwinTypeNodeInitializer() {
    return digitalTwinTypeNodeInitializer;
  }

  public static void setDigitalTwinTypeNodeInitializer(GeneratedNodeInitializer<DigitalTwinTypeNode> digitalTwinTypeNodeInitializerNewValue) {
    digitalTwinTypeNodeInitializer=digitalTwinTypeNodeInitializerNewValue;
  }

  @Mandatory
  @Override
  public FolderTypeNode getDigitalPartsNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "DigitalParts");
    return (FolderTypeNode) getComponent(browseName);
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    if (isComponentMatch(getQualifiedName("http://opcfoundation.org/UA/", "nextUnassembledPart"), methodId)) {
      return new Variant[]{new Variant(donextUnassembledPart(serviceContext))};
    }
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }

  @Mandatory
  @Override
  public UaMethod getnextUnassembledPartNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "nextUnassembledPart");
    return (UaMethod) getComponent(browseName);
  }

  protected abstract NodeId onnextUnassembledPart(ServiceContext serviceContext) throws
      StatusException;

  @Override
  public NodeId nextUnassembledPart() throws StatusException {
    return donextUnassembledPart(ServiceContext.INTERNAL_OPERATION_CONTEXT);
  }

  private NodeId donextUnassembledPart(ServiceContext serviceContext) throws StatusException {
    DigitalTwinTypenextUnassembledPartMethod impl = getNextUnassembledPartMethodImplementation();
    if(impl != null) {
      return impl.nextUnassembledPart(serviceContext, (DigitalTwinTypeNode)this);
    } else {
      return onnextUnassembledPart(serviceContext);
    }
  }

  public static DigitalTwinTypenextUnassembledPartMethod getNextUnassembledPartMethodImplementation() {
    return nextUnassembledPartMethodImplementation;
  }

  public static void setNextUnassembledPartMethodImplementation(DigitalTwinTypenextUnassembledPartMethod nextUnassembledPartMethodImplementationNewValue) {
    nextUnassembledPartMethodImplementation=nextUnassembledPartMethodImplementationNewValue;
  }
}

package aalto.ELECC8204.codegen.DigitalProductDescription.client;

import aalto.ELECC8204.codegen.DigitalProductDescription.DigitalTwinType;
import com.prosysopc.ua.MethodArgumentTransformer;
import com.prosysopc.ua.MethodCallStatusException;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaMethod;
import com.prosysopc.ua.types.opcua.FolderType;
import com.prosysopc.ua.types.opcua.client.BaseObjectTypeImpl;
import java.lang.Override;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.transport.AsyncResult;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=101")
public abstract class DigitalTwinTypeImplBase extends BaseObjectTypeImpl implements DigitalTwinType {
  protected DigitalTwinTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
  }

  @Mandatory
  @Override
  public FolderType getDigitalPartsNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "DigitalParts");
    return (FolderType) getComponent(browseName);
  }

  @Mandatory
  @Override
  public UaMethod getnextUnassembledPartNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "nextUnassembledPart");
    return (UaMethod) getComponent(browseName);
  }

  @Override
  public NodeId nextUnassembledPart() throws MethodCallStatusException, ServiceException {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/", "nextUnassembledPart"));
    return call(methodId, new MethodArgumentTransformer<NodeId>() {
      @Override
      public NodeId fromVariantArray(Variant[] values) {
        return (NodeId) values[0].getValue();
      }
    });
  }

  public AsyncResult<? extends NodeId> nextUnassembledPartAsync() {
    NodeId methodId = getComponentId(getQualifiedName("http://opcfoundation.org/UA/", "nextUnassembledPart"));
    return callAsync(methodId, new MethodArgumentTransformer<NodeId>() {
      @Override
      public NodeId fromVariantArray(Variant[] values) {
        return (NodeId) values[0].getValue();
      }
    });
  }
}

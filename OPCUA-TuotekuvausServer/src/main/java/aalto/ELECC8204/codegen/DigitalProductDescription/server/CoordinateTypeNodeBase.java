package aalto.ELECC8204.codegen.DigitalProductDescription.server;

import aalto.ELECC8204.codegen.DigitalProductDescription.CoordinateType;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.server.GeneratedNodeInitializer;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import com.prosysopc.ua.types.opcua.server.BaseObjectTypeNode;
import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.Variant;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=100")
public abstract class CoordinateTypeNodeBase extends BaseObjectTypeNode implements CoordinateType {
  private static GeneratedNodeInitializer<CoordinateTypeNode> coordinateTypeNodeInitializer;

  protected CoordinateTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    GeneratedNodeInitializer<CoordinateTypeNode> impl = getCoordinateTypeNodeInitializer();
    if(impl != null) {
      impl.init((CoordinateTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<CoordinateTypeNode> getCoordinateTypeNodeInitializer() {
    return coordinateTypeNodeInitializer;
  }

  public static void setCoordinateTypeNodeInitializer(GeneratedNodeInitializer<CoordinateTypeNode> coordinateTypeNodeInitializerNewValue) {
    coordinateTypeNodeInitializer=coordinateTypeNodeInitializerNewValue;
  }

  @Mandatory
  @Override
  public UaProperty getXNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "X");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public Double getX() {
    UaVariable node = getXNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node X does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (Double) value;
  }

  @Mandatory
  @Override
  public void setX(Double value) {
    UaVariable node = getXNode();
    if (node == null) {
      throw new RuntimeException("Setting X failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting X failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getYNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "Y");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public Double getY() {
    UaVariable node = getYNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node Y does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (Double) value;
  }

  @Mandatory
  @Override
  public void setY(Double value) {
    UaVariable node = getYNode();
    if (node == null) {
      throw new RuntimeException("Setting Y failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Y failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getZNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "Z");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public Double getZ() {
    UaVariable node = getZNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node Z does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (Double) value;
  }

  @Mandatory
  @Override
  public void setZ(Double value) {
    UaVariable node = getZNode();
    if (node == null) {
      throw new RuntimeException("Setting Z failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Z failed unexpectedly", e);
    }
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}

package aalto.ELECC8204.codegen.DigitalProductDescription.server;

import aalto.ELECC8204.codegen.DigitalProductDescription.DigitalPartStateDataType;
import aalto.ELECC8204.codegen.DigitalProductDescription.RectangleLegoType;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.server.GeneratedNodeInitializer;
import com.prosysopc.ua.server.NodeManagerUaNode;
import com.prosysopc.ua.server.ServiceContext;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.Variant;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=104")
public abstract class RectangleLegoTypeNodeBase extends DigitalPartTypeNode implements RectangleLegoType {
  private static GeneratedNodeInitializer<RectangleLegoTypeNode> rectangleLegoTypeNodeInitializer;

  protected RectangleLegoTypeNodeBase(NodeManagerUaNode nodeManager, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(nodeManager, nodeId, browseName, displayName);
  }

  @Override
  public void afterCreate() {
    super.afterCreate();

    // Call afterCreate for each sub-node (if the node has any)
    callAfterCreateIfExists(getLocationNode());
    callAfterCreateIfExists(getTopANode());
    callAfterCreateIfExists(getTopBNode());
    callAfterCreateIfExists(getTopCNode());
    callAfterCreateIfExists(getBottomANode());
    callAfterCreateIfExists(getBottomBNode());
    callAfterCreateIfExists(getBottomCNode());
    GeneratedNodeInitializer<RectangleLegoTypeNode> impl = getRectangleLegoTypeNodeInitializer();
    if(impl != null) {
      impl.init((RectangleLegoTypeNode)this);
    }
  }

  public static GeneratedNodeInitializer<RectangleLegoTypeNode> getRectangleLegoTypeNodeInitializer() {
    return rectangleLegoTypeNodeInitializer;
  }

  public static void setRectangleLegoTypeNodeInitializer(GeneratedNodeInitializer<RectangleLegoTypeNode> rectangleLegoTypeNodeInitializerNewValue) {
    rectangleLegoTypeNodeInitializer=rectangleLegoTypeNodeInitializerNewValue;
  }

  @Mandatory
  @Override
  public UaProperty getStateNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "State");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public DigitalPartStateDataType getState() {
    UaVariable node = getStateNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node State does not exist");
    }
    Variant value = node.getValue().getValue();
    return (DigitalPartStateDataType) value.asEnum(DigitalPartStateDataType.class);
  }

  @Mandatory
  @Override
  public void setState(DigitalPartStateDataType value) {
    UaVariable node = getStateNode();
    if (node == null) {
      throw new RuntimeException("Setting State failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting State failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getColorNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "Color");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getColor() {
    UaVariable node = getColorNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node Color does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setColor(String value) {
    UaVariable node = getColorNode();
    if (node == null) {
      throw new RuntimeException("Setting Color failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Color failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public UaProperty getOrientationNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "Orientation");
    return getProperty(browseName);
  }

  @Mandatory
  @Override
  public String getOrientation() {
    UaVariable node = getOrientationNode();
    if (node == null) {
      throw new RuntimeException("Mandatory node Orientation does not exist");
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setOrientation(String value) {
    UaVariable node = getOrientationNode();
    if (node == null) {
      throw new RuntimeException("Setting Orientation failed: does not exist (Optional Nodes must be configured in NodeBuilder)");
    }
    try {
      node.setValue(value);
    } catch(StatusException e) {
      throw new RuntimeException("Setting Orientation failed unexpectedly", e);
    }
  }

  @Mandatory
  @Override
  public CoordinateTypeNode getLocationNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "Location");
    return (CoordinateTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public CoordinateTypeNode getTopANode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "TopA");
    return (CoordinateTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public CoordinateTypeNode getTopBNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "TopB");
    return (CoordinateTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public CoordinateTypeNode getTopCNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "TopC");
    return (CoordinateTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public CoordinateTypeNode getBottomANode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "BottomA");
    return (CoordinateTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public CoordinateTypeNode getBottomBNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "BottomB");
    return (CoordinateTypeNode) getComponent(browseName);
  }

  @Mandatory
  @Override
  public CoordinateTypeNode getBottomCNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "BottomC");
    return (CoordinateTypeNode) getComponent(browseName);
  }

  @Override
  public Variant[] callMethod(ServiceContext serviceContext, NodeId methodId,
      Variant[] inputArguments, StatusCode[] inputArgumentResults,
      DiagnosticInfo[] inputArgumentDiagnosticInfos) throws StatusException {
    return super.callMethod(serviceContext, methodId, inputArguments, inputArgumentResults, inputArgumentDiagnosticInfos);
  }
}

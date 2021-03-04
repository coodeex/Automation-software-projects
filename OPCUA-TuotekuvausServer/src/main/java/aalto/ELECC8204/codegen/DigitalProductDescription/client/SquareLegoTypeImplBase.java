package aalto.ELECC8204.codegen.DigitalProductDescription.client;

import aalto.ELECC8204.codegen.DigitalProductDescription.CoordinateType;
import aalto.ELECC8204.codegen.DigitalProductDescription.DigitalPartStateDataType;
import aalto.ELECC8204.codegen.DigitalProductDescription.SquareLegoType;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaVariable;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.Variant;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=103")
public abstract class SquareLegoTypeImplBase extends DigitalPartTypeImpl implements SquareLegoType {
  protected SquareLegoTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
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
      return null;
    }
    Variant value = node.getValue().getValue();
    return (DigitalPartStateDataType) value.asEnum(DigitalPartStateDataType.class);
  }

  @Mandatory
  @Override
  public void setState(DigitalPartStateDataType value) throws StatusException {
    UaVariable node = getStateNode();
    if (node == null) {
      throw new RuntimeException("Setting State failed, the Optional node does not exist)");
    }
    node.setValue(value);
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
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (String) value;
  }

  @Mandatory
  @Override
  public void setColor(String value) throws StatusException {
    UaVariable node = getColorNode();
    if (node == null) {
      throw new RuntimeException("Setting Color failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }

  @Mandatory
  @Override
  public CoordinateType getLocationNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "Location");
    return (CoordinateType) getComponent(browseName);
  }

  @Mandatory
  @Override
  public CoordinateType getTopNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "Top");
    return (CoordinateType) getComponent(browseName);
  }

  @Mandatory
  @Override
  public CoordinateType getBottomNode() {
    QualifiedName browseName = getQualifiedName("http://opcfoundation.org/UA/", "Bottom");
    return (CoordinateType) getComponent(browseName);
  }
}

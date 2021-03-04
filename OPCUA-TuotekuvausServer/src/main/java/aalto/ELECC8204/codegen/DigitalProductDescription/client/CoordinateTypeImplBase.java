package aalto.ELECC8204.codegen.DigitalProductDescription.client;

import aalto.ELECC8204.codegen.DigitalProductDescription.CoordinateType;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.client.AddressSpace;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.nodes.UaVariable;
import com.prosysopc.ua.types.opcua.client.BaseObjectTypeImpl;
import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.QualifiedName;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=100")
public abstract class CoordinateTypeImplBase extends BaseObjectTypeImpl implements CoordinateType {
  protected CoordinateTypeImplBase(AddressSpace addressSpace, NodeId nodeId,
      QualifiedName browseName, LocalizedText displayName) {
    super(addressSpace, nodeId, browseName, displayName);
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
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (Double) value;
  }

  @Mandatory
  @Override
  public void setX(Double value) throws StatusException {
    UaVariable node = getXNode();
    if (node == null) {
      throw new RuntimeException("Setting X failed, the Optional node does not exist)");
    }
    node.setValue(value);
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
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (Double) value;
  }

  @Mandatory
  @Override
  public void setY(Double value) throws StatusException {
    UaVariable node = getYNode();
    if (node == null) {
      throw new RuntimeException("Setting Y failed, the Optional node does not exist)");
    }
    node.setValue(value);
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
      return null;
    }
    Object value = node.getValue().getValue().getValue();
    return (Double) value;
  }

  @Mandatory
  @Override
  public void setZ(Double value) throws StatusException {
    UaVariable node = getZNode();
    if (node == null) {
      throw new RuntimeException("Setting Z failed, the Optional node does not exist)");
    }
    node.setValue(value);
  }
}

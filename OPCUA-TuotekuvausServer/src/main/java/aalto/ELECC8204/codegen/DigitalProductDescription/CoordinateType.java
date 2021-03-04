package aalto.ELECC8204.codegen.DigitalProductDescription;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import java.lang.Double;
import java.lang.String;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=100")
public interface CoordinateType extends BaseObjectType {
  String X = "X";

  String Y = "Y";

  String Z = "Z";

  @Mandatory
  UaProperty getXNode();

  @Mandatory
  Double getX();

  @Mandatory
  void setX(Double value) throws StatusException;

  @Mandatory
  UaProperty getYNode();

  @Mandatory
  Double getY();

  @Mandatory
  void setY(Double value) throws StatusException;

  @Mandatory
  UaProperty getZNode();

  @Mandatory
  Double getZ();

  @Mandatory
  void setZ(Double value) throws StatusException;
}

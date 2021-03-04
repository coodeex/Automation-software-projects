package aalto.ELECC8204.codegen.DigitalProductDescription;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import java.lang.String;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=102")
public interface DigitalPartType extends BaseObjectType {
  String STATE = "State";

  String ORIENTATION = "Orientation";

  String LOCATION = "Location";

  @Mandatory
  UaProperty getStateNode();

  @Mandatory
  DigitalPartStateDataType getState();

  @Mandatory
  void setState(DigitalPartStateDataType value) throws StatusException;

  @Mandatory
  UaProperty getOrientationNode();

  @Mandatory
  String getOrientation();

  @Mandatory
  void setOrientation(String value) throws StatusException;

  @Mandatory
  CoordinateType getLocationNode();
}

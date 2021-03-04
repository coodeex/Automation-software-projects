package aalto.ELECC8204.codegen.DigitalProductDescription;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import java.lang.String;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=103")
public interface SquareLegoType extends DigitalPartType {
  String STATE = "State";

  String COLOR = "Color";

  String LOCATION = "Location";

  String TOP = "Top";

  String BOTTOM = "Bottom";

  @Mandatory
  UaProperty getStateNode();

  @Mandatory
  DigitalPartStateDataType getState();

  @Mandatory
  void setState(DigitalPartStateDataType value) throws StatusException;

  @Mandatory
  UaProperty getColorNode();

  @Mandatory
  String getColor();

  @Mandatory
  void setColor(String value) throws StatusException;

  @Mandatory
  CoordinateType getLocationNode();

  @Mandatory
  CoordinateType getTopNode();

  @Mandatory
  CoordinateType getBottomNode();
}

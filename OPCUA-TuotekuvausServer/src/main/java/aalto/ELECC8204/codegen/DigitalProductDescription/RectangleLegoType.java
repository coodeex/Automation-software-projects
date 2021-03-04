package aalto.ELECC8204.codegen.DigitalProductDescription;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import java.lang.String;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=104")
public interface RectangleLegoType extends DigitalPartType {
  String STATE = "State";

  String COLOR = "Color";

  String ORIENTATION = "Orientation";

  String LOCATION = "Location";

  String TOP_A = "TopA";

  String TOP_B = "TopB";

  String TOP_C = "TopC";

  String BOTTOM_A = "BottomA";

  String BOTTOM_B = "BottomB";

  String BOTTOM_C = "BottomC";

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
  UaProperty getOrientationNode();

  @Mandatory
  String getOrientation();

  @Mandatory
  void setOrientation(String value) throws StatusException;

  @Mandatory
  CoordinateType getLocationNode();

  @Mandatory
  CoordinateType getTopANode();

  @Mandatory
  CoordinateType getTopBNode();

  @Mandatory
  CoordinateType getTopCNode();

  @Mandatory
  CoordinateType getBottomANode();

  @Mandatory
  CoordinateType getBottomBNode();

  @Mandatory
  CoordinateType getBottomCNode();
}

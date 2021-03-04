package aalto.ELECC8204.codegen.DigitalProductDescription;

import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaProperty;
import java.lang.String;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=105")
public interface FacePlateBackType extends CadPartType {
  String STATE = "State";

  String COLOR = "Color";

  String PATH_TO_MODEL = "PathToModel";

  String LOCATION = "Location";

  String SQUARE_LEFT = "square_left";

  String SQUARE_RIGHT = "square_right";

  String CIRCLE_LEFT = "circle_left";

  String CIRCLE_RIGHT = "circle_right";

  String SHAFT = "shaft";

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
  UaProperty getPathToModelNode();

  @Mandatory
  String getPathToModel();

  @Mandatory
  void setPathToModel(String value) throws StatusException;

  @Mandatory
  CoordinateType getLocationNode();

  @Mandatory
  CoordinateType getsquare_leftNode();

  @Mandatory
  CoordinateType getsquare_rightNode();

  @Mandatory
  CoordinateType getcircle_leftNode();

  @Mandatory
  CoordinateType getcircle_rightNode();

  @Mandatory
  CoordinateType getshaftNode();
}

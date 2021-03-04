package aalto.ELECC8204.codegen.DigitalProductDescription;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.TypeDefinitionId;
import com.prosysopc.ua.nodes.Mandatory;
import com.prosysopc.ua.nodes.UaMethod;
import com.prosysopc.ua.types.opcua.BaseObjectType;
import com.prosysopc.ua.types.opcua.FolderType;
import java.lang.String;
import org.opcfoundation.ua.builtintypes.NodeId;

/**
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=101")
public interface DigitalTwinType extends BaseObjectType {
  String DIGITAL_PARTS = "DigitalParts";

  String NEXT_UNASSEMBLED_PART = "nextUnassembledPart";

  @Mandatory
  FolderType getDigitalPartsNode();

  @Mandatory
  UaMethod getnextUnassembledPartNode();

  NodeId nextUnassembledPart() throws StatusException, ServiceException;
}

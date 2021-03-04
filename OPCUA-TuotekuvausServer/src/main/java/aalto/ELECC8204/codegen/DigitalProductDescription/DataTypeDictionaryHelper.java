package aalto.ELECC8204.codegen.DigitalProductDescription;

import com.prosysopc.ua.typedictionary.GeneratedDataTypeDictionary;
import java.lang.String;
import org.opcfoundation.ua.builtintypes.ExpandedNodeId;

/**
 * Generated on 2021-02-09 19:41:25
 */
public class DataTypeDictionaryHelper {
  public static GeneratedDataTypeDictionary createDataTypeDictionary() {
    GeneratedDataTypeDictionary r = new GeneratedDataTypeDictionary("DigitalProductDescription");
    r.addTypeInformation(eni("nsu=DigitalProductDescription;i=99"), "DigitalPartStateDataType", DigitalPartStateDataType.class);
    return r;
  }

  private static ExpandedNodeId eni(String id) {
    return ExpandedNodeId.parseExpandedNodeId(id);
  }
}

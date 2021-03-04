package aalto.ELECC8204.codegen.DigitalProductDescription.server;

import aalto.ELECC8204.codegen.DigitalProductDescription.DataTypeDictionaryHelper;
import aalto.ELECC8204.codegen.DigitalProductDescription.Serializers;
import com.prosysopc.ua.nodes.UaInstance;
import com.prosysopc.ua.server.ServerCodegenModel;
import java.lang.Class;
import java.util.ArrayList;
import java.util.List;

/**
 * Generated on 2021-02-09 19:41:25
 */
public class ServerInformationModel {
  public static final ServerCodegenModel MODEL = new ServerCodegenModel(createClassesList(), Serializers.SERIALIZERS, DataTypeDictionaryHelper.createDataTypeDictionary());

  private static List<Class<? extends UaInstance>> createClassesList() {
    ArrayList<Class<? extends UaInstance>> list = new ArrayList<Class<? extends UaInstance>>();
    list.add(CoordinateTypeNode.class);
    list.add(DigitalTwinTypeNode.class);
    list.add(DigitalPartTypeNode.class);
    list.add(SquareLegoTypeNode.class);
    list.add(RectangleLegoTypeNode.class);
    list.add(CadPartTypeNode.class);
    list.add(FacePlateBackTypeNode.class);
    list.add(FacePlateFrontTypeNode.class);
    list.add(BoltAngularTypeNode.class);
    list.add(BoltTypeNode.class);
    list.add(ShaftTypeNode.class);
    list.add(PendulumTypeNode.class);
    return list;
  }
}

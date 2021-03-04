package aalto.ELECC8204.codegen.DigitalProductDescription.client;

import aalto.ELECC8204.codegen.DigitalProductDescription.DataTypeDictionaryHelper;
import aalto.ELECC8204.codegen.DigitalProductDescription.Serializers;
import com.prosysopc.ua.client.ClientCodegenModel;
import com.prosysopc.ua.nodes.UaInstance;
import java.lang.Class;
import java.util.ArrayList;
import java.util.List;

/**
 * Generated on 2021-02-09 19:41:25
 */
public class ClientInformationModel {
  public static final ClientCodegenModel MODEL = new ClientCodegenModel(createClassesList(), Serializers.SERIALIZERS, DataTypeDictionaryHelper.createDataTypeDictionary());

  private static List<Class<? extends UaInstance>> createClassesList() {
    ArrayList<Class<? extends UaInstance>> list = new ArrayList<Class<? extends UaInstance>>();
    list.add(CoordinateTypeImpl.class);
    list.add(DigitalTwinTypeImpl.class);
    list.add(DigitalPartTypeImpl.class);
    list.add(SquareLegoTypeImpl.class);
    list.add(RectangleLegoTypeImpl.class);
    list.add(CadPartTypeImpl.class);
    list.add(FacePlateBackTypeImpl.class);
    list.add(FacePlateFrontTypeImpl.class);
    list.add(BoltAngularTypeImpl.class);
    list.add(BoltTypeImpl.class);
    list.add(ShaftTypeImpl.class);
    list.add(PendulumTypeImpl.class);
    return list;
  }
}

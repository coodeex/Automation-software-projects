package aalto.ELECC8204.codegen.DigitalProductDescription;

import com.prosysopc.ua.TypeDefinitionId;
import java.lang.Integer;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opcfoundation.ua.builtintypes.Enumeration;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;

/**
 *
 * 			Enumeration data type for describing the state of a part.
 * 		
 * <p>
 * Generated on 2021-02-09 19:41:25
 */
@TypeDefinitionId("nsu=DigitalProductDescription;i=99")
public enum DigitalPartStateDataType implements Enumeration {
  ToBeFetched(0),

  OnBoard(1),

  AtAssembly(2),

  Assembled(3);

  public static final EnumSet<DigitalPartStateDataType> NONE = EnumSet.noneOf(DigitalPartStateDataType.class);

  public static final EnumSet<DigitalPartStateDataType> ALL = EnumSet.allOf(DigitalPartStateDataType.class);

  private static final Map<Integer, DigitalPartStateDataType> map;

  static {
    map = new HashMap<Integer,DigitalPartStateDataType>();
    for (DigitalPartStateDataType i : DigitalPartStateDataType.values()) {
      map.put(i.value, i);
    }
  }

  private final int value;

  DigitalPartStateDataType(int value) {
    this.value = value;
  }

  public static DigitalPartStateDataType valueOf(int value) {
    return map.get(value);
  }

  public static DigitalPartStateDataType valueOf(Integer value) {
    return value == null ? null : valueOf(value.intValue());
  }

  public static DigitalPartStateDataType valueOf(UnsignedInteger value) {
    return value == null ? null : valueOf(value.intValue());
  }

  public static DigitalPartStateDataType[] valueOf(int[] value) {
    DigitalPartStateDataType[] result = new DigitalPartStateDataType[value.length];
    for (int i = 0; i < value.length; i++) {
      result[i] = valueOf(value[i]);
    }
    return result;
  }

  public static DigitalPartStateDataType[] valueOf(Integer[] value) {
    DigitalPartStateDataType[] result = new DigitalPartStateDataType[value.length];
    for (int i = 0; i < value.length; i++) {
      result[i] = valueOf(value[i]);
    }
    return result;
  }

  public static DigitalPartStateDataType[] valueOf(UnsignedInteger[] value) {
    DigitalPartStateDataType[] result = new DigitalPartStateDataType[value.length];
    for (int i = 0; i < value.length; i++) {
      result[i] = valueOf(value[i]);
    }
    return result;
  }

  public static UnsignedInteger getMask(DigitalPartStateDataType... list) {
    int result = 0;
    for (DigitalPartStateDataType c : list) {
      result |= c.value;
    }
    return UnsignedInteger.getFromBits(result);
  }

  public static UnsignedInteger getMask(Collection<DigitalPartStateDataType> list) {
    int result = 0;
    for (DigitalPartStateDataType c : list) {
      result |= c.value;
    }
    return UnsignedInteger.getFromBits(result);
  }

  public static EnumSet<DigitalPartStateDataType> getSet(UnsignedInteger mask) {
    return getSet(mask.intValue());
  }

  public static EnumSet<DigitalPartStateDataType> getSet(int mask) {
    List<DigitalPartStateDataType> res = new ArrayList<DigitalPartStateDataType>();
    for (DigitalPartStateDataType l : DigitalPartStateDataType.values()) {
      if ((mask & l.value) == l.value) {
        res.add(l);
      }
    }
    return EnumSet.copyOf(res);
  }

  @Override
  public int getValue() {
    return value;
  }
}

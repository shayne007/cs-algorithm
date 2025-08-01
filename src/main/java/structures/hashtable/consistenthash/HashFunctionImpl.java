package structures.hashtable.consistenthash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author fengsy
 * @date 7/8/20
 * @Description
 */
public class HashFunctionImpl implements HashFunction {

  @Override
  public long hash(String key) {
    ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
    int seed = 0x1234ABCD;
    ByteOrder byteOrder = buf.order();
    buf.order(ByteOrder.LITTLE_ENDIAN);
    long m = 0xc6a4a7935bd1e995L;
    int r = 47;
    long h = seed ^ (buf.remaining() * m);
    long k;
    while (buf.remaining() >= 8) {
      k = buf.getLong();
      k *= m;
      k ^= k >>> r;
      k *= m;
      h ^= k;
      h *= m;
    }
    if (buf.remaining() > 0) {
      ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
      finish.put(buf).rewind();
      h ^= finish.getLong();
      h *= m;
    }
    h ^= h >>> r;
    h *= m;
    h ^= h >>> r;
    buf.order(byteOrder);
//        return h;
    return (long) (Math.abs(h) % Integer.MAX_VALUE);
  }
}

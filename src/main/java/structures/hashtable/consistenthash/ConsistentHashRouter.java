package structures.hashtable.consistenthash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author fengsy
 * @date 7/8/20
 * @Description
 */
public class ConsistentHashRouter<T extends Node> {

  private final SortedMap<Long, VirtualNode<T>> ring = new TreeMap<>();
  private final HashFunction hashFunction;

  public ConsistentHashRouter(Collection<T> pNodes, int vNodeCount) {
    this(pNodes, vNodeCount, new MD5Hash());
  }

  public ConsistentHashRouter(Collection<T> pNodes, int vNodeCount, HashFunction hashFunction) {
    if (hashFunction == null) {
      throw new NullPointerException("Hash Function is null");
    }
    this.hashFunction = hashFunction;
    if (pNodes != null) {
      for (T pNode : pNodes) {
        addNode(pNode, vNodeCount);
      }
    }
  }

  public void addNode(T pNode, int vNodeCount) {
    if (vNodeCount < 0) {
      throw new IllegalArgumentException("illegal virtual node counts :" + vNodeCount);
    }
//    int existingReplicas = getExistingReplicas(pNode);
    for (int i = 0; i < vNodeCount; i++) {
      VirtualNode<T> vNode = new VirtualNode<>(pNode, i);
      ring.put(hashFunction.hash(vNode.getKey()), vNode);
    }
  }

  public void removeNode(T pNode) {
    Iterator<Long> it = ring.keySet().iterator();
    while (it.hasNext()) {
      Long key = it.next();
      VirtualNode<T> virtualNode = ring.get(key);
      if (virtualNode.isVirtualNodeOf(pNode)) {
        it.remove();
      }
    }
  }

  public T routeNode(String objectKey) {
    if (ring.isEmpty()) {
      return null;
    }
    Long hashVal = hashFunction.hash(objectKey);
    SortedMap<Long, VirtualNode<T>> tailMap = ring.tailMap(hashVal);
    Long nodeHashVal = !tailMap.isEmpty() ? tailMap.firstKey() : ring.firstKey();
    return ring.get(nodeHashVal).getPhysicalNode();
  }

  public int getExistingReplicas(T pNode) {
    int replicas = 0;
    for (VirtualNode<T> vNode : ring.values()) {
      if (vNode.isVirtualNodeOf(pNode)) {
        replicas++;
      }
    }
    return replicas;
  }

  //default hash function
  private static class MD5Hash implements HashFunction {

    MessageDigest instance;

    public MD5Hash() {
      try {
        instance = MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException e) {
      }
    }

    @Override
    public long hash(String key) {
      instance.reset();
      instance.update(key.getBytes());
      byte[] digest = instance.digest();

      long h = 0;
      for (int i = 0; i < 4; i++) {
        h <<= 8;
        h |= ((int) digest[i]) & 0xFF;
      }
      return h;
    }
  }
}


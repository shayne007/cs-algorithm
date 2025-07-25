package structures.hashtable.consistenthash;

/**
 * @author fengsy
 * @date 7/8/20
 * @Description
 */
public class VirtualNode<T extends Node> implements Node {

  final T physicalNode;
  final int replicaIndex;

  public VirtualNode(T physicalNode, int replicaIndex) {
    this.physicalNode = physicalNode;
    this.replicaIndex = replicaIndex;
  }

  @Override

  public String getKey() {
    return physicalNode.getKey() + "-" + replicaIndex;
  }

  public boolean isVirtualNodeOf(T pNode) {
    return physicalNode.getKey().equals(pNode.getKey());
  }

  public T getPhysicalNode() {
    return physicalNode;
  }
}

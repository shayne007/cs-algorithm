package linkedList;

import java.util.LinkedList;

/**
 * @author fengsy
 * @date 4/19/20
 * @Description
 */
public class LinkedListAlgo {
    /**
     * 单链表的反转
     * */
    private static Node reverse(Node list){
        Node curr = list,pre =null;
        while (curr != null){
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 单链表删除某个节点
     * */
    private Node deleteNode(Node list,int value){
        Node n = list;
        if (n.data == value) {
            return n.next;
        }
        while (n.next!=null){
            if (n.next.data == value) {
                n.next = n.next.next;
                return list;
            }
            n = n.next;
        }
        return  list;
    }

    /**
     * 添加节点至末尾
     * */
    private Node appendToTail(Node list,int value){
        Node n = list;
        Node newNode = new Node(value,null);
        if (n == null) {
            return newNode;
        }
        while(n.next != null){
            n = n.next;
        }
        n.next = newNode;
        return list;
    }

    public static class Node{
        private int data;
        private Node next;
        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }
    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static void main(String[] args) {

        Node node1 = createNode(3);
        Node node2 = createNode(4);
        Node node3 = createNode(7);
        node2.next = node3;
        node1.next = node2;
        Node list = new Node(-1,node1);
        printAll(list);

        Node revlist = reverse(list);

        printAll(revlist);

        LinkedList<String> linkedList = new LinkedList<>();
    }
}




package getalongwithmecommunity;

public class LinkedList {
    Node head;

    public void append(String name, int age, String phone, String email, String city) {
        Node newNode = new Node(name, age, phone, email, city);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }   
}
}

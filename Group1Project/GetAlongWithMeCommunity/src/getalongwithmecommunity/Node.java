/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package getalongwithmecommunity;

/**
 *
 * @author Mona
 */
public class Node {
    String name;
    int age;
    String phone;
    String email;
    String city;
    Node next;

    public Node(String name, int age, String phone, String email, String city) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.next = null;
    }   
}

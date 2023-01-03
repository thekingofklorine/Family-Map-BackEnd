package utility;

import model.Person;

public class BinaryTree {

  public static class Node{
    Person person;
    Node motherNode;
    Node fatherNode;

    public Node(Person person) {
      this.person = person;
      this.motherNode = null;
      this.fatherNode = null;
    }

    public Node root;


  }
}

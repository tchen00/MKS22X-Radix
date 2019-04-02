import java.util.*;

public class MyLinkedList<E>{
  // ----------------------- LINKED LIST ------------------------------------------------------------------//

    private int length;
    private Node start,end;

    //empty constructor
    public MyLinkedList(){
      length = 0;
    }

    //OLD constructor
  /*
    public MyLinkedList(Node starty, Node endy) {
      start = starty;
      end = endy;
      start.setNext(endy);
      end.setPrev(starty);
      size = 2;
      //sets the linked list node w/ start and end
    }
  */
    // size!
    public int size() {
      return length;
    }

    //
    public void clear(){
      start = null;
      end = null;
      size = 0;
    }

    public boolean add(Integer value) {
      //throwing proper exceptiosn
      if (value == null) throw new NullPointerException("from add to last");
      // if the length is 0, create a new Node
      if (length == 0) {
        Node n = new Node(value);
        //making the next and prev null bc length 0
        n.setPrev(null);
        n.setNext(null);
        start = n;
        end = n;
        length++;
      } else {
        Node n = new Node(value);
        n.setPrev(end);
        n.setNext(null);
        end.setNext(n);
        end = n;
        length++;
      } return true;
    }


    public String toString() {
      //start toString from beginning
      Node current = start;
      String output = "[";
      for (int i = 0; i < length; i++) {
         if (i != length - 1) { // if less then add commas
           output += current.getData() + ", ";
           current = current.next();
         } else {
              output += current.getData();
           }
      } return output + "]";
    }

    private Node getNthNode(int nod){
      //mkaing this private bc if it wasn't this list will function like an array
      Node current = start;
      for (int i = 0; i < nod; i++){
        current = current.next();
      }
      return current;
    }

    public Integer get(int index) {
      //adding proper exceptions here
      if (index < 0 || index >= length) {
        throw new IndexOutOfBoundsException("from the get method");
      } return this.getNthNode(index).getData();
    }

    public Integer set(int i, Integer value){
      // adding proper exceptions here
      if (i < 0 || i >= length) {
        throw new IndexOutOfBoundsException("from the set method");
      } Integer old = this.get(i);
        this.getNthNode(i).setData(value);
        return old;
    }

    public boolean contains(Integer value) {
      //returns whether if the value is in the LinkedList
      for (int i = 0; i < length; i++) {
        if (this.get(i).equals(value)) {
          return true;
        }
      } return false;
    }

    public int indexOf(Integer value) {
      //returning the first instance of the value, if any! if not return -1
      for (int i = 0; i < length; i++) {
        if (this.get(i).equals(value)) {
          return i;
        }
      } return -1;
    }

    public void add(int index, Integer value){
      //throw proper exception
      if(index < 0 || index > size()){
        throw new IndexOutOfBoundsException();
      }
      Node toAdd = new Node(value);
      //if you add it to the front
      if (index == 0){
        toAdd.setNext(start);
        start.setPrev(toAdd);
        start = toAdd;
      } else if (index == length) {
        //if add to the end
        toAdd.setPrev(end);
        end.setNext(toAdd);
        end = toAdd;
      } else {
        Node left = getNthNode(index - 1);//fix this
        Node temp = left.next();
        left.setNext(toAdd);
        toAdd.setPrev(left);
        toAdd.setNext(temp);
        temp.setPrev(toAdd);
      } length++;
    }

    public Integer remove(int index){
      if(index < 0 || index > length){
        throw new IndexOutOfBoundsException();
      } Node output = getNthNode(index);
        if(index == 0){
          //if remove from the first
          output.next().setPrev(null);
          start = output.next();
        } else if (index == length - 1) {
          //if remove from the end
          output.prev().setNext(null);
          end = output.prev();
        } else {
          // if you add it to the middle
          Node left = output.prev();
          Node right = output.next();
          right.setPrev(left);
          left.setNext(right);
        } length--;
          return output.getData();
    }

     public boolean remove(Integer value) {
       // check to see if this value is in the linked list first
       if (!(contains(value))) return false;
         // if it is ....
         remove(indexOf(value));
         return true;
     }

     //in O(1) runtime, move the elements from other onto the end of this
     //The size of other is reduced to 0
     //The size of this is now the combined sizes of both original lists
     public void extend(MyLinkedList other){
          // if size was 0
          if (size() == 0){
            if (other.size() == 1 ){
              start = other.start;
              length++;
              other.length = 0;
            } else if (other.size() > 1){
              start = other.start;
              end = other.end;
              length = other.size();
              other.length = 0;
            }
          } else if (size () == 1) { // if size is equal to 1
              if (other.size() == 1){
                end = other.start;
                length++;
                other.length = 0;
              } else if (other.size() > 1){
                start.setNext(other.start);
                other.start.setPrev(start);
                length += other.size();
                end = other.end;
                other.length = 0;
              }
           } else if (other.size() > 1){ // if the other size is greater than 1
              length += other.size();
              end.setNext(other.start);
              other.start.setPrev(end);
              end = other.end;
              other.length = 0;
              other.start = null; // sets the other start and end to null so you can't access in the middle
              other.end = null;
        }
  }
  // ----------------------- NODE ------------------------------------------------------------------------------//
  class Node{
    private Integer data;
    private Node next,prev;
     //constructor
    public Node(Integer info) {
      data = info;
     }

    //accessor methods to get info
    public Node next() {
      return next;
    }

    public Node prev() {
      return prev;
    }

    //set methods
    public void setNext(Node other) {
      next = other;
    }

    public void setPrev(Node other) {
      prev = other;
    }

    public Integer getData() {
      return data;
    }

    //change node
    public Integer setData(int i) {
      data = i;
      return data;
    }

    //toString
    public String toString(){
      return "" + data;
    }

  }

}

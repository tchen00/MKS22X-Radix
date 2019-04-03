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
    // size
    public int size() {
      return length;
    }

    //
    public void clear(){
      start = null;
      end = null;
      length = 0;
    }

    public boolean add(E value){
      if (start == null){
        start = new Node(value, null, null);
        end = start;
      }
      else if (end == start){
        end = new Node(value, null, start);
        start.setNext(end);
      }
      else{
        Node temp = new Node(value, null, end);
        end.setNext(temp);
        end = temp;
      }
      length++;
      return true;
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

    private Node getNthNode(int index){
      int count = 0;
      Node temp = start;
      while (temp != null){
        // when found
        if (count == index){
          return temp;
        }
        count++;
        temp = temp.next();
      }
      return null;
    }

    public void add(int index, E value){
      if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
      if (index == size()){
        add(value);
      }
      else if (index == 0){
        Node temp = new Node(value, start, null);
        start.setPrev(temp);
        start = temp;
        length++;
      }
      else{
        Node temp = new Node(value, getNthNode(index), getNthNode(index - 1));
        getNthNode(index).setPrev(temp);
        getNthNode(index - 1).setNext(temp);
        length++;
      }
    }

    // remove the 1st element of the list, and return that value.
    public E removeFront(){
      if (size() == 0) throw new NoSuchElementException("from removeFront");
      E temp = start.getData(); // temp is the current getData
      if (size() == 1){ // if linked list is 1, clear and return the temp
        clear();
        return temp;
      }
      // if size is greater than 1
      start = start.next();
      start.setPrev(null);
      length--;
      return temp;
    }

     //in O(1) runtime, move the elements from other onto the end of this
     //The size of other is reduced to 0
     //The size of this is now the combined sizes of both original lists
     public void extend(MyLinkedList<E> other){
       //if size is 0
       if (size() == 0){
         start = other.start;
         end = other.end;
       }
       else if (other.size() > 0){
         // makes end of one list the start of the next
         end.setNext(other.start);
         other.start.setPrev(end);
         end = other.end;
       }
       // update length
       length += other.length;
       // clear other
       other.clear();
     }

  // ----------------------- NODE ------------------------------------------------------------------------------//
  class Node{
    private E data;
    private Node next,prev;
     //constructor
     public Node(E value, Node nexty, Node backy){
       data = value;
       next = nexty;
       prev = backy;
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

    public E getData() {
      return data;
    }

    public E setData(E i){
      E temp = data;
      data = i;
      return temp;
    }

    //toString
    public String toString(){
      return "" + data;
    }

  }

}

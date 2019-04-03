import java.util.*;
@SuppressWarnings({"unchecked", "rawtypes"})

public class Radix{
  public static void radixsort(int[]data){
    //initializing mylinkedlist
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    //putting info into buckets
    for (int i = 0; i < 20; i++){
      buckets[i] = new MyLinkedList<>();
    }
  }

  // finding the max
  public static int max(int[] data){
    if (data.length == 0){
      // if the length is 0 obv return 0
      return 0;
    }
    // setting a temp value to store
    int temp = Math.abs(data[0]);
    // looping through data
    for (int i = 0; i < data.length; i++){
      // if next is greater than current temp -- replace
      if (Math.abs(data[i]) > temp){
        temp = Math.abs(data[i]);
      }
     }
    return (int)Math.log10(temp) + 1;
  }

  public static void main(String[] args){

  }
}

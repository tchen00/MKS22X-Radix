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
    MyLinkedList<Integer> list = new MyLinkedList<Integer>(); //initializing new linked list
    int digits = max(data); //number of digits in max value of data

    for (int i = 0; i < digits; i++){ //loops through number of passes
      // first pass
      if (i == 0){
        for (int j = 0; j < data.length; j++){
          int temp = data[j];
          // abs of digit
          int idx = Math.abs((int)(temp / (Math.pow(10, i))) % 10);
          // if positive
          if (temp >= 0) buckets[idx + 10].add(temp);
          // if negative (add in inverted order -- from Mr. K in class)
          else buckets[9 - idx].add(temp);
        }
      }
      // other passes
      else{
        while (list.size() > 0){
          int num = list.removeFront();
          // abs of digits (from above haha)
          int idx = Math.abs((int)(num / (Math.pow(10, i))) % 10);
          // if positive
          if (num >= 0) buckets[idx + 10].add(num);
          // if negative (thanks Mr. K again!)
          else buckets[9 - idx].add(num);
        }
      }

      // adding the buckets into first buckets
      for (int k = 0; k < 20; k++){
        list.extend(buckets[k]);
      }
    }

    int idx = 0;
    while (list.size() > 0){
      data[idx] = list.removeFront();
      idx++;
    }
  }

  // finding the max number of digits
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
    int[] data = {8, 12, 16, 0, 1, 2, 3, 90};
     radixsort(data);
     System.out.println(Arrays.toString(data));
  }
}

import java.util.*;

public class MaxHeapTest{
  public static void main(String[] args){
    MaxHeap maxHeap = new MaxHeap<Integer>(Arrays.asList(new Integer[]{1,2,3,4}));
    System.out.println(maxHeap);
  }
}

class MaxHeap <E extends Comparable<? super E>>{
  List<E> list;

  public MaxHeap(List<E> list){
    if(list == null)
      throw new IllegalArgumentException("list cannot be null");
    this.list = list;
    buildHeap();
  }

  public void buildHeap(){
    for(int i=lastParent(); i >= 0; i--)
      maxHeapify(i);
  }

  private void maxHeapify(int parent){
    if(isParent(parent)){
      //assume left child is bigger for now
      int biggerChild = 2 * parent + 1;
      int rightChild = biggerChild+1;
      if(rightChild < list.size() && compare(rightChild, biggerChild) > 0)
        biggerChild = rightChild;

      if(compare(biggerChild, parent) > 0){
        swap(biggerChild, parent);
        maxHeapify(biggerChild);
      }
    }
  }

  private int compare(int fir, int sec){
    return list.get(fir).compareTo(list.get(sec));
  }

  public boolean add(E item){
    if(item == null)
      return false;

    list.add(item);
    int child = last();
    int parent = getParent(child);

    while(isParent(parent)){
      if(compare(parent, child) < 0){
        swap(parent, child);
        child = parent;
        parent = getParent(parent);
      }
      else
        break;
    }
    return true;
  }

  private void swap(int left, int right){
    Collections.swap(list, left, right);
  }

  public E remove(){
    if(isEmpty())
      return null;

    swap(0, last());
    E max = list.remove(last());
    maxHeapify(0);
    return max;
  }

  public boolean isEmpty(){
    return list.size() == 0;
  }

  private int last(){
    return list.size() - 1;
  }

  private int getParent(int index){
    if(index == 0)
      return -1;
    else
      return (index-1)/2;
  }

  private boolean isParent(int index){
    return index >= 0 && index <= lastParent();
  }

  private int lastParent(){
    return list.size()/2 - 1;
  }


  public String toString(){
    StringBuilder builder = new StringBuilder();
    int lineCounter = 0;
    int lineMax = 1;
    for(E item : list){
      builder.append(item);
      builder.append(" ");
      lineCounter++;
      if(lineCounter >= lineMax){
        builder.append("\n");
        lineCounter = 0;
        lineMax++;
      }
    }
    return builder.toString();
  }
}

class MaxHeap1{
  List<Integer> list;

  public MaxHeap1(List<Integer> list){
    for(Integer num : list)
      this.list.add(num);
    buildHeap();
  }

  private void buildHeap(){
    for(int i = lastParent(); i >= 0; i--)
      maxHeapify(i);
  }

  private void maxHeapify(int index){
    if(isParent(index)){
      int biggerChild = index * 2 + 1;
      int rightChild = biggerChild + 1;
      if(rightChild < list.size() && list.get(biggerChild) < list.get(rightChild))
        biggerChild = rightChild;

      if(list.get(index) < list.get(biggerChild)){
        Collections.swap(list, index, biggerChild);
        maxHeapify(biggerChild);
      }
    }
  }

  private void add(Integer num){
    list.add(num);
    int parent = lastParent();

    while(parent != -1){
      maxHeapify(parent);
      parent = getParent(parent);
    }
  }

  private Integer removeMax(){
    if(!isEmpty()){
      Collections.swap(list, 0, last());
      int max = list.remove(last());
      maxHeapify(0);
      return max;
    }
    return null;
  }

  private boolean isParent(int index){
    return index >= 0 &&  index <= lastParent() ;
  }

  private int getParent(int childIndex){
    if(childIndex == 0)
      return -1;
    else
      return (childIndex - 1) / 2;
  }

  private boolean isLeaf(int index){
    return index > lastParent() && index < list.size();
  }

  //return -1 if list is empty
  private int lastParent(){
    return list.size()/2 - 1;
  }

  //return -1 if list is empty
  private int last(){
    return list.size() - 1;
  }

  private boolean isEmpty(){
    return list.size() == 0;
  }

  public String toString(){
    StringBuilder builder = new StringBuilder();
    int lineCounter = 0;
    int lineMax = 1;
    for(Integer num : list){
      builder.append(num);
      builder.append(" ");
      lineCounter++;
      if(lineCounter >= lineMax){
        builder.append("\n");
        lineCounter = 0;
        lineMax++;
      }
    }
    return builder.toString();
  }
}

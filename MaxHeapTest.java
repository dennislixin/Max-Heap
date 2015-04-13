import java.util.*;

public class MaxHeapTest{
  public static void main(String[] args){
    test(new Integer[]{1,2,3,4});
    test(new Integer[]{});
  }

  public static void test(Integer[] array){
    MaxHeap<Integer> maxHeap = new MaxHeap<>(array);
    Random random = new Random();
    for(int i=0; i<100; i++){
      Integer num = random.nextInt();
      maxHeap.add(num);
    }

    int last = Integer.MAX_VALUE;
    while(maxHeap.size() > 0){
      int cur = maxHeap.remove();
      if(last < cur){
        System.out.println("wrong");
        break;
      }
      last = cur;
    }
  }
}

class MaxHeap <E extends Comparable<? super E>>{
  List<E> list;

  public MaxHeap(E[] array){
    this(Arrays.asList(array));
  }
  public MaxHeap(List<E> list){
    if(list == null)
      throw new IllegalArgumentException("list cannot be null");
    this.list = new ArrayList<E>(list);
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
      if(rightChild < size() && compare(rightChild, biggerChild) > 0)
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

  public int size(){
    return list.size();
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
    return size() == 0;
  }

  private int last(){
    return size() - 1;
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
    return size()/2 - 1;
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

import java.util.*;

public class MaxHeapTest{
  public static void main(String[] args){
    MaxHeap maxHeap = new MaxHeap(Arrays.asList());
    System.out.println(maxHeap);
  }
}

class MaxHeap{
  List<Integer> list;

  public MaxHeap(List<Integer> list){
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

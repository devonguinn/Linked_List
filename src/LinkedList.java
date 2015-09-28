import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by Devon on 9/21/2015.
 *
 *
 *
 */
public class LinkedList<T> {

    //Private variables used to hold links
    private Node head;
    private Node tail;
    private int size;//init size var

    public LinkedList(){//Constructor declares size
        size = 0;
    }
    private class Node {
        T element;
        Node next;//next node in linked list
        Node prev;//previous node in linked list

        public Node(T element, Node next, Node prev){//Node constructor
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
    //@return: Returns size of linked list.
    public int size() {
        return size;
    }
    //checks if the list is empty
    //@return boolean (true if empty)
    public boolean isEmpty(){
        if(size==0) {
            System.out.println("Empty");
        }
        return size == 0;
    }

    //removes all elements
    //no param or reutrn
    public void clear(){
        while(!isEmpty()){
            remove();
        }
    }

    //isFull method is fairly useless for this scenario.
    //@return is always false, because our implementation can never be full.
    public boolean isFull(){
        return false;
    }

    //Adds an element at the beginning of the list.
    //@return boolean (true) if successful.)
    //@param Generic type element
    public boolean add(T element){
        Node tempNode = new Node(element,head, null);
        if(head != null){head.prev = tempNode;}
        head=tempNode;
        if(tail == null){head=tempNode;}
        size++;
        System.out.println("Added: "+element);
        return true;
    }

    //Checks if in element is in the list.
    //@return true if it finds the lement, false if it doesn't
    //@param = element you want to search for.
    //This function uses the getFrequencyOf method to determine if we have the element or not. Allowed me to write less code
    public boolean contains(T element){
        if(getFrequencyOf(element)>0)
            return true;
        else
            return false;
    }

    //finds the reference of an element and returns it.'
    //@return Node that holds the element youre searching for
    //@param element to search for
    private Node getReferenceTo(T element){
        boolean found = false;
        Node currentNode = head.next;

        while(!found && (currentNode!= null)){
            if(element.equals(currentNode.element)){
                found=true;
            }
            else
                currentNode=currentNode.next;
        }
        return currentNode;
    }
    //removes a specific element.
    //@param element to be removed
    //@return boolean, true if removed.
    //Moves object to the front then calls remove() method
    public boolean remove(T element){
        boolean result = false;
        Node tempNode = getReferenceTo(element);
        if(tempNode!=null){
            tempNode.element=head.next.element;
            remove();
            result = true;
        }
        return result;
    }

    //Checks how many times an object appears in the list
    //@return int value corresponding to the frequency of an element
    //@param Generic Element you're searching for
    public int getFrequencyOf(T element){
        Node currentNode = head.next;
        int counter = 0;
        for(int i=0;i<size()-1;i++){
            if(currentNode.element.equals(element))
                counter++;
            currentNode = currentNode.next;
        }
        return counter;
    }
    //Sends the elements to an array of Object type
    //Returns this array
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Integer[size()];
        int index = 0;
        Node currentNode = head;
        while((index < size())&&(currentNode!= null)){
            result[index] = currentNode.element;
            index++;
            currentNode=currentNode.next;
        }
        return result;
    }
    //removes the fisrt element
    //@return element that was removed
    public T remove(){
        T result = null;
        if(head.next != null){
            result = head.next.element;
            head.next=head.next.next;
            size--;
        }
        return result;
    }

    public static void main(String args[]){
        LinkedList<Integer> list = new LinkedList<Integer>();//Creating a list object. Generic data type downcasts to Int type.. I think?
        list.isEmpty();//check if list is empty.


        //Adding 5 elements to the list
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //Checking list size..
        System.out.println("Size: " + list.size());
        //Checking frequency of int value=1
        System.out.println("'1' appears " + list.getFrequencyOf(1) + " times.");
        //Checking our search method..
        System.out.println("Searching for 1 Found?: " + list.contains(1));
        //Another contains() check
        System.out.println("Searching for 3 Found?: " + list.contains(3));
        //Is our list full? NO
        System.out.println("List Full: " + list.isFull());
        //Removing two, and then checking if it was correctly removed below
        System.out.println("remove "+list.remove(1));
        System.out.println("Check if '1' was removed: " + !list.contains(1));
        //Is our list empty? NO
        list.isEmpty();
        //Another size check..should be 4 since we removed '2'
        System.out.println("Size: " + list.size());

                //Prints Array Elements, utilizes toArray method
        for(Integer myInteger : list.toArray()){
            System.out.println(myInteger);
        }

    }
}

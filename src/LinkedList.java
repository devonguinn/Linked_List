import java.util.NoSuchElementException;

/**
 * Created by devon on 9/21/2015.
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

    //Searches list for a generic T element.
    //This method was tricky..in figuring out if i was checking currentNode against an object or a reference
    //@return boolean (true if element was found)
    //@param Generic T element
    public boolean contains(T element){
        System.out.printf("Searching for element: %s%n", element.toString());
        boolean found = false;
        Node currentNode = head.next;
        while(currentNode.element != element){
            if(currentNode.next.element.equals(element))
                return true;
            currentNode = currentNode.next;
        }
        return found;
    }

    //Removes a specigic element, and re-assigns links accordingly.
    //@return generic T of object removed
    //@param Generic T of object to be removed
    public T remove(T element){
        Node currentNode = head;

        while(!currentNode.element.equals(element)){
            System.out.println("Current: "+currentNode.element);//debugging: remove later
            if(currentNode.element.equals(element)) {
                currentNode.prev = currentNode.next;
                currentNode.next = currentNode.prev;
                currentNode.prev = null;
                currentNode.next = null;
                return currentNode.element;
            }
            currentNode = currentNode.next;
        }
        return currentNode.element;
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
        System.out.println("remove "+list.remove(2));
        System.out.println("Check if '2' was removed: " + list.contains(2));
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

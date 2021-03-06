
//import TreeNode;

// CUSTOM QUEUE IS IMPLEMENTED
public class myQueue {
    Node front, last;

    // CONSTRUCTOR
    public myQueue(){
        front = null;
        last = null;
    }

    // CHECKS IF QUEUE IS EMPTY
    public boolean isEmpty(){
        return front == null;
    }

    // GETS FIRST ELEMENT OF QUEUE
    public Element getFront(){
        return front.data;
    }

    // THIS METHOD GETS THE SIZE OF QUEUE
    public int size(){
        int count = 0;
        if (!isEmpty()){
            Node temp = front;

            // ITERATES TILL END
            while(temp != null){
                count++;
                temp = temp.next;
            }
        }
        return count;
    }

    // ADDS NODE TO THE QUEUE
    public void enqueue(Element node){
        Node newNode = new Node();
        newNode.data = node;

        // CHECKS IF QUEUE IS EMPTY
        if (isEmpty()){
            newNode.next = null;
            newNode.previous = null;

            front = newNode;
            last = newNode;
        }

        // IF ONLY ONE ELEMENT IS ADDED TO THE QUEUE
        else if (front.next == null){
            front.next = newNode;
            newNode.previous = front;
            newNode.next = null;

            last = newNode;
        }

        // OF THERE ARE ELEMENTS IN THE QUEUE
        else{
            last.next = newNode;
            newNode.previous = last;
            newNode.next = null;

            last = newNode;
        }
    }

    // REMOVES THE FIRST NODE FROM THE QUEUE
    public void dequeue(){

        // WILL WORK IF QUEUE IS NOT EMPTY
        if (!isEmpty()){
            if (front.next == null){
                front = null;
                last = null;
            }
            else{
                front = front.next;
                front.previous = null;
            }
        }
    }

}


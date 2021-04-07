import java.io.*;

// TERNARY TREE
public class Tree {
    // ROOT IS DEFINED
    Element root;

    // CONSTRUCTOR
    public Tree(){

        // ROOT
        root = new Element("root");
        root.left = root.right = root.middle = null;
    }


    public int treeHeight(Element temp){
        int height = 0;
        if (temp != null){
            height = 1 + Math.max(
                    Math.max(
                            treeHeight(temp.left),
                            treeHeight(temp.middle)
                    ),
                    treeHeight(temp.right)
            );
        }
        return height;
    }

    // ADDS NEXT LEVEL OF TREE TO THE QUEUE
    public myQueue enqueueNextLevel(myQueue queue, Element front){
        if (front.left != null)
            queue.enqueue(front.left);

        if (front.middle != null)
            queue.enqueue(front.middle);

        if (front.right != null)
            queue.enqueue(front.right);

        return queue;
    }


    // SEARCHES THE QUEUE FOR REQUIRED NODE
    public Element findInQueue(String a, boolean add) {
        if (root == null)
            return null;

        // QUEUE VARIABLE
        myQueue queue = new myQueue();
        // ROOT ADDED
        queue.enqueue(root);

        Element temp = null;

        // LOOPS UNTIL ALL THE ITEMS ARE REMOVED
        while (!queue.isEmpty()){
            int nodeCount = queue.size();

            // WHILE QUEUE IS NOT EMPTY
            while (nodeCount > 0){
                Element front = queue.getFront();

                // this is for deletion
                if (!add){

                    // CURRENT NODE IS EQUAL TO THE NDE REQUIRED
                    if (front.data.matches(a)) {
                        temp = front;
                        for (int i = nodeCount; i > 0; i--){
                            front = queue.getFront();
                            queue.dequeue();
                            queue = enqueueNextLevel(queue, front);
                        }
                        break;
                    }
                }

                if (front.data.matches(a))
                    temp = front;

                // REMOVING THE TESTED NODE
                queue.dequeue();

                // load the children to the queue

                queue = enqueueNextLevel(queue, front);

                nodeCount--;
            }
        }
        return temp;
    }



    // addition methods
    // THIS METHOD ADDS A MIDDLE CHILD TO THE NODE
    public void AddL(String a, String b) throws IOException {
        if (a.isBlank() || b.isBlank())
        {
            mySystem.bw.write("Input error.");

            return;
        }

        // GETTING THE REQUIRED NODE
        Element temp = findInQueue(a, true);

        // ADDING CHILD IF LOCATION IS FREE
        if (temp != null){
            if (temp.left == null)
            {
                if (b.startsWith("$"))
                {
                    temp.left = new Element(b.substring(1));
                }
                else {
                    temp.left = new Element(b);
                }
            }
            else{
                if (b.startsWith("$"))
                    temp.left.data = b.substring(1);
                else
                    mySystem.bw.write("Add operation not possible.\n");
            }
        }
    }
    // THIS METHOD ADDS A MIDDLE CHILD TO THE NODE
    public void AddM(String a, String b) throws IOException {
        if (a.isBlank() || b.isBlank())
        {
            mySystem.bw.write("Input error.\n");

            return;
        }
        Element temp = findInQueue(a, true);
        if (temp != null){
            if (temp.middle == null)
            {
                if (b.startsWith("$"))
                {
                    temp.middle = new Element(b.substring(1));
                }
                else {
                    temp.middle = new Element(b);
                }
            }
            else{
                if (b.startsWith("$"))
                    temp.middle.data = b.substring(1);
                else
                    mySystem.bw.write("Add operation not possible.\n");
            }
        }
    }

    // THIS METHOD ADDS A RIGHT CHILD TO THE NODE
    public void AddR(String a, String b) throws IOException {
        if (a.isBlank() || b.isBlank())
        {
            mySystem.bw.write("Input error.");

            return;
        }
        Element temp = findInQueue(a, true);
        if (temp != null){
            if (temp.right == null)
            {
                if (b.startsWith("$"))
                {
                    temp.right = new Element(b.substring(1));
                }
                else {
                    temp.right = new Element(b);
                }
            }
            else
            {
                if (b.startsWith("$"))
                {
                    temp.right.data = b.substring(1);
                }
                else
                    mySystem.bw.write("Add operation not possible.\n");
            }
        }

    }


    // deletion methods
    // THIS METHOD DELETES THE LEFT CHILD OF THE NODE
    public void DelL(String a) throws IOException {
        if(a.isBlank())
        {
            mySystem.bw.write("Input error.");
            return;
        }
        Element temp = findInQueue( a, false);
        if (temp != null){
            if (temp.left != null){
                temp.left = null;
            }
        }
    }

    // THIS METHOD DELETES THE MIDDLE CHILD OF THE NODE
    public void DelM(String a) throws IOException {
        if(a.isBlank())
        {
            mySystem.bw.write("Input error.");
            return;
        }
        Element temp = findInQueue( a, false);
        if (temp != null){
            if (temp.middle != null)
                temp.middle = null;
        }
    }

    // THIS METHOD DELETES THE RIGHT CHILD OF THE NODE
    public void DelR(String a) throws IOException {
        if(a.isBlank())
        {
            mySystem.bw.write("Input error.");
            return;
        }
        Element temp = findInQueue( a, false);
        if (temp != null){
            if (temp.right != null)
                temp.right = null;
        }
    }

    // exchange method
    // THIS METHOD EXCHANGES THE NODES PROVIDED
    public void exchange(String a, String b) throws IOException {
        myQueue queue = new myQueue();
        queue.enqueue(root);

        if (a.isBlank() || b.isBlank())
        {
            mySystem.bw.write("Input error.");
            return;
        }
        Element temp = null;
        while(!queue.isEmpty()){
            for (int nodeCount = queue.size(); nodeCount > 0; nodeCount--){
                Element front = queue.getFront();
                if (front.data.matches(a)){

                    //OVERWITING IF $ IS INVOLVED
                    if (b.startsWith("$"))
                        front.data += b.substring(1);
                    else
                        front.data = b;
                }
                queue.dequeue();
                queue = enqueueNextLevel(queue, front);
            }
        }
    }

    // THIS METHOD PRINTS THE OUTPUT TO THE FILE
    public void print() throws IOException {
        myQueue queue = new myQueue();

        queue.enqueue(root);

        Element temp = null;
        int i = 0;
        int height = treeHeight(root);
        while (!queue.isEmpty()){
            i++;
            for (int nodeCount = queue.size(); nodeCount > 0; nodeCount--){
                Element front = queue.getFront();
                if(i <= height & nodeCount -1 != 0) {
                    mySystem.bw.write(front.data + " ; ");
                }

                else {
                    mySystem.bw.write(front.data);
                }
                queue.dequeue();
                // load the children to the queue
                queue = enqueueNextLevel(queue, front);
            }
            mySystem.bw.write("\n");

        }
    }


}


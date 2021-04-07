
//import Queue;
//import QueueNode;
import java.io.*;

public class Tree {
    // ROOT IS DEFINED
    TreeNode root;

    // CONSTRUCTOR
    public Tree(){

        // ROOT IS INITIATED
        root = new TreeNode("root");
        root.left = root.right = root.middle = null;
    }
    // utility function
    public int height(TreeNode temp){
        int height = 0;
        if (temp != null){
            height = 1 + Math.max(
                    Math.max(
                            height(temp.left),
                            height(temp.middle)
                    ),
                    height(temp.right)
            );
        }
        return height;
    }

    // ADDS NEXT LEVEL OF TREE TO THE QUEUE
    public CustomQueue addChildrenToQueue(CustomQueue queue, TreeNode front){
        if (front.left != null)
            queue.enqueue(front.left);

        if (front.middle != null)
            queue.enqueue(front.middle);

        if (front.right != null)
            queue.enqueue(front.right);

        return queue;
    }


    // SEARCHES THE QUEUE FOR REQUIRED NODE
    public TreeNode transverseSearch(String a, boolean add) {
        if (root == null)
            return null;

        // QUEUE VARIABLE IS INITIATED
        CustomQueue queue = new CustomQueue();
        // ROOT IS ADDED TO THE QUEUE
        queue.enqueue(root);

        // TEMPORARY NODE
        TreeNode temp = null;

        // LOOPS THROUGH THE QUEUE UNTIL ALL THE ITEMS ARE REMOVED
        while (!queue.isEmpty()){
            int nodeCount = queue.size();

            // WHILE QUEUE IS NOT EMPTY
            while (nodeCount > 0){
                TreeNode front = queue.getFront();

                // this is for deletion search purpose
                if (!add){

                    // CURRENT NODE IS EQUAL TO THE NDE REQUIRED
                    if (front.data.matches(a)) {
                        temp = front;
                        for (int i = nodeCount; i > 0; i--){
                            front = queue.getFront();
                            queue.dequeue();
                            queue = addChildrenToQueue(queue, front);
                        }
                        break;
                    }
                }

                if (front.data.matches(a))
                    temp = front;

                // REMOVING THE TESTED NODE
                queue.dequeue();

                // load the children to the queue

                queue = addChildrenToQueue(queue, front);

                nodeCount--;
            }
        }
        return temp;
    }



    // addition methods
    // THIS METHOD ADDS A LEFT CHILD TO THE NODE
    public void AddL(String a, String b) throws IOException {
        if (a == "" || b == "")
        {
            mySystem.bWriter.write("Input error.");

            return;
        }

        // GETTING THE REQUIRED NODE
        TreeNode temp = transverseSearch(a, true);
        if (temp != null){

            // ADDING CHILD IF LOCATION IS FREE
            if (temp.left == null)
                temp.left = new TreeNode(b);
            else{

                // OVERWRITING IF $ IS INVOLVED
                if (b.startsWith("$"))
                    temp.left.data = b.substring(1);
                else
                    mySystem.bWriter.write("Add operation not possible.\n");
            }
        }

    }

    // THIS METHOD ADDS A MIDDLE CHILD TO THE NODE
    public void AddM(String a, String b) throws IOException {
        if (a == "" || b == "")
        {
            mySystem.bWriter.write("Input error.");

            return;
        }
        TreeNode temp = transverseSearch(a, true);
        if (temp != null){
            if (temp.middle == null)
                temp.middle = new TreeNode(b);
            else{
                if (b.startsWith("$"))
                    temp.middle.data = b.substring(1);
                else
                    mySystem.bWriter.write("Add operation not possible.\n");
            }
        }
    }

    // THIS METHOD ADDS A RIGHT CHILD TO THE NODE
    public void AddR(String a, String b) throws IOException {
        if (a == "" || b == "")
        {
            mySystem.bWriter.write("Input error.");

            return;
        }
        TreeNode temp = transverseSearch(a, true);
        if (temp != null){
            if (temp.right == null)
                temp.right = new TreeNode(b);
            else{
                if (b.startsWith("$"))
                    temp.right.data = b.substring(1);
                else
                    mySystem.bWriter.write("Add operation not possible.\n");
            }
        }

    }


    // deletion methods
    // THIS METHOD DELETES THE LEFT CHILD OF THE NODE
    public void DelL(String a) throws IOException {
        if(a == "")
        {
            mySystem.bWriter.write("Input error.");

            return;
        }
        TreeNode temp = transverseSearch( a, false);
        if (temp != null){
            if (temp.left != null){
                temp.left = null;
            }
        }
    }

    // THIS METHOD DELETES THE MIDDLE CHILD OF THE NODE
    public void DelM(String a) throws IOException {
        if(a == "")
        {
            mySystem.bWriter.write("Input error.");
            return;
        }
        TreeNode temp = transverseSearch( a, false);
        if (temp != null){
            if (temp.middle != null)
                temp.middle = null;
        }
    }

    // THIS METHOD DELETES THE RIGHT CHILD OF THE NODE
    public void DelR(String a) throws IOException {
        if(a == "")
        {
            mySystem.bWriter.write("Input error.");
            return;
        }
        TreeNode temp = transverseSearch( a, false);
        if (temp != null){
            if (temp.right != null)
                temp.right = null;
        }
    }

    // exchange method
    // THIS METHOD EXCHANGES THE NODES PROVIDED
    public void exchange(String a, String b) throws IOException {
        CustomQueue queue = new CustomQueue();
        queue.enqueue(root);

        if (a == "" || b == "")
        {
            mySystem.bWriter.write("Input error.");
            return;
        }
        TreeNode temp = null;
        while(!queue.isEmpty()){
            for (int nodeCount = queue.size(); nodeCount > 0; nodeCount--){
                TreeNode front = queue.getFront();
                if (front.data.matches(a)){

                    //OVERWITING IF $ IS INVOLVED
                    if (b.startsWith("$"))
                        front.data += b.substring(1);
                    else
                        front.data = b;
                }
                queue.dequeue();
                queue = addChildrenToQueue(queue, front);
            }
        }
    }

    // THIS METHOD PRINTS THE OUTPUT TO THE FILE
    public void print() throws IOException {
        CustomQueue queue = new CustomQueue();

        queue.enqueue(root);

        TreeNode temp = null;
        int i = 0;
        int height = height(root);
        while (!queue.isEmpty()){
            i++;
            for (int nodeCount = queue.size(); nodeCount > 0; nodeCount--){
                TreeNode front = queue.getFront();
                if(i <= height & nodeCount -1 != 0)
                    mySystem.bWriter.write(front.data + " ; ");

                else {
                    mySystem.bWriter.write(front.data);
                }
                queue.dequeue();
                // load the children to the queue
                queue = addChildrenToQueue(queue, front);
            }

            mySystem.bWriter.write("\n");
        }
    }


}


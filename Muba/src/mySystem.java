

//import Tree.*;

import java.io.*;

public class mySystem {

    public static BufferedWriter bw;
    static FileWriter fw;


    // This is the main method
    public static void main(String[] args) throws IOException {

        // These will be the input and output file's names.
        String inputfile = args[0];
        String outputfile = args[1];
        // These are the write instances, used to write into the output file
        fw = new FileWriter(outputfile);
        bw = new BufferedWriter(fw);

        // This will open the input file to read line by line
        // any error will be caught using try catch
        try(BufferedReader readFromFile = new BufferedReader(new FileReader(new File(inputfile)))){

            // Initiated a tree to store the data
            Tree myTree = new Tree();
            String line;
            String[] strArray;

            // This loop reads the lines of the input file till the end of the file
            while ((line = readFromFile.readLine()) != null){

                // Checking for white space
                if (!checkSpace(line)){


                    // Spliting the line into two parts using ( to extract operation and operators
                    strArray = line.split("\\(");
                    // for function
                    // first part will be operator
                    char[] operands;
                    String operation = strArray[0];

                    try{
                        operands = strArray[1].toCharArray();
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        continue;
                    }
                    String a = "";
                    String b = "";

                    // for a
                    if (operation.startsWith("Del"))
                        if (operands.length == 2)
                            continue;
                    for (int j = 0;  j < operands.length - 1 ; j++)
                    {
                        // To not add the last part of the string i.e., )
                        if (j != operands.length) {

                            // to add the , to the first part until the last comma is faced
                            if (operands[j] == ',' && !(operands[j + 1] == ',')) {
                                break;
                            }
                            // Concatenating the first part of operands
                            a += operands[j];
                        }
                    }
                    // Every character after the first operand will be considered second operand
                    // for b
                    for(int j = a.length() + 1; j < operands.length - 1; j++)
                        b += operands[j];
                    // If operation is Add left
                    if (operation.matches("AddL"))
                    {
                        myTree.AddL(a,b);
                    }
                    // If operation is Add Middle
                    else if (operation.matches("AddM"))
                    {
                        myTree.AddM(a,b);
                    }
                    // If operation is Add Right
                    else if (operation.matches("AddR"))
                    {
                        myTree.AddR(a,b);
                    }
                    // If operation is Delete left
                    else if (operation.matches("DelL"))
                    {
                        myTree.DelL(a);
                    }
                    // If operation is Delete Middle
                    else if (operation.matches("DelM"))
                    {
                        myTree.DelM(a);
                    }
                    // If operation is Delete Right
                    else if (operation.matches("DelR"))
                    {
                        myTree.DelR(a);
                    }
                    // If operation is Print
                    else if (operation.matches("Print"))
                    {
                        myTree.print();
                    }
                    // If operation is Exchange
                    else if (operation.matches("Exchange"))
                    {
                        myTree.exchange(a,b);
                    }
                    // If the operation is not a known operation
                    else
                    {
                        bw.write("Input error.");
                    }
                }
                // IF THE INPUT FILE CANNOT BE ACCESSED
                else{
                    bw.write("Input error.");
                }
            }
        }
        // ERROR WILL BE WRITTEN TO THE FILE
        catch (IOException e){
            bw.write("File "+ inputfile + " not found");

        }

        // CLOSING THE WRITER
        bw.flush();
        bw.close();
    } // This method searches the string for any white spaces

    public static boolean checkSpace(String line) {
        for (char character : line.toCharArray()) {
            if (character == ' ')
                return true;
        }
        return false;
    }
}

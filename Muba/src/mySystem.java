import java.io.*;

public class mySystem {

    public static BufferedWriter bWriter;
    static FileWriter fWriter;

    // This method searches the string for any white spaces
    public static boolean hasWhiteSpace(String line) {
        for (char character : line.toCharArray()) {
            if (character == ' ')
                return true;
        }
        return false;
    }
    // This is the main method
    public static void main(String[] args) throws IOException {
        // This will be the input file's name.
        String in = args[0];
        // This will be the output file's name
        String file = args[1];
        // These are the write instances, used to write into the output file
        fWriter = new FileWriter(file);
        bWriter = new BufferedWriter(fWriter);

        // This will open the input file to read line by line
        // any error will be caught using try catch
        try(BufferedReader bReader = new BufferedReader(new FileReader(new File(in)))){

        // Initiated a tree to store the data
            Tree ternaryTree = new Tree();
            String line;
            String[] arrOfStr;

        // This loop reads the lines of the input file till the end of the file
            while ((line = bReader.readLine()) != null){

                // Checking for white space
                if (!hasWhiteSpace(line)){


                    // Spliting the line into two parts using ( to extract operation and operators
                    arrOfStr = line.split("\\(");
                    // for function
                    // first part will be operator
                    char[] arr;
                    String operation = arrOfStr[0];

                    try{
                        arr = arrOfStr[1].toCharArray();
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        continue;
                    }
                    String a = "";
                    String b = "";
                    // for a
                    for (int j = 0;  j < arr.length - 1 ; j++)
                    {
                        // To not add the last part of the string i.e., )
                        if (j != arr.length) {

                            // to add the , to the first part until the last comma is faced
                            if (arr[j] == ',' && !(arr[j + 1] == ',')) {
                                break;
                            }
                            // Concatenating the first part of operands
                            a += arr[j];
                        }
                    }

                    // Every character after the first operand will be considered second operand
                    // for b
                    for(int j = a.length() + 1; j < arr.length - 1; j++)
                        b += arr[j];

                    // If operation is Add left
                    if (operation.matches("AddL"))
                    {
                        ternaryTree.AddL(a,b);
                    }
                    // If operation is Add Middle
                    else if (operation.matches("AddM"))
                    {
                        ternaryTree.AddM(a,b);
                    }
                    // If operation is Add Right
                    else if (operation.matches("AddR"))
                    {
                        ternaryTree.AddR(a,b);
                    }
                    // If operation is Delete left
                    else if (operation.matches("DelL"))
                    {
                        ternaryTree.DelL(a);
                    }
                    // If operation is Delete Middle
                    else if (operation.matches("DelM"))
                    {
                        ternaryTree.DelM(a);
                    }
                    // If operation is Delete Right
                    else if (operation.matches("DelR"))
                    {
                        ternaryTree.DelR(a);
                    }
                    // If operation is Print
                    else if (operation.matches("Print"))
                    {
                        ternaryTree.print();
                    }
                    // If operation is Exchange
                    else if (operation.matches("Exchange"))
                    {
                        ternaryTree.exchange(a,b);
                    }
                    // If the operation is not a known operation
                    else
                    {
                        bWriter.write("Input error.");
                    }
                }
                // IF THE INPUT FILE CANNOT BE ACCESSED
                else{
                    bWriter.write("Input error.");
                }
            }
        }
        // ERROR WILL BE WRITTEN TO THE FILE
        catch (IOException e){
            bWriter.write("File "+ in + " does not exist");

        }
        // FLUSHING THE CONTENT OF THE BUFFER WRITER ON TO THE FILE
        bWriter.flush();
        bWriter.close();
    }
}

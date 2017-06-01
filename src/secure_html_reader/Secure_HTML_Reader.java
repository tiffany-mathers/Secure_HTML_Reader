/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure_html_reader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Title: JavaURL
 * Author Tiffany Mathers
 * Class: SDEV 325
 * Purpose: Mitigates CWE-311 : missing encryption of sensitive data
 */
public class Secure_HTML_Reader {

 /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        // create variables
        String userInput;
        StringBuilder stringBuilder;
        
        // display welcome message and prompt user to input URL to read
        System.out.println("******** Welcome to the HTML Reader! ********");
        System.out.println("Please enter the any secure URL (HTTPS) you wish to read,");
        userInput = readEntry("starting with www...  : ");
         
        // test userInput
        System.out.println("You entered: " + userInput);
        
        // try clause - connects to URL and outputs each line of HTML to the screen
        try {
            // create URL object
            // Below code has been fixed. The application now only accepts websites
            // with a secure encrypted URL
            // enteredURL = new URL(userInput);
            URL enteredURL = new URL("https://" + userInput);
            // test enteredURL
            System.out.println(enteredURL);
            // connect to URL
            HttpURLConnection connection = (HttpURLConnection) enteredURL.openConnection();
            connection.setRequestMethod("GET");
            
            // reads each inputLine (HTML) and displays it
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            System.out.println(stringBuilder.toString());          
        }
        catch (IOException e) {
            System.out.println("URL was not found.");
        }        
    }
    
    // method for sanitizing user input
    static String readEntry(String prompt) {
        try
        {
          StringBuilder buffer = new StringBuilder();
          System.out.print(prompt);
          System.out.flush();
          int c = System.in.read();
          while (c != '\n' && c != -1)
          {
            buffer.append((char)c);
            c = System.in.read();
          }
          return buffer.toString().trim();
        }
        catch(IOException e)
        {
          return "";
        }
    }  
}

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Set;
import java.util.HashSet;

public class A11 
{
    //check whether the char is a letter
    static boolean isLetter(int character)
    {
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }

    //check whether the char is a letter or digit
    static boolean isLetterOrDigit(int character)
    {
        return isLetter(character) || (character >= '0' && character <= '9');
    }
    
    public static Set<String> getIdentifiers(String filename) throws Exception
    {
        String[] keywordsArray = {"IF", "WRITE" , "READ" , "RETURN" , "BEGIN" ,
        "END" , "MAIN" , "INT" , "REAL"};
        Set<String> keywords = new HashSet<>();
        Set<String> identifiers = new HashSet<>();

        for (String s : keywordsArray) 
        {
            keywords.add(s);    
        }
        String state = "INIT";      //initial stage

        StringBuilder code = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        
        while ((line = br.readLine()) != null) 
        {
            code = code.append(line + "\n");    
        }   //read the text line by line
        code = code.append("$"); //add a special symbol to indicate the end of file 

        String token = "";

        for (int i = 0; i < code.length(); i++) 
        {
            // look at the characters one by one
            char nextChar = code.charAt(i);

            if (state.equals("INIT")) 
            {
                if (nextChar == '\"') 
                {
                    //token += code.charAt(i+1); 
                    state = "INIT"; 
                }
                if (isLetter(nextChar)) 
                {
                    state = "ID";   //switch to ID stage
                    token += nextChar;  
                }   // ignore every thing if it is not a letter
            }
            else if (state.equals("INIT")) 
            {
                if (nextChar == '\"') 
                    state = "ID";    
            }
            else if (state.equals("ID")) 
            {
                if (isLetterOrDigit(nextChar))  // take letter or digit if it is in ID state
                    token += nextChar;    
                else
                {
                    if (!keywords.contains(token))  // if token isnt a keywork it's an identifier
                    {
                        identifiers.add(token);
                        token = "";
                        state = "INIT";                            
                    }                    
                }
            }              
        }
        return identifiers;
    }

    public static void main(String[] args) throws Exception
    {
        Set<String> IDs = getIdentifiers("A11_tiny.txt");
        for (String id : IDs) 
        {
            System.out.println(id);    
        }
    }
}
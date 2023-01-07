import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;

public class A12 
{
    public static Set<String> getIdRegex(String filename) throws Exception
    {
        String[] keywordsArray = {"IF", "WRITE" , "READ" , "RETURN" , "BEGIN" ,
        "END" , "MAIN" , "INT" , "REAL"};
        Set<String> keywords = new HashSet<>();
        Set<String> identifiers = new HashSet<>();

        for (String s : keywordsArray) 
        {
            keywords.add(s);    
        }

        FileReader read = new FileReader(filename);
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        Pattern idPattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
        Pattern quotedStringPattern = Pattern.compile("\"[a-zA-Z][a-zA-Z0-9]*\"");
        
        while ((line = br.readLine()) != null) 
        {
            Matcher m_quoMatcher = quotedStringPattern.matcher(line);
            String lineWithoutQuotedString = m_quoMatcher.replaceAll("");
            Matcher m = idPattern.matcher(lineWithoutQuotedString);
            
            while (m.find()) 
            {
                String id = line.substring(m.start(), m.end());
                if (!keywords.contains(id)) 
                {
                    identifiers.add(id);    
                }
            }
        }
        return identifiers;
    }

    public static void main(String[] args) throws Exception
    {
        Set<String> ids = getIdRegex("A12_tiny.txt");
        for (String id : ids) 
        {
            System.out.println(id);    
        }
    }
}

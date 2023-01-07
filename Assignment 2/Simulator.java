public class Simulator 
{   
   public static boolean run(DFA dfa, String input) 
   {
      int i = 0;
      String currentState = dfa.startState;
      char[] charArr = input.toCharArray();
      char currentChar = charArr[i];

      while (i < charArr.length()) 
      {
         currentState = dfa.transitions.get(currentState + "_" + currentChar); 
         i++;
         currentChar = charArr[i];          
      } 
      return dfa.finalStates.contains(currentState);
   }    
}
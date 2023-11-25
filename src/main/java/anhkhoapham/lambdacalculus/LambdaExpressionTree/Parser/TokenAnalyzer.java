/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Parser;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Khoapa
 */
public class TokenAnalyzer {
    
    /**
     * Check if a variable is of the form "/name".
     * @param token
     * @return 
     */
    public static boolean isParameter(String token)
    {
        return token != null && token.length() >= 2 && token.charAt(0) == '/';
    }
    
    /**
     * Usage: When encountering an openBracket, insert all tokens after the that token as the argument for this method.
     * @param tokens
     * @param openBracket
     * @param endBracket
     * @return The index of the ending round bracket.
     * @throws IllegalArgumentException 
     */
    public static int getBracketEndOffset(List<String> tokens, String openBracket, String endBracket) throws IllegalArgumentException
    {
        int parenthesisCount = 1;
        
        int index = 1;
        
        for(; index < tokens.size(); index++)
        {          
            if (tokens.get(index).equals(openBracket)) {
                parenthesisCount++;
            } else if (tokens.get(index).equals(endBracket)) {
                parenthesisCount--;
            }

            if (parenthesisCount == 0)
                return index;
        }
        
        throw new IllegalArgumentException("Failed to find end bracket: " + Arrays.toString(tokens.toArray()));
    }
    
    @Deprecated
    public static int getCurlyBracketEnd(List<String> tokens) throws IllegalArgumentException
    {
        int index = 1;
        
        for(; index < tokens.size(); index++)
        {          
            if (tokens.get(index).equals("}")) 

                return index;
        }
        
        throw new IllegalArgumentException(Arrays.toString(tokens.toArray()));
    }
    
    public static String getParameter(Iterable<String> tokens, Set<String> args) throws IllegalArgumentException {

        String process = "GetArgs";

        String tokenName = "";
        
        int index = 0;

        boolean breakFlag = false;

        for (var token : tokens) {
            switch (process) {
                case "GetArgs" -> {
                        handleArgs(token, args);
                        
                        tokenName = token.substring(1);
                        
                        process = "LambdaArrow"; 
                        
                        
                        breakFlag = true;
                }

                /**
                case "LambdaArrow" -> {
                    if ("->".equals(token)) {
                        breakFlag = true;
                    } else {
                        throw new IllegalArgumentException("Syntax error.");
                    }
                }
                **/
            }

            if (breakFlag) {
                break;
            }

            index++;
        }

        return tokenName;
    }
    
    /**
     * 
     * @param token
     * @param argsStack
     */
    public static void handleArgs(String token, Set<String> argsStack) {
        
        if (token == null || token.length() == 0) throw new IllegalArgumentException();
        
        if (token.charAt(0) == '/') {
            if (token.length() < 2) {
                throw new IllegalArgumentException(token);
            }

            if(!token.equals("/_"))
                if(argsStack.add(token.substring(1)) == false)
                    throw new IllegalArgumentException(token + " already exists.");

            return;
        }

        if (!argsStack.contains(token)) throw new IllegalArgumentException(token + " not found.");      
    }
    
        private TokenAnalyzer(){}
}

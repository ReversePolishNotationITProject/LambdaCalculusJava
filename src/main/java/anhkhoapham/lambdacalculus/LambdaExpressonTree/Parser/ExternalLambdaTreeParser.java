/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;

/**
 * Receives a string from a LambdaExpressionTokenHandler, then handles that string to return an external tree.
 * @author Khoapa
 */
public interface ExternalLambdaTreeParser {
    
    /**
     * @param input the value of input
     * @return 
     * @throws IllegalArgumentException
     */
    LambdaTermRoot parse(String input) throws IllegalArgumentException;
    
    /**
     * If a variable was not locally declared, a LambdaExpressionParser will call this method to obtain the variable globally.
     * @param varName
     * @return 
     */
    LambdaTermRoot getMissing(String varName) throws IllegalArgumentException;
}

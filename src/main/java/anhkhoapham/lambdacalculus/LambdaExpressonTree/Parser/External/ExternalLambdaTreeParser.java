/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser.External;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;

/**
 * Receives a string from a LambdaExpressionTokenHandler, then handles that string to return an external tree.
 * @author Khoapa
 */
public interface ExternalLambdaTreeParser {
    
    /**
     * @param info the value of input
     * @return 
     * @throws IllegalArgumentException
     */
    default LambdaTermRoot parse(ExternalLambdaInfo info) throws IllegalArgumentException
    {
        return parse(info.inputString());
    }
    
    /**
     * @param input the value of input
     * @return 
     * @throws IllegalArgumentException
     */
    LambdaTermRoot parse(String input) throws IllegalArgumentException;
    
}

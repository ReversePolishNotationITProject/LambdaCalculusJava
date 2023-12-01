/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Parser;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Parser.External.PN;
import java.util.List;

/**
 *
 * @author Khoapa
 */
public interface LambdaExpressionTokenHandler {
    
    /**
     * Get a LambdaTermRoot from a List of tokens with an agreed upon syntax.
     * Forwards string within curly brackets to a LambdaExpressionTreePorterParser.
     * @param wrappedTokens
     * @return
     * @throws IllegalArgumentException
     * @throws UnsupportedOperationException 
     */
    default LambdaTermRoot parse(PN wrappedTokens) throws IllegalArgumentException, UnsupportedOperationException
    {
        return parse(wrappedTokens.tokens());
    }
    
    /**
     * Use if the input tokens are obvious and there is no confusion.
     * @param tokens
     * @return
     * @throws IllegalArgumentException
     * @throws UnsupportedOperationException
     */
    LambdaTermRoot parse(List<String> tokens) throws IllegalArgumentException, UnsupportedOperationException;
}

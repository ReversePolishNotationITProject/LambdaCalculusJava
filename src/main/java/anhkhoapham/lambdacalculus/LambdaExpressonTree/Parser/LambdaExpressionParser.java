/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser.External.PNString;

/**
 * Prefer LambdaExpressionTokenHandler.
 * @author Khoapa
 */
public interface LambdaExpressionParser {

    /**
     * Consider using this overload if avoiding confusion is needed.
     * @param wrappedInput
     * @return
     * @throws IllegalArgumentException
     */
    default LambdaTermRoot parse(PNString wrappedInput) throws IllegalArgumentException
    {
        return parse(wrappedInput.inputString());
    }

    /**
     * Consider using this overload if the input string is obvious.
     * Avoid using in complex code.
     * @param input
     * @return
     * @throws IllegalArgumentException
     */
    LambdaTermRoot parse(String input) throws IllegalArgumentException;
}

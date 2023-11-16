/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;

/**
 * Prefer LambdaExpressionTokenHandler.
 * @author Khoapa
 */
public interface LambdaExpressionParser {

    /**
     * Consider using this overload if the input string is obvious.
     * Avoid using in complex code.
     * @param input
     * @return
     * @throws IllegalArgumentException
     */
    LambdaTermRoot parse(String input) throws IllegalArgumentException;
}

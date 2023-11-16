/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import java.util.List;

/**
 *
 * @author Khoapa
 */
public interface LambdaExpressionTokenHandler {
     
    /**
     * Use if the input tokens are obvious and there is no confusion.
     * @param tokens
     * @return
     * @throws IllegalArgumentException
     * @throws UnsupportedOperationException
     */
    LambdaTermRoot parse(List<String> tokens) throws IllegalArgumentException, UnsupportedOperationException;
}

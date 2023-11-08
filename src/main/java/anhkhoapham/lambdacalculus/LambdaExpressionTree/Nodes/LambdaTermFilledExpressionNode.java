/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;

/**
 *
 * @author Khoapa
 */
public interface LambdaTermFilledExpressionNode extends LambdaTermExpressionNode {
    
    public LambdaTermRoot substitutedRoot();
    
}

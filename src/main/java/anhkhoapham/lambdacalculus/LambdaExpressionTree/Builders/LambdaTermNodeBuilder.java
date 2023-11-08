/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Builders;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import java.util.Collection;

/**
 *
 * @author Khoapa
 */
public interface LambdaTermNodeBuilder {
    
    LambdaTermRoot buildRoot(String name, LambdaTermExpressionNode topNode);
    
    LambdaTermExpressionNode buildNamedNode(String name, Collection<LambdaTermExpressionNode> children);
    
    LambdaTermExpressionNode buildOpenNode(LambdaTermRoot substitutedRoot, Collection<LambdaTermExpressionNode> children);
    
    LambdaTermExpressionNode buildClosedNode(LambdaTermRoot substitutedRoot, Collection<LambdaTermExpressionNode> children);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.treeanditerationlibrary.ReadOnlyTree.ReadOnlyTreeNode;
import java.util.Collection;

/**
 *
 * @author Khoapa
 */
public interface LambdaTermExpressionNode extends ReadOnlyTreeNode<LambdaTermExpressionNode> {
            
    /**
     * @param visitingRoot
     * @param substitutedName
     * @return 
     */
    
    LambdaTermExpressionNode accept(LambdaTermRoot visitingRoot, String substitutedName);    
    
    /**
     * If isParameterDeclaration() of the node following this return true, promotes to a root.
     * Else, invoke the entire tree.
     * @throws UnsupportedOperationException : A root was not provided to this node.
     *
     * @return LambdaTermRoot
     */
    LambdaTermRoot invoke() throws UnsupportedOperationException;
    
    LambdaTermExpressionNode setChildren(Collection<LambdaTermExpressionNode> children);
}

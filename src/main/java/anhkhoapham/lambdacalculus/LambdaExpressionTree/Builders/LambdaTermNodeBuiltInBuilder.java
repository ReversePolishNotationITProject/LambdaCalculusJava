/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Builders;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermNamedNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermOpenNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermClosedNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermRoundBracketNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermSquareBracketNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermUnfilledExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermEntryNode;
import java.util.Collection;

/**
 *
 * @author Khoapa
 */
public final class LambdaTermNodeBuiltInBuilder implements LambdaTermNodeBuilder {

    public static LambdaTermNodeBuiltInBuilder get() {
        return builder;
    }

    private static final LambdaTermNodeBuiltInBuilder builder = new LambdaTermNodeBuiltInBuilder();
    
    private LambdaTermNodeBuiltInBuilder() {}
    
    @Override
    public LambdaTermRoot buildRoot(String name, LambdaTermExpressionNode topNode) {
        return new LambdaTermEntryNode(name, topNode);
    }
    @Override
    public LambdaTermUnfilledExpressionNode buildNamedNode(String name, Collection<LambdaTermExpressionNode> children) {
        return new LambdaTermNamedNode(name, children);
    }

    @Override
    public LambdaTermRoundBracketNode buildOpenNode(LambdaTermRoot substitutedRoot, Collection<LambdaTermExpressionNode> children) {
        return new LambdaTermOpenNode(substitutedRoot, children);
    }

    @Override
    public LambdaTermSquareBracketNode buildClosedNode(LambdaTermRoot substitutedRoot, Collection<LambdaTermExpressionNode> children) {
        return new LambdaTermClosedNode(substitutedRoot, children);
    }
}

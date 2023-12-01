/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author Khoapa
 */
public final class LambdaTermOpenNode extends LambdaTermAbstractNode implements LambdaTermRoundBracketNode {

    
    private final LambdaTermRoot substitutedRoot;
    
    public LambdaTermOpenNode(LambdaTermRoot substitutedRoot, Collection<LambdaTermExpressionNode> children)
    {
        super(children);
        
        if (substitutedRoot == null) throw new IllegalArgumentException("substitutedRoot is null.");
        
        this.substitutedRoot = substitutedRoot;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(substitutedRoot) + super.hashCode();
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LambdaTermRoundBracketNode)) {
            return false;
        }
        final LambdaTermOpenNode other = (LambdaTermOpenNode) obj;
        return Objects.equals(substitutedRoot, other.substitutedRoot) && super.equals(obj);
    }

    @Override
    public LambdaTermRoot substitutedRoot()
    {
        return substitutedRoot;
    }
    
    @Override
    public String displayName()
    {
        var buffer = new StringBuilder(50);
        
        substitutedRoot.print(buffer, "root: ", "");
        
        return "#open " + buffer;
    }

    @Override
    public LambdaTermRoot invoke() throws UnsupportedOperationException {
        var result = substitutedRoot;
        for(var node : children())
        {
            result = result.invoke(node.invoke());
        }
        
        return result;
    }

    @Override
    public LambdaTermExpressionNode setChildren(Collection<LambdaTermExpressionNode> children) {
        return new LambdaTermOpenNode(substitutedRoot, children);
    }

    @Override
    protected LambdaTermExpressionNode afterChildrenSubstitution(LambdaTermRoot visitingRoot, String substitutedName, Collection<LambdaTermExpressionNode> newChildren) {
        return new LambdaTermOpenNode(substitutedRoot.substitute(visitingRoot, substitutedName), newChildren);
    }
    
}

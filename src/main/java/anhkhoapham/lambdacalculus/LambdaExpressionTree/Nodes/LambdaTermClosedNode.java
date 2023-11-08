/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.treeanditerationlibrary.ReadOnlyTree.ReadOnlyTreeNodeExtensions;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author Khoapa
 */
public final class LambdaTermClosedNode extends LambdaTermAbstractNode implements LambdaTermFilledExpressionNode {

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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LambdaTermClosedNode other = (LambdaTermClosedNode) obj;
        return Objects.equals(substitutedRoot, other.substitutedRoot) && super.equals(obj);
    }
    
    private final LambdaTermRoot substitutedRoot;
    
    public LambdaTermClosedNode(LambdaTermRoot substitutedRoot, Collection<LambdaTermExpressionNode> children)
    {
        super(children);
        
        if (substitutedRoot == null) throw new IllegalArgumentException("substitutedRoot is null.");
        
        this.substitutedRoot = substitutedRoot;
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
        
        return "#closed " + buffer;
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
    public boolean equals(LambdaTermExpressionNode other) {
        return ReadOnlyTreeNodeExtensions.compare(this, other, (a, b) -> true);
    }

    @Override
    public LambdaTermExpressionNode setChildren(Collection<LambdaTermExpressionNode> children) {
        return new LambdaTermClosedNode(substitutedRoot, children);
    }

    @Override
    protected LambdaTermExpressionNode afterChildrenSubstitution(LambdaTermRoot visitingRoot, String substitutedName, Collection<LambdaTermExpressionNode> newChildren) {
        return new LambdaTermClosedNode(substitutedRoot, newChildren);
    }
    
}

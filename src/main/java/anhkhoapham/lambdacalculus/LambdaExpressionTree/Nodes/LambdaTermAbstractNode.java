/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.treeanditerationlibrary.Comparision.EqualityComparer;
import anhkhoapham.treeanditerationlibrary.ReadOnlyTree.Operations.TreeIterationOrderer;
import anhkhoapham.treeanditerationlibrary.ReadOnlyTree.ReadOnlyTreeNodeExtensions;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Khoapa
 */
public abstract class LambdaTermAbstractNode implements LambdaTermExpressionNode {
    private final Collection<LambdaTermExpressionNode> children;
    public LambdaTermAbstractNode(Collection<LambdaTermExpressionNode> children) {
        if (children == null) throw new IllegalArgumentException("children is null.");
        
        for(var child : children)
        {
            if (child == null) throw new IllegalArgumentException("A child is null.");
        }
        
        this.children = children;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(children);
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
        final LambdaTermAbstractNode other = (LambdaTermAbstractNode) obj;
        return ReadOnlyTreeNodeExtensions.compare(children, other.children());
    }

    
    @Override
    public LambdaTermExpressionNode accept(LambdaTermRoot visitingRoot, String substitutedName) {
        
        // Pass to children
        var newChildren = new LinkedList<LambdaTermExpressionNode>();
        
        for(var node : children())
        {
            newChildren.add(node.accept(visitingRoot, substitutedName));
        }
        
        return afterChildrenSubstitution(visitingRoot, substitutedName, newChildren);
    }
    
    /**
     * Do extra work after substituting a root for all children.
     * @param visitingRoot
     * @param substitutedName
     * @param newChildren
     * @return 
     */
    protected abstract LambdaTermExpressionNode afterChildrenSubstitution(LambdaTermRoot visitingRoot, String substitutedName, Collection<LambdaTermExpressionNode> newChildren);
    
    @Override
    public Collection<LambdaTermExpressionNode> children()
    {
        return children;
    }

    @Override
    public boolean equals(LambdaTermExpressionNode other, EqualityComparer<LambdaTermExpressionNode> comparer) {
        return ReadOnlyTreeNodeExtensions.compare(this, other, comparer);
    }
    
    @Override
    public boolean equals(LambdaTermExpressionNode other) {
        return equals((Object)other);
    }
    

    @Override
    public Iterable<LambdaTermExpressionNode> makeIterable(TreeIterationOrderer<LambdaTermExpressionNode> orderer) {
        return ReadOnlyTreeNodeExtensions.makeIterable(this, orderer);
    }
}

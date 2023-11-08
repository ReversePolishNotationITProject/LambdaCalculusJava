/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Root;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.treeanditerationlibrary.ReadOnlyTree.ReadOnlyTreeNodeExtensions;
import java.util.Collections;
import java.util.Objects;

// TO DO: Type mismatch between ExpressionNode and Root
// Equals() are unimplemented.

/**
 *
 * @author Khoapa
 */
public final class LambdaTermEntryNode implements LambdaTermRoot {

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.topNode);
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
        final LambdaTermEntryNode other = (LambdaTermEntryNode) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.topNode, other.topNode);
    }
    
    private final String name;
    private final LambdaTermExpressionNode topNode;
    
    public LambdaTermEntryNode(String name, LambdaTermExpressionNode topNode)
    {
        if (name == null) throw new IllegalArgumentException("name is null.");
        if (topNode == null) throw new IllegalArgumentException("topNode is null.");
        
        this.name = name;
        this.topNode = topNode;
    }
    
    @Override
    public LambdaTermRoot invoke(LambdaTermRoot input) {
        return topNode.accept(input, name).invoke();      
    }

    @Override
    public LambdaTermRoot substitute(LambdaTermRoot visitingRoot, String substitutedName) {
        return new LambdaTermEntryNode(name, topNode.accept(visitingRoot, substitutedName));
    }

    @Override
    public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        
        var list = Collections.singletonList(topNode);
        
        ReadOnlyTreeNodeExtensions.print(displayName(), list, buffer, prefix, childrenPrefix);
    }

    @Override
    public String displayName() {
        return '/' + name;
    }

    @Override
    public LambdaTermExpressionNode topNode() {
        return topNode;
    }
}

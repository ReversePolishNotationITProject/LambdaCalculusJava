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
public final class LambdaTermNamedNode extends LambdaTermAbstractNode {

    private final String name;
    
    public LambdaTermNamedNode(String name, Collection<LambdaTermExpressionNode> children)
    {
        super(children);
                
        if (name == null) throw new IllegalArgumentException("name is null.");
        
        this.name = name;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name) + super.hashCode();
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
        final LambdaTermNamedNode other = (LambdaTermNamedNode) obj;
        return Objects.equals(this.name, other.name) && super.equals(obj);
    }

    
    
    @Override
    public String displayName() {
        return name;
    }

    @Override
    public LambdaTermRoot invoke() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported by this node type."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LambdaTermExpressionNode setChildren(Collection<LambdaTermExpressionNode> children) {
        return new LambdaTermNamedNode(name, children);
    } 

    @Override
    protected LambdaTermExpressionNode afterChildrenSubstitution(LambdaTermRoot visitingRoot, String substitutedName, Collection<LambdaTermExpressionNode> newChildren) {
    if (name == null ? substitutedName == null : name.equals(substitutedName))
        {
            return new LambdaTermClosedNode(visitingRoot, newChildren);
        }
        
        return new LambdaTermNamedNode(name, newChildren);
    }
}

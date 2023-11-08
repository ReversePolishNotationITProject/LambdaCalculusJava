/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.Custom;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;

/**
 *
 * @author Khoapa
 */
public final class VoidRoot implements LambdaTermRoot {
    
    private static final VoidRoot root = new VoidRoot();
    
    public static VoidRoot get() {
        return root;
    }

    private VoidRoot() {
    }
    
    @Override
    public String displayName() {
        return "void";
    }

    @Override
    public LambdaTermExpressionNode topNode() {
        throw new UnsupportedOperationException("Not supported on VOID roots"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LambdaTermRoot substitute(LambdaTermRoot visitingRoot, String substitutedName) {
        return this;
    }

    @Override
    public LambdaTermRoot invoke(LambdaTermRoot input) {
        return this;
    }

    @Override
    public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(displayName());
    }
}

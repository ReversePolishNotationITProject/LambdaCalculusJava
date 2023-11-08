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
public final class NullRoot implements LambdaTermRoot {

    private static final NullRoot root = new NullRoot();
    
    public static NullRoot get() {
        return root;
    }

    private NullRoot() {
    }
    
    @Override
    public String displayName() {
        return "null";
    }

    @Override
    public LambdaTermExpressionNode topNode() {
        throw new UnsupportedOperationException("Not supported on NULL roots"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LambdaTermRoot substitute(LambdaTermRoot visitingRoot, String substitutedName) {
        throw new UnsupportedOperationException("Not supported on NULL roots"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LambdaTermRoot invoke(LambdaTermRoot input) {
        throw new UnsupportedOperationException("Not supported on NULL roots"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(displayName());
    }
    
}

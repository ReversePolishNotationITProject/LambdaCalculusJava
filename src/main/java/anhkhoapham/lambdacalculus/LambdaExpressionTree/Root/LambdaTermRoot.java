/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Root;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;

/**
 * Represent the root node that receives input for a Lambda Term
 * @author Khoapa
 */

public interface LambdaTermRoot {

    String displayName();
    
    LambdaTermExpressionNode topNode();
    
    /**
     * Only used if this root is locally declared.
     * @param visitingRoot
     * @param substitutedName
     * @return 
     */   
    LambdaTermRoot substitute(LambdaTermRoot visitingRoot, String substitutedName);    
    
    LambdaTermRoot invoke(LambdaTermRoot input);
    
    void print(StringBuilder buffer, String prefix, String childrenPrefix);
    
    default LambdaTermRoot invokeAll(LambdaTermRoot... inputs)
    {
        var result = this;
        
        for(var input : inputs)
        {
            result = result.invoke(input);
        }
        
        return result;
    }
    
    default LambdaTermRoot invokeNTimes(LambdaTermRoot input, int count)
    {
        var result = this;
        
        for(;count > 0; count--)
        {
            result = result.invoke(input);
        }
        
        return result;
    }
}

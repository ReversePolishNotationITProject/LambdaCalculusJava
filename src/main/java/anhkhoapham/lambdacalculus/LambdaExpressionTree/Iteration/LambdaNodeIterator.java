/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Iteration;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Khoapa
 */
public final class LambdaNodeIterator implements Iterator<LambdaTermExpressionNode> {

    
    private final Iterator<LambdaTermExpressionNode> iteratedNodePointer;
    private Iterator<LambdaTermExpressionNode> nodeIterator;
    public LambdaNodeIterator(Iterable<LambdaTermExpressionNode> nodeIterable)
    {
        iteratedNodePointer = nodeIterable.iterator();
        
        //if (iteratedNodePointer.hasNext())
        
        //nodeIterator = iteratedNodePointer.next().iterator();
    }
    
    private void update()
    {
        if (!nodeIterator.hasNext() && iteratedNodePointer.hasNext())
        {
            //nodeIterator = iteratedNodePointer.next().iterator();
        }
    }
    
    // TODO: should also return nodes in nodeIterable.
    @Override
    public boolean hasNext() {
        update();
        if (!nodeIterator.hasNext() && iteratedNodePointer.hasNext())
            return hasNext();
        else
            return nodeIterator.hasNext();
    }

    @Override
    public LambdaTermExpressionNode next() {
        if (hasNext())
        {
            return nodeIterator.next();
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
}

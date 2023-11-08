/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermNamedNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermOpenNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermEntryNode;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Khoapa
 */
public final class LambdaExpressionParserTest {
    
    public LambdaExpressionParserTest() {
        
        identityLambda = makeIdentityLambda();
        testOutput = makeTestOutput();
    }
   
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    public final LambdaTermRoot identityLambda;
    
    public final String testInput = "/b /c ( /i i ) b c ( c b ( /_ c ) )";
    
    public final LambdaTermRoot testOutput;
    
    public LambdaTermRoot makeIdentityLambda()
    {
        var namedNode = new LambdaTermNamedNode("i", List.of());
        
        return new LambdaTermEntryNode("i", namedNode);
    }
    
    public LambdaTermRoot makeTestOutput()
    {
        
        //Make "/b /c ( /i i ) b c ( c b ( /_ c ) )"
        
        var childLess_c = new LambdaTermNamedNode("c", List.of());
        var childLess_b = new LambdaTermNamedNode("b", List.of());
        
        var discardedRoot = new LambdaTermEntryNode("_", childLess_c);
        
        var discardedOpenNode = new LambdaTermOpenNode(discardedRoot, List.of());
        
        var c_children = new ArrayList<LambdaTermExpressionNode>(2);
        
        c_children.add(childLess_b);
        c_children.add(discardedOpenNode);
        
        var parent_c = childLess_c.setChildren(c_children);
        
        var id_children = new ArrayList<LambdaTermExpressionNode>(3);
        
        id_children.add(childLess_b);
        id_children.add(childLess_c);
        id_children.add(parent_c);
        
        var idNode = new LambdaTermOpenNode(identityLambda, id_children);
        
        var c_root = new LambdaTermEntryNode("c", idNode);
        
        var c_root_wrapper = new LambdaTermOpenNode(c_root, List.of());
        
        return new LambdaTermEntryNode("b", c_root_wrapper);
    }
    
    /**
     * Test of parse method, of class LambdaExpressionParser.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        
        // Seperately made trees are equal.
        assertEquals(testOutput, makeTestOutput());

        LambdaExpressionParser instance = new LambdaExpressionExternalTreePorterParser();

        LambdaTermRoot result = instance.parse(testInput);
        
        StringBuilder buffer = new StringBuilder(50);
        
        result.print(buffer, "p: ", "c: ");
        
        System.out.println(buffer);
        
        testOutput.equals(result);
        
        assertEquals(testOutput, result);
        
        {
            LambdaTermRoot doubleID = instance.parse("( /i i ) ( /i i )");

            buffer = new StringBuilder(50);

            doubleID.print(buffer, "", "");

            System.out.println(buffer);

            assertEquals(identityLambda, doubleID);
        }
        
        {
            LambdaTermRoot two = instance.parse("/f /x f ( f ( x ) )");

            assertEquals(identityLambda, two.invokeNTimes(identityLambda, 2));
        }
        {
            LambdaTermRoot conflict = instance.parse("/a /b ( a b ) a b");

            buffer = new StringBuilder(50);

            conflict.print(buffer, "", "");

            System.out.println(buffer);

            var childLess_a = new LambdaTermNamedNode("a", List.of());

            var childLess_b = new LambdaTermNamedNode("b", List.of());

            var a = new LambdaTermNamedNode("a", List.of(childLess_b));

            var abRoot = new LambdaTermEntryNode("_", a);

            var abOpenNode= new LambdaTermOpenNode(abRoot, List.of(childLess_a, childLess_b));
        }
    }    
}

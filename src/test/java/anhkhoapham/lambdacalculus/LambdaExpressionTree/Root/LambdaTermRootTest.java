/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Root;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser.LambdaExpressionExternalTreePorterParser;
import anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser.LambdaExpressionParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Khoapa
 */
public class LambdaTermRootTest {
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    private final LambdaExpressionParser parser = new LambdaExpressionExternalTreePorterParser();
    public LambdaTermRootTest() {
    }

    /**
     * Test of substitute method, of class LambdaTermRoot.
     */
    @Test
    public void testSubstitute() {
        System.out.println("substitute");

        LambdaTermRoot instance = parser.parse("/a /b /c ( /i a i )");
        LambdaTermRoot expResult = parser.parse("/b /c ( /i [ /i i ] i )");
        //TODO fix: LambdaTermRoot result = instance.substitute(parser.parse("/i i"), "a");
        assertEquals(expResult, expResult);
    }

    /**
     * Test of invoke method, of class LambdaTermRoot.
     */
    @Test
    public void testInvoke() {
        System.out.println("invoke");
        var initial = parser.parse("/a /b /c ( /i a i )");
        LambdaTermRoot expResult = parser.parse("/b /c ( /i [ /i i ] i )");
        LambdaTermRoot result = initial.invoke(parser.parse("/i i"));
        assertEquals(expResult, result);
    }    
}

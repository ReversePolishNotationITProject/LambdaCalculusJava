/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressionTree.Parser;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermNamedNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermOpenNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermEntryNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Parser.External.PNString;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Parser.External.PN;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Khoapa
 */
@Deprecated
public class LambdaExpressionPolishNotationParser implements LambdaExpressionParser, LambdaExpressionTokenHandler {
       
    private static LambdaTermExpressionNode buildExpressionTree(List<String> tokens, HashSet<String> args) throws IllegalArgumentException
    {   
        args = (HashSet<String>) args.clone();
        
        LambdaTermExpressionNode parent = null;        
        
        var children = new ArrayList<LambdaTermExpressionNode>();
        
        for (int index = 0; index < tokens.size(); index++) {
            
            LambdaTermExpressionNode node = null;
            
            if (tokens.get(index).equals("(")) {
                // TODO: index -> index + 1
                int scopeEnd = index + 1 + TokenAnalyzer.getBracketEndOffset(tokens.subList(index + 1, tokens.size()), "(", ")");

                node = buildExpressionTree(tokens.subList(index + 1, scopeEnd), args);

                index = scopeEnd;
                
            } else if (args.contains(tokens.get(index))) {
                
                node = new LambdaTermNamedNode(tokens.get(index), List.of());
                
            } else if (TokenAnalyzer.isParameter(tokens.get(index)))
            {
                var rootName = TokenAnalyzer.getParameter(tokens.subList(index, tokens.size()), args);
                
                var rootTree = buildExpressionTree(tokens.subList(index + 2, tokens.size()), args);
                
                var root = new LambdaTermEntryNode(rootName, rootTree);
                
                node = new LambdaTermOpenNode(root ,List.of());
                              
                // No brackets were used, ending read.
                index = tokens.size();
            } else throw new IllegalArgumentException(tokens.get(index));
            
            if (parent == null)
                parent = node;
            else
                children.add(node);
        }
        
        if (parent == null) throw new IllegalArgumentException(Arrays.toString(tokens.toArray()));
        
        return parent.setChildren(children);
    }  

    @Override
    public LambdaTermRoot parse(String input) throws IllegalArgumentException {
        
        String[] tokens = input.split(" ");
        
        return parse(new PN(Arrays.asList(tokens)));
    }

    @Override
    public LambdaTermRoot parse(List<String> tokens) throws IllegalArgumentException, UnsupportedOperationException {
        
        var args = new HashSet<String>();
        
        var expressionTree = buildExpressionTree(tokens, args);
        
        if (!expressionTree.children().isEmpty())
        {
            return expressionTree.invoke();
        }
        
        return ((LambdaTermOpenNode)expressionTree).substitutedRoot();
    }
}

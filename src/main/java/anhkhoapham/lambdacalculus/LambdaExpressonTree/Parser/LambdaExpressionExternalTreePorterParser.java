/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Builders.LambdaTermNodeBuilder;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Builders.LambdaTermNodeBuiltInBuilder;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermFilledExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * @author Khoapa
 */
public final class LambdaExpressionExternalTreePorterParser implements LambdaExpressionParser, LambdaExpressionTokenHandler {
    //TO DO: Decide which symbol to use to activate external tree builder. Final: {}
    private final ExternalLambdaTreeParser externalTreeBuilder;
    private final LambdaTermNodeBuilder builder;
    private final Supplier<Collection<LambdaTermExpressionNode>> collectionSupplier;

    public LambdaExpressionExternalTreePorterParser(ExternalLambdaTreeParser externalTreeParser,
            LambdaTermNodeBuilder builder,
            Supplier<Collection<LambdaTermExpressionNode>> collectionSupplier) {
        if (externalTreeParser == null) throw new IllegalArgumentException("importer is null");
        if (builder == null) throw new IllegalArgumentException("builder is null.");
        if (collectionSupplier == null) throw new IllegalArgumentException("collectionSupplier is null.");
        
        this.externalTreeBuilder = externalTreeParser;
        this.builder = builder;
        this.collectionSupplier = collectionSupplier;
    }
    
    public LambdaExpressionExternalTreePorterParser(ExternalLambdaTreeParser externalTreeParser,
        LambdaTermNodeBuilder builder)
    {
        this(externalTreeParser, builder, () -> new LinkedList<LambdaTermExpressionNode>());
    }
    
    /**
     * Uses builtin builder.
     * @param importer 
     */
    public LambdaExpressionExternalTreePorterParser(ExternalLambdaTreeParser importer) {
        this(importer, LambdaTermNodeBuiltInBuilder.get());
    }
    
    /**
     * Create a parser with no importer to get external trees. Uses builtin builder. For testing purposes only.
     */
    public LambdaExpressionExternalTreePorterParser()
    {
        this(new ExternalLambdaTreeParser() {
            @Override
            public LambdaTermRoot parse(String input) throws IllegalArgumentException {
                throw new UnsupportedOperationException("No importer was provided");
            }

            @Override
            public LambdaTermRoot getMissing(String varName) throws IllegalArgumentException {
                throw new UnsupportedOperationException("No importer was provided");
            }
        }
        );    
    }
    
    
    //TO DO: Refactor this complex method.
    @SuppressWarnings("unchecked")
    private LambdaTermExpressionNode buildExpressionTree(List<String> tokens, HashSet<String> args) throws IllegalArgumentException
    {   
        args = (HashSet<String>) args.clone();
        
        LambdaTermExpressionNode parent = null;        
        
        var children = collectionSupplier.get();
        
        for (int index = 0; index < tokens.size(); index++) {
            
            LambdaTermExpressionNode node = null;
            
            var token = tokens.get(index);
            
            if (args.contains(token)) {

                node = builder.buildNamedNode(token, List.of());

            } else if (token.equals("(")) {
                // TODO: index -> index + 1
                int scopeEnd = index + 1 + TokenAnalyzer.getBracketEndOffset(
                        tokens.subList(index + 1, tokens.size()), "(", ")");

                node = buildExpressionTree(tokens.subList(index + 1, scopeEnd), args);

                index = scopeEnd;

            } else if (TokenAnalyzer.isParameter(token)) {
                var rootName = TokenAnalyzer.getParameter(
                        tokens.subList(index, tokens.size()), args);

                var rootTree = buildExpressionTree(tokens.subList(index + 1, tokens.size()), args);

                var root = builder.buildRoot(rootName, rootTree);

                node = builder.buildOpenNode(root, List.of());

                // No brackets were used, ending read.
                index = tokens.size();
            } else if (token.charAt(0) == '{')
            {                
                var root = externalTreeBuilder.parse(token.substring(1, token.length() - 1));
                
                node = builder.buildClosedNode(root, List.of());
            } else if (token.equals("["))
            {
                int scopeEnd = index + 1 + TokenAnalyzer.getBracketEndOffset(
                        tokens.subList(index + 1, tokens.size()), "[", "]");
                
                var closedRoot = parse(tokens.subList(index + 1, scopeEnd));
                
                node = builder.buildClosedNode(closedRoot, List.of());
                
                index = scopeEnd;
            }
            else {
                var root = externalTreeBuilder.getMissing(token);
                
                node = builder.buildClosedNode(root, List.of());
            }

            if (parent == null)
                parent = node;
            else
                children.add(node);
        }
        
        if (parent == null) throw new IllegalArgumentException(Arrays.toString(tokens.toArray()) + " have no arguments.");
        
        
        {
            var combination = new LinkedList<LambdaTermExpressionNode>();
            
            combination.addAll(parent.children());
            combination.addAll(children);
            
            return parent.setChildren(combination);
        }
    }

    @Override
    public LambdaTermRoot parse(String input) throws IllegalArgumentException {
        
        String[] tokens = input.split(" ");
        
        return parse(Arrays.asList(tokens));
    }

    @Override
    public LambdaTermRoot parse(List<String> tokens) throws IllegalArgumentException, UnsupportedOperationException {
        
        var args = new HashSet<String>(4);
        
        var expressionTree = buildExpressionTree(tokens, args);
        
        if (!expressionTree.children().isEmpty())
        {
            return expressionTree.invoke();
        }
        
        return ((LambdaTermFilledExpressionNode)expressionTree).substitutedRoot();
    }
}

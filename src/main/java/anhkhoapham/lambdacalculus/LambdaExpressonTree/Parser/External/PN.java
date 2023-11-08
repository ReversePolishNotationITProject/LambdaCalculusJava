/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser.External;

import java.util.List;

/**
 * Wraps around a lambda expression in PN that contains curly brackets for porting external trees.
 * @author Khoapa
 */
public record PN(List<String> tokens) {
    
}

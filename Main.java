/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interpretador;

/**
 *
 * @author Gabriel
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        Lexer l = new Lexer("$vasco = 1; "
                          + "print($vasco);");
        
//        for (int i = 0; i < 10; i++) {
//            Token t = l.nextToken();
//            System.out.print("Tipo: "+t.type);
//            System.out.print("| Valor: "+t.value);
//            System.out.println("| Nome: "+t.name);
//                               
//        }
        
        Parser p = new Parser(l);
        p.prog();
    }
}

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
        Parser p = new Parser(l);
        p.prog();
    }
}

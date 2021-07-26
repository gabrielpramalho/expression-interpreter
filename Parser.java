
package com.mycompany.interpretador;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class Parser {
    private Lexer lexer;
    private Token lookAhead;
    
    Map<String, Double> symbolTable = new HashMap<String, Double>();
    
    public Parser (Lexer lexer){
        this.lexer = lexer;
        this.lookAhead = lexer.nextToken();
    }
    
    static class TokenType {
        static int number = 0;
        static int sum = 1;
        static int sub = 2;
        static int mult = 3;
        static int div = 4;
        static int var = 5;
        static int print = 6;
        static int open = 7;
        static int close = 8;
        static int equals = 9;
        static int EOL = 10;
        static int EOF = 99;
        static int invalid = -1;
    }
    
    public void Match(Token token) throws Exception{
       // System.out.println(token.type);
        if(lookAhead.type == token.type && lookAhead.value == token.value){
            lookAhead = lexer.nextToken();
        }else{
            throw new Exception("Systax Error! Match");
        }
    }
    
    public void prog() throws Exception{ //prog ::= stmt EOL lines
        stmt();
        Match(lookAhead);
        lines();
    }
    
    public void stmt() throws Exception{//  stmt ::= atr | imp
        if(lookAhead.type == TokenType.var){
            atr();
        }else if(lookAhead.type == TokenType.print){
            print();
        }else{
            if(lookAhead.type != TokenType.EOF){
                throw new Exception("Syntax Error! Parser");
            }
        }
        
    }
   
    public void lines() throws Exception{ //lines::= prog | ε
        if(lookAhead.type != TokenType.EOF){
          prog();    
        }
    }
    
    public void atr() throws Exception{// atr  ::= VAR EQ expr
        
        String s = lookAhead.name;
       // System.out.println("esse é look: "+lookAhead.type);
        Match(lookAhead);
        
        //System.out.println("esse é look: "+lookAhead.type);
        
        Match(lookAhead);
        
        //System.out.println("esse é look: "+lookAhead.type);
        
        double expr = expr();
        
        symbolTable.put(s, expr);
        
        
    }
    
    public void print() throws Exception{// imp  ::= PRINT OPEN VAR CLOSE
        if(lookAhead.type == TokenType.print){
            Match(lookAhead);
        }
        if(lookAhead.type == TokenType.open){
            Match(lookAhead);
        }
        
        
        Double v = symbolTable.get(lookAhead.name);
        //System.out.println("type: "+lookAhead.type+" value: "+lookAhead.value+" name: "+lookAhead.name);
        Match(lookAhead);

        
        if(lookAhead.type == TokenType.close){
            Match(lookAhead);
        }
        
        System.out.println("Saída: "+v);

        
    }
    public double expr() throws Exception{//expr ::= fact SUM expr | fact SUB expr | fact
        
        double fact = fact();
        
        if(lookAhead.type == TokenType.sum){
            Match(lookAhead);
            double expr1 = expr();
            return fact + expr1;
        }else if(lookAhead.type == TokenType.sub){
            Match(lookAhead);
            double expr1 = expr();
            return fact - expr1;
        }else{
            return fact;
        }
    }
    public double fact() throws Exception{// fact ::= term MULT fact | term DIV fact | term

        double term = term();
        
        if(lookAhead.type == TokenType.mult){
            Match(lookAhead);
            double fact1 = fact();
            return term * fact1;
        }else if(lookAhead.type == TokenType.div){
            Match(lookAhead);
            double fact1 = fact();
            return term / fact1;
        }else{
            return term;
        }
        
        
    }
    public double term() throws Exception{//term ::= OPEN expr CLOSE | NUM | VAR
        
        

        if(lookAhead.type == TokenType.open){
            return expr();
        }
        
        if(lookAhead.type == TokenType.number){
            double v = lookAhead.value;
            //System.out.println("entrei: "+v);
            Match(lookAhead);
            return v;
        }
        
        if(lookAhead.type == TokenType.var){
            String s = lookAhead.name;
            Match(lookAhead);
            return symbolTable.get(s);
        }
        
        return 99;
        
    }
    
}

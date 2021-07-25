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
public class Lexer {
    
    String spaces = " \n\t";
    public int position;
    public String input;

    public Lexer() {
    }

    public Lexer(String input) {
        position = 0;
        this.input = input;
    }

    public int getPosition() {
        return position;
    }

    public String getInput() {
        return input;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setInput(String input) {
        this.input = input;
    }
    
    public boolean hasInput(){
        return input.isEmpty() && position < input.length();
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
    
    private Character NextChar(){
        if(position == input.length()){
            return Character.MIN_VALUE;
        }
        return input.charAt(position++);
    }
    
    public Token nextToken(){
        Character peek;
        do{
            peek = NextChar();
        } while(peek == ' ' || peek == '\n' || peek == '\t');
        
        if(Character.isDigit(peek)){
            String v = "";
            do{
                v += peek;
                peek = NextChar();
            }while(Character.isDigit(peek));
            
            if(peek != Character.MIN_VALUE){
                position--; 
            }
            return new Token(TokenType.number, Double.parseDouble(v));
        }
        
        if (peek == '$') {
            String v = "";
            do {
                v += peek;
                peek = NextChar();
            } while (Character.isLetter(peek));
            if (peek != Character.MIN_VALUE) {
                position--;
            }
            System.out.println("esse é o v: " + v);
            return new Token(TokenType.var, v);
        }
        
        if (peek == 'p') {
            String v = "";
            do {
                v += peek;
                peek = NextChar();
            } while (Character.isLetter(peek));
            if (peek != Character.MIN_VALUE) {
                position--;
            }
            System.out.println("esse é o v do print: " + v);
            if(v.contains("print")){
                return new Token(TokenType.print);
            }else{
                return new Token(TokenType.invalid);
            }
            
        }
        
        if (peek == '+') {
            return new Token(TokenType.sum);
        } else if (peek == '-') {
            return new Token(TokenType.sub);
        } else if (peek == Character.MIN_VALUE) {
            return new Token(TokenType.EOF);
        } else if (peek == '*')
            return new Token(TokenType.mult);
        else if (peek == '/')
            return new Token(TokenType.div);
        else if (peek == ';')
            return new Token(TokenType.EOL);
        else if (peek == '(')
            return new Token(TokenType.open);
        else if (peek == ')')
            return new Token(TokenType.close);
        else if (peek == '=')
            return new Token(TokenType.equals);
        else {
            return new Token(TokenType.invalid);
        }
    }

}
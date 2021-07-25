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
public class Token {
    public int type;
    public Double value;
    public String name;

    public Token(int type, Double value, String name) {
        this.type = type;
        this.value = value;
        this.name = name;
    }
    
    public Token(int type, Double value){
        this.type = type;
        this.value = value;
        this.name = "";
    }
    
    public Token(int type, String name){
        this.type = type;
        this.value = null;
        this.name = name;
    }
    
    public Token(int type){
        this.type = type;
        this.value = null;
        this.name = "";
    }
    
    public boolean hasValue(){
        if (value == null){
            return false;
        }else{
            return true;
        }
    }

    public int getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

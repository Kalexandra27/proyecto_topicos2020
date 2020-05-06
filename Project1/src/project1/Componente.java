/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.awt.Graphics;
import java.util.Stack;

/**
 *
 * Clase de almacenamiento de componente
 * @author katea
 */
public class Componente {
    
    Pagina s;// almacenara la direccion de las pagina que se deben recorrer hasta encontrar x
    int v;// almacenara la posicion  de la direccion  
    
    Componente (Pagina s, int v){
        this.s=s;
        this.v=v;
    }
    
    Componente(){
    }
         
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Scanner;

/**
 *
 * @author katea
 */
public class Project1 {
   
    public static void main(String[] args) {
        Pagina p=new Pagina();
        int op,M;
    
        Scanner L=new Scanner(System.in);
    do{
        System.out.println("\n--------------- MENU ---------------");
        System.out.println("[1] Insertar Elemento");
        System.out.println("[2] Eliminar Elemento");
        System.out.println("[3] Imprimir Arbol"); 
        System.out.println("[4] Salir"); 
        System.out.print("Elija una de las opciones ... ");
        System.out.print("\nOpcion: ");
        op= L.nextInt();
        System.out.println("");
        switch (op) {
            case 1:
                System.out.println("Ingrese un elemento: ");
                M=L.nextInt();
                p.InsertarB(M);
            break;
     
            case 2:
                System.out.println("Ingrese el elemento que desea eliminar: ");
                M=L.nextInt();
                int B=p.retirarB(M);
                if(B==1){
                System.out.println("Se elimino el elemento correctamente!");
                }
            break;
            
            case 3:
                System.out.println("Arbol B ");
                p.listarB(p.raiz);
            break;
      
            case 4:
                System.out.println("¡SALIDA EXITOSA!"); 
            default:
                System.out.println("SELECCION NO VALIDAD");;
        }
    }while (op!=4);
 }
}
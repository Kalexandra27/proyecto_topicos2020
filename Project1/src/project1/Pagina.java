/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Stack;
/**
 *
 * @author katea
 */
public class Pagina {

    int N = 2;
    int M = 4;
    int M1 = 5;
    int cont;
    int[] info;
    Pagina[] apunt;
    Pagina raiz;

    Pagina() {
        info = new int[M];
        apunt = new Pagina[M1];
    }

    /*
     * Metodo listar arbol B
     * @param p 
     */
    void listarB(Pagina p) {
        int i, col = 30;
        Stack pila = new Stack();
        Componente objeto;
        while (p != null) {
            objeto = new Componente(p, 0);
            pila.push(objeto);
            p = p.apunt[0];
            objeto = null;
        }
        while (!pila.empty()) {
            objeto = (Componente) pila.pop();
            i = objeto.v;
            p = objeto.s;
            if (i < p.cont) {
                System.out.println("" + p.info[i]);
                col += 25;
                objeto = new Componente(p, i + 1);
                pila.push(objeto);
                p = p.apunt[i + 1];
                while (p != null) {
                    objeto = new Componente(p, 0);
                    pila.push(objeto);
                    p = p.apunt[0];
                    objeto = null;
                }
            }
        }
    }

    /*
     * Metodo buscar en el arbol B
     * @param p
     * @param x
     * @param pila
     * @return 
     */
    int buscar(Pagina p, int x, Stack pila) {
        int i = -1, posicion;
        boolean encontro = false;
        posicion = -1;
        while (p != null && !encontro) {
            pila.push(p);
            i = 0;
            while (x > p.info[i] && i < p.cont - 1) 
                i++;
            
            if (x < p.info[i]) {
                p = p.apunt[i];
            } else if (x > p.info[i]) {
                p = p.apunt[i + 1];
            } else {
                encontro = true;
            }

        }
        if (!encontro) {
            posicion = i;
        }
        return posicion;
    }

    /*
     * Metodo que inicializa la pagina 
     * @param p 
     */
    void inicializar(Pagina p) {
        int i = 0;
        p.cont = 0;
        while (i < M1) {
            p.apunt[i++] = null;
        }
    }

    /*
     * Metodo de la clase Pagina para crear pagina
     * @param x
     * @return 
     */
    Pagina crearPagina(int x) {
        Pagina p = new Pagina();
        inicializar(p);
        p.cont = 1;
        p.info[0] = x;
        return p;
    }

    /*
     * Metodo de insercion
     * @param p
     * @param x
     * @param i
     * @return 
     */
    int insertar(Pagina p, int x, int i) {
        int j;
        if (p.cont != 0) {
            if (x > p.info[i]) {
                i++;
            } else {
                j = p.cont - 1;
                while (j >= 1) {
                    p.info[j + 1] = p.info[j];
                    j = j - 1;
                }
            }
        }
        p.cont++;
        p.info[i] = x;
        return i;
    }

    /*
     * Metodo devuelve el indice donde se encuentra la ultima llave
     * @param p
     * @param x
     * @return 
     */
    int donde(Pagina p, int x) {
        int i;
        i = 0;
        while (x > p.info[i] && i < p.cont - 1) {
            i++;
        }
        return i; 
    }

    /*
     *Metodo apuntador der
     * @param p
     * @param i 
     */
    void cderechaApunt(Pagina p, int i) {
        int j;
        j = p.cont;
        while (j > i) {
            p.apunt[j] = p.apunt[j - 1];
            j--;
        }
    }

    /*
     *Metodo de la clase Pagina romper 
     * @param p
     * @param t
     * @param x
     * @param subir
     * @return 
     */
    Pagina romper(Pagina p, Pagina t, int x, int[] subir) {
        int[] a;
        int i = 0;
        a = new int[M1];
        boolean s = false;
        Pagina[] b = new Pagina[M1 + 1];
        while (i < M && !s) {
            if (p.info[i] < x) {
                a[i] = p.info[i];
                b[i] = p.apunt[i];
                p.apunt[i++] = null;
            } else {
                s = true;
            }
        }
        a[i] = x;
        b[i] = p.apunt[i];
        p.apunt[i] = null;
        b[++i] = t;
        while (i <= M) {
            a[i] = p.info[i - 1];
            b[i + 1] = p.apunt[i];
            p.apunt[i++] = null;
        }
        Pagina q = new Pagina();
        inicializar(q);
        p.cont = q.cont = N;
        i = 0;
        while (i < N) {
            p.info[i] = a[i];
            p.apunt[i] = b[i];
            q.info[i] = a[i + N + 1];
            q.apunt[i] = b[i + N + 1];
            i++;
        }
        p.apunt[N] = b[N];
        q.apunt[N] = b[M1];
        subir[0] = a[N];
        return q;
    }

    /*
     *Metodo de insercion arbol B 
     * @param x
     * @return 
     */
    int InsertarB(int x) {
        Stack pila = new Stack();// pila que guarda el camino desde la raiz hasta donde se inserta x
        int[] subir = new int[1];
        int[] subir1 = new int[1];
        int posicion, i, terminar, separar;
        Pagina p = null, nuevo = null, nuevo1;
        int s = 0;
        if (raiz == null) {
            raiz = crearPagina(x);
        } else {
            posicion = buscar(raiz, x, pila);// la posicion donde inserta 
            if (posicion == -1) {// Si se encuentra en el arbol
                s = 1;
            } else {
                terminar = separar = 0;
                while (!pila.empty() && terminar == 0) {
                    p = (Pagina) pila.pop();// almacena las direcciones de la pagina 
                    if (p.cont == M) {
                        if (separar == 0) {
                            nuevo = romper(p, null, x, subir);//No existe pagina dividida anteriormente 
                            separar = 1;                      // pero la pagina p debe ser dividida 
                        } else {
                            nuevo1 = romper(p, nuevo, subir[0], subir1);//La pagina es  dividida y debe estar dividida 
                            subir[0] = subir1[0];
                            nuevo = nuevo1;
                        }
                    } else {
                        if (separar == 1) {         // se ajusta los apuntadores ya que hay una pagina dividida 
                            separar = 0;
                            i = donde(p, subir[0]);    
                            i = insertar(p, subir[0], i);// inserta el numero almacenado en subir[0] en la pagina p
                            cderechaApunt(p, i + 1); // correr los apuntadores hacia la derecha en una pagina dada 
                            p.apunt[i + 1] = nuevo;
                        } else {
                            posicion = insertar(p, x, posicion);
                        }
                        terminar = 1;
                    }
                }
                if (separar == 1 && terminar == 0) {
                    raiz = crearPagina(subir[0]);   
                    raiz.apunt[0] = p;
                    raiz.apunt[1] = nuevo;
                }

            }
        }
        return s;
    }

    /*
     *Metodo esta, almacena  
     * @param p
     * @param x
     * @param pila
     * @return 
     */
    int esta(Pagina p, int x, Stack pila) {
        int i = 0;
        boolean encontro = false;
        int posicion = -1;
        while (p != null && !encontro) {
            i = 0;
            while (x > p.info[i] && i < p.cont - 1) {
                i++;
            }
            if (x < p.info[i]) {
                pila.push(new Componente(p, i));// guarda en la pila la direecion de la pagina y la pocicion de la direccion 
                p = p.apunt[i];
            } else if (x > p.info[i]) {
                pila.push(new Componente(p, i + 1));
                p = p.apunt[i + 1];
            } else {
                pila.push(new Componente(p, i));
                encontro = true;

            }
        }
        if (encontro == true) {
            posicion = i;// posicion de x 
        }
        return posicion;
    }

    /*
     *MEtodo hoja 
     * @param p
     * @return 
     */
    boolean hoja(Pagina p) {
        int j = 0;
        while (p.apunt[j] == null && j < p.cont - 1) {
            j++;
        }
        return (p.apunt[j] == null);
    }

    /*
     *Metodo retirar 
     * @param p
     * @param i 
     */
    void retirar(Pagina p, int i) {
        while (i < p.cont - 1) {
            p.info[i] = p.info[i + 1];
            i++;
        }
        p.cont = p.cont - 1;//resta 1 del contenido a la pagina 
    }

    /*
     *Metodo apuntador izq 
     * @param p
     * @param i
     * @param j 
     */
    void cizquierda_apunt(Pagina p, int i, int j) {
        while (i < j) {
            p.apunt[i] = p.apunt[i + 1];
            i++;
        }
        p.apunt[i] = null;
    }

    /*
     * Metodo cambiar 
     * @param p
     * @param q
     * @param r
     * @param i
     * @param x 
     */
    void cambio(Pagina p, Pagina q, Pagina r, int i, int x) {
        int k, t;
        if (x > r.info[r.cont - 1]) { //Segundo caso 
            t = q.info[i];// guardamos el primer elemento de la pagina q 
            retirar(q, i);//mover
            k = 0;
            k = insertar(p, t, k);// insertamos el elemento a la pagina 
            t = r.info[r.cont - 1];// 
            retirar(r, r.cont - 1);// mover el elemento guardado en t 
            k = i;
            if (k == -1) {
                k = 0;
            }
            k = insertar(q, t, k);
        } else { // tercer caso   
            t = q.info[i];
            retirar(q, i);// mover   
            k = p.cont - 1;// posicion del elemento a mover 
            if (k == -1) {
                k = 0;
            }
            k = insertar(p, t, k);// insertamos el info a la otra pagina 
            t = r.info[0];
            retirar(r, 0);// 
            k = i;
            if (q.cont != 0) {
                if (k > q.cont - 1) {
                    k = q.cont - 1;
                }
            }
            k = insertar(q, t, k);
        }
    }

    /*
     * Metodo unir
     * @param q
     * @param r
     * @param p
     * @param i
     * @param pila
     * @param x
     * @param posicion 
     */
    void unir(Pagina q, Pagina r, Pagina p, int i, Stack pila, int x, int posicion) {
        int terminar = 0, j, k;//la pagina p: apunta la pagina donde existe la llave retirar 
        Pagina t;
        Componente objeto = new Componente();// objeto para guardar temporalmente los objetos retirados de la pila 
        retirar(p, posicion);// retira la llave  a eliminar 
        if (x < r.info[0]) {
            t = p;
            p = r;   // acomodamos que r apuente a la pagina hermana de p 
            r = t;
        }
        while (terminar == 0) {
            if (r.cont < N && p.cont > N) {
                cambio(r, q, p, i, x);
                r.apunt[r.cont] = p.apunt[0];    // cambio(mover)  la pagina
                cizquierda_apunt(p, 0, p.cont + 1);
                terminar = 1;

            } else if (p.cont < N && r.cont > N) {
                cambio(p, q, r, i, x);
                cderechaApunt(p, 0);
                p.apunt[0] = r.apunt[r.cont + 1];    
                r.apunt[r.cont + 1] = null;
                terminar = 1;
            } else {
                j = r.cont;
                r.info[j++] = q.info[i];
                k = 0;
                while (k < p.cont - 1) {
                    r.info[j++] = p.info[k++];  // reoganizamos la pagina r 
                }
                r.cont = j;
                retirar(q, i);// retiramos la llave de q 
                k = 0;
                j = M - p.cont;                   // pasamos los apuntadores 
                while (p.apunt[k] != null) {
                    r.apunt[j++] = p.apunt[k++];

                }
                p = null;// liberamos a p 
                if (q.cont == 0) {
                    q.apunt[i + 1] = null;
                    if (pila.empty() == true) {
                        q = null;
                    }
                } else {
                    cizquierda_apunt(q, i + 1, q.cont + 1);// corremos los apuntadores de la pagina q
                }
                if (q != null) {
                    if (q.cont >= N) {
                        terminar = 1;
                    } else {
                        t = q;                               //
                        if (!pila.empty()) {                 //
                            objeto = (Componente) pila.pop();///
                            q = objeto.s;                    /// actualizar apuntadores 
                            i = objeto.v;                    ///
                            if (x >= q.info[0]) {            ///
                                p = t;                       ///
                                r = q.apunt[i - 1];
                                i = i - 1;
                            } else {
                                r = t;
                                p = q.apunt[i + 1];
                            }
                        } else {
                            terminar = 1;
                        }
                    }
                } else {
                    terminar = 1;
                    raiz = r;
                }
            }
        }
    }

    /*
     * Metodo retirar del arbol B
     * @param x
     * @return 
     */
    int retirarB(int x) {
        int s = 1, posicion = 0, i, k;
        Pagina p, q, r, t;
        Stack pila = new Stack();//se crea la pila para  indicar el camino que que se debe recorrrer para llegar a la pagina donde esta x
        Componente objeto = new Componente();
        posicion = esta(raiz, x, pila);// guarda las direcciones de las pagina y la posicionde cada pagina 
        if (posicion == -1) {
            s = 0;//la llave no existe en el arbol
        } else {
            objeto = (Componente) pila.pop(); 
            p = objeto.s;// en la pagina guarda los apuntadores de la pila 
            i = objeto.v;// guarda la  posiciones de la pila 
            if (!hoja(p)) {// si la pagina es hoja o no 
                t = p;
                k = i;
                pila.push(new Componente(p, i + 1));
                p = p.apunt[i + 1];
                while (p != null) {
                    pila.push(new Componente(p, 0));
                    p = p.apunt[0];
                }
                objeto = (Componente) pila.pop();
                p = objeto.s;
                i = objeto.v;
                t.info[k] = p.info[0];
                x = p.info[0];
                posicion = 0;
            }
            if (p.cont > N) {
                retirar(p, posicion);// retirar la llave de la posicin que se encuentra x de la pagina indicada 
            } else {
                if (!pila.empty()) {
                    objeto = (Componente) pila.pop();
                    q = objeto.s;// guarda los apuntadores de la pila en q
                    i = objeto.v;
                    if (i < q.cont) {
                        r = q.apunt[i + 1];//r apunta a la pagina derecha de p  
                        if (r.cont > N) {// examinamos la pagina herna derecha
                            retirar(p, posicion);
                            cambio(p, q, r, i, x);
                        } else {
                            if (i != 0) {
                                r = q.apunt[i - 1];
                                if (r.cont > N) {// examina la pgina hermana derecha 
                                    retirar(p, posicion);
                                    cambio(p, q, r, i - 1, x); 
                                } else {
                                    unir( q, r, p, i - 1, pila, x, posicion);
                                }
                            } else {
                                unir( q, r, p, i, pila, x, posicion);
                            }
                        }
                    } else {
                        r = q.apunt[i - 1];
                        if (r.cont > N) {
                            retirar(p, posicion);
                            cambio(p, q, r, i - 1, x);
                        } else {
                            unir(q, r, p, i - 1, pila, x, posicion);
                        }
                    }
                } else {
                    retirar(p, posicion);
                    if (p.cont == 0) {
                        raiz = null;
                    }
                }
            }
        }
        return s;
    }

}
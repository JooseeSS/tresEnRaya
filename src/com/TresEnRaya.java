package com;

import java.util.*;

public class TresEnRaya {
    
    static Scanner sc = new Scanner(System.in);
    static char[][] tablero = new char[3][3];
    static char jugadorActual = 'X';

    public static void main(String[] args) {
        int opcion;
        
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            
            switch(opcion) {
                case 1:
                    jugar();
                    break;
                case 2:
                    mostrarInstrucciones();
                    break;
                case 3:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while(opcion != 3);
        
        sc.close();
    }
    
    public static void mostrarMenu() {
    	System.out.println();
        System.out.println("=== TRES EN RAYA ===");
        System.out.println("1. Jugar");
        System.out.println("2. Instrucciones");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    public static void mostrarInstrucciones() {
    	System.out.println();
        System.out.println("=== INSTRUCCIONES ===");
        System.out.println("1. Dos jugadores (X y O) alternan turnos");
        System.out.println("2. Elige una posición del tablero (0-2 para fila y columna)");
        System.out.println("3. Gana quien consiga 3 en línea (horizontal, vertical o diagonal)");
        System.out.println("4. Si se llena el tablero sin ganador, es empate");
    	System.out.println();
        System.out.println("Posiciones del tablero:");
        System.out.println("0,0 | 0,1 | 0,2");
        System.out.println("1,0 | 1,1 | 1,2");
        System.out.println("2,0 | 2,1 | 2,2");
    }
    
    public static void jugar() {
        inicializarTablero();
        jugadorActual = 'X';
        boolean juegoActivo = true;
        
    	System.out.println();
        System.out.println("¡Nueva partida!");
        
        while (juegoActivo) {
            mostrarTablero();
            realizarJugada();
            
            if (hayGanador()) {
                mostrarTablero();
                System.out.println("¡Jugador " + jugadorActual + " ha ganado!");
                juegoActivo = false;
            } else if (tableroLleno()) {
                mostrarTablero();
                System.out.println("¡Empate!");
                juegoActivo = false;
            } else {
                
                jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
            }
        }
    }
    
    public static void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }
    
    public static void mostrarTablero() {
    	System.out.println();
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---------");
        }
        System.out.println();
    }
    
    public static void realizarJugada() {
        int fila, columna;
        boolean jugadaValida = false;
        
        do {
            System.out.print("Jugador " + jugadorActual + ", introduce fila (0-2): ");
            fila = sc.nextInt();
            System.out.print("Jugador " + jugadorActual + ", introduce columna (0-2): ");
            columna = sc.nextInt();
            
            if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3) {
                if (tablero[fila][columna] == ' ') {
                    tablero[fila][columna] = jugadorActual;
                    jugadaValida = true;
                } else {
                    System.out.println("¡Casilla ocupada! Elige otra.");
                }
            } else {
                System.out.println("Posición no válida. Usa números entre 0 y 2.");
            }
        } while (!jugadaValida);
    }
    
    public static boolean hayGanador() {
        
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
        }
        
       
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] != ' ' && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return true;
            }
        }
        
      
        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;
        }
        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true;
        }
        
        return false;
    }
    
    public static boolean tableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
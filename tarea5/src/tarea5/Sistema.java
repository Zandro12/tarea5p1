package tarea5;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aleja
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import objetos.Persona;
import objetos.Regalo;

public class Sistema {
    private ArrayList<Regalo> listaRegalos;
    private ArrayList<Persona> listaPersonas;

    public Sistema() {
        listaRegalos = new ArrayList<>();
        listaPersonas = new ArrayList<>();
    }

    public void insertarRegalo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del regalo:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el precio del regalo:");
        double precio = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingrese la categoria del regalo (nino, nina, mujer, hombre):");
        String categoria = sc.nextLine();
        Regalo regalo = new Regalo(nombre, precio, categoria);
        listaRegalos.add(regalo);
        System.out.println("Regalo insertado exitosamente!");
    }

    public void insertarPersona() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cedula de la persona:");
        String cedula = sc.nextLine();
        System.out.println("Ingrese el nombre de la persona:");
        String nombre = sc.nextLine();
        Persona persona = new Persona(cedula, nombre);
        listaPersonas.add(persona);
        System.out.println("Persona insertada exitosamente");
    }

    public void comprarRegalo() {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Ingrese la cedula de la persona:");
    String cedula = sc.nextLine();

    Persona cliente = buscarPersona(cedula);
    if (cliente == null) {
        System.out.println("Cedula no registrada. No se puede continuar.");
        return;
    }

    int edad = -1;
    while (edad < 0) {
        try {
            System.out.println("Ingrese la edad de la persona:");
            if (sc.hasNextInt()) {
                edad = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Entrada invalida, Debes ingresar un numero entero para la edad.");
                sc.next();
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida, Intente nuevamente.");
            sc.nextLine();
        }
    }

    int opcionGenero = -1;
    while (opcionGenero != 1 && opcionGenero != 2) {
        try {
            System.out.println("Seleccione el genero de los regalos: 1. Masculino 2. Femenino");
            if (sc.hasNextInt()) {
                opcionGenero = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Entrada invalida, Debe ingresar 1 o 2.");
                sc.next(); 
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Intente nuevamente.");
            sc.nextLine();
        }
    }

    String genero = (opcionGenero == 1) ? "hombre" : "mujer";

    mostrarRegalosPorCategoria(edad, genero);

    System.out.println("Ingrese el nombre del regalo que desea comprar:");
    String nombreRegalo = sc.nextLine();
    Regalo regaloSeleccionado = buscarRegalo(nombreRegalo);
    if (regaloSeleccionado == null) {
        System.out.println("Regalo no disponible.");
        return;
    }

    int cantidad = -1;
    while (cantidad < 0) {
        try {
            System.out.println("Ingrese la cantidad:");
            if (sc.hasNextInt()) {
                cantidad = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Entrada invalida. Debe ingresar un numero entero para la cantidad.");
                sc.next(); 
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Intente nuevamente.");
            sc.nextLine(); 
        }
    }

    double total = regaloSeleccionado.getPrecio() * cantidad;
    System.out.println("Compra realizada:");
    System.out.println("Cedula: " + cliente.getCedula());
    System.out.println("Nombre: " + cliente.getNombre());
    System.out.println("Regalo: " + regaloSeleccionado.getNombre());
    System.out.println("Precio unitario: " + regaloSeleccionado.getPrecio());
    System.out.println("Cantidad: " + cantidad);
    System.out.println("Total a pagar: " + total);
}


    private Persona buscarPersona(String cedula) {
        for (Persona p : listaPersonas) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    private Regalo buscarRegalo(String nombre) {
        for (Regalo r : listaRegalos) {
            if (r.getNombre().equals(nombre)) {
                return r;
            }
        }
        return null;
    }

    private void mostrarRegalosPorCategoria(int edad, String genero) {
        String categoria = "";
        if (edad <= 12) {
            categoria = (genero.equals("hombre")) ? "nino" : "nina";
        } else if (edad >= 13 && edad <= 17) {
            categoria = genero;
        }

        System.out.println("Regalos disponibles para " + categoria + ":");
        for (Regalo r : listaRegalos) {
            if (r.getCategoria().equals(categoria)) {
                System.out.println(r);
            }
        }
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu principal:");
            System.out.println("1. Insertar regalos");
            System.out.println("2. Insertar personas");
            System.out.println("3. Comprar regalos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> insertarRegalo();
                case 2 -> insertarPersona();
                case 3 -> comprarRegalo();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 4);
    }
}

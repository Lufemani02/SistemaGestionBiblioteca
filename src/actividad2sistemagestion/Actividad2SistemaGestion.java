package actividad2sistemagestion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Actividad2SistemaGestion {

    public static void main(String[] args) {
        
        ArrayList<String[]> libros = new ArrayList<>();
        LinkedList<String[]> usuarios = new LinkedList<>();
        Stack<String[]> librosPrestados = new Stack<>();
        Queue<String[]> colaEspera = new LinkedList<>();
        
        Scanner entrada = new Scanner(System.in);
        
        int opcion;
        do{
            // Desarrolado por: Luis Fernando Martínez Niño, C.c: 1023025659. 
            System.out.println("=================");
            System.out.println("SISTEMA DE GESTION DE BIBLIOTECA");
            System.out.println("=================");    
            System.out.println("1. Agregar Libro");    
            System.out.println("2. Registrar Usuario");    
            System.out.println("3. Solicitar Libro");    
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios registrados");
            System.out.println("7. Salir");
            System.out.println("Seleccione una opcion: ");
            
            while(!entrada.hasNextInt()){
                System.out.println(" Error: Ingrese un numero Valido!!");
                entrada.next();
                System.out.println("Seleccione una opcion: ");
            }
            
            opcion = entrada.nextInt();
            entrada.nextLine();
            
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el ID del libro que desea agregar");
                    String idLibro = entrada.nextLine();
                    boolean idDuplicado = false;
                    for(String[] libro: libros){
                        if(libro[0].equals(idLibro)){
                            idDuplicado = true;
                            break;
                        }
                    }
                    
                    if (idDuplicado){
                        System.out.println("Error el ID del libro ya existe");
                    }else{
                        System.out.println("Ingrese el Nombre del libro");
                        String nomLibro = entrada.nextLine();
                        System.out.println("Ingrese el autor del libro");
                        String autorLibro = entrada.nextLine();
                        libros.add(new String[] {idLibro, nomLibro, autorLibro});
                        System.out.println("Libro añadido con éxito");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el documento del usuario (Solo números): ");
                    while(!entrada.hasNextInt()){
                        System.out.println(" Error: Ingrese un numero Valido!!");
                        entrada.next();
                        System.out.println("Ingrese el documento del usuario (Solo números): ");
                    }
                    int cedulaUsuario = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Ingrese el nombre del Usuario");
                    String nombreUsuario = entrada.nextLine();
                    System.out.println("Ingrese los apellidos del Usuario");
                    String apellidosUsuario = entrada.nextLine();
                    
                    boolean cedulaDuplicado = false;
                    for(String[] usuario: usuarios){
                        if(usuario[0].equals(String.valueOf(cedulaUsuario))){
                            cedulaDuplicado = true;
                            break;
                        }
                    }
                    if (cedulaDuplicado){
                        System.out.println("Error la cedula del usuario ya existe");
                    }else{
                        usuarios.add(new String[]{String.valueOf(cedulaUsuario), nombreUsuario, apellidosUsuario});
                        System.out.println("Usuario registrado con éxito");
                    }
                    
                    break;
                case 3:
                    System.out.println("Ingrese el Id del libro que desea tomar prestado");
                    String idPrestar = entrada.nextLine();
                    System.out.println("Ingrese el la cedula del usuario que toma presyado el libro");
                    while(!entrada.hasNextInt()){
                        System.out.println(" Error: Ingrese un numero Valido!!");
                        entrada.next();
                        System.out.println("Ingrese el documento del usuario (Solo números): ");
                    }
                    int cedulaPrestar = entrada.nextInt();
                    entrada.nextLine();
                    
                    boolean libroEncontrado = false;
                    for(String[] libro: libros){
                        if(libro[0].equals(idPrestar)){
                            librosPrestados.push(new String[]{idPrestar, String.valueOf(cedulaPrestar)});
                            libros.remove(libro);
                            libroEncontrado = true;
                            System.out.println("Prestamo completo, recuerda que tienes 7 días para devolverlo");
                            break;
                        }
                    }
                    if(!libroEncontrado){
                        System.out.println("Libro no disponible. ¿Desea agrefarlo a la lista de espera? (si/no)");
                        String respuesta = entrada.nextLine();
                        if(respuesta.equalsIgnoreCase("si")){
                            colaEspera.add(new String[]{idPrestar, String.valueOf(cedulaPrestar)});
                            System.out.println("Agreado a la cola de espera");
                        }
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Gracias por usar la biblioteca, hasta pronto");
                    break;
            }            
        }while (opcion != 7);
    
    }
}

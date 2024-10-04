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
            System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''");
            System.out.println("'' Developed by: Luis Fernando Martinez Nino ''");
            System.out.println("''           Cedula: 1023025659               ''");
            System.out.println("''    SISTEMA DE GESTION DE BIBLIOTECA        ''");
            System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''");    
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
                        System.out.println("Libro añadido con exito");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el documento del usuario (Solo numeros): ");
                    while(!entrada.hasNextInt()){
                        System.out.println(" Error: Ingrese un numero Valido!!");
                        entrada.next();
                        System.out.println("Ingrese el documento del usuario (Solo numeros): ");
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
                        System.out.println("Usuario registrado con exito");
                    }
                    
                    break;
                case 3:
                    System.out.println("Ingrese el Id del libro que desea tomar prestado");
                    String idPrestar = entrada.nextLine();
                    System.out.println("Ingrese la cedula del usuario que toma presyado el libro");
                        while(!entrada.hasNextInt()){
                            System.out.println(" Error: Ingrese un numero Valido!!");
                            entrada.next();
                            System.out.println("Ingrese el documento del usuario (Solo números): ");
                        }
                    int cedulaPrestar = entrada.nextInt();
                    entrada.nextLine();
                    
                    boolean usuarioRegistrado = false;
                    for(String[] usuario:usuarios){
                        if(usuario[0].equals(String.valueOf(cedulaPrestar))){
                            usuarioRegistrado = true;
                            break;
                        }
                    }
                    if(!usuarioRegistrado){
                        System.out.println("Error, el usuario con la Cedula:" +" " + cedulaPrestar + 
                        " " +"   No esta registrado, por favor registrese para solicitar el prestamo");
                    }else{
                        boolean libroEncontrado = false;
                        for(String[] libro: libros){
                            if(libro[0].equals(idPrestar)){
                                librosPrestados.push(new String[]{idPrestar, libro[1], libro[2], String.valueOf(cedulaPrestar)});
                                libros.remove(libro);
                                libroEncontrado = true;
                                System.out.println("Prestamo completo, recuerda que tienes 7 dias para devolverlo");
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
                    }
                    break;
                case 4:
                    if(!librosPrestados.isEmpty()){
                        String[] libroDevuelto = librosPrestados.pop();
                        libros.add(new String[]{libroDevuelto[0], libroDevuelto[1], libroDevuelto[2]});
                        System.out.println("Libro devuelto exitosamente");
                    }
                    if(!colaEspera.isEmpty()){
                        String[] proximosEnCola = colaEspera.poll();
                        System.out.println("El usuario con cedula" + proximosEnCola[1] +
                        "Esta en cola y ahora tomara prestado el libro con ID" + proximosEnCola[0]);
                        librosPrestados.push(proximosEnCola);
                    }
                    else{
                        System.out.println("No hay libros disponibles");
                    }
                    break;
                case 5:
                    if(libros.isEmpty()){
                        System.out.println("No hay libros disponibles");
                    }else{
                        System.out.println("'''''''' Libros Disponibles ''''''''''");
                        System.out.printf("%-10s %-20s %-20s%n", "ID" , "Nombre", "Autor");
                        for(String[] libro: libros){
                            System.out.printf("%-10s %-20s %-20s%n", libro[0] , libro[1], libro[2]);
                        }
                    }
                    break;
                case 6:
                    if(usuarios.isEmpty()){
                        System.out.println("No hay Usuarios disponibles");
                    }else{
                        System.out.println("******* Usuarios Disponibles *******");
                        System.out.printf("%-10s %-15s %-20s%n", "Cedula" , "Nombre", "Apellidos");
                        for(String[] usuario: usuarios){
                            System.out.printf("%-10s %-15s %-20s%n", usuario[0] , usuario[1], usuario[2]);
                        }
                    }
                    break;
                case 7:
                    System.out.println("Gracias por usar la biblioteca, hasta pronto");
                    break;
                default:
                    System.out.println("Opcion no valida, por favor intente de nuevo");
                    break;
            }            
        }while (opcion != 7);  
    }
}

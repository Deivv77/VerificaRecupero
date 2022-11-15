package it.fi.meucci;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {
    static ArrayList <Socket> list_socket = new ArrayList<Socket>();
    static ArrayList <Thread> list_thread = new ArrayList<Thread>();
    static ServerSocket server;
    static ArrayList <Persona> lista_persone = new ArrayList<Persona>();
   

    public MultiServer()
    {
        
 
    }
    

    public void beginning()
    {
        try {
           server = new ServerSocket(7073);
          
        for(;;)
        {
            System.out.println("---Server acceso---");
            Socket socket = server.accept();
            list_socket.add(socket);
            System.out.println("Server socket " + socket);
            ServerThread server_thread = new ServerThread(socket);
            list_thread.add(server_thread);
            server_thread.start();

        }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
        
    }
}

   

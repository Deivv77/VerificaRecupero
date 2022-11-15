package it.fi.meucci;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ClientStr {
    String name_server = "localhost";
    int serverport = 7073;
    Socket mysocket;
    BufferedReader keyboard;
    String user;
    String received;
    DataOutputStream output;
    BufferedReader input;


    public Socket connect()
    {

        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            mysocket = new Socket(name_server, serverport);
            output = new DataOutputStream(mysocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
      
            


        } catch (UnknownHostException e) {
            // TODO: handle exception
            System.err.println("Unknowed host");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Error during the connection");
            System.exit(1);
        }
        return mysocket;
        
    }

    public void comunicate()
    {
        
        try {
           
            
                ObjectMapper objectMapper = new ObjectMapper();

           

                ArrayList <Persona> persona_scelta = new ArrayList<>();
                System.out.println("Scegli il nome  delle persone da aggiungere " + '\n');
                user = keyboard.readLine();
                String scelta[] = user.split(" ");
    
                System.out.println("Scegli i cognomi"+ '\n');
                String u = keyboard.readLine();
                String cognomi[] = u.split(" ");
    
                System.out.println("Scegli la nazione"+ '\n');
                String us = keyboard.readLine();
                String nazioni[] = us.split(" ");
    
                for(int i = 0; i < scelta.length; i++)
                {
                    Persona a = new Persona(scelta[i],cognomi[i],nazioni[i]);
                    persona_scelta.add(a);
                }
    
                System.out.println("Scrivi la nazione richiesta");
                String nazione = keyboard.readLine();
    
                Messaggio messaggio = new Messaggio(nazione ,persona_scelta);
                output.writeBytes(objectMapper.writeValueAsString(messaggio)+ '\n');

                if(nazione.equals(null) && persona_scelta.size() == 0)
                {
                    received = input.readLine();
                    Messaggio m = objectMapper.readValue(received, Messaggio.class);
        
                    for(int i = 0; i<m.persone.size(); i++)
                    {
                        System.out.println("Elenco totale:");
                        System.out.println("Nome: " + m.persone.get(i).getNome() + " Cognome: "  + m.persone.get(i).getNome());
                    }
                    mysocket.close();
                }
                else if(persona_scelta.size() == 0)
                {
                    received = input.readLine();
                    Messaggio m = objectMapper.readValue(received, Messaggio.class);
        
                    for(int i = 0; i<m.persone.size(); i++)
                    {
                        System.out.println("Elenco per nazione scelta:");
                        System.out.println("Nome: " + m.persone.get(i).getNome() + " Cognome: "  + m.persone.get(i).getNome());
                       
                    }
                    mysocket.close();
                }
                else
                {
                    mysocket.close();
                }
            
           
            
 
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.out.println("Error during the connection");
            System.exit(1);
        }
        

    } 
}
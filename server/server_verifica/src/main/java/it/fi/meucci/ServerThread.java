package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String received = null;
    String modified = null;
    BufferedReader input;
    DataOutputStream output;
 

    public ServerThread (Socket socket)
    {
        this.client = socket;
       
        
    }

    public void run()
    {    
         try {
            comunicate();
        } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace(System.out);
        }
    }
    public void comunicate() throws Exception{
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new DataOutputStream(client.getOutputStream());
        for(;;)
        {
            
            ObjectMapper objectMapper = new ObjectMapper();
            received = input.readLine();
            Messaggio ricevuto = objectMapper.readValue(received, Messaggio.class);
            

            if(ricevuto.getNazione_richiesta().equals(null))
            {

                for(int i = 0 ; i< ricevuto.persone.size(); i++)
                {
                    MultiServer.lista_persone.add(ricevuto.persone.get(i));
                   
                }
               Messaggio m = new Messaggio(null, null);
               output.writeBytes(objectMapper.writeValueAsString(m) + '\n');
            }
            else if(ricevuto.getPersone().size()==0)
            {
                ArrayList <Persona> persona_per_nazione = new ArrayList<Persona>();
                for(int i = 0 ; i< MultiServer.lista_persone.size(); i++)
                {
                    if(ricevuto.getNazione_richiesta().equals(MultiServer.lista_persone.get(i).getNazioneDiResidenza()))
                    {
                        persona_per_nazione.add(MultiServer.lista_persone.get(i));
                    }
                }
               Messaggio m = new Messaggio(ricevuto.getNazione_richiesta(), persona_per_nazione);
               output.writeBytes(objectMapper.writeValueAsString(m) + '\n');
                
                
            }
            else 
            {
                
             
                Messaggio messaggio2 = new Messaggio(null,MultiServer.lista_persone);
                output.writeBytes(objectMapper.writeValueAsString(messaggio2) + '\n');
            }
            
        
        }
        
     
        
    }
}


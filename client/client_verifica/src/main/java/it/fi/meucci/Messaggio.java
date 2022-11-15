package it.fi.meucci;

import java.util.ArrayList;

public class Messaggio {
    private String nazione_richiesta;
    ArrayList <Persona> persone;

    


    public Messaggio() {
    }

    
    public Messaggio(String nazione_richiesta, ArrayList<Persona> persone) {
        this.nazione_richiesta = nazione_richiesta;
        this.persone = persone;
    }
    public String getNazione_richiesta() {
        return nazione_richiesta;
    }
    public void setNazione_richiesta(String nazione_richiesta) {
        this.nazione_richiesta = nazione_richiesta;
    }
    public ArrayList<Persona> getPersone() {
        return persone;
    }
    public void setPersone(ArrayList<Persona> persone) {
        this.persone = persone;
    }

    
}

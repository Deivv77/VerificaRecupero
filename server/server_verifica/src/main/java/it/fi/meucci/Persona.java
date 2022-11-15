package it.fi.meucci;

public class Persona {
   private String nome;
   private String cognome;
   private String nazioneDiResidenza;

   
public Persona(String nome, String cognome, String nazioneDiResidenza) {
    this.nome = nome;
    this.cognome = cognome;
    this.nazioneDiResidenza = nazioneDiResidenza;
}

public Persona() {
}

public String getNome() {
    return nome;
}
public void setNome(String nome) {
    this.nome = nome;
}
public String getCognome() {
    return cognome;
}
public void setCognome(String cognome) {
    this.cognome = cognome;
}
public String getNazioneDiResidenza() {
    return nazioneDiResidenza;
}
public void setNazioneDiResidenza(String nazioneDiResidenza) {
    this.nazioneDiResidenza = nazioneDiResidenza;
}

   
}

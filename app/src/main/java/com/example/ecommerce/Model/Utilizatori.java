package com.example.ecommerce.Model;

public class Utilizatori
{
    private String nume,telefon,parola;

    public Utilizatori()
    {

    }

    public Utilizatori(String nume, String telefon, String parola) {
        this.nume = nume;
        this.telefon = telefon;
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}

package com.example.ecommerce.Model;

public class Produse
{
    private String produsnume,descriere,pret,imagine,categorii,produsid,data,ora;

    public Produse()
    {

    }

    public Produse(String produsnume, String descriere, String pret, String imagine, String categorii, String produsid, String data, String ora) {
        this.produsnume = produsnume;
        this.descriere = descriere;
        this.pret = pret;
        this.imagine = imagine;
        this.categorii = categorii;
        this.produsid = produsid;
        this.data = data;
        this.ora = ora;
    }


    public String getProdusnume() {
        return produsnume;
    }

    public void setProdusnume(String produsnume) {
        this.produsnume = produsnume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    public String getCategorii() {
        return categorii;
    }

    public void setCategorii(String categorii) {
        this.categorii = categorii;
    }

    public String getProdusid() {
        return produsid;
    }

    public void setProdusid(String produsid) {
        this.produsid = produsid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }
}

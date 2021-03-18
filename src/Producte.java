public class Producte {
    private int IDPRODUCTE;
    private String nom;
    private float preu;

    public Producte(int i, String n, float p){
        IDPRODUCTE= i;
        nom=n;
        preu = p;
    }

    public int getIDPRODUCTE() {
        return IDPRODUCTE;
    }

    public String getNom() {
        return nom;
    }

    public float getPreu() {
        return preu;
    }
}

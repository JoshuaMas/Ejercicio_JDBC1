import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.InflaterOutputStream;

public class GestorEncarrecs {
    GestorBD gestor;
    BufferedReader entrada;
    public static void main(String[] args) throws Exception {
        GestorEncarrecs gbd = new GestorEncarrecs();
        gbd.start();
    }
    public GestorEncarrecs() throws Exception{
        gestor = new GestorBD();
        entrada = new BufferedReader(new InputStreamReader(System.in));
    }
    public void start() throws Exception{
        int opcio;
        while (0 != (opcio=menuPrincipal())){
            try{
                switch (opcio){
                    case 1:
                        cercarClient();
                        break;
                    case 2:
                        afegirClient();
                        break;
                    case 3:
                        cercarProducte();
                        break;
                    case 4:
                        afegirProducte();
                        break;
                    case 5:
                        llistarTotselsProductes();
                        break;
                    default: mostrarDades("Opció incorrecta\n");
                }
            }catch (Exception ex){
                mostrarDades("Opció incorrecta\n");
            }
        }
        gestor.tancar();
    }
    private int menuPrincipal() throws Exception{
        String menu = "\nQuina acció vols realitzar?\n" + "[1] Cercar client\n" + "" +
                "[2] Afegir client\n"+ "[3] Cercar producte\n"+ "[4] Afegir producte\n"+ "[5] Llistar tots els productes\n" + "[0] Sortir\n" + "Opció>";
        String lin=entrarDades(menu);
        try{
            int opcio = Integer.parseInt(lin); return opcio;
        }catch (Exception ex){ return -1;}
    }

    /** Amb els metodes entrarDades i mostrarDades, fem el codi independent de la interficie.
     *    Si mai es fan canvis, nomes cal canviar aquests dos metodes */
    private String entrarDades() throws IOException{
        String linia = entrada.readLine();
        if ("".equals(linia)) return null;
        return linia;
    }
    private String entrarDades(String pregunta) throws IOException{
        mostrarDades(pregunta);
        return entrarDades();
    }

    private void mostrarDades(String dades) throws IOException{
        System.out.print(dades);
    }
    //Cercar un element d'acord al seu nom
    private void cercarClient() throws  Exception{
        String nom = entrarDades("Introdueix el nom del client: "); if (null == nom) return;
        List<Client> llista = gestor.cercarClient(nom);
        Iterator it = llista.iterator();
        mostrarDades("Els clients trobats amb aquest nom son:\n−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−\n");
        while (it.hasNext()){
            Client c = (Client)it.next();
            mostrarDades(c.toString() + "\n");
            start();
        }
    }
    private void cercarProducte() throws  Exception{
        String nom = entrarDades("Introdueix el nom del producte: "); if (null == nom) return;
        List<Producte> llista = gestor.cercarProducte(nom);
        Iterator it = llista.iterator();
        mostrarDades("Els productes trobats amb aquest nom son:\n−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−\n");
        while (it.hasNext()){
            Producte p = (Producte) it.next();
            mostrarDades(p.toString() + "\n");
            start();
        }
    }

    public void afegirClient() throws Exception {
        mostrarDades("Introduex les seguents dades del nou usari (seixa en blanc" +
                " per sortir).\n");
        String nom = entrarDades("Nom: "); if (null == nom) return;
        String apostal = entrarDades("Adreça postal: "); if (null == apostal)
            return;
        String aelectronica = entrarDades("E-mail: "); if (null == aelectronica)
            return;
        String telefon = entrarDades("Telefon: "); if (null == telefon) return;
        int id = gestor.obtenirNouIDClient();
        gestor.afegirClient(new Client(id, nom, apostal, aelectronica, telefon));
        mostrarDades("Operació completada satisfactòriament.\n");
    }
    public void afegirProducte() throws Exception {
        mostrarDades("Introduex les seguents dades del nou usari (seixa en blanc" +
                " per sortir).\n");
        String nom = entrarDades("Nom: "); if (null == nom) return;
        float preu = Float.parseFloat( entrarDades("preu: ")); if (0 == preu)
            return;
        int id = gestor.obtenirnouIDPRODUCTE();
        gestor.afegirProducte(new Producte(id, nom, preu));
        mostrarDades("Operació completada satisfactòriament.\n");
    }
    public void llistarTotselsProductes() throws Exception {
        gestor.llistaTotselsProductes();
        mostrarDades("Operació completada satisfactòriament.\n");
    }

    public void afegirEncarrec() throws Exception {
        mostrarDades("Introduex les seguents dades del nou usari (seixa en blanc" +
                " per sortir).\n");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date data =format.parse(entrarDades("Data: ")); if (null==data) return;
        int id_c = Integer.parseInt( entrarDades("preu: ")); if (0 == id_c)
            return;
        int id_e = gestor.obtenirnouIDENCARREC();
        gestor.afegirEncarrec(new Encarrec(id_e, (Timestamp) data,id_c ));
        mostrarDades("Operació completada satisfactòriament.\n");
    }

}
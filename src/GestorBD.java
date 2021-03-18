import java.util.*;
import java.sql.*;

public class GestorBD {
    Connection conn;

    public GestorBD() throws Exception{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/programacionproductos",
                "root", "Cide2050");
    }

    public void tancar() throws Exception{
        conn.close();
    }

    public int obtenirNouIDClient() throws Exception{
        Statement cercaMaxId = conn.createStatement();
        ResultSet r = cercaMaxId.executeQuery("Select MAX(ID) FROM CLIENTS");
        if (r.next()) return (1 + r.getInt(1));
        else return 1;
    }

    public List<Client> cercarClient(String nom) throws Exception {
        Statement cerca = conn.createStatement();
        ResultSet r = cerca.executeQuery("SELECT * FROM CLIENTS WHERE NOM='" + nom
                + "'");
        LinkedList<Client> llista = new LinkedList<Client>();
        while (r.next()){
            llista.add(new Client(r.getInt("ID"), r.getString("NOM"), r.getString(
                    "APOSTAL"), r.getString("AELECTRONICA"), r.getString("TELEFON")));
        }
        return llista;
    }

    public void afegirClient(Client c) throws Exception{
        Statement update = conn.createStatement();
        String valors = c.getId() + ",'" + c.getNom() + "','" + c.getApostal() + "" +
                "','" + c.getAElectronica() + "','" + c.getTelefon() + "'";
        update.executeUpdate("INSERT INTO CLIENTS VALUE(" + valors + ")");
    }
    public void afegirProducte(Producte p) throws Exception{
        Statement update = conn.createStatement();
        String valors = p.getIDPRODUCTE() + ",'" + p.getNom() + "','" + p.getPreu() +"'";
        update.executeUpdate("INSERT INTO PRODUCTES VALUE(" + valors + ")");

    }
    public List<Producte> cercarProducte(String nom) throws Exception {
        Statement cerca = conn.createStatement();
        ResultSet r = cerca.executeQuery("SELECT * FROM PRODUCTES WHERE NOM='" + nom
                + "'");
        LinkedList<Producte> llista = new LinkedList<Producte>();
        while (r.next()){
            llista.add(new Producte(r.getInt("IDPRODUCTE"), r.getString("NOM"), r.getFloat("PREU")));
        }
        return llista;
    }
    public void afegirEncarrec(Encarrec e) throws Exception{
        Statement update = conn.createStatement();
        String valors = e.getIDENCARREC() + ",'" + e.getDATA() + "','" + e.getIDCLIENT() +"'";
        update.executeUpdate("INSERT INTO ENCARRECS VALUE(" + valors + ")");
    }
    public void llistaTotselsProductes() throws Exception {
        Statement cerca = conn.createStatement();
        ResultSet r = cerca.executeQuery("SELECT * FROM PRODUCTES ");
        while (r.next()){
            System.out.println("|| ID ==> " + r.getInt("IDPRODUCTE") + " || Nom ==> " + r.getString("NOM")
            + " || PREU ==> " + r.getFloat("PREU"));
        }
    }
    public int obtenirnouIDPRODUCTE() throws Exception{
        Statement cercaMaxIDPRODUCTE = conn.createStatement();
        ResultSet r = cercaMaxIDPRODUCTE.executeQuery("Select MAX(IDPRODUCTE) FROM PRODUCTES");
        if (r.next()) return (1 + r.getInt(1));
        else return 1;
    }
    public int obtenirnouIDENCARREC() throws Exception{
        Statement cercaMaxIDENCARREC = conn.createStatement();
        ResultSet r = cercaMaxIDENCARREC.executeQuery("Select MAX(IDENCARREC) FROM ENCARRECS");
        if (r.next()) return (1 + r.getInt(1));
        else return 1;
    }
    public Timestamp covert(java.util.Date data){
        Timestamp dataSql=new Timestamp(data.getTime());
        return dataSql;
    }
}

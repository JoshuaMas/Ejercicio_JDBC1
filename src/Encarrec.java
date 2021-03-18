import java.sql.Timestamp;
import java.util.Date;

public class Encarrec {
    private int IDENCARREC;
    Timestamp DATA;
    private int IDCLIENT;

    public Encarrec(int i, Timestamp d, int i2){
        IDENCARREC= i;
        DATA=d;
        IDCLIENT = i2;
    }

    public int getIDENCARREC() {
        return IDENCARREC;
    }

    public Timestamp getDATA() {
        return DATA;
    }

    public int getIDCLIENT() {
        return IDCLIENT;
    }
}

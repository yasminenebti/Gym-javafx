package sample;

public class membershipModel {
    int idMc;
    String start_date;
    String end_date;
    String idCo;
    String idMb;
    public membershipModel(){
        super();
    }

    public membershipModel(int idMc , String start_date, String end_date , String idCo , String idMb){
        this.idMc=idMc;
        this.start_date=start_date;
        this.end_date=end_date;
        this.idCo=idCo;
        this.idMb=idMb;

    }

    public int getIdMc() {
        return idMc;
    }

    public void setIdMc(int idMc) {
        this.idMc = idMc;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getIdCo() {
        return idCo;
    }

    public void setIdCo(String idCo) {
        this.idCo = idCo;
    }

    public String getIdMb() {
        return idMb;
    }

    public void setIdMb(String idMb) {
        this.idMb = idMb;
    }
}

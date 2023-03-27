package sample;

public class coachModel {
    int idC;
    String nameC;
    String mailC;
    String phoneC;
    public coachModel(){
        super();
    }
    public coachModel(int idC , String nameC,String phoneC ,String mailC ){
        this.idC=idC;
        this.nameC=nameC;
        this.phoneC=phoneC;
        this.mailC=mailC;

    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public String getMailC() {
        return mailC;
    }

    public void setMailC(String mailC) {
        this.mailC = mailC;
    }

    public String getPhoneC() {
        return phoneC;
    }

    public void setPhoneC(String phoneC) {
        this.phoneC = phoneC;
    }
}

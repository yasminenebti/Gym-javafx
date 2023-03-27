package sample;

public class memberModel {
    int idM;
    String nameM;
    String phoneM;
    String mailM;
    public memberModel(){
        super();
    }
    public memberModel(int idM , String nameM,String phoneM ,String mailM){
        this.idM=idM;
        this.nameM=nameM;
        this.phoneM=phoneM;
        this.mailM=mailM;
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public String getNameM() {
        return nameM;
    }

    public void setNameM(String nameM) {
        this.nameM = nameM;
    }

    public String getPhoneM() {
        return phoneM;
    }

    public void setPhoneM(String phoneM) {
        this.phoneM = phoneM;
    }

    public String getMailM() {
        return mailM;
    }

    public void setMailM(String mailM) {
        this.mailM = mailM;
    }
}

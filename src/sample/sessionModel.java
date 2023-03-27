package sample;


public class sessionModel {
    int idA;
    String nameA;
    String Duration;
    public sessionModel(){
        super();
    }
    public sessionModel(int idA , String nameA,String Duration){
        this.idA=idA;
        this.nameA=nameA;
        this.Duration=Duration;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }
}

import java.util.Random;

public class Student implements Runnable, Comparable<Student> {

    private int id;
    private String name;
    private int vremePotrebnoZaOdbranu;
    private int vremeDolaskaNaOdbranu;
    private int ocena;
    private boolean zapoceo;
    
    long trenutnoVreme = System.currentTimeMillis();

    public Student(int id) {
        Random random = new Random();
        this.id = id;
        this.name = "Student " + id;
        this.vremePotrebnoZaOdbranu = random.nextInt(500) + 500;
        this.vremeDolaskaNaOdbranu = random.nextInt(1000) + 1;
        this.zapoceo = false;
    }


    @Override
    public int compareTo(Student o) {
        return Integer.compare(o.getVremeDolaskaNaOdbranu(), this.getVremeDolaskaNaOdbranu());
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public void run() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVremePotrebnoZaOdbranu() {
        return vremePotrebnoZaOdbranu;
    }

    public void setVremePotrebnoZaOdbranu(int vremePotrebnoZaOdbranu) {
        this.vremePotrebnoZaOdbranu = vremePotrebnoZaOdbranu;
    }

    public int getVremeDolaskaNaOdbranu() {
        return vremeDolaskaNaOdbranu;
    }

    public void setVremeDolaskaNaOdbranu(int vremeDolaskaNaOdbranu) {
        this.vremeDolaskaNaOdbranu = vremeDolaskaNaOdbranu;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        synchronized (this){
            this.ocena = ocena;
        }
    }

    public boolean isZapoceo() {
        return zapoceo;
    }

    public void setZapoceo(boolean zapoceo) {
        this.zapoceo = zapoceo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

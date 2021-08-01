import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {

    private static int brojStudenata = 15;
    private static int brojNeocenjenih = 0;
    private static StringBuilder neocenjeni = new StringBuilder();
    private static float zbirOcena = 0;
    private static float prosecnaOcena;
    private static int brojOcenjenihStudenata = 0;

    private static Stack<Student> studentiStack;
    private static ArrayList<Student> profesorStudenti = new ArrayList<>();
    private static ArrayList<Student> asistentStudenti = new ArrayList<>();

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {

        studentiStack = new Stack<>();
        Random random = new Random();

        List<Student> studenti = new ArrayList<>();

        for (int i = 0; i < brojStudenata; i++){
           studenti.add(new Student(i + 1));
        }

        Collections.sort(studenti);

        studentiStack.addAll(studenti);


        while(!studentiStack.isEmpty()){
            if(studentiStack.size()%2 == 0) {
                profesorStudenti.add(studentiStack.pop());
            }else{
                asistentStudenti.add(studentiStack.pop());
            }
        }


        long start = System.currentTimeMillis();
        long stop = start + 5000;

        ArrayList<Thread> zapocetiTredovi = new ArrayList<>();


        do{
                if (!profesorStudenti.isEmpty()) {
                    if (start + profesorStudenti.get(0).getVremePotrebnoZaOdbranu() < System.currentTimeMillis()) {
                        Student student = profesorStudenti.get(0);
                        profesorStudenti.remove(0);

                        Thread newThread = new Thread(new Profesor(cyclicBarrier, student, start));
                        zapocetiTredovi.add(newThread);
                        newThread.start();
                    }
                }
                if (!asistentStudenti.isEmpty()) {
                    if (start + asistentStudenti.get(0).getVremeDolaskaNaOdbranu() < System.currentTimeMillis()) {
                        Student student = asistentStudenti.get(0);
                        asistentStudenti.remove(0);

                        Thread newThread = new Thread(new Asistent(semaphore, student, start));
                        zapocetiTredovi.add(newThread);
                        newThread.start();
                    }

                }

        }while(System.currentTimeMillis() <= stop);

        zapocetiTredovi.forEach(Thread::interrupt);

        System.out.println('\n' + "Broj studenata koji su stigli da odbrane: " + brojOcenjenihStudenata + ". Prosek ocena: "+ prosecnaOcena);
        System.out.println("Broj neocenjenih: " + brojNeocenjenih);
        System.out.println("Neocenjeni studenti: " + neocenjeni.toString());
        System.out.println("Pocetak: " +  start + " | Kraj: " + stop + "  | Vreme trajanje: " + (System.currentTimeMillis() - start) );
    }

    public static synchronized void dodatiOcenuStudenta(int ocena) {
        zbirOcena += ocena;
        brojOcenjenihStudenata++;
        prosecnaOcena = zbirOcena / (float) brojOcenjenihStudenata;
    }


    public static synchronized void brojNeocenjenih() {
        brojNeocenjenih++;
    }

    public static synchronized void neocenjeni(Student s){
        neocenjeni.append(s.getName() + ", ");
    }


}





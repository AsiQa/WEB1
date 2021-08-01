import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Asistent implements Runnable{

private Semaphore semaphore;
private Student student;
private long start;

    public Asistent(Semaphore semaphore, Student student, long start) {
        this.semaphore = semaphore;
        this.student = student;
        this.start = start;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public void run() {

        student.setZapoceo(true);

        System.out.println("DOSAO || Asistent: " + student.getName() + ", je dosao na red. Vreme: " + System.currentTimeMillis()
                + " | Od starta: " + (System.currentTimeMillis()- start));

        try{
            this.semaphore.acquire();

            float razlika = System.currentTimeMillis()-start;
            System.out.println("POCEO || Asistent: " + student.getName() + ", je usao na ispitivanje. Pocetak: " + razlika + " | Trajanje: " + student.getVremePotrebnoZaOdbranu());

            Thread.sleep(student.getVremePotrebnoZaOdbranu());;

            int ocena = getRandomNumberInRange(5,10);
            student.setOcena(ocena);
            Main.dodatiOcenuStudenta(ocena);

            System.out.println("ZAVRSIO || Asistent: "
                    + student.getName() + ", je zavrsio sa ispitivanjem, ocena: "
                    + student.getOcena() + ". Kraj: " + (System.currentTimeMillis() - start)+ " | Pocetak: " + razlika + " | Trajanje: " + student.getVremePotrebnoZaOdbranu());

            semaphore.release();

        } catch (InterruptedException e) {
            if(student.isZapoceo()) {
                System.out.println("*PREKINUT || Asistent: "  + student.getName() + " je prekinut tokom ispitivanja.");
                Main.brojNeocenjenih();
                Main.neocenjeni(student);
            }
        }
    }
}

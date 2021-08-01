import java.awt.*;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Profesor implements Runnable{

    private CyclicBarrier barrier;
    private Semaphore semaphore;
    private Student student;
    private long start;

    public Profesor(CyclicBarrier barrier, Student student, long start) {
        this.barrier = barrier;
        semaphore = new Semaphore(2);
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

        System.out.println("DOSAO || Profesor: " + student.getName() + " je dosao na red. Vreme: " + System.currentTimeMillis()
                + " | Od starta: " + (System.currentTimeMillis()- start));

        try{
            this.semaphore.acquire();
            barrier.await();

            float razlika = System.currentTimeMillis()-start;
            System.out.println("POCEO || Profesor: " + student.getName() + " je usao na ispitivanje. Pocetak: " + razlika + " | Trajanje: " + student.getVremePotrebnoZaOdbranu());

            Thread.sleep(student.getVremePotrebnoZaOdbranu());;

            int ocena = getRandomNumberInRange(5,10);
            student.setOcena(ocena);
            Main.dodatiOcenuStudenta(ocena);

            System.out.println("ZAVRSIO || Profesor: "
                    + student.getName() + " je zavrsio sa ispitivanjem, ocena: " + student.getOcena() + ". Kraj: " + (System.currentTimeMillis() - start)+ " | Pocetak: " + razlika + " | Trajanje: " + student.getVremePotrebnoZaOdbranu());

            semaphore.release();

        } catch (InterruptedException e) {
            if(student.isZapoceo()) {
                System.out.println("*PREKINUT || Profesor: " + student.getName() + " je prekinut tokom ispitivanja.");
                Main.brojNeocenjenih();
                Main.neocenjeni(student);
            }

        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }
}

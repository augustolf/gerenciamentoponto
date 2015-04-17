package org.lalf.gerenciamentoponto.util;

/**
 * Created by lalf on 4/17/2015.
 */
public class ScheduleWork {

    // carga horária
    private int workload;

    // saldo total
    private int balance;

    // horário de início
    private HourMin start;

    // horário início da pausa
    private HourMin startBreak;

    // horário término da pausa
    private HourMin endBreak;

    // horário de saída
    private HourMin end;

    public ScheduleWork(int workload, int balance, HourMin start, HourMin startBreak, HourMin endBreak, HourMin end) {
        this.workload = workload;
        this.balance = balance;
        this.start = start;
        this.startBreak = startBreak;
        this.endBreak = endBreak;
        this.end = end;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public HourMin getStart() {
        return start;
    }

    public void setStart(HourMin start) {
        this.start = start;
    }

    public HourMin getStartBreak() {
        return startBreak;
    }

    public void setStartBreak(HourMin startBreak) {
        this.startBreak = startBreak;
    }

    public HourMin getEndBreak() {
        return endBreak;
    }

    public void setEndBreak(HourMin endBreak) {
        this.endBreak = endBreak;
    }

    public HourMin getEnd() {
        return end;
    }

    public void setEnd(HourMin end) {
        this.end = end;
    }
}

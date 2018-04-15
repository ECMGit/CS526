package hw7;

public class Process {
    int id;
    Integer process_priority;
    int duration;
    int arrivalTime;

    public Process() {
    }

    public Process(int process_id, Integer process_priority, int duration, int arrivalTime) {
        this.process_priority = process_priority;
        this.id = process_id;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    public Integer getProcess_priority() {
        return process_priority;
    }

    public void setProcess_priority(Integer process_priority) {
        this.process_priority = process_priority;
    }

    public int getProcess_id() {
        return id;
    }

    public void setProcess_id(int process_id) {
        this.id = process_id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

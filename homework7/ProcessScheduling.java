package hw7;

import java.io.File;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;

import hw7.HeapAdaptablePriorityQueue.AdaptablePQEntry;


public class ProcessScheduling {

    //print all process in D
    public static void printProcess(Process printonepro) {

        System.out.println("Id = " + printonepro.id + " priority = " + printonepro.process_priority + " duration = " + printonepro.duration + " arrival time = " + printonepro.arrivalTime);

    }


    //print executing process
    
    public static double printRunningProcess(Process process, int currentTime, double total_wait) {
        total_wait = total_wait + currentTime - process.getArrivalTime();
        System.out.println("Process removed from queue is: id = " + process.id + ", at time " + currentTime + ", wait time = " + (currentTime - process.arrivalTime) + " Total wait time = " + total_wait + "\n" +
                "Process id = " + process.id + "\n" +
                "\tPriority = " + process.process_priority + "\n" +
                "\tArrival = " + process.arrivalTime + "\n" +
                "\tDuration  = " + process.duration + "\n" +
                "Process " + process.getProcess_id() + " finished at time " + (process.duration + currentTime) + "\n");
        return total_wait;
    }


    //refresh the priority of each process when program executed a process, and print each priority decrementing process,
    public static void refreshPrioirty(HeapAdaptablePriorityQueue processPQ, int currentTime) {
        AdaptablePQEntry<Integer, Process> processEntry;
        for (int i = 0; i < processPQ.heap.size(); i++) {
            processEntry = (AdaptablePQEntry<Integer, Process>) processPQ.heap.get(i);
            Process p = processEntry.getValue();
            int waitingTime = currentTime - p.arrivalTime;

            if (waitingTime > 10) {
                Integer updatepriority = p.getProcess_priority() - 1;
                p.setProcess_priority(updatepriority);
                processPQ.replaceKey(processEntry, updatepriority);
                processPQ.replaceValue(processEntry, p);
                System.out.println("Prioirty of process " + p.getProcess_id() + " decremented, New priority is " + updatepriority);
            }
        }

    }


    //run a program which can execute process;
    public static void runProcess(Scanner fileinput){
        //store all the process in a arraylist D;
        ArrayList<Process> processlist = new ArrayList<>();//list D

        //init a new HeapAdaptablePriorityQueue to save process;
        HeapAdaptablePriorityQueue<Integer, Process> processPQ = new HeapAdaptablePriorityQueue<>();//list Q
        AdaptablePQEntry<Integer, Process> processEntry;
        while (fileinput.hasNext()) {
            String s = fileinput.nextLine();
            String[] temparrays = s.split(" ");
            processlist.add(new Process(Integer.valueOf(temparrays[0]), Integer.valueOf(temparrays[1]), Integer.valueOf(temparrays[2]), Integer.valueOf(temparrays[3])));
        }
        //print all process in arraylist;
        Iterator<Process> processlistit = processlist.iterator();
        while (processlistit.hasNext()) {
            printProcess(processlistit.next());
        }


        Process p;
        int process_num = processlist.size();
        boolean running = false;
        //set a stopwatch:currentTime
        int currentTime = 0;
        //init a variable, for save every running process end time;
        int endTime = 0;
        double total_wait = 0.0;
        while (!processlist.isEmpty()) {
            p = processlist.get(0);//Get a process p from D that has the earliest arrival time
            if (p.arrivalTime <= currentTime) {//move earliest process from D into Q;
                p = processlist.remove(0);
                processEntry = (AdaptablePQEntry<Integer, Process>) processPQ.insert(p.process_priority, p);

            }//execute a process which has smallest priority;
            if (!processPQ.isEmpty() && running == false) {
                Process rp = processPQ.removeMin().getValue();
                total_wait = printRunningProcess(rp, currentTime, total_wait);
                endTime = currentTime + rp.duration;//set running process's end time;
                running = true;
            } else {
                currentTime++;
            }
            if (currentTime == endTime) {//when current time = end time, process finished;
                running = false;
                refreshPrioirty(processPQ, currentTime);
            }
        }
        //all the process were removed from D


        //skip running process
        currentTime = endTime;

        //execute remained process in Q
        while (!processPQ.isEmpty()) {
            refreshPrioirty(processPQ, currentTime);
            p = processPQ.removeMin().getValue();
            total_wait = printRunningProcess(p, currentTime, total_wait);
            currentTime += p.duration;

        }
        System.out.println("Total wait time = " + total_wait + "\n" +
                "Average wait time = " + total_wait / process_num + "\n");

    }
    

    public static void main(String[] args) throws IOException {
        

        //read file, and print process info;
        Scanner fileinput_1 = new Scanner(new File("src/hw7/process_scheduling_input_1.txt"));
        Scanner fileinput_2 = new Scanner(new File("src/hw7/process_scheduling_input_2.txt"));
        runProcess(fileinput_1);
        runProcess(fileinput_2);

    }
}

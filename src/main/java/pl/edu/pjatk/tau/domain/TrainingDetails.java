package pl.edu.pjatk.tau.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class  TrainingDetails extends AuditableEntity{

    private int id;
    private String name;
    private List<String> excersises = new LinkedList<String>();
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getExcersises() {
        return excersises;
    }

    public void setExcersises(LinkedList<String> excersises) {
        this.excersises = excersises;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TrainingDetails(int id, String name, List<String> excersises, int duration) {
        this.id = id;
        this.name = name;
        this.excersises = excersises;
        this.duration = duration;
    }

    public TrainingDetails() {

    }
}

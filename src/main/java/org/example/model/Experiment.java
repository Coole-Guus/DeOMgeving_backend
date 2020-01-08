package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dropwizard.validation.OneOf;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.security.Principal;

public class Experiment implements Principal {

    @NotNull
    private int experimentId;

    @NotNull
    @Length(max=50)
    private String experiment_naam;

    @NotNull
    private String wijziging_datum;

    @NotNull
    @OneOf(value = {"Idee", "Lab in", "Lab uit"})
    private String fase;

    @NotNull
    @OneOf(value = {"Groen", "Oranje", "Rood"})
    private String color;

    @NotNull
    @Length(max=50)
    private String experiment_leider_primair;

    @NotNull
    @Length(max=50)
    private String experiment_leider_secundair;

    @Override
    @JsonIgnore
    public String getName() {
        return null;
    }

    public String getColor() {
        return color;
    }

    public String getExperiment_leider_primair() {
        return experiment_leider_primair;
    }

    public void setExperiment_leider_primair(String experiment_leider_primair) {
        this.experiment_leider_primair = experiment_leider_primair;
    }

    public String getExperiment_leider_secundair() {
        return experiment_leider_secundair;
    }

    public void setExperiment_leider_secundair(String experiment_leider_secundair) {
        this.experiment_leider_secundair = experiment_leider_secundair;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }
    public void setColor(String color){
        this.color = color;
    }

    public String getExperiment_naam() {
        return experiment_naam;
    }

    public void setExperiment_naam(String experiment_naam) {
        this.experiment_naam = experiment_naam;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public String getWijziging_datum() {
        return wijziging_datum;
    }

    public void setWijziging_datum(String wijziging_datum) {
        this.wijziging_datum = wijziging_datum;
    }

    public boolean equals(Experiment experiment) {
        return experiment.getExperimentId() == this.getExperimentId();
    }

    public String toString() {
        return "\nexperimentId:" + experimentId + "\n" +
                "experiment_naam:" + experiment_naam + "\n" +
                "wijziging_datum:" + wijziging_datum + "\n" +
                "fase:" + fase + "\n" +
                "color:" + color + "\n" +
                "experiment_leider:" + experiment_leider_primair + "";
    }
}

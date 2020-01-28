package org.example.model;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

public class ExperimentStats {

    @NotNull
    private int totaal;

    @NotNull
    private int aantalExperimenten;

    @NotNull
    private int aantalIdee;

    @NotNull
    private int aantalLabIn;

    @NotNull
    private int aantalLabUit;

    @NotNull
    private int aantalVasteDienst;

    @NotNull
    private int aantalGroen;

    @NotNull
    private int aantalOranje;

    @NotNull
    private int aantalRood;

    @NotNull
    private int aantalGewijzigdSinds;

    private List<Experiment> gewijzigdeExperimenten;

    private List<Experiment> gewijzigdeDiensten;

    public int getTotaal() {
        return totaal;
    }

    public void setTotaal(int totaal) {
        this.totaal = totaal;
    }

    public int getAantalExperimenten() {
        return aantalExperimenten;
    }

    public void setAantalExperimenten(int aantalExperimenten) {
        this.aantalExperimenten = aantalExperimenten;
    }

    public int getAantalIdee() {
        return aantalIdee;
    }

    public void setAantalIdee(int aantalIdee) {
        this.aantalIdee = aantalIdee;
    }

    public int getAantalLabIn() {
        return aantalLabIn;
    }

    public void setAantalLabIn(int aantalLabIn) {
        this.aantalLabIn = aantalLabIn;
    }

    public int getAantalLabUit() {
        return aantalLabUit;
    }

    public void setAantalLabUit(int aantalLabUit) {
        this.aantalLabUit = aantalLabUit;
    }

    public int getAantalVasteDienst() {
        return aantalVasteDienst;
    }

    public void setAantalVasteDienst(int aantalVasteDienst) {
        this.aantalVasteDienst = aantalVasteDienst;
    }

    public int getAantalGroen() {
        return aantalGroen;
    }

    public void setAantalGroen(int aantalGroen) {
        this.aantalGroen = aantalGroen;
    }

    public int getAantalOranje() {
        return aantalOranje;
    }

    public void setAantalOranje(int aantalOranje) {
        this.aantalOranje = aantalOranje;
    }

    public int getAantalRood() {
        return aantalRood;
    }

    public void setAantalRood(int aantalRood) {
        this.aantalRood = aantalRood;
    }

    public int getAantalGewijzigdSinds() {
        return aantalGewijzigdSinds;
    }

    public void setAantalGewijzigdSinds(int aantalGewijzigdSinds) {
        this.aantalGewijzigdSinds = aantalGewijzigdSinds;
    }

    public List<Experiment> getGewijzigdeExperimenten() {
        return gewijzigdeExperimenten;
    }

    public void setGewijzigdeExperimenten(List<Experiment> gewijzigdeExperimenten) {
        this.gewijzigdeExperimenten = gewijzigdeExperimenten;
    }

    public List<Experiment> getGewijzigdeDiensten() {
        return gewijzigdeDiensten;
    }

    public void setGewijzigdeDiensten(List<Experiment> gewijzigdeDiensten) {
        this.gewijzigdeDiensten = gewijzigdeDiensten;
    }
}

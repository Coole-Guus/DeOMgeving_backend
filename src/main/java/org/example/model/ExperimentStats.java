package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dropwizard.validation.OneOf;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ExperimentStats {

    private int lab_uit_stats;

    private int lab_in_stats;

    private int idee_stats;

    private int experimenten_stats;

    private int vaste_dienst_stats;

    private int groen_stats;

    private int oranje_stats;

    private int rood_stats;

    private int gewijzig_laatste_30_dagen_stats;

    public int getLabUitStats(){
        return lab_uit_stats;
    }

    public int getIabInStats(){
        return lab_in_stats;
    }

    public int getIdeeStats(){
        return idee_stats;
    }

    public int getExperimentenStats(){
        return experimenten_stats;}

    public int getVasteDienstStats(){
        return vaste_dienst_stats;
    }

    public int getGroenStats(){
        return groen_stats;
    }

    public int getRoodStats(){
        return rood_stats;
    }

    public int getOranjeStats(){
        return oranje_stats;
    }

    public int getGewijzigf(){
        return gewijzig_laatste_30_dagen_stats;
    }

    public void setLabUitStats(int lab_uit_stats){
        this.lab_uit_stats = lab_uit_stats;
    }

    public void setLabInStats(int lab_in_stats){
        this.lab_in_stats = lab_in_stats;
    }

    public void setIdeeStats(int idee_stats){
        this.idee_stats = idee_stats;
    }

    public void setExperimentenStats(int experimenten_stats){
        this.experimenten_stats =experimenten_stats;
    }

    public void setVasteDienstStats(int vaste_dienst_stats){
        this.vaste_dienst_stats = vaste_dienst_stats;
    }

    public void setGroenStats(int groen_stats){
        this.groen_stats = groen_stats;
    }

    public void setRoodStats(int rood_stats){
        this.rood_stats = rood_stats;
    }

    public void setOranjeStats(int oranje_stats){
        this.oranje_stats = oranje_stats;
    }

    public void setGewijzig(int gewijzig_laatste_30_dagen_stats){
        this.gewijzig_laatste_30_dagen_stats = gewijzig_laatste_30_dagen_stats;
    }
}

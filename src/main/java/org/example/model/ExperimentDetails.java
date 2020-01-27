package org.example.model;

import javafx.beans.DefaultProperty;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

public class ExperimentDetails {

    @NotNull
    private int experimentId;

    private String netwerk = "";
    private String status = "";
    private String kosten_inovatie = "";
    private String kosten_anders = "";
    private String doorlooptijd = "";
    private String beschrijving = "";
    private String overige_opmerkingen = "";
    private String archief_type = null;


    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public String getNetwerk() {
        return netwerk;
    }

    public void setNetwerk(String netwerk) {
        this.netwerk = netwerk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKosten_inovatie() {
        return kosten_inovatie;
    }

    public void setKosten_inovatie(String kosten_inovatie) {
        this.kosten_inovatie = kosten_inovatie;
    }

    public String getKosten_anders() {
        return kosten_anders;
    }

    public void setKosten_anders(String kosten_anders) {
        this.kosten_anders = kosten_anders;
    }

    public String getDoorlooptijd() {
        return doorlooptijd;
    }

    public void setDoorlooptijd(String doorlooptijd) {
        this.doorlooptijd = doorlooptijd;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getOverige_opmerkingen() {
        return overige_opmerkingen;
    }

    public void setOverige_opmerkingen(String overige_opmerkingen) {
        this.overige_opmerkingen = overige_opmerkingen;
    }

    public String getArchief_type() {
        return archief_type;
    }

    public void setArchief_type(String archief_type) {
        this.archief_type = archief_type;
    }
}

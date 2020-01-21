package org.example.model;

import javax.validation.constraints.NotNull;

public class ExperimentDetails {
    @NotNull
    private int experimentId;

    @NotNull
    private String netwerk;

    @NotNull
    private String status;

    @NotNull
    private String kosten_inovatie;

    @NotNull
    private String kosten_anders;

    @NotNull
    private String doorlooptijd;

    @NotNull
    private String beschrijving	;

    @NotNull
    private String voortgang;

    private String archief_type;

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

    public String getVoortgang() {
        return voortgang;
    }

    public void setVoortgang(String voortgang) {
        this.voortgang = voortgang;
    }

    public String getArchief_type() {
        return archief_type;
    }

    public void setArchief_type(String archief_type) {
        this.archief_type = archief_type;
    }
}

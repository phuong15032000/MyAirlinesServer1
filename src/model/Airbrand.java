/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Airbrand implements Serializable{
    private static final long serialVersionUID = -6500665823330706018L;
    private int airbrandId;
    private String airbrandName;

    public Airbrand() {
    }

    public Airbrand(int airbrandId, String airbrandName) {
        this.airbrandId = airbrandId;
        this.airbrandName = airbrandName;
    }

    public int getAirbrandId() {
        return airbrandId;
    }

    public void setAirbrandId(int airbrandId) {
        this.airbrandId = airbrandId;
    }

    public String getAirbrandName() {
        return airbrandName;
    }

    public void setAirbrandName(String airbrandName) {
        this.airbrandName = airbrandName;
    }
    
}

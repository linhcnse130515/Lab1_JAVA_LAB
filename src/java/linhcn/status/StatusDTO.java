/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.status;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class StatusDTO implements Serializable{

    private int id;
    private String name;

    public StatusDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StatusDTO(int id) {
        this.id = id;
    }

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

}

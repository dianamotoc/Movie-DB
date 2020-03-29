package com.example.demo.model.DomainObject;
import javax.persistence.*;

@Entity
public class Award {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;


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



    public String toString(){
        return this.name;
    }
}

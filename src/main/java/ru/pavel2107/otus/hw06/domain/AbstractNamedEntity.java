package ru.pavel2107.otus.hw06.domain;


import org.springframework.data.annotation.AccessType;

import javax.persistence.*;

@MappedSuperclass
@AccessType( AccessType.Type.FIELD)
public abstract class AbstractNamedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="id", nullable = false)
    protected Long ID;

    @Column( name = "name")
    protected String Name;

    public AbstractNamedEntity(){}


    public AbstractNamedEntity(Long ID, String name){
        this.ID = ID;
        this.Name = name;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isNew(){
        return ID == null;
    }

}

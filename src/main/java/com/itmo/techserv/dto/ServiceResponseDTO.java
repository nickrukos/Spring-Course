package com.itmo.techserv.dto;

import java.time.LocalDate;

public class ServiceResponseDTO {
    private int id;              //идентификатор услуги
    private int type;            //тип услуги
    private String name;         //название услуги
    private String description;  //описание услуги
    private LocalDate beginDate; //дата начала оказания услуги
    private LocalDate finishDate;//дата окончания оказания услуги

    public ServiceResponseDTO(int id, int type, String name, String description, LocalDate beginDate, LocalDate finishDate) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.beginDate = beginDate;
        this.finishDate = finishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}

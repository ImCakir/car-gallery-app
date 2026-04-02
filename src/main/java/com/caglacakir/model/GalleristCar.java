package com.caglacakir.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gallerist_car",
uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id" , "car_id"}, name = "uq_gallerist_car")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GalleristCar extends BaseEntity {

    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;

/* 1 nolu galerinin ---> 5 nolu arabası var
                1 ---->  6
                1 ----> 7
                1 -----> 5  böyle bir şey olmamalı. 2 defa 1 nolu 5 noluyu satamamalı.



uniqueConstraints =
 {@UniqueConstraint(columnNames = {"gallerist_id" , "car_id"}, name = "uq_gallerist_car
  Anlamı:                               1               3
                                        1               3  olamasın.Tekrar aynı kaydın oluşmasını engeller
 */























}

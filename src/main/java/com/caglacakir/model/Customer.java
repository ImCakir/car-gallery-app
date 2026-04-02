package com.caglacakir.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "tckn")
    private String tckn;

    @Column(name = "birthOfDate")
    private Date birthOfDate;

    //Bir müşterinin bir adresi ve bir hesap adresi olabilir.Mimariye göre birden fazla hesabı olabilir.
    @OneToOne
    private Address address;

    @OneToOne
    private Account account;
}

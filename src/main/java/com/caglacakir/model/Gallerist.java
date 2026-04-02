package com.caglacakir.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gallerist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gallerist extends BaseEntity {

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @OneToOne // 1 galericinin 1 adresi olabilir. Mimariye göre 1 galericinin birden fazla adresi olabikir seklınde de tasarlanablir.
    private Address address;
}

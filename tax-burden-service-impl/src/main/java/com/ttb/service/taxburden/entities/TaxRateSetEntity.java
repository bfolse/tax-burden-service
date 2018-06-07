package com.ttb.service.taxburden.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tax_rate_set")
public class TaxRateSetEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaxRateEntity> taxRates;

}

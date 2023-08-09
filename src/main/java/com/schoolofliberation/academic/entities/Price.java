package com.schoolofliberation.academic.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "prices")
@Getter
@Setter
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_dollar")
    private Double priceDollar;

    @Column(name = "price_career")
    private Double priceCareer;

    @Column(name = "career_id")
    private Long careerId;

//    @OneToOne
//    @JoinColumn(name = "career_id")
//    private Career career;
}

package com.schoolofliberation.academic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "types")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Type {
    
    @Id
    @Column(name = "value_id")
    private Long value;

    @Column(name = "meaning")
    private String meaning;

    @Column(name = "type")
    private String type;
}

package com.schoolofliberation.academic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "types")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

package br.com.skeleton.spendsmart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "INSTALLMENT")
public class Installment {

    @Id
    @Column(name = "ID_INSTALLMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "EXPENSE_VALUE")
    private Double value;

    private Integer paidAmount;

    private Integer pendingAmount;

    private Double pendingValue;

}

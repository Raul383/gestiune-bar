package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
    name = "Linii_Factura",
    uniqueConstraints = @UniqueConstraint(name = "uk_factura_linie", columnNames = {"factura_id", "numar_linie"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinieFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_comanda", nullable = false)
    private Long idComanda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    @Column(name = "numar_linie", nullable = false)
    private Integer numarLinie;

    @Column(name = "denumire_produs", nullable = false, length = 255)
    private String denumireProdus;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal cantitate = BigDecimal.ZERO;

    @Column(name = "pret_unitar", nullable = false, precision = 15, scale = 2)
    private BigDecimal pretUnitar;

    @Column(name = "valoare_fara_tva", nullable = false, precision = 15, scale = 2)
    private BigDecimal valoareFaraTva = BigDecimal.ZERO;

    @Column(name = "cota_tva", nullable = false, precision = 5, scale = 2)
    private BigDecimal cotaTva = new BigDecimal("21.00");

    @Column(name = "valoare_tva", nullable = false, precision = 15, scale = 2)
    private BigDecimal valoareTva = BigDecimal.ZERO;

    @Column(name = "total_linie", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalLinie = BigDecimal.ZERO;

    @Column(name = "mentiune_speciala", length = 100)
    private String mentiuneSpeciala;
}
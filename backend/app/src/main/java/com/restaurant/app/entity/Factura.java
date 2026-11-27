package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "Facturi",
    uniqueConstraints = @UniqueConstraint(name = "uk_factura_serie_numar", columnNames = {"seria", "numar"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {

    public enum MetodaPlata {
        @Column(name = "10") COD_10,
        @Column(name = "30") COD_30,
        @Column(name = "42") COD_42,
        @Column(name = "48") COD_48,
        @Column(name = "68") COD_68,
        ZZZ
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comanda_id")
    private Comanda comanda;

    @Column(nullable = false, length = 4, columnDefinition = "CHAR(4)")
    private String seria = "REST";

    @Column(nullable = false, length = 30)
    private String numar;

    @Column(name = "data_emiterii", insertable = false)
    private LocalDateTime dataEmiterii;

    @Column(name = "data_prestarii")
    private LocalDateTime dataPrestarii;

    @Column(name = "vanzator_cui", length = 15)
    private String vanzatorCui;

    @Column(name = "cumparator_cnp", length = 13)
    private String cumparatorCnp = "0000000000000";

    @Enumerated(EnumType.STRING)
    @Column(name = "metoda_plata", nullable = false, columnDefinition = "ENUM('10','30','42','48','68','ZZZ')")
    private MetodaPlata metodaPlata = MetodaPlata.ZZZ;

    @Column(name = "vanzator_denumire", nullable = false, length = 255)
    private String vanzatorDenumire;

    @Column(name = "vanzator_adresa", nullable = false, columnDefinition = "TEXT")
    private String vanzatorAdresa;

    @Column(name = "cumparator_denumire", nullable = false, length = 255)
    private String cumparatorDenumire = "Client Restaurant";

    @Column(name = "cumparator_adresa", nullable = false, columnDefinition = "TEXT")
    private String cumparatorAdresa;

    @Column(name = "total_fara_tva", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalFaraTva = BigDecimal.ZERO;

    @Column(name = "total_tva", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalTva = BigDecimal.ZERO;

    @Column(name = "total_plata", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPlata = BigDecimal.ZERO;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LinieFactura> linii = new ArrayList<>();
}
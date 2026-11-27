package com.restaurant.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Comenzi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comanda {

    public enum StatusComanda {
        deschisa, inchisa, anulata
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masa_id")
    private Masa masa;

    @Column(name = "numar_comanda", nullable = false, unique = true, length = 30)
    private String numarComanda;

    @Column(name = "data_comanda", nullable = false, insertable = false)
    private LocalDateTime dataComanda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('deschisa','inchisa','anulata')")
    private StatusComanda status = StatusComanda.deschisa;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ComandaLinie> linii = new ArrayList<>();
}
CREATE DATABASE IF NOT EXISTS management_restaurant_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE management_restaurant_db;


SET NAMES utf8mb4;
SET time_zone = '+00:00';

-- ============================================================
-- 1. MESE 
-- ============================================================

CREATE TABLE Mese (
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,

    -- Identificare
    nume            VARCHAR(50) NOT NULL,          -- "A", "B", "C", "Terasă 1" etc.

    -- Geometrie (pentru desenarea pe hartă)
    latime          DECIMAL(6,2) NOT NULL,          -- în unități de hartă (px sau cm)
    lungime         DECIMAL(6,2) NOT NULL,          -- dreptunghiular: lățime x lungime
    coord_x         DECIMAL(8,2) NOT NULL DEFAULT 0.00,
    coord_y         DECIMAL(8,2) NOT NULL DEFAULT 0.00,

    -- Stare operațională
    ocupata         TINYINT(1) NOT NULL DEFAULT 0,  -- 0 = liberă, 1 = ocupată

    -- Soft-delete
    deleted         TINYINT(1) NOT NULL DEFAULT 0,
    deleted_at      TIMESTAMP NULL,

    -- Audit
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                              ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE KEY uk_masa_nume (nume),
    KEY idx_mese_stare (deleted, ocupata)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- 2. MENIU (nu depinde de nimic)
-- ============================================================

CREATE TABLE Meniu (
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    denumire        VARCHAR(255) NOT NULL,
    descriere       TEXT NULL,
    categorie       VARCHAR(100) NULL,          -- ex: "Fel principal", "Băuturi"
    pret            DECIMAL(15,2) NOT NULL,      -- preț curent (fără TVA, dacă vrei așa)
    cota_tva        DECIMAL(5,2)  NOT NULL DEFAULT 21.00,
    unitate_masura  VARCHAR(20)   NOT NULL DEFAULT 'buc',

    -- Stare operațională (se schimbă des, dar nu șterge istoricul)
    disponibil      TINYINT(1) NOT NULL DEFAULT 1,   -- 1 = se poate comanda acum

    -- Soft-delete (element scos din meniu, dar rămâne în istoric)
    deleted         TINYINT(1) NOT NULL DEFAULT 0,
    deleted_at      TIMESTAMP NULL,

    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                              ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    KEY idx_meniu_disponibil (disponibil, deleted),
    KEY idx_meniu_categorie (categorie)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- 3. INVENTAR (nu depinde de nimic)
-- ============================================================

CREATE TABLE Inventar (
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    denumire        VARCHAR(255) NOT NULL,
    unitate_masura  VARCHAR(20)  NOT NULL,
    cantitate       DECIMAL(12,3) NOT NULL DEFAULT 0.000,
    stoc_minim      DECIMAL(12,3) NOT NULL DEFAULT 0.000,
    pret_achizitie  DECIMAL(15,2) NULL,
    deleted         TINYINT(1) NOT NULL DEFAULT 0,
    deleted_at      TIMESTAMP NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                              ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_inventar_denumire (denumire),
    KEY idx_inventar_stare (deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- 4. COMENZI (depinde de Mese)
-- ============================================================

CREATE TABLE Comenzi (
    id                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    masa_id             BIGINT UNSIGNED NULL,
    numar_comanda       VARCHAR(30) NOT NULL,
    data_comanda        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status              ENUM('deschisa','inchisa','anulata') NOT NULL DEFAULT 'deschisa',

    -- alte câmpuri: ospatar_id, numar_persoane...
    PRIMARY KEY (id),
    UNIQUE KEY uk_numar_comanda (numar_comanda),
    KEY idx_comenzi_masa (masa_id),
    CONSTRAINT fk_comanda_masa
        FOREIGN KEY (masa_id) REFERENCES Mese(id)
        ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- 5. COMANDA_LINII (depinde de Comenzi + Meniu)
-- ============================================================

CREATE TABLE Comanda_Linii (
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    comanda_id      BIGINT UNSIGNED NOT NULL,
    meniu_id        BIGINT UNSIGNED NOT NULL,
    cantitate       DECIMAL(10,3) NOT NULL DEFAULT 1.000,
    observatii      VARCHAR(255) NULL,              -- ex: "fără ceapă"
    PRIMARY KEY (id),
    FOREIGN KEY (comanda_id) REFERENCES Comenzi(id) ON DELETE CASCADE,
    FOREIGN KEY (meniu_id)   REFERENCES Meniu(id)   ON DELETE RESTRICT,
    KEY idx_comanda_linii_comanda (comanda_id),
    KEY idx_comanda_linii_meniu (meniu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ============================================================
-- 6. RETETA (depinde de Meniu + Inventar)
-- ============================================================

CREATE TABLE Reteta (
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    meniu_id        BIGINT UNSIGNED NOT NULL,
    inventar_id     BIGINT UNSIGNED NOT NULL,
    cantitate       DECIMAL(12,3) NOT NULL,
    unitate_masura  VARCHAR(20) NOT NULL,
    observatii      VARCHAR(255) NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (meniu_id)    REFERENCES Meniu(id)    ON DELETE CASCADE,
    FOREIGN KEY (inventar_id) REFERENCES Inventar(id) ON DELETE RESTRICT,
    UNIQUE KEY uk_reteta (meniu_id, inventar_id),
    KEY idx_reteta_meniu (meniu_id),
    KEY idx_reteta_inventar (inventar_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ============================================================
-- 7. FACTURI (depinde de Comenzi — dacă adaugi comanda_id)
-- ============================================================

CREATE TABLE Facturi(
	id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	comanda_id      BIGINT UNSIGNED NULL,
	seria			CHAR(4) NOT NULL DEFAULT 'REST',
	numar 			VARCHAR(30) NOT NULL,
	data_emiterii	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	data_prestarii 	TIMESTAMP,
	vanzator_cui	VARCHAR(15),
	cumparator_cnp	VARCHAR(13) DEFAULT "0000000000000",
	metoda_plata	ENUM('10','30','42','48','68','ZZZ') NOT NULL DEFAULT 'ZZZ',
	-- Adaugă în tabelul Facturi:
	vanzator_denumire   VARCHAR(255)	NOT NULL,
	vanzator_adresa     TEXT			NOT NULL,
	cumparator_denumire VARCHAR(255) 	NOT NULL DEFAULT "Client Restaurant",
	cumparator_adresa   TEXT			NOT NULL,
	total_fara_tva      DECIMAL(15,2) 	NOT NULL DEFAULT 0.00,
	total_tva           DECIMAL(15,2) 	NOT NULL DEFAULT 0.00,
	total_plata         DECIMAL(15,2) 	NOT NULL DEFAULT 0.00,
	
	PRIMARY KEY (id),
    UNIQUE KEY uk_factura_serie_numar (seria, numar),
    KEY idx_facturi_comanda (comanda_id),
    CONSTRAINT fk_factura_comanda
        FOREIGN KEY (comanda_id) REFERENCES Comenzi(id)
        ON DELETE SET NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- 8. LINII_FACTURA (depinde de Facturi + Comenzi)
-- ============================================================

CREATE TABLE Linii_Factura (
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_comanda		BIGINT UNSIGNED NOT NULL,
    factura_id      BIGINT UNSIGNED NOT NULL,  -- se leagă prin id-ul din tabelul Facturi
    numar_linie     INT NOT NULL,              -- numărul secvențial al liniei în factură (1, 2, 3...)
    denumire_produs VARCHAR(255) NOT NULL,
    cantitate       DECIMAL(10,3) NOT NULL DEFAULT 0.000,
    pret_unitar     DECIMAL(15,2) NOT NULL,
        -- Valori financiare pe linie
    valoare_fara_tva    DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    cota_tva            DECIMAL(5,2)  NOT NULL DEFAULT 21.00,
    valoare_tva         DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    total_linie         DECIMAL(15,2) NOT NULL DEFAULT 0.00,
        -- Mențiuni speciale (taxare inversă, scutire, regim marjă etc.)
    mentiune_speciala   VARCHAR(100) NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (factura_id) REFERENCES Facturi(id) ON DELETE CASCADE,
    UNIQUE KEY uk_factura_linie (factura_id, numar_linie)
) ENGINE=InnoDB;

-- ============================================================
-- 9. RAPORT_CONSUM
-- ============================================================

CREATE TABLE Raport_Consum (
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    inventar_id     BIGINT UNSIGNED NOT NULL,
    data_consum     DATE NOT NULL,                      -- ziua (fără oră)
    cantitate       DECIMAL(12,3) NOT NULL DEFAULT 0.000,
    unitate_masura  VARCHAR(20) NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                              ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (inventar_id) REFERENCES Inventar(id) ON DELETE RESTRICT,
    UNIQUE KEY uk_raport_consum (inventar_id, data_consum),
    KEY idx_raport_consum_data (data_consum)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Risipa (
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    inventar_id     BIGINT UNSIGNED NOT NULL,
    data_risipa     DATE NOT NULL,                      -- ziua (fără oră)
    cantitate       DECIMAL(12,3) NOT NULL DEFAULT 0.000,
    unitate_masura  VARCHAR(20) NOT NULL,
    motiv           VARCHAR(255) NULL,                  -- "expirat", "vărsat", "preparat greșit"
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                              ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (inventar_id) REFERENCES Inventar(id) ON DELETE RESTRICT,
    UNIQUE KEY uk_risipa (inventar_id, data_risipa),
    KEY idx_risipa_data (data_risipa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

USE management_restaurant_db;


-- ============================================================
-- 1. MESE
-- ============================================================
INSERT INTO Mese (id, nume, latime, lungime, coord_x, coord_y, ocupata) VALUES
                                                                            (1, 'Bar 1',     150.00, 50.00,  20.00,  20.00, 0),
                                                                            (2, 'Bar 2',     150.00, 50.00, 190.00,  20.00, 0),
                                                                            (3, 'M1',         70.00, 70.00,  20.00, 120.00, 0),
                                                                            (4, 'M2',         70.00, 70.00, 110.00, 120.00, 0),
                                                                            (5, 'M3',         90.00, 90.00, 200.00, 120.00, 1),
                                                                            (6, 'M4',         90.00, 90.00, 310.00, 120.00, 0),
                                                                            (7, 'Terasă 1',   70.00, 70.00,  20.00, 260.00, 0),
                                                                            (8, 'Terasă 2',   70.00, 70.00, 110.00, 260.00, 0);

-- ============================================================
-- 2. MENIU (preț fără TVA; alcool 21%, restul 11%)
-- ============================================================
INSERT INTO Meniu (id, denumire, descriere, categorie, pret, cota_tva, unitate_masura, disponibil) VALUES
                                                                                                       (1,  'Bere draft 0.4L',     'Bere la halbă',                     'Bere',          9.00, 21.00, 'buc',    1),
                                                                                                       (2,  'Bere sticlă 0.5L',    NULL,                                'Bere',         10.00, 21.00, 'buc',    1),
                                                                                                       (3,  'Gin Tonic',           'Gin 50 ml, apă tonică, lămâie',     'Cocktailuri',  24.00, 21.00, 'buc',    1),
                                                                                                       (4,  'Mojito',              'Rom alb, mentă, lime, zahăr',       'Cocktailuri',  26.00, 21.00, 'buc',    1),
                                                                                                       (5,  'Aperol Spritz',        'Aperol, prosecco, sifon',          'Cocktailuri',  25.00, 21.00, 'buc',    1),
                                                                                                       (6,  'Whisky Cola',         'Whisky 50 ml cu cola',              'Cocktailuri',  22.00, 21.00, 'buc',    1),
                                                                                                       (7,  'Vodcă shot 4cl',      NULL,                                'Shoturi',       8.00, 21.00, 'buc',    1),
                                                                                                       (8,  'Vin roșu (pahar)',    'Vin de casă, 150 ml',               'Vinuri',       15.00, 21.00, 'buc',    1),
                                                                                                       (9,  'Cola 0.33L',          NULL,                                'Răcoritoare',   8.00, 11.00, 'buc',    1),
                                                                                                       (10, 'Apă minerală 0.33L',  NULL,                                'Răcoritoare',   6.00, 11.00, 'buc',    1),
                                                                                                       (11, 'Limonadă 0.4L',       'Preparată în casă',                 'Răcoritoare',  12.00, 11.00, 'buc',    1),
                                                                                                       (12, 'Cafea espresso',      NULL,                                'Cafea',         8.00, 11.00, 'buc',    1),
                                                                                                       (13, 'Nachos cu brânză',    'Cu sos de cașcaval',                'Snackuri',     22.00, 11.00, 'porție', 1),
                                                                                                       (14, 'Platou aperitive',    'Nachos, cașcaval, alune',           'Snackuri',     45.00, 11.00, 'porție', 1),
                                                                                                       (15, 'Alune sărate',        NULL,                                'Snackuri',      7.00, 11.00, 'porție', 1);

-- ============================================================
-- 3. INVENTAR
-- ============================================================
INSERT INTO Inventar (id, denumire, unitate_masura, cantitate, stoc_minim, pret_achizitie) VALUES
                                                                                               (1,  'Bere draft (KEG)',      'l',   200.000, 40.000,   6.00),
                                                                                               (2,  'Bere sticlă 0.5L',      'buc',  96.000, 24.000,   3.80),
                                                                                               (3,  'Gin',                   'l',     6.000,  1.500,  85.00),
                                                                                               (4,  'Rom alb',               'l',     6.000,  1.500,  70.00),
                                                                                               (5,  'Whisky',                'l',     5.000,  1.500,  95.00),
                                                                                               (6,  'Vodcă',                 'l',     8.000,  2.000,  55.00),
                                                                                               (7,  'Aperol',                'l',     4.000,  1.000,  60.00),
                                                                                               (8,  'Prosecco',              'l',    12.000,  3.000,  35.00),
                                                                                               (9,  'Vin roșu (de casă)',    'l',    15.000,  4.000,  22.00),
                                                                                               (10, 'Apă tonică 0.2L',       'buc',  72.000, 24.000,   2.50),
                                                                                               (11, 'Cola 0.33L',            'buc',  96.000, 24.000,   2.20),
                                                                                               (12, 'Apă minerală 0.33L',    'buc',  72.000, 24.000,   1.50),
                                                                                               (13, 'Sifon / apă gazoasă',   'l',    20.000,  5.000,   1.00),
                                                                                               (14, 'Lămâi / lime',          'kg',    8.000,  2.000,   8.00),
                                                                                               (15, 'Mentă proaspătă',       'kg',    1.000,  0.300,  40.00),
                                                                                               (16, 'Zahăr',                 'kg',   10.000,  2.000,   4.50),
                                                                                               (17, 'Cafea boabe',           'kg',    6.000,  1.500,  65.00),
                                                                                               (18, 'Chipsuri nachos',       'kg',    8.000,  2.000,  18.00),
                                                                                               (19, 'Cașcaval / sos brânză', 'kg',    5.000,  1.500,  30.00),
                                                                                               (20, 'Alune sărate',          'kg',    6.000,  1.500,  24.00),
                                                                                               (21, 'Gheață',                'kg',  100.000, 20.000,   0.50),
                                                                                               (22, 'Portocale',             'kg',    6.000,  2.000,   6.00);

-- ============================================================
-- 4. RETETA
-- ============================================================
INSERT INTO Reteta (meniu_id, inventar_id, cantitate, unitate_masura) VALUES
-- Bere draft
(1,  1,  0.400, 'l'),
-- Bere sticlă
(2,  2,  1.000, 'buc'),
-- Gin Tonic
(3,  3,  0.050, 'l'),
(3,  10, 1.000, 'buc'),
(3,  14, 0.020, 'kg'),
(3,  21, 0.150, 'kg'),
-- Mojito
(4,  4,  0.040, 'l'),
(4,  14, 0.040, 'kg'),
(4,  15, 0.010, 'kg'),
(4,  16, 0.020, 'kg'),
(4,  13, 0.100, 'l'),
(4,  21, 0.150, 'kg'),
-- Aperol Spritz
(5,  7,  0.060, 'l'),
(5,  8,  0.090, 'l'),
(5,  13, 0.030, 'l'),
(5,  22, 0.030, 'kg'),
(5,  21, 0.150, 'kg'),
-- Whisky Cola
(6,  5,  0.050, 'l'),
(6,  11, 1.000, 'buc'),
(6,  21, 0.150, 'kg'),
-- Vodcă shot
(7,  6,  0.040, 'l'),
-- Vin roșu
(8,  9,  0.150, 'l'),
-- Cola
(9,  11, 1.000, 'buc'),
-- Apă minerală
(10, 12, 1.000, 'buc'),
-- Limonadă
(11, 14, 0.100, 'kg'),
(11, 16, 0.030, 'kg'),
(11, 13, 0.300, 'l'),
(11, 21, 0.150, 'kg'),
-- Espresso
(12, 17, 0.009, 'kg'),
-- Nachos cu brânză
(13, 18, 0.150, 'kg'),
(13, 19, 0.080, 'kg'),
-- Platou aperitive
(14, 19, 0.150, 'kg'),
(14, 18, 0.100, 'kg'),
(14, 20, 0.050, 'kg'),
-- Alune sărate
(15, 20, 0.080, 'kg');

-- ============================================================
-- 5. COMENZI
-- ============================================================
INSERT INTO Comenzi (id, masa_id, numar_comanda, data_comanda, status) VALUES
                                                                           (1, 3, 'CMD-20261126-001', '2026-11-26 20:30:00', 'inchisa'),
                                                                           (2, 1, 'CMD-20261126-002', '2026-11-26 22:10:00', 'inchisa'),
                                                                           (3, 5, 'CMD-20261127-001', '2026-11-27 21:15:00', 'deschisa'),
                                                                           (4, 7, 'CMD-20261127-002', '2026-11-27 21:40:00', 'anulata');

-- ============================================================
-- 6. COMANDA_LINII
-- ============================================================
INSERT INTO Comanda_Linii (comanda_id, meniu_id, cantitate, pret_unitar, cota_tva, observatii) VALUES
-- Comanda 1
(1, 1,  4,  9.00, 21.00, NULL),
(1, 13, 1, 22.00, 11.00, NULL),
(1, 3,  2, 24.00, 21.00, 'unul fără lămâie'),
(1, 9,  1,  8.00, 11.00, NULL),
-- Comanda 2
(2, 4,  2, 26.00, 21.00, 'fără zahăr la unul'),
(2, 5,  1, 25.00, 21.00, NULL),
(2, 7,  4,  8.00, 21.00, NULL),
(2, 15, 1,  7.00, 11.00, NULL),
-- Comanda 3 (deschisă)
(3, 2,  3, 10.00, 21.00, NULL),
(3, 14, 1, 45.00, 11.00, NULL),
(3, 10, 1,  6.00, 11.00, NULL),
(3, 6,  2, 22.00, 21.00, 'fără gheață'),
-- Comanda 4 (anulată)
(4, 4,  1, 26.00, 21.00, NULL);

-- ============================================================
-- 7. FACTURI
-- ============================================================
INSERT INTO Facturi
(id, comanda_id, seria, numar, data_emiterii, data_prestarii, vanzator_cui, metoda_plata,
 vanzator_denumire, vanzator_adresa, cumparator_denumire, cumparator_adresa,
 total_fara_tva, total_tva, total_plata) VALUES
                                             (1, 1, 'REST', '000001', '2026-11-26 22:00:00', '2026-11-26 22:00:00', 'RO12345678', '48',
                                              'SC Bar Demo SRL', 'Str. Exemplu nr. 1, Baia Mare', 'Client Bar', NULL,
                                              114.00, 20.94, 134.94),
                                             (2, 2, 'REST', '000002', '2026-11-26 23:30:00', '2026-11-26 23:30:00', 'RO12345678', '10',
                                              'SC Bar Demo SRL', 'Str. Exemplu nr. 1, Baia Mare', 'Client Bar', NULL,
                                              116.00, 23.66, 139.66);

-- ============================================================
-- 8. LINII_FACTURA
-- ============================================================
INSERT INTO Linii_Factura
(factura_id, numar_linie, denumire_produs, cantitate, pret_unitar,
 valoare_fara_tva, cota_tva, valoare_tva, total_linie) VALUES
-- Factura 1
(1, 1, 'Bere draft 0.4L',    4,  9.00, 36.00, 21.00,  7.56, 43.56),
(1, 2, 'Nachos cu brânză',   1, 22.00, 22.00, 11.00,  2.42, 24.42),
(1, 3, 'Gin Tonic',          2, 24.00, 48.00, 21.00, 10.08, 58.08),
(1, 4, 'Cola 0.33L',         1,  8.00,  8.00, 11.00,  0.88,  8.88),
-- Factura 2
(2, 1, 'Mojito',             2, 26.00, 52.00, 21.00, 10.92, 62.92),
(2, 2, 'Aperol Spritz',      1, 25.00, 25.00, 21.00,  5.25, 30.25),
(2, 3, 'Vodcă shot 4cl',     4,  8.00, 32.00, 21.00,  6.72, 38.72),
(2, 4, 'Alune sărate',       1,  7.00,  7.00, 11.00,  0.77,  7.77);

-- ============================================================
-- 9. RAPORT_CONSUM (consum zilnic)
-- ============================================================
INSERT INTO Raport_Consum (inventar_id, data_consum, cantitate, unitate_masura) VALUES
                                                                                    (1,  '2026-11-26', 38.400, 'l'),
                                                                                    (2,  '2026-11-26', 24.000, 'buc'),
                                                                                    (3,  '2026-11-26',  1.250, 'l'),
                                                                                    (4,  '2026-11-26',  0.960, 'l'),
                                                                                    (5,  '2026-11-26',  1.100, 'l'),
                                                                                    (6,  '2026-11-26',  1.600, 'l'),
                                                                                    (10, '2026-11-26', 25.000, 'buc'),
                                                                                    (11, '2026-11-26', 20.000, 'buc'),
                                                                                    (14, '2026-11-26',  1.800, 'kg'),
                                                                                    (21, '2026-11-26', 14.500, 'kg');

-- ============================================================
-- 10. RISIPA
-- ============================================================
INSERT INTO Risipa (inventar_id, data_risipa, cantitate, unitate_masura, motiv) VALUES
                                                                                    (1,  '2026-11-26', 2.000, 'l',  'spumă / curățare linii'),
                                                                                    (6,  '2026-11-26', 0.100, 'l',  'vărsat'),
                                                                                    (6,  '2026-11-26', 0.040, 'l',  'preparat greșit'),
                                                                                    (14, '2026-11-26', 0.500, 'kg', 'expirat'),
                                                                                    (15, '2026-11-27', 0.200, 'kg', 'expirat');
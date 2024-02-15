CREATE TABLE IF NOT EXISTS AUTOR (
    autorID INT PRIMARY KEY,
    nomAutor VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS CANCION (
    cancionID INT PRIMARY KEY,
    nombreCancion VARCHAR(50),
    fecha DATE,
    autorID INT,
    imagen VARCHAR(250),
    duracion FLOAT,
    FOREIGN KEY (autorID) REFERENCES AUTOR(autorID)
);
CREATE TABLE IF NOT EXISTS USUARIO (
    userID INT PRIMARY KEY,
    nombreUsuario VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS PLAYLIST (
    playlistID INT PRIMARY KEY,
    nombrePlaylist VARCHAR(50),
    userID INT,
    minutosTotales FLOAT,
    FOREIGN KEY (userID) REFERENCES USUARIO(userID)
);
CREATE TABLE IF NOT EXISTS PLAYLIST_CANCION (
    playlistID INT,
    cancionID INT,
    PRIMARY KEY (playlistID, cancionID),
    FOREIGN KEY (playlistID) REFERENCES PLAYLIST(playlistID),
    FOREIGN KEY (cancionID) REFERENCES CANCION(cancionID)
);
-- 5 autores
INSERT IGNORE INTO AUTOR (autorID, nomAutor)
VALUES (1, 'Eladio Carrion'),
    (2, 'Eminem'),
    (3, 'Drake'),
    (4, 'Bad Bunny'),
    (5, 'Kaze');
-- Canciones
INSERT IGNORE INTO CANCION (
        cancionID,
        nombreCancion,
        fecha,
        autorID,
        imagen,
        duracion
    )
VALUES (
        1,
        'Coco Chanel',
        '2024-01-20',
        1,
        'imagen_coco_chanel.jpg',
        4.5
    ),
    (
        2,
        'Kemba Walker',
        '2024-01-21',
        1,
        'imagen_kemba_walker.jpg',
        3.2
    ),
    (
        3,
        'Si la calle llama',
        '2024-01-22',
        1,
        'imagen_si_la_calle_llama.jpg',
        5.1
    ),
    (
        4,
        'Without Me',
        '2024-01-20',
        2,
        'imagen_without_me.jpg',
        4.8
    ),
    (
        5,
        'Godzilla',
        '2024-01-21',
        2,
        'imagen_godzilla.jpg',
        5.7
    ),
    (
        6,
        'Lose Yourself',
        '2024-01-22',
        2,
        'imagen_love_yourself.jpg',
        3.4
    ),
    (
        7,
        'One Dance',
        '2024-01-20',
        3,
        'imagen_one_dance.jpg',
        3.9
    ),
    (
        8,
        'Too Good',
        '2024-01-21',
        3,
        'imagen_too_good.jpg',
        4.2
    ),
    (
        9,
        'Hotline Bling',
        '2024-01-22',
        3,
        'imagen_hotline_bling.jpg',
        5.3
    ),
    (
        10,
        'A Tu Merced',
        '2024-01-20',
        4,
        'imagen_a_tu_merced.jpg',
        4.6
    ),
    (
        11,
        'Amorfoda',
        '2024-01-21',
        4,
        'imagen_amorfoda.jpg',
        3.9
    ),
    (
        12,
        'Soy Peor',
        '2024-01-22',
        4,
        'imagen_soy_peor.jpg',
        5.5
    ),
    (
        13,
        'Cómete Mi Éxito',
        '2024-01-20',
        5,
        'imagen_comete_mi_exito.jpg',
        4.3
    ),
    (
        14,
        'Modo Turbio',
        '2024-01-21',
        5,
        'imagen_modo_turbio.jpg',
        3.7
    ),
    (
        15,
        '4 ROSES',
        '2024-01-22',
        5,
        'imagen_4_roses.jpg',
        5.6
    ),
    (
        16,
        'Sigo Trabajando',
        '2024-01-23',
        1,
        'imagen_sigo_trabajando.jpg',
        4.8
    ),
    (
        17,
        'Rap God',
        '2024-01-24',
        2,
        'imagen_rap_god.jpg',
        6.2
    ),
    (
        18,
        'In My Feelings',
        '2024-01-25',
        3,
        'imagen_in_my_feelings.jpg',
        4.5
    ),
    (
        19,
        'La Noche De Los Dos',
        '2024-01-26',
        4,
        'imagen_la_noche_de_los_dos.jpg',
        5.1
    ),
    (
        20,
        'Déjate Llevar',
        '2024-01-27',
        5,
        'imagen_dejate_llevar.jpg',
        3.7
    ),
    (
        21,
        'Mockingbird',
        '2024-01-28',
        2,
        'imagen_mockingbird.jpg',
        4.3
    ),
    (
        22,
        'God''s Plan',
        '2024-01-29',
        3,
        'imagen_gods_plan.jpg',
        5.6
    ),
    (
        23,
        'Goteo',
        '2024-01-30',
        1,
        'imagen_goteo.jpg',
        3.9
    ),
    (
        24,
        'La Noche De Los Dos',
        '2024-01-31',
        4,
        'imagen_la_noche_de_los_dos2.jpg',
        4.1
    ),
    (
        25,
        'Darell',
        '2024-02-01',
        5,
        'imagen_darell.jpg',
        4.9
    );
-- Usuarios
INSERT IGNORE INTO USUARIO (userID, nombreUsuario)
VALUES (1, 'usuario1'),
    (2, 'usuario2'),
    (3, 'usuario3'),
    (4, 'usuario4'),
    (5, 'usuario5');
-- Playlists
INSERT IGNORE INTO PLAYLIST (
        playlistID,
        nombrePlaylist,
        userID,
        minutosTotales
    )
VALUES (1, 'Canciones Tristes', 1, 21.8),
    (2, 'Rap', 2, 23.5),
    (3, 'Pop', 3, 19.9),
    (4, 'Trap', 3, 22.6),
    (5, 'Latino', 4, 21.8),
    (6, 'Reggaeton', 2, 25.5),
    (7, 'Bachata', 5, 19.3),
    (8, 'Rock', 1, 24.7);
-- Relaciones entre playlist y canciones
INSERT IGNORE INTO PLAYLIST_CANCION (playlistID, cancionID)
VALUES (1, 4),
    (1, 6),
    (1, 10),
    (1, 11),
    (1, 12),
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 5),
    (2, 13),
    (2, 14),
    (2, 15),
    (3, 7),
    (3, 8),
    (3, 9),
    (4, 17),
    (4, 18),
    (4, 19),
    (4, 20),
    (4, 21),
    (5, 16),
    (5, 17),
    (5, 18),
    (5, 19),
    (5, 20),
    (6, 22),
    (6, 23),
    (6, 24),
    (6, 25),
    (7, 16),
    (7, 19),
    (7, 20),
    (8, 1),
    (8, 4),
    (8, 5),
    (8, 6),
    (8, 10);
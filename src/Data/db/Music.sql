DROP TABLE IF EXISTS PLAYLIST_CANCION, PLAYLIST, USUARIO, CANCION, AUTOR;


CREATE TABLE IF NOT EXISTS AUTOR (
    autorID INT AUTO_INCREMENT PRIMARY KEY,
    nomAutor VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS CANCION (
    cancionID INT AUTO_INCREMENT PRIMARY KEY,
    nombreCancion VARCHAR(50),
    fecha DATE,
    autorID INT,
    imagen VARCHAR(250),
    duracion FLOAT,
    FOREIGN KEY (autorID) REFERENCES AUTOR(autorID)
);

CREATE TABLE IF NOT EXISTS USUARIO (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL, 
    imagen VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS PLAYLIST (
    playlistID INT AUTO_INCREMENT PRIMARY KEY,
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
INSERT IGNORE INTO AUTOR (nomAutor)
VALUES ('Eladio Carrion'),
    ('Eminem'),
    ('Drake'),
    ('Bad Bunny'),
    ('Kaze');

-- Canciones
INSERT IGNORE INTO CANCION (
        nombreCancion,
        fecha,
        autorID,
        imagen,
        duracion
    )
VALUES (
        'Coco Chanel',
        '2024-01-20',
        1,
        'Coco.jpg',
        4.5
    ),
    (
        'Kemba Walker',
        '2024-01-21',
        1,
        'Kemba.jpg',
        3.2
    ),
    (
        'Si la calle llama',
        '2024-01-22',
        1,
        'SiLaCalle.jpg',
        5.1
    ),
    (
        'Without Me',
        '2024-01-20',
        2,
        'WithoutMe.jpg',
        4.8
    ),
    (
        'Godzilla',
        '2024-01-21',
        2,
        'Godzilla.jpg',
        5.7
    ),
    (
        'Lose Yourself',
        '2024-01-22',
        2,
        'LoseYourself.jpg',
        3.4
    ),
    (
        'One Dance',
        '2024-01-20',
        3,
        'OneDance.jpg',
        3.9
    ),
    (
        'Too Good',
        '2024-01-21',
        3,
        'OneDance.jpg',
        4.2
    ),
    (
        'Hotline Bling',
        '2024-01-22',
        3,
        'OneDance.jpg',
        5.3
    ),
    (
        'A Tu Merced',
        '2024-01-20',
        4,
        'ATuMerced.jpg',
        4.6
    ),
    (
        'Amorfoda',
        '2024-01-21',
        4,
        'Amorfoda.jpg',
        3.9
    ),
    (
        'Soy Peor',
        '2024-01-22',
        4,
        'SoyPeor.jpg',
        5.5
    ),
    (
        'Cómete Mi Éxito',
        '2024-01-20',
        5,
        'CometeMiExito.jpg',
        4.3
    ),
    (
        'Modo Turbio',
        '2024-01-21',
        5,
        'ModoTurbio.jpg',
        3.7
    ),
    (
        '4 ROSES',
        '2024-01-22',
        5,
        '4Roses.jpg',
        5.6
    ),
    (
        'Sigo Trabajando',
        '2024-01-23',
        1,
        'imagen_sigo_trabajando.jpg',
        4.8
    ),
    (
        'Rap God',
        '2024-01-24',
        2,
        'imagen_rap_god.jpg',
        6.2
    ),
    (
        'In My Feelings',
        '2024-01-25',
        3,
        'imagen_in_my_feelings.jpg',
        4.5
    ),
    (
        'MIA',
        '2024-01-26',
        4,
        'imagen_la_noche_de_los_dos.jpg',
        5.1
    ),
    (
        'Déjate Llevar',
        '2024-01-27',
        5,
        'imagen_dejate_llevar.jpg',
        3.7
    ),
    (
        'Mockingbird',
        '2024-01-28',
        2,
        'imagen_mockingbird.jpg',
        4.3
    ),
    (
        'God''s Plan',
        '2024-01-29',
        3,
        'imagen_gods_plan.jpg',
        5.6
    ),
    (
        'Goteo',
        '2024-01-30',
        1,
        'imagen_goteo.jpg',
        3.9
    ),
    (
        'La Noche De Los Dos',
        '2024-01-31',
        4,
        'imagen_la_noche_de_los_dos2.jpg',
        4.1
    ),
    (
        'Darell',
        '2024-02-01',
        5,
        'imagen_darell.jpg',
        4.9
    );

-- Usuarios
INSERT IGNORE INTO USUARIO (nombreUsuario, password, imagen)
VALUES ('juanperez', 'contraseña1', '1.jpg'),
       ('mariagonzalez', 'contraseña', '2.jpg'),
       ('carloslopez', 'contraseña3', '3.jpg'),
       ('lauramartinez', 'contraseña', '4.jpg'),
       ('davidgarcia', 'contraseña5', '5.jpg');

-- Playlists
INSERT IGNORE INTO PLAYLIST (
        nombrePlaylist,
        userID,
        minutosTotales
    )
VALUES ('Canciones Tristes', 1, 21.8),
    ('Rap', 2, 23.5),
    ('Pop', 3, 19.9),
    ('Trap', 3, 22.6),
    ('Latino', 4, 21.8),
    ('Reggaeton', 2, 25.5),
    ('Bachata', 5, 19.3),
    ('Rock', 1, 24.7);

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


CREATE TABLE IF NOT EXISTS characters (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    occupation VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    image_url VARCHAR(255)
);

INSERT INTO characters (name, occupation, status, image_url) VALUES
('Walter White', 'Profesor de Química', 'ALIVE', 'https://example.com/walter-white.jpg'),
('Jesse Pinkman', 'Ex-estudiante', 'ALIVE', 'https://example.com/jesse-pinkman.jpg'),
('Skyler White', 'Contable', 'ALIVE', 'https://example.com/skyler-white.jpg'),
('Hank Schrader', 'Agente DEA', 'ALIVE', 'https://example.com/hank-schrader.jpg'),
('Marie Schrader', 'Técnico Radiológico', 'ALIVE', 'https://example.com/marie-schrader.jpg'),
('Walter White Jr.', 'Estudiante', 'ALIVE', 'https://example.com/walter-jr.jpg'),
('Saul Goodman', 'Abogado', 'ALIVE', 'https://example.com/saul-goodman.jpg'),
('Mike Ehrmantraut', 'Ex-policía', 'ALIVE', 'https://example.com/mike-ehrmantraut.jpg'),
('Gus Fring', 'Empresario', 'ALIVE', 'https://example.com/gus-fring.jpg'),
('Tuco Salamanca', 'Distribuidor', 'ALIVE', 'https://example.com/tuco-salamanca.jpg');

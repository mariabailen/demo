


-- Crear tabla usuario
CREATE TABLE usuario (
  "user" VARCHAR(255) UNIQUE NOT NULL,
  pass VARCHAR(255) NOT NULL,
  "role" VARCHAR(255) NOT NULL
);

-- Crear tabla cocinero
CREATE TABLE cocinero (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  apellido VARCHAR(255) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  image VARCHAR(255)
);

-- Crear tabla receta
CREATE TABLE receta (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  descripcion TEXT NOT NULL,
  cocinero_id INTEGER NOT NULL,
  FOREIGN KEY (cocinero_id) REFERENCES cocinero(id)
);

-- Crear tabla ingrediente
CREATE TABLE ingrediente (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  cantidad DECIMAL(10,2) NOT NULL,
  receta_id INTEGER NOT NULL,
  FOREIGN KEY (receta_id) REFERENCES receta(id)
);

-- Creat tabla para imagenes de receta
CREATE TABLE imagenReceta(
	id SERIAL PRIMARY KEY,
	url VARCHAR(255) NOT NULL,
	receta_id INTEGER NOT NULL,
	FOREIGN KEY (receta_id) REFERENCES receta(id)
);

-- Insertar datos de prueba en la tabla usuario
INSERT INTO usuario ("user", pass, "role") VALUES
  ('admin', '123', 'admin'),
  ('cocinero', '123', 'cocinero');

-- Insertar datos de prueba en la tabla cocinero
INSERT INTO cocinero (nombre, apellido, fecha_nacimiento, image) VALUES
  ('Juan', 'Pérez', '1980-01-01', NULL),
  ('María', 'Gómez', '1990-07-14', NULL),
  ('Pedro', 'López', '2000-12-25', NULL);

-- Insertar datos de prueba en la tabla receta
INSERT INTO receta (nombre, descripcion, cocinero_id) VALUES
  ('Tortilla de patatas', 'Receta tradicional española de patatas, huevos y cebolla.', 1),
  ('Paella valenciana', 'Arroz con azafrán, verduras, carne y marisco.', 2),
  ('Salmorejo cordobés', 'Crema fría de tomate, pan, ajo y aceite de oliva.', 3);

-- Insertar datos de prueba en la tabla ingrediente
INSERT INTO ingrediente (nombre, cantidad, receta_id) VALUES
  ('Patatas', 500, 1),
  ('Huevos', 4, 1),
  ('Cebolla', 1, 1),
  ('Arroz', 250, 2),
  ('Azafrán', 0.05, 2),
  ('Tomates', 1000, 3),
  ('Pan', 200, 3),
  ('Ajo', 2, 3),
  ('Aceite de oliva', 100, 3);

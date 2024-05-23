-- Crear tabla user
CREATE TABLE appuser (
  "user" VARCHAR(255) UNIQUE NOT NULL,
  pass VARCHAR(255) NOT NULL,
  "role" VARCHAR(255) NOT NULL
);

-- Crear tabla chef
CREATE TABLE chef (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  birthDate DATE NOT NULL,
  image VARCHAR(255)
);

-- Crear tabla receipt
CREATE TABLE receipt (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  chef_id INTEGER NOT NULL,
  FOREIGN KEY (chef_id) REFERENCES chef(id)
);

-- Crear tabla ingredient
CREATE TABLE ingredient (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  quantity DECIMAL(10,2) NOT NULL,
  receipt_id INTEGER NOT NULL,
  FOREIGN KEY (receipt_id) REFERENCES receipt(id)
);

-- Creat tabla para imagenes de receta
CREATE TABLE receiptImage(
	id SERIAL PRIMARY KEY,
	url VARCHAR(255) NOT NULL,
	receipt_id INTEGER NOT NULL,
	FOREIGN KEY (receipt_id) REFERENCES receipt(id)
);

-- Insertar user
INSERT INTO appuser ("user", pass, "role") VALUES
  ('admin', '123', 'admin'),
  ('cocinero', '123', 'cocinero');

-- Insertar chef
INSERT INTO chef (name, lastName, birthDate, image) VALUES
  ('Juan', 'Pérez', '1980-01-01', NULL),
  ('María', 'Gómez', '1990-07-14', NULL),
  ('Pedro', 'López', '2000-12-25', NULL);

-- Insertar receipt
INSERT INTO receipt (name, description, chef_id) VALUES
  ('Tortilla de patatas', 'Receta tradicional española de patatas, huevos y cebolla.', 1),
  ('Paella valenciana', 'Arroz con azafrán, verduras, carne y marisco.', 2),
  ('Salmorejo cordobés', 'Crema fría de tomate, pan, ajo y aceite de oliva.', 3);

-- Insertar ingredient
INSERT INTO ingredient (name, quantity, receipt_id) VALUES
  ('Patatas', 500, 1),
  ('Huevos', 4, 1),
  ('Cebolla', 1, 1),
  ('Arroz', 250, 2),
  ('Azafrán', 0.05, 2),
  ('Tomates', 1000, 3),
  ('Pan', 200, 3),
  ('Ajo', 2, 3),
  ('Aceite de oliva', 100, 3);

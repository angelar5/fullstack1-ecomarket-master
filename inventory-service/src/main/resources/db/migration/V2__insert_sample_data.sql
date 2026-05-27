INSERT INTO categories (name, description) VALUES
('Alimentos OrgÃ¡nicos', 'Alimentos producidos sin pesticidas ni quÃ­micos sintÃ©ticos.'),
('Hogar Sostenible', 'Productos ecolÃ³gicos y reutilizables para el hogar.'),
('Cuidado Personal Eco', 'Productos de aseo y cuidado personal biodegradables.');
INSERT INTO products (name, description, price, stock, category_id) VALUES
('Miel de Abeja OrgÃ¡nica 500g', 'Miel 100% pura y orgÃ¡nica.', 6500.00, 50, 1),
('Quinoa OrgÃ¡nica 1kg', 'Superalimento orgÃ¡nico rico en proteÃ­nas.', 4800.00, 100, 1),
('Cepillo de Dientes de BambÃº', 'Cepillo biodegradable.', 2500.00, 200, 3),
('ChampÃº SÃ³lido de Ortiga', 'ChampÃº zero-waste.', 5900.00, 80, 3),
('Bolsa de AlgodÃ³n OrgÃ¡nico', 'Bolsa reutilizable.', 3000.00, 150, 2);
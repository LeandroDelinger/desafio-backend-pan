--CREATE TABLE jpa_client_entity (
--    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--    cpf VARCHAR(14) NOT NULL UNIQUE,
--    name VARCHAR(255) NOT NULL,
--    date_of_birth DATE NOT NULL
--);
--
--INSERT INTO jpa_client_entity (id, cpf, name, date_of_birth) VALUES
--(UUID(), '123.456.789-00', 'Daniel', '2000-01-01');
--
--INSERT INTO jpa_client_entity (id, cpf, name, date_of_birth) VALUES
--(UUID(), '124.433.900-20', 'Wilys', '2000-02-01');
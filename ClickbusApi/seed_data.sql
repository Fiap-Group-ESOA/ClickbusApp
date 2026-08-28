-- Scripts SQL para Seed Data (Caso queira rodar manualmente no Banco de Dados)

INSERT INTO Rodoviarias (Nome, Cidade, SsidWifiLocal, DadosLayoutJson)
VALUES ('Terminal Rodoviário Tietê', 'São Paulo', 'Clickbus_Free_Wifi', '{"gates": [{"id": "08", "coords": "x:10,y:20"}], "pois": [{"name": "Banheiro PCD", "coords": "x:5,y:5"}]}');

INSERT INTO Usuarios (Nome, Email, CPF, SenhaHash, NecessitaAcessibilidade)
VALUES ('João Silva', 'joao@example.com', '123.456.789-00', 'AQAAAAEAACcQAAAAE...', 1);

INSERT INTO Passagens (UsuarioId, Origem, Destino, DataHora, RodoviariaId, PortaoEmbarque, Status)
VALUES (1, 'São Paulo', 'Rio de Janeiro', DATEADD(hour, 2, GETDATE()), 1, 'Portão 08 - Plataforma B', 'Confirmada');

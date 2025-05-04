INSERT INTO users (id, email, username, password, create_at) VALUES
('07f72990-f98e-4152-be6e-94639a612174','test@hotmail.com', 'Hyuse', '$2a$10$FGe2A1i.7XJlpxNJbZs2pOvLO1Kbf6OYl7bAXNnQug568LDlfTXX2', '2025-05-03 22:02:47.375899');


INSERT INTO matchs (team_a, team_b, date_hour, match_status, match_result, match_scoreboard) VALUES
('Furia', 'FaZe Clan', '2024-07-28 17:00:00', 'CLOSED', 'VICTORY', '2 - 1'),
('Furia', 'Cloud9', '2024-07-28 19:30:00', 'CLOSED', 'VICTORY', '2 - 0'),
('Furia', 'Natus Vincere', '2024-07-29 14:00:00', 'CLOSED', 'DEFEAT', '1 - 2'),
('Furia', 'Team Vitality', '2024-07-29 16:30:00', 'CLOSED', 'DEFEAT', '0 - 2'),
('Furia', 'Astralis', '2024-07-30 20:00:00', 'LIVE', NULL, '1 - 1'),
('Furia', 'G2 Esports', '2024-07-31 18:00:00', 'SCHEDULED', NULL, NULL),
('Furia', 'Mibr', '2024-08-01 15:00:00', 'SCHEDULED', NULL, NULL);

INSERT INTO teams (team_name, team_tag, team_country) VALUES
('Natus Vincere', 'NAVI', 'Ukraine'),
('Astralis', 'AST', 'Denmark'),
('G2 Esports', 'G2', 'Europe'),
('Team Vitality', 'VIT', 'France'),
('FaZe Clan', 'FaZe', 'Europe'),
('Liquid', 'Liquid', 'United States'),
('Mibr', 'Mibr', 'Brazil'),
('Furia', 'FUR', 'Brazil');

INSERT INTO players(name, team) VALUES
('FalleN', 'Furia'),
('yuurih', 'Furia'),
('YEKINDAR', 'Furia'),
('KSCERATO', 'Furia'),
('molodoy', 'Furia'),

('Aleksib', 'Natus Vincere'),
('iM', 'Natus Vincere'),
('b1t', 'Natus Vincere'),
('jL', 'Natus Vincere'),
('w0nderful', 'Natus Vincere'),

('apEX', 'Team Vitality'),
('ropz', 'Team Vitality'),
('ZywOo', 'Team Vitality'),
('flameZ', 'Team Vitality'),
('mezii', 'Team Vitality'),

('Snax', 'G2 Esports'),
('huNter-', 'G2 Esports'),
('malbsMd', 'G2 Esports'),
('mONESY', 'G2 Esports'),
('HeavyGod', 'G2 Esports'),

('device', 'Astralis'),
('cadiaN', 'Astralis'),
('stavn', 'Astralis'),
('jabbi', 'Astralis'),
('Staehr', 'Astralis'),

('NAF', 'Liquid'),
('NertZ', 'Liquid'),
('Twistzz', 'Liquid'),
('siuhy', 'Liquid'),
('ultimate', 'Liquid'),

('exit', 'Mibr'),
('Lucauzy', 'Mibr'),
('saffee', 'Mibr'),
('brnz4n', 'Mibr'),
('insani', 'Mibr'),

('karrigan', 'FaZe Clan'),
('rain', 'FaZe Clan'),
('EliGE', 'FaZe Clan'),
('frozen', 'FaZe Clan'),
('broky', 'FaZe Clan');
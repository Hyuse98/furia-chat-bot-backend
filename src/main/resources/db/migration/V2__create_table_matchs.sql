CREATE TABLE matchs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_a VARCHAR(255),
    team_b VARCHAR(255),
    date_hour DATETIME,
    match_status VARCHAR(50),
    match_result VARCHAR(255),
    match_scoreboard VARCHAR(255)
);

CREATE SEQUENCE matchs_seq
    START WITH 1
    INCREMENT BY 50;
CREATE TABLE teams (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(255),
    team_tag VARCHAR(255),
    team_country VARCHAR(255)
);

CREATE SEQUENCE teams_seq
    START WITH 1
    INCREMENT BY 50;
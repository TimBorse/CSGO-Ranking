CREATE TABLE IF NOT EXISTS Player (id  integer, name varchar(255), team_id integer, primary key (id));
CREATE TABLE IF NOT EXISTS Team (id  integer, name varchar(255), primary key (id));
CREATE TABLE IF NOT EXISTS User (name varchar(255) not null, primary key (name));
CREATE TABLE IF NOT EXISTS UserPlayer (id  integer, ranking integer not null, player_id integer, user_name varchar(255), primary key (id));
CREATE TABLE IF NOT EXISTS RankingURL (id integer not null, url varchar(255), primary key (id));
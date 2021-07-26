create table locations (id bigint auto_increment, location_name varchar(255), lat double, lon double, primary key (id));
insert into locations (location_name, lat, lon) values ("Budapest", "47.25", "19.19");
insert into locations (location_name, lat, lon) values ("Bécs", "47.56", "17.19");
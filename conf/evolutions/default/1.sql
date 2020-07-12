--- !Ups
create table posts
(
    id   SERIAL PRIMARY KEY,
    body varchar(255)
);

--- !Downs
drop table posts;
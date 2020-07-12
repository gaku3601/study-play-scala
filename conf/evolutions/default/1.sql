--- !Ups
create table diaries
(
    id   SERIAL PRIMARY KEY,
    body varchar(255)
);

--- !Downs
drop table diaries;
CREATE TABLE location
(
   id          smallint        NOT NULL,
   building    varchar(100),
   floor       varchar(100),
   address_id  integer
);

ALTER TABLE location
   ADD CONSTRAINT location_pkey
   PRIMARY KEY (id);

CREATE TABLE cat
(
   id               integer         NOT NULL,
   cat_name         varchar(100),
   cat_description  varchar(255)
);

ALTER TABLE cat
   ADD CONSTRAINT cat_pkey
   PRIMARY KEY (id);
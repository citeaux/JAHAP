
CREATE VIEW housekeeping
(
  id,
  code,
  cat_name,
  floor,
  clean,
  blocks
)
AS 
  SELECT rooms.id,
    rooms.code,
    cat.cat_name,
    location.floor,
    rooms.clean,
    string_agg(concat(to_char((occ.arrivaldate)::timestamp with time zone, 'DD.MM.YYYY'::text), '-', to_char((occ.departuredate)::timestamp with time zone, 'DD.MM.YYYY'::text)), ' / '::text) AS blocks
   FROM location,
    cat,
    rooms
     LEFT JOIN occ ON rooms.id = occ.room AND occ.arrivaldate >= 'now'::text::date
  WHERE rooms.location = location.id AND rooms.cat = cat.id 
  GROUP BY rooms.code, rooms.id, location.floor, cat.cat_name;


GRANT REFERENCES, INSERT, TRIGGER, UPDATE, SELECT, DELETE, TRUNCATE ON housekeeping TO postgres;

COMMENT ON VIEW housekeeping IS 'Shows all Rooms with Housekeepingblocks form current date to the futur';

CREATE VIEW Maintenance
(
  id,
  code,
  cat_name,
  floor,
  no_maintenance,
  blocks
)
AS
SELECT rooms.id,
    rooms.code,
    cat.cat_name,
    location.floor,
    rooms.no_maintenance,
    string_agg(concat(to_char(occ.arrivaldate::timestamp with time zone, 'DD.MM.YYYY'::text), '-', to_char(occ.departuredate::timestamp with time zone, 'DD.MM.YYYY'::text)), ' / '::text) AS blocks
   FROM location,
    cat,
    rooms
     LEFT JOIN occ ON rooms.id = occ.room AND occ.arrivaldate >= 'now'::text::date AND occ.maintenance IS NOT NULL
  WHERE rooms.location = location.id AND rooms.cat = cat.id
  GROUP BY rooms.code, rooms.id, location.floor, cat.cat_name;

GRANT REFERENCES, INSERT, TRIGGER, UPDATE, SELECT, DELETE, TRUNCATE ON Maintenance TO postgres;
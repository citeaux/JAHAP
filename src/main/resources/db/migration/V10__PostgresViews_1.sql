
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
    string_agg(concat(to_char(occ.arrivaldate::timestamp with time zone, 'DD.MM.YYYY'::text), '-', to_char(occ.departuredate::timestamp with time zone, 'DD.MM.YYYY'::text)), ' / '::text) AS blocks
   FROM occ,
    rooms,
    location,
    cat
  WHERE occ.room = rooms.id AND occ.arrivaldate >= 'now'::text::date AND occ.housekeeping IS NOT NULL AND rooms.location = location.id AND rooms.cat = cat.id
  GROUP BY rooms.code, rooms.id, location.floor, cat.cat_name;


GRANT REFERENCES, INSERT, TRIGGER, UPDATE, SELECT, DELETE, TRUNCATE ON housekeeping TO postgres;

COMMENT ON VIEW housekeeping IS 'Shows all Rooms with Housekeepingblocks form current date to the futur';


 SELECT rooms.id,
    rooms.code,
    cat.cat_name,
    location.floor,
    rooms.clean,
    string_agg(concat(to_char((( SELECT occ_1.arrivaldate
           FROM occ occ_1
          WHERE occ_1.arrivaldate >= 'now'::text::date AND rooms.id = occ.room))::timestamp with time zone, 'DD.MM.YYYY'::text), '-', to_char((( SELECT occ_1.departuredate
           FROM occ occ_1
          WHERE occ_1.arrivaldate >= 'now'::text::date AND rooms.id = occ.room))::timestamp with time zone, 'DD.MM.YYYY'::text)), ' / '::text) AS blocks
   FROM location,
    cat,
    rooms
     LEFT JOIN occ ON rooms.id = occ.room
  WHERE rooms.location = location.id AND rooms.cat = cat.id
  GROUP BY rooms.code, rooms.id, location.floor, cat.cat_name;
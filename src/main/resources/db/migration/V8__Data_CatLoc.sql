
INSERT INTO "public".cat (id, cat_name, cat_description) 
	VALUES (1, 'standard', 'standard'),
	VALUES (2, 'deluxe', 'deluxe'),
	VALUES (3, 'junior suite', 'junior suite');

INSERT INTO "public".location (id, building, floor, address_id) 
	VALUES (1, 'main building', '1st', 1),
	VALUES (2, 'main building', '2nd', 1);
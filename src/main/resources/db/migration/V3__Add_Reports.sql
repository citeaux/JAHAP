
-- Imports Report into DB
-- POSTGRESQL: the files have to be placed in the DATA folder

INSERT INTO REPORTS(ID,NAME,DESCRIPTION,REPORT_GROUP,REPORT,REPORT_LAYOUT,LANGUAGE)
       VALUES (1,'Address List','Address List','Address List',pg_read_binary_file('JAHAPREPORTS/addressList.jrxml'),pg_read_binary_file('JAHAPREPORTS/addressList.jasper'),1),
              (2,'Address','Address','Address',pg_read_binary_file('JAHAPREPORTS/address.jrxml'),pg_read_binary_file('JAHAPREPORTS/address.jasper'),1),
              (3,'Rate','Rate','Rate',pg_read_binary_file('JAHAPREPORTS/rate.jrxml'),pg_read_binary_file('JAHAPREPORTS/rate.jasper'),1),
              (4,'RateList','RateList','RateList',pg_read_binary_file('JAHAPREPORTS/ratesList.jrxml'),pg_read_binary_file('JAHAPREPORTS/ratesList.jasper'),1),
              (5,'Room','Room','Room',pg_read_binary_file('JAHAPREPORTS/room.jrxml'),pg_read_binary_file('JAHAPREPORTS/room.jasper'),1),
              (6,'Room list','Room list','Room list',pg_read_binary_file('JAHAPREPORTS/roomsList.jrxml'),pg_read_binary_file('JAHAPREPORTS/roomsList.jasper'),1);
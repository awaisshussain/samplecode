
-- TASK ONE - ADDING DATA - 16 marks in total
-- ======================
--
-- You must add the following data to that already inserted into the 6 table
-- schema.
-- DO NOT remove any existing records
--
-- ADD the following:
--
--  1 - Create a yourself as a member of staff which includes your name and where
--      the Employee_No is  your SRN. The remaining attributes can be data you make
--      up. There should be no duplication between your SRN and the data in the
--      dataset, but if this does occur, add 1 to your SRN.
--      You can choose which cinema you are employed at unsupervised!!
--                                                                  [ 4 marks ]
INSERT INTO a2_staff (employee_no, name, address, phone_no, DOB, date_joined, salary, supervisor, cinema)
VALUES ('19031526','Awais Hussain','75a, Sandley Towers, Sandley', '09623897336', TO_DATE('27/03/2002', 'DD/MM/YYYY'), TO_DATE('11/01/2021','DD/MM/YYYY'), 68347, null, 'Odeon on the Hill');
--  2 - Create two showings at a cinema and screen of your choice from the database.
--
--      Showing 1 MUST contain a single performance of a new film you must also add
--      to the film table. 
--						  						                    [ 6 marks ]
INSERT INTO a2_film (film_no, film_name, classification, duration, description, year_released)
VALUES ('15836667', 'Bad boys II', '15', 147, 'the film follows detectives Burnett and Lowrey investigating the flow of illegal drugs going into Miami.', TO_DATE('2003', 'YYYY'));
INSERT INTO a2_showing (showing_no, cinema, screen, film_no)
VALUES ('193365', 'Odeon on the Hill', 1, '15836667');
INSERT INTO a2_performance (showing_no, performance_date, performance_time, takings, attendees)
VALUES ('193365', TO_DATE('07/12/2021', 'DD/MM/YYYY'), TO_DATE('19:15', 'HH24:MI'), 22280, 190);
--  3 - Showing 2 MUST contain three performances of a film already in the
--      database showing on consecutive days.
--								  				                    [ 6 marks ]
INSERT INTO a2_showing (showing_no, cinema, screen, film_no)
VALUES ('194458', 'Odeon on the Hill', 1,'88854033');
INSERT INTO a2_performance (showing_no, performance_date, performance_time, takings, attendees)
VALUES ('194458', TO_DATE('10/12/2021', 'DD/MM/YYYY'), TO_DATE('21:00', 'HH24:MI'), '2128', '190');
INSERT INTO a2_performance (showing_no, performance_date, performance_time, takings, attendees)
VALUES ('194458', TO_DATE('11/12/2021', 'DD/MM/YYYY'), TO_DATE('21:00', 'HH24:MI'), '2128', '190');
INSERT INTO a2_performance (showing_no, performance_date, performance_time, takings, attendees)
VALUES ('194458', TO_DATE('12/12/2021', 'DD/MM/YYYY'), TO_DATE('21:00', 'HH24:MI'), '2128', '190');
--  NOTES:
--	Its a good idea to store your final INSERT commands in a script file
--
--	If during this process, you corrupt the dataset, go back and use the
--      script downloaded to reset the original tables and data
--
--	Once you are happy ALL INSERTS are correct, it may be a good idea to
--      run the two scripts ( supplied and yours) again to refresh the
--      dataset before starting Task 2
--
-- END OF TASK ONE ----------------------------------------------------
--
--
-- TASK TWO - QUERYING  [ 7 marks per query ] 84 in total
-- ===================
--
-- For this task use SQL Developer to build queries that provide the correct
-- answer to the question asked. Once the query is correct, COPY THE CODE INTO
-- THE SPACES PROVIDED. Answer as many questions if you can.
--
-- Hints are provided to help you understand what is needed
--
-- Solution Tests indicate how the output should appear if correct and contains
-- formatting guidance.
--
-- Submission instructions are given at the end of this file.
--
--
-- QUESTION 1
-- ==========
--
-- Produce a list of film names which have a date of release this century.  
-- 
-- Solution Test: 6 Films meet this criteria
--
-- FILM_NAME                                                                       
-- ---------------------------
-- Black Panther
-- Parasite
-- Avengers: Endgame
-- Knives Out
-- Us
-- Get Out
--
-- Type your query below:
SELECT film_name
FROM a2_film
WHERE year_released > TO_DATE('2010', 'YYYY');
-- QUESTION 2
-- ==========
--
-- Use a nested select statement to provide the full name and adress details of the cinemas
-- managed by the staff called Claire Wilson and Coren O'Halloran (DO NOT manually lookup
-- their Employee Numbers.)
-- 
-- Solution Test: 
-- CINEMA_NAME                                        LOCATION                                                                                            
-- -------------------------------------------------- -----------------------------------
-- The Glory Showhouse                                16, Leevale Drive, Linton                                                                           
-- Masterton Multiplex                                11, High Street, Masterton
--
-- Type your query below:
SELECT cinema_name, location
FROM a2_cinema 
WHERE manager IN ( SELECT employee_no
                  FROM a2_staff
                  WHERE name IN ('Claire Wilson' , 'Coren O''Halloran') );
-- QUESTION 3
-- ==========
--
-- Lec Dombrovski has phoned in sick, Create a query to report the name and phone
-- number of his supervisor.
--
-- Solution Test: 
-- NAME                                      PHONE_NO   
-- ----------------------------------------- -----------
-- Petr Cillich                              04992730026
--
-- Type your query below:
SELECT name, phone_no
FROM a2_staff
WHERE employee_no = (SELECT supervisor
                     FROM a2_staff
                     WHERE employee_no = '67628421');
-- QUESTION 4
-- ==========
--
-- Write a report of all films shown in August 2021 more than 16 times, give the films name,
-- how many performances of these films there were and how much those films took in total
-- over that period. List the films by the film that took the most money first, and provide
-- meaningful headings to the columns in the output as shown in the Solution Test below.
--
-- Hint: 
--
-- Solution Test:

-- FILM_NAME                   Performances Total Takings         
-- --------------------------- ------------ ----------------------
-- It Happened One Night       39               £63,571 
-- Modern Times                38               £58,332 
-- Parasite                    23               £37,195 
-- Knives Out                  22               £34,362 
-- Citizen Kane                25               £32,711 
-- The Wizard of Oz            18               £21,716 
-- Avengers: Endgame           18               £17,081
--
-- Type your query below:
SELECT f.film_name AS "Film Name", COUNT(s.showing_no) AS Performances, TO_CHAR(SUM(p.takings), 'L99G999') AS "Total Takings"
FROM a2_performance p JOIN a2_showing s ON (p.showing_no = S.showing_no) JOIN a2_film f ON (f.film_no = S.film_no)
WHERE performance_date BETWEEN TO_DATE ('01/08/2021', 'DD/MM/YYYY') AND TO_DATE ('31/08/2021', 'DD/MM/YYYY')
group by f.film_name
HAVING COUNT(s.showing_no) > 16
order by SUM(p.takings) desc ;
-- QUESTION 5
-- ==========
--
-- Report how much each cinema took on the 12th August, the report should
-- include the name of the cinema and the value of the takings in the report.
-- Order by highest takings first. The output should also be formatted as
-- shown below.
--
-- Cinema                         Takings on August 12  
-- ------------------------------ ----------------------
-- Masterton Multiplex            £5,731 
-- The Glory Showhouse            £2,424 
-- Grange Cinema                  £1,974 
-- Treban Picturehouse            £1,719 
-- Marvale Rex                    £1,005
--
-- Hint: Use the format operator (L) to create the use of the £
-- symbol.
--
-- Solution Test: 
--
-- Type your query below:
SELECT c.cinema, TO_CHAR(SUM(t.takings), 'L99G999') AS "Takings on August 12"
FROM a2_performance t JOIN a2_showing c ON (c.showing_no = t.showing_no)
WHERE performance_date IN TO_DATE('12/08/2021', 'DD/MM/YYYY')
GROUP BY c.cinema
order by SUM(t.takings) desc;
-- QUESTION 6
-- ==========
--
-- List the age in years of the oldest employee at each cinema. Order the report by the
-- cinema with the most employees first. Output should be formatted as below.
--
-- Hint: Use the examples in the SQL Sessions to determine the age in years of staff.
--
-- Solution Test:   
--
-- Cinema                                              Oldest Employee
-- -------------------------------------------------- ----------------
-- Masterton Multiplex                                              56
-- Grange Cinema                                                    48
-- The Glory Showhouse                                              52
-- Marvale Rex                                                      62
-- Odeon on the Hill                                                61
-- Flix                                                             57
-- Treban Picturehouse                                              34
--
-- Type your query below:
SELECT cinema, MAX(TRUNC(MONTHS_BETWEEN(SYSDATE,DOB)/12)) AS "Oldest Employee"
FROM a2_staff 
GROUP BY cinema
order by COUNT(cinema) desc;
-- QUESTION 7
-- ==========
--  
-- Which were the showings with the most performances? In which cinema were,
-- they shown, on which screen and how many performances were there starting
-- on which date. Format the output as given below:
--
-- Hint: Reserach the formatting operators fm and th
--
-- Solution Test: 
--
-- Cinema                                                 Screen Performances Started on                                                                     
-- -------------------------------------------------- ---------- ------------ -------------------------------------------------------------------------------
-- Marvale Rex                                                 1            9 Friday, September 3rd                                                          
-- Treban Picturehouse                                         2            9 Monday, August 9th                                                             
-- Treban Picturehouse                                         1            9 Monday, September 20th                                                         
-- Odeon on the Hill                                           2            9 Sunday, August 15th
--
-- Type your query below:
SELECT C.cinema AS "Cinema", C.screen AS "Screen", COUNT(*) AS "Performances", TO_CHAR(MIN(p.performance_date), 'fmDay, Month ddth') AS "Started on"
FROM a2_showing c INNER JOIN a2_performance p ON (c.showing_no = p.showing_no)
GROUP by C.cinema, C.screen, p.showing_no
HAVING COUNT(*) = (SELECT MAX(PERF)
                        FROM(
                            SELECT COUNT(*) AS "PERF"
                            FROM a2_showing CH INNER JOIN a2_performance PE ON (CH.showing_no = PE.showing_no)
                            GROUP BY CH.cinema, CH.screen, PE.showing_no))
ORDER BY 4,1;
-- QUESTION 8
-- ==========
--  
-- Produce a report for all showings of "Casablanca", providing the film name,
-- in which cinema each showing took place and the takings per seat available
-- and takings per person attending.
--
-- Hint: Use the total takings and the total capacity to determine the average
--       not the AVG function. Use formatting to limit the decimal places to 2.
--
-- Solution Test: 
-- FILM_NAME  CINEMA                                             Takings per seat Takings per person   
-- ---------- -------------------------------------------------- ---------------- ------------------
-- Casablanca Marvale Rex                                                   £9.62             £12.81 
-- Casablanca The Glory Showhouse                                          £12.14             £12.67 
-- Casablanca Treban Picturehouse                                          £10.87             £13.31 
-- Casablanca Grange Cinema                                                £15.02             £19.25 
--
-- Type your query below:
SELECT distinct f.film_name, s.cinema,'£' || cast(SUM(p.takings)/SUM(sc.capacity) AS decimal(5,2)) AS "Takings per seat", '£' || cast(SUM(p.takings)/SUM(p.attendees) AS decimal(5,2)) AS "taking per person"
FROM ((((a2_showing s INNER JOIN a2_film f ON (f.film_no = s.film_no))
                INNER JOIN a2_cinema c ON (c.cinema_name = s.cinema))
                INNER JOIN a2_screen sc ON (sc.cinema = s.cinema AND sc.screen = S.screen))
                INNER JOIN a2_performance p ON (s.showing_no = p.showing_no))
WHERE f.film_name = 'Casablanca'
GROUP BY f.film_name, s.cinema;
-- QUESTION 9
-- ==========
--
-- Write a query to list the cinema names of all cinemas which employ more than 12 employees.
-- 
-- Solution Test: 
--
-- CINEMA_NAME                                   Number of Staff
-- --------------------------------------------- ---------------
-- Grange Cinema                                              20
-- Masterton Multiplex                                        27
-- The Glory Showhouse                                        15
--
-- Type your query below:
SELECT cinema AS "Cinema Name", COUNT(employee_no) AS "Number of Staff"
FROM a2_staff
GROUP BY cinema
HAVING COUNT(employee_no) > 12;
-- QUESTION 10, 11, 12
-- ===================
-- Write three queries to provide information about YOU and YOUR orders from
-- Task 1
-- 
-- 10
-- ==
--
-- Create a single line report containing YOUR details as entered on the database
-- in the following format:
--
-- Fullname                        Address                                                   Employed for                                                                       
-- ------------------------------- --------------------------------------------------------- ---------------------------
-- Leon Marvin                     The Marches, Teal Avenue, Lindon                          9y 10m                                                                             
--	Fullname         Address                              Employed for                                                                    
--
-- Where 9y 10m indicates you has been employed for 9 complete years
-- plus 10 complete months at the time the query is run
--
-- Hint: 
--
-- https://docs.oracle.com/cd/E11882_01/server.112/e41084/functions002.htm#SQLRF51178
--
-- Type your query below:
SELECT name AS "Full Name", address, TRUNC(MONTHS_BETWEEN(SYSDATE,DATE_JOINED)/12) ||'y'||'  '||TRUNC(mod(MONTHS_BETWEEN(SYSDATE,DATE_JOINED),12))||'m' AS "Employed for"
FROM a2_staff
WHERE name = ('Awais Hussain');
--
-- 11
-- ==
--
-- Write a query to output the details of single film performance onput in
-- Task 1 part 2.
-- The details needed are the film name, and the cinema, screen, and date and
-- time it is being shown.
--
-- The headings and details should be meaningful to any reader of the report.
-- I.e.
--
-- Film Title                            Cinema                                Screen Date              Time 
-- ------------------------------------- ------------------------------------- ------ ----------------- -----
-- Modern Times                          Grange Cinema                         2      Wed 01-Sep-2021   08:15
--
-- Hint: 
--
-- Type your query below:
SELECT f.film_name AS "Film Title", s.cinema AS "Cinema", s.screen AS "Screen", TO_CHAR((p.performance_date), 'dy DD-month-YYYY') AS "Date", TO_CHAR((p.performance_time), 'HH24:MI' ) AS "Time"
FROM a2_film f JOIN a2_showing s ON (f.film_no = s.film_no) JOIN a2_performance p ON (s.showing_no = p.showing_no)
WHERE f.film_name = 'Bad boys II';
-- 12
-- ==
--
-- Write a query to output the details of the three night showing you entered as shown
-- below.
--
-- Showin Film Title            Cinema          Screen First Night    Last Night                                          
-- ------ --------------------- --------------- ------ -------------- ---------------
-- 183557 Knives Out            Grange Cinema        3 Fri August 6  Sun August 8                                    
--
-- Hint: 
--
-- Type your query below:
SELECT s.showing_no, f.film_name, s.cinema, s.screen, TO_CHAR(MIN(p.performance_date), 'Dy Month dd') AS "First Night", TO_CHAR(MAX(p.performance_date), 'Dy Month dd') AS " Last Night" 
FROM a2_film f JOIN a2_showing s ON (f.film_no = s.film_no) JOIN a2_performance p ON (s.showing_no = p.showing_no) 
WHERE s.showing_no = '194458' 
GROUP BY s.showing_no, f.film_name, s.cinema, s.screen;
-- END OF TASK TWO ---------------------------------------------------

--
-- END OF FILE ==================================================================




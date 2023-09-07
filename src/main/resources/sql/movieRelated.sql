DROP TABLE MOVIE;

CREATE TABLE MOVIE (
                         MOVIENO	NUMBER		NOT NULL,
                         TITLE	VARCHAR2(100)		NOT NULL,
                         RUNTIME	NUMBER		NOT NULL,
                         DIRECTOR	VARCHAR2(50)		NOT NULL,
                         GENRE	VARCHAR2(30)		NOT NULL,
                         RATING	NUMBER(1)		NOT NULL,
                         STARTDATE	DATE		NOT NULL,
                         ACTOR	VARCHAR2(50)		NOT NULL
);

DROP TABLE TICKET;

CREATE TABLE TICKET (
                          TICKETNO	VARCHAR2(30)		NOT NULL,
                          SEATS	NUMBER		NOT NULL,
                          PRICE	NUMBER		NOT NULL,
                          RSVDATE	DATE		NOT NULL,
                          CANCELDATE	DATE		NULL,
                          VALID	NUMBER(1)		NOT NULL,
                          USERNAME	VARCHAR2(50)		NOT NULL,
                          SCREENINFONO	NUMBER		NOT NULL
);


DROP TABLE SCREENINFO;

CREATE TABLE SCREENINFO (
                              SCREENINFONO	NUMBER		NOT NULL,
                              SCREENDATE	DATE		NOT NULL,
                              RUNTIME	NUMBER		NOT NULL,
                              THEATERNO	NUMBER		NOT NULL,
                              BRANCHNO	NUMBER		NOT NULL,
                              MOVIENO	NUMBER		NOT NULL
);

DROP TABLE SEATINFO;

CREATE TABLE SEATINFO (
                            SEATNO	VARCHAR(3)		NOT NULL,
                            SCREENINFONO	VARCHAR(255)		NOT NULL,
                            TICKETNO	VARCHAR2(30)		NOT NULL
);


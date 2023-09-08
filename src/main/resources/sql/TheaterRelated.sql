DROP TABLE THEATER;

CREATE TABLE THEATER (
                           THEATERNO	NUMBER		NOT NULL,
                           BRANCHNO	NUMBER		NOT NULL,
                           SEATROW	NUMBER		NOT NULL,
                           SEATCOL	NUMBER		NOT NULL
);

DROP TABLE BRANCH;

CREATE TABLE BRANCH (
                          BRANCHNO	NUMBER		NOT NULL,
                          LOCATIONNO	NUMBER		NOT NULL,
                          BRANCHNAME	VARCHAR2(30)		NOT NULL
);

DROP TABLE LOCATION;

CREATE TABLE LOCATION (
                            LOCATIONNO	NUMBER		NOT NULL,
                            LOCATIONNAME	VARCHAR2(30		NOT NULL
                                );
DROP TABLE FOOD;

CREATE TABLE FOOD (
                      FOODNO	NUMBER		NOT NULL,
                      FOODNAME	VARCHAR2(100)		NOT NULL,
                      PRICE	NUMBER		NOT NULL,
                      CATEGORY	VARCHAR2(100)		NOT NULL,
                      QUNTITY	NUMBER		NOT NULL
);

create sequence foodseq;
insert into food values (foodseq.nextval, 'test1', 1000, 'test1', 3000);
insert into food values (foodseq.nextval, 'test2', 1500, 'test2', 3000);

DROP TABLE REQUEST;

CREATE TABLE REQUEST (
                         REQUESTNO	NUMBER		NOT NULL,
                         USERNAME	VARCHAR2(50)		NOT NULL
);
create sequence requestseq;
insert into request values (requestseq.nextval, 'testid3');
select * from request;

DROP TABLE FOODREQUEST;

CREATE TABLE FOODREQUEST (
                             FOODREQUESTNO	NUMBER		NOT NULL,
                             REQUESTNO	NUMBER		NOT NULL,
                             FOODNO	NUMBER		NOT NULL,
                             QUNTITIY	NUMBER		NOT NULL
);
create sequence foodrequestseq;
insert into FOODREQUEST values (foodrequestseq.nextval, 1, 1, 10);
insert into FOODREQUEST values (foodrequestseq.nextval, 1, 2, 10);
insert into FOODREQUEST values (foodrequestseq.nextval, 2, 1, 20);
select * from FOODREQUEST;


-- 주문번호 - 총가격 가져오는 쿼리, 세부사항은 따로 다시 짜야함
SELECT T.REQUESTNO, SUM(F.PRICE * T.QUNTITIY) TOTAL
FROM FOOD F JOIN (
    SELECT R.REQUESTNO, R.USERNAME, FR.FOODNO, FR.QUNTITIY
    FROM REQUEST R JOIN FOODREQUEST FR ON R.REQUESTNO = FR.REQUESTNO
) T ON F.FOODNO = T.FOODNO
GROUP BY T.REQUESTNO;

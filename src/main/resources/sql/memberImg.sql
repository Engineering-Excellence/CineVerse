CREATE TABLE MEMBERIMG
(
    USERNAME VARCHAR2(30) NOT NULL,
    ABSPATH  VARCHAR2(300) NULL,
    RELPATH  VARCHAR2(300) NULL,
    FILENAME VARCHAR2(300) NULL
);

ALTER TABLE MEMBERIMG
    ADD CONSTRAINT MEMBERIMG_MEMBER_FK FOREIGN KEY (USERNAME) REFERENCES MEMBER (USERNAME) ON DELETE CASCADE;

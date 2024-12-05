-- user: spring, password: 1234

drop table tbl_member;

create table tbl_member
(
    member_id varchar2(10) primary key,
    member_pw varchar2(30) not null,
    member_name varchar2(30) not null,
    member_phone char(13) not null,
    member_age number,
    member_gender char(1) check(member_gender in ('M', 'W')) not null,
    enroll_date date not null
);

insert into tbl_member values ('admin', '1234', '김찬희', '010-8645-5542', 26, 'M', sysdate);

create table tbl_notice (
    notice_no number primary key,
    notice_title varchar2(200) not null,
    notice_content varchar2(2000) not null,
    notice_writer varchar2(30) references tbl_member(member_id) on delete cascade,
    notice_date date not null
);

commit;

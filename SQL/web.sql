create table tbl_reply(
rno number(10,0),
bno number(10,0) not null,
reply varchar2(1000) not null,
replyer varchar2(50) not null,
replyDate date default  sysdate,
updateDate date default sysdate
);

create SEQUENCE seq_reply;

alter table tbl_reply add CONSTRAINT pk_reply primary key (rno);

alter table tbl_reply add CONSTRAINT fk_reply_board FOREIGN KEY (bno) REFERENCES tbl_board(bno);


select * from tbl_Board where rownum < 10 order by bno desc;

select * from tbl_reply order by rno desc;

	select rno, bno, reply, replyer, replyDate, updatedate
	from tbl_reply where bno = 504 order by rno asc

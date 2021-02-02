create sequence seq_board;

create table tbl_board (
  bno number(10,0),
  title varchar2(200) not null,
  content varchar2(2000) not null,
  writer varchar2(50) not null,
  regdate date default sysdate, 
  updatedate date default sysdate
);

alter table tbl_board add constraint pk_board 
primary key (bno);

insert into tbl_board(bno, title, content, writer)
(select seq_board.nextval, title,content, writer from tbl_board);

commit;
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


   insert into tbl_reply (rno, bno, reply, replyer)
   values (seq_reply.nextval, 49, '추가내용','추가작성자')

    commit;
select * from tbl_Board where rownum < 10 order by bno desc;

select * from tbl_reply order by rno desc;

   select rno, bno, reply, replyer, replyDate, updatedate
   from tbl_reply where bno = 504 order by rno asc


select * from tbl_reply where bno = 49

create index idx_reply on tbl_reply (bno desc,rno asc);

select rno, bno, reply, replyer, replydate, updatedate from
(
select /*+index(tbl_reply idx_reply) */
rownum rn, bno, rno, reply, replyer, replyDate, updatedate
from tbl_reply where bno = 49 and rno > 0 and rownum <= 20
) where rn > 10;

commit;

alter table tbl_board add(replycnt number default 0);
update tbl_board set replycnt = (select count(rno) from tbl_reply 
where tbl_reply.bno = tbl_board.bno);

create table tbl_attach(
uuid varchar2(100) not null,
uploadPath varchar2(200) not null,
fileName varchar2(100) not null,
filetype char(1) default 'I',
bno number(10,0)
);

alter table tbl_attach add constraint pk_attach primary key(uuid);

alter table tbl_attach add constraint fk_board_attach foreign key(bno)
references tbl_board(bno);
commit
	insert into tbl_attach (uuid, uploadpath, filename, filetype, bno)
	values (124, 'zz', '파일네임', 'i', 5)
select * from tbl_attach;
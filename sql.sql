select rownum, bno, title from spring_board;


select rownum, bno, title from spring_board where rownum <= 10 order by bno;

insert into spring_board(bno,title,content,writer) (select seq_board.nextval, title, content, writer from spring_board);


-- �ֽű� 10�� ���� (1~10)
select * 
from (select rownum rn, bno, title, writer 
	from(select bno,title,writer from spring_board where bno>0 order by bno desc) 
	where rownum <= 10) 
where rn>0;

-- �� ���� �ֽű� 10�� (11~20)
select * 
from (select rownum rn, bno, title, writer 
	from(select bno,title,writer from spring_board where bno>0 order by bno desc) 
	where rownum <= 20) 
where rn>10;

-- ����Ŭ ��Ʈ �̿��ϱ�
select * 
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
		rownum rn, bno, title, writer
	from SPRING_BOARD
	where rownum <= 20) 
where rn>10;


-- ��� ���̺� �ۼ�

create table spring_reply(
	rno number(10,0) constraint pk_reply primary key, -- ��� �۹�ȣ
	bno number(10,0) not null, -- ���� �۹�ȣ
	reply varchar2(1000) not null, -- ��� ����
	replyer varchar2(50) not null, -- ��� �ۼ���
	replydate date default sysdate,
	updatedate date default sysdate,
	constraint fk_reply_board foreign key(bno) references spring_board(bno)
);

create sequence seq_reply;

select * from SPRING_REPLY ;

select * from idx_reply ;

select * from fk_reply_board;

create index idx_reply on spring_reply(bno desc, rno asc);


		select rno,bno,reply,replyer,replydate,updatedate
		from(
			select /*+INDEX(spring_reply idx_reply)*/
				rownum rn, rno, bno, reply, replyer, replydate, updatedate
			from spring_reply
			where bno=266 and rno > 0 and rownum <= 2 * 10
		)
		where rn > (2-1) * 10;
		

select * from spring_reply where bno=266;

-- ��� �� ���� Į�� �߰�

alter table spring_board add(replycnt number default 0);

select * from spring_board;


update SPRING_BOARD 
set replycnt = (select count(bno) from spring_reply where SPRING_BOARD.bno = SPRING_REPLY.bno);


-- ���� ÷�� ���̺�

create table spring_attach(
	uuid varchar2(100) not null,
	uploadPath varchar2(200) not null,
	fileName varchar2(100) not null,
	fileType char(1) default 'I',
	bno number(10,0)
);
	
alter table spring_attach add constraint pk_attach primary key(uuid);

alter table spring_attach add constraint fk_board_attach foreign key(bno) references spring_board(bno);

select * from spring_attach;

-- ���� ��¥
select * from spring_attach
where uploadPath = to_char(sysdate-1,'yyyy\mm\dd');


-- member ���̺�
create table spring_member(
	userid varchar2(50) not null primary key,
	userpw varchar2(100) not null,
	username varchar2(100) not null,
	regdate date default sysdate,
	updatedate date default sysdate,
	enabled char(1) default '1'
	);

--�� member �� ������ �� ���� ���̺�
create table spring_member_auth(
	userid varchar2(50) not null,
	auth varchar2(50) not null,
	constraint fk_member_auth foreign key(userid) references spring_member(userid)
	);

	select * from spring_member;
	select * from spring_member_auth;
	
	drop table spring_member_auth;
	
	
	select * from persistent_logins;
	
	select * from SPRING_REPLY where bno = 328;

	select * from spring_board;
	
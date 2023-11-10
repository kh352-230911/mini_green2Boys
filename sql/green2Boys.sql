-- (system 계정) account계정 생성 account/account

alter session set "_oracle_script" = true;

show user;

create user account
identified by "account"
default tablespace users;

grant connect, resource to account;
alter user account quota unlimited on users;

--==================================

-- 회원 테이블
create table ac_member (
    id varchar2(20) not null,
    password varchar2(20) not null,
    name varchar2(100) not null,
    age varchar2(100) not null,
    email varchar2(500),
    address varchar2(2000) not null,
    manager number default 0 not null,
    created_at date default sysdate,
    
    constraints pk_ac_member_id primary key(id),
    constraints uq_ac_member_password unique (password),
    constraints ck_ac_member_age check (age in ( '10대', '20대', '30대', '40대', '50대이상') )
);

select * from ac_member;

-- 시퀀스 생성
create sequence seq_ac_member;

-- 헬스장 테이블
create table ac_gym (
    no number not null,
    health_name varchar2(100) not null,
    health_addr varchar2(500) not null,
    max_people number,
    current_people number,
    price number,
    
    constraints pk_ac_gym_no primary key(no),
    constraints uq_ac_gym_health_name unique(health_name),
    constraints uq_ac_gym_health_addr unique(health_addr),
    constraints ck_ac_gym_max_people check (max_people >= 0),
    constraints ck_ac_gym_current_people check (current_people >= 0),
    constraints ck_ac_gym_price check (price >= 0)
);

-- 시퀀스 생성
create sequence seq_ac_gym_no;
create sequence seq_ac_exer_hist_no;

desc ac_gym;
select * from ac_gym;

drop table ac_exer_hist;
-- 운동 기록 테이블
create table ac_exer_hist (
    no number, 
    member_id varchar2(20),
    gym_name varchar2(100),
    exer_product varchar2(100),
    exer_history varchar2(2000),
    exer_day timestamp default sysdate,
    
    constraints pk_ac_exer_hist_no primary key(no),
    constraints fk_ac_exer_hist_member_id foreign key(member_id) references ac_member(id) on delete cascade,
    constraints fk_ac_exer_hist_gym_name foreign key(gym_name) references ac_gym(health_name),
    constraints fk_ac_exer_hist_exer_product foreign key(exer_product) references ac_exer_recom(exercises)
);

desc ac_exer_hist;
select * from ac_exer_recom;

select * from ac_exer_hist;

-- 시퀀스 생성
create sequence ac_exer_hist_no;

select * from ac_exer_hist;
desc ac_exer_hist;

-- 운동 추천 테이블
create table ac_exer_recom (
    exe_no number,
    exe_level number not null,
    exercies varchar2(100) not null,
    
    constraints pk_ac_exer_recom_exe_no primary key(exe_no),
    constraints uq_ac_exer_recom_exercies unique(exercies),
    constraints ck_ac_exer_recom_exe_level check(exe_level in ('1', '2', '3'))
);

-- 시퀀스 생성
create sequence seq_ac_exer_recom_no;

desc ac_exer_recom;

-- 문의 내역 테이블
create table ac_question(
        ask_no number,
        ask_id varchar2(20) not null,
        category varchar2(1000) not null,
        detail varchar2(2000),
        ask_created_at timestamp default sysdate,
        constraints pk_ac_question_ask_no primary key(ask_no),
        constraints fk_ac_question_ask_id foreign key(ask_id) references ac_member(id)
);

-- 시퀀스 생성
create sequence seq_ac_ask_no;

select * from ac_question;
--------------------------------------------------------------------------------------------
-- 헬스장 리뷰 테이블
create table ac_review(
        rev_no number,
        rev_id varchar2(20) not null,
        rev_addr varchar2(300) not null,
        rev_contents varchar2(2000),
        scores number,
        rev_created_at timestamp default sysdate,
        
        constraints pk_ac_review_rev_no primary key(rev_no),
        constraints fk_ac_review_rev_id foreign key(rev_id) references ac_member(id) on delete cascade,
        constraints fk_ac_review_rev_addr foreign key(rev_addr) references ac_exer_hist(gym_name)
);
-- 시퀀스 생성
create sequence seq_ac_review_rev_no;

desc ac_review;
select * from ac_review;

commit;

------------------------------------------
-- 문의 답변 테이블
create table ac_question_answer(
        answer_no number,
        manager_id varchar2(20) not null,
        detail varchar2(2000),
        answer_created_at timestamp default sysdate,
        
        constraints pk_ac_question_answer_answer_no primary key(answer_no),
        constraints fk_ac_question_answer_answer_no foreign key(answer_no) references ac_question(ask_no) on delete cascade,
        constraints fk_ac_question_answer_manager_id foreign key(manager_id) references ac_member(id) on delete cascade
);

select * from ac_question_answer;

-- 탈퇴테이블 생성 및 트리거 생성
set serveroutput on;

create table ac_member_log
as
select 
    m.*,
    sysdate log_date
from
    ac_member m
where
1 = 2;

select * from ac_member_log;

alter table ac_member_log
add constraint pk_ac_member_log_id primary key(id);

create or replace trigger trig_delete_ac_member_out
    after
    delete on ac_member
    for each row
begin
    if deleting then
    insert into
        ac_member_log(
        id, password, name, age, email, address, manager, created_at, log_date
        )
    values(
    :old.id,
    :old.password,
    :old.name,
    :old.age,
    :old.email,
    :old.address,
    :old.manager,
    :old.created_at,
    sysdate
    );
end if;
end;
/

commit;


--=========================================
-- DML [ 샘플 데이터 삽입 ]

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '서초헬스크럽',
    '서울특별시 서초구 사평대로 349  (반포동,2층)',
    200,
    0,
    200000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '그린헬스',
    '서울특별시 서초구 동광로 18  (방배동)',
    180,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '현대헬스',
    '서울특별시 서초구 방배천로 22  (방배동,3층)',
    150,
    0,
    250000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '헬스토피아',
    '서울특별시 서초구 사평대로 362  (서초동,(3층))',
    160,
    0,
    280000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '헬스토피아',
    '서울특별시 서초구 사평대로 362  (서초동,(3층))',
    160,
    0,
    280000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '동호헬스클럽',
    '서울특별시 서초구 고무래로10길 17  (반포동,4층)',
    165,
    0,
    350000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '그린짐',
    '서울특별시 서초구 효령로 36  (방배동)',
    165,
    0,
    350000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '싸이버짐',
    '서울특별시 서초구 잠원로 94, B1층 (잠원동, 한신빌딩)',
    100,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '한전아트센터스포츠클럽',
    '서울특별시 서초구 효령로72길 60 (서초동)',
    120,
    0,
    280000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '국가대표 소유창 휘트니스',
    '서울특별시 서초구 강남대로 617 (잠원동, 대양빌딩 4/5/6층)',
    200,
    0,
    400000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '우성헬스 2',
    '서울특별시 서초구 서초중앙로 72  (서초동,지하1층)',
    200,
    0,
    400000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '방배휘트니스클럽',
    '서울특별시 서초구 동광로 69  (방배동,2층)',
    150,
    0,
    350000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '제이디아이스포츠',
    '서울특별시 서초구 반포대로12길 10  (서초동,4층)',
    180,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '코리아짐',
    '서울특별시 서초구 서운로 142-4  (서초동,3층)',
    140,
    0,
    220000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '아이러브 휘트니스',
    '서울특별시 서초구 강남대로 27 (양재동,농수산물유통공사 지하1층)',
    130,
    0,
    250000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '파워짐헬스크럽',
    '서울특별시 서초구 서초대로77길 25  (서초동,경일빌딩 7층)',
    140,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '코아텔휘트니스클럽',
    '서울특별시 서초구 강남대로53길 11  (서초동,지하2층)',
    150,
    0,
    350000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '대한퍼스널트레이닝',
    '서울특별시 서초구 사평대로55길 139  (반포동,미성빌딩4층)',
    80,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '헬싱타운',
    '서울특별시 서초구 남부순환로 2636 (양재동,성문빌딩 지1층)',
    120,
    0,
    350000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '듀발휘트니스',
    '서울특별시 서초구 서초중앙로22길 25  (서초동,서초리시온 2층)',
    140,
    0,
    320000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '탑',
    '서울특별시 서초구 효령로53길 45  (서초동,서초이오빌 지1층)',
    100,
    0,
    280000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '우노 휘트니스클럽',
    '서울특별시 용산구 보광동 260-8 지상3층',
    200,
    0,
    400000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    'AK운동맞춤센터',
    '서울특별시 용산구 용산동2가 23',
    200,
    0,
    400000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '웰니스짐',
    '서울특별시 용산구 이태원동 226-3 지하1층',
    400,
    0,
    200000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '동국스포츠',
    '서울특별시 용산구 원효로4가 142-1 2.3층',
    340,
    0,
    240000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '리콥 웰니스센터',
    '서울특별시 용산구 한남동 657-201',
    400,
    0,
    500000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    'J헬스클럽',
    '서울특별시 용산구 한남동 631-5 4층',
    500,
    0,
    500000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '스카이 휘트니스클럽',
    '서울특별시 용산구 남영동 127-1 2층,3층',
    300,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '해밀톤 휘트니스센터',
    '서울특별시 용산구 이태원동 116-1 지하2층',
    250,
    0,
    250000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '드래곤힐스파휘트니스클럽',
    '서울특별시 용산구 한강로3가 40-713 4층',
    500,
    0,
    500000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '원짐',
    '서울특별시 용산구 한강로3가 16-85 GS한강에클라트',
    400,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '에이치 앤 휘트니스',
    '서울특별시 용산구 한남동 96-3 신성미소시티 지하1층',
    400,
    0,
    400000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '밸런스 핏',
    '서울특별시 용산구 청파동3가 111-9 캠퍼스프라자 4층',
    600,
    0,
    700000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '스포짐옐로우 이촌점',
    '서울특별시 용산구 이촌동 300-21 유일빌딩 지하1층',
    300,
    0,
    600000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '킴스짐헬스크럽',
    '서울특별시 용산구 이태원동 124-3 지하1층',
    200,
    0,
    400000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '센티넬 크로스핏 한남1',
    '서울특별시 용산구 한남동 635-1 지하1층',
    250,
    0,
    500000
);


insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '위너핏PT스튜디오',
    '서울특별시 용산구 남영동 61-4',
    280,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '스포짐',
    '서울특별시 용산구 문배동 40-31',
    300,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    '프라이빗 웰니스',
    '서울특별시 용산구 한남동 740-1',
    400,
    0,
    500000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
    'H 스튜디오',
    '서울특별시 용산구 한남동 79-3',
    150,
    0,
    200000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '나의 근육 사용설명서',
   '서울특별시 성동구 성수동2가 284-62',
    280,
    0,
    600000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '파크짐 한양대점',
   '서울특별시 성동구 행당동 32-6',
    190,
    0,
    700000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '크로스핏 아띠',
   '서울특별시 성동구 도선동 402 승우카센타',
    100,
    0,
    800000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '모스트짐',
   '서울특별시 성동구 성수동1가 13-441',
    70,
    0,
    650000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '리피티',
   '서울특별시 성동구 하왕십리동 959-2',
    280,
    0,
    600000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '힐앤핏',
   '서울특별시 성동구 옥수동 218-1',
    360,
    0,
    450000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '해빗피트니스 옥수점',
   '서울특별시 성동구 옥수동 563',
    450,
    0,
    500000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '운동연구소',
   '서울특별시 성동구 옥수동 284-14 동호빌딩',
    80,
    0,
    900000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '핏바디짐',
   '서울특별시 성동구 성수동2가 317-4 청운재',
    100,
    0,
    600000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '바른핏',
   '서울특별시 성동구 옥수동 323-17 조을빌딩',
    70,
    0,
    800000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '휴펫필라테스 앤피티 왕십리점',
   '서울특별시 성동구 하왕십리동 966-14',
    80,
    0,
    600000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '건강과땀 휘트니스센타',
   '서울특별시 성동구 행당동 17',
    95,
    0,
    700000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '인피니티 퍼스널 트레이닝',
   '서울특별시 성동구 금호동1가 481-5',
    50,
    0,
    400000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '센트라스 투엑스 주식회사',
   '서울특별시 성동구 하왕십리동 700',
    80,
    0,
    600000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '커브스 금호클럽',
   '서울특별시 성동구 금호동4가 544',
    400,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '테라피티',
   '서울특별시 성동구 성수동1가 13-135',
    80,
    0,
    600000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '피네테스',
   '서울특별시 성동구 옥수동 244-11 4층',
    100,
    0,
    800000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '허니바디 스포츠',
   '서울특별시 성동구 성수동1가 13-436',
    60,
    0,
    450000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '메디앤핏',
   '서울특별시 성동구 홍익동 396-1번지 김원자산부인과 2층',
    400,
    0,
    300000
);

insert into ac_gym values (
    seq_ac_gym_no.nextval,
   '레이 휘트니스',
   '서울특별시 성동구 행당동 128-22',
    300,
    0,
    800000
);

select * from ac_gym;
--==================================

insert into ac_member values(
    'engyoung',
    '1234',
    '이은경',
    '20대',
    'gyoung@naver.com',
    '서울특별시 서초구 행복동',
    default,
    sysdate
);

insert into ac_member values (
    'ensuck',
    'abc123',
    '김은석',
    '30대',
    'ensuck@naver.com',
    '서울특별시 서초구 이상동',
    default,
    sysdate
);

insert into ac_member values (
    'enseong',
    'abc1234',
    '임은성',
    '40대',
    'enseong@naver.com',
    '서울특별시 서초구 상상동',
    default,
    sysdate
);

insert into ac_member values (
    'jaemin',
    'ab123',
    '송재민',
    '10대',
    'jaemin@naver.com',
    '서울특별시 서초구 보상동',
    default,
    sysdate
);

insert into ac_member values (
    'jangook',
    '456789',
    '박장욱',
    '20대',
    'jangook@naver.com',
    '서울특별시 서초구 상두동',
    default,
    sysdate
);

insert into ac_member values(
    'jandi',
    'ab12367',
    '윤잔디',
    '30대',
    'jandi@naver.com',
    '서울특별시 서초구 두상동',
    default,
    sysdate
);

insert into ac_member values(
    'chanwoo',
    'ab123987',
    '정찬우',
    '40대',
    'chanwoo@naver.com',
    '서울특별시 서초구 보수동',
    default,
    sysdate
);

insert into ac_member values(
    'jaemoo',
    'abc12367',
    '김재무',
    '20대',
    'jaemoo@naver.com',
    '서울특별시 서초구 보수동',
    default,
    sysdate
);

insert into ac_member values(
    'jaehyun',
    'abc4567',
    '우재현',
    '50대이상',
    'jaehyun@naver.com',
    '서울특별시 서초구 보수동',
    default,
    sysdate
);

insert into ac_member values(
    'woophil',
    'abcd456',
    '전우필',
    '30대',
    'woophil@naver.com',
    '서울특별시 서초구 보수동',
    1,
    sysdate
);

insert into ac_member values (
    'lee.hj',
    '12341234',
    '이호준',
    '20대',
    'leehj@naver.com',
    '서울특별시 용산구 남영동',
    default,
    sysdate
);

insert into ac_member values (
    'lee.hd',
    '43214321',
    '이홍대',
    '30대',
    'leehd@naver.com',
    '서울특별시 용산구 문배동',
    default,
    sysdate
);

insert into ac_member values (
    'lee.hwaj',
    '123123',
    '이화정',
    '40대',
    'leehwaj@naver.com',
    '서울특별시 용산구 보광동',
    default,
    sysdate
);

insert into ac_member values (
    'lee.hs',
    '9191',
    '이희수',
    '30대',
    'leehs@naver.com',
    '서울특별시 용산구 용산동',
    default,
    sysdate
);

insert into ac_member values (
    'in.js',
    '142142',
    '임정성',
    '50대이상',
    'leehj@naver.com',
    '서울특별시 용산구 이촌동',
    default,
    sysdate
);

insert into ac_member values (
    'im.kb',
    '2425',
    '임기범',
    '10대',
    'imkb@naver.com',
    '서울특별시 용산구 이태원동',
    default,
    sysdate
);

insert into ac_member values (
    'im.dr',
    '1541',
    '임도란',
    '10대',
    'imdr@naver.com',
    '서울특별시 용산구 정파동',
    default,
    sysdate
);

insert into ac_member values (
    'im.je',
    '2222',
    '임지은',
    '20대',
    'imje@naver.com',
    '서울특별시 용산구 한남동',
    default,
    sysdate
);

insert into ac_member values (
    'im.ch',
    '1122',
    '임충헌',
    '20대',
    'imch@naver.com',
    '서울특별시 용산구 이촌동',
    default,
    sysdate
);

insert into ac_member values (
    'im.cm',
    '4949',
    '임채민',
    '30대',
    'imch@naver.com',
    '서울특별시 용산구 남영동',
    1,
    sysdate
);

insert into ac_member values(
    'dohun',
    'asd123',
    '최도훈',
    '20대',
    'dohun@naver.com',
    '서울특별시 성동구 옥수동',
    default,
    sysdate
);

insert into ac_member values(
    'hyosun',
    'zxzc123',
    '장효선',
    '30대',
    'hyosun@naver.com',
    '서울특별시 성동구 하왕십리동',
    default,
    sysdate
);

insert into ac_member values(
    'jonone',
    'nph123',
    '전원영',
    '40대',
    'jonone@naver.com',
    '서울특별시 성동구 행당동',
    default,
    sysdate
);

insert into ac_member values(
    'jaehn',
    'qlx233',
    '최재훈',
    '50대이상',
    'jaehn@naver.com',
    '서울특별시 성동구 금호동1가',
    default,
    sysdate
);

insert into ac_member values(
    'jonggi',
    'vkdo553',
    '정기원',
    '10대',
    'jonggi@naver.com',
    '서울특별시 성동구 하왕십리동',
    default,
    sysdate
);

insert into ac_member values(
    'jongseo',
    '12345',
    '정승수',
    '20대',
    'jongseo@naver.com',
    '서울특별시 성동구 금호동4가',
    default,
    sysdate
);

insert into ac_member values(
    'jeongae',
    '81531',
    '정애리',
    '30대',
    'jeongae@naver.com',
    '서울특별시 성동구 홍익동',
    default,
    sysdate
);

insert into ac_member values(
    'minjun',
    '308930',
    '김우빈',
    '20대',
    'minjun@naver.com',
    '서울특별시 성동구 성수동1가',
    default,
    sysdate
);

insert into ac_member values(
    'duckbae',
    '4627',
    '홍덕배',
    '30대',
    'duckbae@naver.com',
    '서울특별시 성동구 도선동',
    default,
    sysdate
);

insert into ac_member values(
    'honggongju',
    '2178',
    '홍찬희',
    '20대',
    'hongchan@naver.com',
    '서울특별시 성동구 성수동2가',
    1,
    sysdate
);

select * from ac_member;


--==================================

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    1,
    '랫풀다운'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    1,
    '솔더프레스 머신'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    1,
    '싸이클'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    1,
    '크런치'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    1,
    '레터럴 레이즈머신'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    2,
    '시티드로우머신'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    2,
    '덤벨 벤치 프레스'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    2,
    '런닝머신'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    2,
    '레그 익스텐션'
);

insert into ac_exer_recom values (
    seq_ac_exer_recom_no.nextval,
    2,
    '레그레이즈'
);

insert into ac_exer_recom values(
    seq_ac_exer_recom_no.nextval,
    3,
    '벤치프레스'
);



insert into ac_exer_recom values(
    seq_ac_exer_recom_no.nextval,
    3,
    '스쿼트'
);

insert into ac_exer_recom values(
    seq_ac_exer_recom_no.nextval,
    3,
    '데드리프트'
);

insert into ac_exer_recom values(
    seq_ac_exer_recom_no.nextval,
    3,
    '천국의계단'
);

insert into ac_exer_recom values(
    seq_ac_exer_recom_no.nextval,
    3,
    '덤벨런지'
);

select * from ac_exer_recom;
select * from ac_member;
select * from ac_gym;

--=================================

select * from ac_exer_hist;

insert into ac_exer_hist values (
    seq_ac_exer_hist_no.nextval,
    'lee.hj',
    '드래곤힐스파휘트니스클럽',
    '랫풀다운',
    '첫 운동은 괜찮았다.',
    sysdate
);

insert into ac_exer_hist values (
    seq_ac_exer_hist_no.nextval,
    'lee.hwaj',
    '에이치 앤 휘트니스',
    '런닝머신',
    '가벼운 유산소 운동을 했다.',
    sysdate
);

insert into ac_exer_hist values (
    seq_ac_exer_hist_no.nextval,
    'im.dr',
    '리콥 웰니스센터',
    '시티드로우머신',
    '이 운동은 너무 힘들었다.',
    sysdate
);

insert into ac_exer_hist values (
    seq_ac_exer_hist_no.nextval,
    'im.ch',
    '스카이 휘트니스클럽',
    '데드리프트',
    '오늘 목표치를 달성하였다',
    sysdate
);

insert into ac_exer_hist values (
    seq_ac_exer_hist_no.nextval,
    'im.kb',
    '원짐',
    '천국의계단',
    '오늘 너무 힘들었다.',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'honggongju',
    '모스트짐',
    '데드리프트',
    '역시 쇠질은 재밌다',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'duckbae',
    '힐앤핏',
    '벤치프레스',
    '쫀쫀한 느낌 잊을수 없다',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'minjun',
    '핏바디짐',
    '스쿼트',
    '중력을 드는건 자랑스러운 일이었다',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'hyosun',
    '바른핏',
    '런닝머신',
    '달릴때 살아있음을 느끼는거같다',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'dohun',
    '레이 휘트니스',
    '크런치',
    '근육통으로 인해서 헬스장 그만 나가야겠다',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'engyoung',
    '그린헬스',
    '랫풀다운',
    '근육이 맛있다.',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'ensuck',
    '현대헬스',
    '싸이클',
    '다리 근육이 맛있다.',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'enseong',
    '헬스토피아',
    '런닝머신',
    '하루가 상쾌하다',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'jaemin',
    '그린짐',
    '크런치',
    '바삭바삭 운동이 맛있네',
    sysdate
);

insert into ac_exer_hist values(
    seq_ac_exer_hist_no.nextval,
    'jaehyun',
    '방배휘트니스클럽',
    '천국의계단',
    '천국갔다 돌아왔다.',
    sysdate
);

commit;

--=====================================

select * from ac_exer_recom;
select * from ac_member;
select * from ac_gym;
select * from ac_exer_hist;

desc ac_member;
desc ac_gym;

select * from ac_gym where health_addr like '%용산%';

select * from ac_gym;


commit;

select * from ac_review;
select * from ac_question;



select * from ac_review;

select * from ac_exer_hist;

desc ac_review;
desc ac_member;

select * from ac_review;
select * from ac_member;

select * from ac_review;
select * from ac_exer_hist;

delete from ac_review;


commit;

select * from ac_question;

select * from ac_member;
lee.hj
im.cm
commit;

select * from ac_review;
select * from ac_member;

select * from ac_question;

--==============================================


select * from ac_member;

select * from ac_question;

commit;

select * from ac_exer_hist;

select * from ac_gym;
in.js
select * from ac_member;
commit;
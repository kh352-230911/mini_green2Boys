-- (system계정) cartoon계정 생성
-- cartoon/cartoon
alter session set "_oracle_script" = true;

create user cartoon
identified by cartoon
default tablespace users;

grant connect,resource to cartoon;
alter user cartoon quota unlimited on users;

-- 장르 테이블 생성
create table genre (
    genre_id varchar2(10),
    genre_title varchar2(50) not null,
    constraints pk_genere_genre_id primary key(genre_id)
    );


-- 장르 테이블 값 대입

insert into genre values ('G1', '액션');
insert into genre values ('G2', '성장만화');
insert into genre values ('G3', '수사,범죄');
insert into genre values ('G4', '코미디');
insert into genre values ('G5', '스릴러');
insert into genre values ('G6', 'SF,판타지');
insert into genre values ('G7', '로맨스');
insert into genre values ('G8', '무협');
insert into genre values ('G9', '역사');
insert into genre values ('G10', '기타');
        
select * from genre;


-- 도서 테이블 생성
create table book (
    book_no number,
    book_name varchar2(100) not null,
    author varchar2(50),
    publisher varchar2(50),
    book_genre varchar2(20),
    pb_date date,
    constraints pk_book_book_no primary key (book_no),
    constraints uq_book_book_name unique(book_name),
    constraints uq_book_book_genre foreign key (book_genre) references genre(genre_id) 
);

-- 시퀀스를 이용해 book_no를 채번
create sequence seq_book_book_no;
insert into book values (seq_book_book_no.nextval, '각시탈', '허영만', '거북이북스', 'G1', '20120615');
insert into book values (seq_book_book_no.nextval, '건스미스캣츠', '소노다 켄이치', '대원씨아이', 'G1', '20041010');
insert into book values (seq_book_book_no.nextval, '베이블레이드X', '카와모토 호무라', '쇼가쿠칸', 'G1', '20230620');
insert into book values (seq_book_book_no.nextval, '치킨파이터', '사쿠라타니', '히어로즈', 'G1', '20211012');
insert into book values (seq_book_book_no.nextval, '쾌걸근육맨', '유데타마고', '슈에이샤', 'G1', '20110509');


insert into book values (seq_book_book_no.nextval, '달려라 하니', '이진주', '바다출판사', 'G2', '19850103');
insert into book values (seq_book_book_no.nextval, '메달리스트', '츠루마 이카다', '코단샤', 'G2', '20200510');
insert into book values (seq_book_book_no.nextval, '겁쟁이페달', '와타나베 와타루', '아키타 쇼텐', 'G2', '20150613');
insert into book values (seq_book_book_no.nextval, '캐릭캐릭체인지', '오경화', '코단샤', 'G2', '20110812');
insert into book values (seq_book_book_no.nextval, '하트스토퍼', '엘리스 오스먼', '웹툰', 'G2', '20160409');

insert into book values (seq_book_book_no.nextval, '소년탐정 김전일', '사토 후미야', '코단샤', 'G3', '20050315');
insert into book values (seq_book_book_no.nextval, '명탐정 코난', '아오야마 고쇼', '쇼가쿠칸', 'G3', '19940523');
insert into book values (seq_book_book_no.nextval, '뱃보이즈', '타나카 히로시', '영킹', 'G3', '20030220');
insert into book values (seq_book_book_no.nextval, '모범택시', '까를로스', '투니드', 'G3', '20141112');
insert into book values (seq_book_book_no.nextval, '쿠조의 대죄', '마나베 쇼헤이', '대원씨아이', 'G3', '20220509');

insert into book values (seq_book_book_no.nextval, '무적코털보보보', '사와이 요시오', '슈에이샤', 'G4', '20011215');
insert into book values (seq_book_book_no.nextval, '닥터 슬럼프', '토리야마 아키라', '슈에이샤', 'G4', '19980210');
insert into book values (seq_book_book_no.nextval, '침략 오징어소녀', '안베 마사히로', '아키타 쇼텐', 'G4', '20071211');
insert into book values (seq_book_book_no.nextval, '유유백서', '토가시 요시히로', '슈에이샤', 'G4', '19940612');
insert into book values (seq_book_book_no.nextval, '피너츠', '찰스슐츠', '판타그램', 'G4', '20161122');

insert into book values (seq_book_book_no.nextval, '도쿄구울', '이시다 스이', '슈에이샤', 'G5', '20140908');
insert into book values (seq_book_book_no.nextval, '지옥선생 누베', '마쿠라 쇼', '슈에이샤', 'G5', '20041010');
insert into book values (seq_book_book_no.nextval, '빨간마스크', '이누키 카나코', ' 넥스큐브', 'G5', '19950611');
insert into book values (seq_book_book_no.nextval, '사이렌', '사카이 츠토무', '슈에이샤', 'G5', '20180302');
insert into book values (seq_book_book_no.nextval, '미미의 괴담', '이토 준지', '미디어 팩토리', 'G5', '20040726');

insert into book values (seq_book_book_no.nextval, '드래곤볼 슈퍼', '토리야마 아키라', '슈에이샤', 'G6', '20150623');
insert into book values (seq_book_book_no.nextval, '테라포마스', '사스가 유', '슈에이샤', 'G6', '20181119');
insert into book values (seq_book_book_no.nextval, '우주전함 티라미스', '미야가와 사토시', '신쵸우샤', 'G6', '20200409');
insert into book values (seq_book_book_no.nextval, '월드 트리거', '아시하라 다이스케', '슈에이샤', 'G6', '20131211');
insert into book values (seq_book_book_no.nextval, '플루토', '데즈카 오사무', '서울문화사', 'G6', '20090630');

insert into book values (seq_book_book_no.nextval, '러브히나', '아카마츠 켄', '코단샤', 'G7', '20020115');
insert into book values (seq_book_book_no.nextval, '오 나의 여신님', '후지시마 코스케', '코단샤', 'G7', '20140723');
insert into book values (seq_book_book_no.nextval, '마호라바', '코지마 아키라', '스퀘어 에닉스', 'G7', '20060727');
insert into book values (seq_book_book_no.nextval, '스쿨럼블', '코바야시 진', '코단샤', 'G7', '20080912');
insert into book values (seq_book_book_no.nextval, '센류소녀', '이가라시 마사쿠니', '코단샤', 'G7', '20200617');

insert into book values (seq_book_book_no.nextval, '바람의 나라', '김진', '이코믹스미디어', 'G8', '19920216');
insert into book values (seq_book_book_no.nextval, '북두의 권', '부론손', '학산문화사', 'G8', '19890301');
insert into book values (seq_book_book_no.nextval, '천랑열전', '박성우', '서울문화사', 'G8', '20000510');
insert into book values (seq_book_book_no.nextval, '열혈강호', '전극진', '코믹챔프', 'G8', '19940520');
insert into book values (seq_book_book_no.nextval, '나루토', '키시모토 마사시', '대원씨아이', 'G8', '20150204');

insert into book values (seq_book_book_no.nextval, '킹덤', '하라 야스히사', '슈에이샤', 'G9', '20060123');
insert into book values (seq_book_book_no.nextval, '삼국지', '고우영', '문학동네', 'G9', '20021023');
insert into book values (seq_book_book_no.nextval, '야뇌 백동수', '이재헌', '무툰', 'G9', '20100130');
insert into book values (seq_book_book_no.nextval, '배가본드', '이노우에 다케히코', '주간 모닝', 'G9', '20140723');
insert into book values (seq_book_book_no.nextval, '맹꽁이 서당', '윤승운', '웅진씽크빅', 'G9', '19940520');

insert into book values (seq_book_book_no.nextval, '낚시 소녀', '야마사키 나츠키', '스퀘어 에닉스', 'G10', '20230925');
insert into book values (seq_book_book_no.nextval, '곤돌린의 몰락', '존로널드', '아르테', 'G10', '20030510');
insert into book values (seq_book_book_no.nextval, '축복을 비는 마음', '김혜진', '문학과지성사', 'G10', '20151201');
insert into book values (seq_book_book_no.nextval, '마리암의 노래', '주동근', '태학사', 'G10', '20130922');
insert into book values (seq_book_book_no.nextval, '연수', '장류진', '창비', 'G10', '20150419');

select * from book;

-- food table 생성
create table food (
    food_no number
   , food_name varchar2(50)not null
   , food_price  number not null
   , food_category varchar2(50)
   , constraints pk_food_food_no primary key(food_no)
);
--drop table food;
select * from food;
    --메뉴 넣기
----------------------------------------------------------------
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (1, '아메리카노', 3000, '에스프레소');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (2, '카페라떼', 4000, '에스프레소');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (3, '바닐라라떼', 4000, '에스프레소');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (4, '카라멜마끼아또', 4500, '에스프레소');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (5, '카페모카', 4500, '에스프레소');





------------------------------------------------------
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (6, '녹차라떼', 4000, '라떼');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (7, '고구마 라떼', 4500, '라떼');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (8, '초코 라떼', 5000, '라떼');




------------------------------------------------------
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (9, '초코바나나', 5000, '티/배버리지');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES( 10, '아이스티', 3000, '티/배버리지'); 

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (11, '녹차', 3000, '티/배버리지');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (12, '유자차', 3000, '티/배버리지');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (13, '미숫가루', 4500, '티/배버리지');




--------------------------------------------------------------

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (14, '요거트 스무디', 5000, '에이드/블랜디드');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (15, '딸기 스무드', 5500, '에이드/블랜디드');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (16, '블루베리 스무디', 5000, '에이드/블랜디드');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (17, '생과일 주스(딸기)', 5000, '에이드/블랜디드');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (18, '생과일 주스(블루베리)', 5500, '에이드/블랜디드');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (19, '생과일 주스(딸기바나나)', 5000, '에이드/블랜디드');

------------------------------------------------------------------

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (20, '스팸계란 볶음밥', 6000, '라이스류');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (21, '치킨마요 볶음밥', 6000, '라이스류');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (22, '김치베이컨 볶음밥', 6500, '라이스류');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (23, '제육덮밥', 6500, '라이스류');

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (24, '랜덤볶음밥', 6000, '라이스류');

--------------------------------------------------------------

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (25, '라면', 4000, '분식류');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (26, '짜파게뤼', 4000, '분식류');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (27, '존맛떡볶이', 4500, '분식류');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (28, '로제 떡볶이', 5500, '분식류');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (29, '짜장 떡볶이', 5500, '분식류');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (30, '라볶이', 5500, '분식류');
--------------------------------------------------------------


INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (31, '크림치즈 프레즐', 4000, '베이커리');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (32, '블부베리/어니언 베이글', 4500, '베이커리');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (33, '와플', 4500, '베이커리');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (34, '츄러스', 4500, '베이커리');

--------------------------------------------------------------

INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (35, '치즈 덮은 소세지', 2500, '사이드메뉴');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (36, 'DDQ 닭강정', 6500, '사이드메뉴');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (37, '오징오징무징어', 2500, '사이드메뉴');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (38, '과자류', 2000, '사이드메뉴');
INSERT INTO food (food_no, food_name, food_price, food_category)
VALUES (39, '치즈추가', 500, '사이드메뉴');


-- 회원 정보 테이블 (users)
create table users (
        id varchar2(20)
        , password varchar2(50) not null
        , name varchar2(20) not null
        , gender char(1) not null
        , birthday date not null
        , phone varchar2(100) not null
        , email varchar2(100)
        , created_at date default sysdate
        , charge_cash number default 0
        , balance number default 0
        , point number default 0
        , is_manager char(1) default 'N'
        , favorite_genre varchar2(10) 
        
        , constraints pk_users_id primary key(id)
        , constraints uq_users_phone unique(phone)
        , constraints uq_users_email unique(email)
        , constraints ck_users_gender check(gender in ('M', 'F'))
        , constraints ck_users_is_manager check(is_manager in ('Y','N'))
        , constraints fk_users_favorite_genre foreign key (favorite_genre) references genre(genre_id)
);




-- tb_user삭제
--drop table users;
--drop table member;

-- 조회
select * from users;

 -- 삽입
insert into users values('woojin', 'dnwls7182', '오우진', 'M', '1998-03-23', '010-5049-4819', 'wojin@naver.com', default, default, default, default, 'Y', null); 
insert into users values('cjsanwls', 'cmj0276', '천무진', 'M', '1994-11-11', '010-2328-0276', 'anwls@naver.com', default, default, default, default, 'Y', null);
insert into users values('honggd', 'gildong', '홍길동', 'M', '1996-10-21', '010-3318-3416', 'honggd@naver.com', default, default, default, default, default,'G1');
insert into users values('janggill', 'gilsan', '장길산', 'M', '1987-04-29', '010-1218-3786', 'gilsna@naver.com', default, default, default, default, default, 'G1');
insert into users values('imkeuk', 'kkukjung', '임꺽정', 'M', '2000-12-21', '010-1982-0116', 'imkeuk@naver.com', default, default, default, default, default, 'G1');
insert into users values('sinsa', 'imdang', '신사임당', 'F', '1999-09-12', '010-3218-1416', 'sinsa@naver.com', default, default, default, default, default, 'G1');
insert into users values('yanggui', 'guibee', '양귀비', 'F', '2001-01-02', '010-7149-1311', 'yanggui@naver.com', default, default, default, default, default, 'G1');
insert into users values('heona', 'iop098', '신현아', 'F', '1998-03-02', '010-8201-1416', 'heona@naver.com', default, default, default, default, default, 'G1');
insert into users values('blackbean', 'imdang', '검은콩', 'M', '1999-09-12', '010-3218-1116', 'blackbean@naver.com', default, default, default, default, default, 'G2');
insert into users values('leewon', 'wonjong', '이원종', 'M', '1999-02-10', '010-5050-1416', 'wonjong@naver.com', default, default, default, default, default, 'G2');
insert into users values('keounjin', 'jinbeaom', '권진범', 'M', '1998-01-12', '010-7430-1016', 'jinbeom@naver.com', default, default, default, default, default, 'G2');
insert into users values('kimhea', 'heayou', '김혜유', 'F', '1998-12-11', '010-6518-1436', 'kimhea@naver.com', default, default, default, default, default, 'G2');
insert into users values('leess', 'sunsin', '이순신', 'M', '1977-04-15', '010-1541-3116', 'leess@gmail.com', default, default, default, default, default, 'G2');
insert into users values('janda', 'dareke', '잔다르크', 'F', '2005-04-15', '010-1101-9646', 'janda@gmail.com', default, default, default, default, default, 'G2');
insert into users values('kimso', 'sojin', '김소진', 'F', '2009-08-23', '010-4142-9116', 'sojin@gmail.com', default, default, default, default, default, 'G3');
insert into users values('kimsu', 'sujin', '김수진', 'F', '2009-04-25', '010-9941-9116', 'kimsu@naver.com', default, default, default, default, default, 'G3');
insert into users values('jangbo', 'boree', '장보리', 'F', '2009-11-19', '010-0441-0196', 'jongbo@gmail.com', default, default, default, default, default, 'G3');
insert into users values('bakseo', 'seoyoung', '박서영', 'F', '1998-06-18', '010-9641-3245', 'bakseo@gmail.com', default, default, default, default, default, 'G3');
insert into users values('kimmin', 'minjoung', '김민중', 'M', '1996-11-03', '010-5461-3176', 'kimmin@gmail.com', default, default, default, default, default, 'G3');
insert into users values('kimbeom', 'beomgu', '김범구', 'M', '1996-12-19', '010-1440-7194', 'kimbeom@naver.com', default, default, default, default, default, 'G3');
insert into users values('bakheon', 'heongu', '박현규', 'M', '1998-07-19', '010-2103-5666', 'bakheon@gmail.com', default, default, default, default, default, 'G4');
insert into users values('youji', 'jigheon', '유지현', 'M', '1995-01-24', '010-8439-6072', 'jiheon@naver.com', default, default, default, default, default, 'G4');
insert into users values('jeongjeo', 'jeonghun', '정정훈', 'M', '1998-11-05', '010-7775-0012', 'jeonghun@gmail.com', default, default, default, default, default, 'G4');
insert into users values('cheonsa', 'sarang', '천사랑', 'F', '2011-09-29', '010-2221-9316', 'sarang@gmail.com', default, default, default, default, default, 'G4');
insert into users values('bakmari', 'mari', '백마리', 'F', '1988-04-11', '010-1003-2696', 'mari@naver.com', default, default, default, default, default, 'G4');
insert into users values('sinsung', 'sunga', '신승아', 'F', '2003-07-17', '010-3344-5591', 'sunga@gmail.com', default, default, default, default, default, 'G4');
insert into users values('baekseong', 'seongsu', '백승수', 'M', '1973-05-01', '010-0501-9497', 'seongsu@gmail.com', default, default, default, default, default, 'G5');
insert into users values('jeongjae', 'jaeheon', '전재현', 'M', '1998-12-19', '010-3076-9341', 'jaeheon@naver.com', default, default, default, default, default, 'G5');
insert into users values('kimeun', 'euna', '김윤아', 'F', '1991-07-04', '010-3646-3561', 'euna@gmail.com', default, default, default, default, default, 'G5');
insert into users values('namju', 'juheong', '남주형', 'M', '1992-08-14', '010-8431-7613', 'juheong@naver.com', default, default, default, default, default, 'G5');
insert into users values('kimyou', 'yosin', '김유신', 'M', '2000-06-03', '010-7476-4331', 'yosin@naver.com', default, default, default, default, default, 'G5');
insert into users values('hana', 'ari', '한아리', 'F', '2005-01-07', '010-3926-7352', 'ari@naver.com', default, default, default, default, default, 'G5');
insert into users values('leea', 'arum', '이아름', 'F', '2004-06-09', '010-6710-4213', 'leea@gmail.com', default, default, default, default, default, 'G6');
insert into users values('leejin', 'leejin', '이진', 'M', '1998-05-12', '010-5576-7741', 'leejin@naver.com', default, default, default, default, default, 'G6');
insert into users values('janjun', 'junheong', '장준형', 'M', '1998-03-29', '010-5376-1741', 'jangjun@naver.com', default, default, default, default, default, 'G6');
insert into users values('hansu', 'suhee', '한수희', 'F', '1998-05-04', '010-3476-7341', 'suhee@naver.com', default, default, default, default, default, 'G6');
insert into users values('hanbo', 'bogeong', '한보경', 'F', '1998-02-07', '010-3916-3349', 'bogeong@gmail.com', default, default, default, default, default, 'G6');
insert into users values('ouyoung', 'youngou', '우영우', 'F', '1981-08-11', '010-1276-8431', 'youngou@naver.com', default, default, default, default, default, 'G6');
insert into users values('kimro', 'rowoon', '김로운', 'M', '2007-01-14', '010-1920-9763', 'rowoon@naver.com', default, default, default, default, default, 'G7');
insert into users values('ronaldo', 'ronaldo', '호날두', 'M', '2001-06-20', '010-8176-0941', 'ronaldo@gmail.com', default, default, default, default, default, 'G7');
insert into users values('messi', 'messi', '김메시', 'M', '2001-02-04', '010-3776-5931', 'messi@gmail.com', default, default, default, default, default, 'G7');
insert into users values('leedae', 'daeo', '이대오', 'M', '2010-08-08', '010-5576-5951', 'daeo@naver.com', default, default, default, default, default, 'G7');
insert into users values('kimjae', 'jaehwong', '김재황', 'M', '1999-04-02', '010-2316-2931', 'kimjae@gmail.com', default, default, default, default, default, 'G7');
insert into users values('son7', 'son7', '손홍민', 'M', '1992-07-07', '010-7776-7971', 'son7@gmail.com', default, default, default, default, default, 'G7');
insert into users values('leesong', 'songwo', '이송우', 'M', '2007-04-22', '010-2716-1932', 'songwo@gmail.com', default, default, default, default, default, 'G7');
insert into users values('park13', 'jeasong', '박치성', 'M', '1985-10-04', '010-3106-2437', 'park13@gmail.com', default, default, default, default, default, 'G8');
insert into users values('hwang', 'chan', '황찬', 'M', '2003-08-14', '010-6771-8231', 'hwangchan@gmail.com', default, default, default, default, default, 'G8');
insert into users values('parkjong', 'jongoun', '박종운', 'M', '1983-05-24', '010-1276-5131', 'parkjong@gmail.com', default, default, default, default, default, 'G8');
insert into users values('star01', 'kimstar', '김스타', 'M', '1983-12-14', '010-6294-3628', 'kimstar@gmail.com', default, default, default, default, default, 'G8');
insert into users values('son', 'genius', '손영재', 'F', '2004-07-19', '010-8176-8632', 'genius@gmail.com', default, default, default, default, default, 'G8');
insert into users values('icequeen', 'kimheona', '김현아', 'F', '1997-03-12', '010-1176-0131', 'icequeen@gmail.com', default, default, default, default, default, 'G8');
insert into users values('week', 'leeju', '이주빈', 'F', '2004-05-16', '010-8076-0841', 'week@gmail.com', default, default, default, default, default, 'G8');
insert into users values('sinyoon', 'yoonbean', '신윤빈', 'F', '2004-02-22', '010-2276-2331', 'sinyoo@gmail.com', default, default, default, default, default, 'G9');
insert into users values('kimyeon', 'yeonkk', '김연굥', 'F', '1988-10-24', '010-5376-0564', 'kimyeon@gmail.com', default, default, default, default, default, 'G9');
insert into users values('yanseo', 'seoyong', '안새영', 'F', '2006-06-13', '010-2017-2391', 'seoyong@gmail.com', default, default, default, default, default, 'G9');
insert into users values('leeso', 'sodragon', '이소용', 'F', '2006-12-08', '010-5416-7431', 'leesoo@gmail.com', default, default, default, default, default, 'G9');
insert into users values('jack', 'reper', '잭더리퍼', 'M', '1972-11-08', '010-3316-3439', 'jack@gmail.com', default, default, default, default, default, 'G9');
insert into users values('jesica', 'jesica', '제시카', 'F', '1990-07-18', '010-1816-6637', 'jesica@gmail.com', default, default, default, default, default, 'G9');
insert into users values('juhong', 'hongnam', '주홍남', 'M', '1999-07-12', '010-7816-9039', 'juhong@gmail.com', default, default, default, default, default, 'G10');
insert into users values('chamu', 'musick', '차무식', 'M', '1982-11-18', '010-1609-0409', 'musick@naver.com', default, default, default, default, default, 'G10');
insert into users values('yoongue', 'guenam', '윤귀남', 'M', '2005-05-11', '010-2316-0091', 'guenam@gmail.com', default, default, default, default, default, 'G10');
insert into users values('ohmy', 'mygost', '오나귀', 'M', '2003-07-26', '010-1216-7439', 'gost@gmail.com', default, default, default, default, default, 'G10');
insert into users values('leebeong', 'chun', '이병천', 'M', '1962-11-08', '011-8916-4710', 'chun@gmail.com', default, default, default, default, default, 'G10');
insert into users values('junheon', 'heonwoo', '전현우', 'M', '1980-09-22', '010-1536-3732', 'jun@gmail.com', default, default, default, default, default, 'G10');
insert into users values('leeyeoun', 'yeounbok', '이인백', 'M', '2000-01-28', '010-9123-3449', 'leeyeoun@gmail.com', default, default, default, default, default, 'G10');






-- del_users_log 트리거 관련 sql 코드

-- member_del 테이블 생성

create table del_users_log
as
select
    u.* 
    , systimestamp del_date
from 
    users u
where
    1 = 0; 
    
desc del_users_log;

select * from del_users_log;

-- trig_delete_member 생성
create or replace trigger trig_delete_users
    after
    delete on users
    for each row
begin
        if deleting then 
         insert into
                    del_users_log
            values (
                    :old.id
                    , :old.password
                    , :old.name
                    , :old.gender
                    , :old.birthday
                    , :old.phone
                    , :old.email
                    , :old.created_at
                    , :old.charge_cash
                    , :old.balance
                    , :old.point
                    , :old.is_manager
                    , :old.favorite_genre
                    , sysdate);      
    end if;
end;
/

--delete from users where id = 'leeyeoun';
select * from del_users_log;
select * from users;
select * from genre;
select * from book;
select * from food;
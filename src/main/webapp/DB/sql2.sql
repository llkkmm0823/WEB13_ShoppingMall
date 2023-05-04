
---------------------------------------------샘플 데이터 입력---------------------------------------------

--관리자 입력
insert into worker values('admin','admin','관리자','010-7777-7777');
insert into worker values('scott','tiger','홍길동','010-6400-6068');

--회원 입력
insert into member(id,pwd,name,zip_num,address1,address2,phone,email) values
('one','1111','김나리','133-110','서울시 성동구 성수동1가','1번지 21호','017-777-7777','acc@abc.com');
insert into member(id,pwd,name,zip_num,address1,address2,phone,email) values
('two','2222','김길동','133-120','서울시 송파구 잠실2동','리렌츠아파트 201동 505호','011-123-4567','acc@abc.com');

select*from worker;
select*from member;



--상품입력

insert into product(pseq,name,kind,price1,price2,price3,content,image) values
(product_seq.nextVal,'크로코다일부츠','2',40000,50000,10000,'오리지날 크로코다일부츠입니다.','w2.jpg');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn) values
(product_seq.nextVal,'롱부츠','2',40000,50000,10000,'따뜻한 롱부츠입니다.','w-28.jpg','n');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn) values
(product_seq.nextVal,'힐','1',10000,12000,2000,'여성전용 힐.','w14.jpg','n');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn) values
(product_seq.nextVal,'슬리퍼','4',5000,5500,500,'편안한 슬리퍼입니다.','w-25.jpg','y');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn) values
(product_seq.nextVal,'회색힐','1',10000,12000,2000,'여성전용 힐.','w-23.jpg','n');

insert into product(pseq,name,kind,price1,price2,price3,content,image) values
(product_seq.nextVal,'여성부츠','2',12000,18000,6000,'여성용부츠.','w4.jpg');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn) values
(product_seq.nextVal,'핑크샌달','3',5000,5500,500,'사계절용 샌달입니다.','w-24.jpg','y');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn) values
(product_seq.nextVal,'슬리퍼','3',5000,5500,500,'편안한 슬리퍼입니다.','w11.jpg','y');

insert into product(pseq,name,kind,price1,price2,price3,content,image) values
(product_seq.nextVal,'스니커즈','4',15000,2000,5000,'활동성이 좋은 스니커즈입니다.','w-13.jpg');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn) values
(product_seq.nextVal,'샌달','3',5000,5500,500,'사계절용 샌달입니다.','w-09.jpg','n');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn) values
(product_seq.nextVal,'스니커즈','5',5000,5500,500,'활동성이 좋은 스니커즈입니다.','w-05.jpg','n');

select*from product;

update product set bestyn=upper(bestyn);
--해당 칼럼을 모두 대문자로 (upper)


--카트 추가
select*from cart;

insert into cart(cseq,id,pseq,quantity)values(cart_seq.nextVal,'one',2,1);	--id:one 사용자가 1번상품 1개를
insert into cart(cseq,id,pseq,quantity)values(cart_seq.nextVal,'two',3,1);


--orders와 order_detail 추가
select*from orders;
select*from order_detail;

insert into orders(oseq, id) values(orders_seq.nextVal,'one');
insert into order_detail(odseq,oseq,pseq,quantity)values(order_detail_seq.nextVal,1,2,1);
insert into order_detail(odseq,oseq,pseq,quantity)values(order_detail_seq.nextVal,1,3,2);

insert into orders(oseq, id) values(orders_seq.nextVal,'two');
insert into order_detail(odseq,oseq,pseq,quantity)values(order_detail_seq.nextVal,2,4,3);
insert into order_detail(odseq,oseq,pseq,quantity)values(order_detail_seq.nextVal,2,5,2);

insert into orders(oseq, id) values(orders_seq.nextVal,'one');
insert into order_detail(odseq,oseq,pseq,quantity)values(order_detail_seq.nextVal,3,3,1);
insert into order_detail(odseq,oseq,pseq,quantity)values(order_detail_seq.nextVal,3,2,1);







DROP DATABASE coronakitdb;

CREATE DATABASE coronakitdb;

USE coronakitdb;



CREATE TABLE item(
				pid integer primary key,
				pname varchar(20) not null,
				pcost varchar(20) not null,
				pdesc varchar(50) not null
				);
								
insert into item values(1,"Face Mask","99","N-95 Face Mask");
insert into item values(4,"Sanitizer","49","Dettol Sanitizer");
insert into item values(2,"Vitamin C","144","Boost immunity");
insert into item values(3,"Amla Juice","110","Patanjali Amla Juice");	

CREATE TABLE customer(
				cid integer primary key,
				cname varchar(20) not null,
				cemail varchar(30) not null,
				cphone varchar(20) not null,
				caddress varchar(200)
								);
								
insert into customer values(1,"Raj","raj@gmail.com","9739715432","WF, Marathalli Bangalore- 560087");
insert into customer values(2,"Ramling","ram@gmail.com","9798713213","");

CREATE TABLE kit(
				cid integer not null,
				pid integer not null,
				qty integer not null
								);
								
insert into kit values(1,1,4);
insert into kit values(1,3,2);
insert into kit values(2,4,1);

						

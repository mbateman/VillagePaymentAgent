CREATE schema kj905;

create table kj905.ht_device (
imei BIGINT not null,
product_number varchar(45) not null,
model_number varchar(45) not null,
allocation varchar(4) default null,
ht_mobile_number varchar(25) default null,
allocation_date date default null,
primary key (imei)
);

create table kj905.ht_fom (
idFom int not null auto_increment,
first_name varchar(45) not null,
last_name varchar(45) not null,
email varchar(45) not null,
password varchar(45) not null,
primary key (idFom)
);

create table kj905.ht_image (
idImage int not null auto_increment,
"DATE" date not null,
photo blob not null,
verification_status varchar(45) default null,
verification_date date default null,
verification_comment varchar(100) default null,
employee_type varchar(25) not null,
primary key (idImage)
);

create table kj905.ht_country (
idCountry int not null auto_increment,
title varchar(45) not null,
description varchar(45) not null,
primary key (idCountry)
);

create table kj905.ht_region (
idRegion int not null auto_increment,
title varchar(45) not null,
description varchar(45) not null,
ht_country_idCountry int not null,
primary key (idRegion),
constraint fk_ht_country_idCountry foreign key (ht_country_idCountry) references ht_country (idCountry)
);

create table kj905.ht_district (
idDistrict int not null auto_increment,
title varchar(45) not null,
description varchar(45) not null,
ht_region_idRegion int not null,
primary key (idDistrict),
constraint fk_ht_region_idRegion foreign key (ht_region_idRegion) references ht_region (idRegion)
);

create table kj905.ht_verifier (
idVerifier int not null auto_increment,
first_name varchar(45) default null,
middle_name varchar(45) default null,
last_name varchar(45) default null,
gender varchar(1) default null,
dob date default null,
email varchar(45) not null,
telephone_number varchar(25) default null,
password varchar(45) not null,
education_type varchar(45) default null,
education_level varchar(45) default null,
status varchar(45) not null,
status_date date not null,
start_date date default null,
ht_vacancy_idVacancy int default null,
verification_status varchar(45) default null,
verification_date date default null,
verification_comment varchar(100) default null,
ht_device_imei BIGINT default null,
ht_image_idImage int default null,
primary key (idVerifier),
constraint fk_ht_device_imei foreign key (ht_device_imei) references ht_device (imei),
constraint fk_ht_image_idImage foreign key (ht_image_idImage) references ht_image (idImage)
);

create table kj905.ht_identity_document (
idIdentityDocument int not null auto_increment,
"NUMBER" varchar(25) not null,
type varchar(25) not null,
issue_date date not null,
expiry_date date not null,
verification_status varchar(45) default null,
verification_date date default null,
verification_comment varchar(100) default null,
employee_type varchar(25) not null,
emp_id int not null,
primary key (idIdentityDocument),
constraint fk_emp_id_identity_document foreign key (emp_id) references ht_verifier (idVerifier)
);

create table kj905.ht_interview (
idInterview int not null auto_increment,
"DATE" date default null,
address varchar(100) default null,
status varchar(45) not null,
comment varchar(100) default null,
employee_type varchar(25) not null,
emp_id int not null,
ht_fom_idFom int not null,
primary key (idInterview),
constraint fk_emp_id_interview foreign key (emp_id) references ht_verifier (idVerifier),
constraint fk_ht_fom_idFom_interview foreign key (ht_fom_idFom) references ht_fom (idFom)
);

create table kj905.ht_address (
idAddress int not null auto_increment,
street varchar(45) default null,
village varchar(45) default null,
postcode varchar(45) not null,
town varchar(45) default null,
city varchar(45) default null,
verification_status varchar(45) default null,
verification_date date default null,
verification_comment varchar(100) default null,
employee_type varchar(25) not null,
emp_id int not null,
ht_country_idCountry int not null,
ht_region_idRegion int not null,
ht_district_idDistrict int not null,
primary key (idAddress),
constraint fk_emp_id_address foreign key (emp_id) references ht_verifier (idVerifier),
constraint fk_ht_country_idCountry_address foreign key (ht_country_idCountry) references ht_country (idCountry),
constraint fk_ht_region_idRegion_address foreign key (ht_region_idRegion) references ht_region (idRegion),
constraint fk_ht_district_idDistrict_address foreign key (ht_district_idDistrict) references ht_district (idDistrict)
);

create table kj905.ht_reference (
idReference int not null auto_increment,
organisation_name varchar(45) not null,
contact_number varchar(25) not null,
address varchar(100) not null,
employee_type varchar(25) not null,
title varchar(10) default null,
full_name varchar(45) default null,
designation varchar(45) default null,
email varchar(45) default null,
verification_status varchar(45) default null,
verification_date date default null,
verification_comment varchar(100) default null,
emp_id int not null,
primary key (idReference),
constraint fk_ht_verifier_idVerifier_reference foreign key (emp_id) references ht_verifier (idVerifier)
);

create table kj905.ht_bank (
idBank int not null auto_increment,
accountNumber varchar(15) not null,
bank_name varchar(45) not null,
address varchar(100) not null,
sort_code varchar(10) default null,
iban varchar(45) default null,
contact_number varchar(25) default null,
verification_status varchar(45) default null,
verification_date date default null,
verification_comment varchar(100) default null,
employee_type varchar(25) not null,
emp_id int not null,
primary key (idBank),
constraint fk_ht_verifier_idVerifier_bank foreign key (emp_id) references ht_verifier (idVerifier)
);

create table kj905.ht_static_data (
idStaticData int not null auto_increment,
type varchar(45) not null,
value varchar(45) not null,
description varchar(45) not null,
primary key (idStaticData)
);

create table users(
user_id int auto_increment primary key,
first_name varchar(100) not null,
last_name varchar(100) not null,
email varchar(255) not null unique,
password_hash varchar(255) not null,
phone_number varchar(15),
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp
);


create table categories(
category_id int auto_increment primary key,
name varchar(100),
description varchar(255)
);



create table products(
product_id int auto_increment primary key,
title varchar(255) not null,
description text,
price decimal(10,2)not null,
stock int not null default 0,
category_id int,
images text,
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp,
foreign key(category_id) references categories(category_id) on delete set null

);





create table carts(
cart_id int auto_increment primary key,
user_id int not null
);


create table cart_items(
cart_item_id int auto_increment primary key,
cart_id int not null,
product_id int not null,
quantity int not null,
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp,
foreign key(cart_id) references carts(cart_id) on delete cascade,
foreign key(product_id) references products(product_id) on delete cascade
);


create table wishlists(
wishlist_id int auto_increment primary key,
name varchar(100),
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp
);


create table wishlists_items(
wishlist_item_id int auto_increment primary key,
wishlist_id int not null,
product_id int not null,
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp,
foreign key(product_id) references products(product_id) on delete cascade,
foreign key(wishlist_id) references wishlists(wishlist_id) on delete cascade
);


create table Addresses(
address_id int auto_increment primary key,
user_id int not null,
address_line1 varchar(255),
address_line2 varchar(255),
city varchar(100),
state varchar(100),
pin_code varchar(20),
country varchar(100),
latitude decimal(9,6),
longitude decimal(9,6),
is_default boolean default false,
address_type enum('Home','Billing','Shipping','Office','Other'),
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp,
foreign key(user_id) references users(user_id) on delete cascade
);


create table orders(
order_id int auto_increment primary key,
user_id int,
total_amount decimal(10,2),
order_status enum('Pending','Processing','Completed','Cancelled'),
billing_address_id int,
shipping_address_id int,
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp,
foreign key(user_id) references users(user_id) on delete cascade,
foreign key(billing_address_id) references Addresses(address_id) on delete cascade,
foreign key(shipping_address_id) references Addresses(address_id) on delete cascade
);

create table order_items(
order_item_id int auto_increment primary key,
order_id int,
product_id int,
price decimal(10,2),
quantity int,
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp,
foreign key(order_id) references orders(order_id) on delete cascade,
foreign key(product_id) references products(product_id) on delete cascade
);


create table coupons(
coupon_id int auto_increment primary key,
coupon_code varchar(25),
offer_type enum('Value','Percentage'),
offer_value int,
valid_till datetime,
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp
);



create table reviews(
review_id int auto_increment primary key,
user_id int,
product_id int,
order_id int,
review_text varchar(255),
images_path varchar(255),
videos_path varchar(255),
is_verified boolean default false,
is_deleted boolean default false, 
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp

);

/* CREATE DATABASE */
CREATE DATABASE orgasm;
USE orgasm;

/* CREATE TABLE */
CREATE TABLE Roles (
	id int identity(1, 1) not null primary key,
	name nvarchar(100) not null unique,
);

CREATE TABLE Users (
	id int identity(1, 1) not null primary key,
	email nvarchar(100) not null unique,
	username nvarchar(100) not null unique,
	password nvarchar(100) not null,
	address nvarchar(100),
	phone nvarchar(100),
	gender nvarchar(100),
	status nvarchar(100),
	profile nvarchar(500),
	avatar nvarchar(200),
	shortDescription nvarchar(25),
	role int not null FOREIGN KEY REFERENCES Roles(id),
);

CREATE TABLE ProductCategories (
	id int identity(1, 1) not null primary key,
	name nvarchar(50) unique
);

CREATE TABLE Products (
	id int identity(1, 1) not null primary key,
	name nvarchar(100) not null,
	quantity decimal(19, 6) not null,
	price decimal(19, 6) not null,
	category int not null FOREIGN KEY REFERENCES ProductCategories(id),
	thumbnail nvarchar(100),
	description nvarchar(500),
	unit nvarchar(20),
	creatorId int not null FOREIGN KEY REFERENCES Users(id)
);

CREATE TABLE OrderStatus (
	id int identity(1, 1) not null primary key,
	status nvarchar(50) not null unique
);

CREATE TABLE Orders (
	id int identity(1, 1) not null primary key,
	buyerId int not null FOREIGN KEY REFERENCES Users(id),
	createdAt Date,
	updatedAt Date,
	status int not null FOREIGN KEY REFERENCES OrderStatus(id),
);

CREATE TABLE OrderProduct (
	id int identity(1, 1) not null primary key,
	orderId int not null FOREIGN KEY REFERENCES Orders(id),
	productId int not null FOREIGN KEY REFERENCES Products(id),
	quantity int not null
);

CREATE TABLE BlogCategories (
	id int identity(1, 1) not null primary key,
	name nvarchar(50) unique
);

CREATE TABLE Blogs (
	id int identity(1, 1) not null primary key,
	image nvarchar(100),
	title nvarchar(100) not null,
	createdAt Date,
	content nvarchar(2047),
	category int not null FOREIGN KEY REFERENCES BlogCategories(id),
	authorId int not null FOREIGN KEY REFERENCES Users(id)
);

CREATE TABLE ProductFeedbacks (
	id int identity(1, 1) not null primary key,
	authorId int not null FOREIGN KEY REFERENCES Users(id),
	productId int not null FOREIGN KEY REFERENCES Products(id),
	createdAt Date,
	rating int not null,
	description nvarchar(500),
);

CREATE TABLE Likes (
	id int identity(1, 1) not null primary key,
	authorId int not null FOREIGN KEY REFERENCES Users(id),
	blogId int not null FOREIGN KEY REFERENCES Blogs(id),
	createdAt Date
)

CREATE TABLE Comments (
	id int identity(1, 1) not null primary key,
	authorId int not null FOREIGN KEY REFERENCES Users(id),
	blogId int not null FOREIGN KEY REFERENCES Blogs(id),
	createdAt Date,
	content nvarchar(300)
)

/* ADD SEED DATA */
INSERT INTO Roles (name) VALUES ('user');
INSERT INTO Roles (name) VALUES ('admin');

INSERT INTO Users (username, email, profile, role, address, phone, gender, status, shortDescription, password, avatar) 
VALUES ('username', 'someone@email.com', 'Im proud to the first user in this software!', 2, '192 Avenue Street', '0933 485 222', 'Female', 'Married', 'Hi there!', '$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc', 'https://i.postimg.cc/BZmgq0fT/pexels-dmitriy-ganin-7538060.jpg');

INSERT INTO ProductCategories (name) VALUES ('Fresh Meat');
INSERT INTO ProductCategories (name) VALUES ('Vegetable');
INSERT INTO ProductCategories (name) VALUES ('Fruits & Nut Gifts');
INSERT INTO ProductCategories (name) VALUES ('Fresh Berries');
INSERT INTO ProductCategories (name) VALUES ('Ocean Foods');
INSERT INTO ProductCategories (name) VALUES ('Butter & Eggs');

INSERT INTO Products (name, price, quantity, category, thumbnail, description, creatorId) VALUES ('Crab Pool Security', 30, 50, 1, 'https://i.postimg.cc/BZmgq0fT/pexels-dmitriy-ganin-7538060.jpg', 'Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus.', 1);
INSERT INTO Products (name, price, quantity, category, thumbnail, description, creatorId) VALUES ('Bananas', 69, 50, 1, 'https://i.postimg.cc/zvGFndKH/pexels-lo-2329440.jpg', 'Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus.', 1);
INSERT INTO Products (name, price, quantity, category, thumbnail, description, creatorId) VALUES ('Mangos', 30, 50, 2, 'https://i.postimg.cc/sDfNcmsc/pexels-marta-dzedyshko-2067437.jpg', 'Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus.', 1);
INSERT INTO Products (name, price, quantity, category, thumbnail, description, creatorId) VALUES ('Cucumbers', 96, 50, 3, 'https://i.postimg.cc/CxqHHnB7/pexels-mikhail-nilov-7815116.jpg', 'Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus.', 1);
INSERT INTO Products (name, price, quantity, category, thumbnail, description, creatorId) VALUES ('Vegetables pack', 125, 50, 4, 'https://i.postimg.cc/sgkmt4db/pexels-rajesh-tp-1633525.jpg', 'Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus.', 1);
INSERT INTO Products (name, price, quantity, category, thumbnail, description, creatorId) VALUES ('Organic meat', 30, 50, 5, 'https://i.postimg.cc/Y2RXhFYm/pexels-rajesh-tp-1633559.jpg', 'Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus.', 1);
INSERT INTO Products (name, price, quantity, category, thumbnail, description, creatorId) VALUES ('Porks', 25, 50, 6, 'https://i.postimg.cc/k5Ws9R7G/pexels-rauf-allahverdiyev-1367243.jpg', 'Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus.', 1);

INSERT INTO OrderStatus (status) VALUES ('cart');
INSERT INTO OrderStatus (status) VALUES ('completed');

INSERT INTO Orders (status, buyerId, createdAt, updatedAt) VALUES (1, 1, '2021-06-09', '2021-06-09');
INSERT INTO Orders (status, buyerId, createdAt, updatedAt) VALUES (2, 1, '2021-06-09', '2021-06-09');
INSERT INTO Orders (status, buyerId, createdAt, updatedAt) VALUES (2, 1, '2021-05-09', '2021-05-09');
INSERT INTO Orders (status, buyerId, createdAt, updatedAt) VALUES (2, 1, '2021-04-09', '2021-04-09');
INSERT INTO Orders (status, buyerId, createdAt, updatedAt) VALUES (2, 1, '2021-04-09', '2021-04-09');
INSERT INTO Orders (status, buyerId, createdAt, updatedAt) VALUES (2, 1, '2021-03-09', '2021-03-09');
INSERT INTO Orders (status, buyerId, createdAt, updatedAt) VALUES (2, 1, '2021-02-01', '2021-02-01');

INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (1, 1, 2);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (1, 2, 1);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (1, 3, 1);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (1, 2, 1);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (1, 5, 3);

INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (2, 1, 2);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (2, 2, 3);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (2, 3, 5);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (2, 2, 7);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (2, 5, 2);

INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (3, 1, 6);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (3, 2, 7);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (3, 3, 8);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (3, 2, 9);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (3, 5, 1);

INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (4, 1, 2);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (4, 2, 3);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (4, 3, 2);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (4, 2, 3);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (4, 5, 4);

INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (5, 1, 4);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (5, 2, 1);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (5, 3, 1);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (5, 2, 2);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (5, 5, 6);

INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (6, 1, 5);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (6, 2, 4);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (6, 3, 7);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (6, 2, 2);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (6, 5, 3);

INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (7, 1, 3);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (7, 2, 4);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (7, 3, 6);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (7, 2, 3);
INSERT INTO OrderProduct (orderId, productId, quantity) VALUES (7, 5, 2);

INSERT INTO BlogCategories (name) VALUES ('Beauty');
INSERT INTO BlogCategories (name) VALUES ('Food');
INSERT INTO BlogCategories (name) VALUES ('Life Style');
INSERT INTO BlogCategories (name) VALUES ('Travel');

INSERT INTO Blogs (title, createdAt, category, authorId, image, content) VALUES ('6 Ways To Prepare Breakfast For 30', '2021-06-09', 1, 1, 'https://i.postimg.cc/BZmgq0fT/pexels-dmitriy-ganin-7538060.jpg', 'Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Sed porttitor lectus nibh. Donec sollicitudin molestie malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Proin eget tortor risus. Donec rutrum congue leo eget malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Donec sollicitudin molestie malesuada. Nulla quis lorem ut libero malesuada feugiat. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem.');
INSERT INTO Blogs (title, createdAt, category, authorId, image, content) VALUES ('Visit the clean farm in the US', '2021-06-09', 2, 1, 'https://i.postimg.cc/sgkmt4db/pexels-rajesh-tp-1633525.jpg', 'Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Sed porttitor lectus nibh. Donec sollicitudin molestie malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Proin eget tortor risus. Donec rutrum congue leo eget malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Donec sollicitudin molestie malesuada. Nulla quis lorem ut libero malesuada feugiat. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem.');
INSERT INTO Blogs (title, createdAt, category, authorId, image, content) VALUES ('Cooking tips make cooking simple', '2021-06-09', 1, 1, 'https://i.postimg.cc/k5Ws9R7G/pexels-rauf-allahverdiyev-1367243.jpg', 'Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Sed porttitor lectus nibh. Donec sollicitudin molestie malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Proin eget tortor risus. Donec rutrum congue leo eget malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Donec sollicitudin molestie malesuada. Nulla quis lorem ut libero malesuada feugiat. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem.');
INSERT INTO Blogs (title, createdAt, category, authorId, image, content) VALUES ('The Moment You Need To Remove Garlic From The Menu', '2021-06-09', 3, 1, 'https://i.postimg.cc/zvGFndKH/pexels-lo-2329440.jpg', 'Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Sed porttitor lectus nibh. Donec sollicitudin molestie malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Proin eget tortor risus. Donec rutrum congue leo eget malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Donec sollicitudin molestie malesuada. Nulla quis lorem ut libero malesuada feugiat. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem.');
INSERT INTO Blogs (title, createdAt, category, authorId, image, content) VALUES ('9 Kinds Of Vegetable Protect The Liver', '2021-06-09', 1, 1, 'https://i.postimg.cc/zvGFndKH/pexels-lo-2329440.jpg', 'Sed porttitor lectus nibh. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Sed porttitor lectus nibh. Donec sollicitudin molestie malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Proin eget tortor risus. Donec rutrum congue leo eget malesuada. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Donec sollicitudin molestie malesuada. Nulla quis lorem ut libero malesuada feugiat. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem.');

INSERT INTO ProductFeedbacks (authorId, productId, createdAt, rating, description) VALUES (1, 1, '2021-06-09', 3, 'Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat');
INSERT INTO ProductFeedbacks (authorId, productId, createdAt, rating, description) VALUES (1, 2, '2021-06-09', 4, 'Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat');
INSERT INTO ProductFeedbacks (authorId, productId, createdAt, rating, description) VALUES (1, 3, '2021-06-09', 5, 'Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat');
INSERT INTO ProductFeedbacks (authorId, productId, createdAt, rating, description) VALUES (1, 4, '2021-06-09', 5, 'Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat');

/* SOURCES */
/* https://postimg.cc/gallery/bS4jZdD */

create database CarRentalCCT;
use CarRentalCCT;

create table CarType(
    CarTypeID int primary key auto_increment,
    CarTypeName varchar(50) not null,
    BaseRent decimal(10, 2) not null
);

create table Customer(
    CustomerID int primary key auto_increment,
    CustName varchar(100) not null,
    DOB date not null,
    Street varchar(150) not null,
    City varchar(100) not null
);

create table Service(
    ServiceID int primary key auto_increment,
    ServiceName varchar(100) not null,
    ServiceCharge decimal(10, 2) not null
);

create table Booking(
    BookingID int primary key auto_increment,
    CustomerID int not null,
    CarTypeID int not null,
    DateOfBooking date not null,
    foreign key (CustomerID) references Customer(CustomerID),
    foreign key (CarTypeID) references CarType(CarTypeID)
);

create table BookingService(
    BookingServiceID int primary key auto_increment,
    BookingID int not null,
    ServiceID int not null,
    foreign key (BookingID) references Booking(BookingID),
    foreign key (ServiceID) references Service(ServiceID)
);

#######################################################################

#                           INSERTION

#######################################################################
insert into CarType (CarTypeID, CarTypeName, BaseRent) values
(1, 'Sedan', 50.00),
(2, 'SUV', 80.00),
(3, 'Hatchback', 40.00),
(4, 'Convertible', 70.00),
(5, 'Truck', 90.00);

insert into Service (ServiceID, ServiceName, ServiceCharge) values
(1, 'GPS', 10.00),
(2, 'Child Seat', 15.00),
(3, 'Extra Driver', 15.00),
(4, 'Wash & Vacuum', 20.00);

insert into Customer (CustomerID, CustName, DOB, Street, City) values
(101, 'Freddie Mercury', '1946-09-05', 'Garden Lodge Lane', 'London'),
(102, 'Jimi Hendrix', '1942-11-27', 'Electric Lady Ave', 'Seattle'),
(103, 'David Bowie', '1947-01-08', 'Stardust Road', 'London'),
(104, 'Janis Joplin', '1943-01-19', 'Pearl Street', 'Port Arthur'),
(105, 'Elvis Presley', '1935-01-08', 'Graceland Blvd', 'Memphis'),
(106, 'Mick Jagger', '1943-07-26', 'Dartford Crossing', 'London'),
(107, 'Robert Plant', '1948-08-20', 'Stairway Lane', 'West Bromwich'),
(108, 'Jim Morrison', '1943-12-08', 'Lizard King Way', 'Melbourne'),
(109, 'Kurt Cobain', '1967-02-20', 'Nevermind Drive', 'Aberdeen'),
(110, 'Debbie Harry', '1945-07-01', 'Blondie Ave', 'Miami'),
(111, 'Ozzy Osbourne', '1948-12-03', 'Sabbath Road', 'Birmingham'),
(112, 'Stevie Nicks', '1948-05-26', 'Rhiannon Court', 'Phoenix'),
(113, 'Eddie Van Halen', '1955-01-26', 'Eruption Way', 'Amsterdam'),
(114, 'Joan Jett', '1958-09-22', 'Blackheart Lane', 'Philadelphia'),
(115, 'Axl Rose', '1962-02-06', 'Jungle Road', 'Lafayette'),
(116, 'Slash', '1965-07-23', 'Top Hat Street', 'London'),
(117, 'Tina Turner', '1939-11-26', 'River Deep Blvd', 'Nutbush'),
(118, 'Bruce Springsteen', '1949-09-23', 'Thunder Road', 'Long Branch'),
(119, 'Bono', '1960-05-10', 'Joshua Tree Ave', 'Dublin'),
(120, 'Steven Tyler', '1948-03-26', 'Dream On Lane', 'Yonkers'),
(121, 'Jon Bon Jovi', '1962-03-02', 'Prayer Road', 'Perth Amboy'),
(122, 'Angus Young', '1955-03-31', 'Highway to Hell', 'Glasgow'),
(123, 'Roger Daltrey', '1944-03-01', 'Pinball Way', 'London'),
(124, 'Courtney Love', '1964-07-09', 'Hole Street', 'San Francisco'),
(125, 'Pat Benatar', '1953-01-10', 'Battlefield Rd', 'Brooklyn'),
(126, 'Alice Cooper', '1948-02-04', 'Nightmare Ave', 'Detroit'),
(127, 'Gene Simmons', '1949-08-25', 'Detroit Rock City', 'Haifa'),
(128, 'Lemmy Kilmister', '1945-12-24', 'Ace of Spades', 'Burslem'),
(129, 'Patti Smith', '1946-12-30', 'Horses Lane', 'Chicago'),
(130, 'Iggy Pop', '1947-04-21', 'Stooge Street', 'Muskegon');

insert into Booking (BookingID, CustomerID, CarTypeID, DateOfBooking) values
(1, 101, 1, '2026-01-15'),
(2, 102, 2, '2026-02-20'),
(3, 103, 3, '2026-03-05'),
(4, 104, 4, '2026-04-12'),
(5, 105, 5, '2026-05-22'),
(6, 106, 1, '2026-06-18'),
(7, 107, 2, '2026-07-04'),
(8, 108, 3, '2026-08-30'),
(9, 109, 4, '2026-09-14'),
(10, 110, 5, '2026-10-01'),
(11, 111, 1, '2026-11-11'),
(12, 112, 2, '2026-12-25'),
(13, 113, 3, '2026-01-28'),
(14, 114, 4, '2026-02-14'),
(15, 115, 5, '2026-03-21'),
(16, 116, 1, '2026-04-09'),
(17, 117, 2, '2026-05-30'),
(18, 118, 3, '2026-06-12'),
(19, 119, 4, '2026-07-19'),
(20, 120, 5, '2026-08-05'),
(21, 121, 1, '2026-09-27'),
(22, 122, 2, '2026-10-31'),
(23, 123, 3, '2026-11-20'),
(24, 124, 4, '2026-12-05'),
(25, 125, 5, '2026-01-08'),
(26, 126, 1, '2026-02-28'),
(27, 127, 2, '2026-03-17'),
(28, 128, 3, '2026-04-25'),
(29, 129, 4, '2026-05-04'),
(30, 130, 5, '2026-06-15');

insert into BookingService (BookingServiceID, BookingID, ServiceID) values
(1, 1, 1),
(2, 1, 4),
(3, 2, 2),
(4, 3, 3),
(5, 4, 1),
(6, 4, 4),
(7, 5, 1),
(8, 6, 3),
(9, 6, 4),
(10, 8, 2),
(11, 9, 1),
(12, 12, 4),
(13, 15, 3),
(14, 18, 1),
(15, 20, 2),
(16, 22, 4),
(17, 25, 1),
(18, 25, 3),
(19, 28, 2),
(20, 30, 4);
CREATE DATABASE IF NOT EXISTS jdbc_restaurant_db;
USE jdbc_restaurant_db;

DROP TABLE IF EXISTS MenuItem;
DROP TABLE IF EXISTS Restaurant;

CREATE TABLE Restaurant (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL
);

CREATE TABLE MenuItem (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    resId INT,
    CONSTRAINT fk_restaurant
        FOREIGN KEY (resId) REFERENCES Restaurant(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO Restaurant VALUES
(1, 'Cafe Java', 'Pune Camp'),
(2, 'Spice Hub', 'Baner'),
(3, 'Urban Tadka', 'Kothrud'),
(4, 'Food Court', 'Hinjewadi'),
(5, 'Green Bowl', 'Wakad'),
(6, 'Dosa House', 'Aundh'),
(7, 'Pizza Point', 'FC Road'),
(8, 'Biryani Adda', 'Hadapsar'),
(9, 'Snack Stop', 'Shivajinagar'),
(10, 'Tandoor Town', 'Pimpri');

INSERT INTO MenuItem VALUES
(101, 'Pasta', 120, 1),
(102, 'Burger', 90, 1),
(103, 'Pizza', 250, 7),
(104, 'Paneer Tikka', 180, 10),
(105, 'Poha', 60, 9),
(106, 'Idli', 50, 6),
(107, 'Coffee', 80, 1),
(108, 'Biryani', 200, 8),
(109, 'Salad', 110, 5),
(110, 'Paratha', 95, 2);
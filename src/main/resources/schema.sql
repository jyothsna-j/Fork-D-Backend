CREATE TABLE restaurants (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cuisine VARCHAR(255) NOT NULL,
    image_type BLOB
);

CREATE TABLE dishes (
    id IDENTITY PRIMARY KEY,
    restaurant_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);



INSERT INTO restaurants (name, cuisine) VALUES ('McDonalds', 'Fast Food, Burgers');
INSERT INTO restaurants (name, cuisine) VALUES ('Tantos', 'Italian');
INSERT INTO restaurants (name, cuisine) VALUES ('Dominos', 'Italian, Fast Food');
INSERT INTO restaurants (name, cuisine) VALUES ('Pizza Hut', 'Italian, Pizza');
INSERT INTO restaurants (name, cuisine) VALUES ('Geetham Veg', 'South Indian, Vegetarian');
INSERT INTO restaurants (name, cuisine) VALUES ('Subway', 'Healthy, Sandwiches');
INSERT INTO restaurants (name, cuisine) VALUES ('KFC', 'Fast Food, Fried Chicken');
INSERT INTO restaurants (name, cuisine) VALUES ('Taco Bell', 'Mexican, Fast Food');
INSERT INTO restaurants (name, cuisine) VALUES ('Starbucks', 'Coffee, Bakery');
INSERT INTO restaurants (name, cuisine) VALUES ('The French Café', 'French, Pastries');
INSERT INTO restaurants (name, cuisine) VALUES ('Sushi World', 'Japanese, Sushi');
INSERT INTO restaurants (name, cuisine) VALUES ('BBQ Nation', 'Barbecue, Grilled');
INSERT INTO restaurants (name, cuisine) VALUES ('Burger King', 'Fast Food, Burgers');
INSERT INTO restaurants (name, cuisine) VALUES ('Panda Express', 'Chinese, Fast Food');
INSERT INTO restaurants (name, cuisine) VALUES ('Olive Garden', 'Italian, Pasta');

-- McDonalds Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (1, 'Big Mac', 'Fast Food', 5.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (1, 'McChicken', 'Fast Food', 4.49);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (1, 'French Fries', 'Fast Food', 2.99);

-- Tantos Dishes (Italian)
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (2, 'Margherita Pizza', 'Italian', 8.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (2, 'Pasta Alfredo', 'Italian', 10.99);

-- Dominos Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (3, 'Pepperoni Pizza', 'Italian', 9.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (3, 'Garlic Bread', 'Italian', 3.99);

-- Pizza Hut Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (4, 'BBQ Chicken Pizza', 'Italian', 11.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (4, 'Stuffed Crust Pizza', 'Italian', 12.99);

-- Geetham Veg Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (5, 'Masala Dosa', 'South Indian', 4.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (5, 'Idli Sambar', 'South Indian', 3.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (5, 'Vada', 'South Indian', 2.99);

-- Subway Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (6, 'Turkey Sub', 'Healthy', 6.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (6, 'Veggie Delight', 'Healthy', 5.49);

-- KFC Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (7, 'Zinger Burger', 'Fast Food', 6.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (7, 'Bucket Chicken', 'Fast Food', 12.99);

-- Taco Bell Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (8, 'Crunchy Taco', 'Mexican', 2.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (8, 'Burrito Supreme', 'Mexican', 5.99);

-- Starbucks Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (9, 'Cappuccino', 'Coffee', 4.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (9, 'Blueberry Muffin', 'Bakery', 3.49);

-- Sushi World Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (11, 'Salmon Sushi', 'Japanese', 8.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (11, 'Tuna Roll', 'Japanese', 9.49);

-- BBQ Nation Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (12, 'Grilled Chicken Skewers', 'Barbecue', 14.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (12, 'BBQ Ribs', 'Barbecue', 18.99);

-- Burger King Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (13, 'Whopper', 'Fast Food', 6.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (13, 'Chicken Nuggets', 'Fast Food', 4.99);

-- Panda Express Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (14, 'Orange Chicken', 'Chinese', 7.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (14, 'Chow Mein', 'Chinese', 6.99);

-- Olive Garden Dishes
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (15, 'Fettuccine Alfredo', 'Italian', 12.99);
INSERT INTO dishes (restaurant_id, name, category, price) VALUES (15, 'Lasagna', 'Italian', 13.99);


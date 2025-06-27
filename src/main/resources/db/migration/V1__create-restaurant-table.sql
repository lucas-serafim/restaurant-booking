
CREATE TABLE restaurant_table(
    id UUID UNIQUE PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    capacity INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL
);
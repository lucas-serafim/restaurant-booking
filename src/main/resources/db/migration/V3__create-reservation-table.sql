
CREATE TABLE reservation(
    id UUID UNIQUE PRIMARY KEY,
    user_id UUID,
    table_id UUID,
    booked_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (table_id) REFERENCES restaurant_table(id) ON DELETE CASCADE
);
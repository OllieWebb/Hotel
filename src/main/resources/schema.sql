-- schema.sql
CREATE TABLE IF NOT EXISTS Hotel (
                                     id BIGINT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    hotelType VARCHAR(255),
    address VARCHAR(255),
    countryCode VARCHAR(10)
    );

CREATE TABLE IF NOT EXISTS Landmark (
                                        id BIGINT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(255),
    countryCode VARCHAR(10)
    );

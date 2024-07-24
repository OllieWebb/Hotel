-- data.sql
INSERT INTO Hotel (id, name, hotelType, address, countryCode) VALUES
                                                                  (1, 'Grand Plaza', 'Luxury', '123 Main St, Metropolis', 'US'),
                                                                  (2, 'Budget Inn', 'Budget', '456 Elm St, Smallville', 'US'),
                                                                  (3, 'Oceanview Resort', 'Resort', '789 Beach Ave, Seaside', 'AU');

INSERT INTO Landmark (id, name, description, category, countryCode) VALUES
                                                                        (1, 'Statue of Liberty', 'A colossal neoclassical sculpture on Liberty Island', 'Monument', 'US'),
                                                                        (2, 'Eiffel Tower', 'A wrought-iron lattice tower on the Champ de Mars in Paris', 'Monument', 'FR'),
                                                                        (3, 'Great Wall of China', 'A series of fortifications made of stone, brick, tamped earth, wood, and other materials', 'Historic Site', 'CN');
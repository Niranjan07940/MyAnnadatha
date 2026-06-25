INSERT INTO categories (category_name) VALUES
                                           ('Vegetables'),
                                           ('Fruits'),
                                           ('Leafy Greens'),
                                           ('Grains & Cereals'),
                                           ('Pulses'),
                                           ('Oil Seeds'),
                                           ('Spices'),
                                           ('Dry Fruits'),
                                           ('Flowers'),
                                           ('Herbs'),
                                           ('Plantation Crops'),
                                           ('Sugar Crops'),
                                           ('Millets'),
                                           ('Animal Products') ON CONFLICT (category_name) DO NOTHING;
INSERT INTO units (unit) VALUES
                             ('Kg'),
                             ('Quintal'),
                             ('Ton'),
                             ('Dozen'),
                             ('Piece'),
                             ('Bundle'),
                             ('Litre'),
                             ('Gram') ON CONFLICT (unit) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Tomato',1,1),
                                                               ('Potato',1,1),
                                                               ('Onion',1,1),
                                                               ('Brinjal',1,1),
                                                               ('Lady Finger',1,1),
                                                               ('Carrot',1,1),
                                                               ('Cabbage',1,1),
                                                               ('Cauliflower',1,1),
                                                               ('Bottle Gourd',1,1),
                                                               ('Bitter Gourd',1,1),
                                                               ('Ridge Gourd',1,1),
                                                               ('Snake Gourd',1,1),
                                                               ('Pumpkin',1,1),
                                                               ('Beans',1,1),
                                                               ('Green Peas',1,1),
                                                               ('Drumstick',1,1),
                                                               ('Capsicum',1,1),
                                                               ('Green Chilli',1,1),
                                                               ('Sweet Corn',1,1),
                                                               ('Beetroot',1,1),
                                                               ('Radish',1,1),
                                                               ('Cucumber',1,1),
                                                               ('Raw Banana',1,1) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Mango',2,1),
                                                               ('Banana',2,4),
                                                               ('Apple',2,1),
                                                               ('Orange',2,1),
                                                               ('Mosambi',2,1),
                                                               ('Papaya',2,1),
                                                               ('Watermelon',2,1),
                                                               ('Muskmelon',2,1),
                                                               ('Pomegranate',2,1),
                                                               ('Guava',2,1),
                                                               ('Pineapple',2,5),
                                                               ('Grapes',2,1),
                                                               ('Sapota',2,1),
                                                               ('Custard Apple',2,1),
                                                               ('Coconut',2,5),
                                                               ('Lemon',2,1) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Spinach',3,6),
                                                               ('Coriander',3,6),
                                                               ('Mint',3,6),
                                                               ('Fenugreek Leaves',3,6),
                                                               ('Amaranthus',3,6),
                                                               ('Curry Leaves',3,6),
                                                               ('Sorrel Leaves',3,6) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Paddy',4,2),
                                                               ('Rice',4,1),
                                                               ('Wheat',4,2),
                                                               ('Maize',4,2),
                                                               ('Barley',4,2) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Red Gram',5,2),
                                                               ('Green Gram',5,2),
                                                               ('Black Gram',5,2),
                                                               ('Bengal Gram',5,2),
                                                               ('Horse Gram',5,2),
                                                               ('Cowpea',5,2),
                                                               ('Field Beans',5,2) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Groundnut',6,2),
                                                               ('Sunflower',6,2),
                                                               ('Sesame',6,2),
                                                               ('Mustard',6,2),
                                                               ('Castor',6,2),
                                                               ('Soybean',6,2) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Turmeric',7,1),
                                                               ('Red Chilli',7,1),
                                                               ('Black Pepper',7,1),
                                                               ('Coriander Seeds',7,1),
                                                               ('Cumin',7,1),
                                                               ('Cardamom',7,1),
                                                               ('Cloves',7,1),
                                                               ('Fennel',7,1),
                                                               ('Fenugreek Seeds',7,1) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Cashew',8,1),
                                                               ('Almond',8,1),
                                                               ('Walnut',8,1),
                                                               ('Raisins',8,1),
                                                               ('Pistachio',8,1) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Marigold',9,1),
                                                               ('Rose',9,6),
                                                               ('Jasmine',9,6),
                                                               ('Lotus',9,5),
                                                               ('Chrysanthemum',9,6),
                                                               ('Lily',9,5) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Tulsi',10,6),
                                                               ('Aloe Vera',10,5),
                                                               ('Lemongrass',10,6),
                                                               ('Ashwagandha',10,1)ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Coffee Beans',11,2),
                                                               ('Tea Leaves',11,2),
                                                               ('Cocoa',11,2),
                                                               ('Rubber Latex',11,7) ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
    ('Sugarcane',12,2)ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Jowar',13,2),
                                                               ('Bajra',13,2),
                                                               ('Ragi',13,2),
                                                               ('Foxtail Millet',13,2),
                                                               ('Little Millet',13,2),
                                                               ('Kodo Millet',13,2),
                                                               ('Barnyard Millet',13,2)ON CONFLICT (item_name) DO NOTHING;
INSERT INTO sub_category (item_name,categories_id,unit_id) VALUES
                                                               ('Milk',14,7),
                                                               ('Eggs',14,4),
                                                               ('Honey',14,7)ON CONFLICT (item_name) DO NOTHING;
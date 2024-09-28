use entranceshop;
INSERT INTO categories(category_name)
VALUES
	('Home & Decor'),
	('Clothing'),
	('Accessories'),
	('Outdoor');

INSERT INTO styles(style_name, category_id)
VALUES
	('Modern', 2),
	('Streetwear', 2),
	('Colorfull', 2),
	('Vintage', 2),
	('Rustic', 1),
	('Traditional', 1),
	('Farmhouse', 1),
	('Luxury', 3),
	('Handmade', 3),
	('Sporty', 3),
	('Japanese Zen Garden', 4),
	('Tropical', 4),
	('Cottage Garden', 4);

INSERT INTO colors(color_name)
VALUES
	('Red'),
    ('Blue'),
    ('Green'),
    ('Yellow'),
    ('Orange'),
    ('Purple'),
    ('Pink'),
    ('Black'),
    ('White'),
    ('Gray'),
    ('Brown');

INSERT INTO products(product_code, product_name, description, price, size, quantity, rate, thumbnail, del_flag, style_id)
VALUES
    ('P001', 'T-Shirt', 'Basic cotton T-shirt', 19.99, 'M', 100, 4.5, 'tshirt_thumbnail.jpg', '1', 1),
    ('P002', 'Hoodie', 'Cozy streetwear hoodie', 39.99, 'L', 50, 4.7, 'hoodie_thumbnail.jpg', '1', 2),
    ('P003', 'Floral Dress', 'Vintage floral dress', 49.99, 'S', 30, 4.3, 'floral_dress_thumbnail.jpg', '1', 3),
    ('P004', 'Leather Jacket', 'High-quality leather jacket', 149.99, 'M', 20, 4.8, 'leather_jacket_thumbnail.jpg', '1', 2),
    ('P005', 'Wooden Chair', 'Rustic wooden chair for home decor', 99.99, 'Standard', 15, 4.6, 'wooden_chair_thumbnail.jpg', '1', 5),
    ('P006', 'Throw Pillow', 'Soft and stylish throw pillow', 24.99, '16x16', 75, 4.4, 'throw_pillow_thumbnail.jpg', '1', 5),
    ('P007', 'Handmade Earrings', 'Luxury handmade earrings', 79.99, 'One size', 25, 4.9, 'handmade_earrings_thumbnail.jpg', '1', 9),
    ('P008', 'Bracelet', 'Elegant luxury bracelet', 129.99, 'One size', 40, 4.8, 'bracelet_thumbnail.jpg', '1', 8),
    ('P009', 'Running Shoes', 'Sporty shoes for active lifestyle', 89.99, '10', 60, 4.7, 'running_shoes_thumbnail.jpg', '1', 10),
    ('P010', 'Yoga Mat', 'Comfortable yoga mat', 29.99, 'Standard', 100, 4.6, 'yoga_mat_thumbnail.jpg', '1', 11),
    ('P011', 'Outdoor Bench', 'Traditional outdoor wooden bench', 199.99, 'Large', 10, 4.5, 'outdoor_bench_thumbnail.jpg', '1', 6),
    ('P012', 'Hammock', 'Relaxing outdoor hammock', 59.99, 'Large', 25, 4.7, 'hammock_thumbnail.jpg', '1', 12),
    ('P013', 'Garden Tools Set', 'Tropical style garden tools set', 49.99, 'Standard', 40, 4.5, 'garden_tools_set_thumbnail.jpg', '1', 13),
    ('P014', 'Ceramic Vase', 'Vintage ceramic flower vase', 34.99, 'Medium', 35, 4.3, 'ceramic_vase_thumbnail.jpg', '1', 3),
    ('P015', 'Sunglasses', 'Trendy sunglasses', 79.99, 'One size', 50, 4.8, 'sunglasses_thumbnail.jpg', '1', 8),
    ('P016', 'Farmhouse Table', 'Rustic farmhouse dining table', 499.99, 'Large', 5, 4.9, 'farmhouse_table_thumbnail.jpg', '1', 7),
    ('P017', 'Modern Sofa', 'Stylish modern sofa for living room', 899.99, 'Standard', 3, 4.7, 'modern_sofa_thumbnail.jpg', '1', 5),
    ('P018', 'Leather Bag', 'Luxury leather handbag', 249.99, 'Medium', 12, 4.8, 'leather_bag_thumbnail.jpg', '1', 8),
    ('P019', 'Decorative Lamp', 'Rustic decorative lamp for home', 79.99, 'Standard', 20, 4.4, 'decorative_lamp_thumbnail.jpg', '1', 5),
    ('P020', 'Colorful Scarf', 'Colorful scarf for all seasons', 29.99, 'One size', 60, 4.6, 'colorful_scarf_thumbnail.jpg', '1', 3);

INSERT INTO product_color(product_id, color_id)
VALUES
	(1, 1), -- T-Shirt, Red
    (1, 2), -- T-Shirt, Blue
    (2, 3), -- Hoodie, Green
    (2, 4), -- Hoodie, Yellow
    (3, 5), -- Floral Dress, Orange
    (3, 6), -- Floral Dress, Purple
    (4, 7), -- Leather Jacket, Pink
    (4, 8), -- Leather Jacket, Black
    (5, 9), -- Wooden Chair, White
    (5, 10), -- Wooden Chair, Gray
    (6, 11), -- Throw Pillow, Brown
    (6, 1), -- Throw Pillow, Red
    (7, 2), -- Handmade Earrings, Blue
    (7, 3), -- Handmade Earrings, Green
    (8, 4), -- Bracelet, Yellow
    (8, 5), -- Bracelet, Orange
    (9, 6), -- Running Shoes, Purple
    (9, 7), -- Running Shoes, Pink
    (10, 8), -- Yoga Mat, Black
    (10, 9), -- Yoga Mat, White
    (11, 10), -- Outdoor Bench, Gray
    (11, 11), -- Outdoor Bench, Brown
    (12, 1), -- Hammock, Red
    (12, 2), -- Hammock, Blue
    (13, 3), -- Garden Tools Set, Green
    (13, 4), -- Garden Tools Set, Yellow
    (14, 5), -- Ceramic Vase, Orange
    (14, 6), -- Ceramic Vase, Purple
    (15, 7), -- Sunglasses, Pink
    (15, 8), -- Sunglasses, Black
    (16, 9), -- Farmhouse Table, White
    (16, 10), -- Farmhouse Table, Gray
    (17, 11), -- Modern Sofa, Brown
    (17, 1), -- Modern Sofa, Red
    (18, 2), -- Leather Bag, Blue
    (18, 3), -- Leather Bag, Green
    (19, 4), -- Decorative Lamp, Yellow
    (19, 5), -- Decorative Lamp, Orange
    (20, 6), -- Colorful Scarf, Purple
    (20, 7); -- Colorful Scarf, Pink

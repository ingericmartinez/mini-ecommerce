-- =================================================================
-- DATA SEED FOR H2
-- Insert order respects FK dependencies
-- =================================================================

-- Categories
INSERT INTO Categories (CategoryID, CategoryName) VALUES
  (1, 'Electrónicos'),
  (2, 'Ropa y Accesorios'),
  (3, 'Hogar y Cocina'),
  (4, 'Libros');

-- Suppliers
INSERT INTO Suppliers (SupplierID, SupplierName, ContactEmail) VALUES
  (101, 'TechPro S.A. de C.V.', 'ventas@techpro.com'),
  (102, 'ModaGlobal Distribuidores', 'contacto@modaglobal.com'),
  (103, 'HogarEsencial Proveedores', 'info@hogaresencial.com');

-- Customers
INSERT INTO Customers (CustomerID, Name, Email) VALUES
  (1, 'Ana García', 'ana.garcia@email.com'),
  (2, 'Carlos Martinez', 'carlos.m@email.com'),
  (3, 'Sofía Rodríguez', 'sofia.r@email.com');

-- Products
INSERT INTO Products (ProductID, Name, Price, CategoryID) VALUES
  (1001, 'Laptop UltraSlim X1', 18500.00, 1),
  (1002, 'Smartphone ProMax', 12300.00, 1),
  (1003, 'Cafetera Automática', 2199.99, 3),
  (1004, 'Camiseta Deportiva', 299.50, 2),
  (1005, 'Libro: Patrones de Diseño', 750.00, 4);

-- Orders
INSERT INTO Orders (OrderID, CustomerID, OrderDate) VALUES
  (5001, 1, DATE '2024-09-01'),
  (5002, 2, DATE '2024-09-05');

-- OrderItems
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES
  (90001, 5001, 1001, 1),
  (90002, 5001, 1004, 2),
  (90003, 5002, 1003, 1),
  (90004, 5002, 1005, 1);

-- ProductSuppliers
INSERT INTO ProductSuppliers (ProductID, SupplierID, SupplyPrice) VALUES
  (1001, 101, 15000.00),
  (1002, 101, 9800.00),
  (1004, 102, 180.00),
  (1003, 103, 1700.00);
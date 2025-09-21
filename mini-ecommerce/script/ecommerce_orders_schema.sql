-- =================================================================
-- FASE 1: CREACIÓN DEL ESQUEMA DE LA BASE DE DATOS
-- Las tablas se crean en orden de dependencia.
-- =================================================================

-- 1.1. Tablas base (sin dependencias externas)
CREATE TABLE Categories (
                            CategoryID INT PRIMARY KEY,
                            CategoryName VARCHAR(100)
);

CREATE TABLE Suppliers (
                           SupplierID INT PRIMARY KEY,
                           SupplierName VARCHAR(100),
                           ContactEmail VARCHAR(100)
);

CREATE TABLE Customers (
                           CustomerID INT PRIMARY KEY,
                           Name VARCHAR(100),
                           Email VARCHAR(100)
);

-- 1.2. Tablas que dependen de las tablas base
CREATE TABLE Products (
                          ProductID INT PRIMARY KEY,
                          Name VARCHAR(100),
                          Price DECIMAL(10,2),
                          CategoryID INT,
                          FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE Orders (
                        OrderID INT PRIMARY KEY,
                        CustomerID INT,
                        OrderDate DATE,
                        FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- 1.3. Tablas de unión y detalle (completan las relaciones)
CREATE TABLE ProductSuppliers (
                                  ProductID INT,
                                  SupplierID INT,
                                  SupplyPrice DECIMAL(10,2),
                                  PRIMARY KEY (ProductID, SupplierID),
                                  FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
                                  FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID)
);

CREATE TABLE OrderItems (
                            OrderItemID INT PRIMARY KEY,
                            OrderID INT,
                            ProductID INT,
                            Quantity INT,
                            FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
                            FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);


-- =================================================================
-- FASE 2: POBLACIÓN DE DATOS (INSERTS)
-- Los datos se insertan respetando el orden de creación.
-- =================================================================

-- 2.1. Llenado de tablas base
INSERT INTO Categories (CategoryID, CategoryName) VALUES
                                                      (1, 'Electrónicos'),
                                                      (2, 'Ropa y Accesorios'),
                                                      (3, 'Hogar y Cocina'),
                                                      (4, 'Libros');

INSERT INTO Suppliers (SupplierID, SupplierName, ContactEmail) VALUES
                                                                   (101, 'TechPro S.A. de C.V.', 'ventas@techpro.com'),
                                                                   (102, 'ModaGlobal Distribuidores', 'contacto@modaglobal.com'),
                                                                   (103, 'HogarEsencial Proveedores', 'info@hogaresencial.com');

INSERT INTO Customers (CustomerID, Name, Email) VALUES
                                                    (1, 'Ana García', 'ana.garcia@email.com'),
                                                    (2, 'Carlos Martinez', 'carlos.m@email.com'),
                                                    (3, 'Sofía Rodríguez', 'sofia.r@email.com');

-- 2.2. Llenado de tablas dependientes
INSERT INTO Products (ProductID, Name, Price, CategoryID) VALUES
                                                              (1001, 'Laptop UltraSlim X1', 18500.00, 1),
                                                              (1002, 'Smartphone ProMax', 123
USE [YellowMoonShop]
GO
/****** Object:  Table [dbo].[Cakes]    Script Date: 9/20/2021 11:50:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cakes](
	[StatusId] [int] NOT NULL,
	[CakesId] [int] NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[Description] [nvarchar](1000) NOT NULL,
	[Price] [float] NOT NULL,
	[CreateDate] [date] NOT NULL,
	[ExpirationDate] [date] NOT NULL,
	[Image] [varchar](1000) NOT NULL,
	[Quantity] [int] NOT NULL,
	[CategoryId] [int] NOT NULL,
	[UpdateDate] [date] NULL,
	[UpdateBy] [nvarchar](50) NULL,
 CONSTRAINT [PK__Cakes__CD18C23D2A533A5B] PRIMARY KEY CLUSTERED 
(
	[CakesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 9/20/2021 11:50:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryId] [int] NOT NULL,
	[CategoryName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 9/20/2021 11:50:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderDetailId] [int] IDENTITY(1,1) NOT NULL,
	[Amount] [int] NOT NULL,
	[OrderID] [int] NOT NULL,
	[CakesId] [int] NOT NULL,
 CONSTRAINT [PK__OrderDet__D3B9D36CB31CDF5F] PRIMARY KEY CLUSTERED 
(
	[OrderDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 9/20/2021 11:50:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[Total] [float] NOT NULL,
	[Status] [int] NOT NULL,
	[OrderDate] [date] NOT NULL,
	[PaymentMethod] [varchar](255) NOT NULL,
	[PaymentStatus] [int] NOT NULL,
	[ShippingAddress] [varchar](255) NOT NULL,
	[UserName] [varchar](255) NOT NULL,
	[PhoneNumber] [varchar](255) NULL,
	[UserID] [varchar](255) NULL,
 CONSTRAINT [PK__Orders__C3905BAFA655E1DC] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 9/20/2021 11:50:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[RoleId] [int] NOT NULL,
	[RoleName] [varchar](50) NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 9/20/2021 11:50:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[StatusId] [int] NOT NULL,
	[StatusName] [varchar](50) NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[StatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 9/20/2021 11:50:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [varchar](255) NOT NULL,
	[Password] [varchar](255) NOT NULL,
	[Name] [varchar](255) NOT NULL,
	[StatusId] [int] NOT NULL,
	[RoleId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 1, N'Dau xanh', N'Tết trung thu không thể thiếu bánh trung thu.', 10, CAST(N'2021-09-09' AS Date), CAST(N'2021-09-20' AS Date), N'https://images.unsplash.com/photo-1512101638365-72010d90a610?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80', 20, 1, CAST(N'2021-09-19' AS Date), N'HIHI')
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 2, N'Dau Do', N'Tết trung thu không thể thiếu bánh trung thu.', 12, CAST(N'2021-09-15' AS Date), CAST(N'2021-09-23' AS Date), N'https://unsplash.com/photos/bwxiossMo34', 30, 2, CAST(N'2021-09-19' AS Date), N'HIHI')
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 3, N'Dau Den', N'Tết trung thu không thể thiếu bánh trung thu.', 14, CAST(N'2021-09-17' AS Date), CAST(N'2021-09-26' AS Date), N'https://unsplash.com/photos/dIPyuerpI38', 25, 1, CAST(N'2021-09-19' AS Date), N'HIHI')
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 4, N'pham', N'Tết trung thu không thể thiếu bánh trung thu.', 25, CAST(N'2021-09-18' AS Date), CAST(N'2021-09-30' AS Date), N'https://unsplash.com/photos/FXDtkfA_bUI', 35, 2, CAST(N'2021-09-19' AS Date), N'HIHI')
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 5, N'Truyền thống', N'Tết trung thu không thể thiếu bánh trung thu.', 50, CAST(N'2021-09-20' AS Date), CAST(N'2021-09-30' AS Date), N'https://www.istockphoto.com/photo/mooncake-traditional-chinese-pastry-served-for-mid-autumn-day-festival-gm1277512454-376696284?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmooncake&utm_term=mooncake%3A%3Asearch-aggressive-affiliates-v1%3Aa', 31, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 6, N'Bánh trung thu Kinh Đô', N'Tết trung thu không thể thiếu bánh trung thu.', 70, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), N'https://www.istockphoto.com/photo/mooncake-moon-cake-for-mid-autumn-festival-concept-of-traditional-festive-food-on-gm1257724420-368687654?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmooncake&utm_term=mooncake%3A%3Asearch-aggressive-affiliates-v1%3Aa', 50, 2, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 7, N'Bánh trung thu Givral', N'Tết trung thu không thể thiếu bánh trung thu.', 60, CAST(N'2021-09-21' AS Date), CAST(N'2021-10-01' AS Date), N'https://www.istockphoto.com/photo/mooncake-traditional-chinese-pastry-served-with-tea-for-mid-autumn-day-festival-gm1277512090-376695852?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmooncake&utm_term=mooncake%3A%3Asearch-aggressive-affiliates-v1%3Aa', 25, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 8, N'Bánh trung thu Brodard', N'Tết trung thu không thể thiếu bánh trung thu.', 80, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), N'https://www.istockphoto.com/photo/colorful-beautiful-moon-cake-mung-bean-cake-champion-scholar-pastry-cake-for-mid-gm1273446738-375316032?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmooncake&utm_term=mooncake%3A%3Asearch-aggressive-affiliates-v1%3Aa', 28, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 9, N'Bánh trung thu Đại Phát', N'Tết trung thu không thể thiếu bánh trung thu.', 40, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-30' AS Date), N'https://www.istockphoto.com/photo/an-asian-chinese-beautiful-woman-making-chinese-traditional-mid-autumn-snow-skin-gm1266521211-371304748?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmooncake&utm_term=mooncake%3A%3Asearch-aggressive-affiliates-v1%3Aa', 19, 2, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 10, N'Bánh trung thu Lafeve', N'Tết trung thu không thể thiếu bánh trung thu.', 70, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-30' AS Date), N'https://www.istockphoto.com/photo/chinese-traditional-mid-autumn-snow-skin-mooncake-gm1266521248-371304763?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmooncake&utm_term=mooncake%3A%3Asearch-aggressive-affiliates-v1%3Aa', 19, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 11, N'Bánh trung thu Như Lan', N'Tết trung thu không thể thiếu bánh trung thu.', 80, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), N'https://unsplash.com/photos/PXfBbqyT6x8', 60, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 12, N'Bánh trung thu Bibica', N'Tết trung thu không thể thiếu bánh trung thu.', 60, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), N'https://unsplash.com/photos/dOAy2NF5ixE', 50, 2, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 13, N'Bánh nướng nhân thập cẩm truyền thống', N'Tết trung thu không thể thiếu bánh trung thu.', 50, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-23' AS Date), N'https://unsplash.com/photos/_EyQ2wmok2o', 50, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 14, N'Bánh nướng nhân gà quay.', N'Tết trung thu không thể thiếu bánh trung thu.', 80, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), N'https://unsplash.com/photos/FQ5d0yAsoKQ', 50, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 15, N'Bánh nướng nhân đậu xanh.', N'Tết trung thu không thể thiếu bánh trung thu.', 90, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-30' AS Date), N'https://unsplash.com/photos/caPNy92Ai3w', 60, 2, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 16, N'Bánh nướng nhân hạt sen.', N'Tết trung thu không thể thiếu bánh trung thu.', 60, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), N'https://unsplash.com/photos/OuUGl-7JLg0', 30, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 17, N'Bánh nướng nhân trà xanh', N'Tết trung thu không thể thiếu bánh trung thu.', 60, CAST(N'2021-09-22' AS Date), CAST(N'2021-10-01' AS Date), N'https://unsplash.com/photos/PXfBbqyT6x8', 60, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 18, N'Bánh nướng nhân tiramisu.', N'Tết trung thu không thể thiếu bánh trung thu.', 30, CAST(N'2021-09-20' AS Date), CAST(N'2021-09-29' AS Date), N'https://unsplash.com/photos/p9petNJR41k', 60, 2, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 19, N'Bánh nướng nhân trái cây khô', N'Tết trung thu không thể thiếu bánh trung thu.', 65, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), N'https://unsplash.com/photos/Wbw3TlZwkLI', 65, 1, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 20, N'Bánh nướng nhân mè đen.', N'Tết trung thu không thể thiếu bánh trung thu.', 50, CAST(N'2021-09-14' AS Date), CAST(N'2021-09-22' AS Date), N'https://unsplash.com/photos/bwxiossMo34', 60, 2, NULL, NULL)
INSERT [dbo].[Cakes] ([StatusId], [CakesId], [Name], [Description], [Price], [CreateDate], [ExpirationDate], [Image], [Quantity], [CategoryId], [UpdateDate], [UpdateBy]) VALUES (1, 21, N'Thương hiệu bánh trung thu cao cấp Kinh Đô', N'Tết trung thu không thể thiếu bánh trung thu.', 60, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), N'https://unsplash.com/photos/Rpng5uNmMkw', 60, 1, NULL, NULL)
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (1, N'Round')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (2, N'Square')
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([OrderDetailId], [Amount], [OrderID], [CakesId]) VALUES (4, 1, 5, 1)
INSERT [dbo].[OrderDetail] ([OrderDetailId], [Amount], [OrderID], [CakesId]) VALUES (5, 4, 5, 4)
INSERT [dbo].[OrderDetail] ([OrderDetailId], [Amount], [OrderID], [CakesId]) VALUES (6, 5, 7, 2)
INSERT [dbo].[OrderDetail] ([OrderDetailId], [Amount], [OrderID], [CakesId]) VALUES (7, 3, 8, 1)
INSERT [dbo].[OrderDetail] ([OrderDetailId], [Amount], [OrderID], [CakesId]) VALUES (8, 2, 8, 2)
INSERT [dbo].[OrderDetail] ([OrderDetailId], [Amount], [OrderID], [CakesId]) VALUES (9, 1, 9, 1)
INSERT [dbo].[OrderDetail] ([OrderDetailId], [Amount], [OrderID], [CakesId]) VALUES (10, 1, 10, 1)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderID], [Total], [Status], [OrderDate], [PaymentMethod], [PaymentStatus], [ShippingAddress], [UserName], [PhoneNumber], [UserID]) VALUES (5, 110, 1, CAST(N'2021-09-19' AS Date), N'Cash payment upon delivery', 1, N'', N'Chu Nguyen Linh', N'', N'sa')
INSERT [dbo].[Orders] ([OrderID], [Total], [Status], [OrderDate], [PaymentMethod], [PaymentStatus], [ShippingAddress], [UserName], [PhoneNumber], [UserID]) VALUES (7, 60, 1, CAST(N'2021-09-19' AS Date), N'Cash payment upon delivery', 1, N'quan 9', N'Linh', N'03543456546', N'khach')
INSERT [dbo].[Orders] ([OrderID], [Total], [Status], [OrderDate], [PaymentMethod], [PaymentStatus], [ShippingAddress], [UserName], [PhoneNumber], [UserID]) VALUES (8, 54, 1, CAST(N'2021-09-20' AS Date), N'Cash payment upon delivery', 1, N'', N'Chu Nguyen Linh', N'', N'sa')
INSERT [dbo].[Orders] ([OrderID], [Total], [Status], [OrderDate], [PaymentMethod], [PaymentStatus], [ShippingAddress], [UserName], [PhoneNumber], [UserID]) VALUES (9, 10, 1, CAST(N'2021-09-20' AS Date), N'Cash payment upon delivery', 1, N'', N'Chu Nguyen Linh', N'', N'sa')
INSERT [dbo].[Orders] ([OrderID], [Total], [Status], [OrderDate], [PaymentMethod], [PaymentStatus], [ShippingAddress], [UserName], [PhoneNumber], [UserID]) VALUES (10, 10, 1, CAST(N'2021-09-20' AS Date), N'Cash payment upon delivery', 1, N'', N'Chu Nguyen Linh', N'', N'sa')
SET IDENTITY_INSERT [dbo].[Orders] OFF
INSERT [dbo].[Roles] ([RoleId], [RoleName]) VALUES (1, N'User')
INSERT [dbo].[Roles] ([RoleId], [RoleName]) VALUES (2, N'Admin')
INSERT [dbo].[Status] ([StatusId], [StatusName]) VALUES (1, N'Active')
INSERT [dbo].[Status] ([StatusId], [StatusName]) VALUES (2, N'Deactive')
INSERT [dbo].[Users] ([UserID], [Password], [Name], [StatusId], [RoleId]) VALUES (N'khach', N'khach', N'khach', 1, 1)
INSERT [dbo].[Users] ([UserID], [Password], [Name], [StatusId], [RoleId]) VALUES (N'linhcnse130515@fpt.edu.vn', N'1', N'Chu Nguyen linh', 1, 1)
INSERT [dbo].[Users] ([UserID], [Password], [Name], [StatusId], [RoleId]) VALUES (N'sa', N'1', N'Chu Nguyen Linh', 1, 1)
INSERT [dbo].[Users] ([UserID], [Password], [Name], [StatusId], [RoleId]) VALUES (N'sas', N'q', N'HIHI', 1, 2)
ALTER TABLE [dbo].[Cakes]  WITH CHECK ADD  CONSTRAINT [FK__Cakes__CategoryI__2C3393D0] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([CategoryId])
GO
ALTER TABLE [dbo].[Cakes] CHECK CONSTRAINT [FK__Cakes__CategoryI__2C3393D0]
GO
ALTER TABLE [dbo].[Cakes]  WITH CHECK ADD  CONSTRAINT [FK_Cakes_Status] FOREIGN KEY([StatusId])
REFERENCES [dbo].[Status] ([StatusId])
GO
ALTER TABLE [dbo].[Cakes] CHECK CONSTRAINT [FK_Cakes_Status]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__OrderDeta__Cakes__300424B4] FOREIGN KEY([CakesId])
REFERENCES [dbo].[Cakes] ([CakesId])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK__OrderDeta__Cakes__300424B4]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__OrderDeta__Order__2F10007B] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK__OrderDeta__Order__2F10007B]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__UserID__276EDEB3] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__UserID__276EDEB3]
GO

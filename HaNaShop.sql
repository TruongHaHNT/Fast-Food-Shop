USE [master]
GO
/****** Object:  Database [HaNaShop]    Script Date: 3/1/2020 12:15:05 PM ******/
CREATE DATABASE [HaNaShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HaNaShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS2014\MSSQL\DATA\HaNaShop.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HaNaShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS2014\MSSQL\DATA\HaNaShop_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HaNaShop] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HaNaShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HaNaShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HaNaShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HaNaShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HaNaShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HaNaShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HaNaShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HaNaShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HaNaShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HaNaShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HaNaShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HaNaShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HaNaShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HaNaShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HaNaShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HaNaShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HaNaShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HaNaShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HaNaShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HaNaShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HaNaShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HaNaShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HaNaShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HaNaShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HaNaShop] SET  MULTI_USER 
GO
ALTER DATABASE [HaNaShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HaNaShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HaNaShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HaNaShop] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HaNaShop] SET DELAYED_DURABILITY = DISABLED 
GO
USE [HaNaShop]
GO
/****** Object:  Table [dbo].[cart]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[cart](
	[mail] [varchar](254) NOT NULL,
	[foodID] [varchar](20) NOT NULL,
	[amount] [int] NULL,
 CONSTRAINT [PK_cart] PRIMARY KEY CLUSTERED 
(
	[mail] ASC,
	[foodID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HaNaUser]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HaNaUser](
	[mail] [varchar](254) NOT NULL,
	[name] [varchar](30) NULL,
	[password] [varchar](70) NULL,
	[roleID] [varchar](3) NULL,
	[statusID] [varchar](3) NULL,
 CONSTRAINT [PK_HaNaUser] PRIMARY KEY CLUSTERED 
(
	[mail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HaNaUserRole]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HaNaUserRole](
	[roleID] [varchar](3) NOT NULL,
	[role] [varchar](8) NULL,
 CONSTRAINT [PK_HaNaUserRole] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HaNaUserStatus]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HaNaUserStatus](
	[statusID] [varchar](3) NOT NULL,
	[status] [varchar](6) NULL,
 CONSTRAINT [PK_HaNaUserStatus] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[imageUI]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[imageUI](
	[name] [varchar](20) NOT NULL,
	[image] [varchar](50) NULL,
 CONSTRAINT [PK_imageUI] PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ItemCategory]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ItemCategory](
	[categoryID] [int] IDENTITY(1,1) NOT NULL,
	[category] [varchar](20) NULL,
 CONSTRAINT [PK_ItemCategory] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ItemRecord]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ItemRecord](
	[ItemID] [varchar](20) NOT NULL,
	[PurchaseRecord] [int] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_ItemRecord] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ItemStatus]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ItemStatus](
	[statusID] [varchar](3) NOT NULL,
	[status] [varchar](8) NULL,
 CONSTRAINT [PK_ItemStatus] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ItemStorage]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ItemStorage](
	[ID] [varchar](20) NOT NULL,
	[foodName] [varchar](50) NULL,
	[image] [text] NULL,
	[description] [text] NULL,
	[price] [float] NULL,
	[Date] [datetime] NULL,
	[categoryID] [int] NULL,
	[quantity] [int] NULL,
	[statusID] [varchar](3) NULL,
	[sold] [int] NULL,
 CONSTRAINT [PK_ItemStorage] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[purchaseHistory]    Script Date: 3/1/2020 12:15:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[purchaseHistory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[mail] [varchar](254) NOT NULL,
	[foodID] [varchar](20) NOT NULL,
	[image] [text] NULL,
	[name] [varchar](50) NULL,
	[amount] [int] NULL,
	[price] [float] NULL,
	[date] [datetime] NULL,
 CONSTRAINT [PK_purchaseHistory_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[HaNaUser] ([mail], [name], [password], [roleID], [statusID]) VALUES (N'admin@gmail.com', N'Admin', N'bef57ec7f53a6d40beb640a780a639c83bc29ac8a9816f1fc6c5c6dcd93c4721', N'm01', N's01')
INSERT [dbo].[HaNaUser] ([mail], [name], [password], [roleID], [statusID]) VALUES (N'usera@gmail.com', N'User A', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'c01', N's01')
INSERT [dbo].[HaNaUser] ([mail], [name], [password], [roleID], [statusID]) VALUES (N'userb@gmail.com', N'User B', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'c01', N's01')
INSERT [dbo].[HaNaUser] ([mail], [name], [password], [roleID], [statusID]) VALUES (N'userc@gmail.com', N'User C', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'c01', N's01')
INSERT [dbo].[HaNaUser] ([mail], [name], [password], [roleID], [statusID]) VALUES (N'userd@gmail.com', N'User D', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'c01', N's01')
INSERT [dbo].[HaNaUser] ([mail], [name], [password], [roleID], [statusID]) VALUES (N'usere@gmail.com', N'User E', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'c01', N's01')
INSERT [dbo].[HaNaUserRole] ([roleID], [role]) VALUES (N'c01', N'Customer')
INSERT [dbo].[HaNaUserRole] ([roleID], [role]) VALUES (N'm01', N'Manager')
INSERT [dbo].[HaNaUserStatus] ([statusID], [status]) VALUES (N's01', N'Active')
INSERT [dbo].[HaNaUserStatus] ([statusID], [status]) VALUES (N's02', N'New')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'banner', N'banner.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'cart', N'cartIcon.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'facebook', N'facebook.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'gmail', N'gmail.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'google', N'google.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'logo', N'logo.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'managercreate', N'menu.jpg')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'managersearch', N'managersearch.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'managersetting', N'setting.jpg')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'searchicon', N'search.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'twitter', N'twittericon.png')
INSERT [dbo].[imageUI] ([name], [image]) VALUES (N'view', N'viewIcon.png')
SET IDENTITY_INSERT [dbo].[ItemCategory] ON 

INSERT [dbo].[ItemCategory] ([categoryID], [category]) VALUES (1, N'Fast foods')
INSERT [dbo].[ItemCategory] ([categoryID], [category]) VALUES (2, N'Combos')
INSERT [dbo].[ItemCategory] ([categoryID], [category]) VALUES (3, N'Drinks')
INSERT [dbo].[ItemCategory] ([categoryID], [category]) VALUES (6, N'Pizzas')
SET IDENTITY_INSERT [dbo].[ItemCategory] OFF
SET IDENTITY_INSERT [dbo].[ItemRecord] ON 

INSERT [dbo].[ItemRecord] ([ItemID], [PurchaseRecord], [id]) VALUES (N'FF001', 4, 1)
INSERT [dbo].[ItemRecord] ([ItemID], [PurchaseRecord], [id]) VALUES (N'FF002', 4, 2)
INSERT [dbo].[ItemRecord] ([ItemID], [PurchaseRecord], [id]) VALUES (N'FF003', 4, 3)
INSERT [dbo].[ItemRecord] ([ItemID], [PurchaseRecord], [id]) VALUES (N'FF004', 3, 4)
INSERT [dbo].[ItemRecord] ([ItemID], [PurchaseRecord], [id]) VALUES (N'FF005', 1, 5)
INSERT [dbo].[ItemRecord] ([ItemID], [PurchaseRecord], [id]) VALUES (N'FF006', 2, 6)
SET IDENTITY_INSERT [dbo].[ItemRecord] OFF
INSERT [dbo].[ItemStatus] ([statusID], [status]) VALUES (N'ac', N'Active')
INSERT [dbo].[ItemStatus] ([statusID], [status]) VALUES (N'in', N'Inactive')
INSERT [dbo].[ItemStorage] ([ID], [foodName], [image], [description], [price], [Date], [categoryID], [quantity], [statusID], [sold]) VALUES (N'FF001', N'French fries and Chicken', N'friedandchicken.jpg', N'dkak
fsdfsdfsdfss
fsfsfdjhjkjdhjdjkfs', 9.9899997711181641, CAST(N'2020-02-28 09:07:08.130' AS DateTime), 1, 2975, N'ac', 25)
INSERT [dbo].[ItemStorage] ([ID], [foodName], [image], [description], [price], [Date], [categoryID], [quantity], [statusID], [sold]) VALUES (N'FF002', N'Chicken wings', N'chickenWing.jpg', N'chosufhkf\' + N'
dadaskjdhksjhdhkahdas
dasbdjkdkjasd
asdasdbsajdfsf', 12.989999771118164, CAST(N'2020-02-29 21:57:48.833' AS DateTime), 1, 2993, N'ac', 6)
INSERT [dbo].[ItemStorage] ([ID], [foodName], [image], [description], [price], [Date], [categoryID], [quantity], [statusID], [sold]) VALUES (N'FF003', N'Chicken Burger', N'burgerChicken.jpg', N'chofsfosdsufhkf\' + N'
dadaskjdhksjhdhkahdas
dasbdjkdefdofispof
fsf', 9.9899997711181641, CAST(N'2020-02-28 09:08:58.627' AS DateTime), 1, 2992, N'ac', 6)
INSERT [dbo].[ItemStorage] ([ID], [foodName], [image], [description], [price], [Date], [categoryID], [quantity], [statusID], [sold]) VALUES (N'FF004', N'Christmas Cocktail', N'christmasCocktails.jpg', N'bnvnbmsf fwfuiowenwc wekckknwcw
cwc wcwec wecweec ecw
effwc', 9.9899997711181641, CAST(N'2020-03-01 12:04:15.603' AS DateTime), 3, 2994, N'ac', 3)
INSERT [dbo].[ItemStorage] ([ID], [foodName], [image], [description], [price], [Date], [categoryID], [quantity], [statusID], [sold]) VALUES (N'FF005', N'Fry chicken', N'friedchicken.jpg', N'bnvnbfried chivkeb', 9.9899997711181641, CAST(N'2020-02-28 11:42:07.013' AS DateTime), 1, 2999, N'ac', 1)
INSERT [dbo].[ItemStorage] ([ID], [foodName], [image], [description], [price], [Date], [categoryID], [quantity], [statusID], [sold]) VALUES (N'FF006', N'Roman Pizzas', N'romanpizza.jpg', N'cliilds
csdcscd
  
d a

dasdasdasdsda', 19.989999771118164, CAST(N'2020-03-01 12:03:56.100' AS DateTime), 6, 2969, N'ac', 31)
SET IDENTITY_INSERT [dbo].[purchaseHistory] ON 

INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (1, N'usera@gmail.com', N'FF001', N'friedandchicken.jpg', N'French fries and Chicken', 3, 9.9899997711181641, CAST(N'2020-02-28 09:12:14.860' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (2, N'usera@gmail.com', N'FF003', N'burgerChicken.jpg', N'Chicken Burger', 2, 9.9899997711181641, CAST(N'2020-02-28 09:12:14.860' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (3, N'usera@gmail.com', N'FF004', N'christmasCocktails.jpg', N'Christmas Cocktail', 1, 9.9899997711181641, CAST(N'2020-02-28 09:12:14.860' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (4, N'userb@gmail.com', N'FF002', N'chickenWing.jpg', N'Chicken wings', 2, 9.9899997711181641, CAST(N'2020-02-28 10:42:28.400' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (5, N'userb@gmail.com', N'FF003', N'burgerChicken.jpg', N'Chicken Burger', 2, 9.9899997711181641, CAST(N'2020-02-28 10:42:28.400' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (6, N'userb@gmail.com', N'FF005', N'friedchicken.jpg', N'Fry chicken', 1, 9.9899997711181641, CAST(N'2020-02-28 10:42:28.400' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (7, N'userb@gmail.com', N'FF002', N'chickenWing.jpg', N'Chicken wings', 1, 9.9899997711181641, CAST(N'2020-02-28 10:53:55.757' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (8, N'userb@gmail.com', N'FF003', N'burgerChicken.jpg', N'Chicken Burger', 2, 9.9899997711181641, CAST(N'2020-02-28 10:53:55.757' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (9, N'userb@gmail.com', N'FF004', N'christmasCocktails.jpg', N'Christmas Cocktail', 2, 9.9899997711181641, CAST(N'2020-02-28 10:53:55.757' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (10, N'userb@gmail.com', N'FF001', N'friedandchicken.jpg', N'French fries and Chicken', 1, 9.9899997711181641, CAST(N'2020-02-28 11:02:16.863' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (11, N'userb@gmail.com', N'FF002', N'chickenWing.jpg', N'Chicken wings', 1, 9.9899997711181641, CAST(N'2020-02-28 11:02:16.863' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (12, N'userb@gmail.com', N'FF003', N'burgerChicken.jpg', N'Chicken Burger', 2, 9.9899997711181641, CAST(N'2020-02-28 11:02:16.863' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (13, N'userb@gmail.com', N'FF004', N'christmasCocktails.jpg', N'Christmas Cocktail', 3, 9.9899997711181641, CAST(N'2020-02-28 11:02:16.863' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (14, N'userb@gmail.com', N'FF001', N'friedandchicken.jpg', N'French fries and Chicken', 20, 9.9899997711181641, CAST(N'2020-02-28 20:36:52.247' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (15, N'userc@gmail.com', N'FF001', N'friedandchicken.jpg', N'French fries and Chicken', 1, 9.9899997711181641, CAST(N'2020-03-01 12:05:40.387' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (16, N'userc@gmail.com', N'FF002', N'chickenWing.jpg', N'Chicken wings', 3, 12.989999771118164, CAST(N'2020-03-01 12:05:40.387' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (17, N'userc@gmail.com', N'FF006', N'romanpizza.jpg', N'Roman Pizzas', 30, 19.989999771118164, CAST(N'2020-03-01 12:05:40.387' AS DateTime))
INSERT [dbo].[purchaseHistory] ([id], [mail], [foodID], [image], [name], [amount], [price], [date]) VALUES (18, N'usera@gmail.com', N'FF006', N'romanpizza.jpg', N'Roman Pizzas', 1, 19.989999771118164, CAST(N'2020-03-01 12:07:06.387' AS DateTime))
SET IDENTITY_INSERT [dbo].[purchaseHistory] OFF
ALTER TABLE [dbo].[cart]  WITH CHECK ADD  CONSTRAINT [FK_cart_HaNaUser] FOREIGN KEY([mail])
REFERENCES [dbo].[HaNaUser] ([mail])
GO
ALTER TABLE [dbo].[cart] CHECK CONSTRAINT [FK_cart_HaNaUser]
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD  CONSTRAINT [FK_cart_ItemStorage] FOREIGN KEY([foodID])
REFERENCES [dbo].[ItemStorage] ([ID])
GO
ALTER TABLE [dbo].[cart] CHECK CONSTRAINT [FK_cart_ItemStorage]
GO
ALTER TABLE [dbo].[HaNaUser]  WITH CHECK ADD  CONSTRAINT [FK_HaNaUser_HaNaUserRole] FOREIGN KEY([roleID])
REFERENCES [dbo].[HaNaUserRole] ([roleID])
GO
ALTER TABLE [dbo].[HaNaUser] CHECK CONSTRAINT [FK_HaNaUser_HaNaUserRole]
GO
ALTER TABLE [dbo].[HaNaUser]  WITH CHECK ADD  CONSTRAINT [FK_HaNaUser_HaNaUserStatus] FOREIGN KEY([statusID])
REFERENCES [dbo].[HaNaUserStatus] ([statusID])
GO
ALTER TABLE [dbo].[HaNaUser] CHECK CONSTRAINT [FK_HaNaUser_HaNaUserStatus]
GO
ALTER TABLE [dbo].[ItemRecord]  WITH CHECK ADD  CONSTRAINT [FK_ItemRecord_ItemStorage] FOREIGN KEY([ItemID])
REFERENCES [dbo].[ItemStorage] ([ID])
GO
ALTER TABLE [dbo].[ItemRecord] CHECK CONSTRAINT [FK_ItemRecord_ItemStorage]
GO
ALTER TABLE [dbo].[ItemStorage]  WITH CHECK ADD  CONSTRAINT [FK_ItemStorage_ItemCategory] FOREIGN KEY([categoryID])
REFERENCES [dbo].[ItemCategory] ([categoryID])
GO
ALTER TABLE [dbo].[ItemStorage] CHECK CONSTRAINT [FK_ItemStorage_ItemCategory]
GO
ALTER TABLE [dbo].[ItemStorage]  WITH CHECK ADD  CONSTRAINT [FK_ItemStorage_ItemStatus] FOREIGN KEY([statusID])
REFERENCES [dbo].[ItemStatus] ([statusID])
GO
ALTER TABLE [dbo].[ItemStorage] CHECK CONSTRAINT [FK_ItemStorage_ItemStatus]
GO
ALTER TABLE [dbo].[purchaseHistory]  WITH CHECK ADD  CONSTRAINT [FK_purchaseHistory_HaNaUser] FOREIGN KEY([mail])
REFERENCES [dbo].[HaNaUser] ([mail])
GO
ALTER TABLE [dbo].[purchaseHistory] CHECK CONSTRAINT [FK_purchaseHistory_HaNaUser]
GO
ALTER TABLE [dbo].[purchaseHistory]  WITH CHECK ADD  CONSTRAINT [FK_purchaseHistory_ItemStorage] FOREIGN KEY([foodID])
REFERENCES [dbo].[ItemStorage] ([ID])
GO
ALTER TABLE [dbo].[purchaseHistory] CHECK CONSTRAINT [FK_purchaseHistory_ItemStorage]
GO
USE [master]
GO
ALTER DATABASE [HaNaShop] SET  READ_WRITE 
GO

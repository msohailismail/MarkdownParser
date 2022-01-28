---
title: "使用 C# 裁剪和调整 JPEG 图像大小'"
author: "穆扎米尔汗"
date: 2022-01-05T15:09:32+00:00
summary: "作为 C# 开发人员，您可以轻松地以编程方式裁剪和调整 JPEG/JPG 图像的大小。 在本文中，您将学习如何使用 C# 裁剪和调整 JPEG 图像的大小。"
url: /zh/2022/01/05/使用-csharp-裁剪和调整-jpeg-图像/
categories:
  - Conholdate.Total 产品系列
tags:
  - "在 CSharp 中裁剪图像"
  - "图像裁剪"
  - "矩形裁剪"
  - "在 CSharp 中调整图像大小"
  - "轮班裁剪"
---

{{< figure align=center src="images/crop-and-resize-jpeg-image-using-csharp-1.jpg" alt="使用 C# 裁剪和调整 JPEG 图像大小">}}

[JPEG 或 JPG][2] 是存储图像和照片的最常用的图像格式之一。 在某些情况下，我们可能出于各种原因需要裁剪或旋转图像。 在本文中，我们将学习如何使用 C# 裁剪和调整 JPEG 图像的大小。

本文将涵盖以下主题：

  * [用于裁剪和调整 JPEG 大小的 C# Image API][3]
  * [使用 C# 裁剪 JPEG 图像][4]
  * [将图像裁剪为特定尺寸][5]
  * [在 C# 中调整 JPEG 图像的大小][6]
  * [按比例调整 JPEG 图像的大小][7]

## 用于裁剪和调整 JPEG 大小的 C# Imaging API {#CSharp-Imaging-API-to-Crop-and-Resize-JPEG}

对于裁剪和调整图像大小，我们将使用 [Aspose.Imaging for .NET][8]API。 它使我们能够以编程方式创建、加载、操作、转换和压缩图像或照片。 请[下载][9] API 的 DLL 或使用 [NuGet][10] 安装它。

```
PM> Install-Package Aspose.Imaging
```

## 使用 C# 裁剪 JPEG 图像 {#Crop-JPEG-Images-using-CSharp}

我们可以轻松地从四面八方向图像中心裁剪任何 JPEG/JPG 图像。 为此，_RasterImage_ 类提供了 _Crop(Int32, Int32, Int32, Int32)_ 方法，该方法使用指定的移位值裁剪图像。 它采用左、右、上和下移位值作为参数来裁剪图像边界。 我们可以按照以下步骤使用按班次裁剪的方法裁剪图像：

  1. 首先，使用 **[RasterImage][11]** 类加载输入的 JPG 图像。
  2. 接下来，使用 **[CacheData][12]** 方法缓存图像。
  3. 之后，使用移位值作为参数调用 **[Crop()][13]** 方法。
  4. 最后，调用 **[Save()][14]** 方法，将输出文件路径作为参数保存裁剪后的图像文件。

以下代码示例展示了**如何使用 C# 裁剪 JPEG 图像**。

```
// 此代码示例演示了如何使用按班次裁剪的方法裁剪 JPG 图像。
// 将现有图像加载到 RasterImage 类的实例中
RasterImage rasterImage = (RasterImage)Image.Load(@"C:\Files\images\aspose_logo.jpg");

// 在裁剪之前，应该缓存图像以获得更好的性能
if (!rasterImage.IsCached)
{
    rasterImage.CacheData();
}

// 定义所有四个边的移位值
int leftShift = 30;
int rightShift = 30;
int topShift = 30;
int bottomShift = 30;

// 根据移位值，对图像应用裁剪 
// 裁剪方法会将图像边界向图像中心移动
rasterImage.Crop(leftShift, rightShift, topShift, bottomShift);

// 保存裁剪的图像
rasterImage.Save(@"C:\Files\images\cropped_out.jpg");
```

{{< figure align=center src="images/Crop-JPEG-Images-using-CSharp-1024x467.jpg" alt="使用 C# 裁剪 JPEG 图像" caption="使用 C# 裁剪 JPEG 图像。">}}
 

## 使用 C# 将图像裁剪为特定大小 {#Crop-Images-to-Specific-Size-using-CSharp}

_RasterImage_ 类还提供了一个重载的 _Crop(Rectangle)_ 方法，可将图像的特定区域裁剪为指定大小。 它将 _[Rectangle][16]_ 类的实例作为参数。 我们可以按照以下步骤将图像裁剪为指定大小：

1. 首先，使用 [RasterImage](https://apireference.aspose.com/imaging/net/aspose.imaging/rasterimage) 类加载输入的 JPG 图像。
2. 接下来，使用 [CacheData](https://apireference.aspose.com/imaging/net/aspose.imaging/datastreamsupporter/methods/cachedata) 方法缓存图像。
3. 然后，用指定的位置和大小初始化 Rectangle 类对象。
4. 之后，使用创建的 Rectangle 对象作为参数调用 [Crop()](https://apireference.aspose.com/imaging/net/aspose.imaging/rasterimage/methods/crop) 方法。
5. 最后调用[Save()](https://apireference.aspose.com/imaging/net/aspose.imaging.image/save/methods/3)方法，将输出文件路径作为参数保存裁剪后的图片文件 .

以下代码示例展示了**如何使用 C# 将 JPEG 图像裁剪为特定大小**。

```
// 此代码示例演示如何使用按矩形裁剪的方法裁剪 JPG 图像。
// 将现有图像加载到 RasterImage 类的实例中
RasterImage rasterImage = (RasterImage)Image.Load(@"C:\Files\images\aspose_logo.jpg");

// 在裁剪之前，应缓存图像以获得更好的性能
if (!rasterImage.IsCached)
{
    rasterImage.CacheData();
}

// 创建具有所需大小的 Rectangle 类的实例
Rectangle rectangle = new Rectangle(35, 35, 580, 240);

// 对 Rectangle 类的对象进行裁剪操作
rasterImage.Crop(rectangle);

// 保存裁剪的图像
rasterImage.Save(@"C:\Files\images\CroppingByRectangle_out.jpg");
```

{{< figure align=center src="images/Crop-Image-in-Specific-Size-using-CSharp-1024x431.jpg" alt="使用 C# 裁剪特定大小的图像。" caption="使用 C# 将图像裁剪为特定大小。">}}
 
  	
## 在 C# 中调整 JPEG 图像的大小 {#Resize-JPEG-Images-in-CSharp}

_[Image][18]_ 类提供了 _Resize()_ 方法来调整图像大小。 我们可以按照以下步骤调整 JPEG 图像的大小：

1. 首先，使用 [Image](https://apireference.aspose.com/imaging/net/aspose.imaging/image) 类加载输入的 JPG 图像。
2. 接下来，使用所需的宽度和高度作为参数调用 [Resize(int32, int32)](https://apireference.aspose.com/imaging/net/aspose.imaging/image/methods/resize) 方法。
3. 最后调用[Save()](https://apireference.aspose.com/imaging/net/aspose.imaging.image/save/methods/3)方法，将输出文件路径作为参数保存调整后的图片 .

以下代码示例显示了**如何使用 C# 调整 JPEG 图像的大小**。

```
// 此代码示例演示如何使用按矩形裁剪的方法裁剪 JPG 图像。
// 加载现有图像
Image image = Image.Load(@"C:\Files\images\aspose_logo.jpg");

// 新的宽度和高度
image.Resize(300, 100);

// 保存调整大小的图像
image.Save(@"C:\Files\images\SimpleResizing_out.jpg");
```

{{< figure align=center src="images/Resize-JPEG-Images-in-CSharp-1024x460.jpg" alt="在 C# 中调整 JPEG 图像的大小" caption="在 C# 中调整 JPEG 图像的大小">}}


## 在 C# 中按比例调整 JPEG 图像的大小 {#Resize-JPEG-Images-Proportionally-in-CSharp}

当我们用固定的高度和宽度调整大小时，我们可能会得到拉伸的图像。 我们可以使用比例调整大小来避免拉伸图像。 为此，_[Image][18]_ 类提供了 [_ResizeWidthProportionally(Int32)_][20] 和 [_ResizeHeightProportionally(Int32)_][21] 方法。 请按照下面给出的步骤按比例调整图像的宽度和高度。

1. 首先，使用 [Image](https://apireference.aspose.com/imaging/net/aspose.imaging/image) 类加载输入的 JPG 图像。
2. 接下来，使用新宽度作为参数调用 [Image.ResizeWidthProportionally(Int32)](https://apireference.aspose.com/imaging/net/aspose.imaging/image/methods/resizewidthproportionally) 方法。
3. 之后，使用新高度作为参数调用 [Image.ResizeHeightProportionally(Int32)](https://apireference.aspose.com/imaging/net/aspose.imaging/image/methods/resizeheightproportionally) 方法。
4. 最后调用[Save()](https://apireference.aspose.com/imaging/net/aspose.imaging.image/save/methods/3)方法，将输出文件路径作为参数保存调整后的图片 .

以下代码示例显示了**如何使用 C#按比例调整 JPEG 图像的大小**。

```
// 此代码示例演示如何使用按矩形裁剪的方法裁剪 JPG 图像。
// 加载现有图像
Image image = Image.Load(@"C:\Files\images\sample.jpg");

// 宽度
int new宽度 = image.宽度 / 2;
image.Resize宽度Proportionally(new宽度);

// 高度
int new高度 = image.高度 / 2;
image.Resize高度Proportionally(new高度);

// 定义保存选项
JpegOptions saveOptions = new JpegOptions();
saveOptions.Quality = 100;

// 保存图像
image.Save(@"C:\Files\images\sample_out.jpg", saveOptions);
```

## 获得免费许可证

请通过申请 [免费的临时许可证][22] 来尝试不受评估限制的 API。

## 结论

在本文中，我们学习了如何：

  * 在 C# 中裁剪 JPEG 图像；
  * 将图像裁剪为特定大小；
  * 以编程方式调整 JPEG 图像的大小；
  * 按比例调整 JPEG 图像的大小。

此外，您可以使用 [文档][23] 了解更多关于 Aspose.Imaging for .NET API 的信息。 如有任何歧义，请随时在 [论坛][24] 上与我们联系。

## 也可以看看

  * [使用 C# .NET 调整图像大小][25]
  * [使用 C# 压缩图像][26]

 [1]: https://blog.conholdate.com/wp-content/uploads/sites/27/2022/01/crop-and-resize-jpeg-image-using-csharp-1.jpg
 [2]: https://docs.fileformat.com/image/jpeg/
 [3]: #CSharp-Imaging-API-to-Crop-and-Resize-JPEG
 [4]: #Crop-JPEG-Images-using-CSharp
 [5]: #Crop-Images-to-Specific-Size-using-CSharp
 [6]: #Resize-JPEG-Images-in-CSharp
 [7]: #Resize-JPEG-Images-Proportionally-in-CSharp
 [8]: https://products.aspose.com/imaging/net/
 [9]: https://downloads.aspose.com/imaging/net
 [10]: https://www.nuget.org/packages/aspose.imaging
 [11]: https://apireference.aspose.com/imaging/net/aspose.imaging/rasterimage
 [12]: https://apireference.aspose.com/imaging/net/aspose.imaging/datastreamsupporter/methods/cachedata
 [13]: https://apireference.aspose.com/imaging/net/aspose.imaging.rasterimage/crop/methods/1
 [14]: https://apireference.aspose.com/imaging/net/aspose.imaging.image/save/methods/3
 [15]: https://blog.conholdate.com/wp-content/uploads/sites/27/2022/01/Crop-JPEG-Images-using-CSharp.jpg
 [16]: https://apireference.aspose.com/imaging/net/aspose.imaging/rectangle
 [17]: https://blog.conholdate.com/wp-content/uploads/sites/27/2022/01/Crop-Image-in-Specific-Size-using-CSharp.jpg
 [18]: https://apireference.aspose.com/imaging/net/aspose.imaging/image
 [19]: https://blog.conholdate.com/wp-content/uploads/sites/27/2022/01/Resize-JPEG-Images-in-CSharp.jpg
 [20]: https://apireference.aspose.com/imaging/net/aspose.imaging/image/methods/resizewidthproportionally
 [21]: https://apireference.aspose.com/imaging/net/aspose.imaging/image/methods/resizeheightproportionally
 [22]: https://purchase.conholdate.com/temporary-license
 [23]: https://docs.aspose.com/imaging/net
 [24]: https://forum.aspose.com/c/imaging
 [25]: https://blog.aspose.com/2021/12/20/resize-images-in-csharp/
 [26]: https://blog.aspose.com/2020/11/27/compress-png-jpeg-and-tiff-images-using-csharp/


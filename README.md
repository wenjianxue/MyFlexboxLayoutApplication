#
Google“流式”布局FlexboxLayout简介

## 什么是FlexboxLayout？

FlexboxLayout是Google在2016年开源的Android项目，地址：[https://github.com/google/flexbox-layout](https://github.com/google/flexbox-layout)。根据Github对这个库的介绍：

> FlexboxLayout 一个Android平台上与CSS的 Flexible box 布局模块 有相似功能的库。
>
> FlexboxLayout is a library project which brings the similar capabilities of [CSS Flexible Box Layout Module](https://www.w3.org/TR/css-flexbox-1) to Android。

### CSS的 Flexible box 布局模块又是什么呢？

CSS的Flexible box 布局不在这里叙述，有兴趣的同学可以自行google。Flexible box 布局的主要特点是

> （1）Flexbox是布局模块，而不是一个简单的属性，它包含父元素和子元素的属性。
>
> （2）布局的主体思想是元素可以改变大小以适应可用空间，当可用空间变大，Flex元素将拉伸大小以填充可用空间，当Flex元素超出可用空间时将自动缩小。总之，Flex元素是可以让你的布局根据浏览器的大小变化进行自动伸缩。

回归正题，FlexboxLayout是一种布局模块，类似我们熟悉的RelativeLayout、LinearLayout等，FlexboxLayout可以让布局改变大小以适应可用空间，可以简单理解为高级版的LinearLayout，不同的是FlexboxLayout可以根据具体情况进行换行。

## FlexboxLayout示例

先看一下google提供的一个gif 大致了解FlexboxLayout的功能。

![](/images/flexbox-layoutmanager.gif)


好像没有了不起的，那我们再来看一个例子。


![](/images/20170404145254052.gif)
![](/images/20170404173048756.gif)

是不是看出点门道了。没错，这就是FlexboxLayout的功能能够根据加入布局的内容调整其中的百分比宽度或者自动换行。


## FlexboxLayout的应用场景 ##
除了上面给出来的图片墙场景，根据图片尺寸自动进行变化之外，FlexboxLayout还可以应用的在搜索历史等场景下，这样就不用FlowLayout来实现咯~
![](/images/3c8c21f8dcfb7df697800f8364b108ed.jpg)


接下来让我们来接入FlexboxLayout吧。

## 引入FlexboxLayout ##
在项目的gradle中直接引用：
 `compile 'com.google.android:flexbox:0.3.1'`

## Flexbox的布局和相关名称 ##
![](/images/693110-53c5ca883d4326ca.png)
上图模型中包含以下概念

1. flex container 父容器，用来包含子元素，对应于FlexboxLayout类。
1. flex item 子元素，父容器直接包裹的元素。
1. main axis 主轴，子元素通过主轴来排列，如上图是从左往右。
1. corss axis 副轴，垂直于主轴的第二个轴
1. main start,main end 父容器中主轴开始和结束的边界，子元素从main start往main end的方向排列（如果主轴是水平，起点在左端，main start,main end 用来控制子元素从左向右排列）
1. cross start,corss end 父容器中副轴开始和结束的边界。子元素从cross start往cross end方向排列(如果主轴是水平的，那么副轴就是垂直的，假设如上图，cross start 在上，cross end 在下，那么子元素就是从上往下排列)


## 布局示例 ##
    <com.google.android.flexbox.FlexboxLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:flexWrap="wrap"
    app:alignItems="stretch"
    app:alignContent="stretch" >
    
    <TextView
    android:id="@+id/textview1"
    android:layout_width="120dp"
    android:layout_height="80dp"
    app:layout_flexBasisPercent="50%"
    />
    
    <TextView
    android:id="@+id/textview2"
    android:layout_width="80dp"
    android:layout_height="80dp"
    app:layout_alignSelf="center"
    />
    
    <TextView
    android:id="@+id/textview3"
    android:layout_width="160dp"
    android:layout_height="80dp"
    app:layout_alignSelf="flex_end"
    />
    </com.google.android.flexbox.FlexboxLayout>
或者代码

    FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
    flexboxLayout.setFlexDirection(FlexboxLayout.FLEX_DIRECTION_COLUMN);
    
    View view = flexboxLayout.getChildAt(0);
    FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
    lp.order = -1;
    lp.flexGrow = 2;
    view.setLayoutParams(lp);

## 属性介绍 ##
### Contatiner的属性 ###
flexDirection：子元素的排列方向，决定主轴和副轴的方向，有以下四个值，文字太鸡肋，直接看图结合demo一目了然。

row (default)

![](/images/row.png)

row_reverse

![](/images/row_reverse.png)

column

![](/images/column.png)

column_reverse

![](/images/column_reverse.png)



![](/images/flex-direction.gif)


flexWrap：控制单行和多行，以及副轴的方向

- nowrap (default) 不换行
- wrap 换行
- wrap_reverse 副轴方向置反



![](/images/flex-Wrap.gif)


justifyContent 控制沿主轴对齐

- flex-start（default）：左对齐
- flex-end：右对齐
- center： 居中
- space-between：两端对齐，项目之间的间隔都相等。
- space-around：每个项目两侧的间隔相等。所以，项目之间的间隔比项目与边框的间隔大一倍。

![](/images/justify-content.gif)


alignItems 控制沿副轴对齐(单行起作用)

- stretch（default）：如果项目未设置高度或设为auto，将占满整个容器的高度。
- flex-start：交叉轴的起点对齐。
- flex-end：交叉轴的终点对齐。
- center：交叉轴的中点对齐。
- baseline: 项目的第一行文字的基线对齐。

![](/images/align-items.gif)


alignContent ：控制沿副轴对齐(多行起作用)

- stretch（default）：轴线占满整个交叉轴。
- flex-start：与交叉轴的起点对齐。
- flex-end：与交叉轴的终点对齐。
- center：与交叉轴的中点对齐。
- space-between：与交叉轴两端对齐，轴线之间的间隔平均分布。
- space-around：每根轴线两侧的间隔都相等。所以，轴线之间的间隔比轴线与边框的间隔大一倍。

![](/images/align-content.gif)

### 子元素的属性 ###
1. ayout_order： 控制子元素布局的顺序，默认值为1，顺序为XML中元素的顺序


![](/images/20160708134300827.jpg)

1. layout_flexGrow 类似于weight。


![](/images/20160708135036366.jpg)

1. layout_flexShrink 空间不足时，子元素缩小，如果为0，不变化


![](/images/20160708135436970.jpg)

1. layout_alignSelf 允许单个元素有不一样的对齐方式，会覆盖align-item,除auto外，其他取值都和align-item的含义一致。
	- auto (default) 继承父元素的align-item
	- flex_start
	- flex_end
	- center
	- baseline
	- stretch

![](/images/layout_alignSelf.gif)

2. layout_flexBasisPercent 只能为百分比的值，只有父元素是MeasureSpec.EXACTLY的模式时才有效。

![](/images/layout_flexBasisPercent.gif)
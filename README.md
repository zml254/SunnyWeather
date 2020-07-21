# SunnyWeather
这次考核感觉没有达到往年学长们写考核的强度，因为没有api所以就用的第一行代码上的例子，但是感觉还是不太够，就用了强行用了上学期的wanandroid的api，用motionlayout加了一点动画，还是踩了很多的坑。</br>
最后因为改bug没有来得及写readme。。。</br>
我把最开始的界面从fragment里面分离出来了，下面有原来在上面搜索的记录（ChipGroup），搜索栏不需要点击确认，只有监听到值改变就会更新。</br>
然后右下角的fab的动画有个坑不知道怎么解决，如果点击就播放动画，activity里面的内容不会显示，但是点一下就会显示。所以没有做那个优化。</br>
在一个linearlayout里面动态添加了一个横向的recyclerview。然后下面有一个自定义view的动画。</br>
然后感觉内容不够，就强行凑的wanandroid，上面自己撸了一个轮播图，把每个内容都注册了点击事件。用了一个viewpager2和tablayout一起写界面，里面添加了轮播图，所以简单处理了一下滑动冲突，动态添加tabitem的约束布局，用监听器监听页面变化，对图标和汉字作出相应的变化。</br>
第二个界面里面每个item里面添加了很多个textView，然后改变他的visibility让它显示确定的个数。</br>
最后的一个界面就是把chipgroup和recyclerview进行嵌套，在注册点击事件。</br>

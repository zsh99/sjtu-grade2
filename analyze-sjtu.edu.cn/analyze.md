# ANAYLZE OF SJTU HOMEPAGE
## PERFORMANCE
![performance](https://github.com/zsh99/sjtu-grade2/blob/master/analyze-sjtu.edu.cn/images/1.png?raw=true)

overview窗格包含以下3个图表：

a.FPS。每秒帧数。绿色竖线越高，FPS越高。FPS图表上的红色块表示长时间帧，很可能会出现卡顿。

b.CPU。CPU资源。此面积图指示消耗 CPU 资源的事件类型

c.NET。每条彩色横杠表示一种资源。横杠越长，检索资源所需的时间越长。 每个横杠的浅色部分表示等待时间（从请求资源到第一个字节下载完成的时间）

### 分析fps
FPS（frames per second）是用来分析动画的一个主要性能指标。能保持在60的FPS的话，那么用户体验就是不错的。观察FPS图表，如果你发现了一个红色的长条，那么就说明这些帧存在严重问题，有可能导致非常差的用户体验。一般来说，绿色的长条越高，说明FPS越高，用户体验越好。

图中fps绿色部分占大多数位置则说明该网站体验较好

### cpu
就在FPS图表下方，你会看到CPU图表。在CPU图表中的各种颜色与Summary面板里的颜色是相互对应的，Summary面板就在Performance面板的下方。CPU图表中的各种颜色代表着在这个时间段内，CPU在各种处理上所花费的时间。

Loading：网络通信和HTML解析

Scripting：JavaScript执行

Rendering：样式计算和布局，即重排

Painting：媒体文件的加载。

Othering： 其他资源的加载。

我们可以看到script和rendering两个板块消耗是最大，主要是因为交大主页存在非常多的动画以及首屏的轮播盘，这些动画都需要进行redering。页面中的所有数据均是在document加载完成后通过ajax访问后端得到数据，再动态加载到页面中的，以及部分动画用js实现。


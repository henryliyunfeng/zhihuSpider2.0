# zhihuSpider2.0
使用httpclient4.5.2+Jsoup+java，完成多线程爬取知乎-发现页面
# 框架
![enter description here][1]
模拟登陆：这部分没有做，比较复杂，之后会继续做；
初始化队列：存放所有待爬取的叶子URL；
Fetcher: 爬虫模拟浏览器发出GET URL请求，下载页面；
Handler:对Fetcher下载的页面进行初步处理，如判断该页面的返回状态码是否正确、页面内容是否为反爬信息等，从而保证传到Parser进行解析的页面是正确的;
Parser：对Fetcher下载的页面内容进行解析，获取叶子链接，获取目标数据；
Store: 将Parser解析出的目标数据存入本地存储，可以是MySQL传统数据库，也可以文件存储
# 需求
与第一个版本相同
# 效果
![enter description here][2]


  [1]: http://7xvohu.com1.z0.glb.clouddn.com/img/zhihu%E7%AC%AC%E4%BA%94%E5%BC%B92.jpg
  [2]: http://7xvohu.com1.z0.glb.clouddn.com/img/zhihu%E7%AC%AC%E4%BA%94%E5%BC%B93.jpg
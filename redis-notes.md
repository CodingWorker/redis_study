#NOSQL

nosql  == not only sql

nosql是以key-value形式存储

不一定遵循传统数据库的一些基本要求，比如SQL标准，ACID属性即事务属性和表结构等

>特点：非关系型 分布式的 开源的 水平可扩展的
处理超大量的数据，运行在便宜的PC服务器集群上，击碎了性能瓶颈

##使用场景
对数据高并发读写；对海量数据的高效率存储和访问；对数据的高可扩展性和高性能性如分布式等

#Redis

- 开源  
- key-value存储
- 键可以包含字符串 哈希 链表 集合和有序集合

redis周期性的将数据写入磁盘：快照或者更新数据的方式，因此或宕机后数据不丢失

新浪微博就是使用的redis

**适用场合：**应用程序直接访问redis,如果redis没有数据或者出现问题就访问mysql数据

1. 取得最新的N个数据的操作
2. 排行榜的应用，TOPN
3. 精确设定过期的时间
4. 计数器应用
5. unique操作，获取某段时间所有哦数据排重
6. 实时系统，反垃圾系统
7. 发布订阅系统
8. 构建队列
9. 作为缓存

reids的端口是6379

##安装



##开启
服务端：redis-cli

客户端：redis-cli 

##关闭


#redis的几种数据类型

##String 
一个key只对应一个value 是二进制安全的

甚至可以存储图片等

###set
设置key对应的string类型的值

能够修改value

###get
获取string类型的key对应的值

###setnx 
即set not exists,不存在才会设置值，否则不会修改已存在的key值

如果key存在返回0，否则设置新的key-value并返回1

###setex
即set expire,设置值的同时设置值得有效期

	setex name 10 hahha 

注意：第二个参数执行有效期


###setrange
等量字符替换

	set email 123@163.com
	get email
	setrange email 3 hahh//返回设置后新值的长度
	get email
	输出：hahh.com

###mset
批量设置key-value,如果有一个不成功就全部不能设置

	mset key1 a key2 b key 3//返回ok设置成功
	get key1
	get key2
	get key3

###msetnx
批量的setnx

##getset
相当于先get 后set 
get旧值设置新值

###getrange
getrange email 0 4 返回email对应值得下标从0-4的字符

###mget
批量获得

	mget key1 key2 key3 

###incr
递增1

		incr key1

###incrby
递增指定值

	incrby key1 3 
	incrby key1 -3//减

###decr


###decrby

###append 
给指定key追加value后字符串，修改value

		get key1 //输出：123
		append key1 haha
		get key1//返回123haha

返回新的value的长度

###strlen
查看key对应的value的长度

##hash类型

时间复杂度0(1)代表添加和删除为平均操作

key就相应与映射表，field为字段

适合存储对象

存储string类型的key-value

可以将其看做一张表，field就是字段

###hset
设置hash field为指定值

	hash user:001 name lijie

###hsetnx
不存在设置，存在不设置

###hget
获取指定表中指定字段的值

###hmset
批量设置

	hmset user:003 name lijie age 20 set 1

###hmget

	hmget user:003 name age sex

###hincrby

	hincrby user:003 age 5

###hexists
判断hash表中的字段是否存在

	hexists user:003 age

###hlen
返回hash表存在的字段field的数量

###hdel
删除指定hash表中的字段

	hdel user:003 age

###hkeys
返回指定hash表中的所有字段

	hkeys user:003

###hvalus
返回hash里面的所有value

###hgetall
获取hash表中的所有字段field和对应的value


##list类型

主要功能是

- push
- poop
- 获取某一个范围的所有值等

栈的特性：filo 先进后出

队列：fifo  先进先出

redis的list是一个双向链表，可以从开始压入和弹出，也可以从尾部压入和弹出

###lpush
从头部压入一个元素

	kpush mylist 'hello'//返回list新的元素个数
	lpush mylist 'world'

###lrange
类似PHP的substr,

	lrange mylist 0 -1 
	返回mylist中的所有的值,hello world

###rpush
从list的尾部压如元素

	rpush mylist2 'hi'
	rpush mylist2 'earth'

###linsert
将值插入指定值得前面

	lpush list3 one
	lpusth list3 three
	lrange 0 -1
	linsert list3 before one three

有为至头为向前，头在前，尾在后

###lset 设置list指定下标的元素的值

	lpush list5 one
	lpush list5 two
	lset list5 1 2

###lrem
从list中删除指定个数指定值

	lrem list5 3 'hello' 从list5中删除3个hello

###ltrim
保留指定key值范围内的数据

	ltrim list5 1 -1 //只保留下标为1 下标为-1的元素，在此范围两端的元素全部删除

###lpop
从list的头部弹出元素并删除该元素

###rpop
从list的尾部弹出元素并删除该元素

###rpoplpush
从第一个list的尾部弹出删除元素并将该元素添加到第二个list的头部

	rpoplpush list1 list2//将list1的尾部元素弹出添加到list2的头部


###lindex
返回list中下标为index的元素

	lindex list1 1 返回list1中索引为1的元素

###llen
返回链表的长度，即list中元素的个数


##sets类型

set是无集合，他是通过hash表来实现的，添加和删除和查找的复杂度都为1

适用于 sns中的好友推荐和blog中的tag功能，利用的就是集合的差集

###sadd
向名称为key的集合中添加数据

	sadd myset1 one
	sadd myset1 two
	sadd myset1 one//添加不成功，集合元素唯一

###smembers
查看集合的成员

	smembers myset1

###srem
删除名称为key的集合中的值

		sadd myset2 one
		sadd myset2 two
		sadd myset2 three
		srem myset2 two
		smembers myset2

###spop
随机从名称为key的集合中弹出并删除元素

	spop myset2

###sdiff
返回给定key的集合与另一个名称为key的集合的差集
以第一个集合为准

类似array_diff

###sdiffstore
将返回的差集存储到另一个集合里

	sdiffstore myset3 myset1 myset2 将1和2的差集存储到myset3

###sinter
取交集

###sinterstore
将交集存储到另一个集合里


###sunion
返回所有集合的并集

###sunionstore
将并集存储到另一个集合里

###smove
将一个集合里的数据移到另一个集合中

	smove myset2 myset1 three 将myset2里的three元素移到myset1里面


###scard
返回名称为key的集合的元素个数

###sismember
判断某一个元素是否为集合的元素

###srandmember
随机返回名称为key的集合的一个元素，但不删除该元素

	srandmember myset1


##sorted sets类型 有序集合zset
在set的基础上增加了顺序属性

###zadd
在有序集合key添加元素member，并指定score用于排序

	zadd myzet 1 'one'

###zrange
取指定索引范围的数据

	zrange myzet 0 -1 withscore 取出所有数据并显示score

##zrem
删除名称为key的集合中的元素

	zrem zset1 one

###zincrby
以指定值来增加存在元素的score,如果元素不存在则添加新元素

	zincrby zset1  5 'one'

###zrank
返回名称为key的集合中指定值的索引，注意索引和score不同

	zrank zset1 two 返回zset1中元素two的索引

###zrevrank
反向排序再返回索引，即头的索引0变为最后

###zrevrange
降序排列，输出指定范围的元素

	zrevrange zset1 0 -1 withscores

###zrangebyscore
通过score指定范围来返回值，之前的zrange是利用的索引

###zcount
返回集合中score在指定范围的数量

###zcard
返回集合中的所有元素的个数

###zremrangebyrank
通过索引正常排序删除自定索引范围的元素

###zremrangebyscore
通过score排序删除score在指定范围的元素


**byrank为依据索引，byscore为依据score**

#redis常用命令

##键值相关命令

###keys pattern
返回满足pattern 格式的所有键

	keys *
	keys my*

###exists key
确认key是否存在

	exists mykey
	exists key
###del key
删除一个键key

	del key1
	del my*

###expire key seconds
设置存在key的过期时间

	expire key 10

###ttl key
产看key的有效时长

	ttl key1

###select 0
选择0数据库

###move age 1 
将当前数据库中的age移到1数据库

**进入redis客户端时默认进入的是0数据库，一共0-15数据库**

###persist key
移除key的过期时间

	expire age 20
	persist age

###randomkey 
随机返回数据库中的一个键

	randomkey

###rename key new_name
给key重命名

###type key
产看键key的数据类型


##服务器相关命令

###ping
测试连接是否存活

	ping
	返回PONG 为有响应

###echo string
输出string字符串

###select 3
选择数据库，编号为0-15

###quit 或者exit
退出客户端

###dbsize
返回当前数据库中的key的数目

	dbsize

###info
获取服务器的信息和统计

	info

###config get dir
实时转储

	config get dir
	config get timeout
###flushdb
清空当前数据库

	flushdb

###flushall
清空所有数据库

	flushall

#Redis的高级应用

##安全性
速度太快，一秒中可以尝试15万次密码，需要非常复杂的密码

编辑配置文件，`requirepass`取消注释，并设置密码

重启服务，这时即使能够进入但不能操作，需要在操作之前先授权，即进入后输入：auth 你的密码

**或者在开始启动客户端时加上-a 密码**


##主从复制

通过主从复制可以实现从服务器拥有主服务器的数据版本

**特点：**

- 一个master可以与多个slave相连
- slave除了可以连接master,还会与其他slave相连
- 主从复制不会堵塞master,在同步数据时master可以继续处理client请求
- 提高系统的伸缩性

###配置：需要配置从服务器，主机不需要配置
1. slaveof 主机ip 主机端口
2. masterauth 主机密码
3. 通过info可以查是从还是主role属性

**完成配置**

**过程：**

1. slave与master建立链接，从机向主机发送sync同步命令
2. master会启动一个后台进程，将数据同步到从服务器

##事务处理

关键命令:

multi和exec

在客户端发送命令：multi，打开事务处理的上下文，之后的所有命令都会放在一个队列里面不会立即执行，当发送命令：exec时才会执行

discard取消在之前加入队列的命令并退出上下文，达到事务回滚的效果

**redis里的事务处理：即使队列的某个命令没有执行成功，其他成功的命令仍然会执行，事务不会回滚**

Redis的乐观锁：watch命令

##持久化机制

Redis需要经常将内存中的数据同步硬盘来保证数据的持久化

**同步方式：**

- 快照是默认的方式，
- aof方式，通过该命令redis会将命令的每一次操作写入磁盘

由于操作系统的缓存机制，可以通过fsync函数强制os写入到磁盘的时机来避免aof方式可能导致的部分数据丢失


##发布订阅信息

publish和subscribe

可以做为消息发布系统和web聊天系统

##虚拟内存使用

虚拟内存**配置**后重启

info查询具体信息


#php-redis实例

安装phpredis模块

启动服务器

list列表的而使用

使用列表做分页操作


































































































































































































CnAgent
=======

ChinaAgent for enhance Class method by javassist lib at Runtime/StartUp


usage
-----

Use _Jvm attach Api_ or _-javaagent_ to load this agent, and pass the arguments like following:

```
  argentargs = 'com.yet.another.test.plain.ClassB::producer|data$=$"world";$System.out.println(data);'
```
** where **

* class is  : com.yet.another.test.plain.ClassB
* method is : producer
* use $ as placeholder of white blank char

This will insert the code ``"data = "world";`` into the front of the method ``producer`` of class``com.yet.another.test.plain.ClassB``

You can use [CnAttacher](https://github.com/ChinaXing/CnJvmAttacher) to load this agent.


Author
======

ChinaXing (chen.yack at gmail.com)

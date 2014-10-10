CnAgent
=======

ChinaAgent for enhance Class method by javassist lib at Runtime


usage
-----

Use Jvm attach Api load this agent, and pass the arguments like following:

```
  argentargs = "com.yet.another.test.plain.ClassB::producer data = \"world\";
```
This will insert the code ``"data = "world";`` into the front of the method ``producer`` of class``com.yet.another.test.plain.ClassB``

You can use [CnAttacher](https://github.com/ChinaXing/CnJvmAttacher) to load this agent.


Author
======

ChinaXing (chen.yack at gmail.com)

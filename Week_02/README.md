学习笔记
---
<br>各种GC对比 jdk:openjdk 11 
---
>***-Xms1g -Xmx1g*** 
> - java -Xms1g -Xmx1g -XX:-UseAdaptiveSizePolicy -XX:+PrintGC -XX:+UseSerialGC GCLogAnalysis</br>共生成对象次数:15855
>- java -XX:+UseParallelGC -Xms1g -Xmx1g -XX:-UseAdaptiveSizePolicy -XX:+PrintGC  GCLogAnalysis</br>共生成对象次数:19620
>- java -Xms1g -Xmx1g -XX:-UseAdaptiveSizePolicy -XX:+PrintGC -XX:+UseConcMarkSweepGC  GCLogAnalysis</br>共生成对象次数:16879
>- java -Xms1g -Xmx1g -XX:-UseAdaptiveSizePolicy -XX:+PrintGC -XX:+UseG1GC  GCLogAnalysis</br>共生成对象次数:22034
 
---
 
>***-Xms2g -Xmx2g***
>- java -Xms2g -Xmx2g -XX:-UseAdaptiveSizePolicy -XX:+PrintGC -XX:+UseSerialGC
 GCLogAnalysis</br>共生成对象次数:14340，性能与1g略低
>- java -Xms2g -Xmx2g -XX:-UseAdaptiveSizePolicy -XX:+PrintGC -XX:+UseParallelGC GCLogAnalysis</br>共生成对象次数:19602, 性能相当
>- java -Xms2g -Xmx2g -XX:-UseAdaptiveSizePolicy -XX:+PrintGC -XX:+UseConcMarkSweepGC GCLogAnalysis</br>共生成对象次数:17840，性能略有提升
>- java -Xms2g -Xmx2g -XX:-UseAdaptiveSizePolicy -XX:+PrintGC -XX:+UseG1GC GCLogAnalysis</br>共生成对象次数:21709，性能较1g下降

- **现象分析：** 堆空间2g时，横向对比仍然是G1更优，但纵向对比时发现2g空间，G1性能反而下降。对于堆的大小选取，我们应该尽量估算准确，够用就好。其他GC虽有波动，但差距不大。
---

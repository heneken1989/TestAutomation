file:///C:/Users/huyhi/Desktop/C%20sharp/Project%20Term3/Automation/Automation/testauto/src/test/scala/mock/MockUtils.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

action parameters:
offset: 992
uri: file:///C:/Users/huyhi/Desktop/C%20sharp/Project%20Term3/Automation/Automation/testauto/src/test/scala/mock/MockUtils.java
text:
```scala
package mock;

import com.intuit.karate.PerfContext;
import com.intuit.karate.core.MockServer;

import java.util.Collections;
import java.util.Map;

/**
 *
 * @author pthomas3
 */
public class MockUtils {
    
    public static void main(String[] args) {
        startServer(8080);
    }
    
    public static void startServer(int port) {
        MockServer server = MockServer.feature("classpath:mock/mock.feature").http(port).build();
        System.setProperty("mock.port", server.getPort() + "");        
    }

    public static Map<String, Object> myRpc(Map<String, Object> map, PerfContext context) {
        long startTime = System.currentTimeMillis();
        // this is just an example, you can put any kind of code here
        int sleepTime = (Integer) map.get("sleep");
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        // and here i@@s where you send the performance data to the reporting engine
        context.capturePerfEvent("myRpc-" + sleepTime, startTime, endTime);
        return Collections.singletonMap("success", true);
    }
    
}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:933)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:168)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:34)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:329)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator
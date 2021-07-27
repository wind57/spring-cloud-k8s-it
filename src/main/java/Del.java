import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

public class Del {

    public static void main(String[] args) {
        CompletableFuture[] arr = new CompletableFuture[1];
        arr[0] = testMe(new Input());

        CompletableFuture.allOf(arr)
                .whenComplete((x, y) -> {
                    System.out.println("done");
                });

        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));
    }

    static CompletableFuture<Void> testMe(Input input) {
        return
        one(input)
                .thenAccept(input::setId)
                .thenAccept(void_ -> oneMore());
                //.thenCompose(void_ -> oneMore());
    }

    static CompletableFuture<Long> one(Input input) {
        System.out.println("one started");
        return CompletableFuture.supplyAsync(() -> {
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
            return 42L;
        });
    }

    static CompletableFuture<Void> oneMore() {
        System.out.println("one more started");
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        System.out.println("one more done");
        return CompletableFuture.completedFuture(null);
    }

    static class Input {

        private Long value;

        void setId(long value) {
            this.value = value;
        }
    }

}

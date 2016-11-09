package base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akui on 16/11/8.
 */
public class AnonyClass {

    @Test
    public void testCirlc() {
        Observer observer = new Observer();
        observer.regNew(param -> {
            System.out.println("listener I,param:" + param);
        });
        observer.regNew(param -> {
            System.out.println("listener II,param:" + param);
        });
        observer.notify("my param");
    }


    class Observer {

        private List<Action> regList = new ArrayList<Action>();

        public void regNew(Action action) {
            regList.add(action);
        }

        public void notify(String param) {
            regList.forEach(a -> a.doAction(param));
        }

    }

    interface Action {
        public void doAction(String param);
    }
}

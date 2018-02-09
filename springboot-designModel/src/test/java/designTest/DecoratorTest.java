package designTest;

import com.moekosu.design.decorator.realDemo1.JsonLogger;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * @author chenxu
 * @date 2018/02
 */
public class DecoratorTest {

    @Test
    public void ownerLogger()
    {
        Logger logger = JsonLogger.JsonLoggerFactory.getLogger(DecoratorTest.class);
        //
        logger.info("test");
    }

}

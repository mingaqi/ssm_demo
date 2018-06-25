import com.domi.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

//@ContextConfiguration("classpath:applicationContext.xml")
public class MainTest {

//    @Resource(name = "userService")
//    private UserService userService;

    @Test
    public void transactionTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = ctx.getBean("userService", UserService.class);
        System.out.println(userService);

        //默认spring事务只在发生未被捕获的 runtimeexcetpion时才回滚。 所以service需要抛出异常, 最外层调用再进行try catch
        //或者在catch中手动TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        try {
            System.out.println(userService.iinsert2Rows());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInstance(){
        try {
            UserService userService = (UserService)Class.forName("com.domi.service.UserService").newInstance();
            System.out.println(userService);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

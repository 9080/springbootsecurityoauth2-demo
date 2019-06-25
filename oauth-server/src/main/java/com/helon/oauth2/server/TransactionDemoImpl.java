package com.helon.oauth2.server;

import com.helon.oauth2.mybatis.City;
import com.helon.oauth2.mybatis.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: TransactionDemoImpl
 * @summary: Spring中7种事务传播行为
 * @Description:
 *              1. PROPAGATION_REQUIRED: 如果当前没有事务，就新建一个事务，
 *              如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
 *              2. PROPAGATION_SUPPORTS: 支持当前事务，如果当前没有事务，就以非事务方式执行。
 *              3. PROPAGATION_MANDATORY: 使用当前的事务，如果当前没有事务，就抛出异常。
 *              4. PROPAGATION_REQUIRES_NEW: 新建事务，如果当前存在事务，把当前事务挂起。
 *              5. PROPAGATION_NOT_SUPPORTED: 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
 *              6. PROPAGATION_NEVER: 以非事务方式执行，如果当前存在事务，则抛出异常。
 *              7. PROPAGATION_NESTED: 如果当前存在事务，则在嵌套事务内执行。
 *              如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
 * @author: helon
 * date: 2019/5/18 10:39 AM
 * version: v1.0
 */
@Service
@Slf4j
public class TransactionDemoImpl implements TransactionDemo {
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    /** 
     * @Summary PROPAGATION_REQUIRED 场景一：此场景外围方法没有开启事务
     * @Description 在两个插入操作都执行结束抛出异常
     *              执行结果：成功。外围方法未开启事务，两个insert方法在自己的事务中独立运行，
     *              外围方法异常不影响内部两个insert方法独立的事务。
     * @Author helon 
     * @Date 2019/5/18 10:40 AM 
     * @Param [] 
     * @return void 
     **/
    @Override
    public void notransaction_exception_required_required() {
        City city = new City();
        city.setCountry("法国");
        city.setState("1");
        city.setName("haha1");
        cityService.insert(city);

        User user = new User();
        user.setName("haha1");
        userService.insert(user);
        throw new RuntimeException();
    }

    /** 
     * @Summary PROPAGATION_REQUIRED 场景一：此场景外围方法没有开启事务
     * @Description userService（独立事务）内部抛出异常
     *              执行结果：city成功，user回滚。外围方法未开启事务，两个insert方法在自己的事务中独立运行，
     *              所以userService方法抛出异常只会回滚插入user方法，插入city方法不会受影响。
     *
     * @Author helon 
     * @Date 2019/5/18 10:53 AM 
     * @Param [] 
     * @return void 
     **/
    @Override
    public void notransaction_exception_required_exception() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("wawa");
        cityService.insert(city);

        User user = new User();
        user.setName("wawa");
        userService.insertException(user);

    }

    /** 
     * @Summary PROPAGATION_REQUIRED 场景一结论：通过以上两个方法证明了在外围方法未开启事务的情况下
     *          Propagation.REQUIRED修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
     * @Description TODO 
     * @Author helon 
     * @Date 2019/5/18 11:09 AM 
     * @Param  
     * @return  
     **/

    /** 
     * @Summary  PROPAGATION_REQUIRED 场景二：外围方法开启事务，这个是使用率比较高的场景。
     * @Description 执行结果：全部回滚。外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。
     *
     * @Author helon 
     * @Date 2019/5/18 11:01 AM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("gaga");
        cityService.insert(city);

        User user = new User();
        user.setName("gaga");
        userService.insert(user);

        throw new RuntimeException();

    }

    /** 
     * @Summary  PROPAGATION_REQUIRED 场景二：外围方法开启事务，这个是使用率比较高的场景。
     * @Description 执行结果：全部回滚。外围方法开启事务，内部方法加入外围方法事务，
     *              内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     *
     * @Author helon 
     * @Date 2019/5/18 11:17 AM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional
    public void transaction_required_required_exception() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("heihei");
        cityService.insert(city);

        User user = new User();
        user.setName("heihei");
        userService.insertException(user);

    }

    /** 
     * @Summary  PROPAGATION_REQUIRED 场景二：外围方法开启事务，这个是使用率比较高的场景。
     * @Descriptionz  执行结果：
     *                外围方法开启事务，内部方法加入外围方法事务，
     *                内部方法抛出异常回滚，即使方法被catch不被外围方法感知，整个事务依然回滚
     * @Author helon 
     * @Date 2019/5/18 11:39 AM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional
    public void transaction_required_required_exception_try() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("huhuxixi1");
        cityService.insert(city);

        User user = new User();
        user.setName("huhuxixi1");

        try {
            userService.insertException(user);
        } catch (Exception e) {
            log.error("事务回滚");
        }
    }

    /** 
     * @Summary PROPAGATION_REQUIRED 场景二结论：以上场景二试验结果证明在外围方法开启事务的情况下，Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，
     *          所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
     * @Description TODO 
     * @Author helon 
     * @Date 2019/5/18 11:41 AM 
     * @Param  
     * @return  
     **/


    /** 
     * @Summary  PROPAGATION_REQUIRES_NEW 场景一：外围方法没有开启事务
     * @Description 执行结果：
     *              外围方法没有事务，插入city、user方法都在自己的事务中独立运行,
     *              外围方法抛出异常回滚不会影响内部方法
     * @Author helon 
     * @Date 2019/5/19 7:21 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    public void notransaction_exception_requiresNew_requiresNew() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("xixi");
        cityService.insertRequiresNew(city);

        User user = new User();
        user.setName("xixi");
        userService.insertRequiresNew(user);
    }

    /** 
     * @Summary  PROPAGATION_REQUIRES_NEW 场景一：外围方法没有开启事务
     * @Description 执行结果：外围方法没有开启事务，插入city方法和插入user方法分别开启自己的事务，
     *              插入user方法抛出异常回滚，其他事务不受影响
     * @Author helon 
     * @Date 2019/5/19 7:29 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    public void notransaction_requiresNew_requiresNew_exception() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("huohuo");
        cityService.insertRequiresNew(city);

        User user = new User();
        user.setName("huohuo");
        userService.insertRequiresNewException(user);
    }

    /** 
     * @Summary PROPAGATION_REQUIRES_NEW 场景二：外围方法开启事务
     * @Description 执行结果：
     *              外围方法开启事务，插入city方法和外围方法一个事务，
     *              插入user1方法、插入user2方法分别在独立的新建事务中，
     *              外围方法抛出异常只回滚和外围方法同一事务的方法，故插入city的方法回滚
     * @Author helon 
     * @Date 2019/5/19 7:40 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requiresNew() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("biubiu");
        cityService.insert(city);

        User user1 = new User();
        user1.setName("biubiu1");
        userService.insertRequiresNew(user1);

        User user2 = new User();
        user2.setName("biubiu2");
        userService.insertRequiresNew(user2);
        throw new RuntimeException();
    }

    /** 
     * @Summary PROPAGATION_REQUIRES_NEW 场景二：外围方法开启事务
     * @Description 执行结果：
     *              外围方法开启事务，插入city方法和外围方法一个事务，
     *              插入user1方法、插入user2方法分别在独立的新建事务中。
     *              插入user2方法抛出异常，首先插入user2方法的事务被回滚，
     *              异常继续抛出被外围方法感知，外围方法事务亦被回滚，故插入city方法也被回滚
     * @Author helon 
     * @Date 2019/5/19 8:40 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional
    public void transaction_required_requiresNew_requiresNew_exception() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("biubiu");
        cityService.insert(city);

        User user1 = new User();
        user1.setName("biubiu1");
        userService.insertRequiresNew(user1);

        User user2 = new User();
        user2.setName("biubiu2");
        userService.insertRequiresNewException(user2);
    }

    /** 
     * @Summary PROPAGATION_REQUIRES_NEW 场景二：外围方法开启事务
     * @Description 执行结果：
     *              外围方法开启事务，插入city方法和外围方法一个事务，
     *              插入user1方法、插入user2方法分别在独立的新建事务中。
     *              插入user2方法抛出异常，首先插入user2方法的事务被回滚，
     *              异常被catch不会被外围方法感知，外围方法事务不回滚，故插入city方法成功
     * @Author helon 
     * @Date 2019/5/19 8:52 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional
    public void transaction_required_requiresNew_requiresNew_exception_try() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("dudu");
        cityService.insert(city);

        User user1 = new User();
        user1.setName("dudu1");
        userService.insertRequiresNew(user1);

        User user2 = new User();
        user2.setName("dudu2");

        try {
            userService.insertRequiresNewException(user2);
        } catch (Exception e) {
            log.error("事务回滚");
        }
    }

    /** 
     * @Summary PROPAGATION_REQUIRES_NEW 场景二：外围方法开启事务
     * @Description 结论：
     *              在外围方法开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法依然会单独开启独立事务，
     *              且与外部方法事务也独立，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰
     * @Author helon 
     * @Date 2019/5/19 8:54 PM 
     * @Param  
     * @return  
     **/
    
    /**  
     * @Summary PROPAGATION_NESTED 场景一：此场景外围方法没有开启事务
     * @Description 执行结果：
     *              外围方法未开启事务，插入city、user方法在自己的事务中独立运行，
     *              外围方法异常不影响内部插入city、user方法独立的事务
     * @Author helon  
     * @Date 2019/5/19 9:15 PM  
     * @Param []  
     * @return void  
     **/
    @Override
    public void notransaction_exception_nested_nested() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("hello1");
        cityService.insertNested(city);

        User user1 = new User();
        user1.setName("hello1");
        userService.insertNested(user1);
        throw new RuntimeException();
    }

    /** 
     * @Summary PROPAGATION_NESTED 场景一：此场景外围方法没有开启事务
     * @Description 执行结果：
     *              外围方法没有事务，插入city、user方法都在自己的事务中独立运行,
     *              所以插入user方法抛出异常只会回滚插入user方法，插入city方法不受影响
     * @Author helon 
     * @Date 2019/5/19 9:20 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    public void notransaction_nested_nested_exception() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("hi");
        cityService.insertNested(city);

        User user1 = new User();
        user1.setName("hi");
        userService.insertNestedException(user1);
    }

    /** 
     * @Summary PROPAGATION_NESTED 场景一：此场景外围方法没有开启事务
     * @Description 结论：通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，
     *              修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰
     * @Author helon 
     * @Date 2019/5/19 9:23 PM 
     * @Param  
     * @return  
     **/


    /** 
     * @Summary PROPAGATION_NESTED 场景二：外围方法开启事务
     * @Description 执行结果：
     *              外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚
     * @Author helon 
     * @Date 2019/5/19 9:27 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional
    public void transaction_exception_nested_nested() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("nihao");
        cityService.insertNested(city);

        User user1 = new User();
        user1.setName("nihao");
        userService.insertNested(user1);

        throw new RuntimeException();
    }

    /** 
     * @Summary PROPAGATION_NESTED 场景二：外围方法开启事务
     * @Description 执行结果：外围方法开启事务，内部事务为外围事务的子事务，
     *              内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚
     * @Author helon 
     * @Date 2019/5/19 9:31 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional
    public void transaction_nested_nested_exception() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("sawadika");
        cityService.insertNested(city);

        User user1 = new User();
        user1.setName("sawadika");
        userService.insertNestedException(user1);
    }

    /** 
     * @Summary PROPAGATION_NESTED 场景二：外围方法开启事务
     * @Description 执行结果：
     *              外围方法开启事务，内部事务为外围事务的子事务，插入user内部方法抛出异常，可以单独对子事务回滚
     * @Author helon 
     * @Date 2019/5/19 9:36 PM 
     * @Param [] 
     * @return void 
     **/
    @Override
    @Transactional
    public void transaction_nested_nested_exception_try() {
        City city = new City();
        city.setCountry("某国");
        city.setState("某值");
        city.setName("one");
        cityService.insertNested(city);

        User user1 = new User();
        user1.setName("one");

        try {
            userService.insertNestedException(user1);
        } catch (Exception e) {
            log.error("事务回滚");
        }
    }

    /** 
     * @Summary PROPAGATION_NESTED 场景二：外围方法开启事务
     * @Description 结论：
     *              以上试验结果我们证明在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，
     *              外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
     * @Author helon 
     * @Date 2019/5/19 9:37 PM 
     * @Param  
     * @return  
     **/
}
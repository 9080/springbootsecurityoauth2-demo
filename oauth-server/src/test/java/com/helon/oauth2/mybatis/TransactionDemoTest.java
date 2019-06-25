package com.helon.oauth2.mybatis;

import com.helon.oauth2.server.TransactionDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @className: TransactionDemoTest
 * @summary: 事务场景测试
 * @Description: TODO
 * @author: helon
 * date: 2019/5/18 10:43 AM
 * version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TransactionDemoTest {
    @Autowired
    private TransactionDemo transactionDemo;

    /** 
     * @Summary 此场景外围方法没有开启事务
     * @Description 测试
     * @Author helon 
     * @Date 2019/5/18 10:40 AM 
     * @Param [] 
     * @return void 
     **/
    @Test
    public void notransaction_exception_required_required_Test() {
        transactionDemo.notransaction_exception_required_required();
    }

    @Test
    public void notransaction_exception_required_exception_Test() {
        transactionDemo.notransaction_exception_required_exception();
    }

    @Test
    public void transaction_exception_required_required_Test() {
        transactionDemo.transaction_exception_required_required();
    }

    @Test
    public void transaction_required_required_exception_Test() {
        transactionDemo.transaction_required_required_exception();
    }

    @Test
    public void transaction_required_required_exception_try_Test() {
        transactionDemo.transaction_required_required_exception_try();
    }

    @Test
    public void notransaction_exception_requiresNew_requiresNew_Test() {
        transactionDemo.notransaction_exception_requiresNew_requiresNew();
    }

    @Test
    public void notransaction_requiresNew_requiresNew_exception_Test() {
        transactionDemo.notransaction_requiresNew_requiresNew_exception();
    }
    @Test
    public void transaction_exception_required_requiresNew_requiresNew_Test() {
        transactionDemo.transaction_exception_required_requiresNew_requiresNew();
    }

    @Test
    public void transaction_required_requiresNew_requiresNew_exception_Test() {
        transactionDemo.transaction_required_requiresNew_requiresNew_exception();
    }

    @Test
    public void transaction_required_requiresNew_requiresNew_exception_try_Test() {
        transactionDemo.transaction_required_requiresNew_requiresNew_exception_try();
    }

    @Test
    public void notransaction_exception_nested_nested_Test() {
        transactionDemo.notransaction_exception_nested_nested();
    }

    @Test
    public void notransaction_nested_nested_exception_Test() {
        transactionDemo.notransaction_nested_nested_exception();
    }

    @Test
    public void transaction_exception_nested_nested_Test() {
        transactionDemo.transaction_exception_nested_nested();
    }
    @Test
    public void transaction_nested_nested_exception_Test() {
        transactionDemo.transaction_nested_nested_exception();

    }
    @Test
    public void transaction_nested_nested_exception_try_Test() {
        transactionDemo.transaction_nested_nested_exception_try();
    }
}
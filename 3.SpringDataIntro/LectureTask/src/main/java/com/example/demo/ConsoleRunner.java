package com.example.demo;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import com.example.demo.services.AccountService;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;

@Service
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();

        user.setUserName("Pesho");
        user.setAge(20);

        Account account = new Account();
        account.setBalance(new BigDecimal(25000));
        account.setUser(user);

        user.setAccounts(new LinkedList<>() {{
            add(account);
        }
        });
        userService.registerUser(user);

        accountService.withdrawMoney(new BigDecimal(25000), account.getId());
        accountService.transferMoney(new BigDecimal(30000), account.getId());
    }
}

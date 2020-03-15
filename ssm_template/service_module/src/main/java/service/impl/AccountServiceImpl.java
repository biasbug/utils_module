package service.impl;

import dao.AccountDao;
import domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public void transfromAccount(String fromName, String toName, double money) {
        Account fromAccount = accountDao.findAccount(fromName);
        Account toAccount = accountDao.findAccount(toName);

        fromAccount.setMoney(fromAccount.getMoney() - money);
        toAccount.setMoney(toAccount.getMoney() + money);

        accountDao.updateAccount(fromAccount);
        accountDao.updateAccount(toAccount);
    }
}

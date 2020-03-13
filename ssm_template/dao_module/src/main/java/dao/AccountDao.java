package dao;

import domain.Account;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountDao {

    @Select("select * from myaccount where name = #{name}")
    public Account findAccount(String name);

    @Update("update myaccount set money = #{money} where id = #{id} and name = #{name}")
    public void updateAccount(Account account);
}

package dao;

import domain.UserBean;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from usertable where username = #{param1} and password = #{param2}")
    public UserBean findUser(String username,String password);
}

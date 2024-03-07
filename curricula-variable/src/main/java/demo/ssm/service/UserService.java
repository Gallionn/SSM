package demo.ssm.service;

import demo.ssm.pojo.User;

public interface UserService {
    public String index_get(String uId,String uPassword);
    public String user_update(User user);
}

package demo.ssm.service.impl;

import demo.ssm.mapper.UserMapper;
import demo.ssm.pojo.User;
import demo.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public String index_get(String uId,String uPassword)
    {
        User user = userMapper.selectByPrimaryKey(uId);
        if(user == null || !user.getuPassword().equals(uPassword))
        return null;
        return user.getuFlag();
    }

    public String user_update(User user) {
        System.out.println(user.getuId());
        System.out.println(user.getuFlag());
        System.out.println(user.getuPassword());
        userMapper.updateByPrimaryKeySelective(user);
        return null;
    }
}

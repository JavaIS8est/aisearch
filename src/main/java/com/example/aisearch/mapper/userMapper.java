package com.example.aisearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aisearch.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Mapper
@Repository
public interface userMapper extends BaseMapper<User> {
    @Select("SELECT user.* from user "+
    "where user.login_name =#{userName} "
    )
    User getUserByLoginName(String userName);

}

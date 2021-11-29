package com.hackday.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hackday.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author i531869
 * @Date 11/26/21 6:02 PM
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

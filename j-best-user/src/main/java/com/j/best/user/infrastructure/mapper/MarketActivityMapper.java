package com.j.best.user.infrastructure.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketActivityMapper {


    @Insert(value = "INSERT INTO `act`.`market_activity_apply`(`id`, `activity_id`, `apply_user_id`, `apply_user_type`) VALUES (#{id}, #{activityId}, #{applyUserId}, #{applyUserType})")
    Long save(@Param("id") Integer id,@Param("activityId") Integer activityId,@Param("applyUserId") Integer applyUserId,@Param("applyUserType") String applyUserType);
}

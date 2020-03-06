package com.atguigu.guli.service.ucenter.service;

import com.atguigu.guli.service.ucenter.entity.Member;
import com.atguigu.guli.service.ucenter.entity.vo.LoginInfoVo;
import com.atguigu.guli.service.ucenter.entity.vo.LoginVo;
import com.atguigu.guli.service.ucenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author ZHX
 * @since 2020-03-04
 */
public interface MemberService extends IService<Member> {

    Integer countRegisterByDay(String day);

    void register(RegisterVo registerVo);

    /**
     * 用户登录
     * @param loginVo
     * @return token
     */
    String login(LoginVo loginVo);

    /**
     * 根据token获取会员登录信息
     * @param jwtToken
     * @return 用户登录信息
     */
    LoginInfoVo getLoginInfoByJwtToken(String jwtToken);
}

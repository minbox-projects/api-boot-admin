package org.minbox.framework.api.boot.admin.service;

import com.alibaba.fastjson.JSON;
import com.gitee.hengboy.mybatis.enhance.dsl.serach.Searchable;
import com.gitee.hengboy.mybatis.enhance.dsl.update.filter.SetFilter;
import com.gitee.hengboy.mybatis.enhance.sort.SortEnum;
import org.minbox.framework.api.boot.admin.api.request.SelectSystemUserByParamRequest;
import org.minbox.framework.api.boot.admin.common.constants.Constants;
import org.minbox.framework.api.boot.admin.common.enums.ResponseCode;
import org.minbox.framework.api.boot.admin.common.enums.Status;
import org.minbox.framework.api.boot.admin.common.exception.LogicException;
import org.minbox.framework.api.boot.admin.entity.SystemUser;
import org.minbox.framework.api.boot.admin.entity.dsl.DSystemUser;
import org.minbox.framework.api.boot.admin.service.base.BaseService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 系统用户业务逻辑
 *
 * @author 恒宇少年
 */
@Service
public class SystemUserService extends BaseService<SystemUser, String> {
    /**
     * 更新用户状态
     *
     * @param userIds {@link SystemUser#getUserId()}用户编号集合
     * @param status  {@link SystemUser#getStatus()}
     */
    public void updateUserStatus(List<String> userIds, Status status) {
        DSystemUser dSystemUser = DSystemUser.DSL();
        dslFactory.createUpdateable()
                .update(dSystemUser)
                .set(SetFilter.set(dSystemUser.status, status.getValue()))
                .where(dSystemUser.userId.in(userIds))
                .execute();

        globalLogging.debug("列表内的用户：{}，状态已经更新为：{}", JSON.toJSONString(userIds), status.getValue());
    }

    /**
     * 根据查询条件分页查询用户列表
     *
     * @param request {@link SelectSystemUserByParamRequest}
     * @return {@link SystemUser}
     */
    public List<SystemUser> findByParams(SelectSystemUserByParamRequest request) {
        DSystemUser dSystemUser = DSystemUser.DSL();
        Searchable searchable = dslFactory.createSearchable()
                .selectFrom(dSystemUser)
                .orderBy(dSystemUser.createTime, SortEnum.DESC);

        // 如果传递了用户名查询条件，使用like语法模糊查询
        // 生成SQL示例：su_username like '%xxxx%'
        if (!ObjectUtils.isEmpty(request.getUsername())) {
            searchable.and(dSystemUser.username.like(Constants.PERCENT_SIGN, request.getUsername(), Constants.PERCENT_SIGN));
        }

        // 如果传递了用户状态查询条件，进行过滤用户状态列表
        // 生成SQL示例：su_status = 1
        if (!ObjectUtils.isEmpty(request.getStatus())) {
            searchable.and(dSystemUser.status.eq(request.getStatus()));
        }
        // 如果并未传递用户状态查询条件，则使用默认的方式
        // 除了已删除的其他状态都查询
        else {
            searchable.and(dSystemUser.status.ne(Status.DELETE.getValue()));
        }

        return searchable.resultType(SystemUser.class).fetch();
    }

    /**
     * 添加系统用户
     *
     * @param user {@link SystemUser}
     * @return {@link SystemUser#getUserId()}
     * @throws LogicException
     */
    public String addUser(SystemUser user) throws LogicException {
        SystemUser systemUser = findByUsername(user.getUsername());
        if (!ObjectUtils.isEmpty(systemUser) && !Status.DELETE.getValue().equals(systemUser.getStatus())) {
            throw new LogicException(ResponseCode.USER_ALREADY_CREATED, user.getUsername());
        }
        // 加密密码
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        mapper.insert(user);
        globalLogging.debug("用户：{}，添加完成，分配的编号为：{}", user.getUsername(), user.getUserId());
        return user.getUserId();
    }

    /**
     * 查询系统用户并检查用户有效性
     *
     * @param username {@link SystemUser#getUsername()}
     * @return {@link SystemUser}
     * @throws LogicException
     */
    public SystemUser findByUsernameAndCheck(String username) throws LogicException {
        SystemUser systemUser = findByUsername(username);
        if (ObjectUtils.isEmpty(systemUser)) {
            throw new LogicException(ResponseCode.USER_NOT_FOUND, username);
        }
        if (!Status.ENABLE.getValue().equals(systemUser.getStatus())) {
            throw new LogicException(ResponseCode.USER_STATUS_ABNORMAL, username);
        }
        return systemUser;
    }

    /**
     * 查询系统用户信息
     *
     * @param username {@link SystemUser#getUsername()}
     * @return {@link SystemUser}
     */
    public SystemUser findByUsername(String username) {
        DSystemUser dSystemUser = DSystemUser.DSL();
        SystemUser systemUser = dslFactory.createSearchable()
                .selectFrom(dSystemUser)
                .where(dSystemUser.username.eq(username))
                .resultType(SystemUser.class)
                .fetchOne();
        return systemUser;
    }
}

import request from '@/utils/request'

/**
 * 分页查询系统用户列表
 * @param queryParam
 * @returns {AxiosPromise}
 */
export function getUserList(queryParam) {
    return request({
        url: "/system/user/filters/pageable",
        method: 'get',
        params: {
            'page': queryParam.page,
            'size': queryParam.limit,
            'username': queryParam.username,
            'status': queryParam.status
        }
    });
}

/**
 * 禁用用户
 * @param ids 用户编号集合
 * @returns {AxiosPromise}
 */
export function disableUser(ids) {
    return request({
        url: '/system/user/actions/disable',
        method: 'post',
        data: {'userIds': Array.of(ids)}
    });
}

/**
 * 启用用户
 * @param ids 用户编号集合
 * @returns {AxiosPromise}
 */
export function enableUser(ids) {
    return request({
        url: '/system/user/actions/enable',
        method: 'post',
        data: {'userIds': Array.of(ids)}
    });
}

/**
 * 删除系统用户
 * @param ids 用户编号集合
 * @returns {AxiosPromise}
 */
export function removeUser(ids) {
    return request({
        url: '/system/user',
        method: 'delete',
        data: {'userIds': Array.of(ids)}
    });
}

/**
 * 创建用户
 * @param user
 * @returns {AxiosPromise}
 */
export function createUser(user) {
    return request({
        url: '/system/user',
        method: 'post',
        data: user
    });
}
import request from '@/utils/request'
import axios from 'axios'
import {Message} from "element-ui";

export function login(data) {
    return axios.post('/oauth/token?grant_type=password&username=' + data.username + '&password=' + data.password, null, {
        auth: {
            "username": "api-boot-admin",
            "password": '123456'
        }
    }).catch(function (error) {
        Message({
            message: '登录遇到异常，请检查用户名密码.',
            type: 'error',
            duration: 5 * 1000
        })
    });
}

export function getInfo(token) {
    if (token != undefined) {
        return request({
            url: '/system/user',
            method: 'get'
        });
    } else {
        Message({
            message: '请求令牌获取失败，请重新登录.',
            type: 'error',
            duration: 5 * 1000
        })
    }
}

export function logout() {
    return request({
        url: '/user/logout',
        method: 'post'
    })
}

/*
 * Copyright [2019] [恒宇少年 - 于起宇]
 *
 *        Licensed under the Apache License, Version 2.0 (the "License");
 *        you may not use this file except in compliance with the License.
 *        You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 *        Unless required by applicable law or agreed to in writing, software
 *        distributed under the License is distributed on an "AS IS" BASIS,
 *        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *        See the License for the specific language governing permissions and
 *        limitations under the License.
 *
 */

package org.minbox.framework.api.boot.admin.common.model;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页请求对象
 * 分页参数，当前页码：{@link #getPage()}
 * 分页参数，每页条数：{@link #getSize()}
 *
 * @author：恒宇少年 - 于起宇
 */
@Data
public class PageableRequest {
    /**
     * 默认当前页码
     */
    static final Integer DEFAULT_PAGE = 1;
    /**
     * 默认每页条数
     */
    static final Integer DEFAULT_SIZE = 20;

    @Min(1)
    private Integer page = DEFAULT_PAGE;

    @Min(1)
    private Integer size = DEFAULT_SIZE;


    /**
     * 提供默认的分页对象实例
     *
     * @return {@link PageableRequest}
     */
    public static PageableRequest defaultInstance() {
        return new PageableRequest();
    }

    /**
     * 提供可设置当前页码参数实例化方式
     *
     * @param page {@link PageableRequest#getPage()}
     * @return {@link PageableRequest}
     */
    public static PageableRequest instance(Integer page) {
        PageableRequest pageableRequest = defaultInstance();
        pageableRequest.setPage(page);
        return pageableRequest;
    }

    /**
     * 提供可设置当前页码，每页条数实例化方式
     *
     * @param page {@link PageableRequest#getPage()}
     * @param size {@link PageableRequest#getSize()}
     * @return {@link PageableRequest}
     */
    public static PageableRequest instance(Integer page, Integer size) {
        PageableRequest pageableRequest = instance(page);
        pageableRequest.setSize(size);
        return pageableRequest;
    }
}

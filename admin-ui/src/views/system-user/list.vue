<template>
    <div class="app-container">
        <div class="filter-container">
            <!--查询条件：设备唯一码-->
            <el-input v-model="listQuery.username" placeholder="请输入用户名" clearable style="width: 260px;"
                      class="filter-item"/>

            <el-select v-model="listQuery.status" placeholder="请选择用户状态" clearable class="filter-item"
                       style="width: 200px">
                <el-option v-for="s in status" :label="s.label" :value="s.value"/>
            </el-select>


            <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
                查询
            </el-button>
            <el-button type="primary" class="filter-item" icon="el-icon-edit" @click="handleCreate">
                添加用户
            </el-button>
        </div>

        <el-table :key="tableKey" v-loading="listLoading" :data="list" border fit highlight-current-row
                  style="width: 100%;">
            <el-table-column label="用户编号" prop="userId" align="center"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center" width="150"></el-table-column>
            <el-table-column label="邮箱地址" prop="email" align="center"></el-table-column>
            <el-table-column label="家庭地址" prop="address" align="center"></el-table-column>
            <el-table-column label="添加时间" width="240px" align="center">
                <template slot-scope="{row}">
                    <span>{{ row.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="用户状态" prop="type" align="center" width="100px">
                <template slot-scope="{row}">
                    <div class="status normal-status" v-if="row.status==1">
                        正常
                    </div>
                    <div class="status disable-status" v-if="row.status==0">
                        已禁用
                    </div>
                </template>

            </el-table-column>

            <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
                <template slot-scope="{row}">
                    <el-button size="mini" type="warning" v-if="row.status==1"
                               @click="disableUser(row.userId,row.username)">
                        禁用
                    </el-button>
                    <el-button size="mini" type="primary" v-if="row.status==0"
                               @click="enableUser(row.userId,row.username)">
                        启用
                    </el-button>
                    <el-button size="mini" type="danger" v-if="row.status==0"
                               @click="removeUser(row.userId,row.username)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
                    @pagination="getList"/>

        <el-dialog title="添加用户" :visible.sync="dialogFormVisible">
            <el-form ref="newUser" :model="newUser" :rules="rules">
                <el-form-item label="用户名" :label-width="formLabelWidth" required prop="username">
                    <el-input v-model="newUser.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                    <el-input v-model="newUser.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
                    <el-input v-model="newUser.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="家庭住址" :label-width="formLabelWidth" prop="address">
                    <el-input v-model="newUser.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="头像地址" :label-width="formLabelWidth" prop="avatar">
                    <el-input v-model="newUser.avatar" autocomplete="off"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelCreateUser">取 消</el-button>
                <el-button v-loading="loading" type="primary" @click="submitCreateUser">确 定</el-button>
            </div>
        </el-dialog>
    </div>


</template>

<script>
    import waves from '@/directive/waves' // waves directive
    import Pagination from '@/components/Pagination' // secondary package based on el-pagination
    import {getUserList, disableUser, enableUser, removeUser, createUser} from '@/api/system-user'

    const defaultUserModel = {
        username: '',
        email: '',
        address: '',
        age: 18,
        avatar: '/images/advatar.jpeg',
        password: '123456'
    };
    export default {
        name: 'SystemUserList',
        components: {Pagination},
        directives: {waves},
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '120px',
                tableKey: 0,
                list: null,
                total: 0,
                listLoading: false,
                loading: false,
                newUser: Object.assign({}, defaultUserModel),
                // 查询条件
                listQuery: {
                    // 页码
                    page: 1,
                    // 每页条数
                    limit: 20,
                    // 用户名
                    username: undefined,
                    // 状态
                    status: undefined
                },
                status: [{label: '正常', value: '1'}, {label: '已禁用', value: '0'}],
                rules: {
                    username: [
                        {required: true, message: '请输入用户名'}
                    ],
                    password: [
                        {required: true, message: '请输入密码'}
                    ],
                    email: [
                        {required: true, message: '请输入邮箱'}
                    ],
                    address: [
                        {required: true, message: '请输入家庭住址'}
                    ],
                    avatar: [
                        {required: true, message: '请输入头像地址'}
                    ]
                }
            }
        },
        created() {
            // 页面初始化后执行查询第一页用户
            this.getList()
        },
        methods: {
            getList() {
                this.listLoading = true
                getUserList(this.listQuery).then(response => {
                    let res = response.data;
                    this.list = res.data;
                    this.total = res.totalElements;
                    this.listLoading = false
                });
            },
            handleFilter() {
                this.listQuery.page = 1
                this.getList()
            },
            handleCreate() {
                this.dialogFormVisible = true;
            },
            // 重置添加用户的表单
            resetCreateUserForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 取消添加用户，重置输入内容
            cancelCreateUser() {
                this.resetCreateUserForm('newUser');
                this.dialogFormVisible = false;
            },
            // 提交创建用户
            submitCreateUser() {
                this.$refs.newUser.validate(valid => {
                    if (valid) {
                        this.loading = true;
                        createUser(this.newUser).then(response => {
                            this.$notify({
                                title: '成功',
                                message: '系统用户添加成功',
                                type: 'success',
                                duration: 2000
                            });
                            this.dialogFormVisible = false;
                            this.loading = false;
                            this.resetCreateUserForm('newUser');
                            this.handleFilter();
                        }).catch(error => {
                            this.loading = false;
                            reject(error);
                        });

                    } else {
                        return false;
                    }
                });
            },
            disableUser(userId, username) {
                this.$confirm('此操作会禁用用户的正常使用，是否继续？', '提示', {
                    type: "warning"
                }).then(() => {
                    this.listLoading = true;
                    disableUser(userId).then(response => {
                        this.$notify({
                            title: '成功',
                            message: '用户【' + username + '】禁用成功',
                            type: 'success',
                            duration: 2000
                        });
                        this.listLoading = false;
                        this.handleFilter();
                    }).catch(() => {
                        this.listLoading = false;
                    });
                });
            },
            enableUser(userId, username) {
                this.$confirm('此操作会启用用户恢复使用，是否继续？', '提示', {
                    type: "warning"
                }).then(() => {
                    this.listLoading = true;
                    enableUser(userId).then(response => {
                        this.$notify({
                            title: '提示',
                            message: '用户【' + username + '】启用成功',
                            type: "success",
                            duration: 2000
                        });
                        this.listLoading = false;
                        this.handleFilter();
                    }).catch(() => {
                        this.listLoading = false;
                    });
                });
            },
            removeUser(userId, username) {
                this.$confirm('此操作会将用户删除，是否继续？', '提示', {
                    type: "error"
                }).then(() => {
                    this.listLoading = true;
                    removeUser(userId).then(() => {
                        this.$notify({
                            title: '成功',
                            message: '用户【' + username + '】删除成功',
                            type: 'success',
                            duration: 2000
                        });
                        this.listLoading = false;
                        this.handleFilter();
                    }).catch(() => {
                        this.listLoading = false;
                    });
                });

            }
        }
    }
</script>
<style>
    .status {
        padding: 3px 5px;
        font-size: 13px;
        font-weight: 400;
        border-radius: 3px;
        color: #fff;
    }

    .normal-status {
        background-color: #13ce66;
        border-color: #13ce66;
    }

    .delete-status {
        background-color: #F56C6C;
        border-color: #F56C6C;
    }

    .disable-status {
        background-color: #909399;
        border-color: #909399;
    }
</style>

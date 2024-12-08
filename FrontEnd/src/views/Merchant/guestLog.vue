<template>
    <div>
        <el-container style="height: 100%; ">
            <el-header style="display: flex; align-items:center; justify-content: center; background-color:#4f7dbd52;">
                <i class="el-icon-shopping-cart-2" style="font-size: 4.5rem; color: #222; margin-right: 22%;"></i>
                <span style="font-size: 50px; font-family:'Courier New', Courier, monospace; margin-right: 22%;">在 线 商
                    城</span>
                <el-popover placement="top" width="160" v-model="popoverVisible">
                    <p style="text-align: center;">确定退出登录？</p>
                    <div style="text-align: center; margin: 0">
                        <el-button size="mini" type="text" @click="popoverVisible = false">取 消</el-button>
                        <el-button type="primary" size="mini" @click="dialogFormVisible = true">确 定</el-button>
                    </div>
                    <el-button icon="el-icon-user" style="background-color:azure;" slot="reference">
                        <span v-text="userName"></span>
                    </el-button>
                </el-popover>
                <el-dialog title="登 录" :visible.sync="dialogFormVisible"
                    style="left: 25%;top: 10%;text-align: center; width: 800px;" :show-close="false"
                    :close-on-click-modal="false">
                    <el-form>
                        <el-form-item label="账 号" label-width=50px>
                            <el-input v-model="signIn.account" autocomplete="off"
                                style="width: 300px; text-align-last: center;"></el-input>
                        </el-form-item>
                        <el-form-item label="密 码" label-width=50px>
                            <el-input v-model="signIn.password" autocomplete="off" show-password
                                style="width: 300px; text-align-last: center;"></el-input>
                        </el-form-item>
                        <el-select v-model="signIn.role" placeholder="选择身份" style="text-align-last: center;">
                            <el-option label="商 家" value="merchant">
                                商 家
                            </el-option>
                            <el-option label="顾 客" value="guest">
                                顾 客
                            </el-option>
                        </el-select>
                    </el-form>
                    <div slot="footer" style="text-align: center;">
                        <el-button type="primary" @click="dialogFormVisible = false; submitRegistry()">确 定</el-button>
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="warning" @click="dialogFormVisible = false; deleteRegistry()">注 销</el-button>
                    </div>
                </el-dialog>
                <i class="el-icon-s-custom" style="margin-left: 2%;"></i>
            </el-header>
            <el-container>
                <el-aside width="230px" style="background-color:#4f7dbd82; height: 100%;">
                    <el-menu :default-openeds="['1', '2']" style="background-color:#4f7dbd82;">
                        <el-submenu index="1">
                            <template slot="title"><i class="el-icon-message"></i>商家特权</template>
                            <el-menu-item-group style="background-color: #4f7dbd79;">
                                <template slot="title"><span style="color: #222;">订单</span></template>
                                <el-menu-item index="1-1">
                                    <router-link to="/orderManagement" style="color: black; text-decoration: none;">
                                        订单管理
                                    </router-link>
                                </el-menu-item>
                                <el-menu-item index="1-2">
                                    <router-link to="/statisticalForm" style="color: black; text-decoration: none;">
                                        统计报表
                                    </router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group style="background-color: #4f7dbd79;">
                                <template slot="title"><span style="color: #222;">客户</span></template>
                                <el-menu-item index="1-3">
                                    <router-link to="/guestManagement" style="color: black; text-decoration: none;">
                                        客户管理
                                    </router-link>
                                </el-menu-item>
                                <el-menu-item index="1-4">
                                    <router-link to="/guestLog" style="color: black; text-decoration: none;">
                                        客户日志
                                    </router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group style="background-color: #4f7dbd79;">
                                <template slot="title"><span style="color: #222;">商品</span></template>
                                <el-menu-item index="1-5">
                                    <el-button type="text" @click="controlCommodityVisible = true;"
                                        style="color: black;">
                                        商品目录
                                    </el-button>

                                    <el-dialog title="商品目录管理" :visible.sync="controlCommodityVisible"
                                        style="text-align-last: center;left: 25%;top: 10%;width: 800px;">
                                        <el-button type="primary" @click="addItemVisible = true">添加<i
                                                class="el-icon-upload el-icon--right"></i></el-button>
                                        <el-button type="primary" @click="deleteItemVisible = true">删除<i
                                                class="el-icon-delete el-icon--right"></i></el-button>
                                        <el-button type="primary" @click="editItemVisible = true">修改<i
                                                class="el-icon-edit el-icon--right"></i></el-button>
                                    </el-dialog>

                                    <el-dialog title="添加商品" :visible.sync="addItemVisible"
                                        style="text-align-last: center;">
                                        品名&ensp;&ensp;<el-input v-model="itemInfo.name" style="width: 400px;">
                                        </el-input><br>
                                        价格&ensp;&ensp;<el-input v-model="itemInfo.price" style="width: 400px;">
                                        </el-input><br>
                                        地址&ensp;&ensp;<el-input v-model="itemInfo.addr" style="width: 400px;">
                                        </el-input><br>
                                        时间&ensp;&ensp;<el-input v-model="itemInfo.time" style="width: 400px;">
                                        </el-input><br><br>
                                        <el-upload action drag multiple ref="upload" :auto-upload="false"
                                            :on-change="fileChange">
                                            <i class="el-icon-upload"></i>
                                            <div class="el-upload__text">将商品图片拖到此处，或<em>点击上传</em></div>
                                            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                                        </el-upload>
                                        <el-button type="primary" @click="submitItem('add'); addItemVisible = false">
                                            确认
                                        </el-button>
                                    </el-dialog>

                                    <el-dialog title="修改商品" :visible.sync="editItemVisible"
                                        style="text-align-last: center;">
                                        原名称&ensp;&ensp;<el-input v-model="itemInfo.pastname" style="width: 400px;">
                                        </el-input><br>
                                        修改名称&ensp;&ensp;<el-input v-model="itemInfo.name" style="width: 400px;">
                                        </el-input><br>
                                        修改价格&ensp;&ensp;<el-input v-model="itemInfo.price" style="width: 400px;">
                                        </el-input><br>
                                        修改地址&ensp;&ensp;<el-input v-model="itemInfo.addr" style="width: 400px;">
                                        </el-input><br>
                                        修改时间&ensp;&ensp;<el-input v-model="itemInfo.time" style="width: 400px;">
                                        </el-input><br><br>
                                        <el-upload action drag multiple ref="upload" :auto-upload="false"
                                            :on-change="fileChange">
                                            <i class="el-icon-upload"></i>
                                            <div class="el-upload__text">将商品图片拖到此处，或<em>点击上传</em></div>
                                            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                                        </el-upload>
                                        <el-button type="primary" @click="submitItem('edit'); editItemVisible = false">
                                            确认
                                        </el-button>
                                    </el-dialog>

                                    <el-dialog title="删除商品" :visible.sync="deleteItemVisible"
                                        style="text-align-last: center;">
                                        商品名称&ensp;&ensp;<el-input v-model="itemInfo.name" style="width: 400px;">
                                        </el-input><br><br>
                                        <el-button type="primary"
                                            @click="submitItem('delete'); deleteItemVisible = false">
                                            确认
                                        </el-button>
                                    </el-dialog>

                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main style="background-color: #4f7dbd65;">
                    <!--表单-->
                    <el-form :inline="true" :model="searchForm" size="mini" style="text-align: center;">
                        <el-form-item>
                            <template slot="label"><span style="color: #222;">客户日志</span></template>
                            <el-select v-model="searchForm.watchOrBuy" placeholder="浏览/购买">
                                <el-option label="浏览记录" value="watch"></el-option>
                                <el-option label="购买记录" value="buy"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit(); displayTable()">查询</el-button>
                        </el-form-item>
                    </el-form>
                    <!--表格-->
                    <el-table :data="orderData" v-if="orderData.length > 0" style="margin-top: 20px; color:#222;"
                        :header-cell-style="{ backgroundColor: '#4f7dbd85', color: '#222' }"
                        :cell-style="{ backgroundColor: '#4f7dbd68', color: '#222' }">
                        <el-table-column v-for="(header, index) in tableHeaders" :key="index" :prop="tableFormat[index]"
                            :label="header">
                        </el-table-column>
                    </el-table>
                    <br><br><br><br>
                    <div id="pagination">
                        <!--分页条  每次点击都会触发一次change，并且将对应的值传递给函数-->
                        <el-pagination background layout="prev, pager, next, jumper"
                            @current-change="handleCurrentChange" :total="1000">
                        </el-pagination>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import axios from 'axios';
// axios.defaults.withCredentials = true;


export default {
    data() {
        return {
            userName: "",
            orderData: [],
            searchForm: {
                watchOrBuy: ""
            },
            dialogFormVisible: false,
            popoverVisible: false,
            signIn: {
                account: "",
                password: "",
                role: ""
            },
            tableHeaders: [],
            tableFormat: [],
            controlCommodityVisible: false,
            addItemVisible: false,
            deleteItemVisible: false,
            editItemVisible: false,
            pictureFile: {},
            itemInfo: {
                name: "",
                price: "",
                addr: "",
                status: ""
            },

        }
    },
    methods: {
        onSubmit: function () {
            axios.post("http://113.44.84.103:8080/orderManagement",
                this.searchForm).then(result => {
                    this.orderData = result.data.data;
                })
        },
        submitRegistry: function () {
            axios.post("http://113.44.84.103:8080/changeAccount", this.signIn).
                then((result) => {
                    if (result.data.status == 1) this.userName = result.data.userName;
                    else alert("该用户不存在")
                    if (this.signIn.role == "guest" && result.data.status == 1) {
                        location.replace("http://113.44.84.103:7000/#/guest");
                    }
                })

            // document.getElementById("userName").innerHTML = this.userName;
        },
        handleCurrentChange: function (val) {
            // alert("页码发生变化" + val);//console.log的才是逗号分隔，alert是+号
            this.searchForm['user'] = this.userName;
            this.searchForm['page'] = val;
            axios.post("http://113.44.84.103:8080/orderManagement", this.searchForm).then(result => {
                this.orderData = result.data.data;
            });
        },
        displayTable() {
            // 根据查询条件设置表头和表格数据
            if (this.searchForm.watchOrBuy === 'watch') {
                this.tableHeaders = ['浏览日期', '客户名', '商品名'];
                this.tableFormat = ['watchday', 'gname', 'pname'];
            } else {
                this.tableHeaders = ['购买日期', '客户名', '商品名', "数量", "金额￥"];
                this.tableFormat = ['orderday', 'gname', 'pname', "nums", "amount"];
            }
        },
        fileChange(file) {
            const typeArr = ['image/png', 'image/jpeg', 'image/jpg'];
            const isJPG = typeArr.indexOf(file.raw.type) !== -1;
            // image/png, image/jpeg, image/jpg
            const isLt3M = file.size / 1024 / 1024 < 3;

            if (!isJPG) {
                this.$message.error('只能是图片!');
                this.$refs.upload.clearFiles();
                this.pictureFile = null;
                return;
            }
            if (!isLt3M) {
                this.$message.error('上传图片大小不能超过 3MB!');
                this.$refs.upload.clearFiles();
                this.pictureFile = null;
                return;
            }
            this.pictureFile = file.raw;
            console.log(file);
        },
        submitItem(status) {
            this.itemInfo.status = status;
            const formData = new FormData();
            Object.keys(this.itemInfo).forEach((ele) => {
                formData.append(ele, this.itemInfo[ele]);
            });
            if (this.itemInfo.status != 'delete')
            formData.append('file', this.pictureFile);
            axios.post("http://113.44.84.103:8080/changeCommodity", formData).then(() => { alert("操作成功！") })
            this.pictureFile = {};
            this.itemInfo = {};
            this.$refs.upload.clearFiles();
        },
        deleteRegistry() {
            axios.get("http://113.44.84.103:8080/deleteUser", {
                params: {
                    userName: this.userName,//请求参数
                },
            }).then(
                () => {
                    alert("注销成功！")
                    location.replace("http://113.44.84.103:7000/#/")
                }
            )
        }
    },
    mounted() {
        this.searchForm['page'] = 1;
        this.searchForm['forWhat'] = 'guestLog';
        axios.post("http://113.44.84.103:8080/orderManagement", this.searchForm).then(result => {
            this.orderData = result.data.data;
        });
        axios.get("http://113.44.84.103:8080/username").then((result) => this.userName = result.data.userName);
    }

}
</script>

<style>
#pagination {
    /* 根据需要调整上下间距 */
    display: flex;
    /* 水平居中 */
    justify-content: center;

    margin: 20px 0;
}
</style>

<!--此页面默认展示订单管理页-->
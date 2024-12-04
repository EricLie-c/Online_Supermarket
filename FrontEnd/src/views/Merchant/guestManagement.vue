<template>
    <div>
        <el-container style="height: 100%; ">
            <el-header style="display: flex; align-items:center; justify-content: center; background-color:#4f7dbd82;">
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
                <el-aside width="230px" style="background-color:#4f7dbd79; height: 100%; border: 1px solid #eee;">
                    <el-menu :default-openeds="['1', '2']" style="background-color:#4f7dbd79;">
                        <el-submenu index="1">
                            <template slot="title"><i class="el-icon-message" style="color: #222;"></i>商家特权</template>
                            <el-menu-item-group style="background-color:#4f7dbd79;">
                                <template slot="title"><span style="color:#222;">订单</span></template>
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
                                <template slot="title"><span style="color:#222;">客户</span></template>
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
                                <template slot="title"><span style="color:#222;">商品</span></template>
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
                <el-main style="background-color: #4f7dbd92;">
                    <!--表格-->
                    <el-table :data="orderData" style="margin-top: 20px; color:#222;"
                        :header-cell-style="{ backgroundColor: '#4f7dbd85', color: '#222' }"
                        :cell-style="{ backgroundColor: '#4f7dbd58', color: '#222' }">
                        <el-table-column prop="gname" label="客户名">
                        </el-table-column>
                        <el-table-column prop="address" label="客户地址">
                        </el-table-column>
                        <el-table-column prop="consumption" label="消费￥">
                        </el-table-column>
                        <el-table-column prop="operation" label="操作">
                            <template slot-scope="scope">
                                <el-button @click.native.prevent="deleteRow(scope.$index, orderData)" type="text"
                                    size="small">
                                    移除
                                </el-button>
                            </template>
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
                address: "",
                gname: "",
                consumption: "",
            },
            dialogFormVisible: false,
            popoverVisible: false,
            signIn: {
                account: "",
                password: "",
                role: ""
            },
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
        deleteRow(index, rows) {
            //这行代码利用 JavaScript 的 splice 方法从 rows 数组中删除元素。
            //splice 方法的第一个参数是开始的索引，第二个参数是要删除的元素的数量。
            alert(rows[index].gname);
            axios.get("http://113.44.84.103:8080/deleteGuest", {
                params: {
                    gname: rows[index].gname
                }
            });
            rows.splice(index, 1);
        },
        fileChange(file) {
            const typeArr = ['image/png', 'image/jpg', 'image/jpeg'];
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
        this.searchForm['forWhat'] = 'guestManagement';
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
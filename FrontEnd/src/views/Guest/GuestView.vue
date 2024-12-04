<template>
    <el-container style="height: 100%; " class="bgimg">
        <el-header style="display: flex; align-items: center; justify-content: center; background-color:#4f7dbd52;">
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
            <i class="el-icon-shopping-bag-2" style="margin-left: 2%;"></i>
        </el-header>
        <el-container>
            <el-aside width="230px" style="background-color:#4f7dbd52; height: 97.5%; ">
                <el-menu :default-openeds="['1']" style="background-color: #4f7dbd52;">
                    <el-submenu index="1">
                        <template slot="title"><i class="el-icon-message"></i>我的空间</template>
                        <el-menu-item-group style="background-color: #4f7dbd79;">
                            <el-menu-item index="1-1" @click="orderStatusDialog = true; orderStatusDetail()">
                                订单状态
                            </el-menu-item>
                            <el-dialog title="订单状态" :visible.sync="orderStatusDialog" style="text-align: center;">
                                <el-table :data="orderStatus">
                                    <el-table-column property="date" label="日期"></el-table-column>
                                    <el-table-column property="pname" label="商品名"></el-table-column>
                                    <el-table-column property="orderID" label="订单号"></el-table-column>
                                    <el-table-column property="address" label="发货地址"></el-table-column>
                                    <el-table-column property="price" label="金额￥"></el-table-column>
                                    <el-table-column property="count" label="数量"></el-table-column>
                                    <el-table-column property="time" label="预计送达时间"></el-table-column>
                                    <el-table-column property="status" label="状态">
                                        <!--插槽用法妙处多 有点像模版字符串的用法`${}`-->
                                        <template slot-scope="scope">
                                            {{ scope.row.status == 1 ? "已完成" : "未完成" }}
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="operation" label="操作">
                                        <template slot-scope="scope">
                                            <el-button @click.native.prevent="deleteRow(scope.$index, orderStatus)"
                                                type="text" size="small">
                                                移除
                                            </el-button>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </el-dialog>

                            <el-menu-item index="1-2" @click="shopTrolleyDialog = true; shopTrolleyDetail()">
                                购物车
                            </el-menu-item>
                            <el-dialog title="购物车" :visible.sync="shopTrolleyDialog" style="text-align: center;">
                                <el-table :data="shopTrolley">
                                    <el-table-column property="name" label="商品名"></el-table-column>
                                    <el-table-column property="price" label="价格￥"></el-table-column>
                                    <el-table-column prop="operation" label="操作">
                                        <template slot-scope="scope">
                                            <el-button @click.native.prevent="deleteRow(scope.$index, shopTrolley)"
                                                type="text" size="small">
                                                移除
                                            </el-button>
                                            <el-button @click="queryBuy = true; buyindex = scope.$index" type="text"
                                                size="small">
                                                购买
                                            </el-button>
                                            <el-dialog :visible.sync="queryBuy" width="40%" :append-to-body="true">
                                                <h2 style="text-align: center; margin-top: 0%;" v-text="step"></h2>
                                                <el-steps :active="active" finish-status="success">
                                                    <el-step title="确认"></el-step>
                                                    <el-step title="付款"></el-step>
                                                    <el-step title="交易成功"></el-step>
                                                </el-steps>
                                                <div style="position: relative; text-align: center;">
                                                    <div>
                                                        <br>
                                                        <!--万能居中样式-->
                                                        <select-nums v-if="currentComponent == 'selectPage'"
                                                            ref="forNums"
                                                            style="position: absolute;left: 50%;top: 30%;transform: translate(-50%, -50%);"></select-nums>
                                                        <payment-page v-else-if="currentComponent == 'paymentPage'"
                                                            ref="forAmount"
                                                            style="position: absolute;left: 50%;top: 30%;transform: translate(-50%, -50%);"></payment-page>
                                                        <payment-success
                                                            v-else-if="currentComponent == 'paymentSuccess'"
                                                            style="position: absolute;left: 50%;top: 30%;transform: translate(-50%, -50%);">
                                                        </payment-success>
                                                        <br>
                                                    </div>
                                                    <br><br><br>

                                                    <el-button type="primary" @click="buy(buyindex); next()">
                                                        确定
                                                    </el-button>

                                                    <el-button type="primary"
                                                        @click="queryBuy = false; currentComponent = 'selectPage'; active = 0">取消</el-button>
                                                </div>
                                            </el-dialog>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </el-dialog>

                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-container>
                <el-main style="height: 100%; width:100%">
                    <div style="margin-top: 15px; text-align: center;">
                        <el-input placeholder="请输入内容" v-model="queryCommodityContent" style=" width: 50%;">
                            <el-select v-model="selectCondition" slot="prepend" placeholder="请选择"
                                style=" width: 130px;">
                                <el-option label="发货地址" value="address"></el-option>
                                <el-option label="商品名" value="pname"></el-option>
                                <!-- <el-option label="商家名" value="mname"></el-option> -->
                            </el-select>
                            <el-button slot="append" icon="el-icon-search" @click="queryCommodity"></el-button>
                        </el-input>
                    </div>
                    <!-- <ul v-infinite-scroll="load" style="overflow:auto; height: 670px; text-align: left; width: 48%;">
                        <li v-for="(i, index) in commodityList" :key="index"
                            style="height: 300px; list-style-type:none"> -->
                    <div v-infinite-scroll="load"
                        style="overflow: auto; display: flex; flex-wrap: wrap; height:calc(100vh - 190px); text-align: center;margin-top: 30px;"
                        infinite-scroll-distance="20px">
                        <div v-for="(i, index) in commodityList" :key="index" style="height: 300px; flex: 0 0 48%;">
                            <img :src="i.picture" style="height: 200px; width:200px"> <br>
                            <span>{{ i.name }}</span><br>
                            <span>￥{{ i.price }}</span><br>
                            <el-button icon="el-icon-search" circle
                                @click="forDetail = true; putDetail(index)"></el-button>
                            <!--因为是所有加载完之后才点击的按钮，此时的index早就不对了，没法用插值表达式直接把对应属性输出到详情页上-->
                            <el-dialog title="商品详情" :visible.sync="forDetail" width="30%" top="8vh"
                                style="text-align: center;">
                                <img :src="detailImg" style="height: 400px; width:400px"> <br><br>
                                <span v-text="detailName"></span><br><br>
                                <span v-text="detailPrice"></span><br><br>
                                发货地址: &ensp;<span v-text="detailAddr"></span><br><br>
                                <span v-text="detailTime"></span><br><br>
                                <!-- <span v-text=""></span> -->
                                <el-button type="primary" @click="forDetail = false">确 定</el-button>
                            </el-dialog>

                            <el-button type="warning" icon="el-icon-star-off" circle
                                @click="addin = true; addindex = index"></el-button>
                            <el-dialog title="确定放入购物车？" :visible.sync="addin" width="30%">
                                <el-button type="primary" @click="addin = false; addInTrolley(addindex)">确 定</el-button>
                                <el-button type="primary" @click="addin = false">取 消</el-button>
                            </el-dialog>
                        </div>
                    </div>

                </el-main>
                <el-footer style="height: 25px"></el-footer>
            </el-container>
        </el-container>
    </el-container>
</template>
<script>
import PaymentPage from '@/components/paymentPage.vue';
import PaymentSuccess from '@/components/paymentSuccess.vue';
import SelectNums from '@/components/selectNums.vue';
import axios from 'axios';
axios.defaults.baseURL = 'http://113.44.84.103:8080'
// import infiniteScroll from 'vue-infinite-scroll';
// Vue.use(infiniteScroll);

// axios.defaults.withCredentials = true;

export default {
    data() {
        return {
            userName: "",
            dialogFormVisible: false,
            popoverVisible: false,
            signIn: {
                account: "",
                password: "",
                role: ""
            },
            orderStatusDialog: false,
            shopTrolleyDialog: false,
            commodityList: [],
            page: 0,
            detailImg: "",
            detailName: "",
            detailPrice: "",
            detailAddr: "",
            detailTime: "",
            forDetail: false,
            addin: false,
            queryBuy: false,
            shopTrolley: [],
            orderStatus: [],
            active: 0,
            currentComponent: "selectPage",
            step: "请选择数量",
            queryCommodityContent: "",
            selectCondition: "",
            addindex: "",
            buyindex: "",
            isSearchResult: 0
        }
    },
    components: {
        PaymentPage,
        PaymentSuccess,
        SelectNums
    },
    methods: {
        load() {
            // alert("bottom")
            if (this.isSearchResult)
                return;
            this.page = this.page + 1;
            axios.post("http://113.44.84.103:8080/guestView", {
                "page": this.page
            }).then(result => {
                this.commodityList = this.commodityList.concat(result.data.data);

            });

        },
        submitRegistry: function () {
            axios.post("http://113.44.84.103:8080/changeAccount", this.signIn).
                then((result) => {
                    if (result.data.status == 1) this.userName = result.data.userName;
                    else alert("该用户不存在")
                    if (this.signIn.role == "merchant" && result.data.status == 1) {
                        location.replace("http://113.44.84.103:7000/#/merchant");
                    }
                })

            // document.getElementById("userName").innerHTML = this.userName;
        },
        putDetail(index) {
            this.detailImg = this.commodityList[index].picture;
            this.detailName = this.commodityList[index].name;
            this.detailPrice = "￥" + this.commodityList[index].price;
            this.detailAddr = this.commodityList[index].delivAddr;
            this.detailTime = this.commodityList[index].delivTime;
            axios.get("http://113.44.84.103:8080/guestBrows", {
                params: {
                    pname: this.detailName,//上传浏览记录
                },
            });
        },
        addInTrolley(index) {
            // alert(this.commodityList[index].name);
            axios.get("http://113.44.84.103:8080/addInTrolley", {
                params: {
                    name: this.commodityList[index].name,//请求参数
                },
            }).then(
                (result) => {
                    if (result.data.code == "failed") alert("已在购物车中！");
                    else alert("加入购物车成功！");
                }
            );
            axios.get("http://113.44.84.103:8080/guestBrows", {
                params: {
                    pname: this.commodityList[index].name
                }
            });
        },
        //从数据库获取该用户的购物车
        shopTrolleyDetail() {
            axios.get("http://113.44.84.103:8080/shopTrolleyDetail", {
                params: {
                    userName: this.userName,
                }
            }).then(
                (result) => { this.shopTrolley = result.data.data; }
            )
        },
        orderStatusDetail() {
            axios.get("http://113.44.84.103:8080/guestOrderStatus", {
                params: {
                    userName: this.userName,
                },
            }).then(
                (result) => this.orderStatus = result.data.data
            )
        },
        buy(index) {
            if (this.active != 0) return;
            const nums = this.$refs.forNums.getData();
            // if (index == 1) {
            //     axios.post("http://113.44.84.103:8080/buy", {
            //         name: this.shopTrolley[index].name,
            //         nums: nums
            //     }
            //     ).then((result) => this.$refs.forAmount.changeData(result.data.amount))
            // }
            // else {
            axios.post("http://113.44.84.103:8080/buy", {
                name: this.shopTrolley[index].name,
                nums: nums
            }
            ).then((result) => this.$refs.forAmount.changeData(result.data.amount))
            // }

        },
        deleteRow(index, rows) {

            axios.get("http://113.44.84.103:8080/deleteFromTrolley", {
                params: {
                    name: rows[index].name
                }
            });
            rows.splice(index, 1);
        },
        next() {

            if (this.active++ == 2) {
                this.queryBuy = false;
                this.active = 0;
                alert("交易成功！")
                this.currentComponent = "selectPage";
            }
            if (this.active == 1) {
                this.currentComponent = "paymentPage";
                this.step = "请选择付款方式";
            }
            if (this.active == 2) {
                this.currentComponent = "paymentSuccess";
                this.step = "交易成功";
            }
        },
        queryCommodity() {
            if (this.selectCondition == "pname") {
                axios.post("http://113.44.84.103:8080/getCommodity", {
                    pname: this.queryCommodityContent
                }).then((result) => {
                    this.commodityList = result.data.data;
                })
            }
            // if (this.selectCondition == "mname") {
            //     axios.post("https:/http://113.44.84.103:8080foxmock.com/m1/5168580-4833413-default/getCommodity", {
            //         mname: this.queryCommodityContent
            //     }).then((result) => {
            //         this.commodityList = result.data.data;
            //     })
            // }
            if (this.selectCondition == "address") {
                axios.post("http://113.44.84.103:8080/getCommodity", {
                    address: this.queryCommodityContent
                }).then((result) => {
                    this.commodityList = result.data.data;
                })
            }
            this.isSearchResult = 1;

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
        axios.get("http://113.44.84.103:8080/username").then((result) => this.userName = result.data.userName)
        this.isSearchResult = 0;
    }
}
</script>

<style>
.bgimg {
    /* position: fixed; */
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    min-width: 1000px;
    z-index: -10;
    zoom: 1;
    background-color: #4f7dbd52;
    /* background-image: url(../../../public/favicon.ico); */
    background-repeat: no-repeat;
    background-size: cover;
    -webkit-background-size: cover;
    -o-background-size: cover;
    background-position: center 0;
}
</style>
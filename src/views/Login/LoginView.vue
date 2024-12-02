<template>
    <el-container class="bgimg">
        <el-dialog title="登录/注册" :visible.sync="signInVisible" width="30%" style="text-align: center;"
            :show-close="false" :close-on-press-escape="false" :close-on-click-modal="false" :modal="false">
            <el-form style="text-align: center;">
                <el-form-item label="账 号" label-width=50px>
                    <el-input v-model="signIn.account" autocomplete="off" style="width:370px;"></el-input>
                </el-form-item>
                <el-form-item label="密 码" label-width=50px>
                    <el-input v-model="signIn.password" autocomplete="off" style="width:370px" show-password></el-input>
                </el-form-item>
                <el-select v-model="signIn.role" placeholder="选择身份">
                    <el-option label="商 家" value="merchant">
                        商 家
                    </el-option>
                    <el-option label="顾 客" value="guest">
                        顾 客
                    </el-option>
                </el-select>
            </el-form>
            <!--父组件的样式管不了插槽的-->
            <div slot="footer" style="text-align: center;">
                <el-button type="primary" @click="submitSignIn()">确 定</el-button>
                <el-button type="primary" @click="registryVisible = true">注 册</el-button>
            </div>
        </el-dialog>

        <el-dialog title="注册" :visible.sync="registryVisible" width="30%" style="text-align: center;">
            <el-form>
                <el-form-item label="昵 称" label-width=50px>
                    <el-input v-model="registryForm.userName" autocomplete="off" style="width:370px;"></el-input>
                </el-form-item>
                <el-form-item label="密 码" label-width=50px>
                    <el-input v-model="registryForm.password" autocomplete="off" style="width:370px"
                        show-password></el-input>
                </el-form-item>
                <el-form-item label="地 址" label-width=50px>
                    <el-input v-model="registryForm.address" autocomplete="off" style="width:370px"></el-input>
                </el-form-item>
                <el-select v-model="registryForm.role" placeholder="选择身份">
                    <el-option label="商 家" value="merchant">
                        商 家
                    </el-option>
                    <el-option label="顾 客" value="guest">
                        顾 客
                    </el-option>
                </el-select>
            </el-form>
            <div slot="footer" style="text-align: center;">
                <el-button type="primary" @click="registry()">确 定</el-button>
            </div>
        </el-dialog>
    </el-container>




</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            signInVisible: true,
            signIn: {
                account: "",
                password: "",
                role: ""
            },
            registryForm: {
                userName: "",
                password: "",
                role: "",
                address: ""
            },
            registryVisible: false
        }
    },
    methods: {
        submitSignIn: function () {
            axios.post("http://localhost:8080/changeAccount", this.signIn).
                then((result) => {
                    if (result.data.status == 1) alert("欢迎: " + result.data.userName);
                    else if (result.data.status == 2) alert("该用户不存在");
                    else if (result.data.status == 3) alert("密码错误");
                    else alert("权限不合要求");
                    if (this.signIn.role == "merchant" && result.data.status == 1) {
                        location.replace("http://localhost:7000/#/merchant");
                    }
                    if (this.signIn.role == "guest" && result.data.status == 1) {
                        location.replace("http://localhost:7000/#/guest");
                    }
                })
        },
        registry: function () {
            axios.post("http://localhost:8080/registry", this.registryForm).then((result) => {
                if (result.data.status == 1) alert("欢迎: " + this.registryForm.userName + "  您的账号是: " + result.data.account);
                else alert("用户名重复")
                if (this.registryForm.role == "merchant" && result.data.status == 1) {
                    location.replace("http://localhost:7000/#/merchant");
                }
                if (this.registryForm.role == "guest" && result.data.status == 1) {
                    location.replace("http://localhost:7000/#/guest");
                }
            })

        }

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
    background-color: #1751a152;
    /* background-image: url(../../../public/favicon.ico); */
    background-repeat: no-repeat;
    background-size: cover;
    -webkit-background-size: cover;
    -o-background-size: cover;
    background-position: center 0;
}
</style>
<template>
    <el-container>
        <div>
            <el-radio v-model="radio" label="1">微信支付</el-radio>
            <el-radio v-model="radio" label="2">支付宝</el-radio>
            <el-radio v-model="radio" label="3">银联</el-radio>
            <br><br>总金额: ￥{{ amount }}
        </div>

    </el-container>
</template>

<script>
export default {
    data() {
        return {
            radio: "",
            amount: ""
        };
    },
    methods: {
        changeData(amount){
            this.amount = amount;
        }
    }
}
</script>

<style>

</style>
<template>
    <el-input-number v-model="nums" :min="1" :max="1000" label="数量"></el-input-number>
</template>

<script>
export default {
    data() {
        return {
            nums: 1
        }
        
    },
    methods: {
        getData() {
            return this.nums;
        }
    }
}
</script>

<style>

</style>
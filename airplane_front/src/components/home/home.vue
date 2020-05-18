<template>
  <div class="home">
    <el-row :gutter="74">
      <el-col :span="12" :offset="3" style="height:280px">
        <booking></booking>
      </el-col>
      <el-col :span="7" style="height:280px">
        <register></register>
      </el-col>
    </el-row>
    <el-row style="margin-top:30px">
      <el-col :span="18" align="center" :offset="3">
        <router-view name="below"></router-view>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import booking from "@/components/home/booking";
import register from "@/components/home/register";

export default {
  components: {
    booking,
    register
  },
  methods: {}
};
</script>

<style>
.el-row {
  width: 100%;
}
</style>
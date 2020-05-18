<template>
  <div class="head">
    &nbsp;
    <p>&nbsp; &nbsp;飞机售票管理系统<el-button icon="el-icon-user" circle size="small" @click="goAdminLogin" style="float:right"></el-button></p>
    
  </div>
</template>

<script>
export default {
  methods: {
    goAdminLogin(){
      this.$router.push({ path: "/login" });
    }
  }
}
</script>

<style>
.head {
  background-color: #409eff;
  width: 100%;
  height: 100%;

  text-align: left;
}
p {
  margin: 0;
  font-size: 30px;
  color:white;
}
</style>
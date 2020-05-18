<template>
  <div class="admin">
    <el-row>
      <el-col :span="24">
        <el-menu
          :default-active="this.$router.path"
          class="el-menu-demo"
          mode="horizontal"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          router
        >
          <el-submenu>
            <template slot="title">航班管理</template>
            <el-menu-item index="/admin" @click="admin">航班列表</el-menu-item>
            <el-menu-item index="/admin/addflight">航班添加</el-menu-item>
          </el-submenu>
          <el-menu-item index="/admin/order" @click="order">订单管理</el-menu-item>

          <el-menu-item index="/admin/user" @click="user">用户管理</el-menu-item>
          <el-menu-item index="/admin/setting">账户设置</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
    <router-view name="_below"></router-view>
  </div>
</template>

<script>
export default {
  data() {
    return {
      array: []
    };
  },
  methods: {
    admin() {
      this.$http
        .post("http://localhost:3000/admin/showflight", {})
        .then(res => {
          console.log(res.data);
          if (res.data) {
            res.data.forEach(element => {
              this.array.push(element);
            });
            this.$store.dispatch("actionsnewFlightList", this.array);
            this.array=[];
          }
        })
        .catch(res => {
          console.log(res);
        });
    },
    order(){
      this.$http
        .post("http://localhost:3000/admin/showorder")
        .then(res => {
          console.log(res.data);
          if (res.data) {
            res.data.forEach(element => {
              this.array.push(element);
            });
            this.$store.dispatch("actionsnewFlightList", this.array);
            this.array=[];
          }
        })
        .catch(res => {
          console.log(res);
        });
    },
    user(){
      this.$http
        .post("http://localhost:3000/admin/showuser")
        .then(res => {
          console.log(res.data);
          if (res.data) {
            res.data.forEach(element => {
              this.array.push(element);
            });
            this.$store.dispatch("actionsnewFlightList", this.array);
            this.array=[];
          }
        })
        .catch(res => {
          console.log(res);
        });
    }
  }
};
</script>

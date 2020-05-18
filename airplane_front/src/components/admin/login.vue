<template>
  <div class="login">
    <el-row>
      <el-col :span="8" align="center" :offset="8">
        <span>管理员登录</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8" align="center" :offset="8">
        <el-input v-model="username" size="small" autosize placeholder="账号"></el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8" align="center" :offset="8">
        <el-input v-model="password" size="small" autosize placeholder="密码" show-password></el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8" align="center" :offset="8">
        <el-button plain size="small" @click="login">登录</el-button>
        <el-button plain size="small" @click="back">返回</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      array: []
    };
  },
  methods: {
    login() {
      if (this.username == "" || this.password == "") {
        this.$notify({
          title: "账号密码不能为空",
          type: "warning"
        });
      } else {
        this.$http
          .post("http://localhost:3000/admin/login", {
            username: this.username,
            passWord: this.password
          })
          .then(res => {
            console.log(res);
            if (res.data) {
              res.data.forEach(element => {
                this.array.push(element);
              });
              this.$store.dispatch("actionsnewFlightList", this.array);
              this.$router.push({ path: "/admin" });
            } else {
              this.$notify({
                title: "账号或密码错误",
                type: "warning"
              });
            }
          })
          .catch(res => {});
      }
    },
    back() {
      this.$router.push({ path: "/" });
    }
  }
};
</script>
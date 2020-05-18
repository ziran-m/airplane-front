<template>
  <div class="register">
    <div class="register1">
      <el-row class="ch_search_tab" v-show="div0">
        <el-col :span="4" :offset="4">
          <el-button type="text" @click="div1=true;div2=false;div3=false">登录</el-button>
        </el-col>
        <el-col :span="8" align="center">|</el-col>
        <el-col :span="7" :offset="1">
          <el-button type="text" @click="div1=false;div2=true;div3=false">注册</el-button>
        </el-col>
      </el-row>
      <div v-show="div1">
        <el-row style="margin-top:35px">
          <el-col :span="18" align="center" :offset="3">
            <el-input v-model="username" size="small" autosize placeholder="账号"></el-input>
          </el-col>
        </el-row>
        <el-row style="margin-top:35px">
          <el-col :span="18" align="center" :offset="3">
            <el-input v-model="pwd" show-password size="small" placeholder="密码"></el-input>
          </el-col>
        </el-row>
        <el-row style="margin-top:35px">
          <el-col :span="24" align="center">
            <el-button plain size="small" @click="sign">登录</el-button>
          </el-col>
        </el-row>
      </div>
      <div v-show="div2">
        <el-row style="margin-top:20px">
          <el-col :span="18" align="center" :offset="3">
            <el-input v-model="newUsername" size="small" autosize placeholder="账号"></el-input>
          </el-col>
        </el-row>
        <el-row style="margin-top:20px">
          <el-col :span="18" align="center" :offset="3">
            <el-input v-model="newPwd" show-password size="small" placeholder="密码"></el-input>
          </el-col>
        </el-row>
        <el-row style="margin-top:20px">
          <el-col :span="18" align="center" :offset="3">
            <el-input v-model="nnewPwd" show-password size="small" placeholder="确认密码"></el-input>
          </el-col>
        </el-row>
        <el-row style="margin-top:20px">
          <el-col :span="24" align="center">
            <el-button plain size="small" @click="register">注册</el-button>
          </el-col>
        </el-row>
      </div>
      <div v-show="div3">
        <el-row style="margin-top:35px">
          <el-col :span="18" align="center" :offset="3">
            <span>Welcome</span>
          </el-col>
        </el-row>
        <el-row style="margin-top:35px">
          <el-col :span="24" align="center">
            <el-button plain size="small" @click="logout">注销</el-button>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      pwd: "",
      newUsername: "",
      newPwd: "",
      nnewPwd: "",
      div0: true,
      div1: true,
      div2: false,
      div3: false
    };
  },
  methods: {
    register() {
      if (this.newUsername == "" || this.newPwd == "") {
        this.$notify({
          title: "账号密码不能为空",
          type: "warning"
        });
      } else {
        if (this.nnewPwd != this.newPwd) {
          this.$notify({
            title: "2次密码不同",
            type: "warning"
          });
        } else {
          // console.log("2222");
          this.$http
            .post("http://localhost:3000/user/register", {
              username: this.newUsername,
              passWord: this.newPwd
            })
            .then(res => {
              this.newUsername='';
              this.newPwd='';
              this.nnewPwd='';
              if (res.data != "") {
                this.div0 = false;
                this.div1 = false;
                this.div2 = false;
                this.div3 = true;
                localStorage.setItem("token", res.data);
                console.log(res.data);
                this.$notify({
                  title: "注册成功",
                  type: "success"
                });
              } else {
                this.$notify({
                  title: "注册失败",
                  type: "warning"
                });
              }
            })
            .catch(function(res) {
              consolo(res);
            });
        }
      }
    },
    sign() {
      if (this.username == "" || this.pwd == "") {
        this.$notify({
          title: "账号密码不能为空",
          type: "warning"
        });
      } else {
        this.$http
          .post("http://localhost:3000/user/login", {
            username: this.username,
            passWord: this.pwd
          })
          .then(res => {
            this.username = "";
            this.pwd = "";
            if (res.data != "") {
              this.div0 = false;
              this.div1 = false;
              this.div2 = false;
              this.div3 = true;
              localStorage.setItem("token", res.data);
            } else {
              this.$notify({
                title: "账号或密码错误",
                type: "warning"
              });
            }
            console.log(res.data);
          })
          .catch(function(res) {
            consolo(res);
          });
      }
    },
    logout() {
      this.div0 = true;
      this.div1 = true;
      this.div2 = false;
      this.div3 = false;
      localStorage.removeItem("token");
    }
  }
};
</script>

<style>
.register {
  width: 100%;
  padding: 1%;
  background: #00d0d4;
  height: 100%;
  zoom: 1;
}

.register1 {
  height: 90%;
  margin: 3%;
  padding: 1%;
  width: 92%;
  position: relative;
  z-index: 30;
  zoom: 1;
  display: block;
  background: #fff;
}

.ch_search_tab {
  border-bottom: 1px solid #97d3d9;
  height: 38px;
  line-height: 30px;
}
</style>
<template>
  <div class="login">
    <el-row>
      <el-col :span="8" align="center" :offset="8">
        <el-input v-model="pwd" size="small" autosize placeholder="新密码" show-password></el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8" align="center" :offset="8">
        <el-input v-model="newPwd" size="small" autosize placeholder="确认新密码" show-password></el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8" align="center" :offset="8">
        <el-button plain size="small" @click="notarize">确认</el-button>
        <el-button plain size="small" @click="back">返回</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pwd: "",
      newPwd: ""
    };
  },
  methods: {
    notarize() {
      if ((this.pwd == "" || this.newPwd == "")) {
        this.$notify({
          title: "账号密码不能为空",
          type: "warning"
        });
      }else if(this.pwd != this.newPwd){
           this.$notify({
          title: "2次输入密码不同",
          type: "warning"
        });
                this.pwd = ''
        this.newPwd = ''
      }
       else {
        this.$http
          .post("http://localhost:3000/admin/setting", {
            passWord: this.pwd
          })
          .then(res => {
            if(res.data){
              this.$notify({
          title: "密码修改成功",
          type: "success"
        });
        this.pwd='';
        this.newPwd='';
            }
          })
          .catch(res => {});
      }
    },
    back(){
        this.$router.push({ path: "/" });
    }
  }
};
</script>
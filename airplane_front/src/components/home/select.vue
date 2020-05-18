<template>
  <div class="select">
    <div class="select1">
      <el-row :gutter="10">
        <el-col :span="3" :offset="1">
          <el-tag>日期</el-tag>&nbsp;
          <el-tag>航班</el-tag>&nbsp;
          <el-tag>起点</el-tag>&nbsp;
          <el-tag>终点</el-tag>&nbsp;
          <el-tag>时间</el-tag>
        </el-col>
        <el-col :span="6">
          <el-tag>{{this.$store.state.select.date}}</el-tag>&nbsp;
          <el-tag>{{this.$store.state.select.flightNumber}}</el-tag>&nbsp;
          <el-tag>{{this.$store.state.select.origin}}</el-tag>&nbsp;
          <el-tag>{{this.$store.state.select.destination}}</el-tag>&nbsp;
          <el-tag>{{this.$store.state.select.startTime}}—{{this.$store.state.select.endTime}}</el-tag>
        </el-col>
        <el-col :span="4" :offset="2">
          <el-input v-model="id" size="small" autosize placeholder="身份证号"></el-input>&nbsp;
          <el-input v-model="tel" size="small" autosize placeholder="联系方式"></el-input>&nbsp;
          <el-input v-model="name" size="small" autosize placeholder="姓名"></el-input>&nbsp;
          <el-select v-model="sex" placeholder="性别" size="small">
            <el-option v-for="item in options" :key="item.value" :value="item.value"></el-option>
          </el-select>&nbsp;
          <font size="6">￥&nbsp;{{this.$store.state.select.cost}}</font>
        </el-col>
        <el-col :span="4" :offset="2">
          <div style="width:100%;height:100%;background: #00d0d4;">这里放个二维码收钱</div>
          <el-button
            type="primary"
            plain
            size="small"
            style="width:70%;margin-top:100%"
            @click="reserve"
          >订票</el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      tel: "",
      name: "",
      sex: "男",
      options: [
        {
          value: "男"
        },
        {
          value: "女"
        }
      ]
    };
  },

  methods: {
    reserve() {
      if (this.id == "" || this.tel == "" || this.name == "") {
        this.$notify({
          title: "请输入订票人信息",
          type: "warning"
        });
      } else {
        this.$http
          .post("http://localhost:3000/user/reserve", {
            id: this.id,
            name: this.name,
            tel: this.tel,
            sex: this.sex,
            flightNumber: this.$store.state.select.flightNumber
          })
          .then(res => {
            if (res.data) {
              this.$notify({
                title: "订票成功",
                type: "success"
              });
            } else {
              this.$notify({
                title: "订票已存在",
                type: "warning"
              });
            }
            console.log(res.data);
          })
          .catch(res => {
            this.$notify({
              title: "请登录",
              type: "warning"
            });
          });
      }
    }
  }
};
</script>

<style>
.select {
  width: 100%;
  background: #00d0d4;
  height: 100%;
  zoom: 1;
  padding: 1.5% 0% 1.5% 0%;
}

.select1 {
  height: 90%;
  padding: 0.7% 0.7% 0.7% 0%;
  width: 95%;
  position: relative;
  z-index: 30;
  zoom: 1;
  display: block;
  background: #fff;
}

.el-tag {
  width: 100%;
}
</style>
<template>
  <div class="booking">
    <div class="booking1">
      <el-row class="ch_search_tab">
        <el-col :span="8" align="center">
          <el-button type="text">机票查询/预定</el-button>
        </el-col>
        <el-col :span="3" align="center" :offset="12">
          <i class="el-icon-document-remove">
            <el-button type="text" @click="order">退票</el-button>
          </i>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="4" align="center">
          <el-radio v-model="radio" label="1" @change="isAble=true;back=''">单程</el-radio>
        </el-col>
        <el-col :span="4" align="center">
          <el-radio v-model="radio" label="2" @change="isAble=false">往返</el-radio>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8" :offset="1">
          <el-select v-model="origin" placeholder="起点" filterable size="small">
            <el-option-group v-for="group in options" :key="group.label" :label="group.label">
              <el-option v-for="item in group.options" :key="item.value" :value="item.value"></el-option>
            </el-option-group>
          </el-select>
          <el-select
            v-model="destination"
            placeholder="终点"
            filterable
            size="small"
            style="margin-top:20px"
          >
            <el-option-group v-for="group in options" :key="group.label" :label="group.label">
              <el-option v-for="item in group.options" :key="item.value" :value="item.value"></el-option>
            </el-option-group>
          </el-select>
        </el-col>
        <el-col :span="8" :offset="5">
          <el-date-picker
            v-model="go"
            type="date"
            placeholder="出发日期"
            size="small"
            :picker-options="pickerOptions"
            style="width:100%"
            value-format="yyyy-MM-dd"
          ></el-date-picker>
          <el-date-picker
            :disabled="isAble"
            v-model="back"
            type="date"
            placeholder="返回日期"
            size="small"
            style="margin-top:20px;width:100%"
            value-format="yyyy-MM-dd"
            :picker-options="pickerOptions"
          ></el-date-picker>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="4" style="float:right">
          <el-button plain size="small" @click="search">查询</el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      radio: "1", //单选框
      isAble: "true", // dising:"true",//日期选择器禁用

      array: [],

      go: "", //日期选择器
      back: "",
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 3600 * 1000 * 24;
        }
      },

      origin: "", //地点选择器
      destination: "",

      options: [
        {
          label: "国内城市",
          options: [
            {
              value: "上海"
            },
            {
              value: "北京"
            }
          ]
        },
        {
          label: "国际·港澳台",
          options: [
            {
              value: "巴黎"
            },
            {
              value: "纽约"
            },
            {
              value: "东京"
            },
            {
              value: "大连"
            }
          ]
        }
      ]
    };
  },
  methods: {
    search() {
      if (this.start == "" || this.end == "") {
        this.$notify({
          title: "起点终点不能为空",
          type: "warning"
        });
      } else if (this.go == "") {
        this.$notify({
          title: "出发日期不能为空",
          type: "warning"
        });
      } else {
        // console.log(this.origin,this.destination,this.go);
        this.$http
          .post("http://localhost:3000//user/search", {
            origin: this.origin,
            destination: this.destination,
            date: this.go
          })
          .then(res => {
            res.data.forEach(element => {
              this.array.push(element);
            });
            // console.log(this.array);
            this.$store.dispatch("actionsnewFlightList", this.array);
            this.array = [];
            this.$router.push({ path: "/flightList" });
            if(this.back != ""){
              this.$http
          .post("http://localhost:3000//user/search", {
            origin: this.destination,
            destination: this.origin,
            date: this.back
          })
          .then(res => {
            res.data.forEach(element => {
              this.array.push(element);
            });
            // console.log(this.array);
            this.$store.dispatch("actionsnewBackFlightList", this.array);
            this.array = [];
          })
          .catch(function(res) {
            console.log(res);
          });

            }
          })
          .catch(function(res) {
            console.log(res);
          });
      }
    },
    order() {
      this.$http
        .post("http://localhost:3000/user/order")
        .then(res => {
          console.log(res.data)
          res.data.forEach(element => {
            this.array.push(element);
          });
          this.$store.dispatch("actionsnewFlightList", this.array);
          this.array = [];
          this.$router.push({ path: "/order" });
        })
        .catch(res => {
           console.log(res)
          this.$notify({
            title: "请登录",
            type: "warning"
          });
        });
    }
  }
};
</script>

<style>
.booking {
  width: 100%;
  padding: 1%;
  background: #00d0d4;
  height: 100%;
  zoom: 1;
  padding-top: 0.1%;
}

.booking1 {
  height: 90%;
  margin: 2%;
  padding: 0.7%;
  width: 95%;
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

.el-row {
  margin-bottom: 20px;
}
</style>
<template>
  <div class="booking1">
    <el-row class="ch_search_tab">
      <el-col :span="8" align="center">
        <el-button type="text">航班添加</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="3">
        <el-select v-model="origin" placeholder="起点" filterable size="small">
          <el-option-group v-for="group in options" :key="group.label" :label="group.label">
            <el-option v-for="item in group.options" :key="item.value" :value="item.value"></el-option>
          </el-option-group>
        </el-select>
      </el-col>
      <el-col :span="3" :offset="1">
        <el-select v-model="destination" placeholder="终点" filterable size="small">
          <el-option-group v-for="group in options" :key="group.label" :label="group.label">
            <el-option v-for="item in group.options" :key="item.value" :value="item.value"></el-option>
          </el-option-group>
        </el-select>
      </el-col>
      <el-col :span="3" :offset="1">
        <el-date-picker
          v-model="date"
          type="date"
          placeholder="出发日期"
          size="small"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd"
        ></el-date-picker>
      </el-col>
      <el-col :span="1" :offset="1">
        <el-input v-model="seat" size="small" autosize placeholder="座位"></el-input>
      </el-col>
      <el-col :span="2" :offset="1">
        <el-input v-model="cost" size="small" autosize placeholder="价格"></el-input>
      </el-col>
      <el-col :span="3" :offset="1">
        <el-input v-model="flightNumber" size="small" autosize placeholder="航班号"></el-input>
      </el-col>
      <el-col :span="3" :offset="1">
        <el-input v-model="moudel" size="small" autosize placeholder="飞机型号"></el-input>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="3">
        <el-time-select
          size="small"
          v-model="startTime"
          :picker-options="{
    start: '00:00',
    step: '00:15',
    end: '24:00'
  }"
          placeholder="选择时间"
        ></el-time-select>
      </el-col>
      <el-col :span="3" :offset="1">
        <el-time-select
          size="small"
          placeholder="结束时间"
          v-model="endTime"
          :picker-options="{
      start: '00:00',
      step: '00:15',
      end: '24:00',
    }"
        ></el-time-select>
      </el-col>
      <el-col :span="4" style="float:right">
        <el-button plain size="small" @click="add" style="width:100%">添加</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      moudel: "",
      flightNumber: "",
      startTime: "",
      endTime: "",
      date: "", //日期选择器
      seat: "",
      cost: "",
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
    add() {
      if (
        this.moudel == "" ||
        this.flightNumber == "" ||
        this.startTime == "" ||
        this.endTime == "" ||
        this.date == "" ||
        this.seat == "" ||
        this.cost == "" ||
        this.origin == "" ||
        this.destination == ""
      ) {
        this.$notify({
          title: "别空",
          type: "warning"
        });
      }else{
        this.$http
          .post("http://localhost:3000/admin/add", {
            moudel: this.moudel,
            flightNumber: this.flightNumber,
            startTime: this.startTime,
            endTime: this.endTime,
            date: this.date,
            seat: this.seat,
            cost: this.cost,
            origin: this.origin,
            destination: this.destination
          })
          .then(res => {
            console.log(res);
            if(res.data){
              this.$notify({
                title: "添加成功",
                type: "success"
              });
            }else{
              this.$notify({
                title: "添加失败",
                type: "warning"
              });
            }
          }).catch(res => {
            console.log(res);
             this.$notify({
                title: "添加失败",
                type: "warning"
              });
          })
      }
    }
  }
};
</script>
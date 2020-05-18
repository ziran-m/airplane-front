<template>
  <div class="booking1">
    <el-table
      :data="this.$store.state.flightList.filter(data => !search || data.flightNumber.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
    >
      <el-table-column label="日期" prop="date" align="center" sortable>
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column label="航班号" prop="flightNumber" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.flightNumber }}</span>
        </template>
      </el-table-column>
      <el-table-column label="历时" prop="time" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.startTime }}—{{scope.row.endTime}}</span>
        </template>
      </el-table-column>

      <el-table-column label="路程" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.origin }}—>{{ scope.row.destination }}</span>
        </template>
      </el-table-column>

      <el-table-column label="型号" prop="moudel" align="center"></el-table-column>
      <el-table-column label="座位" prop="seat" align="center"></el-table-column>
      <el-table-column label="价格" prop="cost" align="center"></el-table-column>
      <el-table-column align="center">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: "",
      array:[]
    };
  },
  methods: {
    handleDelete(row) {
      this.$store.dispatch("actionsNewSelect", row);
      this.$http
        .post("http://localhost:3000/admin/deleteflight", {
          flightNumber: this.$store.state.select.flightNumber
        })
        .then(res => {
          this.$notify({
            title: "退票成功",
            type: "success"
          });
          this.$http
        .post("http://localhost:3000/admin/showflight", {})
        .then(res => {
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
        }).catch(res =>{});
    }
  }
};
</script>
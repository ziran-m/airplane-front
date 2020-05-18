<template>
  <div class="booking1">
    <el-table
      :data="this.$store.state.flightList.filter(data => !search || data.id.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
    >
      <el-table-column label="Date" sortable align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证号" prop="id" align="center"></el-table-column>
      <el-table-column label="姓名" prop="name" align="center"></el-table-column>
      <el-table-column label="性别" prop="sex" align="center"></el-table-column>
      <el-table-column label="联系方式" prop="tel" align="center"></el-table-column>
      <el-table-column label="航班号" prop="flightNumber" align="center"></el-table-column>
      <el-table-column label="username" prop="username" align="center"></el-table-column>
      <el-table-column label="下单时间" prop="time" align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.time }}</span>
        </template>
      </el-table-column>
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
      array: []
    };
  },
  methods: {
    handleDelete(row) {
      this.$store.dispatch("actionsNewSelect", row);
      this.$http
        .post("http://localhost:3000/admin/deleteorder", {
          id: this.$store.state.select.id,
          flightNumber: this.$store.state.select.flightNumber
        })
        .then(res => {
          if (res.data) {
            this.$notify({
                title: "删除成功",
                type: "success"
              });
            this.$http
              .post("http://localhost:3000/admin/showorder")
              .then(res => {
                if (res.data) {
                  res.data.forEach(element => {
                    this.array.push(element);
                  });

                  this.$store.dispatch("actionsnewFlightList", this.array);
                  this.array = [];
                }
              })
              .catch(res => {
                console.log(res);
              });
          }
        })
        .catch(res => {});
    }
  }
};
</script>
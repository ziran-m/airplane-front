<template>
  <div class="delete">
    <div class="delete1">
      <el-row>
        <el-col :span="24">
          <el-table
            :data="this.$store.state.flightList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            style="width: 100%"
          >
            <el-table-column label="日期" align="center">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.date }}</span>
              </template>
            </el-table-column>
            <el-table-column label="航班" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.flightNumber }}</span>
              </template>
            </el-table-column>

            <el-table-column label="路程" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.origin }}—>{{ scope.row.destination }}</span>
              </template>
            </el-table-column>
            <el-table-column label="时间" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.startTime }}—{{scope.row.endTime}}</span>
              </template>
            </el-table-column>
            <el-table-column label="身份证号" align="center" width="200px">
              <template slot-scope="scope">
                <span>{{ scope.row.id }}</span>
              </template>
            </el-table-column>
            <el-table-column label="乘客姓名" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="mini" type="danger" @click="handleDelete(scope.row)">退票</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[2, 4, 8]"
            :page-size="pagesize"
            :total="this.$store.state.flightList.length"
            layout="total, sizes, prev, pager, next, jumper"
          ></el-pagination>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      array:[],

      currentPage: 1,
      pagesize: 2
    };
  },
  methods: {
    handleSizeChange: function(val) {
      this.pagesize = val;
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
    },
    handleDelete(row) {
      this.$store.dispatch("actionsNewSelect",row)
      console.log(row.id);
      this.$http.post("http://localhost:3000/user/delete",{
        id:this.$store.state.select.id,
        flightNumber:this.$store.state.select.flightNumber
      }).then(res=>{
        this.$notify({
            title: "退票成功",
            type: "success"
          });
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
      }).catch(res=>{
        console.log(res);
      })
    }
  }
};
</script>

<style>
.delete {
  width: 100%;
  background: #00d0d4;
  height: 100%;
  zoom: 1;
  padding: 1.5% 0% 1.5% 0%;
}

.delete1 {
  height: 90%;
  padding: 0.7%;
  width: 95%;
  position: relative;
  z-index: 30;
  zoom: 1;
  display: block;
  background: #fff;
}
</style>
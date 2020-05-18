<template>
  <div class="planeList">
    <div class="planeList1">
    <!-- {{JSON.stringify(this.$store.state.flightList).replace(/\"/g, "").replace(/\:/g,"\:\"").replace(/\,/g,"\"\,").replace(/\}\]/g,"\"\}\]").replace(/\}\"\,\{/g,"\"\}\,\{")}} -->
    <el-table
      :data="this.$store.state.flightList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
      style="width: 100%"
      @row-click="select"
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
      <el-table-column label="型号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.moudel }}</span>
        </template>
      </el-table-column>
      <el-table-column label="起点" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.origin }}—>{{ scope.row.destination }}</span>
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.startTime }}—{{scope.row.endTime}}</span>
        </template>
      </el-table-column>
      <el-table-column label="价格" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.cost }}</span>
        </template>
      </el-table-column>
       <el-table-column label="余座" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.seat }}</span>
        </template>
      </el-table-column>
    </el-table>
    <el-row>
      <el-col :span="12">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[4, 8, 12, 16]"
          :page-size="pagesize"
          :total="this.$store.state.flightList.length"
          layout="total, sizes, prev, pager, next, jumper"
        ></el-pagination>
      </el-col>
      <el-col :span="12">
        <el-button-group>
          <el-button icon="el-icon-arrow-left" @click="back" :disabled="!enAble">返回</el-button>
          <el-button @click="depart" :disabled="enAble">
            出发
            <i class="el-icon-arrow-right el-icon--right"></i>
          </el-button>
        </el-button-group>
      </el-col>
    </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      array:this.$store.state.flightList,
      currentPage: 1,
      pagesize: 4,
      enAble:true
    };
  },
  methods: {
    handleSizeChange: function(val) {
      this.pagesize = val;
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
    },
    select:function(row, event, column) {  
      // console.log(row)
      this.$store.dispatch("actionsNewSelect",row)
      this.$router.push({path:'/select'});
    },
    depart(){
      this.enAble=true;
      this.$store.dispatch("actionsnewFlightList", this.array);
    },
    back(){
      this.enAble=false;
      this.array = this.$store.state.flightList;
      this.$store.dispatch("actionsnewFlightList", this.$store.state.backFlightList);
    }
  }
};
</script>

<style>
.el-table-column {
  align-content: center;
}
.planeList {
  width: 100%;
  background: #00d0d4;
  height: 100%;
  zoom: 1;
  padding: 1.5% 0% 1.5% 0%;
}

.planeList1 {
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
<template>
  <div class="booking1">
    <el-table
      :data="this.$store.state.flightList.filter(data => !search || data.id.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
    >
      <el-table-column label="username" prop="username" align="center"></el-table-column>
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
      console.log(row)
      this.$store.dispatch("actionsNewSelect", row);
      this.$http
        .post("http://localhost:3000/admin/deleteuser", {
          username: this.$store.state.select.username
        })
        .then(res => {
          console.log(res.data)
          if (res.data) {
            this.$http
              .post("http://localhost:3000/admin/showuser")
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
        .catch(res => {
          console.log(res);
        });
    }
  }
};
</script>
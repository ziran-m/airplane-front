<template>
  <div id="app">
    <el-row>
      <el-col :span="24" style="height:80px">
        <myHead></myHead>
      </el-col>
    </el-row>
    <br>
    <router-view></router-view>
  </div>
</template>

<script>
import myHead from "@/components/Head";

export default {
  name: 'App',
  components: {
    myHead,
  },
   mounted: function () {
         //此方法刷新页面时也会执行
         window.addEventListener('beforeunload',()=>{
            localStorage.removeItem('token');
        });
    }
}
</script>

<style>
.el-row {
  width: 100%;
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  overflow-y: auto;
  height: 100%;
  width: 100%;
  position: fixed;
  background: url("./assets/back.jpg") center;
  background-size:cover;
  overflow-y: auto;
}

body {
  margin: 0;
  padding: 0;
}

::-webkit-scrollbar {      
  width: 0;
}
</style>

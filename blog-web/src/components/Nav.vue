<template>
  <!-- 导航条开始 -->
  <el-affix>
    <el-menu
        mode="horizontal"
        background-color="#3375b9"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="border-radius: 4px; border-bottom-width: 0"
    >
      <el-menu-item index="0">
        <router-link to="/" style="text-decoration: none">Fcidd</router-link>
      </el-menu-item>
      <el-menu-item index="1">
        <router-link to="/tools" style="text-decoration: none">工具集合</router-link>
      </el-menu-item>
    </el-menu>
    <router-view/>
  </el-affix>
  <!-- 导航条结束 -->
</template>

<script setup lang="ts">

</script>
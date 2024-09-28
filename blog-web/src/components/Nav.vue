<template>
  <!-- 导航条开始 -->
  <el-affix>
    <el-menu
        mode="horizontal"
        background-color="#0d6efd"
        text-color="#fff"
        active-text-color="#ffd04b"
    >
      <el-menu-item index="0">
        <router-link to="/" style="text-decoration: none">Fcidd</router-link>
      </el-menu-item>
      <el-menu-item index="1">
        <router-link to="/login">
          Processing Center
        </router-link>
      </el-menu-item>
      <el-sub-menu index="2">
        <template #title>Workspace</template>
        <el-menu-item index="2-1">item one</el-menu-item>
        <el-menu-item index="2-2">item two</el-menu-item>
        <el-menu-item index="2-3">item three</el-menu-item>
        <el-sub-menu index="2-4">
          <template #title>item four</template>
          <el-menu-item index="2-4-1">item one</el-menu-item>
          <el-menu-item index="2-4-2">item two</el-menu-item>
          <el-menu-item index="2-4-3">item three</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-menu-item index="3" disabled>Info</el-menu-item>
      <el-menu-item index="4">Orders</el-menu-item>
    </el-menu>
  </el-affix>
  <!-- 导航条结束 -->
</template>

<script setup lang="ts">

</script>
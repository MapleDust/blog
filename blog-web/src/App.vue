<script lang="ts" setup>
import Nav from './components/Nav.vue';
</script>

<template>
  <!-- 背景视频开始 -->
  <video class="bg_video" autoplay loop muted>
    <source src="./assets/video/bg.mp4">
  </video>
  <!-- 背景视频结束 -->
  <Nav />
</template>

<style scoped>
.bg_video {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
  height: auto;
  width: auto;
  /*加滤镜*/
  filter: blur(2px);
  /*-webkit-filter: grayscale(100%);*/
  /*filter:grayscale(100%); //背景灰度设置*/
  z-index: -11
}
</style>
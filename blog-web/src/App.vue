<script lang="ts" setup>
import Nav from "./components/Nav.vue"
</script>

<template>
  <Nav />
  <!-- 背景视频开始 -->
  <video class="bg_video" autoplay loop muted>
    <source src="./assets/video/bg.mp4">
  </video>
  <!-- 背景视频结束 -->
  <!-- 主页中部信息结束 -->
  <div class="splash">
    <h1 class="splash_head">
      Fcidd Blog
    </h1>
  </div>
  <!-- 主页中部信息结束 -->
</template>

<style scoped>
.splash {
  width: 80%;
  height: 50%;
  margin: auto;
  position: absolute;
  top: 100px;
  left: 0;
  bottom: 0;
  right: 0;
  text-align: center;
}
.splash_head {
  color: #fff;
  padding: 0.8em 0.5em 1em;
  line-height: 1.5em;
  margin: 0.2em 0 0.5em;
  text-shadow: 0 4px 3px rgb(0 0 0 / 40%), 0 8px 13px rgb(0 0 0 / 10%), 0 18px 23px rgb(0 0 0 / 10%);
}
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